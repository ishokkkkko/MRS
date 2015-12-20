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
       
            <br>
    <h1>Movie Recommendation System</h1>
    <br>

         <form action="recomme" method="get" class="eval">
          <table class="menu">
             
               <h2>movie user wishes to watch </h2>
         <form action="login" method="get" class="login">
           
                        fun:
                      <select name="wish_f">
                            <option value="5">5</option>
                            <option value="4">4</option>
                            <option value="3">3</option>
                            <option value="2">2</option>
                            <option value="1">1</option>
                        </select>
                        cool:
                          <select name="wish_c">
                            <option value="5">5</option>
                            <option value="4">4</option>
                            <option value="3">3</option>
                            <option value="2">2</option>
                            <option value="1">1</option>
                        </select>
                        sad:
                          <select name="wish_sa">
                            <option value="5">5</option>
                            <option value="4">4</option>
                            <option value="3">3</option>
                            <option value="2">2</option>
                            <option value="1">1</option>
                        </select> 
                      　scary:
                          <select name="wish_sc">
                            <option value="5">5</option>
                            <option value="4">4</option>
                            <option value="3">3</option>
                            <option value="2">2</option>
                            <option value="1">1</option>
                        </select>
                           
                      </p>
                      <br>
                      <br>
                    
                      
                          
              <h2>evalute 20 movies with 4 emotion</h2> 
              <c:forEach items="${data}" var="rec" begin="0" end="4" step="1"> 
                  <tr>
                     
                  <h3>    <p>${rec.id}:${rec.name}<br></h3>
                       
                      fun
                      <select name="fun">
                            <option value="5">5</option>
                            <option value="4">4</option>
                            <option value="3">3</option>
                            <option value="2">2</option>
                            <option value="1">1</option>
                        </select>
                       
                        cool
                          <select name="cool">
                            <option value="5">5</option>
                            <option value="4">4</option>
                            <option value="3">3</option>
                            <option value="2">2</option>
                            <option value="1">1</option>
                        </select>
                        sad
                          <select name="sad">
                            <option value="5">5</option>
                            <option value="4">4</option>
                            <option value="3">3</option>
                            <option value="2">2</option>
                            <option value="1">1</option>
                        </select> 
                      　scary
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

             <br>
          <input type="submit" name="next" value="next" />
      </form>
    </body>
</html>
