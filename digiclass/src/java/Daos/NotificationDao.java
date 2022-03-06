/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.Notification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author junta
 */
public class NotificationDao extends Dao implements NotificationDaoInterface {

    public NotificationDao(String databaseName) {
        super(databaseName);
    }

    /**
     * This method gets a list of a notification by user id from the
     * notification table in the database
     *
     * @param userID The user's ID
     *
     * @return object, successful if true, else is false
     */
    @Override
    public ArrayList<Notification> getNotificationById(int userID) {
        int user_id = userID;
        Notification noti = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Notification> notificationList = new ArrayList<>();
        try {
            con = getConnection();
            String query = "SELECT * FROM `notification` WHERE userId  = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                noti = new Notification(rs.getInt("id"), rs.getInt("userId"), rs.getString("description"), rs.getBoolean("seen"));
                notificationList.add(noti);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getNotificationById() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getNotificationById() method: " + e.getMessage());
            }
        }
        return notificationList;
    }

    @Override
    public boolean deleteNotification(int notification_id) {
        int notiId = notification_id;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;

        try {
            con = getConnection();
            String query = "DELETE FROM `notification` WHERE id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, notiId);

            if (ps.executeUpdate() > 0) {
                updated = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the deleteNotification() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the deleteNotification() method: " + e.getMessage());
            }
        }
        return updated;
    }

    @Override
    public boolean updateNotification(int notification_id) {
        int notiId = notification_id;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;

        try {
            con = getConnection();
            String query = "UPDATE `notification` SET seen = 1 WHERE id = ? ";
            ps = con.prepareStatement(query);
            ps.setInt(1, notiId);

            if (ps.executeUpdate() > 0) {
                updated = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the updateNotification() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the updateNotification() method: " + e.getMessage());
            }
        }
        return updated;
    }

    @Override
    public boolean insertNotification(int user_id, String description) {
        int userId = user_id;
        String des = description;

        boolean updated = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            String query = "INSERT INTO notification VALUES(NULL,?,?, FALSE)";

            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setString(2, des);

            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the InsertInquiry() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the InsertInquiry() method: " + e.getMessage());
            }
        }
        return updated;
    }
}
