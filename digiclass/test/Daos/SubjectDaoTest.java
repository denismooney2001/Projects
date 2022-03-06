/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.Subject;
import Daos.SubjectDao;
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
public class SubjectDaoTest {

    private static final SubjectDao subjectDao = new SubjectDao("digiclass_test");

    public SubjectDaoTest() {
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
     * Test of getAllSubjects method, of class SubjectDao.
     */
    @Test
    public void testGetAllSubjects() {
        System.out.println("1) Test #1 - To get All Subjects using the getAllSubjects() method.");
        ArrayList<Subject> result = subjectDao.getAllSubjects();
        int expResult = 43;
        assertEquals(expResult, result.size());
    }

    @Test
    public void testGetSubjectNamebyId() {
        System.out.println("2) Test #2 - To get All Subjects using the getAllSubjects() method. - Using a Valid Subject ID");
        int subject_id = 1;
        String expResult = "Accounting";
        String result = subjectDao.getSubjectNamebyId(subject_id);
        assertEquals(expResult, result);
    }
    
    @Test
    public void test2GetSubjectNamebyId() {
        System.out.println("3) Test #3 - To get All Subjects using the getAllSubjects() method. - Using an Invalid Subject ID");
        int subject_id = -1;
        String expResult = "";
        String result = subjectDao.getSubjectNamebyId(subject_id);
        assertEquals(expResult, result);
    }


}
