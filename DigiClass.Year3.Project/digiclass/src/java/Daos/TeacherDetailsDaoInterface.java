/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.TeacherDetails;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public interface TeacherDetailsDaoInterface {

    public int insertTeacherDetails(int teacher_id, String qualifications, String about_me);

    public TeacherDetails getTeacherDetailsByUserId(int teacherId);

    public TeacherDetails getTeacherDetailsByTeacherId(int teacherID);

    public boolean updateTeacherDetails(int user_id, String about_me);

    public boolean verifyTeacher(int userId);

    public ArrayList<TeacherDetails> getAllNonVerifiedTeachers();

    public ArrayList<TeacherDetails> getAllVerifiedTeachers();

    public boolean changePremiumStatus(int teacher_id, boolean premiumStatus);

    public boolean uploadQualification(int user_id, String image);
}
