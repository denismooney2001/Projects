/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public class CourseDao extends Dao implements CourseDaoInterface {

    public CourseDao(String databaseName) {
        super(databaseName);
    }

    public CourseDao(Connection conn) {
        super(conn);
    }

    /**
     * This method gets a list of all existing courses from the course table in
     * the database
     *
     *
     * @return ArrayList containing Course objects, if no records are returned,
     * it returns a null.
     */
    @Override
    public ArrayList<Course> getAllExistingCourses() {

        Course c = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Course> courseList = new ArrayList<>();
        try {
            con = getConnection();
            String query = "SELECT * FROM course WHERE exist = TRUE";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {

                c = new Course(rs.getInt("course_id"), rs.getInt("teacher_subject_id"), rs.getInt("places"), rs.getString("course_description"), rs.getString("start_date"), rs.getString("end_date"), rs.getBoolean("exist"));
                courseList.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllExistingCourses() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllExistingCourses() method: " + e.getMessage());
            }
        }
        return courseList;
    }

    /**
     * This method gets a list of all existing courses by teacher_id from the
     * course table in the database
     *
     * @param teacher_id The teacher's ID
     *
     * @return ArrayList containing Course objects, if no records are returned,
     * it returns a null.
     */
    @Override
    public ArrayList<Course> getAllExistingCoursesByTeacherId(int teacher_id) {

        int teacherId = teacher_id;
        Course c = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Course> courseList = new ArrayList<>();
        try {
            con = getConnection();
            String query = "SELECT c.* FROM course c, teacher_subjects ts, teacher_details td WHERE exist = TRUE AND c.teacher_subject_id = ts.teacher_subject_id AND ts.teacher_id = td.teacher_id AND td.teacher_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, teacherId);
            rs = ps.executeQuery();
            while (rs.next()) {
                c = new Course(rs.getInt("course_id"), rs.getInt("teacher_subject_id"), rs.getInt("places"), rs.getString("course_description"), rs.getString("start_date"), rs.getString("end_date"), rs.getBoolean("exist"));
                courseList.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllExistingCoursesByTeacherId() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllExistingCoursesByTeacherId() method: " + e.getMessage());
            }
        }
        return courseList;
    }

    /**
     * This method inserts the new course information into the course table in
     * the database
     *
     * @param teacher_subject_id The teacher_subject's id
     * @param places The course places (Integer)
     * @param description The description's String
     * @param start_date The start date string
     * @param end_date The end date String
     *
     * @return Course ID (Integer) that has been generated along with inserting
     * the data into the Course table in the database.
     */
    @Override
    public int insertCourse(int teacher_subject_id, int places, String description, String start_date, String end_date) {

        int teacherSubId = teacher_subject_id;
        int plces = places;
        String des = description;
        String startDate = start_date;
        String endDate = end_date;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int courseId = 0;
        try {
            con = getConnection();
            String query = "INSERT INTO course VALUES(NULL,?,?,?,?,?,TRUE)";
            ps = con.prepareStatement(query, ps.RETURN_GENERATED_KEYS);
            ps.setInt(1, teacherSubId);
            ps.setInt(2, plces);
            ps.setString(3, des);
            ps.setString(4, startDate);
            ps.setString(5, endDate);
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                courseId = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the insertCourse() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the insertCourse() method: " + e.getMessage());
            }
        }
        return courseId;
    }

    /**
     * This method checks a course has been disabled by course_id from the
     * course table in the database
     *
     * @param course_id The course ID
     *
     * @return boolean object, successful if true, else is false
     */
    @Override
    public boolean disableCourse(int course_id) {

        int courseId = course_id;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "UPDATE course SET exist = FALSE WHERE course_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, courseId);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the disableCourse() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the disableCourse() method: " + e.getMessage());
            }
        }
        return updated;
    }

    /**
     * This method checks if teacher subject id is ongoing by teacher_subject_id
     * from the course table in the database
     *
     * @param teacher_subject_id The teacher_subject's ID
     *
     * @return boolean object, successful if true, else is false
     */
    @Override
    public boolean checkIfTeacherSubjectIdIsOngoing(int teacher_subject_id) {
        int teacherSubId = teacher_subject_id;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "SELECT * FROM course WHERE teacher_subject_id = ? AND end_date > CURRENT_DATE()";
            ps = con.prepareStatement(query);

            ps.setInt(1, teacherSubId);
            rs = ps.executeQuery();
            if (rs.next()) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the checkIfTeacherSubjectIdIsOngoing() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the checkIfTeacherSubjectIdIsOngoing() method: " + e.getMessage());
            }
        }
        return updated;
    }

    /**
     * This method updates ongoing courses by teacher_subject_id, course_places,
     * description, start_date, end_date from the course table in the database
     *
     * @param teacher_subject_id The teacher_subject's id
     * @param course_places The number of course places
     * @param description The description's String
     * @param startDate The start date string
     * @param endDate The end date String
     *
     * @return boolean object, successful if true, else is false
     */
    @Override
    public boolean updateOngoingCourse(int teacher_subject_id, int course_places, String description, String startDate, String endDate) {
        int tsID = teacher_subject_id;
        int places = course_places;
        String des = description;
        String sDate = startDate;
        String eDate = endDate;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "UPDATE course SET places = ?, course_description = ?,start_date=?,end_date=? WHERE teacher_subject_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, places);
            ps.setString(2, des);
            ps.setString(3, sDate);
            ps.setString(4, eDate);
            ps.setInt(5, tsID);

            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the updateOngoingCourse() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the updateOngoingCourse() method: " + e.getMessage());
            }
        }
        return updated;
    }

    /**
     * This method gets a list of a courses that have not yet started by
     * teacher_id from the course table in the database
     *
     * @param teacher_id The teacher's id
     *
     * @return ArrayList containing Course objects, if no records are returned,
     * it returns a null.
     */
    @Override
    public ArrayList<Course> getCoursesNotYetStarted(int teacher_id) {
        int teacherId = teacher_id;
        Course c = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Course> resultList = new ArrayList<>();

        try {
            con = getConnection();
            String query = "SELECT c.* FROM course c, teacher_subjects ts, teacher_details td WHERE c.teacher_subject_id = ts.teacher_subject_id AND ts.teacher_id = td.teacher_id  AND c.start_date > CURRENT_DATE() AND exist = TRUE AND td.teacher_id = ? ORDER BY c.teacher_subject_id DESC";
            ps = con.prepareStatement(query);
            ps.setInt(1, teacherId);
            rs = ps.executeQuery();
            while (rs.next()) {
                c = new Course(rs.getInt("course_id"), rs.getInt("teacher_subject_id"), rs.getInt("places"), rs.getString("course_description"), rs.getString("start_date"), rs.getString("end_date"), rs.getBoolean("exist"));
                resultList.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getStudentsOngoingCourses() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getStudentsOngoingCourses() method: " + e.getMessage());
            }
        }
        return resultList;
    }

    /**
     * This method gets a list of a started ongoing courses by teacher_id from
     * the course table in the database
     *
     * @param teacher_id The teacher's id
     *
     * @return ArrayList containing Course objects, if no records are returned,
     * it returns a null.
     */
    @Override
    public ArrayList<Course> getStartedOngoingCourses(int teacher_id) {
        int teacherId = teacher_id;
        Course c = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Course> resultList = new ArrayList<>();

        try {
            con = getConnection();
            String query = "SELECT c.* FROM course c, teacher_subjects ts, teacher_details td WHERE c.teacher_subject_id = ts.teacher_subject_id AND ts.teacher_id = td.teacher_id  AND c.start_date < CURRENT_DATE() AND exist = TRUE AND td.teacher_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, teacherId);
            rs = ps.executeQuery();
            while (rs.next()) {
                c = new Course(rs.getInt("course_id"), rs.getInt("teacher_subject_id"), rs.getInt("places"), rs.getString("course_description"), rs.getString("start_date"), rs.getString("end_date"), rs.getBoolean("exist"));
                resultList.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getStudentsOngoingCourses() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getStudentsOngoingCourses() method: " + e.getMessage());
            }
        }
        return resultList;
    }

    /**
     * This method gets a course by course_id from the course table in the
     * database
     *
     * @param course_id The course id
     *
     * @return Course object, successful if true, else is false
     */
    @Override
    public Course getCourseByID(int course_id) {
        int courseID = course_id;
        Course c = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            String query = "SELECT c.* FROM course c WHERE course_id = ?";

            ps = con.prepareStatement(query);
            ps.setInt(1, courseID);
            rs = ps.executeQuery();
            if (rs.next()) {
                c = new Course(rs.getInt("course_id"), rs.getInt("teacher_subject_id"), rs.getInt("places"), rs.getString("course_description"), rs.getString("start_date"), rs.getString("end_date"), rs.getBoolean("exist"));
            }
        } catch (SQLException e) {

            System.out.println("Exception occured in the getAllCoursesByTeacherId() method: " + e.getMessage());

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

                System.out.println("Exception occured in the finally section of the getAllCoursesByTeacherId() method: " + e.getMessage());

            }
        }
        return c;
    }
    
    @Override 
    public int getNoOfPastAndPresentCourse(){
        int count = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String query = "SELECT COUNT(*) FROM course WHERE exist = TRUE";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("COUNT(*)");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getNoOfPastAndPresentCourse() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getNoOfPastAndPresentCourse() method: " + e.getMessage());
            }
        }
        return count;
    }

}
