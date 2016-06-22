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
import vo.Auth;
import vo.ProfessorInfo;
import vo.StudentInfo;
import vo.SubjectInfo;


@WebServlet("/professorupdate")
public class ProfessorUpdateServlet extends HttpServlet {
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
			System.out.println("career");
			if(auth.getAuth().equals("professor")) {

				ProfessorInfoDao profInfoDao = (ProfessorInfoDao)sc.getAttribute("profInfoDao");

				ProfessorInfo profInfo = new ProfessorInfo()
															.setProfPhone(request.getParameter("userid"))
															.setProfEmail(request.getParameter("userid1"))
															.setProfPwd(request.getParameter("userpw1"))
															.setProfName(request.getParameter("name"))
															.setProfAuth(request.getParameter("career"))
															.setProfId(Integer.parseInt(request.getParameter("stu_num")));
														

				profInfoDao.update(profInfo);

				session.removeAttribute("profInfo");	
				session.setAttribute("profInfo", profInfo);

				RequestDispatcher rd = request.getRequestDispatcher("/pro_main.jsp");
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
