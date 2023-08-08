package com.eventiming.form2.Controller;

import com.eventiming.form2.Service.TopicServiceImpl;
import com.eventiming.form2.pojo.MemoryTopic;
import com.eventiming.form2.pojo.ResponseData;
import com.eventiming.form2.pojo.post;
import com.eventiming.form2.pojo.topic;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TopicController {
    @Autowired
    private TopicServiceImpl topicService;

    @GetMapping("/index")
    public ResponseData<List<topic>> getIndexTopic(@Param("num") int num, @Param("offset") int offset){
        return topicService.selectIndexTopics(num, offset);
    }
    @GetMapping("/topic-{i}")
    public ResponseData<List<post>> getTopic(@PathVariable("i") long i){
        //数据库访问呢
        return topicService.selectTopicContextById(i);
    }

    @GetMapping("mtopic-{i}")
    public ResponseData<MemoryTopic> getMemoryTopic(@PathVariable("i") long i){
        //快速访问
       return topicService.selectMemoryTopicById(i);
    }

    //TODO 点赞帖子
    //TODO 收藏帖子
    //TODO 转发帖子
    // TODO 筛选帖子

}
