package tn.esprit.restauMobile.services;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailService {

    private static final String EMAIL_ADDRESS = "yasminenasfi2001@gmail.com";  // Remplacez par votre email Gmail
    private static final String EMAIL_PASSWORD = "aplx wqvy ojng mfgv";    // Remplacez par votre mot de passe Gmail

    public static void sendEmail(String toEmail, String subject, String body) {
        // Configuration des propriétés pour l'authentification SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587"); // Port pour envoyer des mails
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Sécurisation de la connexion

        // Session avec authentification
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_ADDRESS, EMAIL_PASSWORD);
            }
        });

        try {
            // Création du message email
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_ADDRESS)); // Email de l'expéditeur
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail)); // Email du destinataire
            message.setSubject(subject);  // Sujet de l'email
            message.setText(body);  // Corps de l'email

            // Envoi de l'email
            Transport.send(message);
            System.out.println("Email envoyé avec succès.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'envoi de l'email.");
        }
    }
}
