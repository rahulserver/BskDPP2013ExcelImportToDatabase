package com.bsk.dpp13.utils;

import com.sms.sender.FullOnSMS;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: rahulserver
 * Date: 8/25/13
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String args[]){
        FullOnSMS fullOnSMS = new FullOnSMS("9586490842", "717160");
        try{
            if(fullOnSMS.isLoggedIn() && fullOnSMS.sendSMS("9586490842","Hi!! How are u?")){
                fullOnSMS.logoutSMS();
                System.out.println("Message was sent successfully " );
            }
        }catch(Exception e){
            System.out.println("Unable to send message, possible cause: " + e.getMessage());
        }
    }
}
