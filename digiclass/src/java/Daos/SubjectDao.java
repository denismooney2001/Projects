/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.Subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public class SubjectDao extends Dao implements SubjectDaoInterface {

    public SubjectDao(Connection conn) {
        super(conn);
    }

    public SubjectDao(String databaseName) {
        super(databaseName);
    }

    /**
     * Returns an ArrayList of {@code Subject} objects based on information in
     * the database. Including Subject Id's and Subject name's in the subjects
     * table are selected from the database and stored as {@code Subject}
     * objects in a {@code List}.
     *
     * @return The {@code ArrayList} of all Book entries in the subjects table.
     * This {@code ArrayList} may be empty where no books are present in the
     * database.
     */
    @Override
    public ArrayList<Subject> getAllSubjects() {
        //Database Variables
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //Declare Subject ArrayList object as this object will store the Subject obtained from the database
        ArrayList<Subject> subjects = new ArrayList();

        try {
            con = getConnection();  //Get connection

            String query = "SELECT * FROM subjects";   //query
            ps = con.prepareStatement(query);   //Prepare Query
            rs = ps.executeQuery(); //Execute Query

            while (rs.next()) { //while loop to go round each record
                Subject subject = new Subject(rs.getInt("subject_id"), rs.getString("subject_name"));
                subjects.add(subject);  //adds each subject to the subjects List
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllSubjects() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllSubjects() method: " + e.getMessage());  //If theres an Error
            }
        }

        return subjects;   //Return the list of all books in the database
    }

    @Override
    public String getSubjectNamebyId(int subject_id) {
        int subId = subject_id;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String subjectName = "";
        try {
            con = getConnection();
            String query = "SELECT subject_name FROM subjects WHERE subject_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, subId);
            rs = ps.executeQuery();
            if (rs.next()) {
                subjectName = rs.getString("subject_name");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getSubjectNamebyId() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getSubjectNamebyId() method: " + e.getMessage());
            }
        }
        return subjectName;
    }

    @Override
    public ArrayList<Subject> getRandomSubjects() {
        //Database Variables
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //Declare Subject ArrayList object as this object will store the Subject obtained from the database
        ArrayList<Subject> subjects = new ArrayList();

        try {
            con = getConnection();  //Get connection

            String query = "SELECT * FROM subjects ORDER BY RAND() LIMIT 9";   //query
            ps = con.prepareStatement(query);   //Prepare Query
            rs = ps.executeQuery(); //Execute Query

            while (rs.next()) { //while loop to go round each record
                Subject subject = new Subject(rs.getInt("subject_id"), rs.getString("subject_name"));
                subjects.add(subject);  //adds each subject to the subjects List
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllSubjects() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllSubjects() method: " + e.getMessage());  //If theres an Error
            }
        }

        return subjects;   //Return the list of all books in the database
    }
}
