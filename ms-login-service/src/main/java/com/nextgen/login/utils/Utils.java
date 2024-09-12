package com.nextgen.login.utils;

import java.util.Random;

public class Utils {

    public static String generateClientCode(){
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000;
        return "NGR"+randomNumber;
    }
}
