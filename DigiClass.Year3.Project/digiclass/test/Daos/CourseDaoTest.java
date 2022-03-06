/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.Course;
import Daos.CourseDao;
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
public class CourseDaoTest {

    public static CourseDao courseDao = new CourseDao("digiclass_test");

    public CourseDaoTest() {
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
     * Test of createCourse method, of class CourseDao.
     */
    @Test
    public void test1CreateCourse() throws Exception {
        System.out.println("createCourse");
        int teacher_subject_id = 50;
        String description = "description";
        int places = 10;
        String start_date = "2021-02-26";
        String end_date = "2021-05-23";
        int expResult = 78;
        int result = courseDao.insertCourse(teacher_subject_id, places, description, start_date, end_date);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertCourse method, of class CourseDao.
     */
    @Test
    public void test2InsertCourse() {
        System.out.println("insertCourse");
        int teacher_subject_id = 38;
        int places = 20;
        String description = "Hungarian Course for (OL students)";
        String start_date = "2021-03-29";
        String end_date = "2021-04-07";
        int expResult = 0;
        int result = courseDao.insertCourse(teacher_subject_id, places, description, start_date, end_date);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertCourse method, of class CourseDao.
     */
    @Test
    public void test3InsertCourse() {
        System.out.println("insertCourse");
        int teacher_subject_id = 38;
        int places = 20;
        String description = "Hungarian Course for (OL students)";
        String start_date = "2021-03-19";
        String end_date = "2021-04-06";
        int expResult = 0;
        int result = courseDao.insertCourse(teacher_subject_id, places, description, start_date, end_date);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertCourse method, of class CourseDao.
     */
    @Test
    public void test4InsertCourse() {
        System.out.println("insertCourse");
        int teacher_subject_id = 38;
        int places = 20;
        String description = "A Place for the good";
        String start_date = "2021-03-29";
        String end_date = "2021-04-06";
        int expResult = 0;
        int result = courseDao.insertCourse(teacher_subject_id, places, description, start_date, end_date);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertCourse method, of class CourseDao.
     */
    @Test
    public void test5InsertCourse() {
        System.out.println("insertCourse");
        int teacher_subject_id = 38;
        int places = 0;
        String description = "Hungarian Course for (OL students)";
        String start_date = "2021-03-29";
        String end_date = "2021-04-06";
        int expResult = 0;
        int result = courseDao.insertCourse(teacher_subject_id, places, description, start_date, end_date);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertCourse method, of class CourseDao.
     */
    @Test
    public void test6InsertCourse() {
        System.out.println("insertCourse");
        int teacher_subject_id = 1;
        int places = 20;
        String description = "Hungarian Course for (OL students)";
        String start_date = "2021-03-29";
        String end_date = "2021-04-06";
        int expResult = 0;
        int result = courseDao.insertCourse(teacher_subject_id, places, description, start_date, end_date);
        assertEquals(expResult, result);
    }

    /**
     * Test of disableCourse method, of class CourseDao.
     */
    @Test
    public void testDisableCourse() {
        System.out.println("disableCourse");
        int course_id = 29;
        boolean expResult = false;
        boolean result = courseDao.disableCourse(course_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of disableCourse method, of class CourseDao.
     */
    @Test
    public void test2DisableCourse() {
        System.out.println("disableCourse");
        int course_id = 1;
        boolean expResult = false;
        boolean result = courseDao.disableCourse(course_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkIfTeacherSubjectIdIsOngoing method, of class CourseDao.
     */
    @Test
    public void testCheckIfTeacherSubjectIdIsOngoing() {
        System.out.println("checkIfTeacherSubjectIdIsOngoing");
        int teacher_subject_id = 39;
        boolean expResult = false;
        boolean result = courseDao.checkIfTeacherSubjectIdIsOngoing(teacher_subject_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkIfTeacherSubjectIdIsOngoing method, of class CourseDao.
     */
    @Test
    public void test2CheckIfTeacherSubjectIdIsOngoing() {
        System.out.println("checkIfTeacherSubjectIdIsOngoing");
        int teacher_subject_id = 1;
        boolean expResult = false;
        boolean result = courseDao.checkIfTeacherSubjectIdIsOngoing(teacher_subject_id);
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateOngoingCourse() {
        System.out.println("updateOngoingCourse");
        int course_id = 50;
        int course_places = 20;
        String description = "Course Description";
        String startDate = "2021-04-05";
        String endDate = "2021-03-30";
        boolean expResult = true;
        boolean result = courseDao.updateOngoingCourse(course_id, course_places, description, startDate, endDate);
        assertEquals(expResult, result);
    }

    @Test
    public void test2UpdateOngoingCourse() {
        System.out.println("updateOngoingCourse");
        int course_id = 0;
        int course_places = 20;
        String description = "Course Description";
        String startDate = "";
        String endDate = "";
        boolean expResult = false;
        boolean result = courseDao.updateOngoingCourse(course_id, course_places, description, startDate, endDate);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllExistingCourses method, of class CourseDao.
     */
    @Test
    public void testGetAllExistingCourses() {
        System.out.println("getAllExistingCourses");

        int expResult = 5;
        ArrayList<Course> result = courseDao.getAllExistingCourses();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getAllExistingCoursesByTeacherId method, of class CourseDao.
     */
    @Test
    public void testGetAllExistingCoursesByTeacherId() {
        System.out.println("getAllExistingCoursesByTeacherId");
        int teacher_id = 32;
        int expResult = 5;
        ArrayList<Course> result = courseDao.getAllExistingCoursesByTeacherId(teacher_id);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getCoursesNotYetStarted method, of class CourseDao.
     */
    @Test
    public void testGetCoursesNotYetStarted() {
        System.out.println("getCoursesNotYetStarted");
        int teacher_id = 35;

        int expResult = 0;
        ArrayList<Course> result = courseDao.getCoursesNotYetStarted(teacher_id);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getStartedOngoingCourses method, of class CourseDao.
     */
    @Test
    public void testGetStartedOngoingCourses() {
        System.out.println("getStartedOngoingCourses");
        int teacher_id = 32;
        int expResult = 5;
        ArrayList<Course> result = courseDao.getStartedOngoingCourses(teacher_id);
        assertEquals(expResult, result.size());
    }

}
