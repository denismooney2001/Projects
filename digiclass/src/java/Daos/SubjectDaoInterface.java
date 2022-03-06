/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.Subject;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public interface SubjectDaoInterface {

    public ArrayList<Subject> getAllSubjects();

    public String getSubjectNamebyId(int subject_id);

    public ArrayList<Subject> getRandomSubjects();

}
