/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.Reviews;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public class ReviewsDao extends Dao implements ReviewsDaoInterface {

    public ReviewsDao(String databaseName) {
        super(databaseName);
    }

    @Override
    public boolean insertReview(int teacher_id, int student_id, int rating, String description) {
        int teacherId = teacher_id;
        int studentId = student_id;
        int rate = rating;
        String des = description;

        boolean updated = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String query = "INSERT INTO reviews VALUES(NULL,?,?,?,CURRENT_DATE(), ?)";

            ps = con.prepareStatement(query);
            ps.setInt(1, teacherId);
            ps.setInt(2, studentId);
            ps.setInt(3, rate);
            ps.setString(4, des);

            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the InsertReview() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the InsertReview() method: " + e.getMessage());
            }
        }
        return updated;
    }

    @Override
    public ArrayList<Reviews> getAllReviewsTeacherId(int teacher_id) {
        int teacherId = teacher_id;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Reviews review = null;
        ArrayList<Reviews> reviewList = new ArrayList<>();

        try {
            con = getConnection();
            String query = "SELECT * FROM `reviews` WHERE teacher_id = ? ORDER BY CONVERT(date, datetime ) DESC;";
            ps = con.prepareStatement(query);
            ps.setInt(1, teacherId);
            rs = ps.executeQuery();
            while (rs.next()) {
                review = new Reviews(rs.getInt("review_id"), rs.getInt("teacher_id"), rs.getInt("student_id"), rs.getInt("ratingOutOfTen"), rs.getString("date"), rs.getString("description"));
                reviewList.add(review);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getCoursesNotYetPaid() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getCoursesNotYetPaid() method: " + e.getMessage());
            }
        }
        return reviewList;
    }

    @Override
    public ArrayList<Reviews> getAllTeacherReviews() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Reviews review = null;
        //Declare Subject ArrayList object as this object will store the Subject obtained from the database
        ArrayList<Reviews> reviewList = new ArrayList();

        try {
            con = getConnection();  //Get connection

            String query = "SELECT * FROM `reviews` ORDER BY CONVERT(date, datetime) DESC;";   //query
            ps = con.prepareStatement(query);   //Prepare Query
            rs = ps.executeQuery(); //Execute Query

            while (rs.next()) { //while loop to go round each record
                review = new Reviews(rs.getInt("review_id"), rs.getInt("teacher_id"), rs.getInt("student_id"), rs.getInt("ratingOutOfTen"), rs.getString("date"), rs.getString("description"));
                reviewList.add(review);  //adds each subject to the subjects List
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllTeacherReviews() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllTeacherReviews() method: " + e.getMessage());  //If theres an Error
            }
        }

        return reviewList;   //Return the list of all books in the database
    }

}
