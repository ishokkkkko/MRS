<%-- 
    Document   : failed
    Created on : 2015/12/07, 14:28:30
    Author     : shoko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>final</title>
    </head>
    <body>
        <div align="center">
            <br>
    <h1>Movie Recommendation System</h1>
      <br>
    
      <h4>user's average emotional position</h4>
      
     <form action="recomme" method="get" class="eval">
    <table class="menu">
        
        <c:forEach items="${data}" var="rec"> 
         <tr>
                      <p>fun:&nbsp;${rec.fun}
                         cool:&nbsp;${rec.cool}
                         sad:&nbsp;${rec.sad}
                         scary:&nbsp;${rec.scary}<br>
                      </p>
    </tr>
     </c:forEach>
        
    
      <c:forEach items="${data2}" var="rec2"> 
         <tr>
         <h4>emotional numbers that relate to users select location</h4>    
                      <p>${rec2.e1}
                         ${rec2.e2}
                         ${rec2.e3}
                         ${rec2.e4}
                         ${rec2.e5}
                         ${rec2.e6}<br>
                         
                      </p>
     </tr>
     </c:forEach>
     
     
     
     <c:forEach items="${data1}" var="rec1"> 
         <tr>
                 *******Movie ID******
                      <p>${rec1.id}<br>
                        
                          emotional genre numbers<br>
                          ${rec1.em1}
                          ${rec1.em2}
                          ${rec1.em3}<br>
                         
                      </p>
     </tr>
     </c:forEach>
     <br>
     <h4>It's that user wishes to watch movie<br> </h4> 
     fun：${wsf}&nbsp;cool：${wsc}&nbsp;sad：${wssa}&nbsp;scary：${wssc}<br>
    
     
     <br>
     <br>
     <h3>★Your Recommendation Movie★</h3>
      <c:forEach items="${dataRec}" var="rec"> 
         <tr>
         <p><h4>No.${rec.m_ranking}
                title:${rec.m_name}<br></h4>
               cosin similality:${rec.cos}<br>
                     
                         
                         
                      </p>
     </tr>
     </c:forEach>
     
    </table>
     </form>

     <br>
     
   
    <a href="fin.jsp">next</a>
</div>
    </body>
</html>