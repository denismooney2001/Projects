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
public class Timetable {

    private int timetable_id;
    private int course_id;
    private String day;
    private int time;

    public Timetable(int timetable_id, int course_id, String day, int time) {
        this.timetable_id = timetable_id;
        this.course_id = course_id;
        this.day = day;
        this.time = time;
    }

    public int getTimetable_id() {
        return timetable_id;
    }

    public void setTimetable_id(int timetable_id) {
        this.timetable_id = timetable_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.timetable_id;
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
        final Timetable other = (Timetable) obj;
        if (this.timetable_id != other.timetable_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Timetable{" + "timetable_id=" + timetable_id + ", course_id=" + course_id + ", day=" + day + ", time=" + time + '}';
    }

}
