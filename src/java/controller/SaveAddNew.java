/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.InvoicesDAO;
import dto.InvoiceError;
import dto.InvoicesDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SaveAddNew", urlPatterns = {"/SaveAddNew"})
public class SaveAddNew extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            
            HttpSession session = request.getSession();
            
            String invId = request.getParameter("invId");
            String invDate = request.getParameter("invDate");
            String customer = request.getParameter("customer");
            
            UserDTO user = (UserDTO) session.getAttribute("USER");
            String UserId = user.getUser_ID();
            InvoicesDAO dao = new InvoicesDAO();
            InvoiceError invErr = new InvoiceError();
            boolean check = true;

            //check id validation
            if (!invId.matches("^I\\d{5}$")) {
                invErr.setInvIdError("invalid ID");
                check = false;
            } else if (dao.checkDuplicate(invId) == true) {
                invErr.setInvIdError("Id duplicated");
                check = false;
            }
            //check date format
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
            fm.setLenient(false);
            // Lay nhung ngay co that trong thuc te
            try {
                Date date = fm.parse(invDate);
            } catch (ParseException e) {
                invErr.setInvDateError("Invalid date");
                check = false;                
            }
            
            if (customer.length() < 2 || customer.length() > 30) {
                invErr.setCustomerError("Error customer");
                check = false;
            }
            
            if (check == true) {
                
                InvoicesDTO new_dto = new InvoicesDTO(invId, invDate, customer, UserId);
                
                if (dao.AddNewInvoice(new_dto) >= 0) {
                    List<InvoicesDTO> list = dao.getListInvoices(UserId, "");
                    session.setAttribute("LIST", list);
                    response.sendRedirect("search.jsp");
                }
                
            } else {
                session.setAttribute("INVOICEERROR", invErr);
                response.sendRedirect("AddInvoice.jsp");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
