package com.nextgen.sms.utility;

import java.util.Random;

public class Utils {
    public static int generateOtp(){
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }
}
