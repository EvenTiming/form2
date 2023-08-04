package com.eventiming.form2.DAO;

import com.eventiming.form2.pojo.user;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.sql.Timestamp;
import java.util.List;


@Mapper
@Repository
public interface userdao {
    //增加用户
    int insertUser(long userid, String username, String password,
                   String email, Timestamp registertime);

    int insertUserObject(user u);

//  1. 根据用户名返回密码
    String selectUserPasswordByUsername(String username);
//	2. 根据用户邮箱返回密码
    String selectUserPasswordByEmail(String email);
//	3. 根据用户id/用户名/用户邮箱返回个人信息
    user selectUserById(long userid);
    user selectUserByUsername(String username);
    user selectUserByEmail(String email);
//	4. 返回符合注册时间的用户
    List<user> selectUsersByRegisterTimeById(Timestamp registertime);
//	6. 根据用户id更改用户名
    int updateUserNameById(long userid, String newUsername);
  //	7. 根据用户id更改密码
    int updateUserPasswordByID(long userid, String password);

    //据用户id更改用户邮箱
    int updateUserEmailById(long userid, String newEmail);
    //根据id删除用户
    int deleteUserById(long userid);

}
