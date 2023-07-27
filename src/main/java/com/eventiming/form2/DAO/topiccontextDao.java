package com.eventiming.form2.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
@Mapper
@Repository
public interface topiccontextDao {
    int insertContext(BigInteger topicid, String text);
    int deleteContext(BigInteger topicid);
    int updateContext(BigInteger topicid, String context);
    String selectContext(BigInteger topicid);

}
