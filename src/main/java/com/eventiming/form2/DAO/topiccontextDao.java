package com.eventiming.form2.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface topiccontextDao {
    int insertContext(long topicid, String text);
    int deleteContext(long topicid);
    int updateContext(long topicid, String context);
    String selectContext(long topicid);

}
