package com.eventiming.form2.util.Cache;

import com.eventiming.form2.pojo.topic;
import com.eventiming.form2.pojo.topiccontext;



public class TopicAndContext{
    private topic t;
    private String tc;
    private PostHashMap postHashMap;

    public long topicid;

    public TopicAndContext getNext() {
        return next;
    }

    public void setNext(TopicAndContext next) {
        this.next = next;
    }

    private TopicAndContext next;

    public TopicAndContext getPre() {
        return pre;
    }

    public void setPre(TopicAndContext pre) {
        this.pre = pre;
    }

    private TopicAndContext pre;
    public topic getT() {
        return t;
    }

    public void setT(topic t) {
        this.t = t;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public PostHashMap getPostHashMap() {
        return postHashMap;
    }

    public void setPostHashMap(PostHashMap postHashMap) {
        this.postHashMap = postHashMap;
    }

    public TopicAndContext(topic t, String tc,PostHashMap postHashMap){
        super();
        this.t = t;
        this.tc =tc;
        this.postHashMap = postHashMap;
        if(t == null ){
            this.topicid = 0;
        }else{
            this.topicid = t.getTopicid();
        }
    }

}