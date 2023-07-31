package com.eventiming.form2.DAO;

import com.eventiming.form2.pojo.topic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface topicDao {
    int insertTopic(String title,
                    BigInteger userid,
                    String username,
                    Timestamp posttime,
                    Timestamp lastreplyedtime,
                    Timestamp lasteditedtime);
    int insertTopicObject(topic t);
    int deleteTopic(BigInteger topicid);

    int updateTopicTitle(BigInteger topicid,
                         String newtitle);

    int updateTopicLastReplyedTime(BigInteger topicid,
                                   Timestamp timestamp);

    int updateTopicLastEditedTime(BigInteger topicid,
                                  Timestamp timestamp);

    List<topic> selectTopicByUser(BigInteger userid);
    List<topic> selectTopicByTitle(String title);
    topic selectTopicById(BigInteger topicid);

    List<topic> selectTopicByPostedTime(Timestamp posttime);
    List<topic> selectTopicByEditedTime(Timestamp editedtime);





}
