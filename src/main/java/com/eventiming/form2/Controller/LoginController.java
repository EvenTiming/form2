package com.eventiming.form2.Controller;


import com.eventiming.form2.Service.UserServiceImpl;
import com.eventiming.form2.pojo.userstatus;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private UserServiceImpl userService;
    @PostMapping("/rg")
    public int Register(@Param("username") String username, @Param("password") String password,
                        @Param("email") String email){
        if(username.equals("")||password.equals("")||email.equals("")){
            return 0;
        }
        return userService.Register(username, password, email);
    }

    @PostMapping("/loginByUserName")
    public userstatus LoginByUserName(@Param("username") String username, @Param("password") String password){
        if(username.equals("")||password.equals("")){
            return null;
        }
        return userService.LoginByUserName(username, password);
    }

    @PostMapping("/loginByEmail")
    public userstatus LoginByUserEmail(@Param("email") String email, @Param("password") String password){
        if(password.equals("")||email.equals("")){
            return null;
        }
        return userService.LoginByEmail(email, password);
    }
}
