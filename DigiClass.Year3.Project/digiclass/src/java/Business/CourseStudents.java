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
public class CourseStudents {

    private int course_students_id;
    private int course_id;
    private int student_id;

    public CourseStudents() {
    }

    public CourseStudents(int course_students_id, int course_id, int student_id) {
        this.course_students_id = course_students_id;
        this.course_id = course_id;
        this.student_id = student_id;
    }

    public int getCourse_students_id() {
        return course_students_id;
    }

    public void setCourse_students_id(int course_students_id) {
        this.course_students_id = course_students_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.course_students_id;
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
        final CourseStudents other = (CourseStudents) obj;
        if (this.course_students_id != other.course_students_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CourseStudents{" + "course_students_id=" + course_students_id + ", course_id=" + course_id + ", student_id=" + student_id + '}';
    }

}
