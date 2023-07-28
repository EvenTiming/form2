package com.eventiming.form2.Controller;

import com.eventiming.form2.Service.UserServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateTopicController {
    @Autowired
    private UserServiceImpl userService;
    @PostMapping("/create")
    public int CreateTopic(@Param("title") String title,
                           @Param("userid") String userid,
                           @Param("context") String context){
        return 0;
    }
}
