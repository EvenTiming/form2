package com.eventiming.form2.Controller;


import com.eventiming.form2.Service.UserServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private UserServiceImpl userService;
    @GetMapping("/rg")
    public int Register(@Param("username") String username, @Param("password") String password,
                        @Param("email") String email){

        return userService.Register(username, password, email);
    }
}
