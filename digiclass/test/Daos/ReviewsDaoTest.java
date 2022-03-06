/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.Reviews;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author conor
 */
public class ReviewsDaoTest {
    public static ReviewsDao reviewsDao = new ReviewsDao("digiclass_test");
    
    public ReviewsDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of insertReview method, of class ReviewsDao.
     */
    @Test
    public void testInsertReview() {
        System.out.println("insertReview");
        int teacher_id = 32;
        int student_id = 26;
        int rating = 4;
        String description = "Great teaching";
        boolean expResult = false;
        boolean result = reviewsDao.insertReview(teacher_id, student_id, rating, description);
        assertEquals(expResult, result);
    }
    
      @Test
    public void test2InsertReview() {
        System.out.println("insertReview");
        int teacher_id = 2;
        int student_id = 26;
        int rating = 4;
        String description = "Great teaching";
        boolean expResult = false;
        boolean result = reviewsDao.insertReview(teacher_id, student_id, rating, description);
        assertEquals(expResult, result);
    }
    
      @Test
    public void test3InsertReview() {
        System.out.println("insertReview");
        int teacher_id = 32;
        int student_id = 6;
        int rating = 4;
        String description = "Great teaching";
        boolean expResult = false;
        boolean result = reviewsDao.insertReview(teacher_id, student_id, rating, description);
        assertEquals(expResult, result);
    }
    
      @Test
    public void test4InsertReview() {
        System.out.println("insertReview");
        int teacher_id = 32;
        int student_id = 26;
        int rating = 1;
        String description = "Great teaching";
        boolean expResult = false;
        boolean result = reviewsDao.insertReview(teacher_id, student_id, rating, description);
        assertEquals(expResult, result);
    }
    
      @Test
    public void test5InsertReview() {
        System.out.println("insertReview");
        int teacher_id = 32;
        int student_id = 26;
        int rating = 4;
        String description = " teaching";
        boolean expResult = false;
        boolean result = reviewsDao.insertReview(teacher_id, student_id, rating, description);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllReviewsTeacherId method, of class ReviewsDao.
     */
    @Test
    public void testGetAllReviewsTeacherId() {
        System.out.println("getAllReviewsTeacherId");
        int teacher_id = 32;
        ArrayList<Reviews> expResult = new ArrayList();
        ArrayList<Reviews> result = reviewsDao.getAllReviewsTeacherId(teacher_id);
        assertEquals(expResult, result);
    }
    
     @Test
    public void test2GetAllReviewsTeacherId() {
        System.out.println("getAllReviewsTeacherId");
        int teacher_id = 2;
        ArrayList<Reviews> expResult = new ArrayList();
        ArrayList<Reviews> result = reviewsDao.getAllReviewsTeacherId(teacher_id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllTeacherReviews method, of class ReviewsDao.
     */
    @Test
    public void testGetAllTeacherReviews() {
        System.out.println("getAllTeacherReviews");
        ArrayList<Reviews> expResult = new ArrayList();
        ArrayList<Reviews> result = reviewsDao.getAllTeacherReviews();
        assertEquals(expResult, result);
    }
    
}
