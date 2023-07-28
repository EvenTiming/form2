package com.eventiming.form2.Service;

import com.eventiming.form2.pojo.ResponseData;
import com.eventiming.form2.pojo.topic;

import java.math.BigInteger;
import java.util.List;

public interface TopicService {
    int createTopic(BigInteger userid, String title,String context);
    int deleteTopic(BigInteger userid, BigInteger topicid);
    int updateTopicTitleById(BigInteger userid, BigInteger topicid, String newtitle);
    int updateTopicContextById(BigInteger userid, BigInteger topicid, String context);
    ResponseData<topic> selectTopicById(BigInteger topicid);
    ResponseData<List<BigInteger>> selectTopicByUserId(BigInteger userid);
    ResponseData<List<BigInteger>> selectTopicByTitle(String title);

}
