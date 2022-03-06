/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

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
public class CourseLinkDaoTest {
    public static CourseLinkDao courseLinkDao = new CourseLinkDao("digiclass_test");
    public CourseLinkDaoTest() {
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
     * Test of insertCourseLink method, of class CourseLinkDao.
     */
    @Test
    public void testInsertCourseLink() {
        System.out.println("insertCourseLink");
        int course_id = 44;
        String course_link = "Link";
        boolean expResult = true;
        boolean result = courseLinkDao.insertCourseLink(course_id, course_link);
        assertEquals(expResult, result);
    }
    
    @Test
    public void test2InsertCourseLink() {
        System.out.println("insertCourseLink");
        int course_id = 4;
        String course_link = "Link";
        boolean expResult = false;
        boolean result = courseLinkDao.insertCourseLink(course_id, course_link);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getLink method, of class CourseLinkDao.
     */
    @Test
    public void testGetLink() {
        System.out.println("getLink");
        int course_id = 44;
        String expResult = "Link";
        String result = courseLinkDao.getLink(course_id);
        assertEquals(expResult, result);
    }
    
    @Test
    public void test2GetLink() {
        System.out.println("getLink");
        int course_id = 4;
        String expResult = "";
        String result = courseLinkDao.getLink(course_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateLink method, of class CourseLinkDao.
     */
    @Test
    public void testUpdateLink() {
        System.out.println("updateLink");
        int course_id = 44;
        String course_link = "Link";
        boolean expResult = true;
        boolean result = courseLinkDao.updateLink(course_id, course_link);
        assertEquals(expResult, result);
    }
    
      @Test
    public void test2UpdateLink() {
        System.out.println("updateLink");
        int course_id = 4;
        String course_link = "Link";
        boolean expResult = false;
        boolean result = courseLinkDao.updateLink(course_id, course_link);
        assertEquals(expResult, result);
    }
    

    
}
