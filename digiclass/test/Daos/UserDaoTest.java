/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.User;
import Daos.UserDao;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author junta
 */
public class UserDaoTest {

    public static UserDao userDao = new UserDao("digiclass_test");

    public UserDaoTest() {
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
     * Test of userSignup method, of class UserDao.
     *
     * @throws java.security.NoSuchAlgorithmException
     */
    @Test
    public void testUserSignup() throws NoSuchAlgorithmException {
        System.out.println("userSignup");
        String userN = "Test123";
        String email = "test@gmail.com";
        String pass = "denismooney123!";
        String userType = "ACTIVE";
        boolean expResult = false;
        boolean result = false;
        if (userN != null && !"".equals(userN) && pass != null && !"".equals(pass) && email != null && !"".equals(email)) {
            result = userDao.userSignup(userN, email, pass, userType);
        }
        assertEquals(expResult, result);
      
    }

//    password not insert
    @Test
    public void test2UserSignup() throws NoSuchAlgorithmException {
        System.out.println("userSignup");
        String userN = "Bob";
        String email = "denis.e.mooney@gmail.com";
        String pass = "";
        String userType = "ACTIVE";
        boolean expResult = false;
        boolean result = false;
        if (userN != null && !"".equals(userN) && pass != null && !"".equals(pass) && email != null && !"".equals(email)) {
            result = userDao.userSignup(userN, email, pass, userType);
        }
        assertEquals(expResult, result);
    }

//    username not insert
    @Test
    public void test3UserSignup() throws NoSuchAlgorithmException {
        System.out.println("userSignup");
        String userN = "";
        String email = "denis.e.mooney@gmail.com";
        String pass = "test1232!";
        String userType = "ACTIVE";
        boolean expResult = false;
        boolean result = false;
        if (userN != null && !"".equals(userN) && pass != null && !"".equals(pass) && email != null && !"".equals(email)) {
            result = userDao.userSignup(userN, email, pass, userType);
        }
        assertEquals(expResult, result);
    }

//    email not insert
    @Test
    public void test4UserSignup() throws NoSuchAlgorithmException {
        System.out.println("userSignup");
        String userN = "denis";
        String email = "";
        String pass = "test1232!";
        String userType = "ACTIVE";
        boolean expResult = false;
        boolean result = false;
        if (userN != null && !"".equals(userN) && pass != null && !"".equals(pass) && email != null && !"".equals(email)) {
            result = userDao.userSignup(userN, email, pass, userType);
        }
        assertEquals(expResult, result);
    }

//    username exist
    @Test
    public void test5UserSignup() throws NoSuchAlgorithmException {
        System.out.println("userSignup");
        String userN = "denisTeacher";
        String email = "testing123@gmail.com";
        String pass = "test1232!";
        String userType = "ACTIVE";
        boolean expResult = false;
        boolean result = false;
        if (userN != null && !"".equals(userN) && pass != null && !"".equals(pass) && email != null && !"".equals(email)) {
            result = userDao.userSignup(userN, email, pass, userType);
        }
        assertEquals(expResult, result);
    }

//    email exists
    @Test
    public void test6UserSignup() throws NoSuchAlgorithmException {
        System.out.println("userSignup");
        String userN = "userTest123";
        String email = "tanStudent@gmail.com";
        String pass = "test1232!";
        String userType = "ACTIVE";
        boolean expResult = false;
        boolean result = false;
        if (userN != null && !"".equals(userN) && pass != null && !"".equals(pass) && email != null && !"".equals(email)) {
            result = userDao.userSignup(userN, email, pass, userType);
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of userLogin method, of class UserDao.
     *
     * @throws java.security.NoSuchAlgorithmException
     */
    @Test
    public void testUserLogin() throws NoSuchAlgorithmException {
        System.out.println("userLogin");
        String usernam = "denisTeacher";
        String pass = "Monday8001!";
        boolean expResult = false;
        boolean result = userDao.userLogin(usernam, pass);
        assertEquals(expResult, result);
    }

//    password incorrect
    @Test
    public void test2UserLogin() throws Exception {
        System.out.println("userLogin");
        String usernam = "denismooney";
        String pass = "test";
        boolean expResult = false;
        boolean result = userDao.userLogin(usernam, pass);
        assertEquals(expResult, result);
    }

//    username not exist
    @Test
    public void test3UserLogin() throws Exception {
        System.out.println("userLogin");
        String usernam = "denis";
        String pass = "Monday8001!";
        boolean expResult = false;
        boolean result = userDao.userLogin(usernam, pass);
        assertEquals(expResult, result);
    }

    /**
     * Test of usernameAlreadyExists method, of class UserDao.
     */
    @Test
    public void testUsernameAlreadyExists() {
        System.out.println("usernameAlreadyExists");
        String usernam = "denisTeacher";
        boolean expResult = true;
        boolean result = userDao.usernameAlreadyExists(usernam);
        assertEquals(expResult, result);
    }

    @Test
    public void test2UsernameAlreadyExists() {
        System.out.println("usernameAlreadyExists");
        String usernam = "Bob";
        boolean expResult = false;
        boolean result = userDao.usernameAlreadyExists(usernam);
        assertEquals(expResult, result);
    }

    /**
     * Test of emailAlreadyExists method, of class UserDao.
     */
    @Test
    public void testEmailAlreadyExists() {
        System.out.println("emailAlreadyExists");
        String email = "denis.e.mooney2001@gmail.com";
        boolean expResult = true;
        boolean result = userDao.emailAlreadyExists(email);
        assertEquals(expResult, result);
    }

    @Test
    public void test2EmailAlreadyExists() {
        System.out.println("emailAlreadyExists");
        String email = "mooney@gmail.com";
        boolean expResult = false;
        boolean result = userDao.emailAlreadyExists(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserByUsername method, of class UserDao.
     */
    @Test
    public void testGetUserByUsername() {
        System.out.println("getUserByUsername");
        String username = "denisTeacher";
        String expResult = "denisTeacher";
        User result = userDao.getUserByUsername(username);
        assertEquals(expResult, result.getUsername());
    }

    @Test
    public void test2GetUserByUsername() {
        System.out.println("getUserByUsername");
        String username = "Conor123";
        String expResult = null;
        User result = userDao.getUserByUsername(username);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserById method, of class UserDao. user not exists user is not
     * exist, get error
     */
    @Test
    public void testGetUserById() {
        System.out.println("getUserById");
        int userId = 22;
        String expResult = "denisTeacher";
        User result = userDao.getUserById(userId);
        assertEquals(expResult, result.getUsername());
    }

//    userID not exist
    @Test
    public void test2GetUserById() {
        System.out.println("getUserById");
        int userId = 19;
        User expResult = null;
        User result = userDao.getUserById(userId);
        assertEquals(expResult, result);
    }

    /**
     * Test of changeUserStatus method, of class UserDao.
     */
    @Test
    public void testChangeUserStatus() {
        System.out.println("changeUserStatus");
        int userId = 22;
        String status = "ACTIVE";
        boolean expResult = true;
        boolean result = userDao.changeUserStatus(userId, status);
        assertEquals(expResult, result);
    }

//    user not exist
    @Test
    public void test2ChangeUserStatus() {
        System.out.println("changeUserStatus");
        int userId = 7;
        String status = "DISABLED";
        boolean expResult = false;
        boolean result = userDao.changeUserStatus(userId, status);
        assertEquals(expResult, result);
    }

   
    /**
     * Test of userPassword method, of class UserDao.
     */
    @Test
    public void testUserPassword() {
        System.out.println("userPassword");
        String usernam = "denisTeacher";
        String expResult = "7c61ec725980f35f6073f6f618d4775573996b8e6ebea243b6bb1197d3f62c4cec287cba3031ea72869e9ec5a886f623e30280f380366cdf569ddcf31c5d45d7";
        String result = userDao.userPassword(usernam);
        assertEquals(expResult, result);
    }

    @Test
    public void test2UserPassword() {
        System.out.println("userPassword");
        String usernam = "denis";
        String expResult = null;
        String result = userDao.userPassword(usernam);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllUsers method, of class UserDao.
     */
    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        int expResult = 9;
        ArrayList<User> result = userDao.getAllUsers();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of resetPassword method, of class UserDao.
     */
    @Test
    public void testResetPassword() throws Exception {
        System.out.println("resetPassword");
        int userId = 22;
        String password = "513b30b3fd10f42a30b65f3262f81033ca967c5c09571beef36debd11deea1d3f37cca7f713d9e6f8495bb9ef90a6c85bafcfecdd15824b0d74d1a017aa367cc";
        boolean expResult = true;
        boolean result = userDao.resetPassword(userId, password);
        assertEquals(expResult, result);
    }

    @Test
    public void test2ResetPassword() throws Exception {
        System.out.println("resetPassword");
        int userId = 2;
        String password = "513b30b3fd10f42a30b65f3262f81033ca967c5c09571beef36debd11deea1d3f37cca7f713d9e6f8495bb9ef90a6c85bafcfecdd15824b0d74d1a017aa367cc";
        boolean expResult = false;
        boolean result = userDao.resetPassword(userId, password);
        assertEquals(expResult, result);
    }

    @Test
    public void test3ResetPassword() throws Exception {
        System.out.println("resetPassword");
        int userId = 22;
        String password = "513b3b3fd10f42a30b65f3262f81033ca967c5c09571beef36debd11deea1d3f37cca7f713d9e6f8495bb9ef90a6c85bafcfecdd15824b0d74d1a017aa367cc";
        boolean expResult = false;
        boolean result = userDao.resetPassword(userId, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of resetPasswordByEmail method, of class UserDao.
     */
    @Test
    public void testResetPasswordByEmail() throws Exception {
        System.out.println("resetPasswordByEmail");
        String email = "denis.e.mooney2001@gmail.com";
        String password = "513b30b3fd10f42a30b65f3262f81033ca967c5c09571beef36debd11deea1d3f37cca7f713d9e6f8495bb9ef90a6c85bafcfecdd15824b0d74d1a017aa367cc";
        boolean expResult = true;
        boolean result = userDao.resetPasswordByEmail(email, password);
        assertEquals(expResult, result);
    }

    @Test
    public void test2ResetPasswordByEmail() throws Exception {
        System.out.println("resetPasswordByEmail");
        String email = "denis.e.mooney2001@gmail.com";
        String password = "3b30b3fd10f42a30b65f3262f81033ca967c5c09571beef36debd11deea1d3f37cca7f713d9e6f8495bb9ef90a6c85bafcfecdd15824b0d74d1a017aa367cc";
        boolean expResult = true;
        boolean result = userDao.resetPasswordByEmail(email, password);
        assertEquals(expResult, result);
    }
    /**
     * Test of updateSecretKey method, of class UserDao.
     */
    @Test
    public void testUpdateSecretKey() {
        System.out.println("updateSecretKey");
        int id = 22;
        String secretKey = "1";
        boolean expResult = true;
        boolean result = userDao.updateSecretKey(id, secretKey);
        assertEquals(expResult, result);
    }

    @Test
    public void test2UpdateSecretKey() {
        System.out.println("updateSecretKey");
        int id = 2;
        String secretKey = "1";
        boolean expResult = false;
        boolean result = userDao.updateSecretKey(id, secretKey);
        assertEquals(expResult, result);
    }

    @Test
    public void test3UpdateSecretKey() {
        System.out.println("updateSecretKey");
        int id = 22;
        String secretKey = "14";
        boolean expResult = false;
        boolean result = userDao.updateSecretKey(id, secretKey);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateResetKey method, of class UserDao.
     */
    @Test
    public void testUpdateResetKey() {
        System.out.println("updateResetKey");
        int id = 22;
        String resetKey = "1";
        boolean expResult = true;
        boolean result = userDao.updateResetKey(id, resetKey);
        assertEquals(expResult, result);
    }

    @Test
    public void test2UpdateResetKey() {
        System.out.println("updateResetKey");
        int id = 2;
        String resetKey = "1";
        boolean expResult = false;
        boolean result = userDao.updateResetKey(id, resetKey);
        assertEquals(expResult, result);
    }

    @Test
    public void test3UpdateResetKey() {
        System.out.println("updateResetKey");
        int id = 22;
        String resetKey = "4";
        boolean expResult = false;
        boolean result = userDao.updateResetKey(id, resetKey);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserByEmail method, of class UserDao.
     */
    @Test
    public void testGetUserByEmail() {
        System.out.println("getUserByEmail");
        String email = "denis.e.mooney2001@gmail.com";
        String expResult = "denisTeacher";
        User result = userDao.getUserByEmail(email);
        assertEquals(expResult, result.getUsername());
    }

    @Test
    public void test2GetUserByEmail() {
        System.out.println("getUserByEmail");
        String email = "denismooney2001@gmail.com";
        User expResult = null;
        User result = userDao.getUserByEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserKeyByEmail method, of class UserDao.
     */
    @Test
    public void testGetUserKeyByEmail() {
        System.out.println("getUserKeyByEmail");
        String email = "denis.e.mooney2001@gmail.com";
        String expResult = "1";
        String result = userDao.getUserKeyByEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkEmailAndHashCode method, of class UserDao.
     */
    @Test
    public void testCheckEmailAndHashCode() {
        System.out.println("checkEmailAndHashCode");
        String email = "denis.e.mooney2001@gmail.com";
        String hash = "1";
        boolean expResult = false;
        boolean result = userDao.checkEmailAndHashCode(email, hash);
        assertEquals(expResult, result);
    }

}
