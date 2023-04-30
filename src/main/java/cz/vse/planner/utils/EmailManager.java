package cz.vse.planner.utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailManager {

    /* stored useremail as a text */
    private String useremail;

    public static void sendEmail(String to, String subject, String body) {
        String from = "myplannerappka@gmail.com"; // Your Gmail address
        String appPassword = "qevmmceddlmyupiy"; // Replace with your generated app password

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, appPassword);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    /**
     * Returns the useremail as a text
     * @return useremail
     */
    public String getLoginEmail() {
        return useremail;
    }
    /**
     * Setter for the useremail as a text
     * @param email
     * @return "new" useremail
     */
    public void setLoginEmail(String email) {
        return this.useremail = email;
    }



}