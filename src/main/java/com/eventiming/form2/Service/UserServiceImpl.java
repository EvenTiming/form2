package com.eventiming.form2.Service;

import com.eventiming.form2.DAO.userdao;
import com.eventiming.form2.DAO.userstatusDao;
import com.eventiming.form2.pojo.ResponseData;
import com.eventiming.form2.pojo.user;
import com.eventiming.form2.pojo.userstatus;
import com.eventiming.form2.util.TokenMangeer;
import com.eventiming.form2.util.UserIdGenerate;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private userdao userd;
    @Autowired
    private userstatusDao userstatusdao;
    @Autowired
    private UserIdGenerate userIdGenerate;
    @Autowired
    private TokenMangeer tokenMangeer;

    public int Register(String username, String password, String email) {
        try{
            if(userd.selectUserByUsername(username) == null){
                BigInteger userid = userIdGenerate.generateUniqueId();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                userd.insertUser(userid, username,password,email,timestamp);
                userstatusdao.insertUserStatus(userid,1,timestamp,0,0);
            }
            else {
                return 2;
            }
        }catch (Exception e){
            return 3;
        }
        return 1;
    }

    public ResponseData<userstatus> LoginByUserName(String username, String password){
        ResponseData<userstatus> responseData =new ResponseData<userstatus>();
        user result = userd.selectUserByUsername(username);
        if(result == null){
            responseData.setCode("500");
            return responseData;
        } else{
            if(result.getPassword().equals(password)){
                responseData.setCode("100");
                responseData.setData(userstatusdao.selectUserStatusById(result.getUserid()));
                responseData.setToken(tokenMangeer.getToken(result.getUserid()));
                return responseData;
            } else{
                responseData.setCode("600");
                return responseData;
            }
        }
    }

    public ResponseData<userstatus> LoginByEmail(String email, String password){
        ResponseData<userstatus> responseData =new ResponseData<userstatus>();
        user result = userd.selectUserByEmail(email);
        if(result == null){
            responseData.setCode("500");
            return responseData;
        } else{
            if(result.getPassword().equals(password)){
                responseData.setCode("100");
                responseData.setData(userstatusdao.selectUserStatusById(result.getUserid()));
                responseData.setToken(tokenMangeer.getToken(result.getUserid()));
                return responseData;
            } else{
                responseData.setCode("600");
                return responseData;
            }
        }
    }

    public int ChangeUserName( BigInteger userid, String newName) {
        user result = userd.selectUserByUsername(newName);
        if(result == null){
            userd.updateUserNameById(userid, newName);
            return 1;
        }
        return 2;
    }

    public int ChangePassword(BigInteger userid, String newPassword) {
        try {userd.updateUserPasswordByID(userid, newPassword);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    public int ChangeEmail(BigInteger userid, String newEmail) {
        user result = userd.selectUserByUsername(newEmail);
        if(result == null){
            userd.updateUserEmailById(userid, newEmail);
            return 1;
        }
        return 2;
    }
    public String UserInfo(BigInteger userid) {
        return userd.selectUserById(userid).toString();
    }
}
