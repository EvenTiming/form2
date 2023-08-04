package com.eventiming.form2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class topicinfo {
    private long topicid;
    private long likenum;
    private long storenum;

    @Override
    public String toString() {
        return "topicinfo{" +
                "topicid=" + topicid +
                ", likenum=" + likenum +
                ", storenum=" + storenum +
                ", forward=" + forward +
                '}';
    }

    private long forward;
}
