<%-- 
    Document   : AddInvoice
    Created on : Oct 2, 2026, 4:38:38 PM
    Author     : Huynh Ngoc Thang
--%>

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
        <form action="Maincontroller" method="post">
            <h2 class="infor">Add New Invoice information</h2>

            <div class="box">
                <label>Invoice ID</label>
                <input type="text" name="invId" required="true" />
            </div>

            <div class="box">
                <label>Invoice Date</label>
                <input type="date" name="invDate" required="true" />
            </div>

            <div class="box">
                <label>Customer</label>
                <input type="text" name="customer" required="true" />
            </div>

            <div class="box">
                <input type="submit" name="action" value="SaveUpdate" class="marginleft" />
                <a href="Maincontroller?action=Cancel" class="btn-cancel">Cancel</a>
            </div>
        </form>
    </body>
</html>