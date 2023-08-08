package com.eventiming.form2.Cache;

import com.eventiming.form2.DAO.*;
import com.eventiming.form2.pojo.*;
import com.eventiming.form2.util.BeforeTimeStamp;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class TopicCache implements cache{

    @Autowired
    private userdao userd;
    @Autowired
    private topicDao topicDao;
    @Autowired
    private topiccontextDao topiccontextdao;
    @Autowired
    private topicinfoDao topicinfodao;
    @Autowired
    private userstatusDao userstatusdao;
    @Autowired
    private postDao postdao;
    @Autowired
    private postinfoDao postinfodao;
    @Autowired
    private BeforeTimeStamp beforeTimeStamp;

    private LinkedList<MemoryTopic> linkedList;
    private final int MAX_SIZE = 20;

    @PostConstruct
    public void afterInjection() {
        // 在这里可以进行依赖的操作，例如调用 topicDao 的方法等
        // 这里的方法会在依赖注入完成后自动被调用
        linkedList = new LinkedList<>();
        List<topic> t = topicDao.selectIndexTopic(20,0);
        Iterator<topic> it = t.listIterator();
        while(it.hasNext()){
            topic currenttopic = it.next();
            MemoryTopic mt = getMemoryTopic(currenttopic);
            linkedList.addLast(mt);
        }
    }

    private MemoryTopic getMemoryTopic(topic it){
        long topicid = it.getTopicid();
        String title = it.getTitle();
        long userid= it.getUserid();
        String username = it.getUsername();
        Timestamp posttime = it.getPosttime();
        Timestamp lastreplyedtime = it.getLastreplyedtime();
        Timestamp lasteditedtime = it.getLasteditedtime();
        String context = topiccontextdao.selectContext(topicid);

        topicinfo ti = topicinfodao.selectTopicNumByID(topicid);
        long likenum = ti.getLikenum();
        long storenum = ti.getStorenum();
        long forward = ti.getForward();
        HashMap<Long, MemoryPost> memoryPost = getMemoryPostHashMap(topicid);
        return new MemoryTopic( topicid, title, userid,
         username, posttime, lastreplyedtime, lasteditedtime,
         context, likenum, storenum, forward, memoryPost, false ,false, false,false);
    }
    private HashMap<Long, MemoryPost> getMemoryPostHashMap(long topicid){
        HashMap<Long, MemoryPost> map = new HashMap<>();
        List<post> posts = postdao.selectPostsByTopicId(topicid);
        Iterator<post> pit = posts.iterator();
        while(pit.hasNext()){
            post currentpost = pit.next();
            long postid = currentpost.getPostid();
            long userid = currentpost.getUserid();
            postinfo currentpostinfo = postinfodao.selectPostInfoByID(postid);
            String postcontext = currentpost.getPostcontext();
            long likenum =currentpostinfo.getPostlikenum();
            Timestamp timestamp = currentpost.getTimestamp();
            MemoryPost m = new MemoryPost(postid, userid, topicid,timestamp, postcontext,likenum,false,false);
            map.put(postid, m);
        }
        return map;
    }
    @Override
    public int WriteToMemory(long topicid) {
        topic t = topicDao.selectTopicById(topicid);
        if(t == null)
            return 0;
        MemoryTopic mt = getMemoryTopic(t);
        if(linkedList.size() < MAX_SIZE){
            linkedList.addFirst(mt);
        } else{
            MemoryTopic removedTopic = linkedList.removeLast();
            WriteBackStorage(removedTopic);
            linkedList.addFirst(mt);
        }
        return 1;
    }

    public MemoryTopic visitTopic(long topicid){
        Iterator<MemoryTopic> it = linkedList.listIterator();
        while(it.hasNext()){
            MemoryTopic currentmt = it.next();
            if(currentmt.getTopicid() == topicid){
                return currentmt;
            }
        }
        if(WriteToMemory(topicid) == 1){
            return linkedList.getFirst();
        }
        return null;
    }

    public int updatetitle(long topicid, String newtitle){
        //TODO 线程不安全
        if(visitTopic(topicid) != null){
            linkedList.getFirst().setTitle(newtitle);
            linkedList.getFirst().setLasteditedtime(beforeTimeStamp.getTime(0));
            linkedList.getFirst().setTitleIsEdited(true);
            return 1;
        }
        return 0;
    }

    public int updatecontext(long topicid, String newcontext){
        //TODO 线程不安全
        if(visitTopic(topicid) != null){
            linkedList.getFirst().setContext(newcontext);
            linkedList.getFirst().setLasteditedtime(beforeTimeStamp.getTime(0));
            linkedList.getFirst().setContextIsEdited(true);
            return 1;
        }
        return 0;
    }

    public int updatelikenum(long topicid){
        //TODO 线程不安全
        if(visitTopic(topicid) != null){
            linkedList.getFirst()
                    .setLikenum(linkedList.getFirst().getLikenum() + 1);
            return 1;
        }
        return 0;
    }

    public int updatestorenum(long topicid){
        //TODO 线程不安全
        if(visitTopic(topicid) != null){
            linkedList.getFirst()
                    .setStorenum(linkedList.getFirst().getStorenum() + 1);
            return 1;
        }
        return 0;
    }

    public int updateforward(long topicid){
        //TODO 线程不安全
        if(visitTopic(topicid) != null){
            linkedList.getFirst()
                    .setForward(linkedList.getFirst().getForward() + 1);
            return 1;
        }
        return 0;
    }

    static long minvalue = Long.MAX_VALUE - 10000;
    public long getOnlyMemoryID(){
        //TODO  唯一ID生成算法！,这个地方存在很大问题
        if(minvalue < Long.MAX_VALUE)
            return minvalue++;
        else
            return 0;

    }
    public int createTopic(String title, long userid, String username,
                           String context){
        // TODO 涉及到唯一id的问题，计划采用唯一id生成方法，在内存中采用临时id
        long topicid = getOnlyMemoryID();
        Timestamp posttime = beforeTimeStamp.getTime(0);
        Timestamp lastreplyedtime = posttime;
        Timestamp lasteditedtime = posttime;
        long likenum = 0;
        long storenum = 0;
        long forward = 0;
        HashMap<Long, MemoryPost> memoryPost = new HashMap<>();
        MemoryTopic mt = new MemoryTopic(topicid, title, userid,
                username, posttime, lastreplyedtime, lasteditedtime,
                context, likenum, storenum, forward, memoryPost,
                false ,false, false,false);
        linkedList.addFirst(mt);
        return 1;
    }
    public int createPost(long topicid, long userid, String postcontext ){
        // TODO 涉及到唯一id的问题，计划采用唯一id生成方法，在内存中采用临时id
        HashMap<Long, MemoryPost> map = visitTopic(topicid).getPostmap();
        long postid = getOnlyMemoryID();
        Timestamp posttimestamp = beforeTimeStamp.getTime(0);
        long likenum = 0;
        boolean isNew = false;
        boolean isDeleted = false;
            MemoryPost m = new MemoryPost(postid, userid, topicid,posttimestamp, postcontext,likenum,isNew,isDeleted);
            map.put(postid, m);

        return 1;
    }

    public int deleteTopic(long topicid){
        //TODO 删除内存评论，如何与数据库进行同步
        if(visitTopic(topicid) != null){
            linkedList.getFirst().setDeleted(true);
            WriteBackStorage(linkedList.getFirst());
            return 1;
        }
        return 0;
    }

    public int deletePost(long topicid, long postid){
        //TODO 删除内存中的评论
        if(visitTopic(topicid) != null){
            if( linkedList.getFirst().getPostmap().containsKey(postid)){
                linkedList.getFirst().getPostmap().get(postid).setDeleted(true);
                return 1;
            }
            return 0;
        }
        return 0;
    }

    @Override
    public int WriteBackStorage(MemoryTopic memoryTopic) {
        boolean isDeleted = memoryTopic.isDeleted();
        boolean isNew = memoryTopic.isNew();
        long topicid = memoryTopic.getTopicid();
        //这里逻辑可能有问题
        if(isDeleted){
            if( !isNew ){
                // TODO 缺少一键删除对应topic下所有评论的dao
                topicDao.deleteTopic(topicid);
                topicinfodao.deleteTopicInfo(topicid);
                topiccontextdao.deleteContext(topicid);
            }
            return 1;
        }
        String title  = memoryTopic.getTitle();
        Timestamp posttime = memoryTopic.getPosttime();
        Timestamp lastreplyedtime = memoryTopic.getLastreplyedtime();
        Timestamp lasteditedtime = memoryTopic.getLasteditedtime();
        String context = memoryTopic.getContext();
        long likenum = memoryTopic.getLikenum();
        long storenum = memoryTopic.getStorenum();
        long forward = memoryTopic.getForward();
        boolean titleIsEdited  = memoryTopic.isTitleIsEdited();
        boolean contextIsEdited = memoryTopic.isContextIsEdited();
        long userid = memoryTopic.getUserid();
        WriteTopicBack(userid, topicid, title, posttime, lastreplyedtime, lasteditedtime,
                context, likenum, storenum, forward, titleIsEdited ,contextIsEdited ,isNew);
        writePostsBack(memoryTopic.getPostmap());
        return 0;
    }

    private int WriteTopicBack(long userid, long topicid,String title,Timestamp posttime,
                               Timestamp lastreplyedtime, Timestamp lasteditedtime,
                               String context,long likenum,long storenum,long forward,
                               boolean titleedited, boolean contextedited, boolean isNew){
        if(isNew){
            user u =userd.selectUserById(userid);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            topic t =new topic();
            t.setTitle(title);
            t.setUsername(u.getUsername());
            t.setUserid(userid);
            t.setLastreplyedtime(lastreplyedtime);
            t.setPosttime(posttime);
            t.setLasteditedtime(timestamp);
            topicDao.insertTopicObject(t);
            topiccontextdao.insertContext(t.getTopicid(),context);
            topicinfodao.insertTopicInfo(topicid);
            topicinfodao.updateTopicNumByID(topicid,likenum,
                    storenum,forward);
            userstatusdao.updateUserTopicNum(userid, timestamp);
            return 1;
        }
        else{
            if(titleedited){
                topicDao.updateTopicLastEditedTime(topicid,lasteditedtime);
                topicDao.updateTopicTitle(topicid,title);
            }
            if(contextedited) {
                topicDao.updateTopicLastEditedTime(topicid,lasteditedtime);
                topicDao.updateTopicTitle(topicid,title);
            }

            topicDao.updateTopicLastReplyedTime(topicid, lastreplyedtime);
            topicinfodao.updateTopicNumByID(topicid,likenum,
                    storenum,forward);

            return 1;}

    }
    private int writePostsBack(HashMap<Long, MemoryPost> map){
        for (MemoryPost p : map.values()) {
            if(p.isDeleted() && p.isNew()){
                // 如果被删除了，还是新的，什么都不做
            }else if(p.isDeleted() && !p.isNew()){
                postdao.deletePostById(p.getPostid());
                postinfodao.deletePostInfo(p.getPostid());
            }else if(!p.isDeleted() && p.isNew()){
                post newpost = new post();
                newpost.setTopicid(p.getTopicid());
                newpost.setTimestamp(p.getPosttimestamp());
                newpost.setUserid(p.getUserid());
                newpost.setPostcontext(p.getPostcontext());
                postdao.insertPostObj(newpost);
                postinfodao.insertPostInfo(newpost.getPostid());
                postinfodao.updatePostInfo(newpost.getPostid(), p.getLikenum());
            }else if(!p.isDeleted() && !p.isNew()){
                postinfodao.updatePostInfo(p.getPostid(), p.getLikenum());
            }
        }
        return 1;
    }
}
