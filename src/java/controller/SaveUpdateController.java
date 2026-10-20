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
@WebServlet(name = "SaveUpdateController", urlPatterns = {"/SaveUpdateController"})
public class SaveUpdateController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();

            String invId = request.getParameter("invId");
            String invDate = request.getParameter("invDate");
            String customer = request.getParameter("customer");
            UserDTO user = (UserDTO) session.getAttribute("USER");
            String UserId = user.getUser_ID();
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
            fm.setLenient(true);

            InvoicesDAO check_dao = new InvoicesDAO();
            InvoiceError invErr = new InvoiceError();
            InvoicesDTO new_invoices;
            boolean check = true;

            if (customer.length() < 2 || customer.length() > 30) {
                invErr.setCustomerError("Invalid customer name");
                check = false;
            }
            try {
                Date date = fm.parse(invDate);
            } catch (ParseException e) {
                invErr.setInvDateError("Invalid Date");
                check = false;
            }
            
            if (check == true) {
                new_invoices = new InvoicesDTO(invId, invDate, customer, UserId);
                if (check_dao.UpdateInvoice(new_invoices) >= 0) {
                    List<InvoicesDTO> list = check_dao.getListInvoices(UserId, "");
                    session.setAttribute("LIST", list);
                    response.sendRedirect("search.jsp");
                }
                else{
                    session.setAttribute("ERRORUPDATE", "update database error");
                    response.sendRedirect("update.jsp");
                }
            }
            else{
                session.setAttribute("INVOICEUPDATEERROR", invErr);
                response.sendRedirect("update.jsp");
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
