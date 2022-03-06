/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MockTest;

import Business.Subject;
import Daos.SubjectDao;
import Daos.SubjectDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.BDDMockito;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author junta
 */
public class SubjectDaoWithMock {

    public SubjectDaoWithMock() {

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @Test
    public void testGetAllSubjects() throws SQLException {
        // Create expected results
        Subject s1 = new Subject(1, "test");
        Subject s2 = new Subject(2, "test2");
        ArrayList<Subject> expectedResults = new ArrayList();
        expectedResults.add(s1);
        expectedResults.add(s2);

        // Create mock objects
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("SELECT * FROM subjects")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, true, false);
        when(rs.getInt("subject_id")).thenReturn(s1.getSubject_id(), s2.getSubject_id());
        when(rs.getString("subject_name")).thenReturn(s1.getSubject_name(), s2.getSubject_name());

        SubjectDao subjectDao = new SubjectDao(dbConn);
        ArrayList<Subject> result = subjectDao.getAllSubjects();

        assertEquals(expectedResults.size(), result.size());
    }

    @Test
    public void testGetSubjectNamebyId() throws SQLException {
        Subject s1 = new Subject(1, "test");

        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(dbConn.prepareStatement("SELECT subject_name FROM subjects WHERE subject_id = ?")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("subject_id")).thenReturn(s1.getSubject_id());
        when(rs.getString("subject_name")).thenReturn(s1.getSubject_name());

        SubjectDao subjectDao = new SubjectDao(dbConn);
        String name = subjectDao.getSubjectNamebyId(s1.getSubject_id());

        assertEquals(s1.getSubject_name(), name);
    }
}
