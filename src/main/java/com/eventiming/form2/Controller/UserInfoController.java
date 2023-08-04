package com.eventiming.form2.Controller;

import com.eventiming.form2.Service.UserServiceImpl;
import com.eventiming.form2.pojo.ResponseData;
import com.eventiming.form2.pojo.userstatus;
import com.eventiming.form2.util.TokenMangeer;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserInfoController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private TokenMangeer tokenMangeer;
    @PostMapping("/changeName")
    public ResponseData<Object> changeName(@Param("userid")long userid, @Param("newusername") String newusername,
                          @Param("token") String token){
        if((""+userid).equals("")||newusername.equals("")){
            ResponseData<Object> responseData =new ResponseData<>();
            responseData.setCode("400");
            return responseData;
        }

        if(!tokenMangeer.confirmToken(userid, token)){
            ResponseData<Object> responseData =new ResponseData<>();
            responseData.setCode("401");
            return responseData;
        }

        return userService.ChangeUserName(userid, newusername);
    }

    @PostMapping("/changeEmail")
    public ResponseData<Object> changeEmail(@Param("userid")long userid,
                                            @Param("newemail") String newemail,
                                            @Param("token") String token){
        if((""+userid).equals("")||newemail.equals("")){
            ResponseData<Object> responseData =new ResponseData<>();
            responseData.setCode("400");
            return responseData;
        }

        if(!tokenMangeer.confirmToken(userid, token)){
            ResponseData<Object> responseData =new ResponseData<>();
            responseData.setCode("401");
            return responseData;
        }
        return userService.ChangeEmail(userid, newemail);
    }

    @PostMapping("/changePassword")
    public ResponseData<Object> chagePassword(@Param("userid")long userid,
                                              @Param("newpassword") String newpassword,
                                              @Param("token") String token){
        if((""+userid).equals("")||newpassword.equals("")){
            ResponseData<Object> responseData =new ResponseData<>();
            responseData.setCode("400");
            return responseData;
        }

        if(!tokenMangeer.confirmToken(userid, token)){
            ResponseData<Object> responseData =new ResponseData<>();
            responseData.setCode("401");
            return responseData;
        }
        return userService.ChangePassword(userid, newpassword);
    }

    @GetMapping("/user")
        public ResponseData<userstatus> userInfo(@Param("userid") long userid,
                                                 @Param("token") String token){
        if((""+userid).equals("")||token.equals("")){
            ResponseData<userstatus> responseData =new ResponseData<>();
            responseData.setCode("400");
            return responseData;
        }

        if(!tokenMangeer.confirmToken(userid, token)){
            ResponseData<userstatus> responseData =new ResponseData<>();
            responseData.setCode("401");
            return responseData;
        }
        return userService.UserInfo(userid);
        }

}
