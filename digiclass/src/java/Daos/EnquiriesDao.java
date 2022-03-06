/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.Enquiries;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public class EnquiriesDao extends Dao implements EnquiriesDaoInterface {

    public EnquiriesDao(String databaseName) {
        super(databaseName);
    }

    public EnquiriesDao(Connection conn) {
        super(conn);
    }

    /**
     * This method inserts the new Inquiry information into the enquiries table
     * in the database
     *
     * @param full_name The full name of the User
     * @param email The email of the User
     * @param message The message of the User
     *
     * @return boolean object, successful if true, else is false
     */
    @Override
    public boolean InsertInquiry(String full_name, String email, String message) {
        String name = full_name;
        String em = email;
        String mes = message;

        boolean updated = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String query = "INSERT INTO enquiries VALUES(NULL,?,?,?,CURRENT_DATE(), FALSE)";

            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, em);
            ps.setString(3, mes);

            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the InsertInquiry() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the InsertInquiry() method: " + e.getMessage());
            }
        }
        return updated;
    }

    /**
     * This method gets a list of All Unseen Enquiries from the enquiries table
     * in the database
     *
     *
     * @return ArrayList containing Enquiries objects, if no records are
     * returned, it returns a null.
     */
    @Override
    public ArrayList<Enquiries> getAllUnseenEnquiries() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //Declare Subject ArrayList object as this object will store the Subject obtained from the database
        ArrayList<Enquiries> enquiries = new ArrayList();

        try {
            con = getConnection();  //Get connection

            String query = "SELECT * FROM enquiries WHERE seen = FALSE";   //query
            ps = con.prepareStatement(query);   //Prepare Query
            rs = ps.executeQuery(); //Execute Query

            while (rs.next()) { //while loop to go round each record
                Enquiries enq = new Enquiries(rs.getInt("inquiry_id"), rs.getString("full_name"), rs.getString("email"), rs.getString("message"), rs.getString("date"), rs.getBoolean("seen"));
                enquiries.add(enq);  //adds each subject to the subjects List
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllUnseenEnquiries() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close(); //Close the ResultSet object
                }
                if (ps != null) {
                    ps.close(); //Close the PreparedStatement object
                }
                if (con != null) {
                    freeConnection(con);    //Frees connection
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getAllUnseenEnquiries() method: " + e.getMessage());  //If theres an Error
            }
        }

        return enquiries;   //Return the list of all books in the database
    }

    /**
     * This method gets a list of All Seen Enquiries from the enquiries table in
     * the database
     *
     *
     * @return ArrayList containing Enquiries objects, if no records are
     * returned, it returns a null.
     */
    @Override
    public ArrayList<Enquiries> getAllSeenEnquiries() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //Declare Subject ArrayList object as this object will store the Subject obtained from the database
        ArrayList<Enquiries> enquiries = new ArrayList();

        try {
            con = getConnection();  //Get connection

            String query = "SELECT * FROM enquiries WHERE seen = TRUE";   //query
            ps = con.prepareStatement(query);   //Prepare Query
            rs = ps.executeQuery(); //Execute Query

            while (rs.next()) { //while loop to go round each record
                Enquiries enq = new Enquiries(rs.getInt("inquiry_id"), rs.getString("full_name"), rs.getString("email"), rs.getString("message"), rs.getString("date"), rs.getBoolean("seen"));
                enquiries.add(enq);  //adds each subject to the subjects List
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllUnseenEnquiries() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close(); //Close the ResultSet object
                }
                if (ps != null) {
                    ps.close(); //Close the PreparedStatement object
                }
                if (con != null) {
                    freeConnection(con);    //Frees connection
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getAllUnseenEnquiries() method: " + e.getMessage());  //If theres an Error
            }
        }

        return enquiries;   //Return the list of all books in the database
    }

    /**
     * This method sets an inquiry to seen in the database
     *
     * @param inquiry_id The inquiry's ID
     *
     * @return boolean object, successful if true, else is false
     */
    @Override
    public boolean setInquiryToSeen(int inquiry_id) {
        int inqId = inquiry_id;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "UPDATE enquiries SET seen = TRUE WHERE inquiry_id = ?";
            ps = con.prepareStatement(query);

            ps.setInt(1, inqId);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the setInquiryToSeen() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the setInquiryToSeen() method: " + e.getMessage());
            }
        }
        return updated;

    }
}
