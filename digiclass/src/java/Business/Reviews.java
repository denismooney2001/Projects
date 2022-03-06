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
public class Reviews {

    private int review_id;
    private int teacher_id;
    private int student_id;
    private int ratingOutOfTen;
    private String date;
    private String description;

    public Reviews(int review_id, int teacher_id, int student_id, int ratingOutOfTen, String date, String description) {
        this.review_id = review_id;
        this.teacher_id = teacher_id;
        this.student_id = student_id;
        this.ratingOutOfTen = ratingOutOfTen;
        this.date = date;
        this.description = description;
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getRatingOutOfTen() {
        return ratingOutOfTen;
    }

    public void setRatingOutOfTen(int ratingOutOfTen) {
        this.ratingOutOfTen = ratingOutOfTen;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.review_id;
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
        final Reviews other = (Reviews) obj;
        if (this.review_id != other.review_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reviews{" + "review_id=" + review_id + ", teacher_id=" + teacher_id + ", student_id=" + student_id + ", ratingOutOfTen=" + ratingOutOfTen + ", date=" + date + ", description=" + description + '}';
    }

}
