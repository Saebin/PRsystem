package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

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
import dao.TaskInfoDao;
import vo.Auth;
import vo.ProfessorInfo;
import vo.StudentInfo;
import vo.StudentSubject;
import vo.SubjectInfo;

@WebServlet("/studentList")
public class StudentListServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		try {
			ServletContext sc = this.getServletContext();

			HttpSession session = (HttpSession) request.getSession();
			Auth auth = (Auth) session.getAttribute("auth");

					ProfessorInfoDao profInfoDao = (ProfessorInfoDao)sc.getAttribute("proInfoDao");
					//				request.setAttribute("proInfoDao", proInfoDao.selectList());

					ProfessorInfo profInfo = (ProfessorInfo) session.getAttribute("profInfo");

					
					// 로그인 후 메인화면에 올 때,
					// 수강/강의중인 강의 정보를 갖고온다.
					int profId = profInfo.getProfId();
					SubjectInfoDao subjectInfoDao = (SubjectInfoDao) sc.getAttribute("subjectInfoDao");
					ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>) subjectInfoDao.getSubjInfo(profId);
					session.setAttribute("subjInfoList", subjInfoList);
					
					// 로그인 후 메인화면에 올 때,
					// 교수의 강의를 수강하는 학생 정보를 가져온다.
					ArrayList<String> subj_code_divList = new ArrayList<String>();
					
					// 교수가 강의하는 모든 과목코드를 가져온다.
					for(int i=0 ; i<subjInfoList.size();i++) {
						subj_code_divList.add(subjInfoList.get(i).getSubj_code_div());
					}
					
					
					// 교수가 강의하는 과목코드들에 맞는 학번을 배열로 가져온다.
					StudentSubjectDao studentSubjectDao = (StudentSubjectDao) sc.getAttribute("studentSubjectDao");
					Map<String,ArrayList<Integer>>  code_stdIdList= 
							(Map<String,ArrayList<Integer>> ) studentSubjectDao.getInClassStdId(subj_code_divList);
					
					// 과목코드에 맞는 모든 학생정보를 출력해옴
					StudentInfoDao stdInfoDao = (StudentInfoDao) sc.getAttribute("stdInfoDao");
					Map<String,ArrayList<StudentInfo>> inClassStd = stdInfoDao.getInclassStd(code_stdIdList);
					session.setAttribute("inClassStd", inClassStd);

					
					// 로그인 후 메인화면에 올 때,
					// 교수가 보낸 모든 공지사항을 가져온다.
					// 교수의 id 파라메타.
					NoticeDao noticeDao = (NoticeDao)sc.getAttribute("noticeDao");
					
					session.setAttribute("noticeList", noticeDao.selectList(profId));
			

					// 로그인 후 메인화면에 올 때,
					// 교수가 출제한 모든 과제 정보를 가져온다.
					// 교수의 과목이 파라메타.
					TaskInfoDao taskInfoDao = (TaskInfoDao)sc.getAttribute("taskInfoDao");
					ArrayList<String> subj_code_div = new ArrayList<String>();
					for(int i=0; i<subjInfoList.size(); i++) {
						subj_code_div.add(subjInfoList.get(i).getSubj_code_div());
					}
					session.setAttribute("taskInfoList", taskInfoDao.selectList(subj_code_div));


					response.setContentType("text/html; charset=UTF-8");
					RequestDispatcher rd = request.getRequestDispatcher(
							"/pro_student_management.jsp");
					rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("login");
			rd.forward(request, response);
		}
	}
}
