package com.eventiming.form2.Service;
import com.eventiming.form2.DAO.userdao;
import com.eventiming.form2.DAO.userstatusDao;
import com.eventiming.form2.pojo.ResponseData;
import com.eventiming.form2.pojo.user;
import com.eventiming.form2.pojo.userstatus;
import com.eventiming.form2.util.Password2SHA;
import com.eventiming.form2.util.TokenMangeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.sql.Timestamp;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private userdao userd;
    @Autowired
    private userstatusDao userstatusdao;

    @Autowired
    private TokenMangeer tokenMangeer;

    @Autowired
    private Password2SHA password2SHA;


    public int Register(String username, String password, String email) {
        try{
            if(userd.selectUserByUsername(username) == null || userd.selectUserByEmail(email) == null){
                password = password2SHA.hashPassword(password);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                user u =new user();
                u.setEmail(email);
                u.setPassword(password);
                u.setUsername(username);
                u.setRegistertime(timestamp);
                userd.insertUserObject(u);
                userstatusdao.insertUserStatus(u.getUserid(),1,timestamp,0,0);
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
        password = password2SHA.hashPassword(password);
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
        password = password2SHA.hashPassword(password);
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

    public ResponseData<Object> ChangeUserName( BigInteger userid, String newName) {
        user result = userd.selectUserByUsername(newName);
        ResponseData<Object> responseData = new ResponseData<>();
        if(result == null){
            userd.updateUserNameById(userid, newName);
            responseData.setCode("100");
            return responseData;
        }
        responseData.setCode("501");
        return responseData;
    }

    public ResponseData<Object> ChangePassword(BigInteger userid, String newPassword) {
        newPassword = password2SHA.hashPassword(newPassword);
        user result = userd.selectUserById(userid);
        ResponseData<Object> responseData = new ResponseData<>();
        if(result == null){
            responseData.setCode("501");
            return responseData;
        }
        userd.updateUserPasswordByID(userid, newPassword);
        responseData.setCode("100");
        return responseData;
    }

    public ResponseData<Object> ChangeEmail(BigInteger userid, String newEmail) {
        user result = userd.selectUserByUsername(newEmail);
        ResponseData<Object> responseData = new ResponseData<>();
        if(result == null){
            userd.updateUserEmailById(userid, newEmail);
            responseData.setCode("100");
            return responseData;
        }
        responseData.setCode("501");
        return responseData;
    }
    public ResponseData<userstatus> UserInfo(BigInteger userid) {
        ResponseData<userstatus> responseData = new ResponseData<>();
        responseData.setCode("100");
        responseData.setData(userstatusdao.selectUserStatusById(userid));
        return responseData;
    }

   public int LogOut(BigInteger userid){
        tokenMangeer.removeToken(userid);
        return 1;
   }
}
