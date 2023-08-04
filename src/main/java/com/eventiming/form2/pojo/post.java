package com.eventiming.form2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class post {
    long postid;
    long topicid;
    long userid;
    String postcontext;
    Timestamp timestamp;

    @Override
    public String toString() {
        return "post{" +
                "postid=" + postid +
                ", topicid=" + topicid +
                ", userid=" + userid +
                ", postcontext='" + postcontext + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
