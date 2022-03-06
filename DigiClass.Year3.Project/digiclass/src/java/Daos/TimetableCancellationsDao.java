/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author denis
 */
public class TimetableCancellationsDao extends Dao implements TimetableCancellationsDaoInterface {

    public TimetableCancellationsDao(String databaseName) {
        super(databaseName);
    }

    @Override
    public boolean insertCancelDate(int timetable_id, String cancelDate) {
        int timetableID = timetable_id;
        String date = cancelDate;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean updated = false;
        try {
            con = getConnection();
            String query = "INSERT INTO timetable_cancellations VALUES(NULL,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, timetableID);
            ps.setString(2, date);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the insertCancelDate() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the insertCancelDate() method: " + e.getMessage());
            }
        }
        return updated;
    }
}
