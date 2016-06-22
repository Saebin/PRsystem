package edu.hkcity.cs;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.util.ArrayList;
import dao.CodeSimilarityDao;
import vo.CodeSimilarityInfo;

/**
 * The Class Output.
 */
public class Output extends HttpServlet {
	static public double Line;
	static public double Function;
	static public double regex;
	static public double cosin;
	static int count = 0;
	static int number = 0;
	static ArrayList<Double> arlist = new ArrayList<Double>();

	//ServletContext sc = this.getServletContext();
		
    /**
     * Instantiates a new output.
     */
    public Output() {
    }
    
    /**
     * Prints the.
     *
     * @param name
     *            the name
     * @param ratio
     *            the ratio
     */
    public void print(String name, double ratio) {
    	
    	
    	//CodeSimilartiyServlet codeDao = (CodeSimilartiyServlet) sc.getAttribute("codeDao");
	
		/*CodeSimilartiyInfo code = new CodeSimilartiyInfo()
										.setStu_name1("")/////////////////////////
										.setStu_name2("")
										.setTitle(name)
										.setContent(ratio);
		
		try {
			codeDao.insert(code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*double tt= ratio * 100;
    	*/
    	if(ratio*100 > (double)85){
    		count++;
    		arlist.add(ratio * 100);
    	}
    	
    	
/*    	java.sql.Connection connection;
        java.sql.Statement statement;
        String sql;
        String jdbcUrl = "jdbc:mysql://localhost:3306/pr_system";
        String databaseID = "root";
        String databasePW = "tjd134";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found : " + e);
        }
        try {
            sql = "INSERT INTO CODE(STU_NAME1,STU_NAME2,TITLE,CONTENT) VALUES ('','','" + name + "','" + tt +"')" ;
            connection = DriverManager.getConnection(jdbcUrl, databaseID, databasePW);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (SQLException e){
            System.err.println("SQL Error : " + e);
        } catch (Exception e) {
            System.err.println("Error : " + e);
        }
    	*/
    	
    	
    	
    	
    	
    	
    	
   	
//    	  if(count >= 2){
//       		name n = new name();
//       		n.simple = true;
//       		number++;
//       		System.out.println("number" + number);
//    	 }
	
    	//4개의 유사도 검출
        System.out.printf("%s : %.2f%%\n", name, ratio * 100);
        
        // 코사인유사도: 내적공간의 두 벡터간 각도의 코사인값을 이용하여 측정된 벡터간의 유사한 정도
        //
        
        
//    	System.out.printf("%f", Line * 100);
//    	System.out.printf("%f", cosin * 100);
//    	System.out.printf("%f", regex * 100);
//    	System.out.printf("%f", Function * 100);


          
        
      	}
}
