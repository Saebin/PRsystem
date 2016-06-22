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
import dao.TaskEvaluateManagementDao;
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

@WebServlet("/taskevaluation")
public class StudentEvaluationServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ServletContext sc = this.getServletContext();

			HttpSession session = (HttpSession) request.getSession();
			Auth auth = (Auth) session.getAttribute("auth");
			System.out.println("--------------------------------------------------");
			if (auth == null) {

				response.sendRedirect("login");

			} else {

				if (auth.getAuth().equals("student")) {
					String task_num = request.getParameter("num");
					String code = request.getParameter("code");
					String std_id = request.getParameter("std_id");
					int num = Integer.parseInt(task_num);
					int id = Integer.parseInt(std_id);
					int div_group = 0;

					Map<String, Map<Integer, ArrayList<TaskManagementInfo>>> taskManagement = (Map<String, Map<Integer, ArrayList<TaskManagementInfo>>>) session
							.getAttribute("AlltaskManagement");
					// Map<String, Map<Integer, ArrayList<TaskManagementInfo>>>
					// temptaskManagement = new HashMap<String, Map<Integer,
					// ArrayList<TaskManagementInfo>>>();
					ArrayList<TaskManagementInfo> select_taskManagement = new ArrayList<TaskManagementInfo>();
					for (int i = 0; i < taskManagement.get(code).get(num).size(); i++) {

						if (taskManagement.get(code).get(num).get(i).getStd_id() == id) {
							div_group = taskManagement.get(code).get(num).get(i).getGroup_div();

						}
					}
					for (int i = 0; i < taskManagement.get(code).get(num).size(); i++) {

						if (taskManagement.get(code).get(num).get(i).getGroup_div() == div_group
								&& taskManagement.get(code).get(num).get(i).getStd_id() != id) {
							// System.out.println(taskManagement.get(code).get(num).get(i).getStd_id());
							select_taskManagement.add(taskManagement.get(code).get(num).get(i));
							// temptaskManagement.put(code,select_taskManagement);
						}

					}

					String task_number = request.getParameter("num");

					List<TaskInfo> taskInfoList = (List<TaskInfo>) session.getAttribute("AlltaskInfoList");
					for (int i = 0; i < taskInfoList.size(); i++) {
						int Task_number = taskInfoList.get(i).getTask_number();
						int num01 = Integer.parseInt(task_number);

						if (Task_number == num01) {
							ArrayList<TaskInfo> select_taskInfoList = new ArrayList<TaskInfo>();
							select_taskInfoList.add(taskInfoList.get(i));
							session.setAttribute("select_taskInfoList", select_taskInfoList);
						}
					}

					session.setAttribute("select_taskManagement", select_taskManagement);
					// System.out.println(session.getAttribute("select_taskManagement"));
					/*
					 * System.out.println("---------");
					 * System.out.println(session.getAttribute(
					 * "select_taskManagement"));
					 */
					

					response.setContentType("text/html; charset=UTF-8");
					RequestDispatcher rd = request.getRequestDispatcher("/stu_evaluation.jsp");
					rd.forward(request, response);

				} else if (auth.getAuth().equals("professor")) {
					System.out.println("--------------------------------------------------");
					String task_num = request.getParameter("num");
					String code = request.getParameter("Subj_code");
					int num = Integer.parseInt(task_num);

					TaskManagementDao taskManagementDao = (TaskManagementDao) sc.getAttribute("taskManagementDao");
					if (taskManagementDao.check(num) == 0) {
						
						session.setAttribute("code_task_num", num);
						response.setContentType("text/html; charset=UTF-8");
						RequestDispatcher rd1 = request.getRequestDispatcher("/code");
						System.out.println("--------------------------------------------------");
						rd1.forward(request, response);
					} else {

						Map<String, Map<Integer, ArrayList<TaskManagementInfo>>> taskManagement = (Map<String, Map<Integer, ArrayList<TaskManagementInfo>>>) session
								.getAttribute("AlltaskManagement");
						// Map<String, Map<Integer,
						// ArrayList<TaskManagementInfo>>>
						// temptaskManagement = new HashMap<String, Map<Integer,
						// ArrayList<TaskManagementInfo>>>();
						ArrayList<TaskManagementInfo> select_taskManagement = new ArrayList<TaskManagementInfo>();
/*						System.out.println(taskManagement);
						System.out.println(task_num);
						System.out.println(num);
						System.out.println(code);*/
						System.out.println("--------------------------------------------------");
						for (int i = 0; i < taskManagement.get(code).get(num).size(); i++) {

							if (taskManagement.get(code).get(num).get(i).getTask_number() == num) {
								// System.out.println(taskManagement.get(code).get(num).get(i).getStd_id());
								select_taskManagement.add(taskManagement.get(code).get(num).get(i));
								// temptaskManagement.put(code,select_taskManagement);
							}

						}
						session.setAttribute("select_taskManagement", select_taskManagement);

						ArrayList<TaskInfo> taskInfoList = (ArrayList<TaskInfo>) session
								.getAttribute("pro_taskInfoList");

						CodeSimilarityDao codesimilarityDao = (CodeSimilarityDao) sc.getAttribute("codesimilarityDao");
						session.setAttribute("codeList", codesimilarityDao.selectList(num));
						System.out.println(taskInfoList);
						for (int i = 0; i < taskInfoList.size(); i++) {
							int Task_number = taskInfoList.get(i).getTask_number();

							if (Task_number == num) {
								ArrayList<TaskInfo> select_taskInfoList = new ArrayList<TaskInfo>();
								select_taskInfoList.add(taskInfoList.get(i));
								session.setAttribute("select_taskInfoList", select_taskInfoList);
							}
						}

						TaskEvaluateManagementDao taskEvaluateManagementDao = (TaskEvaluateManagementDao) sc
								.getAttribute("taskEvaluateManagementDao");

						session.setAttribute("taskEvaluateManagement", taskEvaluateManagementDao.getInfo(num));

						response.setContentType("text/html; charset=UTF-8");
						RequestDispatcher rd = request.getRequestDispatcher("/pro_task_situation_detail.jsp");
						rd.forward(request, response);
						
					}
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
