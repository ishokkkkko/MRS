<%-- 
    Document   : evalution
    Created on : 2015/12/05, 12:46:22
    Author     : shoko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recommendation System</title>
    </head>
    <body>
        <div align="center">
        <h1>○○○</h1>
        
        
        <h2>${user}20の映画を4つの感情で評価して下さい</h2>

         <form action="recomme" method="get" class="eval">
          <table class="menu">
             
              <c:forEach items="${data}" var="rec" begin="0" end="4" step="1"> 
                  <tr>
                     
                      <p>${rec.id}${rec.name}<br>
                       
                      楽しい
                      <select name="fun">
                            <option value="5">5</option>
                            <option value="4">4</option>
                            <option value="3">3</option>
                            <option value="2">2</option>
                            <option value="1">1</option>
                        </select>
                       
                         かっこいい
                          <select name="cool">
                            <option value="5">5</option>
                            <option value="4">4</option>
                            <option value="3">3</option>
                            <option value="2">2</option>
                            <option value="1">1</option>
                        </select>
                        悲しい
                          <select name="sad">
                            <option value="5">5</option>
                            <option value="4">4</option>
                            <option value="3">3</option>
                            <option value="2">2</option>
                            <option value="1">1</option>
                        </select> 
                      　怖い
                          <select name="scary">
                            <option value="5">5</option>
                            <option value="4">4</option>
                            <option value="3">3</option>
                            <option value="2">2</option>
                            <option value="1">1</option>
                        </select>
                           
                      </p>
                  
                
                  </tr>
              </c:forEach>
                     
          </table>

     
          <input type="submit" name="next" value="次へ進む" />
      </form>
    </body>
</html>
