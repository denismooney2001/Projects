/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public class JoinDao extends Dao implements JoinDaoInterface {

    public JoinDao(String databaseName) {
        super(databaseName);
    }

    /**
     * This method gets a list of the ongoing teacher timetables by teacher from
     * multiple tables in the database
     *
     * @param teacher_id The teacher 's ID
     *
     * @return ArrayList containing Timetable objects, if no records are
     * returned, it returns a null.
     */
    @Override
    public ArrayList<Timetable> getOngoingTeacherTimeTables(int teacher_id) {
        int teacherId = teacher_id;
        Timetable t = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Timetable> userTimetable = new ArrayList<>();
        try {
            con = getConnection();
            String query = "SELECT t.* FROM timetable t, course c, teacher_details td, teacher_subjects ts WHERE c.course_id = t.course_id AND c.exist = TRUE AND c.end_date > CURRENT_DATE() AND c.teacher_subject_id = ts.teacher_subject_id AND ts.teacher_id = td.teacher_id AND td.teacher_id = ? ORDER BY t.time ASC";
            ps = con.prepareStatement(query);
            ps.setInt(1, teacherId);
            rs = ps.executeQuery();
            while (rs.next()) {
                t = new Timetable(rs.getInt("timetable_id"), rs.getInt("course_id"), rs.getString("day"), rs.getInt("time"));
                userTimetable.add(t);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllTimeTablesForOngoingCoursesByTeacherId() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllTimeTablesForOngoingCoursesByTeacherId() method: " + e.getMessage());
            }
        }
        return userTimetable;
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
            String query = "SELECT * FROM timetable WHERE course_id = ?";
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

    /**
     * This method gets a list of student timetables for ongoing courses by
     * user_id from multiple tables in the database.
     *
     * @param user_id The student's (User) ID
     *
     * @return ArrayList containing Timetable objects, if no records are
     * returned, it returns a null.
     */
    @Override
    public ArrayList<Timetable> getStudentTimeTablesForOngoingCourses(int user_id) {
        int studentId = user_id;
        Timetable t = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Timetable> userTimetable = new ArrayList<>();
        try {
            con = getConnection();
            String query = "SELECT t.* FROM timetable t, course c, course_students cs, users u WHERE cs.course_id = c.course_id AND c.exist = TRUE AND c.end_date > CURRENT_DATE() AND cs.student_id = u.user_id AND u.user_id = ? AND t.course_id = c.course_id ORDER BY t.time ASC";
            ps = con.prepareStatement(query);
            ps.setInt(1, studentId);
            rs = ps.executeQuery();
            while (rs.next()) {

                t = new Timetable(rs.getInt("timetable_id"), rs.getInt("course_id"), rs.getString("day"), rs.getInt("time"));
                userTimetable.add(t);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllTimeTablesForOngoingCoursesByTeacherId() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllTimeTablesForOngoingCoursesByTeacherId() method: " + e.getMessage());
            }
        }
        return userTimetable;
    }

    /**
     * This method gets a list of all ongoing courses by teacher_id from
     * multiple tables in the database
     *
     * @param teacher_id The teacher's ID
     *
     * @return ArrayList containing Course objects, if no records are returned,
     * it returns a null.
     */
    @Override
    public ArrayList<Course> getAllOngoingCoursesByTeacherId(int teacher_id) {
        int teacherId = teacher_id;
        Course c = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Course> courses = new ArrayList<>();
        try {
            con = getConnection();
            String query = "SELECT c.* FROM course c, teacher_details td, teacher_subjects ts WHERE c.end_date > CURRENT_DATE() AND c.exist = TRUE AND c.teacher_subject_id = ts.teacher_subject_id AND ts.teacher_id = td.teacher_id AND td.teacher_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, teacherId);
            rs = ps.executeQuery();
            while (rs.next()) {

                c = new Course(rs.getInt("course_id"), rs.getInt("teacher_subject_id"), rs.getInt("places"), rs.getString("course_description"), rs.getString("start_date"), rs.getString("end_date"), rs.getBoolean("exist"));
                courses.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllTimeTablesForOngoingCoursesByTeacherId() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllTimeTablesForOngoingCoursesByTeacherId() method: " + e.getMessage());
            }
        }
        return courses;
    }

    /**
     * This method returns the subject id used in the teacher_subject_id From
     * multiple tables in the database going by the course_id
     *
     * @param course_id The course 's ID
     *
     * @return Subject ID (Integer)
     */
    @Override
    public int getSubjectIdByCourseId(int course_id) {
        int courseId = course_id;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int subId = 0;
        try {
            con = getConnection();
            String query = "SELECT ts.subject_id FROM course c, teacher_subjects ts WHERE c.teacher_subject_id = ts.teacher_subject_id AND c.course_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();
            if (rs.next()) {
                subId = rs.getInt("subject_id");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getSubjectIdByCourseId() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getSubjectIdByCourseId() method: " + e.getMessage());
            }
        }
        return subId;
    }

    /**
     * This method gets a list of user ids of teachers by user_id from the join
     * table in the database
     *
     * @param user_id The user's ID
     *
     * @return ArrayList containing Integers, if no records are returned, it
     * returns a null.
     */
    @Override
    public ArrayList<Integer> getUserIdsOfTeacherByStudent(int user_id) {
        int userId = user_id;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //Declare Subject ArrayList object as this object will store the Subject obtained from the database
        ArrayList<Integer> teacherIds = new ArrayList();

        try {
            con = getConnection();  //Get connection

            String query = "SELECT u.user_id FROM users u, course c, course_students cs, teacher_subjects ts, teacher_details td WHERE cs.student_id = ? AND cs.course_id = c.course_id AND c.teacher_subject_id = ts.teacher_subject_id AND ts.teacher_id = td.teacher_id AND td.user_id = u.user_id";   //query
            ps = con.prepareStatement(query);   //Prepare Query
            ps.setInt(1, userId);
            rs = ps.executeQuery(); //Execute Query

            while (rs.next()) { //while loop to go round each record
                int tId = rs.getInt("user_id");
                teacherIds.add(tId);  //adds each subject to the subjects List
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getUserIdsOfTeacherByStudent() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getUserIdsOfTeacherByStudent() method: " + e.getMessage());  //If theres an Error
            }
        }

        return teacherIds;   //Return the list of all books in the database
    }

    /**
     * This method returns an ArrayList of the Non-existing courses by
     * teacher_id using multiple tables in the database
     *
     * @param teacher_id The teacher's ID
     *
     * @return ArrayList containing Course objects, if no records are returned,
     * it returns a null.
     *
     */
    @Override
    public ArrayList<Course> getNonExistingCourses(int teacher_id) {

        int teacherId = teacher_id;
        Course c = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Course> courseList = new ArrayList<>();
        try {
            con = getConnection();
            String query = "SELECT c.* FROM course c, teacher_subjects ts, teacher_details td WHERE c.exist = FALSE AND c.teacher_subject_id = ts.teacher_subject_id AND ts.teacher_id = td.teacher_id AND td.teacher_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, teacherId);
            rs = ps.executeQuery();
            while (rs.next()) {

                c = new Course(rs.getInt("course_id"), rs.getInt("teacher_subject_id"), rs.getInt("places"), rs.getString("course_description"), rs.getString("start_date"), rs.getString("end_date"), rs.getBoolean("exist"));
                courseList.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getNonExistingCourses() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getNonExistingCourses() method: " + e.getMessage());
            }
        }
        return courseList;
    }

    /**
     * This method returns an ArrayList of all courses by teacher_id from the
     * join table in the database
     *
     * @param teacher_id The teacher's ID
     *
     * @return ArrayList containing Course objects, if no records are returned,
     * it returns a null.
     */
    @Override
    public ArrayList<Course> getAllCoursesByTeacherId(int teacher_id) {

        int teacherId = teacher_id;
        Course c = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Course> courseList = new ArrayList<>();
        try {
            con = getConnection();
            String query = "SELECT c.* FROM course c, teacher_subjects ts, teacher_details td WHERE c.teacher_subject_id = ts.teacher_subject_id AND ts.teacher_id = td.teacher_id AND td.teacher_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, teacherId);
            rs = ps.executeQuery();
            while (rs.next()) {

                c = new Course(rs.getInt("course_id"), rs.getInt("teacher_subject_id"), rs.getInt("places"), rs.getString("course_description"), rs.getString("start_date"), rs.getString("end_date"), rs.getBoolean("exist"));
                courseList.add(c);
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
        return courseList;
    }

    /**
     * This method returns an ArrayList of students ongoing courses by user_id
     * from the join table in the database
     *
     * @param user_id The user's ID
     *
     * @return ArrayList containing Course objects, if no records are returned,
     * it returns a null.
     */
    @Override
    public ArrayList<Course> getStudentsOngoingCourses(int user_id) {
        int userId = user_id;
        Course c = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Course> courseList = new ArrayList<>();
        try {
            con = getConnection();
            String query = "SELECT c.* FROM course c, course_students cs WHERE c.exist = TRUE AND c.course_id = cs.course_id AND student_id = ? AND end_date > CURRENT_DATE()";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {

                c = new Course(rs.getInt("course_id"), rs.getInt("teacher_subject_id"), rs.getInt("places"), rs.getString("course_description"), rs.getString("start_date"), rs.getString("end_date"), rs.getBoolean("exist"));
                courseList.add(c);
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
        return courseList;
    }

    /**
     * This method returns an ArrayList of searches by teacher by teacher from
     * the join table in the database
     *
     * @param teacher_id The teacher's ID
     *
     * @return ArrayList containing String objects, if no records are returned,
     * it returns a null.
     */
    @Override
    public ArrayList<String> searchByTeacher(int teacher_id) {
        int teacherId = teacher_id;
        Course c = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<String> resultList = new ArrayList<>();
        String result = "";

        try {
            con = getConnection();
            String query = "SELECT ud.first_name, ud.last_name, s.subject_name, ts.subject_level, td.about_me FROM teacher_details td, subjects s, teacher_subjects ts, user_details ud WHERE td.user_id = ud.user_id AND ts.teacher_id = td.teacher_id AND ts.subject_id = s.subject_id AND ts.past = FALSE AND td.teacher_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, teacherId);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getString("first_name") + ", " + rs.getString("last_name") + ", " + rs.getString("subject_name") + ", " + rs.getString("subject_level") + ", " + rs.getString("about_me");
                resultList.add(result);
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
    //***************************************************

    /**
     * This method gets a list of course almost start by student_id from the
     * join table in the database
     *
     * @param student_id The student's ID
     *
     * @return object, successful if true, else is false
     */
    @Override
    public int getCourseAlmostStart(int student_id) {
        int studentId = student_id;
        int id = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String query = "SELECT course.course_id FROM `timetable`,course_students,course WHERE timetable.course_id = course_students.course_id AND course.course_id = course_students.course_students_id and timetable.time >= NOW() - INTERVAL 10 MINUTE AND course_students.course_students_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, studentId);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("course_id");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getCourseAlmostStart() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getCourseAlmostStart() method: " + e.getMessage());
            }
        }
        return id;
    }

    /**
     * This method returns an ArrayList of course that are not yet paid for by
     * student_id from multiple tables in the database
     *
     * @param student_id The student's ID
     *
     * @return object, successful if true, else is false
     */
    @Override
    public ArrayList<Course> getOverdueCourses(int student_id) {

        int studentId = student_id;
        Course c = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Course> courseList = new ArrayList<>();

        try {
            con = getConnection();

            String query = "SELECT c.course_id, c.teacher_subject_id, c.places, c.course_description, c.start_date, c.end_date, c.exist FROM course c, user_payment up WHERE up.user_id = ? AND c.course_id = up.course_id AND (up.end_date < CURRENT_DATE() OR up.end_date IS NULL) AND c.end_date > CURRENT_DATE()";
            ps = con.prepareStatement(query);
            ps.setInt(1, studentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                c = new Course(rs.getInt("course_id"), rs.getInt("teacher_subject_id"), rs.getInt("places"), rs.getString("course_description"), rs.getString("start_date"), rs.getString("end_date"), rs.getBoolean("exist"));
                courseList.add(c);
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

    @Override
    public ArrayList<TimetableCancellations> getAllUpcomingCancellationsByCourse(int course_id) {
        int courseId = course_id;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<TimetableCancellations> cancellationsList = new ArrayList<>();
        TimetableCancellations tc;

        try {
            con = getConnection();
            String query = "SELECT tc.timetable_cancellations_id, tc.timetable_id, tc.date FROM timetable_cancellations tc, timetable t, course c WHERE c.course_id = ? AND t.course_id = c.course_id AND t.timetable_id = tc.timetable_id AND tc.date > CURRENT_DATE()";
            ps = con.prepareStatement(query);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();
            while (rs.next()) {
                tc = new TimetableCancellations(rs.getInt("timetable_cancellations_id"), rs.getInt("timetable_id"), rs.getString("date"));
                cancellationsList.add(tc);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllUpcomingCancellationsByCourse() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllUpcomingCancellationsByCourse() method: " + e.getMessage());
            }
        }
        return cancellationsList;
    }

    @Override
    public boolean checkPresentCancellationsOnAllStudentsCourse(int student_id) {
        int studentId = student_id;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean alreadyExists = false;
        try {
            con = getConnection();

            String query = "SELECT tc.timetable_cancellations_id FROM timetable_cancellations tc, timetable t, course c, course_students cs WHERE cs.student_id = ? AND cs.course_id = c.course_id AND t.course_id = c.course_id AND t.timetable_id = tc.timetable_id AND tc.date > CURRENT_DATE()";
            ps = con.prepareStatement(query);
            ps.setInt(1, studentId);
            rs = ps.executeQuery();
            if (rs.next()) {
                alreadyExists = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the checkAnyCancellationsOnCourse() method: " + e.getMessage());
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

                System.out.println("Exception occured in the finally section of the checkAnyCancellationsOnCourse() method: " + e.getMessage());
            }
        }
        return alreadyExists;
    }

    @Override
    public ArrayList<Course> getAllCourses() {

        Course c = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Course> courseList = new ArrayList<>();
        try {
            con = getConnection();
            String query = "SELECT c.* FROM course c , teacher_subjects ts WHERE c.teacher_subject_id = ts.teacher_subject_id ORDER BY ts.teacher_id ASC";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {

                c = new Course(rs.getInt("course_id"), rs.getInt("teacher_subject_id"), rs.getInt("places"), rs.getString("course_description"), rs.getString("start_date"), rs.getString("end_date"), rs.getBoolean("exist"));
                courseList.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllCourses() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllCourses() method: " + e.getMessage());
            }
        }
        return courseList;
    }

    @Override
    public boolean checkIfTimetableCancellationExistsByCourse(int course_id) {
        int courseId = course_id;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean alreadyExists = false;
        try {
            con = getConnection();

            String query = "SELECT * FROM timetable_cancellations tc, timetable t WHERE tc.timetable_id = t.timetable_id AND t.course_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();

            if (rs.next()) {
                alreadyExists = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the userAlreadyExists() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the userAlreadyExists() method: " + e.getMessage());
            }
        }
        return alreadyExists;
    }
}
