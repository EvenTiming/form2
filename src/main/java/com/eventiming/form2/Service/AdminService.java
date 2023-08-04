package com.eventiming.form2.Service;

public interface AdminService {
    int blockUser(long userid);
    int deleteUser(long userid);
    int deleteTopic(long topicid);
    int deletePost(long postid);
}
