/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author denis
 */
public class TeacherSubjects {

    private int teacherSubjectId;
    private int teacher_id;
    private int subject_id;
    private String subject_level;
    private boolean past;

    public TeacherSubjects() {
    }

    public TeacherSubjects(int teacherSubjectId, int teacher_id, int subject_id, String subject_level, boolean past) {
        this.teacherSubjectId = teacherSubjectId;
        this.teacher_id = teacher_id;
        this.subject_id = subject_id;
        this.subject_level = subject_level;
        this.past = past;
    }

    public boolean isPast() {
        return past;
    }

    public void setPast(boolean past) {
        this.past = past;
    }

    public int getTeacherSubjectId() {
        return teacherSubjectId;
    }

    public void setTeacherSubjectId(int teacherSubjectId) {
        this.teacherSubjectId = teacherSubjectId;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_level() {
        return subject_level;
    }

    public void setSubject_level(String subject_level) {
        this.subject_level = subject_level;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.teacherSubjectId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TeacherSubjects other = (TeacherSubjects) obj;
        if (this.teacherSubjectId != other.teacherSubjectId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TeacherSubjects{" + "teacherSubjectId=" + teacherSubjectId + ", teacher_id=" + teacher_id + ", subject_id=" + subject_id + ", subject_level=" + subject_level + ", past=" + past + '}';
    }

}
