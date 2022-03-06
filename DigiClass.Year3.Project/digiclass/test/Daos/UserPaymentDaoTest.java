/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Daos.UserPaymentDao;
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
public class UserPaymentDaoTest {

    private static final UserPaymentDao userPaymentDao = new UserPaymentDao("digiclass_test");

    public UserPaymentDaoTest() {
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
     * Test of insertPaymentDetails method, of class UserPaymentDao.
     */
    @Test
    public void testInsertPaymentDetails() {
        System.out.println("insertPaymentDetails");
        int user_id = 24;
        int course_id = 44;
        boolean expResult = true;
        boolean result = userPaymentDao.insertPaymentDetails(user_id, course_id);
        assertEquals(expResult, result);
    }

    @Test
    public void test2InsertPaymentDetails() {
        System.out.println("insertPaymentDetails");
        int user_id = 2;
        int course_id = 44;
        boolean expResult = false;
        boolean result = userPaymentDao.insertPaymentDetails(user_id, course_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertCoursePaymentLater method, of class UserPaymentDao.
     */
    @Test
    public void testInsertCoursePaymentLater() {
        System.out.println("insertCoursePaymentLater");
        int user_id = 26;
        int course_id = 44;
        boolean expResult = true;
        boolean result = userPaymentDao.insertCoursePaymentLater(user_id, course_id);
        assertEquals(expResult, result);
    }

    @Test
    public void test2InsertCoursePaymentLater() {
        System.out.println("insertCoursePaymentLater");
        int user_id = 26;
        int course_id = 4;
        boolean expResult = true;
        boolean result = userPaymentDao.insertCoursePaymentLater(user_id, course_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of userCoursePaymentExists method, of class UserPaymentDao.
     */
    @Test
    public void testUserCoursePaymentExists() {
        System.out.println("userCoursePaymentExists");
        int user_id = 24;
        int course_id = 44;
        boolean expResult = true;
        boolean result = userPaymentDao.userCoursePaymentExists(user_id, course_id);
        assertEquals(expResult, result);
    }

    @Test
    public void test2UserCoursePaymentExists() {
        System.out.println("userCoursePaymentExists");
        int user_id = 4;
        int course_id = 44;
        boolean expResult = false;
        boolean result = userPaymentDao.userCoursePaymentExists(user_id, course_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of userCoursePaymentInDate method, of class UserPaymentDao.
     */
    @Test
    public void testUserCoursePaymentInDate() {
        System.out.println("userCoursePaymentInDate");
        int user_id = 24;
        int course_id = 44;
        boolean expResult = true;
        boolean result = userPaymentDao.userCoursePaymentInDate(user_id, course_id);
        assertEquals(expResult, result);
    }

    @Test
    public void test2UserCoursePaymentInDate() {
        System.out.println("userCoursePaymentInDate");
        int user_id = 4;
        int course_id = 44;
        boolean expResult = false;
        boolean result = userPaymentDao.userCoursePaymentInDate(user_id, course_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateCoursePayment method, of class UserPaymentDao.
     */
    @Test
    public void testUpdateCoursePayment() {
        System.out.println("updateCoursePayment");
        int user_id = 24;
        int course_id = 44;
        boolean expResult = true;
        boolean result = userPaymentDao.updateCoursePayment(user_id, course_id);
        assertEquals(expResult, result);
    }

    @Test
    public void test2UpdateCoursePayment() {
        System.out.println("updateCoursePayment");
        int user_id = 2;
        int course_id = 44;
        boolean expResult = false;
        boolean result = userPaymentDao.updateCoursePayment(user_id, course_id);
        assertEquals(expResult, result);
    }

}
