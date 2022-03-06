/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Business.Enquiries;
import java.util.ArrayList;

/**
 *
 * @author denis
 */
public interface EnquiriesDaoInterface {

    public ArrayList<Enquiries> getAllUnseenEnquiries();

    public ArrayList<Enquiries> getAllSeenEnquiries();

    public boolean setInquiryToSeen(int inquiry_id);

    public boolean InsertInquiry(String full_name, String email, String message);
}
