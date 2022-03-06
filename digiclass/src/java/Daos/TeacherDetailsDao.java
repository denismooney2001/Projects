/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.TeacherDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public class TeacherDetailsDao extends Dao implements TeacherDetailsDaoInterface {

    public TeacherDetailsDao(String databaseName) {
        super(databaseName);
    }

    /**
     * This method inserts the new TeacherDetails information into the database
     *
     * @param userId User ID
     * @param qualifications The qualifications of the teacher
     * @param about_me About the lecture
     *
     * @return int
     */
    @Override
    public int insertTeacherDetails(int userId, String qualifications, String about_me) {

        int user_Id = userId;
        String qualification = qualifications;
        String aboutMe = about_me;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int teacherId = -1;

        try {
            con = getConnection();
            String query = "INSERT INTO teacher_details VALUES(NULL,?,?,?,FALSE,FALSE)";

            ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, user_Id);
            ps.setString(2, qualification);
            ps.setString(3, aboutMe);

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                teacherId = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the insertTeacherDetails() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the insertTeacherDetails() method: " + e.getMessage());
            }
        }
        return teacherId;
    }

    /**
     * This method gets the Teacher's Details information from the database
     *
     * @param userId User ID
     *
     * @return TeacherDetails object, containing information relating to the
     * teacher
     */
    @Override
    public TeacherDetails getTeacherDetailsByUserId(int userId) {
        int user_id = userId;
        TeacherDetails td = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            String query = "SELECT * FROM teacher_details WHERE user_id =?";
            ps = con.prepareStatement(query);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                td = new TeacherDetails(rs.getInt("teacher_id"), rs.getInt("user_id"), rs.getString("qualifications"), rs.getString("about_me"), rs.getBoolean("verify"), rs.getBoolean("premium"));
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getTeacherDetailsByUserId() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getTeacherDetailsByUserId() method: " + e.getMessage());

            }
        }
        return td;
    }

    @Override
    public TeacherDetails getTeacherDetailsByTeacherId(int teacherID) {
        int teacher_id = teacherID;
        TeacherDetails td = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            String query = "SELECT * FROM teacher_details WHERE teacher_id =?";
            ps = con.prepareStatement(query);
            ps.setInt(1, teacher_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                td = new TeacherDetails(rs.getInt("teacher_id"), rs.getInt("user_id"), rs.getString("qualifications"), rs.getString("about_me"), rs.getBoolean("verify"), rs.getBoolean("premium"));
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getTeacherDetailsByUserId() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getTeacherDetailsByUserId() method: " + e.getMessage());

            }
        }
        return td;
    }

    @Override
    public boolean updateTeacherDetails(int user_id, String about_me) {
        int userId = user_id;
        String about = about_me;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "UPDATE teacher_details SET about_me = ? WHERE user_id = ?";
            ps = con.prepareStatement(query);

            ps.setString(1, about);
            ps.setInt(2, userId);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the updateTeacherDetails() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the updateTeacherDetails() method: " + e.getMessage());
            }
        }
        return updated;
    }

    @Override
    public ArrayList<TeacherDetails> getAllNonVerifiedTeachers() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<TeacherDetails> teacherDetailsArray = new ArrayList<>();
        boolean alreadyExists = false;
        try {
            con = getConnection();

            String query = "SELECT * FROM teacher_details WHERE verify = 'FALSE'";
            ps = con.prepareStatement(query);   //Prepare Query
            rs = ps.executeQuery(); //Execute Query

            while (rs.next()) { //while loop to go round each record
                TeacherDetails teacherDetails = new TeacherDetails(rs.getInt("teacher_id"), rs.getInt("user_id"), rs.getString("qualifications"), rs.getString("about_me"), rs.getBoolean("verify"), rs.getBoolean("premium"));
                teacherDetailsArray.add(teacherDetails);  //adds each subject to the subjects List
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllNonVerifiedTeachers() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllNonVerifiedTeachers() method: " + e.getMessage());
            }
        }
        return teacherDetailsArray;
    }

    @Override
    public boolean verifyTeacher(int userId) {
        int user_id = userId;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "UPDATE teacher_details SET verify = TRUE WHERE user_id = ?";
            ps = con.prepareStatement(query);

            ps.setInt(1, user_id);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the verifyTeacher() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the verifyTeacher() method: " + e.getMessage());
            }
        }
        return updated;
    }

    @Override
    public ArrayList<TeacherDetails> getAllVerifiedTeachers() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<TeacherDetails> teacherDetailsArray = new ArrayList<>();
        boolean alreadyExists = false;
        try {
            con = getConnection();

            String query = "SELECT * FROM teacher_details WHERE verify = TRUE";
            ps = con.prepareStatement(query);   //Prepare Query
            rs = ps.executeQuery(); //Execute Query

            while (rs.next()) { //while loop to go round each record
                TeacherDetails teacherDetails = new TeacherDetails(rs.getInt("teacher_id"), rs.getInt("user_id"), rs.getString("qualifications"), rs.getString("about_me"), rs.getBoolean("verify"), rs.getBoolean("premium"));
                teacherDetailsArray.add(teacherDetails);  //adds each subject to the subjects List
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllVerifiedTeachers() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllVerifiedTeachers() method: " + e.getMessage());
            }
        }
        return teacherDetailsArray;
    }

    @Override
    public boolean changePremiumStatus(int teacher_id, boolean premiumStatus) {
        int teacherId = teacher_id;
        boolean premium = premiumStatus;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "UPDATE teacher_details SET premium = ? WHERE teacher_id = ?";
            ps = con.prepareStatement(query);
            ps.setBoolean(1, premium);
            ps.setInt(2, teacherId);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the changePremiumStatus() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the changePremiumStatus() method: " + e.getMessage());
            }
        }
        return updated;
    }

    @Override
    public boolean uploadQualification(int teacher_id, String image) {
        int teacherId = teacher_id;
        String img = image;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "UPDATE teacher_details SET qualifications = ? WHERE teacher_id = ?";
            ps = con.prepareStatement(query);

            ps.setString(1, img);
            ps.setInt(2, teacherId);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the uploadQualification() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the uploadQualification() method: " + e.getMessage());
            }
        }
        return updated;
    }
}
