package cz.vse.planner.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailManager {
    private final JavaMailSender mailSender;
    public EmailManager(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    private static String useremail;

    public void sendMessage(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        System.out.println("Email sent successfully.");
    }
    public static boolean isEmailValid(String email) {
        String regex = "^(?:(?:[^<>\\[\\]()\\\\.,;:\\s@\"]+(\\.[^<>\\[\\]()\\\\.,;:\\s@\"]+)*)|(\\\".+\\\"))@((?:[a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}|(\\d{1,3}\\.){3}\\d{1,3}|\\[(\\d{1,3}\\.){3}\\d{1,3}])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static String getLoginEmail() {
        return useremail;
    }
    public void setLoginEmail(String email) {
        System.out.println("User insert email: " + email);
        this.useremail = email;
    }
}
