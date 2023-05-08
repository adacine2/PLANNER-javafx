package cz.vse.planner.utils;

import cz.vse.planner.entity.*;
import cz.vse.planner.repo.*;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserManager {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private EmailManager emailManager;
    private boolean userLogged;
    /**
     * Checks if the user with the given email exists in the database
     *
     * @param email the email to check
     * @return true if the user exists, false otherwise
     */
    public boolean userExists(String email) {
        return userRepo.findByEmail(email) != null;
    }
    /**
     * Adds a new user with the given email and generates a random password
     * that is sent to the user's email address.
     * @param email the email to add
     */
    public void addUser(String email) {
        // Generate a random password
        String plainTextPassword = PasswordManager.generateRandomPassword(4);
        // Hash the password using bcrypt
        String hashedPassword = BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
        // Create a new user and save it to the database
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword);
        userRepo.save(newUser);
        // Send the plain text password to the user's email
        emailManager.sendMessage(email, "Your password is:", plainTextPassword);
        // Save the email to the EmailManager
        emailManager.setLoginEmail(email);
    }
    public boolean isUserLogged() {
        return userLogged;
    }

    public void setLoggedIn(boolean loggedIn) {
        userLogged = loggedIn;
    }
}
