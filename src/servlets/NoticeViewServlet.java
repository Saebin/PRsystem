package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.NoticeDao;
import dao.SubjectInfoDao;
import vo.Auth;
import vo.Notice;
import vo.ProfessorInfo;
import vo.SubjectInfo;

@WebServlet("/noticeview")
public class NoticeViewServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		try {
			ServletContext sc = this.getServletContext();
			
			HttpSession session = (HttpSession) request.getSession();
			
			Auth auth = (Auth) session.getAttribute("auth");

			if (auth == null) {

				response.sendRedirect("login");

			} else {

				if (auth.getAuth().equals("professor")) {
					NoticeDao noticeDao = (NoticeDao)sc.getAttribute("noticeDao");
					ProfessorInfo profInfo = (ProfessorInfo) session.getAttribute("profInfo");
					
					// 세션 객체가 없다면, 다시 불러들인다. 
					// 교수 id 파라메타
					if(session.getAttribute("noticeList") == null) {
						int profId = profInfo.getProfId();
						session.setAttribute("noticeList", noticeDao.selectList(profId));
					}
		
					
					response.setContentType("text/html; charset=UTF-8");
					RequestDispatcher rd = request.getRequestDispatcher(
							"pro_notify.jsp");
					rd.include(request, response);
				}
				else if (auth.getAuth().equals("student")){
					
					response.setContentType("text/html; charset=UTF-8");
					RequestDispatcher rd = request.getRequestDispatcher(
							"stu_notify.jsp");
					rd.include(request, response);
					
				}
					
				}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("login");
			rd.forward(request, response);
		}
		
	}

	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {


//
//		try {
//			ServletContext sc = this.getServletContext();
//
//			HttpSession session = request.getSession();
//			Auth getAuth = (Auth) session.getAttribute("auth");
//			String auth =getAuth.getAuth();
//
//			// 공지사항을 보내기 위해서는
//			// 교수가 만든 과목 (과목코드) 이 있어야 하고,
//			// 교수의 교번이 필요하다.
//
//			// 과목이 여러개일 것인데, 웹에서 선택한 것으로 한다.
//
//			if(auth.equals("professor")) {
//				SubjectInfoDao subjectInfoDao = (SubjectInfoDao) sc.getAttribute("subjectInfoDao");
//
//				ArrayList<String> list = new ArrayList<String>();
//				//	list = "받은과목";//////////////////////////////////////////////////////////////
//
//				list.add(request.getParameter("notify"));
//
//				//				List<String> subjectSelect = subjectInfoDao.selectSubject(list);
//				// 생각해보니 필요없음
//
//				ProfessorInfo profInfo = (ProfessorInfo) session.getAttribute("profInfo");
//				//이런 ;;
//				//과목 선택수만큼 갯수를  디비에 insert ...
//				NoticeDao noticeDao = (NoticeDao) sc.getAttribute("noticeDao");
//				Iterator<String> it = list.iterator();	
//				while(it.hasNext()){
//
//					Notice notice = new Notice().setNoti_comment(request.getParameter("smarteditor"))/////////////////////////
//							.setProf_id(profInfo.getProfId())
//							.setSubj_code_div(it.next())
//							.setNoti_name(request.getParameter("name"));
//					noticeDao.insert(notice);
//				}
//
//
//				RequestDispatcher rd = request.getRequestDispatcher("pro_notify.jsp");
//				rd.forward(request, response);
//
//			} else {
//
//			}
//
//		} catch (Exception e) {
//			throw new ServletException(e);
//
//		}


	}

}
