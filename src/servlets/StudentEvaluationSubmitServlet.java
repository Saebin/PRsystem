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
import vo.TaskEvaluateManagementInfo;
import vo.TaskInfo;
import vo.TaskManagementInfo;

@WebServlet("/evaluationSubmit")
public class StudentEvaluationSubmitServlet extends HttpServlet {
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

			if (auth == null) {

				response.sendRedirect("login");

			} else {

				if (auth.getAuth().equals("student")) {
					
					
					StudentInfo stdInfo = (StudentInfo) session.getAttribute("stdInfo");
					int stdNumber = stdInfo.getStdId();
					String score = request.getParameter("score");
					String comment = request.getParameter("comment");
					String stdNumber_eval = request.getParameter("std_id_eval");
					String task_num = request.getParameter("task_num");
					int std_id_eval = Integer.parseInt(stdNumber_eval);
					int score_eval = Integer.parseInt(score);
					int task_number = Integer.parseInt(task_num);
					
					TaskEvaluateManagementDao taskEvaluateManagementDao = (TaskEvaluateManagementDao) sc.getAttribute("taskEvaluateManagementDao");
					taskEvaluateManagementDao.insert(new TaskEvaluateManagementInfo()
							.setTask_number(task_number)
							.setStd_id(stdNumber)
							.setStd_id_evaluate(std_id_eval)
							.setSubmit_comment(comment)
							.setSubmit_score(score_eval)
							);
					
					session.removeAttribute("taskEvaluateManagement");	
					TaskEvaluateManagementDao taskEvaluateManagementDao1 = (TaskEvaluateManagementDao) sc.getAttribute("taskEvaluateManagementDao");
					session.setAttribute("taskEvaluateManagement", taskEvaluateManagementDao1.AllgetInfo());
					
					response.setContentType("text/html; charset=UTF-8");
					RequestDispatcher rd = request.getRequestDispatcher("/main");
					rd.forward(request, response);

				} else if (auth.getAuth().equals("professor")) {
					response.sendRedirect("main");
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
