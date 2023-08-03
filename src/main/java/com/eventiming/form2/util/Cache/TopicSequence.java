package com.eventiming.form2.util.Cache;

import com.eventiming.form2.pojo.topic;
import com.eventiming.form2.pojo.topiccontext;
import org.springframework.stereotype.Component;
import java.math.BigInteger;
@Component
public class TopicSequence {

    //TODO LRU待检查
    private final int MAX_SPACE_NUM = 20;
    private int size;
    private TopicAndContext head ;
    private TopicAndContext tail;
    public TopicSequence(){
        size = 0;
        head = new TopicAndContext(null, null , null);
        tail =head;

    }

    public void addTopicAndContext(topic t, topiccontext tc,  PostHashMap postHashMap){
        TopicAndContext topicAndContext = new TopicAndContext(t, tc, postHashMap);
        if(size <= MAX_SPACE_NUM){
            tail.setNext(topicAndContext);
            topicAndContext.setPre(tail);
            tail = topicAndContext;
            size++;
        } else{
            tail = tail.getPre();
            tail.setNext(null);
        }
    }

    public TopicAndContext visitNode(BigInteger topicid){
        TopicAndContext topicAndContext = findNode(topicid);
        if(topicAndContext == null)
            return topicAndContext;
        else if(topicAndContext.getPre() == head)
            return topicAndContext;
        else{
            if(topicAndContext == tail){
                tail.setNext(head.getNext());
                head.getNext().setPre(tail);
                head.setNext(tail);
                head.getNext().setPre(head);
                tail = tail.getPre();
                tail.setNext(null);
                return topicAndContext;
            }else{
                topicAndContext.getPre().setNext(topicAndContext.getNext());
                topicAndContext.getNext().setPre(topicAndContext.getPre());
                head.getNext().setPre(topicAndContext);
                topicAndContext.setPre(head);
                topicAndContext.setNext(head.getNext());
                head.setNext(topicAndContext);
                return  topicAndContext;
            }
        }
    }

    public TopicAndContext findNode(BigInteger topicid){
            TopicAndContext point = head;
            if(point == tail){
                return null;
            }
            point = point.getNext();
            while(point != null){
                if(point.topicid.equals(topicid)){
                    return point;
                }
                point = point.getNext();
            }
            return null;
        }
}
