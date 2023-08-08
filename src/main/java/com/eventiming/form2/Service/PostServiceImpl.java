package com.eventiming.form2.Service;

import com.eventiming.form2.Cache.TopicCache;
import com.eventiming.form2.DAO.postDao;
import com.eventiming.form2.pojo.post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private postDao postdao;
    @Autowired
    private TopicCache topicCache;
    public int addpost(long userid, long topicid, String postcontext){
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        postdao.insertPost(topicid,userid,postcontext,timestamp);
//        return 1;
        return topicCache.createPost(topicid, userid, postcontext);
    }

    public int deletepost(long topicid, long postid){
//        postdao.deletePostById(postid);
//        return 0;
        return topicCache.deletePost(topicid, postid);
    }

    public int likepost(long postid){
        return topicCache.updatelikenum(postid);
    }

    // TODO 支持更改

    public List<post> selectPostByTopic(long topicid){
        return postdao.selectPostsByTopicId(topicid);
    }

    public List<post> selectPostByUser(long userid){
        return postdao.selectPostsByUserId(userid);
    }
}
