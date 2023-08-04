package com.eventiming.form2.util.Cache;

import org.springframework.stereotype.Component;


import java.util.Iterator;
import java.util.LinkedList;

@Component
public class TopicCache {
    private LinkedList<TopicAndContext> linkedList;
    private final int MAX_SIZE = 20;
    public void addTopicAndContext(TopicAndContext topicAndContext){
        if(linkedList.size() < MAX_SIZE){
            linkedList.addFirst(topicAndContext);
        }else{
            linkedList.removeLast();
            linkedList.addFirst(topicAndContext);
        }
    }
    public TopicAndContext visitNode(long topicid){
        Iterator<TopicAndContext> iterator = linkedList.iterator();
        while(iterator.hasNext()){
            TopicAndContext current = iterator.next();
            if(iterator.next().topicid ==topicid){
                linkedList.remove(current);
                linkedList.addFirst(current);
                return current;
            }
        }
        return null;
    }
}
