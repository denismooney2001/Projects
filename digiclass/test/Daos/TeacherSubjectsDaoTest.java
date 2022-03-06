/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.TeacherSubjects;
import Daos.TeacherSubjectsDao;
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
public class TeacherSubjectsDaoTest {

    private static final TeacherSubjectsDao teacherSubjectsDao = new TeacherSubjectsDao("digiclass_test");

    public TeacherSubjectsDaoTest() {
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
     * Test of insertTeacherSubjects method, of class TeacherSubjectsDao.
     */
    @Test
    public void testInsertTeacherSubjects() {
        System.out.println("1) Test #1 - Inserting the subjects the Teachers teach using the insertTeacherSubjects() method. Using valid inputs");
        int teacher_id = 35;
        int subject_id = 5;
        String subject_level = "HL";
        boolean expResult = true;
        boolean result = teacherSubjectsDao.insertTeacherSubjects(teacher_id, subject_id, subject_level);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertTeacherSubjects method, of class TeacherSubjectsDao.
     * teacher id not exist
     */
    @Test
    public void test2InsertTeacherSubjects() {
        System.out.println("2) Test #2 - Inserting the subjects the Teachers teach using the insertTeacherSubjects() method. Using invalid inputs");
        int teacher_id = 38;
        int subject_id = 5;
        String subject_level = "HL";
        boolean expResult = false;
        boolean result = teacherSubjectsDao.insertTeacherSubjects(teacher_id, subject_id, subject_level);
        assertEquals(expResult, result);
    }

//    subject id not exist
    @Test
    public void test3InsertTeacherSubjects() {
        System.out.println("2) Test #2 - Inserting the subjects the Teachers teach using the insertTeacherSubjects() method. Using invalid inputs");
        int teacher_id = 30;
        int subject_id = 44;
        String subject_level = "HL";
        boolean expResult = false;
        boolean result = teacherSubjectsDao.insertTeacherSubjects(teacher_id, subject_id, subject_level);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllTeacherSubjectsById method, of class TeacherSubjectsDao.
     */
    @Test
    public void testGetAllTeacherSubjectsById() {
        System.out.println("3) Test #1 - Retrive all the Teacher Subjects using the getAllTeacherSubjectsById() method. (Using valid inputs)");
        int teacherId = 33;
        int expResult = 2;
        int result = teacherSubjectsDao.getAllTeacherSubjectsById(teacherId).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllTeacherSubjectsById method, of class TeacherSubjectsDao.
     * teacher id not exiss
     */
    @Test
    public void test2GetAllTeacherSubjectsById() {
        System.out.println("4) Test #2 - Inserting the subjects the Teachers teach using the getAllTeacherSubjectsById() method. (Using invalid inputs)");
        int teacherId = 28;
        int expResult = 0;
        int result = teacherSubjectsDao.getAllTeacherSubjectsById(teacherId).size();
        assertEquals(expResult, result);
    }

//    return false value
    @Test
    public void test3GetAllTeacherSubjectsById() {
        System.out.println("4) Test #2 - Inserting the subjects the Teachers teach using the getAllTeacherSubjectsById() method. (Using invalid inputs)");
        int teacherId = 32;
        int expResult = 5;
        int result = teacherSubjectsDao.getAllTeacherSubjectsById(teacherId).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllPastTeacherSubjectsById method, of class
     * TeacherSubjectsDao.
     */
    @Test
    public void testGetAllPastTeacherSubjectsById() {
        System.out.println("getAllPastTeacherSubjectsById");
        int teacherId = 33;
        int expResult = 0;
        ArrayList<TeacherSubjects> result = teacherSubjectsDao.getAllPastTeacherSubjectsById(teacherId);
        assertEquals(expResult, result.size());
    }

//    teacher id not exist
    @Test
    public void test2GetAllPastTeacherSubjectsById() {
        System.out.println("getAllPastTeacherSubjectsById");
        int teacherId = 15;
        ArrayList<TeacherSubjects> expResult = new ArrayList<>();
        ArrayList<TeacherSubjects> result = teacherSubjectsDao.getAllPastTeacherSubjectsById(teacherId);
        assertEquals(expResult, result);
    }

//    return false value
    @Test
    public void test3GetAllPastTeacherSubjectsById() {
        System.out.println("getAllPastTeacherSubjectsById");
        int teacherId = 32;
        int expResult = 10;
        ArrayList<TeacherSubjects> result = teacherSubjectsDao.getAllPastTeacherSubjectsById(teacherId);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getAllPresentTeacherSubjectsById method, of class
     * TeacherSubjectsDao.
     */
    @Test
    public void testGetAllPresentTeacherSubjectsById() {
        System.out.println("getAllPresentTeacherSubjectsById");
        int teacherId = 33;
        int expResult = 2;
        ArrayList<TeacherSubjects> result = teacherSubjectsDao.getAllPresentTeacherSubjectsById(teacherId);
        assertEquals(expResult, result.size());
    }
//    teacher id not exist

    @Test
    public void test2GetAllPresentTeacherSubjectsById() {
        System.out.println("getAllPresentTeacherSubjectsById");
        int teacherId = 10;
        int expResult = 0;
        ArrayList<TeacherSubjects> result = teacherSubjectsDao.getAllPresentTeacherSubjectsById(teacherId);
        assertEquals(expResult, result.size());
    }

    //    return false value
    @Test
    public void test3GetAllPresentTeacherSubjectsById() {
        System.out.println("getAllPresentTeacherSubjectsById");
        int teacherId = 30;
        int expResult = 0;
        ArrayList<TeacherSubjects> result = teacherSubjectsDao.getAllPresentTeacherSubjectsById(teacherId);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getTeacherSubjectIdbyBothId method, of class TeacherSubjectsDao.
     */
    @Test
    public void testGetTeacherSubjectIdbyBothId() {
        System.out.println("getTeacherSubjectIdbyBothId");
        int teacherId = 35;
        int subjectId = 15;
        int expResult = 48;
        int result = teacherSubjectsDao.getTeacherSubjectIdbyBothId(teacherId, subjectId);
        assertEquals(expResult, result);
    }

//    subject id not exist
    @Test
    public void test2GetTeacherSubjectIdbyBothId() {
        System.out.println("getTeacherSubjectIdbyBothId");
        int teacherId = 30;
        int subjectId = 44;
        int expResult = 0;
        int result = teacherSubjectsDao.getTeacherSubjectIdbyBothId(teacherId, subjectId);
        assertEquals(expResult, result);
    }

//    teacher id not exist
    @Test
    public void test3GetTeacherSubjectIdbyBothId() {
        System.out.println("getTeacherSubjectIdbyBothId");
        int teacherId = 10;
        int subjectId = 44;
        int expResult = 0;
        int result = teacherSubjectsDao.getTeacherSubjectIdbyBothId(teacherId, subjectId);
        assertEquals(expResult, result);
    }

    /**
     * Test of setTeacherSubjectToPast method, of class TeacherSubjectsDao.
     */
    @Test
    public void testSetTeacherSubjectToPast() {
        System.out.println("setTeacherSubjectToPast");
        int teacher_subject_id = 44;
        boolean expResult = true;
        boolean result = false;
        ArrayList<TeacherSubjects> ts = teacherSubjectsDao.getAllPastTeacherSubjectsById(32);
        for (TeacherSubjects t : ts) {
            if (t.getTeacherSubjectId() != teacher_subject_id) {
                result = teacherSubjectsDao.setTeacherSubjectToPast(teacher_subject_id);
            }
        }
        assertEquals(expResult, result);
    }

//    already past
    @Test
    public void test2SetTeacherSubjectToPast() {
        System.out.println("setTeacherSubjectToPast");
        int teacher_subject_id = 50;
        boolean expResult = false;
        boolean result = false;
        ArrayList<TeacherSubjects> ts = teacherSubjectsDao.getAllPastTeacherSubjectsById(32);
        for (TeacherSubjects t : ts) {
            if (t.getTeacherSubjectId() != teacher_subject_id) {
                result = teacherSubjectsDao.setTeacherSubjectToPast(teacher_subject_id);
            }
        }
        assertEquals(expResult, result);
    }

//    teachersubject not exist
    @Test
    public void test3SetTeacherSubjectToPast() {
        System.out.println("setTeacherSubjectToPast");
        int teacher_subject_id = 2;
        boolean expResult = false;
        boolean result = false;
        ArrayList<TeacherSubjects> ts = teacherSubjectsDao.getAllPastTeacherSubjectsById(32);
        for (TeacherSubjects t : ts) {
            if (t.getTeacherSubjectId() != teacher_subject_id) {
                result = teacherSubjectsDao.setTeacherSubjectToPast(teacher_subject_id);
            }
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of checkIfTeacherSubjectAlreadyExists method, of class
     * TeacherSubjectsDao.
     */
    @Test
    public void testCheckIfTeacherSubjectAlreadyExists() {
        System.out.println("checkIfTeacherSubjectAlreadyExists");
        int teacher_id = 30;
        int subject_id = 37;
        String subject_level = "HL";
        boolean expResult = false;
        boolean result = teacherSubjectsDao.checkIfTeacherSubjectAlreadyExists(teacher_id, subject_id, subject_level);

        assertEquals(expResult, result);
    }

    @Test
    public void test2CheckIfTeacherSubjectAlreadyExists() {
        System.out.println("checkIfTeacherSubjectAlreadyExists");
        int teacher_id = 33;
        int subject_id = 1;
        String subject_level = "HL";
        boolean expResult = true;
        boolean result = teacherSubjectsDao.checkIfTeacherSubjectAlreadyExists(teacher_id, subject_id, subject_level);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateTeacherSubject method, of class TeacherSubjectsDao.
     */
    @Test
    public void testUpdateTeacherSubject() {
        System.out.println("updateTeacherSubject");
        int teacher_subject_id = 43;
        int new_subject_id = 25;
        String subject_level = "OL";
        boolean expResult = true;
        boolean result = false;

        ArrayList<TeacherSubjects> ts = teacherSubjectsDao.getAllPresentTeacherSubjectsById(32);
        for (TeacherSubjects t : ts) {
            if (teacher_subject_id == t.getTeacherSubjectId()) {
                result = teacherSubjectsDao.updateTeacherSubject(teacher_subject_id, new_subject_id, subject_level);
            }
        }
        assertEquals(expResult, result);
    }

//    already past
    @Test
    public void test2UpdateTeacherSubject() {
        System.out.println("updateTeacherSubject");
        int teacher_subject_id = 50;
        int new_subject_id = 44;
        String subject_level = "OL";
        boolean expResult = false;
        boolean result = false;

        ArrayList<TeacherSubjects> ts = teacherSubjectsDao.getAllPresentTeacherSubjectsById(32);
        for (TeacherSubjects t : ts) {
            if (teacher_subject_id == t.getTeacherSubjectId()) {
                result = teacherSubjectsDao.updateTeacherSubject(teacher_subject_id, new_subject_id, subject_level);
            }
        }
        assertEquals(expResult, result);
    }

//    no teachersubjectid found
    @Test
    public void test3UpdateTeacherSubject() {
        System.out.println("updateTeacherSubject");
        int teacher_subject_id = 2;
        int new_subject_id = 25;
        String subject_level = "OL";
        boolean expResult = false;
        boolean result = false;

        ArrayList<TeacherSubjects> ts = teacherSubjectsDao.getAllPresentTeacherSubjectsById(32);
        for (TeacherSubjects t : ts) {
            if (teacher_subject_id == t.getTeacherSubjectId()) {
                result = teacherSubjectsDao.updateTeacherSubject(teacher_subject_id, new_subject_id, subject_level);
            }
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of getTeacherSubjectId method, of class TeacherSubjectsDao.
     */
    @Test
    public void testGetTeacherSubjectId() {
        System.out.println("getTeacherSubjectId");
        int teacherId = 33;
        int subjectId = 1;
        int expResult = 45;
        int result = teacherSubjectsDao.getTeacherSubjectId(teacherId, subjectId);
        assertEquals(expResult, result);
    }

//    teacher id not exist
    @Test
    public void test2GetTeacherSubjectId() {
        System.out.println("getTeacherSubjectId");
        int teacherId = 24;
        int subjectId = 4;
        int expResult = 0;
        int result = teacherSubjectsDao.getTeacherSubjectId(teacherId, subjectId);
        assertEquals(expResult, result);
    }

//    subject id not exist
    @Test
    public void test3GetTeacherSubjectId() {
        System.out.println("getTeacherSubjectId");
        int teacherId = 32;
        int subjectId = 50;
        int expResult = 0;
        int result = teacherSubjectsDao.getTeacherSubjectId(teacherId, subjectId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAll method, of class TeacherSubjectsDao.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        ArrayList<TeacherSubjects> expResult = new ArrayList();
        ArrayList<TeacherSubjects> result = teacherSubjectsDao.getAll();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSubjectIdByTeacherSubjectId method, of class TeacherSubjectsDao.
     */
    @Test
    public void testGetSubjectIdByTeacherSubjectId() {
        System.out.println("getSubjectIdByTeacherSubjectId");
        int teacher_subject_id = 44;
        int expResult = 6;
        int result = teacherSubjectsDao.getSubjectIdByTeacherSubjectId(teacher_subject_id);
        assertEquals(expResult, result);
    }
    
    @Test
    public void test2GetSubjectIdByTeacherSubjectId() {
        System.out.println("getSubjectIdByTeacherSubjectId");
        int teacher_subject_id = 4;
        int expResult = 6;
        int result = teacherSubjectsDao.getSubjectIdByTeacherSubjectId(teacher_subject_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTeacherSubjectById method, of class TeacherSubjectsDao.
     */
    @Test
    public void testGetTeacherSubjectById() {
        System.out.println("getTeacherSubjectById");
        int teacherSubject_id = 44;
        boolean expResult = true;
        TeacherSubjects result = teacherSubjectsDao.getTeacherSubjectById(teacherSubject_id);
        assertEquals(expResult, result);
    }
    
     @Test
    public void test2GetTeacherSubjectById() {
        System.out.println("getTeacherSubjectById");
        int teacherSubject_id = 4;
        boolean expResult = false;
        TeacherSubjects result = teacherSubjectsDao.getTeacherSubjectById(teacherSubject_id);
        assertEquals(expResult, result);
    }

}
