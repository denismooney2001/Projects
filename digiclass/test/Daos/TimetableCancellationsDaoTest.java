/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Daos.TimetableCancellationsDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author D00223984
 */
public class TimetableCancellationsDaoTest {
    
    public static TimetableCancellationsDao timetableCancellationsDao = new TimetableCancellationsDao("digiclass_test");
    public TimetableCancellationsDaoTest() {
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
     * Test of insertCancelDate method, of class TimetableCancellationsDao.
     */
    @Test
    public void testInsertCancelDate() {
        System.out.println("insertCancelDate");
        int timetable_id = 75;
        String cancelDate = "2021-04-06";
        boolean expResult = true;
        boolean result = timetableCancellationsDao.insertCancelDate(timetable_id, cancelDate);
        assertEquals(expResult, result);
    }
    
    
//    timetable not exist
     @Test
    public void test2InsertCancelDate() {
        System.out.println("insertCancelDate");
        int timetable_id = 50;
        String cancelDate = "2021-04-08";
        boolean expResult = false;
        boolean result = timetableCancellationsDao.insertCancelDate(timetable_id, cancelDate);
        assertEquals(expResult, result);
    }
    

}
