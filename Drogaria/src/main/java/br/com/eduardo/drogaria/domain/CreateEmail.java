package br.com.eduardo.drogaria.domain;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class CreateEmail {
	
    public void sendEmail(String to, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Configuração das propriedades (semelhante ao exemplo anterior)

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("eduardoteixeira.dev@gmail.com", "AIzaSyBh9zl6K0py0yPyz9k7S6lRLUMOJoEDP9g");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("eduardoteixeira.dev@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
