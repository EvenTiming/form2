package com.eventiming.form2.Service;

import com.eventiming.form2.DAO.postDao;
import com.eventiming.form2.pojo.post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private postDao postdao;
    public int addpost(BigInteger userid, BigInteger topicid, String postcontext){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        postdao.insertPost(topicid,userid,postcontext,timestamp);
        return 1;
    }

    public int deletepost(BigInteger postid){
        postdao.deletePostById(postid);
        return 0;
    }

    // TODO 支持更改

    public List<post> selectPostByTopic(BigInteger topicid){
        return postdao.selectPostsByTopicId(topicid);
    }

    public List<post> selectPostByUser(BigInteger userid){
        return postdao.selectPostsByUserId(userid);
    }
}
