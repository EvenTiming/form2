package com.eventiming.form2.Cache;

import com.eventiming.form2.pojo.MemoryTopic;

public interface cache {
    // 写入内存
    int WriteToMemory(long topicid);
    // 写回数据库
    int WriteBackStorage(MemoryTopic memoryTopic);

}
