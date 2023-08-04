package com.eventiming.form2.util.Cache;

import com.eventiming.form2.pojo.post;


import java.util.TreeMap;

public class PostHashMap {
    TreeMap<Long, String> treeMap;
    public void addpostTree(post p){
        long postid = p.getPostid();
        String postContext = p.getPostcontext();
        treeMap.put(postid, postContext);
    }
}
