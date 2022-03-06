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
public class CourseLinkDao extends Dao implements CourseLinkDaoInterface {

    public CourseLinkDao(String databaseName) {
        super(databaseName);
    }

    /**
     * This method inserts the new link information into the course link table
     * in the database
     *
     * @param course_id The Course ID (Integer)
     * @param course_link The course Link (String)
     *
     * @return boolean object, successful if true, else is false
     */
    @Override
    public boolean insertCourseLink(int course_id, String course_link) {

        int c_id = course_id;
        String c_link = course_link;

        boolean updated = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String query = "INSERT INTO course_links VALUES(NULL,?,?)";

            ps = con.prepareStatement(query);
            ps.setInt(1, c_id);
            ps.setString(2, c_link);

            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the insertCourseLink() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the insertCourseLink() method: " + e.getMessage());
            }
        }
        return updated;
    }

    /**
     * This method gets link of course by course_id from the course link table
     * in the database
     *
     * @param course_id The Course ID
     *
     * @return object, successful if true, else is false
     */
    @Override
    public String getLink(int course_id) {
        int c_id = course_id;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String link = "";
        try {
            con = getConnection();
            String query = "SELECT link FROM course_links WHERE course_id = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, c_id);
            rs = ps.executeQuery(); //Execute Query

            if (rs.next()) { //while loop to go round each record
                link = rs.getString("link");
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getLink() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getLink() method: " + e.getMessage());
            }
        }
        return link;
    }

    /**
     * This method updates link of course by course_id, course_link from the
     * course link table in the database
     *
     * @param course_id The Course ID (Integer)
     * @param course_link The course Link (String)
     *
     * @return object, successful if true, else is false
     */
    @Override
    public boolean updateLink(int course_id, String course_link) {
        int c_id = course_id;
        String link = course_link;

        boolean updated = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String query = "UPDATE course_links SET link = ? WHERE course_id = ?";

            ps = con.prepareStatement(query);
            ps.setString(1, link);
            ps.setInt(2, c_id);

            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the updateLink() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the updateLink() method: " + e.getMessage());
            }
        }
        return updated;
    }
}
