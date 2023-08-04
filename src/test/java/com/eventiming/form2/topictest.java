package com.eventiming.form2;

import com.eventiming.form2.DAO.postDao;
import com.eventiming.form2.DAO.topicDao;
import com.eventiming.form2.DAO.topiccontextDao;
import com.eventiming.form2.Service.TopicService;
import com.eventiming.form2.Service.TopicServiceImpl;
import com.eventiming.form2.pojo.topiccontext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Time;
import java.sql.Timestamp;

@SpringBootTest
public class topictest {
    @Autowired
    private topicDao topicdao;


    @Test
    public void test() {
        String title = "123";
        long userid = 123123;
        String username = "wzq";
        Timestamp timestamp = new Timestamp(1111);
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
    public void test2() {
        long userid = 1;
        //topictext.insertContext(userid,"1313123");
//        topictext.selectContext(userid);
//        topictext.updateContext(userid, "hhhhhh");
        topictext.deleteContext(userid);

    }

    @Autowired
    private postDao postdao;

    @Test
    public void test3() {
        long topicid = 123;
        long userid = 1233;
        String postcontext = "hhhh";
        long p = 1;
        Timestamp timestamp = new Timestamp(1111);
        postdao.insertPost(topicid, userid, postcontext, timestamp);
        postdao.updatePost(topicid, "222222");
        postdao.selectPostById(p);
        postdao.selectPostsByTopicId(topicid);
        postdao.selectPostsByUserId(userid);
    }

    @Autowired
    private TopicServiceImpl topicService;

    @Test
    public void test4() {
        String title = "hahahh";
        long userid = 5117;
//        topicService.createTopic(userid, title);
    }

    @Test
    public void test5(){
        for(int i = 0;i <20 ;i++){
            String title = "test" +i;
            String context = "test";
            long userid = 1;
            topicService.createTopic(userid, title, context);
        }

    }
    @Test
    public void test6(){
        long user1 = 1;
        long user2 = 2;
        long topicid = 10;
        long topicid2 = 11;
//        topicService.deleteTopic(user1, topicid);
//        topicService.deleteTopic(user2, topicid2);
//        topicService.updateTopicTitleById(user1, topicid2, "newtitle");
//        topicService.updateTopicContextById(user1, topicid2, "newcontext");
//        System.out.println(topicService.selectTopicContextById(topicid2));
//        System.out.println(topicService.selectTopicByUserId(user1));
//        System.out.println(topicService.selectTopicByTitle("test"));
        Timestamp t = new Timestamp(1);
        System.out.println(topicService.selectTopicByEditedTime(t));
        System.out.println(topicService.selectTopicByReplyedTime(t));


    }
}
