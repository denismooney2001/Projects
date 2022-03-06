/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.*;
import Daos.JoinDao;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author D00223984
 */
public class JoinDaoTest {

    public static JoinDao joinDao = new JoinDao("digiclass_test");

    public JoinDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTimetableforTeacherByTeacherId method, of class JoinDao.
     */
    @Test
    public void testGetTimetableforTeacherByTeacherId() {
        System.out.println("Test #1 - Get Ongoing Teacher TimeTables - Using a Valid Teacher ID");
        int teacher_id = 32;
        int expResult = 2;
        int result = joinDao.getOngoingTeacherTimeTables(teacher_id).size();
        assertEquals(expResult, result);
    }

    @Test
    public void test2GetTimetableforTeacherByTeacherId() {
        System.out.println("Test #2 - Get Ongoing Teacher TimeTables - Using an Invalid Teacher ID");
        int teacher_id = -1;
        int expResult = 0;
        int result = joinDao.getOngoingTeacherTimeTables(teacher_id).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStudentTimeTablesForOngoingCourses method, of class JoinDao.
     */
    @Test
    public void testGetStudentTimeTablesForOngoingCourses() {
        System.out.println("Test #3 - Get Student TimeTables For Ongoing Courses - Using a Valid User (Student) ID");
        int user_id = 26;
        int expResult = 2;
        int result = joinDao.getStudentTimeTablesForOngoingCourses(user_id).size();
        assertEquals(expResult, result);
    }

    @Test
    public void test2GetStudentTimeTablesForOngoingCourses() {
        System.out.println("Test #4 - Get Student TimeTables For Ongoing Courses - Using an Invalid User (Student) ID");
        int user_id = -1;
        int expResult = 0;
        int result = joinDao.getStudentTimeTablesForOngoingCourses(user_id).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllOngoingCoursesByTeacherId method, of class JoinDao.
     */
    @Test
    public void testGetAllOngoingCoursesByTeacherId() {
        System.out.println("Test #5 - Get All Ongoing Courses By TeacherId - Using a Valid Teacher ID");
        int teacher_id = 32;
        int expResult = 3;
        int result = joinDao.getAllOngoingCoursesByTeacherId(teacher_id).size();
        assertEquals(expResult, result);
    }

    @Test
    public void test2GetAllOngoingCoursesByTeacherId() {
        System.out.println("Test #6 - Get All Ongoing Courses By TeacherId - Using an Invalid Teacher ID");
        int teacher_id = -1;
        int expResult = 0;
        int result = joinDao.getAllOngoingCoursesByTeacherId(teacher_id).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSubjectIdByCourseId method, of class JoinDao.
     */
    @Test
    public void testGetSubjectIdByCourseId() {
        System.out.println("Test #7 - Get Subject Id By Course Id - Using a Valid Course ID");
        int course_id = 44;
        int expResult = 4;
        int result = joinDao.getSubjectIdByCourseId(course_id);
        assertEquals(expResult, result);
    }

    @Test
    public void test2GetSubjectIdByCourseId() {
        System.out.println("Test #8 - Get Subject Id By Course Id - Using an Invalid Course ID");
        int course_id = -1;
        int expResult = 0;
        int result = joinDao.getSubjectIdByCourseId(course_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserIdsOfTeacherByStudent method, of class JoinDao.
     */
    @Test
    public void testGetUserIdsOfTeacherByStudent() {
        System.out.println("Test #9 - Get User Ids Of Teacher By Student - Using a Valid User(Student) ID");
        int user_id = 26;
        int expResult = 1;
        int result = joinDao.getUserIdsOfTeacherByStudent(user_id).size();
        assertEquals(expResult, result);
    }

    @Test
    public void test2GetUserIdsOfTeacherByStudent() {
        System.out.println("Test #10 - Get User Ids Of Teacher By Student - Using an Invalid User(Student) ID");
        int user_id = -1;
        int expResult = 0;
        int result = joinDao.getUserIdsOfTeacherByStudent(user_id).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNonExistingCourses method, of class JoinDao.
     */
    @Test
    public void testGetNonExistingCourses() {
        System.out.println("Test #10 - Get Non-Existing Courses - Using a Valid Teacher ID");
        int teacher_id = 35;
        int expResult = 2;
        int result = joinDao.getNonExistingCourses(teacher_id).size();
        assertEquals(expResult, result);
    }

    @Test
    public void test2GetNonExistingCourses() {
        System.out.println("Test #11 - Get Non-Existing Courses - Using an Invalid Teacher ID");
        int teacher_id = -1;
        int expResult = 0;
        int result = joinDao.getNonExistingCourses(teacher_id).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllCoursesByTeacherId method, of class JoinDao.
     */
    @Test
    public void testGetAllCoursesByTeacherId() {
        System.out.println("Test #12 - Get All Courses By Teacher Id - Using a Valid Teacher ID");
        int teacher_id = 32;
        int expResult = 4;
        int result = joinDao.getAllCoursesByTeacherId(teacher_id).size();
        assertEquals(expResult, result);
    }

    @Test
    public void test2GetAllCoursesByTeacherId() {
        System.out.println("Test #13 - Get All Courses By Teacher Id - Using an Invalid Teacher ID");
        int teacher_id = -1;
        int expResult = 0;
        int result = joinDao.getAllCoursesByTeacherId(teacher_id).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOngoingTeacherTimeTables method, of class JoinDao.
     */
    @Test
    public void testGetOngoingTeacherTimeTables() {
        System.out.println("getOngoingTeacherTimeTables");
        int teacher_id = 32;
        int expResult = 2;
        ArrayList<Timetable> result = joinDao.getOngoingTeacherTimeTables(teacher_id);
        assertEquals(expResult, result.size());
    }

    @Test
    public void test2GetOngoingTeacherTimeTables() {
        System.out.println("getOngoingTeacherTimeTables");
        int teacher_id = 2;
        int expResult = 0;
        ArrayList<Timetable> result = joinDao.getOngoingTeacherTimeTables(teacher_id);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getTimeTablesbyCourse method, of class JoinDao.
     */
    @Test
    public void testGetTimeTablesbyCourse() {
        System.out.println("getTimeTablesbyCourse");
        int courseID = 44;
        int expResult = 2;
        ArrayList<Timetable> result = joinDao.getTimeTablesbyCourse(courseID);
        assertEquals(expResult, result.size());
    }

    @Test
    public void test2GetTimeTablesbyCourse() {
        System.out.println("getTimeTablesbyCourse");
        int courseID = 4;
        ArrayList<Timetable> expResult = new ArrayList();
        ArrayList<Timetable> result = joinDao.getTimeTablesbyCourse(courseID);
        assertEquals(expResult, result);
    }

    /**
     * Test of getStudentsOngoingCourses method, of class JoinDao.
     */
    @Test
    public void testGetStudentsOngoingCourses() {
        System.out.println("getStudentsOngoingCourses");
        int user_id = 26;
       int expResult = 1;
        ArrayList<Course> result = joinDao.getStudentsOngoingCourses(user_id);
        assertEquals(expResult, result.size());
    }

    @Test
    public void test2GetStudentsOngoingCourses() {
        System.out.println("getStudentsOngoingCourses");
        int user_id = 6;
        int expResult = 0;
        ArrayList<Course> result = joinDao.getStudentsOngoingCourses(user_id);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of searchByTeacher method, of class JoinDao.
     */
    @Test
    public void testSearchByTeacher() {
        System.out.println("searchByTeacher");
        int teacher_id = 32;
        int expResult = 3;
        ArrayList<String> result = joinDao.searchByTeacher(teacher_id);
        assertEquals(expResult, result.size());
    }

    @Test
    public void test2SearchByTeacher() {
        System.out.println("searchByTeacher");
        int teacher_id = 2;
        int expResult = 0;
        ArrayList<String> result = joinDao.searchByTeacher(teacher_id);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getCourseAlmostStart method, of class JoinDao.
     */
    @Test
    public void testGetCourseAlmostStart() {
        System.out.println("getCourseAlmostStart");
        int student_id = 29;
        int expResult = 0;
        int result = joinDao.getCourseAlmostStart(student_id);
        assertEquals(expResult, result);
    }

    @Test
    public void test2GetCourseAlmostStart() {
        System.out.println("getCourseAlmostStart");
        int student_id = 9;
        int expResult = 0;
        int result = joinDao.getCourseAlmostStart(student_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getOverdueCourses method, of class JoinDao.
     */
    @Test
    public void testGetOverdueCourses() {
        System.out.println("getOverdueCourses");
        int student_id = 26;
        ArrayList<Course> expResult = new ArrayList();
        ArrayList<Course> result = joinDao.getOverdueCourses(student_id);
        assertEquals(expResult, result);
    }

    @Test
    public void test2GetOverdueCourses() {
        System.out.println("getOverdueCourses");
        int student_id = 2;
        ArrayList<Course> expResult = new ArrayList();
        ArrayList<Course> result = joinDao.getOverdueCourses(student_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllUpcomingCancellationsByCourse method, of class JoinDao.
     */
    @Test
    public void testGetAllUpcomingCancellationsByCourse() {
        System.out.println("getAllUpcomingCancellationsByCourse");
        int course_id = 50;
        int expResult = 0;
        ArrayList<TimetableCancellations> result = joinDao.getAllUpcomingCancellationsByCourse(course_id);
        assertEquals(expResult, result.size());
    }

    @Test
    public void test2GetAllUpcomingCancellationsByCourse() {
        System.out.println("getAllUpcomingCancellationsByCourse");
        int course_id = 5;
        int expResult = 0;
        ArrayList<TimetableCancellations> result = joinDao.getAllUpcomingCancellationsByCourse(course_id);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of checkPresentCancellationsOnAllStudentsCourse method, of class
     * JoinDao.
     */
    @Test
    public void testCheckPresentCancellationsOnAllStudentsCourse() {
        System.out.println("checkPresentCancellationsOnAllStudentsCourse");
        int student_id = 26;
        boolean expResult = false;
        boolean result = joinDao.checkPresentCancellationsOnAllStudentsCourse(student_id);
        assertEquals(expResult, result);
    }

    @Test
    public void test2CheckPresentCancellationsOnAllStudentsCourse() {
        System.out.println("checkPresentCancellationsOnAllStudentsCourse");
        int student_id = 2;
        boolean expResult = false;
        boolean result = joinDao.checkPresentCancellationsOnAllStudentsCourse(student_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllCourses method, of class JoinDao.
     */
    @Test
    public void testGetAllCourses() {
        System.out.println("getAllCourses");
        int expResult = 6;
        ArrayList<Course> result = joinDao.getAllCourses();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of checkIfTimetableCancellationExistsByCourse method, of class
     * JoinDao.
     */
    @Test
    public void testCheckIfTimetableCancellationExistsByCourse() {
        System.out.println("checkIfTimetableCancellationExistsByCourse");
        int course_id = 26;
        boolean expResult = false;
        boolean result = joinDao.checkIfTimetableCancellationExistsByCourse(course_id);
        assertEquals(expResult, result);
    }

    @Test
    public void test2CheckIfTimetableCancellationExistsByCourse() {
        System.out.println("checkIfTimetableCancellationExistsByCourse");
        int course_id = 6;
        boolean expResult = false;
        boolean result = joinDao.checkIfTimetableCancellationExistsByCourse(course_id);
        assertEquals(expResult, result);
    }

}
