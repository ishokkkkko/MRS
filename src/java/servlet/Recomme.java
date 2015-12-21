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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
public class Recomme extends HttpServlet {

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
        try {
             Class.forName("org.apache.derby.jdbc.ClientDriver");
           String driverURL = "jdbc:derby://localhost:1527/movierec";
           Connection con = DriverManager.getConnection(driverURL,"db","db");
           Statement stmt = con.createStatement();
          
           
            
            String wish_f = request.getParameter("wish_f");
            String wish_c = request.getParameter("wish_c");
            String wish_sa = request.getParameter("wish_sa");
            String wish_sc = request.getParameter("wish_sc");
           
           //user's evalution scores
           String fun[] = request.getParameterValues("fun");
           String cool[] = request.getParameterValues("cool");
           String sad[] = request.getParameterValues("sad");
           String scary[] = request.getParameterValues("scary");
          
           String sql = "select * from MOVIES";
           PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           rs = stmt.executeQuery(sql);
        
           List<Map<String, Double>> list = new ArrayList<Map<String,Double>>();
           List<Map<String, String>> list1 = new ArrayList<Map<String,String>>();
           List<Map<String, String>> list2 = new ArrayList<Map<String,String>>();
           List<Map<String, String>> listRec = new ArrayList<Map<String,String>>();
          
         
           //each movies of average and standdeviation and emotional numbers
           //from MOVIES DB
           //10　= a number of all movies in MOVIES DB
           //*you change 10 to 60
           double[] fs = new double[10];
           double[] cs = new double[10];
           double[] sas = new double[10];
           double[] scs = new double[10];
           
           double[] fa = new double[10];
           double[] ca = new double[10];
           double[] saa = new double[10];
           double[] sca = new double[10];
           
           int[] em1 = new int[10];
           int[] em2 = new int[10];
           int[] em3 = new int[10];
           
           //average of each emotion position　for user
           //fun.length=20
           double[] f_posi = new double[fun.length];
           double[] c_posi = new double[cool.length];
           double[] sa_posi = new double[sad.length];
           double[] sc_posi = new double[scary.length];
           
        //put MOVIES DB's STD and AVE and EM1,2,3 in fs[] etc...
          int i=0;
          while(rs.next()){
              fs[i] = rs.getInt("FUN_STDEV");
              cs[i] = rs.getInt("COOL_STDEV");
              sas[i] = rs.getInt("SAD_STDEV");
              scs[i] = rs.getInt("SCARY_STDEV");
              
              fa[i] = rs.getInt("FUN_AVERAGE");
              ca[i] = rs.getInt("COOL_AVERAGE");
              saa[i] = rs.getInt("SAD_AVERAGE");
              sca[i] = rs.getInt("SCARY_AVERAGE");
              
              em1[i] = rs.getInt("EM1");
              em2[i] = rs.getInt("EM2");
              em3[i] = rs.getInt("EM3");
           
              i++;
          }
          
     
       //calucurate user's each emotion position
           for(int j=0; j<fun.length; j++){
             
              double f = Double.parseDouble(fun[j]);
              double c = Double.parseDouble(cool[j]);
              double sa = Double.parseDouble(sad[j]);
              double sc = Double.parseDouble(scary[j]);
          
              f_posi[j]=((f-fa[j])/fs[j])*10+50;
              c_posi[j]=((c-ca[j])/cs[j])*10+50;
              sa_posi[j]=((sa-saa[j])/sas[j])*10+50;
              sc_posi[j]=((sc-sca[j])/scs[j])*10+50;  
              
           }
                   
            double sum1=0,sum2=0,sum3=0,sum4=0;
            Map<String,Double> record = new LinkedHashMap<String, Double>();       
                for (int x = 0; x< f_posi.length; x++) {
                    sum1 += f_posi[x];
                    sum2 += c_posi[x];
                    sum3 += sa_posi[x];
                    sum4 += sc_posi[x];
                }
           //average emotional position         
            double f_poAve = sum1/f_posi.length;
            double c_poAve = sum2/c_posi.length;
            double sa_poAve = sum3/sa_posi.length;
            double sc_poAve = sum4/sc_posi.length;
 
            record.put("fun",f_poAve);
            record.put("cool",c_poAve);
            record.put("sad",sa_poAve);
            record.put("scary",sc_poAve);
         
            list.add(record);
       
           
            //*you fetch location id from evalutoion.jsp where user checked it
            //*and,finally do int loc=location_id
           int loc=1;//postlate user check "1"
                      
           //*you fetch location_name and id from LOCATION DB
           //*and show location_name that match user checked loc_id at nextpage(final.jsp) 
           String sqllc = "select * from LOCATION";
           PreparedStatement pslc = con.prepareStatement(sqllc);
           ResultSet rslc = pslc.executeQuery();
           rslc = stmt.executeQuery(sqllc);
           
           //if LOCATION_ID match the id which user check location id(=int loc),
           //store e[0]~[5] E1~E6 which match the LOCATION_ID
           Map<String,String> record1 = new LinkedHashMap<String, String>();   
           int[] e = new int[6];
           while(rslc.next()){
               int locid = rslc.getInt("LOC_ID");
               if(loc==locid){
                   e[0]=rslc.getInt("E1");
                   e[1]=rslc.getInt("E2");
                   e[2]=rslc.getInt("E3");
                   e[3]=rslc.getInt("E4");
                   e[4]=rslc.getInt("E5");
                   e[5]=rslc.getInt("E6");
                  
                   record1.put("e1",String.valueOf(e[0]));
                   record1.put("e2",String.valueOf(e[1]));
                   record1.put("e3",String.valueOf(e[2]));
                   record1.put("e4",String.valueOf(e[3]));
                   record1.put("e5",String.valueOf(e[4]));
                   record1.put("e6",String.valueOf(e[5]));
                   list2.add(record1);
               }    
                   
           }
           
           String sqlem = "select * from MOVIES where MOVIEID > 5";//*autually 5→20
           PreparedStatement psem = con.prepareStatement(sqlem);
           ResultSet rsem = psem.executeQuery();
           rsem = stmt.executeQuery(sqlem);
           //*the number of 5 but acutually 40
           int[] id = new int[5];//store recommendaion movie id
           int a=0;
           while(rsem.next()){
               id[a]=rsem.getInt("MOVIEID");//now,id[0]=5,id[1]=6 so id[a]=a+5
                a++;                        //acutally, id[0]=21~id[59]=80, so id[a]=a+20 
           }
          
           int[] id1 = new int[40];//ultimate cantidate movie id
           int z=0;
           for(int m=0; m<5; m++){//*now is 5 ,but actual is 40
               for(int k=0 ; k<6; k++){
                 if(em1[m+5]==e[k] || em2[m+5]==e[k] || em3[m+5]==e[k]){//*actually m+5 → m+20
                     id1[z]=id[m];//put recomend movie id / finally z = ultimate number of candidate movies
                     
                   Map<String,String> record_loc = new LinkedHashMap<String, String>();
                       record_loc.put("id",String.valueOf(id1[z]));
                       record_loc.put("em1",String.valueOf(em1[m+5]));//*
                       record_loc.put("em2",String.valueOf(em2[m+5]));//*
                       record_loc.put("em3",String.valueOf(em3[m+5]));//*
                       list1.add(record_loc);
                       z++;
                       break;
                    }                
               }
           }
           
            Map<String,Double> mapA = new LinkedHashMap<String, Double>();
            mapA.put("fun", Double.parseDouble(wish_f));
            mapA.put("cool", Double.parseDouble(wish_c));
            mapA.put("sad", Double.parseDouble(wish_sa));
            mapA.put("scary", Double.parseDouble(wish_sc));
            
           
	    double[][] cos = new double[z][2];
	    //cos[i][0]=ultimate candidat movie id、cos[i][1]=cosin            
            double[][] c = new double[z][4];//z = ultimate number of candidate movies
            //最終的映画候補の予測評価値/predict evalution value　of ultimate movies
           for(int p=0; p<z; p++){ 
                  c[p][0]=((f_poAve-50)/10)*fs[id1[p]-1]+fa[id1[p]-1];
                  c[p][1]=((c_poAve-50)/10)*cs[id1[p]-1]+ca[id1[p]-1];
                  c[p][2]=((sa_poAve-50)/10)*sas[id1[p]-1]+saa[id1[p]-1];
                  c[p][3]=((sc_poAve-50)/10)*scs[id1[p]-1]+sca[id1[p]-1];
             Map<String,Double> mapB = new LinkedHashMap<String, Double>();
                mapB.put("fun"  , c[p][0]);
                mapB.put("cool" , c[p][1]);
                mapB.put("sad"  , c[p][2]);
                mapB.put("scary", c[p][3]);   
          
            //calulat DotPuroduct (mapA・mapB)
		Set<String> setA = mapA.keySet();
		Iterator<String> iterA = setA.iterator();
		double naiseki = 0.0;
		while(iterA.hasNext()){
		    String key = iterA.next();
			if(mapB.containsKey(key)){//mapBにAと同じkeyがあるかどうか
			    naiseki += (double)(mapA.get(key)*mapB.get(key));
			}
		}
                
                //vector A size
		iterA = setA.iterator();
		double sizeA = 0.0;
		while(iterA.hasNext()){
		    String key = iterA.next();
			sizeA += (double)(mapA.get(key) * mapA.get(key));
		}
		sizeA = Math.sqrt(sizeA);
		//vector B size
		Iterator<String> iterB = mapB.keySet().iterator(); 
		double sizeB = 0.0;
		while(iterB.hasNext()){
		    String key = iterB.next();
			sizeB += (double)(mapB.get(key) * mapB.get(key));
		}
		sizeB = Math.sqrt(sizeB);
                
		//calclate cosin vectorA and vectorB 
		double cosine = naiseki / (sizeA * sizeB);	
		
		cos[p][0]=id1[p];
		cos[p][1]=cosine;
		
	    }
	    //ここまででcos[][]にユーザと各映画のコサイン類似度を格納する
	    

           //類似度の結果をソート
	    //cos[i][1]をcos2[i]に格納
	    double cos2[] = new double[cos.length];
	    for(int x = 0; x < cos.length ; x++){
		cos2[x] = cos[x][1];
	    }
	    
	    //cos2を降順に//sort descending order cos2[](=cos[][1])
		for(int x=0; x<cos.length-1; x++) {
		    for(int y=0; y < cos.length-x-1; y++) {
			if(cos2[y] < cos2[y+1]) {
			    double asc = cos2[y];
			    cos2[y] = cos2[y+1];
			    cos2[y+1] = asc;
			}
		    }
		}
		
           double[][] rec_id= new double[z][2];
           for(int l = 0; l<cos2.length; l++){
		    for(int m = 0; m < cos.length; m++) {
			if(cos[m][1]==cos2[l]){
			    rec_id[l][0] = cos[m][0];//put recomend movie id int rec_id[][0] 
                            rec_id[l][1] = cos[m][1];//cosin
                        }
		    }
		} 
         ResultSet rs_last = psem.executeQuery();
         rs_last = stmt.executeQuery(sqlem);
             
           double[] movieid= new double[5];//*"5" is number of conndidate movie /actually 5→40
           String[] movie_name = new String[5];//*5→40
            int r=0;
            while(rs_last.next()){
                movieid[r]=rs_last.getInt("MOVIEID");
                movie_name[r]=rs_last.getString("M_NAME");
                r++;
            }
            
            for(int s=0; s<rec_id.length; s++){//*actually, rec_id.length→5 because this system show 5 recommendation movies 
                for(int t=0; t<movieid.length; t++){
                if(rec_id[s][0]==movieid[t]){
                   
                        Map<String,String> Recmap = new LinkedHashMap<String, String>();
                        Recmap.put("m_ranking", String.valueOf(s+1));
                        Recmap.put("m_name",movie_name[t]);
                        Recmap.put("cos", String.valueOf(rec_id[s][1]));
                        listRec.add(Recmap);
                       
                        break;
                        }
               
        	}
            }
            
            stmt.close();
            con.close();   
             
            
             session.setAttribute("wsf",wish_f);
             session.setAttribute("wsc",wish_c); 
             session.setAttribute("wssa",wish_sa); 
             session.setAttribute("wssc",wish_sc);
             request.setAttribute("data",list);
             request.setAttribute("data1",list1);
             request.setAttribute("data2",list2);
             request.setAttribute("dataRec",listRec);
         
            RequestDispatcher rd =
                       request.getRequestDispatcher("/final.jsp");
            rd.forward(request,response);
               
       

       /** RequestDispatcher rd =
                       request.getRequestDispatcher("/failed.jsp");
               rd.forward(request,response);
               
               
           //String cool = request.getParameter("cool"+String.valueOf(i));
           //String sad = request.getParameter("sad"+String.valueOf(i));
           //String scary = request.getParameter("scary"+String.valueOf(i));
         
           
                   //session.setAttribute("fun", fun);  
                   //session.setAttribute("cool", cool);  
                   //session.setAttribute("sad", sad);  
                   //session.setAttribute("scary", scary);  
           // List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
      /**         Map<String,String> record = new HashMap<String, String>();
         for(int i=0; i<fun.length; i++){
                
                   
                    record.put("fun",fun[i]);
                    //record.put("cool", cool);
                    //record.put("sad", sad);
                    //record.put("scary", scary);
                  
                    //list.add(record);
                   
                    
               }
               stmt.close();
               con.close();
                request.setAttribute("data", record);
               RequestDispatcher rd =
                       request.getRequestDispatcher("/failed.jsp");
               rd.forward(request,response);
               
            }
           
           
           /**
               String sql = "select * from LOCATION";
               ResultSet rs = stmt.executeQuery(sql);
               List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
               while(rs.next()){
                    Map<String,Object> record = new HashMap<String, Object>();
                    record.put("id",rs.getString(String.valueOf("LOC_ID")));
                    record.put("name", rs.getString("M_NAME"));
                  
                    list.add(record);
           }
               rs.close();
               stmt.close();
               con.close();
               
               request.setAttribute("data", list);
               //次のページに移動
               //RequestDispatcher rd = 
                       //request.getRequestDispatcher("/loginSucce	ss.jsp");
               RequestDispatcher rd =
                       request.getRequestDispatcher("/evalution.jsp");
               rd.forward(request,response);
           
            
            /* TODO output your page here. You may use following sample code. */
        }
        
        
      catch(Exception e) {
                throw new ServletException(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
   