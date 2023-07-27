package com.eventiming.form2.DAO;

import com.eventiming.form2.pojo.userstatus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
@Mapper
@Repository
public interface userstatusDao {

    int insertUserStatus(BigInteger userid, int usergroup, Timestamp lastactivetime ,int topicnum, int threadnum);
//   1. 根据用户id返回基本信息
    userstatus selectUserStatusById(BigInteger userid);
//	2. 根据用户id返回用户组
    int selectUserGroupById(BigInteger userid);
    int updateUserGroupById(BigInteger userid, int usergroup);
    int updateUserTopicNum(BigInteger userid, Timestamp timestamp);
    int updateUserThreadNum(BigInteger userid, Timestamp timestamp);
    List<BigInteger> selectUserIdByGroup(int group);
    List<BigInteger> selectUserIdByTopicNum(int min, int max);
    List<BigInteger> selectUserIdByThreadNum(int min, int max);
    List<BigInteger> selectUserIdBytopTopicNum(int num, int offset);
    List<BigInteger> selectUserIdBytopThreadNum(int num, int offset);
}
