package com.eventiming.form2.DAO;


import com.eventiming.form2.pojo.topicinfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface topicinfoDao {
    int insertTopicInfo(long topicid);
    int deleteTopicInfo(long topicid);
    topicinfo selectTopicNumByID(long topicid);
    long updateTopicNumByID(long topicid, long likenum,
                            long storenum, long forward);
}
