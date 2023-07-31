package com.eventiming.form2.Service;

import com.eventiming.form2.DAO.*;
import com.eventiming.form2.pojo.ResponseData;
import com.eventiming.form2.pojo.post;
import com.eventiming.form2.pojo.topic;
import com.eventiming.form2.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService{
    @Autowired
    private userdao userd;

    @Autowired
    private topicDao topicDao;

    @Autowired
    private topiccontextDao topiccontextdao;

    @Autowired
    private userstatusDao userstatusdao;

    @Autowired
    private postDao postdao;

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
        topiccontextdao.insertContext(t.getTopicid(),context);
        userstatusdao.updateUserTopicNum(userid, timestamp);
        return 1;

    }
    public int deleteTopic(BigInteger userid, BigInteger topicid){
        topic t = topicDao.selectTopicById(topicid);
        if(t.getUserid().equals(userid)){
            topicDao.deleteTopic(topicid);
            topiccontextdao.deleteContext(topicid);
            return 1;
        }
        return 0;
    }
    public int updateTopicTitleById(BigInteger userid, BigInteger topicid, String newtitle){
        topic t = topicDao.selectTopicById(topicid);
        if(t.getUserid().equals(userid)){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            topicDao.updateTopicTitle(topicid, newtitle);
            topicDao.updateTopicLastEditedTime(topicid, timestamp);
            return 1;
        }
        return 0;
    }
    public int updateTopicContextById(BigInteger userid, BigInteger topicid, String context){
        topic t = topicDao.selectTopicById(topicid);
        if(t.getUserid().equals(userid)){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            topiccontextdao.updateContext(topicid, context);
            topicDao.updateTopicLastEditedTime(topicid, timestamp);
            return 1;
        }
        return 0;
    }
    public ResponseData<topic> selectTopicById(BigInteger topicid){
        ResponseData<topic> responseData = new ResponseData<>();
        topic  t= topicDao.selectTopicById(topicid);
        if(t != null){
            responseData.setCode("100");
            responseData.setData(t);
            return responseData;
        }
        responseData.setCode("200");
        return responseData;
    }
    public ResponseData<List<post>> selectTopicContextById(BigInteger topicid){
        ResponseData<List<post>> responseData =new ResponseData<>();
        String t =topiccontextdao.selectContext(topicid);
       if(t!=null){
           responseData.setCode("100");
           HashMap<String, String> map = new HashMap<>();
           map.put("context", t);
           responseData.setData(postdao.selectPostsByTopicId(topicid));
           return responseData;
       }
        responseData.setCode("200");
        return responseData;
    }
    public ResponseData<List<topic>> selectTopicByUserId(BigInteger userid){

        List<topic> list = topicDao.selectTopicByUser(userid);
        return Response(list);
    }
    public ResponseData<List<topic>> selectTopicByTitle(String title){

        List<topic> list = topicDao.selectTopicByTitle(title);
        return Response(list);

    }

    public ResponseData<List<topic>> selectTopicByReplyedTime(Timestamp timestamp){

        List<topic> list = topicDao.selectTopicByPostedTime(timestamp);
        return Response(list);
    }
    public ResponseData<List<topic>> selectTopicByEditedTime(Timestamp timestamp){
        List<topic> list = topicDao.selectTopicByEditedTime(timestamp);
        return Response(list);

    }

    public ResponseData<List<topic>> selectIndexTopics(int num, int offset){

        List<topic> list = topicDao.selectIndexTopic(num,offset);
        return Response(list);
    }
    private ResponseData<List<topic>> Response( List<topic> list){
        ResponseData<List<topic>> responseData =new ResponseData<>();
        if(!list.isEmpty()){
            responseData.setCode("100");
            responseData.setData(list);
            return responseData;
        }
        responseData.setCode("200");
        return responseData;
    }

}
