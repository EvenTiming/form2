package com.eventiming.form2.DAO;

import com.eventiming.form2.pojo.postinfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface postinfoDao {
    int insertPostInfo(long postid);
    int deletePostInfo(long postid);
    postinfo selectPostInfoByID(long postid);
    int updatePostInfo(long postid, long postlikenum);
}
