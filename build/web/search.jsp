<%-- 
    Document   : search
    Created on : Sep 22, 2026, 4:39:22 PM
    Author     : ADMIN
--%>

<%@page import="dto.InvoicesDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% String fullname = (String) session.getAttribute("FULLNAME");
            if (fullname == null) {
                fullname = "";
            }
        %>
        <h1>Welcome, <%=fullname%>
        </h1>
        <div>
            <%
                String customername = (String) session.getAttribute("CUSTOMERNAME");
                if (customername == null) {
                    customername = "";
                }
            %>
            <form action="Maincontroller" method="post">
                <input type ="text" name="txtsearch" value ="<%=customername%>" />
                <input type ="submit" value="Search" name="action" />
            </form>
        </div>

        <p>
            <a href="Maincontroller?action=AddNew">AddNew</a>
        </p>      
    
       
        

        <table border = "1">
            <tr>
                <th>InvID</th>
                <th>InvDate</th>
                <th>Customer</th>
                <th>UserID</th>
                <th colspan="2">Choice</th>
            </tr>
            <%
                List<InvoicesDTO> list = (List<InvoicesDTO>) session.getAttribute("LIST");
                if (list != null) {
                    for (InvoicesDTO i : list) {

            %>
            <tr>
                <td>
                    <%= i.getInvID()%>
                </td>
                <td>
                    <%= i.getInvDate()%>
                </td>
                <td>
                    <%= i.getCustomer()%>
                </td>
                <td>
                    <%= i.getUserID()%>
                </td>
                <td>
                    <a href="Maincontroller?action=delete">Delete</a>
                </td>
                <td>
                    <a href="Maincontroller?action=update&InvID= <%= i.getInvID()%>">Update</a>
                </td>
                
            </tr>
            <%
                    }
                }
            %>   
        </table>
        <% String loi = (String) session.getAttribute("ERRORSEARCH");
            if (list == null) {
        %>
        <p> <%= loi%></p>
        <% }%>




    </body>
</html>
