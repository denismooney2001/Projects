/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.Timetable;
import Daos.TimetableDao;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author D00223984
 */
public class TimetableDaoTest {

    public static TimetableDao timeDao = new TimetableDao("digiclass_test");
    ArrayList<Timetable> a1 = new ArrayList<>();

    public TimetableDaoTest() {
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

    @Test
    public void testInsertTimetable() {
        System.out.println("insertTimetable");
        int course_id = 45;
        String day = "Friday";
        int time = 15;
        boolean expResult = true;
        boolean result = false;
        System.out.println(timeDao.courseDayTimeExsits(course_id, day, time));
        if (timeDao.courseDayTimeExsits(course_id, day, time) == false) {
            result = timeDao.insertTimetable(course_id, day, time);
            System.out.println(result);
        }
        assertEquals(expResult, result);
    }

//    course id not exist
    @Test
    public void test2InsertTimetable() {
        System.out.println("insertTimetable");
        int course_id = 10;
        String day = "Monday";
        int time = 15;
        boolean expResult = false;
        boolean result = false;
        if (timeDao.courseDayTimeExsits(course_id, day, time) == false) {
            result = timeDao.insertTimetable(course_id, day, time);
        }
        assertEquals(expResult, result);
    }

//    duplicate record insert
    @Test
    public void test3InsertTimetable() {
        System.out.println("insertTimetable");
        int course_id = 46;
        String day = "Monday";
        int time = 20;
        boolean expResult = false;
        boolean result = false;
        if (timeDao.courseDayTimeExsits(course_id, day, time) == false) {
            result = timeDao.insertTimetable(course_id, day, time);
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of updateTimetableByTimetableid method, of class TimetableDao.
     */
    @Test
    public void testUpdateTimetableByTimetableid() {
        System.out.println("updateTimetableByTimetableid");
        int timetable_id = 72;
        String day = "Friday";
        int time = 20;
        boolean expResult = true;
        boolean result = timeDao.updateTimetableByTimetableid(timetable_id, day, time);
        assertEquals(expResult, result);
    }

//    timetable not exist
    @Test
    public void test2UpdateTimetableByTimetableid() {
        System.out.println("updateTimetableByTimetableid");
        int timetable_id = 9;
        String day = "Friday";
        int time = 15;
        boolean expResult = false;
        boolean result = timeDao.updateTimetableByTimetableid(timetable_id, day, time);
        assertEquals(expResult, result);
    }

    /**
     * Test of courseDayTimeExsits method, of class TimetableDao.
     */
    @Test
    public void testCourseDayTimeExsits() {
        System.out.println("courseDayTimeExsits");
        int course_id = 46;
        String day = "Monday";
        int time = 20;
        boolean expResult = true;
        boolean result = timeDao.courseDayTimeExsits(course_id, day, time);
        assertEquals(expResult, result);
    }

//    courseid not exist
    @Test
    public void test2CourseDayTimeExsits() {
        System.out.println("courseDayTimeExsits");
        int course_id = 2;
        String day = "Saturday";
        int time = 22;
        boolean expResult = false;
        boolean result = timeDao.courseDayTimeExsits(course_id, day, time);
        assertEquals(expResult, result);
    }

//    no same record on database
    @Test
    public void test3CourseDayTimeExsits() {
        System.out.println("courseDayTimeExsits");
        int course_id = 28;
        String day = "Monday";
        int time = 22;
        boolean expResult = false;
        boolean result = timeDao.courseDayTimeExsits(course_id, day, time);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTimeTablesbyCourse method, of class TimetableDao.
     */
    @Test
    public void testGetTimeTablesbyCourse() {
        System.out.println("getTimeTablesbyCourse");
        int courseID = 44;
        int expResult = 2;
        ArrayList<Timetable> result = timeDao.getTimeTablesbyCourse(courseID);
        assertEquals(expResult, result.size());
    }
    
    @Test
    public void test2GetTimeTablesbyCourse() {
        System.out.println("getTimeTablesbyCourse");
        int courseID = 4;
        ArrayList<Timetable> expResult = new ArrayList();
        ArrayList<Timetable> result = timeDao.getTimeTablesbyCourse(courseID);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCourseIdByTid method, of class TimetableDao.
     */
    @Test
    public void testGetCourseIdByTid() {
        System.out.println("getCourseIdByTid");
        int timeId = 19;
        int expResult = 44;
        int result = timeDao.getCourseIdByTid(timeId);
        assertEquals(expResult, result);
    }
    
    @Test
    public void test2GetCourseIdByTid() {
        System.out.println("getCourseIdByTid");
        int timeId = 9;
        int expResult = 44;
        int result = timeDao.getCourseIdByTid(timeId);
        assertEquals(expResult, result);
    }

}
