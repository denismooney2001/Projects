/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.Notification;
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
public class NotificationDaoTest {

    public static NotificationDao notificationDao = new NotificationDao("digiclass_test");

    public NotificationDaoTest() {
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
     * Test of getNotificationById method, of class NotificationDao.
     */
    @Test
    public void testGetNotificationById() {
        System.out.println("getNotificationById");
        int userID = 22;
        int expResult = 4;
        ArrayList<Notification> result = notificationDao.getNotificationById(userID);
        assertEquals(expResult, result.size());
    }

    @Test
    public void test2GetNotificationById() {
        System.out.println("getNotificationById");
        int userID = 2;
        int expResult = 0;
        ArrayList<Notification> result = notificationDao.getNotificationById(userID);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of deleteNotification method, of class NotificationDao.
     */
    @Test
    public void testDeleteNotification() {
        System.out.println("deleteNotification");
        int notification_id = 6;
        boolean expResult = true;
        boolean result = notificationDao.deleteNotification(notification_id);
        assertEquals(expResult, result);
    }

    @Test
    public void test2DeleteNotification() {
        System.out.println("deleteNotification");
        int notification_id = 150;
        boolean expResult = false;
        boolean result = notificationDao.deleteNotification(notification_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateNotification method, of class NotificationDao.
     */
    @Test
    public void testUpdateNotification() {
        System.out.println("updateNotification");
        int notification_id = 4;
        boolean expResult = true;
        boolean result = notificationDao.updateNotification(notification_id);
        assertEquals(expResult, result);
    }

    @Test
    public void test2UpdateNotification() {
        System.out.println("updateNotification");
        int notification_id = 150;
        boolean expResult = false;
        boolean result = notificationDao.updateNotification(notification_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertNotification method, of class NotificationDao.
     */
    @Test
    public void testInsertNotification() {
        System.out.println("insertNotification");
        int user_id = 22;
        String description = "Results";
        boolean expResult = true;
        boolean result = notificationDao.insertNotification(user_id, description);
        assertEquals(expResult, result);
    }

    @Test
    public void test2InsertNotification() {
        System.out.println("insertNotification");
        int user_id = 2;
        String description = "Results";
        boolean expResult = false;
        boolean result = notificationDao.insertNotification(user_id, description);
        assertEquals(expResult, result);
    }

}
