package com.eventiming.form2.Controller;

import com.eventiming.form2.Service.TopicService;
import com.eventiming.form2.Service.TopicServiceImpl;
import com.eventiming.form2.Service.UserServiceImpl;
import com.eventiming.form2.util.TokenMangeer;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class CreateTopicController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private TopicServiceImpl topicService;
    @Autowired
    private TokenMangeer tokenMangeer;
    @PostMapping("/create")
    public int CreateTopic(@Param("title") String title,
                           @Param("userid") BigInteger userid,
                           @Param("context") String context,
                           @Param("token") String token){
        if(title.equals("")||userid.toString().equals("")||context.equals("")){
            return 0;
        }
        if(tokenMangeer.confirmToken(userid, token)){
            return topicService.createTopic(userid, title ,context);
        }
        return 0;
    }

    @PostMapping("/changetitle")
    public int ChangeTitle(@Param("userid") BigInteger userid,
                       @Param("topicid") BigInteger topicid,
                       @Param("newtitle") String newtitle,
                       @Param("token") String token){
        if(topicid.equals("")||userid.toString().equals("")||token.equals("")||newtitle.equals("")){
            return 0;
        }
        if(tokenMangeer.confirmToken(userid, token)){
            return topicService.updateTopicTitleById(userid, topicid, newtitle);
        }
        return 0;
    }

    @PostMapping("/changecontext")
    public int ChangeContext(@Param("userid") BigInteger userid,
                             @Param("topicid") BigInteger topicid,
                             @Param("newcontext") String newcontext,
                             @Param("token" )String token
                             ){
        if(topicid.equals("")||userid.toString().equals("")||token.equals("")||newcontext.equals("")){
            return 0;
        }
        if(tokenMangeer.confirmToken(userid, token)){
            return topicService.updateTopicContextById(userid, topicid, newcontext);
        }
        return 0;
    }

}

