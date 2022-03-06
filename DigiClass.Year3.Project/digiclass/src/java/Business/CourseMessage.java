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
public class CourseMessage {

    private int course_message_id;
    private int course_id;
    private String message;
    private String date;

    public CourseMessage() {
    }

    public CourseMessage(int course_message_id, int course_id, String message, String date) {
        this.course_message_id = course_message_id;
        this.course_id = course_id;
        this.message = message;
        this.date = date;
    }

    public int getCourse_message_id() {
        return course_message_id;
    }

    public void setCourse_message_id(int course_message_id) {
        this.course_message_id = course_message_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        hash = 43 * hash + this.course_message_id;
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
        final CourseMessage other = (CourseMessage) obj;
        if (this.course_message_id != other.course_message_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CourseMessage{" + "course_message_id=" + course_message_id + ", course_id=" + course_id + ", message=" + message + ", date=" + date + '}';
    }

}
