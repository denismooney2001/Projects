/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.CourseMessage;
import Daos.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public class CourseMessageDao extends Dao implements CourseMessageDaoInterface {

    public CourseMessageDao(String databaseName) {
        super(databaseName);
    }

    /**
     * This method inserts the new message information into the message table in
     * the database
     *
     * @param course_id The Course ID
     * @param message The message's string
     *
     * @return boolean object, successful if true, else is false
     */
    @Override
    public boolean insertMessage(int course_id, String message) {
        int c_id = course_id;
        String mes = message;

        boolean updated = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String query = "INSERT INTO course_messages VALUES(NULL,?,?, CURRENT_DATE())";

            ps = con.prepareStatement(query);
            ps.setInt(1, c_id);
            ps.setString(2, mes);

            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the insertMessage() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the insertMessage() method: " + e.getMessage());
            }
        }
        return updated;
    }

    /**
     * This method updates message by course_id ,message from the message table
     * in the database
     *
     * @param course_id The Course ID (Integer)
     * @param message The Message (String)
     *
     * @return object, successful if true, else is false
     */
    @Override
    public boolean updateMessage(int course_id, String message) {
        int c_id = course_id;
        String mes = message;

        boolean updated = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String query = "UPDATE course_messages SET message = ? WHERE course_id = ?";

            ps = con.prepareStatement(query);
            ps.setString(1, mes);
            ps.setInt(2, c_id);

            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the updateMessage() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the updateMessage() method: " + e.getMessage());
            }
        }
        return updated;
    }

    /**
     * This method deletes a message by course_message_id from the message table
     * in the database
     *
     * @param course_message_id The course_message's ID
     *
     * @return object, successful if true, else is false
     */
    @Override
    public boolean deleteMessage(int course_message_id) {
        int c_m_id = course_message_id;

        boolean updated = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String query = "DELETE FROM course_messages WHERE course_message_id = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, c_m_id);

            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the deleteMessage() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the deleteMessage() method: " + e.getMessage());
            }
        }
        return updated;
    }

    /**
     * This method gets a list of all course messages by course_id from the
     * course_messages table in the database
     *
     * @param courseId The course ID
     *
     * @return ArrayList containing CourseMessage objects, if no records are
     * returned, it returns a null.
     */
    @Override
    public ArrayList<CourseMessage> getAllMessagesByPublishDateCourseId(int courseId) {
        int course_id = courseId;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CourseMessage courseMessage = null;
        ArrayList<CourseMessage> courseList = new ArrayList<>();

        try {
            con = getConnection();
            String query = "SELECT * FROM `course_messages` WHERE course_id = ? ORDER BY CONVERT(date, datetime ) DESC;";
            ps = con.prepareStatement(query);
            ps.setInt(1, course_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                courseMessage = new CourseMessage(rs.getInt("course_message_id"), rs.getInt("course_id"), rs.getString("message"), rs.getString("date"));
                courseList.add(courseMessage);
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
        return courseList;
    }
}
