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

import dao.NoticeDao;
import dao.ProfessorInfoDao;
import dao.StudentInfoDao;
import dao.StudentSubjectDao;
import dao.SubjectInfoDao;
import vo.Notice;
import vo.StudentInfo;
import vo.StudentSubject;
import vo.SubjectInfo;

@WebServlet("/mmain")
public class MMainServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String auth = request.getParameter("auth");
		String token = request.getParameter("token");
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);

		try {
			ServletContext sc = this.getServletContext();

			HttpSession session = (HttpSession) request.getSession();

			if (auth == null) {

				response.sendRedirect("mlogin");

			} else {

				if (auth.equals("student")) {

					StudentInfoDao stdInfoDao = (StudentInfoDao) sc.getAttribute("stdInfoDao");
					// request.setAttribute("stdInfo", stdInfoDao.selectList());

					StudentInfo stdInfo = (StudentInfo) session.getAttribute("stdInfo");

					// GCM용 토큰을 업데이트한다.
					if (token != "" || !token.equals(null)) {
						stdInfoDao.tokenUpdate(token, id);
					}

					StudentSubjectDao studentSubjectDao = (StudentSubjectDao) sc.getAttribute("studentSubjectDao");
					ArrayList<StudentSubject> subjCodeDivList = (ArrayList<StudentSubject>) studentSubjectDao
							.getSubjCodeDivList(id);

					// 로그인 후 메인화면에 올 때,
					// 수강/강의중인 강의 정보를 갖고온다.
					SubjectInfoDao subjectInfoDao = (SubjectInfoDao) sc.getAttribute("subjectInfoDao");
					ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>) subjectInfoDao
							.getSubjInfo(subjCodeDivList);
					request.setAttribute("subjInfoList", subjInfoList);

					// 로그인 후 메인화면에 올 때,
					// 학생이 받은 모든 공지사항을 불러온다.
					// 학생의 id 파라메타.
					// NoticeDao noticeDao =
					// (NoticeDao)sc.getAttribute("noticeDao");
					//// int profId = profInfo.getProfId();
					// session.setAttribute("noticeList",
					// noticeDao.selectList(id));

					response.setContentType("text/html; charset=UTF-8");
					RequestDispatcher rd = request.getRequestDispatcher("/MStd_main.jsp");
					rd.forward(request, response);

					/*
					 * 교수 메인화면 접근
					 */
				} else if (auth.equals("professor")) {

					ProfessorInfoDao profInfoDao = (ProfessorInfoDao) sc.getAttribute("profInfoDao");
					// request.setAttribute("proInfoDao",
					// proInfoDao.selectList());

					System.out.println(token);
					// GCM용 토큰을 업데이트한다.
					if (token != "" || !token.equals(null)) {
						profInfoDao.tokenUpdate(token, id);
					}

					// 로그인 후 메인화면에 올 때,
					// 수강/강의중인 강의 정보를 갖고온다.
					SubjectInfoDao subjectInfoDao = (SubjectInfoDao) sc.getAttribute("subjectInfoDao");
					ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>) subjectInfoDao.getSubjInfo(id);
					request.setAttribute("subjInfoList", subjInfoList);

					// 로그인 후 메인화면에 올 때,
					// 교수가 보낸 모든 공지사항을 가져온다.
					// 교수의 id 파라메타.
					NoticeDao noticeDao = (NoticeDao) sc.getAttribute("noticeDao");
					// int profId = profInfo.getProfId();
					request.setAttribute("noticeList", noticeDao.selectList(id));
					ArrayList<SubjectInfo> subjInfoList1 = (ArrayList<SubjectInfo>) request
							.getAttribute("subjInfoList");
					System.out.println(subjInfoList1.get(0).getSubj_outline());
					// // 로그인 후 메인화면에 올 때,
					// // 교수가 출제한 모든 과제 정보를 가져온다.
					// // 교수의 과목이 파라메타.
					// TaskInfoDao taskInfoDao =
					// (TaskInfoDao)sc.getAttribute("taskInfoDao");
					// ArrayList<String> subj_code_div = new
					// ArrayList<String>();
					// for(int i=0; i<subjInfoList.size(); i++) {
					// subj_code_div.add(subjInfoList.get(i).getSubj_code_div());
					// }
					// session.setAttribute("taskInfoList",
					// taskInfoDao.selectList(subj_code_div));

					response.setContentType("text/html; charset=UTF-8");

					RequestDispatcher rd = request.getRequestDispatcher("/MProf_main.jsp");
					rd.include(request, response);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
}
