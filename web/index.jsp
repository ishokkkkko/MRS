<%-- 
    Document   : index
    Created on : 2015/12/02, 11:31:58
    Author     : shoko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
    </head>
    <body>
        <h1>Login</h1>
    
  <form action="../recomme/servlet.inServlet" method="post" class="login">
    <table>
    <tr>
      <td>User Name</td>
      <td><input type="text" name="user" size="16" value="" /></td>
    </tr>
    <tr>
      <td>Password</td>
      <td><input type="password" name="password" size="16" value="" /></td>
    </tr>
    </table>

    <input type="submit" value="Login" />
  </form>
</html>
