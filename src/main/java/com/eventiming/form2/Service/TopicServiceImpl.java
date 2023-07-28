package com.eventiming.form2.Service;

import com.eventiming.form2.DAO.topicDao;
import com.eventiming.form2.DAO.topiccontextDao;
import com.eventiming.form2.DAO.userdao;
import com.eventiming.form2.pojo.ResponseData;
import com.eventiming.form2.pojo.topic;
import com.eventiming.form2.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService{
    @Autowired
    private userdao userd;
    @Autowired
    private topicDao topicDao;
    @Autowired
    private topiccontextDao topiccontextdao;
    public int createTopic(BigInteger userid, String title, String context){
        user u = userd.selectUserById(userid);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        topic t =new topic();
        t.setTitle(title);
        t.setUsername(u.getUsername());
        t.setUserid(userid);
        t.setLastreplyedtime(timestamp);
        t.setPosttime(timestamp);
        t.setLasteditedtime(timestamp);
        topicDao.insertTopicObject(t);
//        System.out.println(t.getTopicid());
        topiccontextdao.insertContext(t.getTopicid(),context);
        return 1;

    }
    public int deleteTopic(BigInteger userid, BigInteger topicid){
        return 0;
    }
    public int updateTopicTitleById(BigInteger userid, BigInteger topicid, String newtitle){
        return 0;
    }
    public int updateTopicContextById(BigInteger userid, BigInteger topicid, String context){
        return 0;
    }
    public ResponseData<topic> selectTopicById(BigInteger topicid){
        return null;
    }
    public ResponseData<List<BigInteger>> selectTopicByUserId(BigInteger userid){
        return null;
    }
    public ResponseData<List<BigInteger>> selectTopicByTitle(String title){
        return null;
    }
}
