<%-- 
    Document   : AddInvoice
    Created on : Oct 2, 2026, 4:38:38 PM
    Author     : Huynh Ngoc Thang
--%>

<%@page import="dto.InvoiceError"%>
<%@page import="dto.InvoicesDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Invoice</title>
        <style>
            .box {
                width: 500px;
                margin-bottom: 15px;
            }
            label {
                display: inline-block;
                width: 120px;
            }
            .a, .btn-cancel {
                text-decoration: none;
                color: #333333;
                padding: 3px 8px;
                border: 1px solid #767676;
                border-radius: 3px;
                background-color: #efefef;
                font-size: 13px;
            }
            span {
                color: #ff3333;
            }
        </style>
    </head>
    <body>
        <%
            InvoicesDTO invoiceUpdate = (InvoicesDTO) session.getAttribute("INVOICENEEDUPDATE");
        %>

        <%
            InvoiceError error = (InvoiceError) session.getAttribute("INVOICEUPDATEERROR");
            String errorCustomer = "";
            String errorDate = "";
            if (error != null) {
                errorCustomer = error.getCustomerError();
                errorDate = error.getInvDateError();
            } else {
                errorCustomer = "";
                errorDate = "";
            }
        %>
        <%
            String loi = (String) session.getAttribute("ERRORUPDATE");
            if (loi == null) {
                loi = "";
            }
        %>
        <p><%=loi%></p>
        <form action="Maincontroller" method="post">
            <h2 class="infor">Update Invoice information</h2>

            <div class="box">
                <label>Invoice ID</label>
                <input type="text" name="invId" required="true" value="<%= invoiceUpdate.getInvID()%>" readonly />
            </div>

            <div class="box">
                <label>Invoice Date</label>
                <input type="text" name="invDate" required="true" value="<%=invoiceUpdate.getInvDate()%>" />
                <span style="color: red"><%=errorDate%></span>
            </div>

            <div class="box">
                <label>Customer</label>
                <input type="text" name="customer" required="true" value="<%=invoiceUpdate.getCustomer()%>" />
                <span style="color: red"><%=errorCustomer%> </span>
            </div>

            <div class="box">
                <input type="hidden" name="checksave" value="saveupdate">
                <input type="submit" name="action" value="Save" class="marginleft" />
                <a href="Maincontroller?action=Cancel" class="btn-cancel">Cancel</a>
            </div>
        </form>
    </body>
</html>