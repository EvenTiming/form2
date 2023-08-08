//package com.eventiming.form2.util.Cache;
//
//import com.eventiming.form2.DAO.*;
//import com.eventiming.form2.pojo.*;
//import com.eventiming.form2.util.BeforeTimeStamp;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//
//import java.sql.Timestamp;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//
//@Component
//public class TopicCachetest {
//
//    // TODO 该层现在线程不安全，不支持多线程访问
//    @Autowired
//    private userdao userd;
//
//    @Autowired
//    private com.eventiming.form2.DAO.topicDao topicDao;
//
//    @Autowired
//    private topiccontextDao topiccontextdao;
//
//    @Autowired
//    private topicinfoDao topicinfodao;
//
//    @Autowired
//    private userstatusDao userstatusdao;
//
//    @Autowired
//    private postDao postdao;
//
//    @Autowired
//    private postinfoDao postinfodao;
//
//
//    @Autowired
//    private BeforeTimeStamp beforeTimeStamp;
//    private LinkedList<TopicAndContext> linkedList;
//    private final int MAX_SIZE = 20;
//
//    public TopicCachetest(){
//        // 构造函数，从数据库中读取数据
//        Timestamp timestamp = beforeTimeStamp.getTime(72);
//        Timestamp time = beforeTimeStamp.getTime(72);
//        List<topic> topics = topicDao.selectTopicByEditedTime(time);
//        Iterator<topic> iterator = topics.iterator();
//        while(iterator.hasNext()){
//            topic current = iterator.next();
//            long topicid = current.getTopicid();
//            String context = topiccontextdao.selectContext(topicid);
//            topicinfo ti = topicinfodao.selectTopicNumByID(topicid);
//            PostHashMap postHashMap = getPostsByTopicId(topicid);
//            TopicAndContext topicAndContext =new TopicAndContext(current, context, postHashMap,ti);
//            addTopicAndContext(topicAndContext);
//        }
//    }
//
//    public int createTopic(long userid, String title, String context){
//        // TODO 这里采用的自增id，必须先对数据库进行操作，后面还有改进的空间，比如唯一id生成算法
//        user u = userd.selectUserById(userid);
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        topic t =new topic();
//        t.setTitle(title);
//        t.setUsername(u.getUsername());
//        t.setUserid(userid);
//        t.setLastreplyedtime(timestamp);
//        t.setPosttime(timestamp);
//        t.setLasteditedtime(timestamp);
//        topicDao.insertTopicObject(t);
//        topiccontextdao.insertContext(t.getTopicid(),context);
//        topicinfodao.insertTopicInfo(t.getTopicid());
//        userstatusdao.updateUserTopicNum(userid, timestamp);
//        //发表后，自动将其移动到队列头部
//        getTopicByID(t.getTopicid());
//        return 1;
//    }
//
//    public int deleteTopic(long topicid){
//        TopicAndContext topicAndContext = visitNode(topicid);
//        if(topicAndContext != null){
//            linkedList.remove(topicAndContext);
//        }
//        // TODO 可能有问题，不存在topicid不合法
//        topicDao.deleteTopic(topicid);
//        topicinfodao.deleteTopicInfo(topicid);
//        topiccontextdao.deleteContext(topicid);
//        // TODO 数据库中post未删除
//        return 1;
//    }
//    public topic getTopicByID(long topicid){
//        TopicAndContext topicAndContext = visitNode(topicid);
//        if(topicAndContext != null){
//           return topicAndContext.getT();
//        }else{
//           if(visitMemory(topicid)){
//               return visitNode(topicid).getT();
//           }
//           return null;
//        }
//    }
//
//
//    public topiccontext getTopiccontextByID(long topicid){
//        TopicAndContext topicAndContext = visitNode(topicid);
//        if(topicAndContext != null){
//            topiccontext tcobject = new topiccontext(topicid, topicAndContext.getTc());
//            return tcobject;
//        }else{
//            if(visitMemory(topicid)){
//                topiccontext tcobject = new topiccontext(topicid, topicAndContext.getTc());
//                return tcobject;
//            } else{
//                return null;
//            }
//        }
//    }
//
//    public topicinfo getTopicinfoByID(long topicid){
//        TopicAndContext topicAndContext = visitNode(topicid);
//        if(topicAndContext != null){
//            return topicAndContext.getTopicinfo();
//        }else{
//            if(visitMemory(topicid)){
//                return visitNode(topicid).getTopicinfo();
//            }
//            return null;
//        }
//    }
//
//    public int updateTopicByID(long topicid, String newtitle){
//        TopicAndContext topicAndContext = visitNode(topicid);
//        if(topicAndContext != null){
//            Timestamp timestamp = beforeTimeStamp.getTime(0);
//            //这里相应的用户数据并没有改
//            topicAndContext.getT().setTitle(newtitle);
//            topicAndContext.getT().setLasteditedtime(timestamp);
//            return 1;
//        }else{
//            if(visitMemory(topicid)){
//                updateTopicByID(topicid, newtitle);
//                return 1;
//            }
//            return 0;
//        }
//    }
//
//    public int updateTopiccontextByID(long topicid, String newcontext){
//        TopicAndContext topicAndContext = visitNode(topicid);
//        if(topicAndContext != null){
//            Timestamp timestamp = beforeTimeStamp.getTime(0);
//            //这里相应的用户数据并没有改
//            topicAndContext.setTc(newcontext);
//            topicAndContext.getT().setLasteditedtime(timestamp);
//            return 1;
//        }else{
//            if(visitMemory(topicid)){
//                updateTopiccontextByID(topicid, newcontext);
//                return 1;
//            }
//            return 0;
//        }
//    }
//
//    public int updateTopicinfoByID(long topicid, long like,long store, long forward){
//        // TODO 帖子的点赞，收藏功能，有问题
////        TopicAndContext topicAndContext = visitNode(topicid);
////        if(topicAndContext != null){
////            topicAndContext.setTopicinfo();
////            return 1;
////        }else{
////            if(visitMemory(topicid)){
////                updateTopicByID(topicid, newtitle);
////                return 1;
////            }
//            return 0;
////        }
//    }
//    public int createPost(long userid, long topicid, String postcontext){
//        TopicAndContext topicAndContext = visitNode(topicid);
//        if(topicAndContext != null){
//            //如果内存中存在
//            Timestamp timestamp = beforeTimeStamp.getTime(0);
//            post p = new post();
//            p.setTimestamp(timestamp);
//            p.setUserid(userid);
//            p.setTopicid(topicid);
//            p.setPostcontext(postcontext);
//            postdao.insertPostObj(p);
//            postinfo pi = new postinfo(p.getPostid(),0);
//            fastpost fp =new fastpost(p, pi);
//            topicAndContext.getPostHashMap().addpostTree(fp);
//            return 1;
//        }else{
//            //如果不存在
//            Timestamp timestamp = beforeTimeStamp.getTime(0);
//            postdao.insertPost(topicid,userid,postcontext,timestamp);
//            visitMemory(topicid);
//                return 1;
//            }
//        }
//
//    public List<fastpost> getpostByID(long topicid){
//        TopicAndContext topicAndContext = visitNode(topicid);
//        if(topicAndContext != null){
//            List<fastpost> list = topicAndContext.getPostHashMap().getAll();
//            return list;
//        }else{
//            if(visitMemory(topicid)){
//                return getpostByID(topicid);
//            }
//            return null;
//        }
//    }
//
//    public int updatepostinfoByID(long topicid,long postid){
//        TopicAndContext topicAndContext = visitNode(topicid);
//        if(topicAndContext != null){
//
//           fastpost f = topicAndContext.getPostHashMap().getTreeMap().get(postid);
//           f.getPi().setPostlikenum(f.getPi().getPostlikenum() + 1);
//           return 1;
//
//        }else{
//            if(visitMemory(topicid)){
//                return updatepostinfoByID(topicid,postid);
//            }
//            return 0;
//        }
//    }
//
//    public int deletepost(){
//        // TODO 删除功能，准备写一个数据库层面的定时脚本，定时删除无主回复
//        return 1;
//    }
//
//
//    public int writeback(TopicAndContext topicAndContext){
//        //TODO 写回法有点问题,先采用定时写回的策略，后续可改进为批处理
//        long topicid = topicAndContext.topicid;
//        topicDao.updateTopicTitle(topicid, topicAndContext.getT().getTitle());
//        topiccontextdao.updateContext(topicid, topicAndContext.getTc());
//        topicinfodao.updateTopicNumByID(topicid,topicAndContext.getTopicinfo().getLikenum(),
//                topicAndContext.getTopicinfo().getStorenum(),
//                topicAndContext.getTopicinfo().getForward());
//        PostHashMap postHashMap = topicAndContext.getPostHashMap();
//        Iterator<fastpost> iterator = postHashMap.getAll().iterator();
//        while(iterator.hasNext()){
//            fastpost fp =iterator.next();
//            postinfodao.updatePostInfo(fp.getP().getPostid(), fp.getPi().getPostlikenum());
//        }
//        return 0;
//    }
//
//    public void addTopicAndContext(TopicAndContext topicAndContext){
//        if(linkedList.size() < MAX_SIZE){
//            linkedList.addFirst(topicAndContext);
//        }else{
//            linkedList.removeLast();
//            linkedList.addFirst(topicAndContext);
//        }
//    }
//    public TopicAndContext visitNode(long topicid){
//        Iterator<TopicAndContext> iterator = linkedList.iterator();
//        while(iterator.hasNext()){
//            TopicAndContext current = iterator.next();
//            if(iterator.next().topicid ==topicid){
//                linkedList.remove(current);
//                linkedList.addFirst(current);
//                return current;
//            }
//        }
//        return null;
//    }
//
//    private PostHashMap getPostsByTopicId(long topicid){
//        List<post> posts = postdao.selectPostsByTopicId(topicid);
//        Iterator<post> postIterator = posts.iterator();
//        // TODO 初始化缓冲层
//        PostHashMap postHashMap = new PostHashMap();
//        while(postIterator.hasNext()){
//            post postcurrent =postIterator.next();
//            fastpost fp =new fastpost(postcurrent,postinfodao.selectPostInfoByID(postcurrent.getPostid()));
//            postHashMap.addpostTree(fp);
//        }
//        return postHashMap;
//    }
//
//    private boolean visitMemory(long topicid){
//        // TODO 注意多线程
//        topic current = topicDao.selectTopicById(topicid);
//        if(current == null ){
//            return false;
//        }
//        String tcontext = topiccontextdao.selectContext(topicid);
//        PostHashMap postHashMap = getPostsByTopicId(topicid);
//        topicinfo ti = topicinfodao.selectTopicNumByID(topicid);
//        TopicAndContext topicAndContext = new TopicAndContext(current, tcontext, postHashMap,ti);
//        addTopicAndContext(topicAndContext);
//        return true;
//    }
//}
