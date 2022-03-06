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
public class CourseWaitingList {

    private int course_waiting_list;
    private int course_id;
    private int student_id;

    public CourseWaitingList(int course_waiting_list, int course_id, int student_id) {
        this.course_waiting_list = course_waiting_list;
        this.course_id = course_id;
        this.student_id = student_id;
    }

    public int getCourse_waiting_list() {
        return course_waiting_list;
    }

    public void setCourse_waiting_list(int course_waiting_list) {
        this.course_waiting_list = course_waiting_list;
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
        int hash = 5;
        hash = 23 * hash + this.course_waiting_list;
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
        final CourseWaitingList other = (CourseWaitingList) obj;
        if (this.course_waiting_list != other.course_waiting_list) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CourseWaitingList{" + "course_waiting_list=" + course_waiting_list + ", course_id=" + course_id + ", student_id=" + student_id + '}';
    }

}
