/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

/**
 *
 * @author denis
 */
public interface CourseWaitingListDaoInterface {

    public boolean insertStudentInWaitingList(int course_id, int student_id);

    public boolean enrollStudentInCourse(int course_id, int student_id);

    public boolean studentInCourseWaitingList(int course_id, int student_id);

}
