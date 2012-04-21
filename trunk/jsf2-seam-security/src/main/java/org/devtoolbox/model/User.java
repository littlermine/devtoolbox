package org.devtoolbox.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user")
public class User implements Serializable {
	
    private static final long serialVersionUID = -602733026033932730L;
    private String username;
    private String password;
    private String name;
    private String email;

    public User() {
    }

    public User(final String name, final String username, final String email) {
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public User(final String name, final String username, final String email, final String password) {
        this(name, username, email);
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Transient
    public String getEmailWithName() {
        return name + " <" + email + ">";
    }

    @Override
    public String toString() {
        return "User(" + username + ")";
    }
}