package com.eventiming.form2.DAO;

import com.eventiming.form2.pojo.post;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface postDao {
    int insertPost(BigInteger topicid,
                   BigInteger userid,
                   String postcontext,
                   Timestamp timestamp);
    int deletePostById(BigInteger postid);
    int updatePost(BigInteger postid , String postcontext);
    post selectPostById(BigInteger postid);
    List<post> selectPostsByTopicId(BigInteger topicid);
    List<post> selectPostsByUserId(BigInteger userid);
}
