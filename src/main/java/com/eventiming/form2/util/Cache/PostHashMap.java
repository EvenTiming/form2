package com.eventiming.form2.util.Cache;

import com.eventiming.form2.pojo.post;

import java.math.BigInteger;
import java.util.TreeMap;

public class PostHashMap {
    TreeMap<BigInteger, String> treeMap;
    public void addpostTree(post p){
        BigInteger postid = p.getPostid();
        String postContext = p.getPostcontext();
        treeMap.put(postid, postContext);
    }
}
