package com.eventiming.form2;

import com.eventiming.form2.DAO.userdao;
import com.eventiming.form2.DAO.userstatusDao;
import com.eventiming.form2.pojo.user;
import com.eventiming.form2.util.UserIdGenerate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.sql.Timestamp;

@SpringBootTest
class userstatusteset {
    @Autowired
    private userdao ud;
    @Autowired
    private userstatusDao usdao;
    @Autowired
    private UserIdGenerate uig;

    String username = "testname2";
    String password = "testpassword";

    String email = "testemail";


    @Test
    public void insertuser(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        BigInteger userid = uig.generateUniqueId();
        ud.insertUser(userid, username, password, email, timestamp);
        usdao.insertUserStatus(userid, 1, timestamp, 0,0);

    }

    @Test
    public void testUser(){
//        user u = ud.selectUserByUsername("allalalala");
//        BigInteger id = u.getUserid();
////        ud.selectUserPasswordByUsername();
////        ud.selectUserByUsername();
////        ud.deleteUserById();
////        ud.selectUserPasswordByUsername("allalalala");
////        ud.selectUserByEmail("jjjjjj@163.com");
//        Timestamp timestamp = new Timestamp(0);
//        ud.selectUsersByRegisterTimeById(timestamp);
////        ud.updateUserEmailById(id, "jjjjjj@163.com");
////        ud.updateUserNameById(id, "allalalala");
////        ud.updateUserPasswordByID(id, "123123123123131");
    }

    @Test
    public void testUserstatus(){
//        BigInteger id = new BigInteger("324571690427821951");
//        int a = usdao.selectUserGroupById(id);
//        System.out.println(a );
//        System.out.println(usdao.selectUserIdByGroup(1));
//        System.out.println(usdao.selectUserIdBytopThreadNum(1,0));
//        System.out.println(usdao.selectUserStatusById(id));
    }
}
