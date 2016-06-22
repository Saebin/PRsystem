package servlets;

import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.NoticeDao;
import dao.ProfessorInfoDao;
import dao.StudentInfoDao;
import dao.StudentSubjectDao;
import dao.SubjectInfoDao;
import dao.TaskEvaluateManagementDao;
import dao.TaskInfoDao;
import dao.TaskManagementDao;
import vo.Auth;
import vo.ProfessorInfo;
import vo.StudentInfo;
import vo.SubjectInfo;
import vo.TaskInfo;
import vo.TaskManagementInfo;

@WebServlet("/task_submit")
public class StudentTaskSubmitServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("resource")
	@Override
	public void doPost(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		try {
			
			ServletContext sc = this.getServletContext();
			HttpSession session = (HttpSession) request.getSession();
			
			request.setCharacterEncoding("UTF-8");
			String str = request.getParameter("smarteditor");
			String title = request.getParameter("title");
			
			
			List<TaskInfo> taskInfoList = (List<TaskInfo>) session.getAttribute("select_taskInfoList");
			int number = taskInfoList.get(0).getTask_number();
			String code = taskInfoList.get(0).getSubj_code_div();
			String Subj_name = taskInfoList.get(0).getTask_name();
			
			StudentInfo stdInfo = (StudentInfo) session.getAttribute("stdInfo");
			String name = stdInfo.getStdName();
			int stdNumber = stdInfo.getStdId();
			
			System.out.println("결과"+str);
			File file = new File("C:/Users/b10310/git/web/PRsystem/WebContent/code_similarity/"+code+"_"+Subj_name+"/"+title+"_"+name+".txt");
			FileWriter fileWriterTest   =  null;
			PrintWriter newLineTest    =  null;
			try{
				
				fileWriterTest = new FileWriter(file);
				//fileWriterTest.write(str.replace("\r\n", "<br>")); 디비용
				fileWriterTest.write(str);
				fileWriterTest.flush();
		        	   
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(fileWriterTest  != null){ fileWriterTest.close();}
					if(newLineTest   != null){ fileWriterTest.close();}
				}catch(Exception ee){
					ee.printStackTrace();
				}
			}
			
			TaskManagementDao taskManagementDao = (TaskManagementDao) sc.getAttribute("taskManagementDao");
			taskManagementDao.Contentinsert(number, stdNumber, 
						new TaskManagementInfo().setTask_title(title)
						.setTask_content(str.replace("\r\n", "<br>"))
					);
			

			RequestDispatcher rd = request.getRequestDispatcher("/main");
			rd.forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("login");
			rd.forward(request, response);
		}
	}
	
}
