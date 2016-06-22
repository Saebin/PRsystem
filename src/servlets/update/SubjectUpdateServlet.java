package servlets.update;


import java.io.IOException;
import java.util.ArrayList;

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


@WebServlet("/subjectupdate")
public class SubjectUpdateServlet extends HttpServlet {
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

			System.out.println(request.getParameter("regist_code"));
			System.out.println(request.getParameter("code"));
			System.out.println(request.getParameter("limit_num"));
			System.out.println(request.getParameter("sub_outline"));
			
			if(auth.getAuth().equals("professor")) {

 
				SubjectInfoDao subjectInfoDao = (SubjectInfoDao)sc.getAttribute("subjectInfoDao");

				SubjectInfo subjInfoList = new SubjectInfo()
															.setSubj_outline(request.getParameter("sub_outline"))
															.setSubj_code_div(request.getParameter("code"))
															.setRegist_code(request.getParameter("regist_code"))
															.setLimit_num(Integer.parseInt(request.getParameter("limit_num")));

				subjectInfoDao.update(subjInfoList);
				
				
				ProfessorInfo profInfo = (ProfessorInfo) session.getAttribute("profInfo");
				int profId = profInfo.getProfId();				
				ArrayList<SubjectInfo> subjInfoList01 = (ArrayList<SubjectInfo>) subjectInfoDao.getSubjInfo(profId);
				if (session.getAttribute("subjInfoList01") == null) {
					session.setAttribute("subjInfoList", subjInfoList01);
				}
				
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
