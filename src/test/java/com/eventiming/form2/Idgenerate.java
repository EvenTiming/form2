package com.eventiming.form2;

import com.eventiming.form2.util.UserIdGenerate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Idgenerate {
    @Autowired
    private UserIdGenerate userIdGenerate;

    @Test
    public void testid(){

        int i = 100;
        while(i>0){
            System.out.println(userIdGenerate.generateUniqueId());
            i--;
        }
    }
}
