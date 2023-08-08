package com.eventiming.form2.Service;

import com.eventiming.form2.pojo.post;

import java.util.List;

public interface PostService {
    int addpost(long userid, long topicid, String postcontext);

    int deletepost(long topicid, long postid);

    // TODO 支持更改
    List<post> selectPostByTopic(long topicid);

    List<post> selectPostByUser(long userid);

}
