package com.eventiming.form2.Service;

import com.eventiming.form2.DAO.postDao;
import com.eventiming.form2.DAO.topicDao;
import com.eventiming.form2.DAO.userdao;
import com.eventiming.form2.DAO.userstatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public int blockUser(long userid){
        return userstatusdao.updateUserGroupById(userid, 2);
    }
    public int deleteUser(long userid){
        userstatusdao.updateUserGroupById(userid,3);
        return userd.deleteUserById(userid);
    }
    public int deleteTopic(long topicid){
        return topicdao.deleteTopic(topicid);
    }
    public int deletePost(long postid){
        return postdao.deletePostById(postid);
    }
}
