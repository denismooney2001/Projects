/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.CourseStudents;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public class CourseStudentsDao extends Dao implements CourseStudentsDaoInterface {

    public CourseStudentsDao(String databaseName) {
        super(databaseName);
    }

    /**
     * This method inserts the new Course student information into the course
     * student table in the database
     *
     * @param course_id The course ID
     * @param student_id The student ID
     * @param paid The paid boolean
     *
     * @return boolean object, successful if true, else is false
     */
    @Override
    public boolean insertCourseStudent(int course_id, int student_id) {

        int coId = course_id;
        int suId = student_id;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean updated = false;
        try {
            con = getConnection();
            String query = "INSERT INTO course_students VALUES(NULL,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, coId);
            ps.setInt(2, suId);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the insertCourseStudent() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the insertCourseStudent() method: " + e.getMessage());
            }
        }
        return updated;
    }

    /**
     * This method returns the amount of students in a course using the
     * course_id from the course student table in the database
     *
     * @param courseId The course ID
     *
     * @return Integer object, the number of places taken up by students
     */
    @Override
    public int getCourseCount(int courseId) {
        int co_id = courseId;
        int count = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String query = "SELECT COUNT(*) FROM course_students WHERE course_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, co_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("COUNT(*)");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getCourseCount() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getCourseCount() method: " + e.getMessage());
            }
        }
        return count;
    }

    /**
     * This method deletes a record of a student in a course by course_id,
     * user_id from the course_student table in the database
     *
     * @param course_id The course ID
     * @param user_id The student's ID
     *
     * @return object, successful if true, else is false
     */
    @Override
    public boolean deleteStudentCourse(int course_id, int user_id) {
        int courseId = course_id;
        int userId = user_id;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "DELETE FROM course_students WHERE student_id = ? AND course_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the deleteStudentCourse() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the deleteStudentCourse() method: " + e.getMessage());
            }
        }
        return updated;

    }

    /**
     * This method gets a list of students by course_id from the course student
     * table in the database
     *
     * @param courseID The course ID
     *
     * @return ArrayList containing CourseStudents objects, if no records are
     * returned, it returns a null.
     */
    @Override
    public ArrayList<CourseStudents> selectStudentsByCourseId(int courseID) {
        int course_id = courseID;
        CourseStudents cs = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<CourseStudents> courseStudentList = new ArrayList<>();
        try {
            con = getConnection();
            String query = "SELECT * FROM course_students WHERE course_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, course_id);
            rs = ps.executeQuery();
            while (rs.next()) {

                cs = new CourseStudents(rs.getInt("course_students_id"), rs.getInt("course_id"), rs.getInt("student_id"));
                courseStudentList.add(cs);
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
        return courseStudentList;
    }

    /**
     * This method checks if a student is enrolled in course by course_id and
     * student_id from the course_student table in the database
     *
     * @param course_id The course ID
     * @param student_id The student's ID
     * @return boolean object, successful if true, else is false
     */
    @Override
    public boolean studentEnrolledInCourse(int course_id, int student_id) {
        int c_id = course_id;
        int s_id = student_id;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean alreadyExists = false;
        try {
            con = getConnection();

            String query = "SELECT * FROM course_students WHERE course_id = ? AND student_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, c_id);
            ps.setInt(2, s_id);
            rs = ps.executeQuery();

            if (rs.next()) {
                alreadyExists = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the studentEnrolledInCourse() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the studentEnrolledInCourse() method: " + e.getMessage());
            }
        }
        return alreadyExists;
    }

    /**
     * This method gets a list of students Id's already places in a course using
     * the course_id from the course_student table in the database
     *
     * @param course_id The course ID
     *
     * @return ArrayList of type Integer, if no records are returned, it returns
     * a null.
     */
    @Override
    public ArrayList<Integer> getStudentIdsInCourse(int courseId) {
        int course_id = courseId;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Integer> studentIdList = new ArrayList<>();
        int u = 0;

        try {
            con = getConnection();
            String query = "SELECT student_id FROM course_students WHERE course_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, course_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                u = rs.getInt("student_id");
                studentIdList.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getStudentIdsInCourse() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getStudentIdsInCourse() method: " + e.getMessage());
            }
        }
        return studentIdList;
    }

}
