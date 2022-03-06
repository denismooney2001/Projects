/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.UserDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author denis
 */
public class UserDetailsDao extends Dao implements UserDetailsDaoInterface {

    public UserDetailsDao(String databaseName) {
        super(databaseName);
    }

    @Override
    public UserDetails getUserDetailsByUserId(int userId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        UserDetails userDetails = null;

        try {
            con = getConnection(); //Get connection

            String query = "SELECT * FROM user_details WHERE user_id = ?"; //query
            ps = con.prepareStatement(query); //Prepare Query
            ps.setInt(1, userId);
            rs = ps.executeQuery(); //Execute Query

            if (rs.next()) { //while loop to go round each record

                userDetails = new UserDetails(rs.getInt("user_details_id"), rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("profile_picture"), rs.getString("contact_number"), rs.getString("date_of_birth"), rs.getString("addressLine1"), rs.getString("addressLine2"));

            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getUserDetailsByUserId() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close(); //Close the ResultSet object
                }
                if (ps != null) {
                    ps.close(); //Close the PreparedStatement object
                }
                if (con != null) {
                    freeConnection(con); //Frees connection
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getUserDetailsByUserId() method: " + e.getMessage()); //If theres an Error
            }
        }

        return userDetails; //Return the list of all books in the database
    }

    /**
     * This method inserts the new UserDetails information into the database
     *
     * @param user_id The User's ID
     * @param first_name The First Name of the User
     * @param last_name The Last Name of the User
     * @param profile_picture The name of the Users Profile picture (Used to
     * create a path)
     * @param contact_number The number of the User
     * @param date_of_birth The date of birth of the User
     * @param addressLine1 The first line of the User's address
     * @param addressLine2 The first line of the User's address
     *
     * @return boolean object, successful if true, else is false
     */
    @Override
    public boolean insertUserDetails(int user_id, String first_name, String last_name, String profile_picture, String contact_number, String date_of_birth, String addressLine1, String addressLine2) {

        int userId = user_id;
        String fName = first_name;
        String lName = last_name;
        String pPicture = profile_picture;
        String number = contact_number;
        String dOb = date_of_birth;
        String address1 = addressLine1;
        String address2 = addressLine2;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "INSERT INTO user_details VALUES(NULL,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setString(2, fName);
            ps.setString(3, lName);
            ps.setString(4, pPicture);
            ps.setString(5, number);
            ps.setString(6, dOb);
            ps.setString(7, address1);
            ps.setString(8, address2);

            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the insertUserDetails() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the insertUserDetails() method: " + e.getMessage());
            }
        }
        return updated;
    }

    /**
     * This method updates User's details in the UserDetails table in the
     * database
     *
     * @param user_id The User's ID
     * @param first_name The First Name of the User
     * @param last_name The Last Name of the User
     * @param contact_number The number of the User
     * @param date_of_birth The date of birth of the User
     * @param addressLine1 The first line of the User's address
     * @param addressLine2 The first line of the User's address
     *
     * @return boolean object, successful if true, else is false
     */
    @Override
    public boolean updateUserDetails(int user_id, String first_name, String last_name, String contact_number, String date_of_birth, String addressLine1, String addressLine2) {
        int userId = user_id;
        String fName = first_name;
        String lName = last_name;
        String number = contact_number;
        String dOb = date_of_birth;
        String address1 = addressLine1;
        String address2 = addressLine2;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "UPDATE user_details SET first_name = ?, last_name = ?, contact_number = ?, date_of_birth = ?, addressLine1 = ?, addressLine2 = ? WHERE user_id = ?";
            ps = con.prepareStatement(query);

            ps.setString(1, fName);
            ps.setString(2, lName);
            ps.setString(3, number);
            ps.setString(4, dOb);
            ps.setString(5, address1);
            ps.setString(6, address2);
            ps.setInt(7, userId);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the updateUserDetails() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the updateUserDetails() method: " + e.getMessage());
            }
        }
        return updated;
    }

    @Override
    public boolean updateImage(int user_id, String image) {
        int userId = user_id;
        String img = image;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "UPDATE user_details SET profile_picture = ? WHERE user_id = ?";
            ps = con.prepareStatement(query);

            ps.setString(1, img);
            ps.setInt(2, userId);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the updateUserDetails() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the updateUserDetails() method: " + e.getMessage());
            }
        }
        return updated;
    }

}
