package com.eventiming.form2.Controller;


import com.eventiming.form2.Service.UserServiceImpl;
import com.eventiming.form2.pojo.ResponseData;
import com.eventiming.form2.pojo.userstatus;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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
    public ResponseData<userstatus> LoginByUserName(@Param("username") String username, @Param("password") String password){
        if(username.equals("")||password.equals("")){
            ResponseData<userstatus> responseData = new ResponseData<>();
            responseData.setCode("400");
            return responseData;
        }
        ResponseData<userstatus> responseData = userService.LoginByUserName(username, password);
        return  responseData;
    }

    @PostMapping("/loginByEmail")
    public  ResponseData<userstatus> LoginByUserEmail(@Param("email") String email, @Param("password") String password){
        if(password.equals("")||email.equals("")){
            ResponseData<userstatus> responseData = new ResponseData<>();
            responseData.setCode("400");
            return responseData;
        }
        ResponseData<userstatus> responseData = userService.LoginByEmail(email, password);
        return  responseData;
    }
}
