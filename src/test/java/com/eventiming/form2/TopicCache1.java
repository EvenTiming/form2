package com.eventiming.form2;

import com.eventiming.form2.pojo.MemoryTopic;
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
        System.out.println(topicCache.visitTopic(3));
        topicCache.updatecontext(1,"lalalalalalaakakakaka");
    }
    @Test
    public void test2(){
        MemoryTopic memoryTopic = topicCache.createTopic("testest2", 1, "luoye111", "testdelete");
        long id =memoryTopic.getTopicid();
        topicCache.updatecontext(id, "mytestcontextahhahahaa");
        topicCache.updateforward(id);
        topicCache.updatelikenum(id);
        topicCache.updatestorenum(id);
        topicCache.updatetitle(id,"ksdlfakjsdfka");
    }
    @Test
    public void test3(){
        topicCache.createPost(28, 2, "hahahahah123");
        topicCache.updatePostLikeNum(28, 6);
    }
}
