package Controller;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class Mailer {
    public static void send()
    {
        // Set up the SMTP server.
        Properties props = new Properties();
        //props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", "true");
        props.put("mail.smtp.socketFactory.class", "java.net.ssl.sslSocketFactory");
        Session session = Session.getDefaultInstance(props, null);

        // Construct the message
        String to = "";
        String from = "";
        String subject = "Hello";
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText("Hi,\n\nHow are you?");

            // Send the message.
            Transport.send(msg);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
