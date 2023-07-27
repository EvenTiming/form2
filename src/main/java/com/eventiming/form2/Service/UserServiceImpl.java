package com.eventiming.form2.Service;

import com.eventiming.form2.DAO.userdao;
import com.eventiming.form2.DAO.userstatusDao;
import com.eventiming.form2.util.UserIdGenerate;
import org.apache.catalina.User;
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
    private UserIdGenerate userIdGenerate;

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
            return 0;
        }
        return 1;
    }

    public int ChangeUserName(BigInteger userid, String newName) {
        return 0;
    }

    public int ChangePassword(BigInteger userid, String newPassword) {
        return 0;
    }

    public int ChangeEmail(BigInteger userid, String newEmail) {
        return 0;
    }

    public String UserInfo(BigInteger userid) {
        return null;
    }
}
