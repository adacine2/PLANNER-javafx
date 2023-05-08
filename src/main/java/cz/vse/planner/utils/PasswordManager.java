package cz.vse.planner.utils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

@Component
public class PasswordManager {

    @Autowired
    private EmailManager emailManager;
    @Autowired
    private DataSource dataSource;

    /**
     * Generates a random password with the specified length.
     * @param length The length of the generated password.
     * @return The generated random password.
     */
    public static String generateRandomPassword(int length) {
        String characters = "a";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }

    public String getPasswordFromDB() {
        String email = emailManager.getLoginEmail();
        String password = null;
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT password FROM users WHERE email = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("email je: " + email);
            if (rs.next()) {
                password = rs.getString("password");
            }
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }

    public static boolean checkInsertedPassword(String userEnteredPassword, String storedHash) {
        if (storedHash == null) {
            return false;
        }
        return BCrypt.checkpw(userEnteredPassword, storedHash);
    }
}
