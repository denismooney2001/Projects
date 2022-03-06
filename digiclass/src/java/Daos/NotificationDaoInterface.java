/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.Notification;
import java.util.ArrayList;

/**
 *
 * @author junta
 */
public interface NotificationDaoInterface {

    public ArrayList<Notification> getNotificationById(int userID);

    public boolean deleteNotification(int notification_id);

    public boolean updateNotification(int notification_id);

    public boolean insertNotification(int user_id, String description);
}
