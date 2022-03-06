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
public class TimetableCancellations {

    private int timetable_cancellations_id;
    private int timetable_id;
    private String date;

    public TimetableCancellations() {
    }

    public TimetableCancellations(int timetable_cancellations_id, int timetable_id, String date) {
        this.timetable_cancellations_id = timetable_cancellations_id;
        this.timetable_id = timetable_id;
        this.date = date;
    }

    public int getTimetable_cancellations_id() {
        return timetable_cancellations_id;
    }

    public void setTimetable_cancellations_id(int timetable_cancellations_id) {
        this.timetable_cancellations_id = timetable_cancellations_id;
    }

    public int getTimetable_id() {
        return timetable_id;
    }

    public void setTimetable_id(int timetable_id) {
        this.timetable_id = timetable_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.timetable_cancellations_id;
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
        final TimetableCancellations other = (TimetableCancellations) obj;
        if (this.timetable_cancellations_id != other.timetable_cancellations_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TimetableCancellations{" + "timetable_cancellations_id=" + timetable_cancellations_id + ", timetable_id=" + timetable_id + ", date=" + date + '}';
    }

}
