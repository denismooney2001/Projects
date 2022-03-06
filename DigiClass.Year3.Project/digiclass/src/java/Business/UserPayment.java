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
public class UserPayment {

    private int user_payment_id;
    private int user_id;
    private int course_id;
    private String start_date;
    private String end_date;

    public UserPayment() {
    }

    public UserPayment(int user_payment_id, int user_id, int course_id, String start_date, String end_date) {
        this.user_payment_id = user_payment_id;
        this.user_id = user_id;
        this.course_id = course_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getUser_payment_id() {
        return user_payment_id;
    }

    public void setUser_payment_id(int user_payment_id) {
        this.user_payment_id = user_payment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.user_payment_id;
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
        final UserPayment other = (UserPayment) obj;
        if (this.user_payment_id != other.user_payment_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserPayment{" + "user_payment_id=" + user_payment_id + ", user_id=" + user_id + ", course_id=" + course_id + ", start_date=" + start_date + ", end_date=" + end_date + '}';
    }

}
