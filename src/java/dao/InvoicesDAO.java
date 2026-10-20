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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
            p.setString(2, "%" + name + "%");
            rs = p.executeQuery();
            
            String outputDate  = "";
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            // tu database len
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
            //format lai giao dien
            
            while (rs.next()) {
                invID = rs.getString("invID");
                invDate = rs.getString("invDate");
                customer = rs.getString("customer");
                userID = rs.getString("userID");
                try{
                    Date date = inputFormat.parse(invDate);
                    outputDate = outputFormat.format(date);
                }catch(ParseException e){
                    System.out.println("Format error");
                }
                
                invoices = new InvoicesDTO(invID, outputDate, customer, userID);

                list.add(invoices);
            }
            if (conn != null) {
                conn.close();
            }
            if (p != null) {
                p.close();
            }
            if (rs != null){
                rs.close();
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
            p = conn.prepareStatement("INSERT INTO tblInvoices (invID, invDate, customer, userID) VALUES (?,?,?,?)");
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
            if (conn != null) {
                conn.close();
            }
            if (p != null) {
                p.close();
            }

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
            p = conn.prepareStatement("update tblInvoices set invDate=?,customer=? where invID=?  ");
            p.setString(1, invoiceUpdate.getInvDate());
            p.setString(2, invoiceUpdate.getCustomer());
            p.setString(3, invoiceUpdate.getInvID());
            result = p.executeUpdate();
            if (conn != null) {
                conn.close();
            }
            if (p != null) {
                p.close();
            }

        } catch (SQLException e) {
            System.out.println("Error connection");
        } catch (ClassNotFoundException e) {
            System.out.println("Error connection");
        }

        return result;
    }
 
    
    public boolean checkDuplicate(String invID) {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        InvoicesDTO invoices = null;
        boolean result =false;

        try {
            conn = util.getConnection();
            p = conn.prepareStatement("select * from tblInvoices where invID = ?");
            p.setString(1, invID);
            rs = p.executeQuery();

           if(rs.next()) {
               result = true;
            }
            if (conn != null) {
                conn.close();
            }
            if (p != null) {
                p.close();
            }
        } catch (SQLException e) {
            System.out.println("Error connection");
        } catch (ClassNotFoundException e) {
            System.out.println("Error connection");
        }
        return result;
    }

    
    
    
    
    
}
