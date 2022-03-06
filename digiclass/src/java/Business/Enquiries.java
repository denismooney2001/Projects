/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author denis
 */
public class Enquiries {

    private int inquiry_id;
    private String full_name;
    private String email;
    private String message;
    private String date;
    private boolean seen;

    public Enquiries(int inquiry_id, String full_name, String email, String message, String date, boolean seen) {
        this.inquiry_id = inquiry_id;
        this.full_name = full_name;
        this.email = email;
        this.message = message;
        this.date = date;
        this.seen = seen;
    }

    public int getInquiry_id() {
        return inquiry_id;
    }

    public void setInquiry_id(int inquiry_id) {
        this.inquiry_id = inquiry_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.inquiry_id;
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
        final Enquiries other = (Enquiries) obj;
        if (this.inquiry_id != other.inquiry_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Enquiries{" + "inquiry_id=" + inquiry_id + ", full_name=" + full_name + ", email=" + email + ", message=" + message + ", date=" + date + ", seen=" + seen + '}';
    }

}
