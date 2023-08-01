package com.eventiming.form2.Controller;

import com.eventiming.form2.Service.AdminServiceImpl;
import com.eventiming.form2.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

// TODO 更新管理员功能，不同权限的用户操作仍需设计
    @PostMapping("/blockuser")
    public int blockUser(BigInteger userid){
        return adminService.blockUser(userid);
    }
    @PostMapping("/deleteuser")
    public int deleteUser(BigInteger userid){
        return adminService.deleteUser(userid);
    }

    @PostMapping("/deleteTopic")
    public int deleteTopic(BigInteger topicid){
        return adminService.deleteTopic(topicid);
    }


    @PostMapping("/deletePost")
    public int deletePost(BigInteger postid){
        return adminService.deletePost(postid);
    }
}
