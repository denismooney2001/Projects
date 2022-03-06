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
public class Course {

    private int course_id;
    private int teacher_subject_id;
    private int places;
    private String course_description;
    private String start_date;
    private String end_date;
    private boolean exist;

    public Course() {

    }

    public Course(int course_id, int teacher_subject_id, int places, String course_description, String start_date, String end_date, boolean exist) {
        this.course_id = course_id;
        this.teacher_subject_id = teacher_subject_id;
        this.places = places;
        this.course_description = course_description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.exist = exist;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getTeacher_subject_id() {
        return teacher_subject_id;
    }

    public void setTeacher_subject_id(int teacher_subject_id) {
        this.teacher_subject_id = teacher_subject_id;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public String getCourse_description() {
        return course_description;
    }

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.course_id;
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
        final Course other = (Course) obj;
        if (this.course_id != other.course_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Course{" + "course_id=" + course_id + ", teacher_subject_id=" + teacher_subject_id + ", places=" + places + ", course_description=" + course_description + ", start_date=" + start_date + ", end_date=" + end_date + ", exist=" + exist + '}';
    }
}
