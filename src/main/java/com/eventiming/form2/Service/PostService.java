package com.eventiming.form2.Service;

import com.eventiming.form2.pojo.post;
import java.math.BigInteger;
import java.util.List;

public interface PostService {
    int addpost(BigInteger userid, BigInteger topicid, String postcontext);

    int deletepost(BigInteger postid);

    // TODO 支持更改
    List<post> selectPostByTopic(BigInteger topicid);

    List<post> selectPostByUser(BigInteger userid);

}
