/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shoko
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         HttpSession session = request.getSession();
        boolean found = false;
        
        try {
           // String id = request.getParameter("id");
            String user = request.getParameter("user");
           
            String wish_f = request.getParameter("wish_f");
            String wish_c = request.getParameter("wish_c");
            String wish_sa = request.getParameter("wish_sa");
            String wish_sc = request.getParameter("wish_sc");
         
           Class.forName("org.apache.derby.jdbc.ClientDriver");
           String driverURL = "jdbc:derby://localhost:1527/movierec";
           Connection con = DriverManager.getConnection(driverURL,"db","db");
           Statement stmt = con.createStatement();
           
          
          String sql = "select * from USERS where USERNAME=? and WISH_F=?"
                  + "and WISH_C=? and WISH_SA=? and WISH_SC=? ";
         
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setString(1,user);
           ps.setString(2,wish_f);
           ps.setString(3,wish_c);
           ps.setString(4,wish_sa);
           ps.setString(5,wish_sc);
           
           ResultSet rs = ps.executeQuery();
/*
             String sql = "insert into USERS(USERNAME, LOCATION)";
             sql += "VALUES(?, ?)";  // ? は順に 1 2 3 4  と番号がつく。
             PreparedStatement ps = con.prepareStatement(sql);
             ps.setString(1,user); 
             ps.setInt(2,newloca); 
             int rc = ps.executeUpdate();
             
           String sql1 = "select * from USERS";
           PreparedStatement ps1 = con.prepareStatement(sql1);
           //ps.setString(1,user);
           //ps.setInt(2,newloca);  
           ResultSet rs = ps1.executeQuery();
           
           
          /*
           if(newrc.next()) {
               found = true;
           }
           
           String nextJsp;
           
          /** if(!found) {
               session.invalidate();
               
               nextJsp  = "/failed.jsp";
               RequestDispatcher dispatcher =
                       request.getRequestDispatcher(nextJsp);
               dispatcher.forward(request,response);
           } else {**/
          
          if(found=true){
             
             session.setAttribute("user", user);
             session.setAttribute("wsf",wish_f);
             session.setAttribute("wsc",wish_c); 
             session.setAttribute("wssa",wish_sa); 
             session.setAttribute("wssc",wish_sc);
               sql = "select * from MOVIES";
               rs = stmt.executeQuery(sql);
               List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
               while(rs.next()){
                    Map<String,Object> record = new LinkedHashMap<String, Object>();
                    record.put("id",rs.getString(String.valueOf("MOVIEID")));
                    record.put("name", rs.getString("M_NAME"));
                  
                    list.add(record);
           }
              
               rs.close();
                ps.close();
               stmt.close();
               con.close();
               
               request.setAttribute("data", list);
               RequestDispatcher rd =
                    request.getRequestDispatcher("/evalution.jsp");
               rd.forward(request,response);
               
           } 
        } catch(Exception e) {
                throw new ServletException(e);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

        
        
        
 
