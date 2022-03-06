/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.UserPayment;

/**
 *
 * @author denis
 */
public interface UserPaymentDaoInterface {

    public boolean insertPaymentDetails(int user_id, int course_id);

    public boolean insertCoursePaymentLater(int user_id, int course_id);

    public boolean userCoursePaymentExists(int user_id, int course_id);

    public boolean userCoursePaymentInDate(int user_id, int course_id);

    public boolean updateCoursePayment(int user_id, int course_id);
}
