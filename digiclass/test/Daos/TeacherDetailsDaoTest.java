/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.TeacherDetails;
import Daos.TeacherDetailsDao;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author junta
 */
public class TeacherDetailsDaoTest {

    private static final TeacherDetailsDao teacherDetailsDao = new TeacherDetailsDao("digiclass_test");

    public TeacherDetailsDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of insertTeacherDetails method, of class TeacherDetailsDao.
     */
    @Test
    public void testInsertTeacherDetails() {
        System.out.println("1) Test #1 - Inserting Teacher Details using the insertTeacherDetails() method. Using valid inputs");
        int userId = 24;
        String qualifications = "qualifications";
        String about_me = "Test";
        int expResult = 36;
        int result = teacherDetailsDao.insertTeacherDetails(userId, qualifications, about_me);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertTeacherDetails method, of class TeacherDetailsDao. user id
     * not exist
     */
    @Test
    public void test2InsertTeacherDetails() {
        System.out.println("2) Test #2 - Inserting Teacher Details using the insertTeacherDetails() method. (Using an invalid input for the User ID)");
        int userId = 1;
        String qualifications = "qualifications";
        String about_me = "Test";
        int expResult = -1;
        int result = teacherDetailsDao.insertTeacherDetails(userId, qualifications, about_me);
        assertEquals(expResult, result);
    }

//    user id already exist
    @Test
    public void test3InsertTeacherDetails() {
        System.out.println("1) Test #1 - Inserting Teacher Details using the insertTeacherDetails() method. Using valid inputs");
        int userId = 24;
        String qualifications = "qualifications";
        String about_me = "Test";
        int expResult = -1;
        int result = teacherDetailsDao.insertTeacherDetails(userId, qualifications, about_me);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTeacherDetailsByUserId method, of class TeacherDetailsDao.
     */
    @Test
    public void testGetTeacherDetailsByUserId() {
        System.out.println("3) Test #1 - Get Teacher Details using the insertTeacherDetails() method.");
        int userId = 25;
        int expResult = 33;
        TeacherDetails result = teacherDetailsDao.getTeacherDetailsByUserId(userId);
        assertEquals(expResult, result.getTeacher_id());
    }


    /**
     * Test of updateTeacherDetails method, of class TeacherDetailsDao.
     */
    @Test
    public void testUpdateTeacherDetails() {
        System.out.println("5) Test #1 - Update Teacher Details using the updateTeacherDetails() method.");
        boolean expResult = true;
        boolean result = teacherDetailsDao.updateTeacherDetails(25, "about me");
        assertEquals(expResult, result);
    }

    /**
     * Test of updateTeacherDetails method, of class TeacherDetailsDao. user id
     * not exist
     */
    @Test
    public void test2UpdateTeacherDetails() {
        System.out.println("6) Test #2 - Update Teacher Details using the updateTeacherDetails() method.");
        boolean expResult = false;
        boolean result = teacherDetailsDao.updateTeacherDetails(5, "about me");
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllNonVerifiedTeachers method, of class TeacherDetailsDao.
     */
    @Test
    public void testGetAllNonVerifiedTeachers() {
        System.out.println("9) Test #1 - Retrieve all Non-Verified Teachers using the getAllNonVerifiedTeachers() method.");
        int expResult = 1;
        int result = teacherDetailsDao.getAllNonVerifiedTeachers().size();
        assertEquals(expResult, result);
    }

//    return false value
    @Test
    public void test2GetAllNonVerifiedTeachers() {
        System.out.println("9) Test #1 - Retrieve all Non-Verified Teachers using the getAllNonVerifiedTeachers() method.");
        int expResult = 0;
        int result = teacherDetailsDao.getAllNonVerifiedTeachers().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllVerifiedTeachers method, of class TeacherDetailsDao.
     */
    @Test
    public void testGetAllVerifiedTeachers() {
        System.out.println("getAllVerifiedTeachers");
        int expResult = 3;
        ArrayList<TeacherDetails> result = teacherDetailsDao.getAllVerifiedTeachers();
        assertEquals(expResult, result.size());
    }

//    return false value
    @Test
    public void test2GetAllVerifiedTeachers() {
        System.out.println("getAllVerifiedTeachers");
        int expResult = 20;
        ArrayList<TeacherDetails> result = teacherDetailsDao.getAllVerifiedTeachers();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of verifyTeacher method, of class TeacherDetailsDao.
     */
    @Test
    public void testVerifyTeacher() {
        System.out.println("7) Test #1 - Verify a teacher using the verifyTeacher() method.");
        int userId = 28;
        boolean expResult = true;
        ArrayList<TeacherDetails> td = teacherDetailsDao.getAllNonVerifiedTeachers();
        boolean result = false;

        for (TeacherDetails t : td) {
            if (t.getUser_id() == userId) {
                result = teacherDetailsDao.verifyTeacher(userId);
            }
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of verifyTeacher method, of class TeacherDetailsDao. user id not
     * exist
     */
    @Test
    public void test2VerifyTeacher() {
        System.out.println("8) Test #2 - Verify a teacher using the verifyTeacher() method. (Using an invalid input for the User ID)");
        int userId = 0;
        boolean expResult = false;
        ArrayList<TeacherDetails> td = teacherDetailsDao.getAllNonVerifiedTeachers();
        boolean result = false;

        for (TeacherDetails t : td) {
            if (t.getUser_id() == userId) {
                result = teacherDetailsDao.verifyTeacher(userId);
            }
        }
        assertEquals(expResult, result);
    }

//    already verify
    @Test
    public void test3VerifyTeacher() {
        System.out.println("8) Test #2 - Verify a teacher using the verifyTeacher() method. (Using an invalid input for the User ID)");
        int userId = 25;
        boolean expResult = false;
        ArrayList<TeacherDetails> td = teacherDetailsDao.getAllNonVerifiedTeachers();
        boolean result = false;

        for (TeacherDetails t : td) {
            if (t.getUser_id() == userId) {
                result = teacherDetailsDao.verifyTeacher(userId);
            }
        }
        assertEquals(expResult, result);
    }
    
    /**
     * Test of changePremiumStatus method, of class TeacherDetailsDao.
     */
    @Test
    public void testChangePremiumStatus() {
        System.out.println("changePremiumStatus");
        int teacher_id = 33;
        boolean premiumStatus = true;
        boolean expResult = true;
        boolean result = teacherDetailsDao.changePremiumStatus(teacher_id, premiumStatus);
        assertEquals(expResult, result);
    }
    
//    teacherid not found

    @Test
    public void test2ChangePremiumStatus() {
        System.out.println("changePremiumStatus");
        int teacher_id = 50;
        boolean premiumStatus = false;
        boolean expResult = false;
        boolean result = teacherDetailsDao.changePremiumStatus(teacher_id, premiumStatus);
        assertEquals(expResult, result);
    }

    @Test
    public void testUploadQualification() {
        System.out.println("uploadQualification");
        int teacher_id = 33;
        String image = "test.png";
        boolean expResult = true;
        boolean result = teacherDetailsDao.uploadQualification(teacher_id, image);
        assertEquals(expResult, result);
    }

//    user not found
    @Test
    public void test2UploadQualification() {
        System.out.println("uploadQualification");
        int teacher_id = 2;
        String image = "test.png";
        boolean expResult = false;
        boolean result = teacherDetailsDao.uploadQualification(teacher_id, image);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTeacherDetailsByTeacherId method, of class TeacherDetailsDao.
     */
    @Test
    public void testGetTeacherDetailsByTeacherId() {
        System.out.println("getTeacherDetailsByTeacherId");
        int teacherID = 32;
        boolean expResult = true;
        TeacherDetails result = teacherDetailsDao.getTeacherDetailsByTeacherId(teacherID);
        assertEquals(expResult, result);
    }
    
    @Test
    public void test2GetTeacherDetailsByTeacherId() {
        System.out.println("getTeacherDetailsByTeacherId");
        int teacherID = 2;
        boolean expResult = false;
        TeacherDetails result = teacherDetailsDao.getTeacherDetailsByTeacherId(teacherID);
        assertEquals(expResult, result);
    }

}
