package com.eventiming.form2.Controller;

import com.eventiming.form2.Service.PostServiceImpl;
import com.eventiming.form2.util.TokenMangeer;
import org.apache.el.parser.Token;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class PostController {
    @Autowired
    private PostServiceImpl postService;
    @Autowired
    private TokenMangeer tokenMangeer;
    @PostMapping("/post")
    public int createPost(@Param("userid")BigInteger userid ,
                          @Param("topicid")BigInteger topicid,
                          @Param("postcontext") String postcontext,
                          @Param("token") String token){
        if(tokenMangeer.confirmToken(userid, token))
            return postService.addpost(userid, topicid, postcontext);
        return 0;
    }
}
