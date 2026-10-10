/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Utils.util;
import dto.InvoicesDTO;
import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class InvoicesDAO {

    public List<InvoicesDTO> getListInvoices(String userId, String name) {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        InvoicesDTO invoices = null;
        String invID;
        String invDate;
        String customer;
        String userID;
        List<InvoicesDTO> list = new ArrayList<>();

        try {
            conn = util.getConnection();
            p = conn.prepareStatement("select * from tblInvoices where userID = ? and customer like ?");
            p.setString(1, userId);
            p.setString(2, "%"+name+"%");
            rs = p.executeQuery();
            
            while (rs.next()) {
                invID = rs.getString("invID");
                invDate = rs.getString("invDate");
                customer = rs.getString("customer");
                userID = rs.getString("userID");
                invoices = new InvoicesDTO(invID, invDate, customer, userID);
                
                list.add(invoices);
            }
        } catch (SQLException e) {
            System.out.println("Error connection");
        } catch (ClassNotFoundException e) {
            System.out.println("Error connection");
        }
        return list;
    }
    
    
    
    
     public int AddNewInvoice(InvoicesDTO newInv) {
        Connection conn = null;
        PreparedStatement p = null;
        
        int result = -1;

        try {
            conn = util.getConnection();
            p = conn.prepareStatement("Insert tblInvoices values(?,?,?,?)");
            p.setString(1, newInv.getInvID());
            p.setString(2, newInv.getInvDate());
            p.setString(3, newInv.getCustomer());
            p.setString(4, newInv.getUserID());
            result = p.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println("Error connection");
        } catch (ClassNotFoundException e) {
            System.out.println("Error connection");
        }
        return result;
    }
     
     
       
     public int DeleteInvoice(String invID) {
        Connection conn = null;
        PreparedStatement p = null;
        
        int result = -1;

        try {
            conn = util.getConnection();
            p = conn.prepareStatement("Delete from tblInvoices where invID=? ");
            p.setString(1, invID);
            result = p.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println("Error connection");
        } catch (ClassNotFoundException e) {
            System.out.println("Error connection");
        }
        return result;
    }
     
     
     
       public int UpdateInvoice(InvoicesDTO invoiceUpdate) {
        Connection conn = null;
        PreparedStatement p = null;
        
        int result = -1;

        try {
            conn = util.getConnection();
            p = conn.prepareStatement("update tblInvoices set invID=?, invDate=?,customer=? where invID=?  ");
       // hello
            p.setString(1, InvDate);
            p.setString(2, Customer);
            p.setString(3, InvID);
            result = p.executeUpdate();
 
        } catch (SQLException e) {
          
       
    
    }
    
    
    
    
}
