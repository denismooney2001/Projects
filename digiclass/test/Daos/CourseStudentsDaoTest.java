/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.CourseStudents;
import Daos.CourseStudentsDao;
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
public class CourseStudentsDaoTest {

    public static CourseStudentsDao coursestudentsDao = new CourseStudentsDao("digiclass_test");

    public CourseStudentsDaoTest() {
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
     * Test of insertCourseStudent method, of class CourseStudentsDao.
     */
    @Test
    public void testInsertCourseStudent() {
        System.out.println("insertCourseStudent");
        int course_id = 44;
        int student_id = 29;
        boolean expResult = true;
        boolean result = coursestudentsDao.insertCourseStudent(course_id, student_id);
        assertEquals(expResult, result);
    }

    @Test
    public void test2InsertCourseStudent() {
        System.out.println("insertCourseStudent");
        int course_id = -1;
        int student_id = 16;
        boolean expResult = false;
        boolean result = coursestudentsDao.insertCourseStudent(course_id, student_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCourseCount method, of class CourseStudentsDao.
     */
    @Test
    public void testGetCourseCount() {
        System.out.println("getCourseCount");
        int courseId = 44;
        int expResult = 2;
        int result = coursestudentsDao.getCourseCount(courseId);
        assertEquals(expResult, result);
    }

    @Test
    public void test2GetCourseCount() {
        System.out.println("getCourseCount");
        int courseId = -1;
        int expResult = 0;
        int result = coursestudentsDao.getCourseCount(courseId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getStudentIdsInCourse method, of class CourseStudentsDao.
     */
    @Test
    public void testGetStudentIdsInCourse() {
        System.out.println("getStudentIdsInCourse");
        int courseId = 44;
        int expResult = 2;
        ArrayList<Integer> result = coursestudentsDao.getStudentIdsInCourse(courseId);
        assertEquals(expResult, result.size());
    }

    @Test
    public void test2GetStudentIdsInCourse() {
        System.out.println("getStudentIdsInCourse");
        int courseId = 4;
        ArrayList<Integer> expResult = new ArrayList();
        ArrayList<Integer> result = coursestudentsDao.getStudentIdsInCourse(courseId);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteStudentCourse method, of class CourseStudentsDao.
     */
    @Test
    public void testDeleteStudentCourse() {
        System.out.println("deleteStudentCourse");
        int course_id = 20;
        int user_id = 18;
        boolean expResult = false;
        boolean result = coursestudentsDao.deleteStudentCourse(course_id, user_id);
        assertEquals(expResult, result);
    }

    @Test
    public void test2DeleteStudentCourse() {
        System.out.println("deleteStudentCourse");
        int course_id = 10;
        int user_id = 19;
        boolean expResult = false;
        boolean result = coursestudentsDao.deleteStudentCourse(course_id, user_id);
        assertEquals(expResult, result);
    }

    @Test
    public void test3DeleteStudentCourse() {
        System.out.println("deleteStudentCourse");
        int course_id = 20;
        int user_id = 18;
        boolean expResult = false;
        boolean result = coursestudentsDao.deleteStudentCourse(course_id, user_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of selectStudentsByCourseId method, of class CourseStudentsDao.
     */
    @Test
    public void testSelectStudentsByCourseId() {
        System.out.println("selectStudentsByCourseId");
        int courseID = 44;
        int expResult = 3;
        ArrayList<CourseStudents> result = coursestudentsDao.selectStudentsByCourseId(courseID);
        assertEquals(expResult, result.size());
    }

    @Test
    public void test2SelectStudentsByCourseId() {
        System.out.println("selectStudentsByCourseId");
        int courseID = 4;
        ArrayList<CourseStudents> expResult = new ArrayList();
        ArrayList<CourseStudents> result = coursestudentsDao.selectStudentsByCourseId(courseID);
        assertEquals(expResult, result);
    }

    /**
     * Test of studentEnrolledInCourse method, of class CourseStudentsDao.
     */
    @Test
    public void testStudentEnrolledInCourse() {
        System.out.println("studentEnrolledInCourse");
        int course_id = 44;
        int student_id = 26;
        boolean expResult = true;
        boolean result = coursestudentsDao.studentEnrolledInCourse(course_id, student_id);
        assertEquals(expResult, result);
    }

    @Test
    public void test2StudentEnrolledInCourse() {
        System.out.println("studentEnrolledInCourse");
        int course_id = 44;
        int student_id = 2;
        boolean expResult = false;
        boolean result = coursestudentsDao.studentEnrolledInCourse(course_id, student_id);
        assertEquals(expResult, result);
    }

    @Test
    public void test3StudentEnrolledInCourse() {
        System.out.println("studentEnrolledInCourse");
        int course_id = 4;
        int student_id = 26;
        boolean expResult = false;
        boolean result = coursestudentsDao.studentEnrolledInCourse(course_id, student_id);
        assertEquals(expResult, result);
    }

}
