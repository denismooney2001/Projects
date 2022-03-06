/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.Objects;

/**
 *
 * @author junta
 */
public class User {

    private int ID;
    private String user_type;
    private String username;
    private String password;
    private String email;
    private String user_status;
    private String secret_key;

    public User() {

    }

    public User(int ID, String user_type, String username, String password, String email, String user_status, String secret_key) {
        this.ID = ID;
        this.user_type = user_type;
        this.username = username;
        this.password = password;
        this.email = email;
        this.user_status = user_status;
        this.secret_key = secret_key;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.ID;
        hash = 37 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "ID=" + ID + ", user_type=" + user_type + ", username=" + username + ", password=" + password + ", email=" + email + ", user_status=" + user_status + '}';
    }
}
