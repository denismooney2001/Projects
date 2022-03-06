/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.CourseMessage;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public interface CourseMessageDaoInterface {

    public boolean updateMessage(int course_id, String message);

    public boolean deleteMessage(int course_message_id);

    public boolean insertMessage(int course_id, String message);

    public ArrayList<CourseMessage> getAllMessagesByPublishDateCourseId(int courseId);
}
