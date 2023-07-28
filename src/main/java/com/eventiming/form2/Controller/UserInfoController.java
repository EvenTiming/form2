package com.eventiming.form2.Controller;

import com.eventiming.form2.DAO.userdao;
import com.eventiming.form2.DAO.userstatusDao;
import com.eventiming.form2.Service.UserServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class UserInfoController {
    @Autowired
    private userdao userd;

    @Autowired
    private userstatusDao userstatusdao;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/changeName")
    public int changeName(@Param("userid")BigInteger userid, @Param("newusername") String newusername){
        if(userid.equals("")||newusername.equals("")){
            return 0;
        }
        return userService.ChangeUserName(userid, newusername);
    }

    @PostMapping("/changeEmail")
    public int changeEmail(@Param("userid")BigInteger userid, @Param("newemail") String newemail){
        if(userid.equals("")||newemail.equals("")){
            return 0;
        }
        return userService.ChangeEmail(userid, newemail);
    }

    @PostMapping("/changePassword")
    public int chagePassword(@Param("userid")BigInteger userid, @Param("newpassword") String newpassword){
        if(userid.equals("")||newpassword.equals("")){
            return 0;
        }
        return userService.ChangePassword(userid, newpassword);
    }

    @GetMapping("/user")
        public String userInfo(@Param("userid") BigInteger userid){
            return userService.UserInfo(userid);
        }

}
