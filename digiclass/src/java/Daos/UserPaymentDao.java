/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.UserPayment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author denis
 */
public class UserPaymentDao extends Dao implements UserPaymentDaoInterface {

    public UserPaymentDao(Connection conn) {
        super(conn);
    }

    public UserPaymentDao(String databaseName) {
        super(databaseName);
    }

    @Override
    public boolean insertPaymentDetails(int user_id, int course_id) {

        int userId = user_id;
        int courseId = course_id;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "INSERT INTO user_payment VALUES(NULL,?,?,CURRENT_DATE(),TIMESTAMPADD(MONTH,1,CURRENT_DATE()))";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the insertPaymentDetails() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the insertPaymentDetails() method: " + e.getMessage());
            }
        }
        return updated;
    }

    @Override
    public boolean insertCoursePaymentLater(int user_id, int course_id) {

        int userId = user_id;
        int courseId = course_id;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "INSERT INTO user_payment VALUES(NULL,?,?,NULL,NULL)";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the insertPaymentDetails() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the insertPaymentDetails() method: " + e.getMessage());
            }
        }
        return updated;
    }

    @Override
    public boolean userCoursePaymentExists(int user_id, int course_id) {
        int userId = user_id;
        int courseId = course_id;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean alreadyExists = false;
        try {
            con = getConnection();

            String query = "SELECT * FROM user_payment WHERE user_id = ? AND course_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            rs = ps.executeQuery();

            if (rs.next()) {
                alreadyExists = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the userPaymentExists() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the userPaymentExists() method: " + e.getMessage());
            }
        }
        return alreadyExists;
    }

    @Override
    public boolean userCoursePaymentInDate(int user_id, int course_id) {
        int userId = user_id;
        int courseId = course_id;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean inDate = false;
        try {
            con = getConnection();

            String query = "SELECT * FROM user_payment WHERE user_id = ? AND course_id = ? AND end_date > CURRENT_DATE()";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            rs = ps.executeQuery();

            if (rs.next()) {
                inDate = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the userPaymentInDate() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the userPaymentInDate() method: " + e.getMessage());
            }
        }
        return inDate;
    }

    @Override
    public boolean updateCoursePayment(int user_id, int course_id) {
        int uId = user_id;
        int cId = course_id;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean updated = false;
        try {
            con = getConnection();
            String query = "UPDATE user_payment SET start_date = CURRENT_DATE(), end_date = TIMESTAMPADD(MONTH,1,CURRENT_DATE()) WHERE user_id = ? AND course_id = ?";
            ps = con.prepareStatement(query);

            ps.setInt(1, uId);
            ps.setInt(2, cId);
            if (ps.executeUpdate() > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the updatePayment() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the updatePayment() method: " + e.getMessage());
            }
        }
        return updated;
    }

}
