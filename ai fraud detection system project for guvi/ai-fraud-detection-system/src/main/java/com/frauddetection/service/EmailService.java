/*package com.frauddetection.service;

public class EmailService {

    public void sendAlert(String toEmail, String message) {
        // Replace this with SMTP or JavaMail later
        System.out.println("=== EMAIL ALERT ===");
        System.out.println("To: " + toEmail);
        System.out.println("Message: " + message);
        System.out.println("===================");
    }
}
*/



package com.frauddetection.service;

public class EmailService {
    public void sendAlert(String to, String subject, String body) {
        // placeholder: in production use JavaMail or SMTP
        System.out.println("Sending email to " + to + " subject=" + subject);
        System.out.println(body);
    }
}
