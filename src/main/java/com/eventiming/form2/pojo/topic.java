package com.eventiming.form2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class topic {
    BigInteger topicid;
    String title;
    BigInteger userid;
    String username;
    Timestamp posttime;
    Timestamp lastreplyedtime;
    Timestamp lasteditedtime;

    @Override
    public String toString() {
        return "topic{" +
                "topicid=" + topicid +
                ", title='" + title + '\'' +
                ", userid=" + userid +
                ", username='" + username + '\'' +
                ", posttime=" + posttime +
                ", lastreplyedtime=" + lastreplyedtime +
                ", lasteditedtime=" + lasteditedtime +
                '}';
    }
}
