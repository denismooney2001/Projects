/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.Enquiries;
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
public class EnquiriesDaoTest {

    public static EnquiriesDao enquiriesDao = new EnquiriesDao("digiclass_test");

    public EnquiriesDaoTest() {

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
    ////////////////////////////*************NEEDS TO BE LOOKED AT
    /**
     * Test of InsertInquiry method, of class EnquiriesDao.
     */
    @Test
    public void testInsertInquiry() {
        System.out.println("InsertInquiry");
        String full_name = "123";
        String email = "G";
        String message = "123";
        boolean expResult = true;
        boolean result = enquiriesDao.InsertInquiry(full_name, email, message);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllUnseenEnquiries method, of class EnquiriesDao.
     */
    @Test
    public void testGetAllUnseenEnquiries() {
        System.out.println("getAllUnseenEnquiries");
        int expResult = 5;
        int result = enquiriesDao.getAllUnseenEnquiries().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllSeenEnquiries method, of class EnquiriesDao.
     */
    @Test
    public void testGetAllSeenEnquiries() {
        System.out.println("getAllSeenEnquiries");
        int expResult = 3;
        int result = enquiriesDao.getAllSeenEnquiries().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of setInquiryToSeen method, of class EnquiriesDao.
     */
    @Test
    public void testSetInquiryToSeen() {
        System.out.println("setInquiryToSeen");
        int inquiry_id = 8;
        boolean expResult = true;
        boolean result = enquiriesDao.setInquiryToSeen(inquiry_id);
        assertEquals(expResult, result);
    }

    @Test
    public void test2SetInquiryToSeen() {
        System.out.println("setInquiryToSeen");
        int inquiry_id = -1;
        boolean expResult = false;
        boolean result = enquiriesDao.setInquiryToSeen(inquiry_id);
        assertEquals(expResult, result);
    }
}
