package com.duodev.duodevbackend.service;

import com.duodev.duodevbackend.model.Email;
import com.duodev.duodevbackend.model.Sessao;
import com.duodev.duodevbackend.repository.EmailRepository;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService  {

    @Autowired
    private EmailRepository emailRepository;

    public String sendEmailInvite(Email email, Sessao sessao) {
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

            email.setSessao(sessao);
            email.setInviteId(email.getInviteId());
            emailRepository.save(email);
            return "Email enviado para " + email.getSendTo();


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }


    public String sendEmail(Email email) {
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

            email.setSessao(null);
            email.setInviteId(null);
            emailRepository.save(email);
            return "Email enviado para " + email.getSendTo();


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }


    public Email getEmailByInviteId(String inviteId) {
        return emailRepository.findByInviteId(inviteId);
    }
}
