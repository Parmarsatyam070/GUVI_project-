package com.frauddetection.service;

public class EmailService {
    public void sendAlert(String to, String subject, String body) {
        // placeholder: in production use JavaMail or SMTP
        System.out.println("Sending email to " + to + " subject=" + subject);
        System.out.println(body);
    }
}
