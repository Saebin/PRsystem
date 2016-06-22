package servlets;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class test {

   public static void main(String[] args) {
      // TODO Auto-generated method stub

	   java.sql.Connection connection;
       java.sql.Statement statement;
       ResultSet resultset;
       ResultSet total_resul;
       
       String sql;
       String total;
       String jdbcUrl = "jdbc:mysql://localhost:3306/pr_system";
       String databaseID = "root";
       String databasePW = "tjd134";
       int[] test = null;
       int i = 0;
       
       try {
           Class.forName("com.mysql.jdbc.Driver");
       } catch (ClassNotFoundException e) {
           System.out.println("Class not found : " + e);
       }
       try {
           sql = "SELECT * FROM task_div";
           total = "SELECT count(task_number) FROM task_div";
           connection = DriverManager.getConnection(jdbcUrl, databaseID, databasePW);
           statement = connection.createStatement();
           resultset = statement.executeQuery(sql);
           total_resul = statement.executeQuery(total);
           int tt = 0;
           while (total_resul.next()) {
        	   int count = total_resul.getRow();
        	   tt = total_resul.getInt(count);
        	   test = new int[tt];
        	   System.out.println(tt);
        	          	   	           	             
           }
           statement.close();
           connection.close();
           
           sql = "SELECT * FROM task_div where task_number=1";
           connection = DriverManager.getConnection(jdbcUrl, databaseID, databasePW);
           statement = connection.createStatement();
           resultset = statement.executeQuery(sql);
           while (resultset.next()) {
        	   test[i++] = resultset.getInt("std_id");
           }
           for(int a=0; a<test.length;a++)
        	   System.out.print(test[a]+":");
        	              
           statement.close();
           connection.close();
       } catch (SQLException e){
           System.err.println("SQL Error : " + e);
       } catch (Exception e) {
           System.err.println("Error : " + e);
       }
       
       System.out.println("");
	  
	   HashMap<Integer, int[]> map = new HashMap<Integer, int[]>();
	   int group_num = 5;
	   int tt = test.length/group_num;
	   int[] group_array = new int[group_num];
	   int k = 0;
	   shuffleArray(test);
	   for(int a=0; a<test.length;a++)
		   System.out.print(test[a]+":");
	   System.out.println("");
	   System.out.println("============================");
	   for(int p=0; p<tt; p++){
		   for(int j=0; j<group_num; j++){
			   group_array[j] = test[k];
			   k++;
		   }
		   map.put(p, group_array);
		   
		   for(int r=0; r<map.get(p).length; r++) {
			   System.out.print(map.get(p)[r]+"-");
			   
			   try {
				   	System.out.println(p);
		            sql = "update task_div set task_group="+p+" where task_number=1 and std_id="+map.get(p)[r]+"";
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
			   
		   	}
		   	System.out.println("");
		   	System.out.println("============================");
		   
	   	}
	   
   	}

   	// Implementing Fisher–Yates shuffle
   	static void shuffleArray(int[] ar) {
   		// If running on Java 6 or older, use `new Random()` on RHS here
   		Random rnd = ThreadLocalRandom.current();
   		for (int i = ar.length - 1; i > 0; i--) {
   			int index = rnd.nextInt(i + 1);
   			// Simple swap
   			int a = ar[index];
   			ar[index] = ar[i];
   			ar[i] = a;
   		}
   	}
   	
   	public static int[] Group(int[] ak){
   		//System.out.println("너ㅐ너"+ak[1]);
   		return ak;
   	}
}