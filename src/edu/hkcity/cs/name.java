package edu.hkcity.cs;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class name {
	ArrayList<String> list = new ArrayList<String>();
	Scanner scanner = null;
	static public boolean simple = false;
	private String folderName;
	Input input;
	ArrayList<String> filename;

	public String fact(String name, String number) {// TODO Auto-generated method stub
		String path = input_folder_name(name);
	
		filename = new ArrayList<String>();
		filename.clear();
		File dirFile = new File(path);
		File[] fileList = dirFile.listFiles();
		java.sql.Connection connection;
        java.sql.Statement statement;
        String sql;
        String sql01;
        String jdbcUrl = "jdbc:mysql://localhost:3306/pr_system";
        String databaseID = "root";
        String databasePW = "tjd134";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found : " + e);
        }

		System.out.println(path);
		for (File tempFile : fileList) {
			if (tempFile.isFile()) {
				String tempPath = tempFile.getParent();
				tempPath = tempPath.replace("\\", "\\\\");
				String tempFileName = tempFile.getName();
				list.add(tempPath + "\\\\" + tempFileName);
				filename.add(tempFileName);
				// System.out.println(list.size());
				// System.out.println(list.get(list.size() - 1));
				// System.out.println("Path=" + tempPath);
				// System.out.println("FileName=" + tempFileName);
			}
			
		}
		
		Output op = new Output();
//		ArrayList save_filename;
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				op.count = 0;
				System.out.println("----------------------------------------------------------------");
				System.out.println(filename.get(i).toString() + " 학생과 " + filename.get(j).toString() + "의 유사도 입니다.");
				System.out.println("----------------------------------------------------------------");
				input = new Input(list.get(i).toString(), list.get(j).toString());
				try {
					input.getInput();
					System.out.println(op.arlist.size());
					
					if(op.count >= 2) {
						
				        double avg = 0;
				        
				        for(int k = 0; k < op.arlist.size(); k++) {
				            System.out.println("one index " + k + " : value " + op.arlist.get(k));
				            avg = avg + op.arlist.get(k);
				        }
				        avg = avg/op.arlist.size();
				        
				        String tt1 = filename.get(i).toString().split("_")[1];
				        String tt2 = filename.get(j).toString().split("_")[1];
				        System.out.println(tt1);
				        System.out.println(tt2);
				        
				        String name01 = tt1.split(".tx")[0]; 
				        String name02 = tt2.split(".tx")[0]; 
				        System.out.println(name01);
				        System.out.println(name02);
				        
				       	try {
				            sql = "INSERT INTO CODESIMILARITY(task_number,STU_NAME1,STU_NAME2,CONTENT) VALUES ('" + number + "','" + name01 + "','" + name02 +"','" + avg +"')" ;
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
					op.arlist.clear(); 
								        					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				System.out.println("----------------------------------------------------------------");
				System.out.println("");
				 if(op.count >= 2){
		         		name n = new name();
		         		n.simple = true;
		         		op.number++;
		      	 }
				op.count = 0;
				simple = false;
				
				
			}
		}
		
		try {
        	int a = Integer.parseInt(number);
        	System.out.println(a);
            sql01 = "update task_info set task_group=1 where task_number="+ a +"";
            connection = DriverManager.getConnection(jdbcUrl, databaseID, databasePW);
            statement = connection.createStatement();
            statement.executeUpdate(sql01);				            
            statement.close();
            connection.close();
        } catch (SQLException e){
            System.err.println("SQL Error : " + e);
        } catch (Exception e) {
            System.err.println("Error : " + e);
        }
		
		//System.out.println(op.number);
		return null;
		
	}
	private String input_folder_name(String msg){
		
		String ori_path = "C:/Users/b10310/git/web/PRsystem/WebContent/code_similarity/"+msg;
		/* if (scanner==null)
	            scanner = new Scanner(System.in);
        String folderName = null;
		while (folderName == null) {
            System.out.print(msg);
            folderName = scanner.next();
		}
		ori_path = ori_path+folderName;*/
		return ori_path;
	}
}