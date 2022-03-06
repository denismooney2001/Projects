/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.UserDetails;

/**
 *
 * @author denis
 */
public interface UserDetailsDaoInterface {

    public UserDetails getUserDetailsByUserId(int userId);

    public boolean insertUserDetails(int user_id, String first_name, String last_name, String profile_picture, String contact_number, String date_of_birth, String addressLine1, String addressLine2);

    public boolean updateUserDetails(int user_id, String first_name, String last_name, String contact_number, String date_of_birth, String addressLine1, String addressLine2);

    public boolean updateImage(int user_id, String image);
}
