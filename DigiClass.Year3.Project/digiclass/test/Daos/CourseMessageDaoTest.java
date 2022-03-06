/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.CourseMessage;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author conor
 */
public class CourseMessageDaoTest {
    public static CourseMessageDao courseMessageDao = new CourseMessageDao("digiclass_test");
    public CourseMessageDaoTest() {
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
     * Test of insertMessage method, of class CourseMessageDao.
     */
    @Test
    public void testInsertMessage() {
        System.out.println("insertMessage");
        int course_id = 44;
        String message = "Welcome";
        boolean expResult = true;
        boolean result = courseMessageDao.insertMessage(course_id, message);
        assertEquals(expResult, result);
    }
    
    
      @Test
    public void test2InsertMessage() {
        System.out.println("insertMessage");
        int course_id = 4;
        String message = "Welcome";
        boolean expResult = false;
        boolean result = courseMessageDao.insertMessage(course_id, message);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateMessage method, of class CourseMessageDao.
     */
    @Test
    public void testUpdateMessage() {
        System.out.println("updateMessage");
        int course_id = 44;
        String message = "Welcome!!!";
        boolean expResult = true;
        boolean result = courseMessageDao.updateMessage(course_id, message);
        assertEquals(expResult, result);
    }
    
    @Test
    public void test2UpdateMessage() {
        System.out.println("updateMessage");
        int course_id = 4;
        String message = "well!!";
        boolean expResult = false;
        boolean result = courseMessageDao.updateMessage(course_id, message);
        assertEquals(expResult, result);
    }


    /**
     * Test of deleteMessage method, of class CourseMessageDao.
     */
    @Test
    public void testDeleteMessage() {
        System.out.println("deleteMessage");
        int course_message_id = 3;
        boolean expResult = true;
        boolean result = courseMessageDao.deleteMessage(course_message_id);
        assertEquals(expResult, result);
    }
    
    @Test
    public void test2DeleteMessage() {
        System.out.println("deleteMessage");
        int course_message_id = 55;
        boolean expResult = false;
        boolean result = courseMessageDao.deleteMessage(course_message_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllMessagesByPublishDateCourseId method, of class CourseMessageDao.
     */
    @Test
    public void testGetAllMessagesByPublishDateCourseId() {
        System.out.println("getAllMessagesByPublishDateCourseId");
        int courseId = 44;
        int expResult = 2;
        ArrayList<CourseMessage> result = courseMessageDao.getAllMessagesByPublishDateCourseId(courseId);
        assertEquals(expResult, result.size());
    }
    
    @Test
    public void test2GetAllMessagesByPublishDateCourseId() {
        System.out.println("getAllMessagesByPublishDateCourseId");
        int courseId = 4;
        ArrayList<CourseMessage> expResult = new ArrayList();
        ArrayList<CourseMessage> result = courseMessageDao.getAllMessagesByPublishDateCourseId(courseId);
        assertEquals(expResult, result);
    }
    
}
