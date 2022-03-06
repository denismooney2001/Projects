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
public class TeacherDetails {

    private int teacher_id;
    private int user_id;
    private String qualifications;
    private String about_me;
    private boolean verify;
    private boolean premium;

    public TeacherDetails() {
    }

    public TeacherDetails(int teacher_id, int user_id, String qualifications, String about_me, boolean verify, boolean premium) {
        this.teacher_id = teacher_id;
        this.user_id = user_id;
        this.qualifications = qualifications;
        this.about_me = about_me;
        this.verify = verify;
        this.premium = premium;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.teacher_id;
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
        final TeacherDetails other = (TeacherDetails) obj;
        if (this.teacher_id != other.teacher_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TeacherDetails{" + "teacher_id=" + teacher_id + ", user_id=" + user_id + ", qualifications=" + qualifications + ", about_me=" + about_me + ", verify=" + verify + ", premium=" + premium + '}';
    }

}
