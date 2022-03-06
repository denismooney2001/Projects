/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.User;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public interface UserDaoInterface {

    public boolean userSignup(String userN, String email, String pass, String userType) throws NoSuchAlgorithmException;

    public boolean userLogin(String usernam, String pass) throws NoSuchAlgorithmException;

    public String userSalt(String usernam);

    public String userPassword(String usernam);

    public boolean emailAlreadyExists(String email);

    public boolean usernameAlreadyExists(String usernam);

    public User getUserById(int userId);

    public User getUserByUsername(String username);

    public ArrayList<User> getAllUsers();

    public boolean changeUserStatus(int userId, String status);

    public boolean resetPassword(int userId, String password) throws NoSuchAlgorithmException;

    public boolean resetPasswordByEmail(String email, String password) throws NoSuchAlgorithmException;

    public boolean updateSecretKey(int id, String secretKey);

    public User getUserByEmail(String email);

    public String getUserKeyByEmail(String email);

    public boolean checkEmailAndHashCode(String email, String hash);

    public int getNoOfStudents();
    
    public int getNoOfTeachers();


    public boolean updateResetKey(int id, String resetKey);


}
