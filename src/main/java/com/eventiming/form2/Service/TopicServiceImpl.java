package com.eventiming.form2.Service;

import com.eventiming.form2.Cache.TopicCache;
import com.eventiming.form2.DAO.*;
import com.eventiming.form2.pojo.*;
import com.eventiming.form2.util.BeforeTimeStamp;
import com.eventiming.form2.util.Cache.PostHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
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

    @Autowired
    private postinfoDao postinfodao;

    @Autowired
    private BeforeTimeStamp beforeTimeStamp;

    @Autowired
    private TopicCache topicCache;


    public int createTopic(long userid, String title, String context){
        user u = userd.selectUserById(userid);
        topicCache.createTopic(title, userid ,u.getUsername(), context);
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        topic t =new topic();
//        t.setTitle(title);
//        t.setUsername(u.getUsername());
//        t.setUserid(userid);
//        t.setLastreplyedtime(timestamp);
//        t.setPosttime(timestamp);
//        t.setLasteditedtime(timestamp);
//        topicDao.insertTopicObject(t);
//        topiccontextdao.insertContext(t.getTopicid(),context);
//        userstatusdao.updateUserTopicNum(userid, timestamp);
        return 1;
    }
    public int deleteTopic(long userid, long topicid){
//        topic t = topicDao.selectTopicById(topicid);
//        if(t.getUserid() == userid){
//            topicDao.deleteTopic(topicid);
//            topiccontextdao.deleteContext(topicid);
//            return 1;
//        }
//        return 0;
        if(topicCache.visitTopic(topicid).getUserid() == userid)
            return topicCache.deleteTopic(topicid);
        return 0;
    }
    public int updateTopicTitleById(long userid, long topicid, String newtitle){
//        topic t = topicDao.selectTopicById(topicid);
//        if(t.getUserid() == userid){
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//            topicDao.updateTopicTitle(topicid, newtitle);
//            topicDao.updateTopicLastEditedTime(topicid, timestamp);
//            return 1;
//        }
//        return 0;
        if(topicCache.visitTopic(topicid).getUserid() == userid){
            return topicCache.updatetitle(topicid, newtitle);
        }
        return 0;
    }
    public int updateTopicContextById(long userid, long topicid, String context){
//        topic t = topicDao.selectTopicById(topicid);
//        if(t.getUserid() == userid){
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//            topiccontextdao.updateContext(topicid, context);
//            topicDao.updateTopicLastEditedTime(topicid, timestamp);
//            return 1;
//        }
//        return 0;
        if(topicCache.visitTopic(topicid).getUserid() == userid){
            return topicCache.updatecontext(topicid, context);
        }
        return 0;
    }

    public ResponseData<MemoryTopic> selectMemoryTopicById(long topicid){
        // 避免更改下面的代码，直接重写
        ResponseData<MemoryTopic> responseData = new ResponseData<>();
        MemoryTopic t = topicCache.visitTopic(topicid);
        if(t != null){
            responseData.setCode("100");
            responseData.setData(t);
        }
        responseData.setCode("200");
        return responseData;

    }
    public ResponseData<topic> selectTopicById(long topicid){
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
    public ResponseData<List<post>> selectTopicContextById(long topicid){
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
    public ResponseData<List<topic>> selectTopicByUserId(long userid){

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

    public int likeTopic(long topicid){
        return topicCache.updatelikenum(topicid);
    }
    public int storeTopic(long topicid){
        return topicCache.updatestorenum(topicid);
    }

    public int forwardTopic(long topicid){
        return  topicCache.updateforward(topicid);
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

    private PostHashMap getPostsByTopicId(long topicid){
        List<post> posts = postdao.selectPostsByTopicId(topicid);
        Iterator<post> postIterator = posts.iterator();
        // TODO 初始化缓冲层
        PostHashMap postHashMap = new PostHashMap();
        while(postIterator.hasNext()){
            post postcurrent =postIterator.next();
            fastpost fp =new fastpost(postcurrent,postinfodao.selectPostInfoByID(postcurrent.getPostid()));
            postHashMap.addpostTree(fp);
        }
        return postHashMap;
    }

}
