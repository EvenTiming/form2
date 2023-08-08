package com.eventiming.form2.DAO;

import com.eventiming.form2.pojo.topic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface topicDao {
    int insertTopic(String title,
                    long userid,
                    String username,
                    Timestamp posttime,
                    Timestamp lastreplyedtime,
                    Timestamp lasteditedtime);
    int insertTopicObject(topic t);
    int deleteTopic(long topicid);

    int updateTopicTitle(long topicid,
                         String newtitle);

    int updateTopicLastReplyedTime(long topicid,
                                   Timestamp timestamp);

    int updateTopicLastEditedTime(long topicid,
                                  Timestamp timestamp);

    List<topic> selectTopicByUser(long userid);
    List<topic> selectTopicByTitle(String title);
    topic selectTopicById(long topicid);

    List<topic> selectTopicByPostedTime(Timestamp posttime);
    List<topic> selectTopicByEditedTime(Timestamp editedtime);
    List<topic> selectIndexTopic(int num, int offset);

}
