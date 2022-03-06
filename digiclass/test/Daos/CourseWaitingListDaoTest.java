/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Daos.CourseWaitingListDao;
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
public class CourseWaitingListDaoTest {
    public static CourseWaitingListDao coursewaitinglistDao = new CourseWaitingListDao("digiclass_test");
    public CourseWaitingListDaoTest() {
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
     * Test of insertStudentInWaitingList method, of class CourseWaitingListDao.
     */
    @Test
    public void testInsertStudentInWaitingList() {
        System.out.println("insertStudentInWaitingList");
        int course_id = 45;
        int student_id = 26;
        boolean expResult = true;
        boolean result = coursewaitinglistDao.insertStudentInWaitingList(course_id, student_id);
        assertEquals(expResult, result);
    }
    
     @Test
    public void test2InsertStudentInWaitingList() {
        System.out.println("insertStudentInWaitingList");
        int course_id = -1;
        int student_id = 16;
        boolean expResult = false;
        boolean result = coursewaitinglistDao.insertStudentInWaitingList(course_id, student_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of enrollStudentInCourse method, of class CourseWaitingListDao.
     */
    @Test
    public void testEnrollStudentInCourse() {
        System.out.println("enrollStudentInCourse");
        int course_id = 45;
        int student_id = 26;
        boolean expResult = true;
        boolean result = coursewaitinglistDao.enrollStudentInCourse(course_id, student_id);
        assertEquals(expResult, result);
    }
    
    @Test
    public void test2EnrollStudentInCourse() {
        System.out.println("enrollStudentInCourse");
        int course_id = -1;
        int student_id = 16;
        boolean expResult = false;
        boolean result = coursewaitinglistDao.enrollStudentInCourse(course_id, student_id);
        assertEquals(expResult, result);
    }
    
    @Test
    public void test3EnrollStudentInCourse() {
        System.out.println("enrollStudentInCourse");
        int course_id = 20;
        int student_id = 10;
        boolean expResult = false;
        boolean result = coursewaitinglistDao.enrollStudentInCourse(course_id, student_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of studentInCourseWaitingList method, of class CourseWaitingListDao.
     */
    @Test
    public void testStudentInCourseWaitingList() {
        System.out.println("studentInCourseWaitingList");
        int course_id = 44;
        int student_id = 29;
        boolean expResult = true;
        boolean result = coursewaitinglistDao.studentInCourseWaitingList(course_id, student_id);
        assertEquals(expResult, result);
    }
    
     @Test
    public void test2StudentInCourseWaitingList() {
        System.out.println("studentInCourseWaitingList");
        int course_id = 4;
        int student_id = 29;
        boolean expResult = false;
        boolean result = coursewaitinglistDao.studentInCourseWaitingList(course_id, student_id);
        assertEquals(expResult, result);
    }
    
     @Test
    public void test3StudentInCourseWaitingList() {
        System.out.println("studentInCourseWaitingList");
        int course_id = 44;
        int student_id = 9;
        boolean expResult = false;
        boolean result = coursewaitinglistDao.studentInCourseWaitingList(course_id, student_id);
        assertEquals(expResult, result);
    }
}
