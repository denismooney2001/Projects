/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author denis
 */
public class CourseWaitingListDao extends Dao implements CourseWaitingListDaoInterface {

    public CourseWaitingListDao(String databaseName) {
        super(databaseName);
    }

    /**
     * This method inserts the a Student Id into the course_waiting_list table
     * in the database
     *
     * @param course_id The Course ID
     * @param student_id The student's ID
     *
     * @return boolean object, successful if true, else is false
     */
    @Override
    public boolean insertStudentInWaitingList(int course_id, int student_id) {
        int co_id = course_id;
        int su_id = student_id;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean updated = false;
        try {
            con = getConnection();
            String query = "INSERT INTO course_waiting_list VALUES(NULL,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, co_id);
            ps.setInt(2, su_id);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the insertStudentInWaitingList() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the insertStudentInWaitingList() method: " + e.getMessage());
            }
        }
        return updated;
    }

    /**
     * This method enrolls a student into a course by inserting the course_id
     * and student_id into the course_waiting_list table in the database
     *
     * @param course_id The course ID
     * @param student_id The student's ID
     *
     * @return boolean object, successful if true, else is false
     */
    @Override
    public boolean enrollStudentInCourse(int course_id, int student_id) {

        int co_id = course_id;
        int su_id = student_id;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "DELETE FROM course_waiting_list WHERE course_id AND ? student_id = ?";
            ps = con.prepareStatement(query);

            ps.setInt(1, co_id);
            ps.setInt(2, su_id);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the enrollStudentInCourse() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the enrollStudentInCourse() method: " + e.getMessage());
            }
        }
        return updated;
    }

    /**
     * This method checks if a student is present in the course_waiting_list
     * table using the course_id and student_id
     *
     * @param course_id The course ID
     * @param student_id The student's ID
     *
     * @return boolean object, successful if true, else is false
     */
    @Override
    public boolean studentInCourseWaitingList(int course_id, int student_id) {
        int c_id = course_id;
        int s_id = student_id;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean alreadyExists = false;
        try {
            con = getConnection();

            String query = "SELECT * FROM course_waiting_list WHERE course_id = ? AND student_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, c_id);
            ps.setInt(2, s_id);
            rs = ps.executeQuery();

            if (rs.next()) {
                alreadyExists = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the studentInCourseWaitingList() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the studentInCourseWaitingList() method: " + e.getMessage());
            }
        }
        return alreadyExists;
    }
}
