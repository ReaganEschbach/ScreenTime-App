package com.tryingagain;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class EmailSender {

    public static void sendEmail(final String toEmail, final String subject, final String body){
        final String fromEmail = "screentimehelper@gmail.com";
        final String smtpUsername = "AKIA4IXXPJDSQKOW6IHH";
        final String smtpPassword = "BK7MU7vju+86D/j00Zf4hT356IYjpD08bSZvE9Wa07Xf";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "email-smtp.us-east-2.amazonaws.com");
        props.put("mail.smtp.port", "587");


        Session session = Session.getInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(smtpUsername, smtpPassword);
                    }
                }
        );

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);


        } catch (MessagingException e){
            e.printStackTrace();
        }
    }
}
