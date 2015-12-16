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
        <title>failed</title>
    </head>
    <body>
        <div align="center">
    <h1>recommendation system</h1>
    
    
      <h2>${user}さんの平均偏差値</h2>
      
     <form action="recomme" method="get" class="eval">
    <table class="menu">
        
     <c:forEach items="${data}" var="rec"> 
         <tr>
                      <p>${rec.fun}<br>
                         ${rec.cool}<br>
                         ${rec.sad}<br>
                         ${rec.scary}<br>
                         
                         
                      </p>
     </tr>
     </c:forEach>
     
      <c:forEach items="${data2}" var="rec2"> 
         <tr>
                        場所と一致する感情ジャンル番号
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
                 ************映画ID************
                      <p>${rec1.id}<br>
                          <p>${rec1.id1}<br>
                          感情ジャンル番号
                           <p>${rec1.em1}<br>
                                <p>${rec1.em2}<br>
                                     <p>${rec1.em3}<br>
                         
                      </p>
     </tr>
     </c:forEach>
     <h3>  
     ${user}さんが今みたい映画は<br>
     楽しい：${wsf}かっこいい：${wsc}悲しい：${wssa}怖い：${wssc}<br>
     </h3> 
     
    </table>
     </form>

    
    <p>
    ユーザ名またはパスワードが間違っているため、<br />
    ログインできませんでした。
    </p>
    <a href="login.jsp">もう一度ログインを試す。</a>
</div>
    </body>
</html>