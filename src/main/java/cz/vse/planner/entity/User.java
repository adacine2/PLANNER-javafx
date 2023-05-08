package cz.vse.planner.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    public User() {
    }

    /**
     * Getters and setters for the User class.
     * @return The value of the specified variable.
     */
    public String getEmail() {
        return email;
    }
    public void setEmail(String username) {
        this.email = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}