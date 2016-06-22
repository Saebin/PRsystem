package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProfessorInfoDao;
import dao.StudentInfoDao;
import vo.ProfessorInfo;
import vo.StudentInfo;

// ServletContext에 보관된 MemberDao 사용하기  
@WebServlet("/add")
public class MemberAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		//    RequestDispatcher rd = request.getRequestDispatcher(
		//        "/member/MemberForm.jsp");
		//    rd.forward(request, response);
	}

	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		try {
			ServletContext sc = this.getServletContext();
			if(request.getParameter("career").equals("student")) {
				StudentInfoDao stdInfoDao = (StudentInfoDao)sc.getAttribute("stdInfoDao");
				stdInfoDao.insert( new StudentInfo().setStdId(Integer.parseInt(request.getParameter("stu_num")))
						.setStdName(request.getParameter("name"))
						.setStdPwd(request.getParameter("userpw"))
						.setStdEmail(request.getParameter("userid1"))
						.setStdPhone(request.getParameter("userid"))
						.setStdAuth(request.getParameter("career")));
//System.out.println("1");
				//    	      response.sendRedirect("list");
				RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
				rd.forward(request, response);
			} else if(request.getParameter("career").equals("professor")) {
				ProfessorInfoDao profInfoDao = (ProfessorInfoDao)sc.getAttribute("profInfoDao");
				profInfoDao.insert( new ProfessorInfo().setProfId(Integer.parseInt(request.getParameter("stu_num")))
						.setProfName(request.getParameter("name"))
						.setProfPwd(request.getParameter("userpw"))
						.setProfEmail(request.getParameter("userid1"))
						.setProfPhone(request.getParameter("userid"))
						.setProfAuth(request.getParameter("career")));
				System.out.println("2");
				//        	      response.sendRedirect("list");
				RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
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
