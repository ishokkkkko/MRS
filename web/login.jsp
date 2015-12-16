<%-- 
    Document   : login
    Created on : 2015/12/02, 14:31:05
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
    
  <form action="login" method="post" class="login">
    <table>
    <tr>
      <td>User Name</td>
      <td><input type="text" name="user" size="16" value="" /></td>
    </tr>
    
    <h3>今見たい映画を4つの感情で表して下さい</h3>
         <form action="login" method="get" class="login">
             <table class="menu">
                  楽しい
                      <select name="wish_f">
                            <option value="5">5</option>
                            <option value="4">4</option>
                            <option value="3">3</option>
                            <option value="2">2</option>
                            <option value="1">1</option>
                        </select>
                       
                         かっこいい
                          <select name="wish_c">
                            <option value="5">5</option>
                            <option value="4">4</option>
                            <option value="3">3</option>
                            <option value="2">2</option>
                            <option value="1">1</option>
                        </select>
                        悲しい
                          <select name="wish_sa">
                            <option value="5">5</option>
                            <option value="4">4</option>
                            <option value="3">3</option>
                            <option value="2">2</option>
                            <option value="1">1</option>
                        </select> 
                      　怖い
                          <select name="wish_sc">
                            <option value="5">5</option>
                            <option value="4">4</option>
                            <option value="3">3</option>
                            <option value="2">2</option>
                            <option value="1">1</option>
                        </select>
                           
                      </p>
                  
             </table>
        
    
    </table>

    <input type="submit" value="Login" />
  </form>
     </form>
    
</html>
