package com.eventiming.form2.util;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Math.abs;

@Component
public class UserIdGenerate {

        // 生成随机10位正数
        private static long generateRandomTenDigitNumber() {
            long min = (long) Math.pow(10, 4); // 10的9次方，即1,000,000,000
            long max = (long) Math.pow(10, 5) - 1; // 10的10次方减1，即9,999,999,999
            Random random = new Random();
            return abs(min + random.nextLong() % (max - min + 1));
        }

        // 生成唯一ID（10位正数 + 当前时间戳）
        public synchronized BigInteger generateUniqueId() {
            long randomTenDigitNumber = generateRandomTenDigitNumber();
            long currentTimeStamp = System.currentTimeMillis();
            BigInteger b =new BigInteger(randomTenDigitNumber + "" + currentTimeStamp);
            return b;
        }


}
