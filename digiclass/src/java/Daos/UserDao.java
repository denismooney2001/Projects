/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.User;
import Business.HashPass;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author denis
 */
public class UserDao extends Dao implements UserDaoInterface {

    public UserDao(Connection conn) {
        super(conn);
    }

    public UserDao(String databaseName) {
        super(databaseName);
    }

    /**
     * This method inserts the new UserDetails information into the database
     *
     * @param userN The Username of the User
     * @param email The email of the User
     * @param pass The password of the User
     * @param userType The name of the Users Profile picture (Used to create a
     * path)
     * @param userKey
     *
     * @return boolean object, successful if true, else is false
     * @throws java.security.NoSuchAlgorithmException
     */
    @Override
    public boolean userSignup(String userN, String email, String pass, String userType) throws NoSuchAlgorithmException {

        HashPass hp = new HashPass();
        byte[] salt = hp.getSalt();
        String saltResult = Arrays.toString(salt);
        String hashPass = hp.SecurePass512(pass, salt);
        String user_type = userType;
        String username = userN;
        String em = email;
        String password = hashPass;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "INSERT INTO users VALUES(NULL,?,?,?,?,?,'ACTIVE',NULL,NULL)";
            ps = con.prepareStatement(query);
            ps.setString(1, user_type);
            ps.setString(2, username);
            ps.setString(3, em);
            ps.setString(4, password);
            ps.setString(5, saltResult);

            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the userSignup() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the userSignup() method: " + e.getMessage());
            }
        }
        return updated;
    }

    /**
     * This method checks the User's credentials to see if the User is eligible
     * to login.
     *
     * @param usernam Username of the User
     * @param pass Password of the User
     *
     * @return a boolean object 'login' whether the login was successful (true)
     * or unsuccessful (false).
     *
     * @throws java.security.NoSuchAlgorithmException
     */
    @Override
    public boolean userLogin(String usernam, String pass) throws NoSuchAlgorithmException {
        User u = new User();
        HashPass hp = new HashPass();
        boolean login = false;
        try {
            String saltReturn = userSalt(usernam);
            byte[] salt = hp.StringtoByte(saltReturn);
            String password = pass;
            String comfirmPassword = hp.SecurePass512(password, salt);

            if (userPassword(usernam).equals(comfirmPassword)) {
                login = true;
            }
        } catch (NullPointerException | NoSuchAlgorithmException e) {
            login = false;
        }

        return login;
    }

    /**
     * This method searches the database if a username is already taken. Returns
     * true if it already exists, false if it does not.
     *
     * @param usernam Username then used to check if it already exists.
     *
     * @return a boolean object.
     */
    @Override
    public boolean usernameAlreadyExists(String usernam) {
        String username = usernam;
        int id = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean alreadyExists = false;
        try {
            con = getConnection();

            String query = "SELECT * FROM users WHERE username = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                alreadyExists = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the userAlreadyExists() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the userAlreadyExists() method: " + e.getMessage());
            }
        }
        return alreadyExists;
    }

    /**
     * This method searches the database if a username is already taken. Returns
     * true if it already exists, false if it does not.
     *
     *
     * @param email
     * @return a boolean object.
     */
    @Override
    public boolean emailAlreadyExists(String email) {
        String em = email;
        int id = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean alreadyExists = false;
        try {
            con = getConnection();

            String query = "SELECT * FROM users WHERE email = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, em);
            rs = ps.executeQuery();

            if (rs.next()) {
                alreadyExists = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the emailAlreadyExists() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the emailAlreadyExists() method: " + e.getMessage());
            }
        }
        return alreadyExists;
    }

    @Override
    public User getUserByUsername(String username) {
        String userName = username;
        User u = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            String query = "SELECT * FROM users WHERE username =?";
            ps = con.prepareStatement(query);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            if (rs.next()) {
                u = new User(rs.getInt("user_id"), rs.getString("user_type"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("user_status"), rs.getString("secret_key"));
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getUserById() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getUserById() method: " + e.getMessage());
            }
        }
        return u;
    }

    /**
     * This method retrieves the salt data from the users table using the
     * username of the User.
     *
     * @param usernam Username of User
     *
     * @return a String object.
     */
    @Override
    public String userSalt(String usernam) {
        String salt = null;
        String username = usernam;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            String query = "SELECT salt FROM users WHERE username =?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);

            rs = ps.executeQuery();
            if (rs.next()) {
                salt = rs.getString("salt");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the userSalt() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the userSalt() method: " + e.getMessage());
            }
        }
        return salt;
    }

    /**
     * This method returns the hashed password of the User that exists in the
     * users table in the database.
     *
     * @param usernam Username of User
     *
     * @return a String object (i.e the 'password').
     */
    @Override
    public String userPassword(String usernam) {
        String password = null;
        String username = usernam;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            String query = "SELECT password FROM users WHERE username =?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);

            rs = ps.executeQuery();
            if (rs.next()) {
                password = rs.getString("password");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the userPassword() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the userPassword() method: " + e.getMessage());
            }
        }
        return password;
    }

    /**
     * This method retrieves the User information from the database using an ID
     * as each User has a unique ID
     *
     * @param userId ID of the User
     *
     * @return a String object.
     */
    @Override
    public User getUserById(int userId) {

        int user_id = userId;
        User u = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            String query = "SELECT * FROM users WHERE user_id =?";
            ps = con.prepareStatement(query);
            ps.setInt(1, user_id);

            rs = ps.executeQuery();
            if (rs.next()) {
                u = new User(rs.getInt("user_id"), rs.getString("user_type"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("user_status"), rs.getString("secret_key"));
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getUserById() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getUserById() method: " + e.getMessage());
            }
        }
        return u;
    }

    @Override
    public ArrayList<User> getAllUsers() {

        User u = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<User> userList = new ArrayList<>();
        try {
            con = getConnection();
            String query = "SELECT * FROM users";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {
                u = new User(rs.getInt("user_id"), rs.getString("user_type"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("user_status"), rs.getString("secret_key"));
                userList.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllUsers() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getAllUsers() method: " + e.getMessage());
            }
        }
        return userList;
    }

    @Override
    public boolean changeUserStatus(int userId, String status) {
        int user_id = userId;
        String stat = status;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "UPDATE users SET user_status = ? WHERE user_id = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, stat);
            ps.setInt(2, user_id);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the changeUserStatus() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the changeUserStatus() method: " + e.getMessage());
            }
        }
        return updated;
    }

    @Override
    public boolean resetPassword(int userId, String password) throws NoSuchAlgorithmException {
        int user_id = userId;
        HashPass hp = new HashPass();
        byte[] salt = hp.getSalt();
        String saltResult = Arrays.toString(salt);
        String hashPass = hp.SecurePass512(password, salt);

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "UPDATE users SET password = ? , salt = ? WHERE user_id = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, hashPass);
            ps.setString(2, saltResult);
            ps.setInt(3, user_id);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the resetPassword() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the resetPassword() method: " + e.getMessage());
            }
        }
        return updated;
    }

    @Override
    public boolean resetPasswordByEmail(String email, String password) throws NoSuchAlgorithmException {
        String em = email;
        HashPass hp = new HashPass();
        byte[] salt = hp.getSalt();
        String saltResult = Arrays.toString(salt);
        String hashPass = hp.SecurePass512(password, salt);

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "UPDATE users SET password = ? , salt = ? WHERE email = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, hashPass);
            ps.setString(2, saltResult);
            ps.setString(3, em);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the resetPasswordByEmail() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the resetPasswordByEmail() method: " + e.getMessage());
            }
        }
        return updated;
    }

    @Override
    public boolean updateSecretKey(int id, String secretKey) {
        int uId = id;
        String key = secretKey;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "UPDATE users SET secret_key = ? WHERE user_id = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, key);
            ps.setInt(2, uId);

            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the updateSecretKey() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the updateSecretKey() method: " + e.getMessage());
            }
        }
        return updated;
    }

    @Override
    public boolean updateResetKey(int id, String resetKey) {
        int uId = id;
        String key = resetKey;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "UPDATE users SET reset_key = ? WHERE user_id = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, key);
            ps.setInt(2, uId);

            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the updateSecretKey() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the updateSecretKey() method: " + e.getMessage());
            }
        }
        return updated;
    }

    @Override
    public User getUserByEmail(String email) {
        String em = email;
        User u = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            String query = "SELECT * FROM users WHERE email =?";
            ps = con.prepareStatement(query);
            ps.setString(1, em);

            rs = ps.executeQuery();
            if (rs.next()) {
                u = new User(rs.getInt("user_id"), rs.getString("user_type"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("user_status"), rs.getString("secret_key"));
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getUserByEmail() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getUserByEmail() method: " + e.getMessage());
            }
        }
        return u;
    }

    @Override
    public String getUserKeyByEmail(String email) {
        String salt = null;
        String em = email;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            String query = "SELECT reset_key FROM users WHERE email =?";
            ps = con.prepareStatement(query);
            ps.setString(1, em);

            rs = ps.executeQuery();
            if (rs.next()) {
                salt = rs.getString("reset_key");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getUserSaltByEmail() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getUserSaltByEmail() method: " + e.getMessage());
            }
        }
        return salt;
    }

    @Override
    public boolean checkEmailAndHashCode(String email, String hash) {
        String em = email;
        String reset_key = hash;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean alreadyExists = false;
        try {
            con = getConnection();

            String query = "SELECT * FROM users WHERE email = ? and reset_key = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, em);
            ps.setString(2, reset_key);
            rs = ps.executeQuery();

            if (rs.next()) {
                alreadyExists = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the checkEmailAndHashCode() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the checkEmailAndHashCode() method: " + e.getMessage());
            }
        }
        return alreadyExists;
    }
    
    @Override 
    public int getNoOfStudents(){
        int count = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String query = "SELECT COUNT(*) FROM users WHERE user_type = 'STUDENT'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("COUNT(*)");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getCourseCount() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getCourseCount() method: " + e.getMessage());
            }
        }
        return count;
    }
    
    @Override 
    public int getNoOfTeachers(){
        int count = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String query = "SELECT COUNT(*) FROM users WHERE user_type = 'TEACHER'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("COUNT(*)");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getCourseCount() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getCourseCount() method: " + e.getMessage());
            }
        }
        return count;
    }
}
