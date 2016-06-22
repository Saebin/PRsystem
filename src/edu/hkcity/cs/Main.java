package edu.hkcity.cs;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import dao.AuthDao;
import dao.CodeSimilarityDao;
import dao.SubjectInfoDao;
import dao.TaskInfoDao;
import vo.CodeSimilarityInfo;
import vo.SubjectInfo;
import vo.TaskInfo;

@WebServlet("/code")
public class Main extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			ServletContext sc = this.getServletContext();
			HttpSession session = (HttpSession) request.getSession();
			name na = new name();
			String str = request.getParameter("similarity");
			String code = request.getParameter("Subj_code");
			String task_num = request.getParameter("num");
			int code_num = (int) session.getAttribute("code_task_num");
			System.out.println(code_num);
			int num = Integer.parseInt(task_num);
			
			List<TaskInfo> taskInfoList12 = (List<TaskInfo>)session.getAttribute("pro_taskInfoList");

			for(int i=1; i<taskInfoList12.size(); i++) {
				if(taskInfoList12.get(i).getTask_number() == num){
					if(taskInfoList12.get(i).getTask_code() == 0){
						System.out.println(taskInfoList12.get(i).getTask_code());
						System.out.println("0이기 때문에 계속");
						na.fact(str, task_num);
						System.out.println("완료");
						TaskInfoDao taskInfoDao = (TaskInfoDao)sc.getAttribute("taskInfoDao");
						taskInfoDao.updateTask_code(num);
						
						//System.out.println(taskInfoDao.taskCount(num)+"ㄴㅁㅇㄹ");
					} 
				}
			}
			TaskInfoDao taskInfoDao = (TaskInfoDao)sc.getAttribute("taskInfoDao");
			taskInfoDao.taskCount(num);
			
			java.sql.Connection connection;
			java.sql.Statement statement;
			String sql;
			String jdbcUrl = "jdbc:mysql://localhost:3306/pr_system";
			String databaseID = "root";
			String databasePW = "tjd134";
			ResultSet resultset;
			int a = 0;
			/*try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Class not found : " + e);
			}
			try {
				sql = "SELECT TASK_GROUP from TASK_INFO where TASK_NUMBER=" + task_num + "";
				connection = DriverManager.getConnection(jdbcUrl, databaseID, databasePW);
				statement = connection.createStatement();
				resultset = statement.executeQuery(sql);
				while (resultset.next()) {
					a = resultset.getInt("task_group");
					// System.out.println(a);
				}
				statement.close();
				connection.close();
			} catch (SQLException e) {
				System.err.println("SQL Error : " + e);
			} catch (Exception e) {
				System.err.println("Error : " + e);
			}

			if (a == 0) {
				na.fact(str, task_num);
			} else {

			}*/

			int kk = Integer.parseInt(task_num);
			ArrayList<TaskInfo> taskInfoList = (ArrayList<TaskInfo>) session.getAttribute("pro_taskInfoList");

			CodeSimilarityDao codesimilarityDao = (CodeSimilarityDao) sc.getAttribute("codesimilarityDao");
			session.setAttribute("codeList", codesimilarityDao.selectList(kk));

			for (int i = 0; i < taskInfoList.size(); i++) {
				int Task_number = taskInfoList.get(i).getTask_number();

				if (Task_number == num) {
					ArrayList<TaskInfo> select_taskInfoList = new ArrayList<TaskInfo>();
					select_taskInfoList.add(taskInfoList.get(i));
					session.setAttribute("select_taskInfoList", select_taskInfoList);
				}
			}

			ResultSet total_resul;
			String total;
			int[] test = null;
			int i = 0;

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Class not found : " + e);
			}
			try {
				//sql = "SELECT * FROM task_div";
				total = "SELECT count(task_number) FROM task_management WHERE task_number="+code_num;
				connection = DriverManager.getConnection(jdbcUrl, databaseID, databasePW);
				statement = connection.createStatement();
				//resultset = statement.executeQuery(sql);
				total_resul = statement.executeQuery(total);
				int tt = 0;
				while (total_resul.next()) {
					int count = total_resul.getRow();
					tt = total_resul.getInt(count);
					test = new int[tt]; 
					//System.out.println(tt);

				}
				statement.close();
				connection.close();

				sql = "SELECT * FROM task_management where task_number="+code_num;
				connection = DriverManager.getConnection(jdbcUrl, databaseID, databasePW);
				statement = connection.createStatement();
				resultset = statement.executeQuery(sql);
				while (resultset.next()) {
					test[i++] = resultset.getInt("std_id");
				}
				for (int aa = 0; aa < test.length; aa++)
					System.out.print(test[aa] + ":");

				statement.close();
				connection.close();
			} catch (SQLException e) {
				System.err.println("SQL Error : " + e);
			} catch (Exception e) {
				System.err.println("Error : " + e);
			}

			System.out.println("");

			HashMap<Integer, int[]> map = new HashMap<Integer, int[]>();
			int group_num = 5;
			int tt = test.length / group_num;
			int[] group_array = new int[group_num];
			int k = 0;
			shuffleArray(test);
			for (int ab = 0; ab < test.length; ab++)
				System.out.print(test[ab] + ":");
			System.out.println("");
			System.out.println("============================");
			for (int p = 0; p < tt; p++) {
				for (int j = 0; j < group_num; j++) {
					group_array[j] = test[k];
					k++;
				}
				map.put(p, group_array);

				for (int r = 0; r < map.get(p).length; r++) {
					System.out.print(map.get(p)[r] + "-");

					try {
						System.out.println(p);
						sql = "update task_management set GROUP_DIV=" + p + " where task_number="+code_num+" and std_id=" + map.get(p)[r]
								+ "";
						connection = DriverManager.getConnection(jdbcUrl, databaseID, databasePW);
						statement = connection.createStatement();
						statement.executeUpdate(sql);
						statement.close();
						connection.close();
					} catch (SQLException e) {
						System.err.println("SQL Error : " + e);
					} catch (Exception e) {
						System.err.println("Error : " + e);
					}

				}
				System.out.println("");
				System.out.println("============================");

			}

			response.setContentType("text/html; charset=UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/taskevaluation");
			rd.forward(request, response);

		} catch (Exception e) {
			throw new ServletException(e);

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
}
