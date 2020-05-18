/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.entities;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

//public class SmsSender {
//
//    // Find your Account Sid and Auth Token at twilio.com/console
//    public static final String ACCOUNT_SID = "ACb943d53045eaf680119725fdf1ed6ab9";
//    public static final String AUTH_TOKEN  = "2d3e5c96ed6e978630900f8e18e4c970";
//    public static final String API_PHONE   = "+12036939921";
//
//    public static void SendSMS(String to, String from, String body){
//        
//        Message message = Message
//                .creator(new PhoneNumber(to),  // to
//                         new PhoneNumber(from),  // from
//                         body)
//                .create();
//    }
//}
public class SmsSender {

    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID
            = "ACb943d53045eaf680119725fdf1ed6ab9";
    public static final String AUTH_TOKEN
            = "2d3e5c96ed6e978630900f8e18e4c970";
//
//    public static void main(String[] args) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        Message message = Message
//                .creator(new PhoneNumber("+21629025104"), // to
//                        new PhoneNumber("+12036939921"), // from
//                        "Where's Wallace?")
//                .create();
//
//        System.out.println(message.getSid());
//    }
}
