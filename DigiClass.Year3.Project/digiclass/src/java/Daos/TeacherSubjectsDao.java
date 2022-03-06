/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.TeacherSubjects;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public class TeacherSubjectsDao extends Dao implements TeacherSubjectsDaoInterface {

    public TeacherSubjectsDao(String databaseName) {
        super(databaseName);
    }

    @Override
    public ArrayList<TeacherSubjects> getAll() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //Declare books List object as this object will store the books obtained from the arraylist
        ArrayList<TeacherSubjects> teacherSubjects = new ArrayList();

        try {
            con = getConnection();  //Get connection

            String query = "SELECT * FROM teacher_subjects";   //query

            ps = con.prepareStatement(query);   //Prepare Query
            rs = ps.executeQuery(); //Execute Query

            while (rs.next()) { //while loop to go round each record

                TeacherSubjects teacherSubject = new TeacherSubjects(rs.getInt("teacher_subject_Id"), rs.getInt("teacher_id"), rs.getInt("subject_id"), rs.getString("subject_level"), rs.getBoolean("past"));
                teacherSubjects.add(teacherSubject);  //adds each book to the books List
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllTeacherSubjectsById() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close(); //Close the ResultSet object
                }
                if (ps != null) {
                    ps.close(); //Close the PreparedStatement object
                }
                if (con != null) {
                    freeConnection(con);    //Frees connection
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getAllTeacherSubjectsById() method: " + e.getMessage());  //If theres an Error
            }
        }

        return teacherSubjects;   //Return the list of all books in the database
    }

    /**
     * This method inserts the teacher ID's and Subject ID's in together in the
     * teacher_subjects table
     *
     * @param teacher_id ID of the teacher
     * @param subject_id ID of the subject
     * @param subject_level Level of the Subject e.g. HL/OL
     *
     * @return boolean object, successful if true, else is false
     */
    @Override
    public boolean insertTeacherSubjects(int teacher_id, int subject_id, String subject_level) {

        int teacherId = teacher_id;
        int subjectId = subject_id;
        String subjectLevel = subject_level;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean updated = false;
        try {
            con = getConnection();
            String query = "INSERT INTO teacher_subjects VALUES(NULL,?,?,?, FALSE)";
            ps = con.prepareStatement(query);
            ps.setInt(1, teacherId);
            ps.setInt(2, subjectId);
            ps.setString(3, subjectLevel);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the insertTeacherSubjects() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the insertTeacherSubjects() method: " + e.getMessage());
            }
        }
        return updated;
    }
//*********************************

    /**
     * Returns an ArrayList of {@code TeacherSubjects} objects based on
     * information in the database. Including Subject Id's and Subject name's in
     * the subjects table are selected from the database and stored as
     * {@code Subject} objects in a {@code List}.
     *
     * @param teacherId, ID of the teacher
     *
     * @return The {@code ArrayList} of all Book entries in the subjects table.
     * This {@code ArrayList} may be empty where no books are present in the
     * database.
     */
    @Override
    public ArrayList<TeacherSubjects> getAllTeacherSubjectsById(int teacherId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int tId = teacherId;

        //Declare books List object as this object will store the books obtained from the arraylist
        ArrayList<TeacherSubjects> teacherSubjects = new ArrayList();

        try {
            con = getConnection();  //Get connection

            String query = "SELECT * FROM teacher_subjects WHERE teacher_id = ? ORDER BY subject_id";   //query

            ps = con.prepareStatement(query);   //Prepare Query
            ps.setInt(1, teacherId);
            rs = ps.executeQuery(); //Execute Query

            while (rs.next()) { //while loop to go round each record

                TeacherSubjects teacherSubject = new TeacherSubjects(rs.getInt("teacher_subject_Id"), rs.getInt("teacher_id"), rs.getInt("subject_id"), rs.getString("subject_level"), rs.getBoolean("past"));
                teacherSubjects.add(teacherSubject);  //adds each book to the books List
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllTeacherSubjectsById() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close(); //Close the ResultSet object
                }
                if (ps != null) {
                    ps.close(); //Close the PreparedStatement object
                }
                if (con != null) {
                    freeConnection(con);    //Frees connection
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getAllTeacherSubjectsById() method: " + e.getMessage());  //If theres an Error
            }
        }

        return teacherSubjects;   //Return the list of all books in the database
    }

    @Override
    public ArrayList<TeacherSubjects> getAllPastTeacherSubjectsById(int teacherId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int tId = teacherId;

        //Declare books List object as this object will store the books obtained from the arraylist
        ArrayList<TeacherSubjects> teacherSubjects = new ArrayList();

        try {
            con = getConnection();  //Get connection

            String query = "SELECT * FROM teacher_subjects WHERE teacher_id = ? AND past = TRUE ORDER BY subject_id";   //query

            ps = con.prepareStatement(query);   //Prepare Query
            ps.setInt(1, teacherId);
            rs = ps.executeQuery(); //Execute Query

            while (rs.next()) { //while loop to go round each record

                TeacherSubjects teacherSubject = new TeacherSubjects(rs.getInt("teacher_subject_Id"), rs.getInt("teacher_id"), rs.getInt("subject_id"), rs.getString("subject_level"), rs.getBoolean("past"));
                teacherSubjects.add(teacherSubject);  //adds each book to the books List
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllPastTeacherSubjectsById() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close(); //Close the ResultSet object
                }
                if (ps != null) {
                    ps.close(); //Close the PreparedStatement object
                }
                if (con != null) {
                    freeConnection(con);    //Frees connection
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getAllPastTeacherSubjectsById() method: " + e.getMessage());  //If theres an Error
            }
        }

        return teacherSubjects;   //Return the list of all books in the database
    }

    @Override
    public ArrayList<TeacherSubjects> getAllPresentTeacherSubjectsById(int teacherId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int tId = teacherId;

        //Declare books List object as this object will store the books obtained from the arraylist
        ArrayList<TeacherSubjects> teacherSubjects = new ArrayList();

        try {
            con = getConnection();  //Get connection

            String query = "SELECT * FROM teacher_subjects WHERE teacher_id = ? AND past = FALSE ORDER BY subject_id";   //query

            ps = con.prepareStatement(query);   //Prepare Query
            ps.setInt(1, teacherId);
            rs = ps.executeQuery(); //Execute Query

            while (rs.next()) { //while loop to go round each record

                TeacherSubjects teacherSubject = new TeacherSubjects(rs.getInt("teacher_subject_Id"), rs.getInt("teacher_id"), rs.getInt("subject_id"), rs.getString("subject_level"), rs.getBoolean("past"));
                teacherSubjects.add(teacherSubject);  //adds each book to the books List
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllPresentTeacherSubjectsById() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close(); //Close the ResultSet object
                }
                if (ps != null) {
                    ps.close(); //Close the PreparedStatement object
                }
                if (con != null) {
                    freeConnection(con);    //Frees connection
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getAllPresentTeacherSubjectsById() method: " + e.getMessage());  //If theres an Error
            }
        }

        return teacherSubjects;   //Return the list of all books in the database
    }

    @Override
    public int getTeacherSubjectIdbyBothId(int teacherId, int subjectId) {

        int teacher_id = teacherId;
        int subject_id = subjectId;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int teacherSubjectId = 0;

        try {
            con = getConnection();  //Get connection

            String query = "SELECT teacher_subject_id FROM teacher_subjects WHERE teacher_id = ? AND subject_id = ?";   //query

            ps = con.prepareStatement(query);   //Prepare Query
            ps.setInt(1, teacher_id);
            ps.setInt(2, subject_id);
            rs = ps.executeQuery(); //Execute Query

            if (rs.next()) { //while loop to go round each record
                teacherSubjectId = rs.getInt("teacher_subject_id");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getTeacherSubjectIdbyBothId() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close(); //Close the ResultSet object
                }
                if (ps != null) {
                    ps.close(); //Close the PreparedStatement object
                }
                if (con != null) {
                    freeConnection(con);    //Frees connection
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getTeacherSubjectIdbyBothId() method: " + e.getMessage());  //If theres an Error
            }
        }

        return teacherSubjectId;
    }

    @Override
    public boolean setTeacherSubjectToPast(int teacher_subject_id) {
        int teacherSubId = teacher_subject_id;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "UPDATE teacher_subjects SET past = TRUE WHERE teacher_subject_id = ?";
            ps = con.prepareStatement(query);

            ps.setInt(1, teacherSubId);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the setTeacherSubjectToPast() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the setTeacherSubjectToPast() method: " + e.getMessage());
            }
        }
        return updated;
    }

    @Override
    public boolean checkIfTeacherSubjectAlreadyExists(int teacher_id, int subject_id, String subject_level) {
        int teacherId = teacher_id;
        int subjectId = subject_id;
        String subLevel = subject_level;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean found = false;
        try {
            con = getConnection();
            String query = "SELECT * FROM teacher_subjects WHERE teacher_id = ? AND subject_id = ? AND subject_level = ? AND past = FALSE";
            ps = con.prepareStatement(query);
            ps.setInt(1, teacherId);
            ps.setInt(2, subjectId);
            ps.setString(3, subLevel);
            rs = ps.executeQuery();
            if (rs.next()) {
                found = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the checkIfTeacherSubjectAlreadyExists() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the checkIfTeacherSubjectAlreadyExists() method: " + e.getMessage());
            }
        }
        return found;
    }

    @Override
    public boolean updateTeacherSubject(int teacher_subject_id, int new_subject_id, String subject_level) {
        int teacherSubId = teacher_subject_id;
        int newSubId = new_subject_id;
        String sub_level = subject_level;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "UPDATE teacher_subjects SET subject_id = ?, subject_level = ? WHERE teacher_subject_id = ?";
            ps = con.prepareStatement(query);

            ps.setInt(1, newSubId);
            ps.setString(2, sub_level);
            ps.setInt(3, teacherSubId);

            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the updateTeacherSubject() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the updateTeacherSubject() method: " + e.getMessage());
            }
        }
        return updated;
    }

    @Override
    public int getTeacherSubjectId(int teacherId, int subjectId) {

        int teacher_id = teacherId;
        int subject_id = subjectId;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int teacherSubjectId = 0;

        try {
            con = getConnection();  //Get connection

            String query = "SELECT teacher_subject_id FROM teacher_subjects WHERE teacher_id = ? AND subject_id = ?";   //query

            ps = con.prepareStatement(query);   //Prepare Query
            ps.setInt(1, teacher_id);
            ps.setInt(2, subject_id);
            rs = ps.executeQuery(); //Execute Query

            if (rs.next()) { //while loop to go round each record
                teacherSubjectId = rs.getInt("teacher_subject_id");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getTeacherSubjectId() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close(); //Close the ResultSet object
                }
                if (ps != null) {
                    ps.close(); //Close the PreparedStatement object
                }
                if (con != null) {
                    freeConnection(con);    //Frees connection
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getTeacherSubjectId() method: " + e.getMessage());  //If theres an Error
            }
        }

        return teacherSubjectId;
    }

    @Override
    public int getSubjectIdByTeacherSubjectId(int teacher_subject_id) {
        int tsId = teacher_subject_id;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int subId = 0;

        try {
            con = getConnection();  //Get connection

            String query = "SELECT subject_id FROM teacher_subjects WHERE teacher_subject_id = ?";   //query

            ps = con.prepareStatement(query);   //Prepare Query
            ps.setInt(1, tsId);
            rs = ps.executeQuery(); //Execute Query

            if (rs.next()) { //while loop to go round each record
                subId = rs.getInt("subject_id");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getSubjectIdByTeacherSubjectId() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close(); //Close the ResultSet object
                }
                if (ps != null) {
                    ps.close(); //Close the PreparedStatement object
                }
                if (con != null) {
                    freeConnection(con);    //Frees connection
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getSubjectIdByTeacherSubjectId() method: " + e.getMessage());  //If theres an Error
            }
        }

        return subId;
    }

    @Override
    public TeacherSubjects getTeacherSubjectById(int teacherSubject_id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int teacherSubId = teacherSubject_id;

        //Declare books List object as this object will store the books obtained from the arraylist
        TeacherSubjects teacherSubject = null;

        try {
            con = getConnection();  //Get connection

            String query = "SELECT * FROM teacher_subjects WHERE teacher_subject_id = ?";   //query

            ps = con.prepareStatement(query);   //Prepare Query
            ps.setInt(1, teacherSubId);
            rs = ps.executeQuery(); //Execute Query

            if (rs.next()) { //while loop to go round each record

                teacherSubject = new TeacherSubjects(rs.getInt("teacher_subject_Id"), rs.getInt("teacher_id"), rs.getInt("subject_id"), rs.getString("subject_level"), rs.getBoolean("past"));

            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllPresentTeacherSubjectsById() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close(); //Close the ResultSet object
                }
                if (ps != null) {
                    ps.close(); //Close the PreparedStatement object
                }
                if (con != null) {
                    freeConnection(con);    //Frees connection
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getAllPresentTeacherSubjectsById() method: " + e.getMessage());  //If theres an Error
            }
        }

        return teacherSubject;
    }

}
