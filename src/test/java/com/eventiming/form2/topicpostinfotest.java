package com.eventiming.form2;

import com.eventiming.form2.DAO.postinfoDao;
import com.eventiming.form2.DAO.topicinfoDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class topicpostinfotest {
    @Autowired
    private topicinfoDao topicinfodao;

    @Autowired
    private postinfoDao postinfodao;
    @Test
    public void test1(){
        topicinfodao.insertTopicInfo(1);
        postinfodao.insertPostInfo(1);
        topicinfodao.insertTopicInfo(2);
        postinfodao.insertPostInfo(2);
        topicinfodao.deleteTopicInfo(2);
        postinfodao.deletePostInfo(2);
    }
    @Test
    public void test2(){
        topicinfodao.updateTopicNumByID(1, 2,3,4);
        postinfodao.updatePostInfo(1, 10);
    }
}
