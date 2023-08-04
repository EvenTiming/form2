package com.eventiming.form2.util;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

@Component
public class BeforeTimeStamp {
    public Timestamp getTime(int hour) {
        long currentTimeMillis = System.currentTimeMillis();
        long seventyTwoHoursInMillis = TimeUnit.HOURS.toMillis(hour);
        long timestampInMillis = currentTimeMillis - seventyTwoHoursInMillis;
        Timestamp time = new Timestamp(timestampInMillis);
        return time;
    }
}
