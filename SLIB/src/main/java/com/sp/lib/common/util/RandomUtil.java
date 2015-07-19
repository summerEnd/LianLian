package com.sp.lib.common.util;


import java.util.Random;

public class RandomUtil {

    private Random random = new Random();

    public String nextString(int minLength, int maxLength) {

        if (maxLength <= 0) {
            return "";
        }
        if (minLength < 0) {
            minLength = 0;
        }
        int length;
        if (minLength == maxLength) {
            length = maxLength;
        } else {
            length  = minLength+random.nextInt(maxLength -minLength);
        }
        String str = "";
        for (int i = 0; i < length; i++) {
            str += (char) ('0' + random.nextInt(36));
        }
        return str;
    }
}
