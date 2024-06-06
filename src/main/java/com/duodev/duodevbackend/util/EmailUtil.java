package com.duodev.duodevbackend.util;

import com.duodev.duodevbackend.model.Email;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class EmailUtil {

    public String sendEmail(Email email) {

        System.out.println("Sending email to " + email.getSendTo());
        System.out.println("From: " + email.getSendFrom());
        System.out.println("Subject: " + email.getSubject());
        System.out.println("Body: " + email.getBody());


        String username = "duodev7@gmail.com";
        String password = "qcrq xwad hwlt gthr";
        String host = "smtp.gmail.com";
        String port = "587";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email.getSendFrom()));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getSendTo()));
            message.setSubject(email.getSubject());
            message.setText(email.getBody());
            Transport.send(message);
            return "Email envadi para " + email.getSendTo();

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}