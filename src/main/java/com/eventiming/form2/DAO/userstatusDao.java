package com.eventiming.form2.DAO;

import com.eventiming.form2.pojo.userstatus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;



import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
@Mapper
@Repository
public interface userstatusDao {

    int insertUserStatus(long userid, int usergroup, Timestamp lastactivetime ,int topicnum, int threadnum);
//   1. 根据用户id返回基本信息
    userstatus selectUserStatusById(long userid);
//	2. 根据用户id返回用户组
    int selectUserGroupById(long userid);
    int updateUserGroupById(long userid, int usergroup);
    int updateUserTopicNum(long userid, Timestamp timestamp);
    int updateUserThreadNum(long userid, Timestamp timestamp);
    List<Long> selectUserIdByGroup(int group);
    List<Long> selectUserIdByTopicNum(int min, int max);
    List<Long> selectUserIdByThreadNum(int min, int max);
    List<Long> selectUserIdBytopTopicNum(int num, int offset);
    List<Long> selectUserIdBytopThreadNum(int num, int offset);
}
