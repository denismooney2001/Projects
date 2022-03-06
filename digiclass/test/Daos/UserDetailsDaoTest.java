/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.UserDetails;
import Daos.UserDetailsDao;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author junta
 */
public class UserDetailsDaoTest {

    private static final UserDetailsDao userDetailsDao = new UserDetailsDao("digiclass_test");

    public UserDetailsDaoTest() {
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
     * Test of getUserDetailsByUserId method, of class UserDetailsDao.
     */
    @Test
    public void testGetUserDetailsByUserId() {
        System.out.println("getUserDetailsByUserId");
        int userId = 25;
        String expResult = "UserDetails{user_id=25, first_name=Denis, last_name=Mooney, profile_picture=, contact_number=0831587369, date_of_birth=2021-03-17, addressLine1=8 Carlinn Green, addressLine2=Mullaharlin Road}";
        String result = userDetailsDao.getUserDetailsByUserId(userId).toString();
        System.out.println(result);
        assertEquals(expResult, result);
    }

//    user not exist
    @Test
    public void test2GetUserDetailsByUserId() {
        System.out.println("getUserDetailsByUserId");
        int userId = 1;
        UserDetails expResult = null;
        UserDetails result = userDetailsDao.getUserDetailsByUserId(userId);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertUserDetails method, of class UserDetailsDao. duplicate
     * results
     */
    @Test
    public void testInsertUserDetails() {
        System.out.println("insertUserDetails");
        int user_id = 17;
        String first_name = "Denis";
        String last_name = "Mooney";
        String profile_picture = "";
        String contact_number = "0831587369";
        String date_of_birth = "2019-11-14";
        String addressLine1 = "8 Carlinn Green";
        String addressLine2 = "Mullaharlin Road";
        boolean expResult = false;
        boolean result = userDetailsDao.insertUserDetails(user_id, first_name, last_name, profile_picture, contact_number, date_of_birth, addressLine1, addressLine2);
        assertEquals(expResult, result);
    }

//    User not exists
    @Test
    public void test2InsertUserDetails() {
        System.out.println("insertUserDetails");
        int user_id = 20;
        String first_name = "Denis";
        String last_name = "Mooney";
        String profile_picture = "";
        String contact_number = "0831587369";
        String date_of_birth = "2019-11-14";
        String addressLine1 = "8 Carlinn Green";
        String addressLine2 = "Mullaharlin Road";
        boolean expResult = false;
        boolean result = userDetailsDao.insertUserDetails(user_id, first_name, last_name, profile_picture, contact_number, date_of_birth, addressLine1, addressLine2);
        assertEquals(expResult, result);
    }

    @Test
    public void test3InsertUserDetails() {
        System.out.println("insertUserDetails");
        int user_id = 29;
        String first_name = "Denis";
        String last_name = "Mooney123";
        String profile_picture = "";
        String contact_number = "0831587369";
        String date_of_birth = "2019-11-14";
        String addressLine1 = "8 Carlinn Green";
        String addressLine2 = "Mullaharlin Road";
        boolean expResult = true;
        boolean result = userDetailsDao.insertUserDetails(user_id, first_name, last_name, profile_picture, contact_number, date_of_birth, addressLine1, addressLine2);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateUserDetails method, of class UserDetailsDao.
     */
    @Test
    public void testUpdateUserDetails() {
        System.out.println("updateUserDetails");
        int user_id = 26;
        String first_name = "Denis";
        String last_name = "Mooney";
        String contact_number = "0831587369";
        String date_of_birth = "2019-11-14";
        String addressLine1 = "8  Green";
        String addressLine2 = "Mullaharlin Road";
        boolean expResult = true;
        boolean result = userDetailsDao.updateUserDetails(user_id, first_name, last_name, contact_number, date_of_birth, addressLine1, addressLine2);
        assertEquals(expResult, result);
    }

//    user not exists
    @Test
    public void test2UpdateUserDetails() {
        System.out.println("updateUserDetails");
        int user_id = 100;
        String first_name = "Denis";
        String last_name = "Mooney";
        String contact_number = "0831587369";
        String date_of_birth = "2019-11-14";
        String addressLine1 = "8 Carlinn Green";
        String addressLine2 = "Mullaharlin Road";
        boolean expResult = false;
        boolean result = userDetailsDao.updateUserDetails(user_id, first_name, last_name, contact_number, date_of_birth, addressLine1, addressLine2);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateImage method, of class UserDetailsDao.
     */
    @Test
    public void testUpdateImage() {
        System.out.println("updateImage");
        int user_id = 22;
        String image = "test.png";
        boolean expResult = true;
        boolean result = userDetailsDao.updateImage(user_id, image);
        assertEquals(expResult, result);
    }

//    accept null value
    @Test
    public void test2UpdateImage() {
        System.out.println("updateImage");
        int user_id = 24;
        String image = null;
        boolean expResult = true;
        boolean result = userDetailsDao.updateImage(user_id, image);
        assertEquals(expResult, result);
    }
}
