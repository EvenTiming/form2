package com.eventiming.form2.Service;

import com.eventiming.form2.pojo.ResponseData;
import com.eventiming.form2.pojo.post;
import com.eventiming.form2.pojo.topic;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

public interface TopicService {
    int createTopic(BigInteger userid, String title,String context);
    int deleteTopic(BigInteger userid, BigInteger topicid);
    int updateTopicTitleById(BigInteger userid, BigInteger topicid, String newtitle);
    int updateTopicContextById(BigInteger userid, BigInteger topicid, String context);
    ResponseData<topic> selectTopicById(BigInteger topicid);
    ResponseData<List<post>> selectTopicContextById(BigInteger topicid);
    ResponseData<List<topic>> selectTopicByUserId(BigInteger userid);
    ResponseData<List<topic>> selectTopicByTitle(String title);
    ResponseData<List<topic>> selectTopicByReplyedTime(Timestamp timestamp);
    ResponseData<List<topic>> selectTopicByEditedTime(Timestamp timestamp);

    ResponseData<List<topic>> selectIndexTopics(int num, int offset);

}
