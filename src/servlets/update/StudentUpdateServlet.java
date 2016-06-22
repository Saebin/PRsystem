package servlets.update;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProfessorInfoDao;
import dao.StudentInfoDao;
import dao.SubjectInfoDao;
import vo.Auth;
import vo.ProfessorInfo;
import vo.StudentInfo;
import vo.SubjectInfo;


@WebServlet("/studentupdate")
public class StudentUpdateServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			ServletContext sc = this.getServletContext();


			HttpSession session = (HttpSession) request.getSession();
			Auth auth = (Auth) session.getAttribute("auth");

			if(auth.getAuth().equals("student")) {


				StudentInfoDao stdInfoDao = (StudentInfoDao)sc.getAttribute("stdInfoDao");

				StudentInfo stdInfo = new StudentInfo()
														.setStdPhone(request.getParameter("phone"))
														.setStdEmail(request.getParameter("userid1"))
														.setStdName(request.getParameter("name"))
														.setStdAuth(request.getParameter("caarer"))
														.setStdPwd(request.getParameter("userpw"))
														.setStdId(Integer.parseInt(request.getParameter("stu_num")));

				stdInfoDao.update(stdInfo);

				session.removeAttribute("stdInfo");	
				session.setAttribute("stdInfo", stdInfo);

				RequestDispatcher rd = request.getRequestDispatcher("/stu_main.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
}
