package com.eventiming.form2.Service;

import com.eventiming.form2.pojo.ResponseData;
import com.eventiming.form2.pojo.post;
import com.eventiming.form2.pojo.topic;


import java.sql.Timestamp;
import java.util.List;

public interface TopicService {
    int createTopic(long userid, String title,String context);
    int deleteTopic(long userid, long topicid);
    int updateTopicTitleById(long userid, long topicid, String newtitle);
    int updateTopicContextById(long userid, long topicid, String context);
    ResponseData<topic> selectTopicById(long topicid);
    ResponseData<List<post>> selectTopicContextById(long topicid);
    ResponseData<List<topic>> selectTopicByUserId(long userid);
    ResponseData<List<topic>> selectTopicByTitle(String title);
    ResponseData<List<topic>> selectTopicByReplyedTime(Timestamp timestamp);
    ResponseData<List<topic>> selectTopicByEditedTime(Timestamp timestamp);

    ResponseData<List<topic>> selectIndexTopics(int num, int offset);

}
