package servlets;

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

import dao.AuthDao;
import dao.ProfessorInfoDao;
import dao.StudentInfoDao;
import dao.SubjectInfoDao;
import vo.Auth;
import vo.ProfessorInfo;
import vo.StudentInfo;
import vo.SubjectInfo;

@WebServlet("/subjectview")
public class ExSubject2Json extends HttpServlet{

//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	
//	@Override
//	protected void doPost(
//			HttpServletRequest request, HttpServletResponse response)
//					throws ServletException, IOException {
//
//
//		try {
//			
//			  String msg = request.getParameter("msg");
//			  System.out.println("msg = "+ msg);
//			  
//		//	if(request.getParameter("submit").equals("subjectview")) {
//			System.out.println("들어왓음");
//				ServletContext sc = this.getServletContext();
//				SubjectInfoDao	 subjInfoDao =  (SubjectInfoDao) sc.getAttribute("subjectInfoDao");
//				ArrayList<SubjectInfo> subjAllList = (ArrayList<SubjectInfo>) subjInfoDao.selectList();
//				request.setAttribute("subjAllList", subjAllList);
//				
//				RequestDispatcher rd = request.getRequestDispatcher(
//						"/JSONServer.jsp");
//				rd.include(request, response);
//		//	}
//			
//		} catch (Exception e) {
//			throw new ServletException(e);
//
//		}
//
//
//	}

}
