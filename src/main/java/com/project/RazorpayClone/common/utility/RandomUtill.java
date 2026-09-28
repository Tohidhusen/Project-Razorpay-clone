package com.project.RazorpayClone.common.utility;

import java.security.SecureRandom;
import java.util.Base64;

public class RandomUtill{

    private static SecureRandom SECURE_RANDOM =new SecureRandom();

    public static String randombase64(int length){
        byte[] buff=new byte[length/2];
        SECURE_RANDOM.nextBytes(buff);
       //generate [4,5,10,23] length of string
        //range from {-128,128}
        return Base64.getUrlEncoder().withoutPadding().encodeToString(buff);

    }


}
