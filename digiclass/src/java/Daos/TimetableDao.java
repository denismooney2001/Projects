/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.Timetable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public class TimetableDao extends Dao implements TimetableDaoInterface {

    public TimetableDao(String databaseName) {
        super(databaseName);
    }

    @Override
    public boolean insertTimetable(int course_id, String day, int time) {

        int courseId = course_id;
        String dayOne = day;
        int timeOne = time;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "INSERT INTO timetable VALUES(NULL,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, courseId);
            ps.setString(2, dayOne);
            ps.setInt(3, timeOne);

            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the insertTimetable() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the insertTimetable() method: " + e.getMessage());
            }
        }
        return updated;
    }

    @Override
    public boolean updateTimetableByTimetableid(int timetable_id, String day, int time) {
        int timetableId = timetable_id;
        String timetable_day = day;
        int timetable_time = time;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "UPDATE timetable SET day = ?, time = ? WHERE timetable_id = ?";
            ps = con.prepareStatement(query);

            ps.setString(1, timetable_day);
            ps.setInt(2, timetable_time);
            ps.setInt(3, timetableId);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the updateTimetableByTimetableid() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the updateTimetableByTimetableid() method: " + e.getMessage());
            }
        }
        return updated;
    }

    @Override
    public boolean courseDayTimeExsits(int course_id, String day, int time) {
        int courseId = course_id;
        String d = day;
        int t = time;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "SELECT * FROM timetable WHERE course_id = ? AND day = ? AND time = ?";
            ps = con.prepareStatement(query);

            ps.setInt(1, courseId);
            ps.setString(2, d);
            ps.setInt(3, t);
            rs = ps.executeQuery();
            if (rs.next()) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the courseDayTimeExsits() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the courseDayTimeExsits() method: " + e.getMessage());
            }
        }
        return updated;
    }

    /**
     * This method gets a list of timetables by courseID from multiple tables in
     * the database
     *
     * @param courseID The course ID
     *
     * @return ArrayList containing Timetable objects, if no records are
     * returned, it returns a null.
     */
    @Override
    public ArrayList<Timetable> getTimeTablesbyCourse(int courseID) {
        int course_Id = courseID;
        Timetable t = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Timetable> userTimetable = new ArrayList<>();
        try {
            con = getConnection();
            String query = "SELECT timetable_id, course_id, day, time FROM timetable  WHERE course_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, course_Id);
            rs = ps.executeQuery();
            while (rs.next()) {
                t = new Timetable(rs.getInt("timetable_id"), rs.getInt("course_id"), rs.getString("day"), rs.getInt("time"));
                userTimetable.add(t);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getTimeTablesbyCourse() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getTimeTablesbyCourse() method: " + e.getMessage());
            }
        }
        return userTimetable;
    }

    @Override
    public int getCourseIdByTid(int timeId) {
        int time_ID = timeId;
        Timetable t = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cId = 0;

        ArrayList<Timetable> userTimetable = new ArrayList<>();
        try {
            con = getConnection();
            String query = "SELECT * FROM timetable WHERE timetable_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, time_ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                cId = rs.getInt("course_id");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getCourseIdByTid() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getCourseIdByTid() method: " + e.getMessage());
            }
        }
        return cId;
    }
}
