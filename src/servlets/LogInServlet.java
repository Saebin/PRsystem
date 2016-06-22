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

// ServletContext에 보관된 MemberDao 사용하기  
@WebServlet("/login")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher(
				"Login.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		try {
			
			ServletContext sc = this.getServletContext();

			AuthDao authDao = (AuthDao) sc.getAttribute("authDao");
			Auth auth = authDao.existAuth( request.getParameter("email"), request.getParameter("password"));	


			// 로그인 할때 학생은 이메일, 학번, 권한, 이름,
			// 과목이름, 과목코드, 과목 이미지를 얻는다.
			if(auth.getAuth().equals("student")) {

				HttpSession session = request.getSession();
				session.setAttribute("auth", auth);

				StudentInfoDao stdInfoDao = (StudentInfoDao) sc.getAttribute("stdInfoDao");


				// stdInfo 에는 이메일 , 학번 , 권한 , 이름이 있다.
				StudentInfo stdInfo = stdInfoDao.exist(
						request.getParameter("email"), 
						request.getParameter("password"));

				if (stdInfo != null) {
					session.setAttribute("stdInfo", stdInfo);
					response.sendRedirect("main");

				} else {
					RequestDispatcher rd = request.getRequestDispatcher(
							"/auth/LogInFail.jsp");
					rd.forward(request, response);
				}

			} else if(auth.getAuth().equals("professor")) {

				HttpSession session = request.getSession();
				session.setAttribute("auth", auth);

				ProfessorInfoDao profInfoDao = (ProfessorInfoDao) sc.getAttribute("profInfoDao");


				// profInfo 에는 이메일 , 교번 , 권한 , 이름이 있다.
				ProfessorInfo profInfo = profInfoDao.exist(
						request.getParameter("email"), 
						request.getParameter("password"));

				if (profInfo != null) {
					session.setAttribute("profInfo", profInfo);
					response.sendRedirect("main");

				} else {	
					RequestDispatcher rd = request.getRequestDispatcher(
							"/auth/LogInFail.jsp");
					rd.forward(request, response);
				}
			}

		} catch (Exception e) {
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('Please check ID and password');");  
			out.println("history.back();");
			out.println("</script>");
//			throw new ServletException(e);

		}


	}
}
