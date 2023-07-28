package com.eventiming.form2.Service;

import com.eventiming.form2.DAO.userdao;
import com.eventiming.form2.pojo.ResponseData;
import com.eventiming.form2.pojo.userstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;


public interface UserService {

    int Register(String username, String password, String email);

    ResponseData<userstatus> LoginByUserName(String username, String password);

    ResponseData<userstatus> LoginByEmail(String email, String password);

    ResponseData<Object>  ChangeUserName(BigInteger userid , String newName);

    ResponseData<Object> ChangePassword(BigInteger userid , String newPassword);

    ResponseData<Object> ChangeEmail(BigInteger userid, String newEmail);

    ResponseData<userstatus> UserInfo(BigInteger userid);

}
