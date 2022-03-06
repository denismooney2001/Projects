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
public class UserDetails {

    private int user_details_id;
    private int user_id;
    private String first_name;
    private String last_name;
    private String profile_picture;
    private String contact_number;
    private String date_of_birth;
    private String addressLine1;
    private String addressLine2;

    public UserDetails() {
    }

    public UserDetails(int user_details_id, int user_id, String first_name, String last_name, String profile_picture, String contact_number, String date_of_birth, String addressLine1, String addressLine2) {
        this.user_details_id = user_details_id;
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.profile_picture = profile_picture;
        this.contact_number = contact_number;
        this.date_of_birth = date_of_birth;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
    }

    public int getUser_details_id() {
        return user_details_id;
    }

    public void setUser_details_id(int user_details_id) {
        this.user_details_id = user_details_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.user_id;

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
        final UserDetails other = (UserDetails) obj;
        if (this.user_id != other.user_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserDetails{" + "user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name + ", profile_picture=" + profile_picture + ", contact_number=" + contact_number + ", date_of_birth=" + date_of_birth + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + '}';
    }

}
