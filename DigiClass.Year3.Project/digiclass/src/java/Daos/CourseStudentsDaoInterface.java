/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.CourseStudents;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public interface CourseStudentsDaoInterface {

    public boolean insertCourseStudent(int course_id, int student_id);

    public int getCourseCount(int courseId);

    public boolean deleteStudentCourse(int course_id, int user_id);

    public ArrayList<CourseStudents> selectStudentsByCourseId(int courseID);

    public boolean studentEnrolledInCourse(int course_id, int student_id);

    public ArrayList<Integer> getStudentIdsInCourse(int course_id);
}
