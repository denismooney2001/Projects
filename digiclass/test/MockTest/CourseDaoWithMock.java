/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MockTest;

import Business.Course;
import Business.TeacherSubjects;
import Daos.CourseDao;
import Daos.CourseDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author junta
 */
public class CourseDaoWithMock {

    public CourseDaoWithMock() {

    }

    @Before
    public void setUp() {

    }

    @Test
    public void testInsertCourse() throws SQLException {
        Course c1 = new Course(1, 1, 20, "desc", "2020-02-01", "2020-09-01", true);
        Course c2 = new Course(2, 2, 20, "desc", "2020-02-01", "2020-09-01", true);
        Course c3 = new Course(3, 3, 20, "desc", "2020-02-01", "2020-09-01", true);

        ArrayList<Course> expectedResults = new ArrayList();
        expectedResults.add(c1);
        expectedResults.add(c2);
        expectedResults.add(c3);
        // Create mock objects
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("INSERT INTO course VALUES(NULL,?,?,?,?,?,TRUE)")).thenReturn(ps);
        verify(ps).setInt(1, c1.getTeacher_subject_id());
        verify(ps).setInt(2, c1.getPlaces());
        verify(ps).setString(3, c1.getCourse_description());
        verify(ps).setString(4, c1.getStart_date());
        verify(ps).setString(5, c1.getEnd_date());
        when(ps.executeUpdate());

        CourseDao courseDao = new CourseDao(dbConn);
        int result = courseDao.insertCourse(1, 1, "desc", "2020-02-01", "2020-09-01");

        assertEquals(result, 1);
    }

    @Test
    public void testGetAllExisitingCourses() throws SQLException {
        // Create expected results
        Course c1 = new Course(1, 1, 20, "desc", "2020-02-01", "2020-09-01", true);
        Course c2 = new Course(2, 2, 20, "desc", "2020-02-01", "2020-09-01", true);
        Course c3 = new Course(3, 3, 20, "desc", "2020-02-01", "2020-09-01", true);

        ArrayList<Course> expectedResults = new ArrayList();
        expectedResults.add(c1);
        expectedResults.add(c2);
        expectedResults.add(c3);
        // Create mock objects
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("SELECT * FROM course WHERE exist = TRUE")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, true, true, false);
        when(rs.getInt("course_id")).thenReturn(c1.getCourse_id(), c2.getCourse_id(), c3.getCourse_id());
        when(rs.getInt("teacher_subject_id")).thenReturn(c1.getTeacher_subject_id(), c2.getTeacher_subject_id(), c3.getTeacher_subject_id());
        when(rs.getInt("places")).thenReturn(c1.getPlaces(), c2.getPlaces(), c3.getPlaces());
        when(rs.getString("course_description")).thenReturn(c1.getCourse_description(), c2.getCourse_description(), c3.getCourse_description());
        when(rs.getString("start_date")).thenReturn(c1.getStart_date(), c2.getStart_date(), c3.getStart_date());
        when(rs.getString("end_date")).thenReturn(c1.getEnd_date(), c2.getEnd_date(), c3.getEnd_date());
        when(rs.getBoolean("exist")).thenReturn(c1.isExist(), c2.isExist(), c3.isExist());

        CourseDao courseDao = new CourseDao(dbConn);
        ArrayList<Course> result = courseDao.getAllExistingCourses();

        assertEquals(result.size(), expectedResults.size());
    }
    


}
