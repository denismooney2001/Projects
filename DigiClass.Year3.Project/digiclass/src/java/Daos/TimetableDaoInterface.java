/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.Timetable;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public interface TimetableDaoInterface {

    public boolean insertTimetable(int course_id, String day, int time);

    public boolean updateTimetableByTimetableid(int timetable_id, String day, int time);

    public boolean courseDayTimeExsits(int course_id, String day, int time);

    public ArrayList<Timetable> getTimeTablesbyCourse(int courseID);

    public int getCourseIdByTid(int timeId);

}
