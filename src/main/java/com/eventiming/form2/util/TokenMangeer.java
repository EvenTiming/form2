package com.eventiming.form2.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.UUID;

@Component
public class TokenMangeer {

    private static HashMap<BigInteger, String> onlineToken;

    private static HashMap<BigInteger, Timestamp> TokenActiveTime;

    public TokenMangeer(){
        onlineToken =new HashMap<>();
        TokenActiveTime = new HashMap<>();
    }

    public String getToken(BigInteger userid){
        String token = tokenGenerate();
        onlineToken.put(userid, token);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        TokenActiveTime.put(userid, timestamp);
        return token;
    }

    public int confirmToken(BigInteger userid, String token){
        if(onlineToken.containsKey(userid)){
            if(onlineToken.get(userid).equals(token)){
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                TokenActiveTime.replace(userid,timestamp);
                return 1;
            } else {
                return 0;
            }
        }else{
            return 0;
        }
    }

    @Scheduled(fixedDelay = 5000)
    private void clearUnActiveToken(){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        System.out.println(11111);
        for (HashMap.Entry<BigInteger, Timestamp> entry : TokenActiveTime.entrySet()) {
            BigInteger key = entry.getKey();
            Timestamp value = entry.getValue();
            if((now.getTime() - value.getTime()) > 5000){
                removeToken(key);
            }
        }
    }
    private void removeToken(BigInteger userid){
        onlineToken.remove(userid);
        TokenActiveTime.remove(userid);
    }
    private String tokenGenerate(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

}
