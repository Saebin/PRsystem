package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
import dao.SubjectInfoDao;
import dao.TaskInfoDao;
import vo.Auth;
import vo.Notice;
import vo.ProfessorInfo;
import vo.StudentInfo;
import vo.SubjectInfo;

@WebServlet("/msubjectlist")
public class MSubjectListServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		int id = 0;
		id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		
		try {
			ServletContext sc = this.getServletContext();

			HttpSession session = (HttpSession) request.getSession();

			if(id == 0) {

				response.sendRedirect("mlogin");

			} else {


					// 과목 가입 페이지에서
					// 가입가능한 과목정보를 불러온다.
					SubjectInfoDao subjectInfoDao = (SubjectInfoDao) sc.getAttribute("subjectInfoDao");
					ArrayList<SubjectInfo> subjRegiList = (ArrayList<SubjectInfo>) subjectInfoDao.subjectRegistList();
					request.setAttribute("subjRegiList", subjRegiList);
						
					
					response.setContentType("text/html; charset=UTF-8");
					
					RequestDispatcher rd = request.getRequestDispatcher(
							"/MSubject_regist.jsp");
					rd.include(request, response);
				}
			
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
}

