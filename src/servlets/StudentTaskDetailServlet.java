package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import dao.CodeSimilarityDao;
import dao.NoticeDao;
import dao.ProfessorInfoDao;
import dao.StudentInfoDao;
import dao.StudentSubjectDao;
import dao.SubjectInfoDao;
import dao.TaskInfoDao;
import dao.TaskManagementDao;
import vo.Auth;
import vo.Notice;
import vo.ProfessorInfo;
import vo.StudentInfo;
import vo.StudentSubject;
import vo.SubjectInfo;
import vo.TaskInfo;
import vo.TaskManagementInfo;

@WebServlet("/taskdetail")
public class StudentTaskDetailServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		try {
			ServletContext sc = this.getServletContext();

			HttpSession session = (HttpSession) request.getSession();
			Auth auth = (Auth) session.getAttribute("auth");
			
			if(auth == null) {

				response.sendRedirect("login");

			} else {

				if(auth.getAuth().equals("student")) {

					String task_num = request.getParameter("num01");
					
					List<TaskInfo> taskInfoList = (List<TaskInfo>) session.getAttribute("AlltaskInfoList");
					for (int i = 0; i < taskInfoList.size(); i++) {
						int Task_number = taskInfoList.get(i).getTask_number();						
						int num01 = Integer.parseInt(task_num);

						if (Task_number == num01) {
							ArrayList<TaskInfo> select_taskInfoList = new ArrayList<TaskInfo>();
							select_taskInfoList.add(taskInfoList.get(i));
							session.setAttribute("select_taskInfoList", select_taskInfoList);
						}
					}
					
					response.setContentType("text/html; charset=UTF-8");
					RequestDispatcher rd = request.getRequestDispatcher(
							"/stu_task_detail.jsp");
					rd.forward(request, response);

				}else if(auth.getAuth().equals("professor")) {

					String task_num = request.getParameter("num01");
					
					List<TaskInfo> taskInfoList = (List<TaskInfo>) session.getAttribute("AlltaskManagement");
					System.out.println(taskInfoList);
					for (int i = 0; i < taskInfoList.size(); i++) {
						int Task_number = taskInfoList.get(i).getTask_number();						
						int num01 = Integer.parseInt(task_num);

						if (Task_number == num01) {
							ArrayList<TaskInfo> select_taskInfoList = new ArrayList<TaskInfo>();
							select_taskInfoList.add(taskInfoList.get(i));
							session.setAttribute("select_taskInfoList", select_taskInfoList);
						}
					}

					int num = Integer.parseInt(task_num);

					CodeSimilarityDao codesimilarityDao = (CodeSimilarityDao) sc.getAttribute("codesimilarityDao");
					session.setAttribute("codeList", codesimilarityDao.selectList(num));

					for (int i = 0; i < taskInfoList.size(); i++) {
						int Task_number = taskInfoList.get(i).getTask_number();

						if (Task_number == num) {
							ArrayList<TaskInfo> select_taskInfoList = new ArrayList<TaskInfo>();
							select_taskInfoList.add(taskInfoList.get(i));
							session.setAttribute("select_taskInfoList", select_taskInfoList);
						}
					}
					response.setContentType("text/html; charset=UTF-8");
					RequestDispatcher rd = request.getRequestDispatcher(
							"/pro_main.jsp");
					rd.forward(request, response);
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("login");
			rd.forward(request, response);
		}
	}
}
