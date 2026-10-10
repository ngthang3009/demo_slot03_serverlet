<%-- 
    Document   : login
    Created on : Sep 25, 2026, 4:41:47 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>



        <% String error = (String) session.getAttribute("ERRORLOGIN");
            if (error == null) {
                error = " ";
            }
        %>

        <p style="font-size: 10px; color: red">  <%=error%>  <p>

        <form action="Maincontroller" method="post"> 
            <div style="height: 30px;">  
                <label>Username</label>
                <input type="text" name="username" />
            </div>
            <div style="height: 30px;">  
                <label>Password</label>
                <input type="password" name="password" />
            </div>
            <div style="height: 30px;">  
                <input type="submit" value="Login" name="action"/>
                <input type="reset" value="Cancel" name="action"/>
            </div>
        </form>
    </body>
</html>
