package com.example.finallaptrinhweb.controller.user_page.MailService;


import com.example.finallaptrinhweb.db.DbProperties;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    public EmailSender() {
    }

    public boolean sendEmail(String toEmail, String subject, String messageText) {
        boolean status = false;

        try {
            Properties prop = new Properties();
            prop.setProperty("mail.smtp.host", DbProperties.SMTP_HOST);
            prop.setProperty("mail.smtp.port", DbProperties.SMTP_PORT);
            prop.setProperty("mail.smtp.auth", DbProperties.AUTH);
            prop.setProperty("mail.smtp.starttls.enable", DbProperties.STARTTLS_ENABLE);
            prop.put("mail.smtp.ssl.protocols", DbProperties.SSL_PROTOCOLS);
            Session session = Session.getInstance(prop, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(DbProperties.FROM_EMAIL, DbProperties.PASSWORD);
                }
            });
            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(DbProperties.FROM_EMAIL));
            mess.addRecipient(RecipientType.TO, new InternetAddress(toEmail));
            mess.setSubject(subject);
            mess.setText(messageText);
            Transport.send(mess);
            status = true;
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return status;
    }
}
