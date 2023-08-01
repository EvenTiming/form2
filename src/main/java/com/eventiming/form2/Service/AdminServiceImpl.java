package com.eventiming.form2.Service;

import com.eventiming.form2.DAO.postDao;
import com.eventiming.form2.DAO.topicDao;
import com.eventiming.form2.DAO.userdao;
import com.eventiming.form2.DAO.userstatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private userstatusDao userstatusdao;

    @Autowired
    private userdao userd;

    @Autowired
    private topicDao topicdao;

    @Autowired
    private postDao postdao;
    public int blockUser(BigInteger userid){
        return userstatusdao.updateUserGroupById(userid, 2);
    }
    public int deleteUser(BigInteger userid){
        userstatusdao.updateUserGroupById(userid,3);
        return userd.deleteUserById(userid);
    }
    public int deleteTopic(BigInteger topicid){
        return topicdao.deleteTopic(topicid);
    }
    public int deletePost(BigInteger postid){
        return postdao.deletePostById(postid);
    }
}
