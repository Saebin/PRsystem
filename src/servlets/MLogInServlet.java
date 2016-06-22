package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AuthDao;
import dao.ProfessorInfoDao;
import dao.StudentInfoDao;
import vo.Auth;
import vo.ProfessorInfo;
import vo.StudentInfo;

@WebServlet("/mlogin")
public class MLogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

	}

	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {


		String id = request.getParameter("email");
		String pwd = request.getParameter("password");
		System.out.println("id = "+ id);
		System.out.println("pwd = "+ pwd );



		try {
			ServletContext sc = this.getServletContext();

			AuthDao authDao = (AuthDao) sc.getAttribute("authDao");
			Auth auth = authDao.existAuth( request.getParameter("email"), request.getParameter("password"));	

			if(auth.getAuth().equals("student")) {

				StudentInfoDao stdInfoDao = (StudentInfoDao) sc.getAttribute("stdInfoDao");

				StudentInfo stdInfo = stdInfoDao.exist(
						request.getParameter("email"), 
						request.getParameter("password"));

				if (stdInfo != null) {
					System.out.println("학생로그인성공");

					request.setAttribute("stdInfo", stdInfo);
					RequestDispatcher rd = request.getRequestDispatcher(
							"/MLogin.jsp");
					rd.include(request, response);

				} else {
					System.out.println("학생로그인실패");
					PrintWriter out = response.getWriter();
					out.print("fail");
					out.close();
				}

			} else if(auth.getAuth().equals("professor")) {


				ProfessorInfoDao profInfoDao = (ProfessorInfoDao) sc.getAttribute("profInfoDao");

				ProfessorInfo profInfo = profInfoDao.exist(
						request.getParameter("email"), 
						request.getParameter("password"));

				if (profInfo != null) {

					System.out.println("교수로그인성공");

					request.setAttribute("profInfo", profInfo);
					//					request.setAttribute("AUTH", auth.getAuth());
					RequestDispatcher rd = request.getRequestDispatcher(
							"/MLogin.jsp");
					rd.include(request, response);

					//					PrintWriter out = response.getWriter();
					//					out.print(auth.getAuth());
					//					out.close();

				} else {
					System.out.println("교수로그인실패");
					RequestDispatcher rd = request.getRequestDispatcher(
							"/MLoginFail.jsp");
					rd.include(request, response);
				}
			}

		} catch (Exception e) {
			throw new ServletException(e);

		}


	}
}
