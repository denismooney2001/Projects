/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.Course;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public interface CourseDaoInterface {

    public int insertCourse(int teacher_subject_id, int places, String description, String start_date, String end_date);

    public boolean disableCourse(int course_id);

    public ArrayList<Course> getAllExistingCourses();

    public ArrayList<Course> getAllExistingCoursesByTeacherId(int teacher_id);

    public boolean checkIfTeacherSubjectIdIsOngoing(int teacher_subject_id);

    public boolean updateOngoingCourse(int teacher_subject_id, int course_places, String description, String startDate, String endDate);

    public ArrayList<Course> getCoursesNotYetStarted(int teacher_id);

    public ArrayList<Course> getStartedOngoingCourses(int teacher_id);

    public Course getCourseByID(int course_id);
    
    public int getNoOfPastAndPresentCourse();
}
