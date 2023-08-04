package com.eventiming.form2.util.Cache;

import com.eventiming.form2.pojo.fastpost;
import com.eventiming.form2.pojo.post;


import java.util.TreeMap;

public class PostHashMap {
    TreeMap<Long, fastpost> treeMap;
    public PostHashMap(){
        treeMap = new TreeMap<>();
    }
    public void addpostTree(fastpost fp){
        // TODO 更改数据结构
        long postid = fp.getP().getPostid();
        treeMap.put(postid, fp);
    }
}
