package com.eventiming.form2.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.UUID;

@Component
public class TokenMangeer {
    private static HashMap<Long, String> onlineToken;
    private static HashMap<Long, Timestamp> TokenActiveTime;
    public TokenMangeer(){
        onlineToken =new HashMap<>();
        TokenActiveTime = new HashMap<>();
    }
    public String getToken(long userid){
        if(onlineToken.containsKey(userid)){
            return null;
        }
        String token = tokenGenerate();
        onlineToken.put(userid, token);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        TokenActiveTime.put(userid, timestamp);
        return token;
    }

    public boolean confirmToken(long userid, String token){
        if(onlineToken.containsKey(userid)){
            if(onlineToken.get(userid).equals(token)){
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                TokenActiveTime.replace(userid,timestamp);
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

    @Scheduled(fixedDelay = 60000)
    private void clearUnActiveToken(){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        for (HashMap.Entry<Long, Timestamp> entry : TokenActiveTime.entrySet()) {
            long key = entry.getKey();
            Timestamp value = entry.getValue();
            if((now.getTime() - value.getTime()) > 50000){
                removeToken(key);
            }
        }
    }
    public void removeToken(long userid){
        onlineToken.remove(userid);
        TokenActiveTime.remove(userid);
    }
    private String tokenGenerate(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

}
