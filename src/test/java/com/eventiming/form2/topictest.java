package com.eventiming.form2;

import com.eventiming.form2.DAO.postDao;
import com.eventiming.form2.DAO.topicDao;
import com.eventiming.form2.DAO.topiccontextDao;
import com.eventiming.form2.pojo.topiccontext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;

@SpringBootTest
public class topictest {
    @Autowired
    private topicDao topicdao;


    @Test
    public void test(){
        String title = "123";
        BigInteger userid = new BigInteger("123123");
        String username = "wzq";
        Timestamp timestamp =new Timestamp(1111);
        Timestamp posttime = timestamp;
        Timestamp lastreplyedtime = timestamp;
        Timestamp lasteditedtime = timestamp;
        topicdao.selectTopicById(userid);
        Timestamp t = new Timestamp(0);
        topicdao.selectTopicByEditedTime(t);
        topicdao.selectTopicByPostedTime(t);
        topicdao.selectTopicByTitle(title);
        topicdao.selectTopicByUser(userid);
        topicdao.updateTopicLastEditedTime(userid, t);
        topicdao.updateTopicLastReplyedTime(userid, t);
        //topicdao.insertTopic(title, userid, username, posttime, lastreplyedtime, lasteditedtime);
    }

    @Autowired
    private topiccontextDao topictext;
    @Test
    public void test2(){
        BigInteger userid = new BigInteger("1");
        //topictext.insertContext(userid,"1313123");
//        topictext.selectContext(userid);
//        topictext.updateContext(userid, "hhhhhh");
        topictext.deleteContext(userid);

    }

    @Autowired
    private postDao postdao;
    @Test
    public void test3(){
        BigInteger topicid =new BigInteger("123");
        BigInteger userid = new BigInteger("1233");
        String postcontext = "hhhh";
        BigInteger p =new BigInteger("1");
        Timestamp timestamp = new Timestamp(1111);
        postdao.insertPost(topicid, userid, postcontext, timestamp);
        postdao.updatePost(topicid, "222222");
        postdao.selectPostById(p);
        postdao.selectPostsByTopicId(topicid);
        postdao.selectPostsByUserId(userid);
    }
}
