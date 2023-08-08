package com.eventiming.form2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.eventiming.form2.Cache.TopicCache;
@SpringBootTest
public class TopicCache1 {
    @Autowired
    private TopicCache topicCache;
    @Test
    public void test1(){
        System.out.println(topicCache.visitTopic(1));
    }
}
