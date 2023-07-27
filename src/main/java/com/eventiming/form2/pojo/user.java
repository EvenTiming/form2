package com.eventiming.form2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class user {
    BigInteger userid;
    String username;
    String password;
    String email;
    Timestamp registertime;

    @Override
    public String toString() {
        return "user{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", registertime='" + registertime + '\'' +
                '}';
    }
}
