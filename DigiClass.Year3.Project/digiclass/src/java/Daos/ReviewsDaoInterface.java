/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.Reviews;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public interface ReviewsDaoInterface {

    public boolean insertReview(int teacher_id, int student_id, int rating, String description);

    public ArrayList<Reviews> getAllReviewsTeacherId(int teacher_id);

    public ArrayList<Reviews> getAllTeacherReviews();
}
