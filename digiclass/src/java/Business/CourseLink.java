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
public class CourseLink {

    private int course_link_id;
    private int course_id;
    private String link;

    public CourseLink(int course_link_id, int course_id, String link) {
        this.course_link_id = course_link_id;
        this.course_id = course_id;
        this.link = link;
    }

    public int getCourse_link_id() {
        return course_link_id;
    }

    public void setCourse_link_id(int course_link_id) {
        this.course_link_id = course_link_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.course_link_id;
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
        final CourseLink other = (CourseLink) obj;
        if (this.course_link_id != other.course_link_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CourseLinks{" + "course_link_id=" + course_link_id + ", course_id=" + course_id + ", link=" + link + '}';
    }

}
