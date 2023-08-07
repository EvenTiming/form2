package com.eventiming.form2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoryTopic {
    long topicid;
    String title;
    long userid;
    String username;
    Timestamp posttime;
    Timestamp lastreplyedtime;
    Timestamp lasteditedtime;
    String context;
    long likenum;
    long storenum;
    long forward;
    HashMap<Long, MemoryPost> postmap;
    //两个修改标志位
    boolean titleIsEdited ;
    boolean contextIsEdited ;
    boolean isNew;
    boolean isDeleted;
}
