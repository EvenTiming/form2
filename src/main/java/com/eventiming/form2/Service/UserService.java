package com.eventiming.form2.Service;

import com.eventiming.form2.DAO.userdao;
import com.eventiming.form2.pojo.ResponseData;
import com.eventiming.form2.pojo.userstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;


public interface UserService {

    int Register(String username, String password, String email);

    ResponseData<userstatus> LoginByUserName(String username, String password);

    ResponseData<userstatus> LoginByEmail(String email, String password);

    ResponseData<Object>  ChangeUserName(long userid , String newName);

    ResponseData<Object> ChangePassword(long userid , String newPassword);

    ResponseData<Object> ChangeEmail(long userid, String newEmail);

    ResponseData<userstatus> UserInfo(long userid);

    int LogOut(long userid);

}
