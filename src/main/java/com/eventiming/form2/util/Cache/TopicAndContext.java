package com.eventiming.form2.util.Cache;

import com.eventiming.form2.pojo.topic;
import com.eventiming.form2.pojo.topiccontext;

import java.math.BigInteger;

public class TopicAndContext{
    private topic t;
    private topiccontext tc;
    private PostHashMap postHashMap;

    public BigInteger topicid;

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

    public topiccontext getTc() {
        return tc;
    }

    public void setTc(topiccontext tc) {
        this.tc = tc;
    }

    public PostHashMap getPostHashMap() {
        return postHashMap;
    }

    public void setPostHashMap(PostHashMap postHashMap) {
        this.postHashMap = postHashMap;
    }

    public TopicAndContext(topic t, topiccontext tc,PostHashMap postHashMap){
        super();
        this.t = t;
        this.tc =tc;
        this.postHashMap = postHashMap;
        if(t == null ){
            this.topicid = null;
        }else{
            this.topicid = t.getTopicid();
        }
    }

}