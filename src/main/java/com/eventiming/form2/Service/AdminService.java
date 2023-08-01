package com.eventiming.form2.Service;

import java.math.BigInteger;

public interface AdminService {
    int blockUser(BigInteger userid);
    int deleteUser(BigInteger userid);
    int deleteTopic(BigInteger topicid);
    int deletePost(BigInteger postid);
}
