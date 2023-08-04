package com.eventiming.form2.DAO;

import com.eventiming.form2.pojo.post;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface postDao {
    int insertPost(long topicid,
                   long userid,
                   String postcontext,
                   Timestamp timestamp);
    int deletePostById(long postid);
    int updatePost(long postid , String postcontext);
    post selectPostById(long postid);
    List<post> selectPostsByTopicId(long topicid);
    List<post> selectPostsByUserId(long userid);
}
