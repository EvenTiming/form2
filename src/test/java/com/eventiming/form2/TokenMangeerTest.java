package com.eventiming.form2;


import com.eventiming.form2.util.TokenMangeer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.PushbackReader;



@SpringBootTest
public class TokenMangeerTest {
    @Autowired
    private TokenMangeer tokenMangeer;
    @Test
    public void test() throws InterruptedException {
        long userid = 123123132;
        String token = tokenMangeer.getToken(userid);
        System.out.println(token);
        System.out.println(tokenMangeer.confirmToken(userid, token));
        Thread.sleep(10000);
        System.out.println(tokenMangeer.confirmToken(userid, token));
    }
}
