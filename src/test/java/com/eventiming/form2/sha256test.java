package com.eventiming.form2;

import com.eventiming.form2.util.Password2SHA;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class sha256test {
    @Autowired
    private Password2SHA password2SHA;
    @Test
    public void test1(){
        String password = "12esodnfaiwen";
        String p = password2SHA.hashPassword(password);
        System.out.println(p);
    }
}
