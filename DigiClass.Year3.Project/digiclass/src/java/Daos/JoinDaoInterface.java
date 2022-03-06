/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.*;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public interface JoinDaoInterface {

    public ArrayList<String> searchByTeacher(int teacher_id);

    public ArrayList<Timetable> getOngoingTeacherTimeTables(int teacher_id);

    public ArrayList<Timetable> getTimeTablesbyCourse(int courseID);

    public ArrayList<Timetable> getStudentTimeTablesForOngoingCourses(int user_id);

    public int getSubjectIdByCourseId(int course_id);

    public ArrayList<Integer> getUserIdsOfTeacherByStudent(int user_id);

    public ArrayList<Course> getAllCourses();

    public ArrayList<Course> getAllOngoingCoursesByTeacherId(int teacher_id);

    public ArrayList<Course> getNonExistingCourses(int teacher_id);

    public ArrayList<Course> getAllCoursesByTeacherId(int teacher_id);

    public ArrayList<Course> getStudentsOngoingCourses(int user_id);

    public ArrayList<Course> getOverdueCourses(int student_id);

    public int getCourseAlmostStart(int student_id);

    public ArrayList<TimetableCancellations> getAllUpcomingCancellationsByCourse(int course_id);

    public boolean checkPresentCancellationsOnAllStudentsCourse(int student_id);

    public boolean checkIfTimetableCancellationExistsByCourse(int course_id);

;

}
