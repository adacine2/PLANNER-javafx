package cz.vse.planner.utils;

import org.mindrot.jbcrypt.BCrypt;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordManager {

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
/*
    public static String getPasswordFromDB(String userEmail) {
        String query = "SELECT password FROM users WHERE email = 'adam.eger@seznam.cz';";
        System.out.println("SQL Query: " + query.replace("?", "'" + userEmail + "'"));
        String storedPassword = null;

        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userEmail);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                storedPassword = resultSet.getString("password");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Get the password from DB:" + storedPassword);
        System.out.println("User Email: " + userEmail);
        return storedPassword;
    }
*/
    public static String getPasswordFromDB(String email) {
        String password = null;
        try {
            Connection con = DBManager.getConnection();
            String query = "SELECT password FROM users WHERE email = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("email je: "+ email);
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
