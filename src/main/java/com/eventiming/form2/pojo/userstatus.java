package com.eventiming.form2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class userstatus {
    long userid;
    int usergroup;
    Timestamp lastactivetime;
    int topicnum;
    int threadnum;

    @Override
    public String toString() {
        return "userstatus{" +
                "userid=" + userid +
                ", usergroup=" + usergroup +
                ", lastactivetime=" + lastactivetime +
                ", topicnum=" + topicnum +
                ", threadnum=" + threadnum +
                '}';
    }
}
