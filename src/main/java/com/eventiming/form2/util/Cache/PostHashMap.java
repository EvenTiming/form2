package com.eventiming.form2.util.Cache;

import com.eventiming.form2.pojo.fastpost;
import com.eventiming.form2.pojo.post;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class PostHashMap {
    public TreeMap<Long, fastpost> getTreeMap() {
        return treeMap;
    }

    public void setTreeMap(TreeMap<Long, fastpost> treeMap) {
        this.treeMap = treeMap;
    }

    private TreeMap<Long, fastpost> treeMap;
    public PostHashMap(){
        treeMap = new TreeMap<>();
    }
    public void addpostTree(fastpost fp){
        // TODO 更改数据结构
        long postid = fp.getP().getPostid();
        treeMap.put(postid, fp);
    }
    public List<fastpost> getAll() {
        List<fastpost> allPosts = new ArrayList<>(treeMap.values());
        return allPosts;
    }
}
