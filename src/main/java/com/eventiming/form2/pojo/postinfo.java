package com.eventiming.form2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class postinfo {
    private long postid;
    private long postlikenum;
    @Override
    public String toString() {
        return "postinfo{" +
                "postid=" + postid +
                ", postlikenum=" + postlikenum +
                '}';
    }
}
