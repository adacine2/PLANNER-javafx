package cz.vse.planner.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailManager {

    /* stored useremail as a text */
    private static String useremail;

    public static void sendEmail(String to, String subject, String body) {
        String from = "myplannerappka@gmail.com"; // Gmail address - sender
        String appPassword = "qevmmceddlmyupiy"; // WEB APP PASSWORD for Google

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(from);
        mailSender.setPassword(appPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        //mailSender.send(message);
        System.out.println("Email sent successfully.");
    }


    /**
     * Checks if the useremail is valid
     * @param email
     * @return
     */
    public static boolean isEmailValid(String email) {
        String regex = "^(?:(?:[^<>\\[\\]()\\\\.,;:\\s@\"]+(\\.[^<>\\[\\]()\\\\.,;:\\s@\"]+)*)|(\\\".+\\\"))@((?:[a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}|(\\d{1,3}\\.){3}\\d{1,3}|\\[(\\d{1,3}\\.){3}\\d{1,3}])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }



    /**
     * Returns the useremail as a text
     * @return useremail
     */
    public static String getLoginEmail() {
        return useremail;
    }
    /**
     * Setter for the useremail as a text
     * @param email
     * @return "new" useremail
     */
    public void setLoginEmail(String email) {
        System.out.println("User insert email: "+email);
        this.useremail = email;
    }



}