/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.TeacherSubjects;

import java.util.ArrayList;

/**
 *
 * @author denis
 */
public interface TeacherSubjectsDaoInterface {

    public ArrayList<TeacherSubjects> getAll();

    public boolean insertTeacherSubjects(int teacher_id, int subject_id, String subject_level);

    public ArrayList<TeacherSubjects> getAllTeacherSubjectsById(int teacherId);

    public int getTeacherSubjectIdbyBothId(int teacherId, int subjectId);

    public boolean checkIfTeacherSubjectAlreadyExists(int teacher_id, int subject_id, String subject_level);

    public boolean updateTeacherSubject(int teacher_subject_id, int new_subject_id, String subject_level);

    public int getTeacherSubjectId(int teacherId, int subjectId);

    public ArrayList<TeacherSubjects> getAllPresentTeacherSubjectsById(int teacherId);

    public ArrayList<TeacherSubjects> getAllPastTeacherSubjectsById(int teacherId);

    public boolean setTeacherSubjectToPast(int teacher_subject_id);

    public int getSubjectIdByTeacherSubjectId(int teacher_subject_id);

    public TeacherSubjects getTeacherSubjectById(int teacherSubject_id);
}
