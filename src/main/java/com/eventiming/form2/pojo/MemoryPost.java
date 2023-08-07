package com.eventiming.form2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoryPost {
    long postid;
    long userid;
    long topicid;
    Timestamp posttimestamp;
    String postcontext;
    long likenum;
    boolean isNew;
    boolean isDeleted;
}
