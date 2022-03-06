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
public class Subject {

    private int subject_id;
    private String subject_name;

    public Subject() {
    }

    public Subject(int subject_id, String subject_name) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.subject_id;
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
        final Subject other = (Subject) obj;
        if (this.subject_id != other.subject_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Subject{" + "subject_id=" + subject_id + ", subject_name=" + subject_name + '}';
    }

}
