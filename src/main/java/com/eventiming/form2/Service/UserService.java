package com.eventiming.form2.Service;

import com.eventiming.form2.DAO.userdao;
import com.eventiming.form2.pojo.userstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;


public interface UserService {

    int Register(String username, String password, String email);

    userstatus LoginByUserName(String username, String password);

    userstatus LoginByEmail(String email, String password);

    int ChangeUserName(BigInteger userid , String newName);

    int ChangePassword(BigInteger userid , String newPassword);

    int ChangeEmail(BigInteger userid, String newEmail);

    String UserInfo(BigInteger userid);

}
