package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import dao.TaskManagementDao;
import vo.Auth;
import vo.Notice;
import vo.ProfessorInfo;
import vo.StudentInfo;
import vo.StudentSubject;
import vo.SubjectInfo;
import vo.TaskInfo;
import vo.TaskManagementInfo;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ServletContext sc = this.getServletContext();

			HttpSession session = (HttpSession) request.getSession();
			Auth auth = (Auth) session.getAttribute("auth");

			if (auth == null) {

				response.sendRedirect("login");

			} else {

				if (auth.getAuth().equals("student")) {

					StudentInfoDao stdInfoDao = (StudentInfoDao) sc.getAttribute("stdInfoDao");
					// request.setAttribute("stdInfo", stdInfoDao.selectList());

					StudentInfo stdInfo = (StudentInfo) session.getAttribute("stdInfo");
					int stdId = stdInfo.getStdId();

					// 로그인 후 메인화면에 올 때,
					// 학생이 수강하는 과목코드를 불러온다.
					StudentSubjectDao studentSubjectDao = (StudentSubjectDao) sc.getAttribute("studentSubjectDao");
					ArrayList<StudentSubject> studentSubject = (ArrayList<StudentSubject>) studentSubjectDao
							.getSubjCodeDivList(stdId);

					// 로그인 후 메인화면에 올 때,
					// 수강/강의중인 강의 정보를 갖고온다.
					SubjectInfoDao subjectInfoDao = (SubjectInfoDao) sc.getAttribute("subjectInfoDao");
					ArrayList<SubjectInfo> stdSubjList = (ArrayList<SubjectInfo>) subjectInfoDao
							.getSubjInfo(studentSubject);
					session.setAttribute("stdSubjList", stdSubjList);

					// 로그인 후 메인화면에 올 때,
					// 교수가 보낸 모든 공지사항을 가져온다.
					// 과목코드가 파라메타.
					NoticeDao noticeDao = (NoticeDao) sc.getAttribute("noticeDao");
					Map<String, ArrayList<Notice>> noticeList = noticeDao.selectList(studentSubject);
					session.setAttribute("noticeList", noticeList);

					// 최근 시간순서대로
					// 공지사항 정렬
					Iterator<String> keys = noticeList.keySet().iterator();
					ArrayList<Notice> latestNotice = new ArrayList<Notice>();
					ArrayList<Notice> temp_notice = new ArrayList<Notice>();
					while (keys.hasNext()) {
						String key = keys.next();
						for (int i = 0; i < noticeList.get(key).size(); i++) {
							temp_notice.add(noticeList.get(key).get(i));
						}
					}
					Date max = null;
					int idx = 0;
					int noticeSize = temp_notice.size();
					for (int j = 0; j < noticeSize; j++) {
						for (int k = 0; k < temp_notice.size(); k++) {
							int m = 0;
							if (k == 0) {
								max = temp_notice.get(k).getNoti_mod_date();
								idx = 0;
							} else {
								m = max.compareTo(temp_notice.get(k).getNoti_mod_date());
							}

							if (m < 0) {
								max = temp_notice.get(k).getNoti_mod_date();
								idx = k;
							}
						}
						latestNotice.add(temp_notice.get(idx));
						temp_notice.remove(idx);
					}
					session.setAttribute("latestNotice", latestNotice);

					ArrayList<String> subj_code_div = new ArrayList<String>();
					for (int i = 0; i < stdSubjList.size(); i++) {
						subj_code_div.add(stdSubjList.get(i).getSubj_code_div());
					}
					// 로그인 후 메인화면에 올 때,
					// 학생이 듣는 모든 과제정보를 가져온다.
					// 과목코드가 파라메타.
					TaskInfoDao taskInfoDao = (TaskInfoDao) sc.getAttribute("taskInfoDao");
					List<TaskInfo> taskNumberList = taskInfoDao.allSelectList(subj_code_div);

					session.setAttribute("AlltaskInfoList", taskNumberList);

					// 과제관리정보 불러오기
					// Map<과목코드,Map<과제번호,ArrayList<과제관리정보>>>
					Map<String, ArrayList<TaskManagementInfo>> taskManagement = new HashMap<String, ArrayList<TaskManagementInfo>>();
					TaskManagementInfo info = new TaskManagementInfo();

					TaskManagementDao taskManagementDao = (TaskManagementDao) sc.getAttribute("taskManagementDao");

					for (int i = 0; i < subj_code_div.size(); i++) {
						ArrayList<TaskManagementInfo> temp_taskManagement = new ArrayList<TaskManagementInfo>();
						for (int j = 0; j < taskNumberList.size(); j++) {
							if (subj_code_div.get(i).equals(taskNumberList.get(j).getSubj_code_div())) {
								info = (TaskManagementInfo) taskManagementDao
										.getInfo(taskNumberList.get(j).getTask_number(), stdId);
								temp_taskManagement.add(info);
							}
						}

						taskManagement.put(subj_code_div.get(i), temp_taskManagement);
					}
					session.setAttribute("taskManagement", taskManagement);

					ArrayList<Integer> temp_taskNumber = new ArrayList<Integer>();
					Iterator<String> keys3 = taskManagement.keySet().iterator();
					while (keys3.hasNext()) {

						String key = keys3.next();
						for (int i = 0; i < taskManagement.get(key).size(); i++) {
							System.out.println(taskManagement.size());
							temp_taskNumber.add(taskManagement.get(key).get(i).getTask_number());

						}
					}

					taskNumberList = taskInfoDao.oneSelectList(temp_taskNumber);
					for (int i = 0; i < taskNumberList.size(); i++) {
						System.out.println(taskNumberList.get(i).getTask_name());
					}
					session.setAttribute("taskInfoList", taskNumberList);

					///////////////////////////

					// 과제관리정보 불러오기
					// Map<과목코드,Map<과제번호,ArrayList<과제관리정보>>>
					Map<String, Map<Integer, ArrayList<TaskManagementInfo>>> taskManagement1 = new HashMap<String, Map<Integer, ArrayList<TaskManagementInfo>>>();
					ArrayList<TaskManagementInfo> info1 = new ArrayList<TaskManagementInfo>();

					TaskManagementDao taskManagementDao1 = (TaskManagementDao) sc.getAttribute("taskManagementDao");

					for (int i = 0; i < subj_code_div.size(); i++) {
						Map<Integer, ArrayList<TaskManagementInfo>> temp_taskManagement = new HashMap<Integer, ArrayList<TaskManagementInfo>>();
						for (int j = 0; j < taskNumberList.size(); j++) {
							if (subj_code_div.get(i).equals(taskNumberList.get(j).getSubj_code_div())) {
								info1 = (ArrayList<TaskManagementInfo>) taskManagementDao1
										.getInfo(taskNumberList.get(j).getTask_number());
								temp_taskManagement.put(taskNumberList.get(j).getTask_number(), info1);
							}
						}

						taskManagement1.put(subj_code_div.get(i), temp_taskManagement);
					}
					session.setAttribute("AlltaskManagement", taskManagement1);

					// System.out.println(taskManagement1);

					///////////////

					response.setContentType("text/html; charset=UTF-8");
					RequestDispatcher rd = request.getRequestDispatcher("/stu_main.jsp");
					rd.forward(request, response);

				} else if (auth.getAuth().equals("professor")) {

					ProfessorInfoDao profInfoDao = (ProfessorInfoDao) sc.getAttribute("proInfoDao");

					ProfessorInfo profInfo = (ProfessorInfo) session.getAttribute("profInfo");

					// 로그인 후 메인화면에 올 때,
					// 수강/강의중인 강의 정보를 갖고온다.
					int profId = profInfo.getProfId();
					SubjectInfoDao subjectInfoDao = (SubjectInfoDao) sc.getAttribute("subjectInfoDao");
					ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>) subjectInfoDao.getSubjInfo(profId);
					if (session.getAttribute("subjInfoList") == null) {
						session.setAttribute("subjInfoList", subjInfoList);
					}

					// 로그인 후 메인화면에 올 때,
					// 교수의 강의를 수강하는 학생 정보를 가져온다.
					ArrayList<String> subj_code_divList = new ArrayList<String>();

					// 교수가 강의하는 모든 과목코드를 가져온다.
					for (int i = 0; i < subjInfoList.size(); i++) {
						subj_code_divList.add(subjInfoList.get(i).getSubj_code_div());
					}

					// 교수가 강의하는 과목코드들에 맞는 학번을 배열로 가져온다.
					StudentSubjectDao studentSubjectDao = (StudentSubjectDao) sc.getAttribute("studentSubjectDao");
					Map<String, ArrayList<Integer>> code_stdIdList = (Map<String, ArrayList<Integer>>) studentSubjectDao
							.getInClassStdId(subj_code_divList);

					// 로그인 후 메인화면에 올 때,
					// 교수가 출제한 모든 과제 정보를 가져온다.
					// 교수의 과목이 파라메타.
					TaskInfoDao taskInfoDao = (TaskInfoDao) sc.getAttribute("taskInfoDao");
					ArrayList<String> subj_code_div = new ArrayList<String>();
					// Map<String,ArrayList<Integer>> subj_task_list = new
					// HashMap<String,ArrayList<Integer>>();
					for (int i = 0; i < subjInfoList.size(); i++) {
						subj_code_div.add(subjInfoList.get(i).getSubj_code_div());

					}
					List<TaskInfo> taskNumberList = taskInfoDao.allSelectList(subj_code_div);
					if (session.getAttribute("taskInfoList") == null) {
						session.setAttribute("pro_taskInfoList", taskNumberList);
					}

					// 과제관리정보 불러오기
					// Map<과목코드,Map<과제번호,ArrayList<과제관리정보>>>
					Map<String, Map<Integer, ArrayList<TaskManagementInfo>>> taskManagement = new HashMap<String, Map<Integer, ArrayList<TaskManagementInfo>>>();
					ArrayList<TaskManagementInfo> info = new ArrayList<TaskManagementInfo>();

					TaskManagementDao taskManagementDao = (TaskManagementDao) sc.getAttribute("taskManagementDao");

					for (int i = 0; i < subj_code_div.size(); i++) {
						Map<Integer, ArrayList<TaskManagementInfo>> temp_taskManagement = new HashMap<Integer, ArrayList<TaskManagementInfo>>();
						for (int j = 0; j < taskNumberList.size(); j++) {
							if (subj_code_div.get(i).equals(taskNumberList.get(j).getSubj_code_div())) {
								info = (ArrayList<TaskManagementInfo>) taskManagementDao
										.getInfo(taskNumberList.get(j).getTask_number());
								temp_taskManagement.put(taskNumberList.get(j).getTask_number(), info);
							}
						}

						taskManagement.put(subj_code_div.get(i), temp_taskManagement);
					}
					session.setAttribute("taskManagement", taskManagement);

					///////////////////////////

					// 과제관리정보 불러오기
					// Map<과목코드,Map<과제번호,ArrayList<과제관리정보>>>
					Map<String, Map<Integer, ArrayList<TaskManagementInfo>>> taskManagement1 = new HashMap<String, Map<Integer, ArrayList<TaskManagementInfo>>>();
					ArrayList<TaskManagementInfo> info1 = new ArrayList<TaskManagementInfo>();

					TaskManagementDao taskManagementDao1 = (TaskManagementDao) sc.getAttribute("taskManagementDao");

					for (int i = 0; i < subj_code_div.size(); i++) {
						Map<Integer, ArrayList<TaskManagementInfo>> temp_taskManagement1 = new HashMap<Integer, ArrayList<TaskManagementInfo>>();
						for (int j = 0; j < taskNumberList.size(); j++) {
							if (subj_code_div.get(i).equals(taskNumberList.get(j).getSubj_code_div())) {
								info1 = (ArrayList<TaskManagementInfo>) taskManagementDao1
										.getInfo(taskNumberList.get(j).getTask_number());
								temp_taskManagement1.put(taskNumberList.get(j).getTask_number(), info1);
							}
						}

						taskManagement1.put(subj_code_div.get(i), temp_taskManagement1);
					}
					session.setAttribute("AlltaskManagement", taskManagement1);
					System.out.println(taskManagement1);
					// System.out.println(taskManagement1);

					/*
					 * 과제관리정보 사용 요령
					 */
					// Iterator<String> keys =
					// taskManagement.keySet().iterator();
					// while( keys.hasNext() ){
					//
					// String key = keys.next();
					// //과목코드의 과제코드
					// Iterator<Integer> keys2 =
					// taskManagement.get(key).keySet().iterator();
					//
					// //과제코드 나열
					// while(keys2.hasNext()) {
					// int key2 = keys2.next();
					// for(int i=0 ; i<taskManagement.get(key).get(key2).size()
					// ; i++) {
					// // 여기서 출력
					// //교수의 과목코드당 과제당 학생의 과제관리정보
					// System.out.println(taskManagement.get(key).get(key2).get(i).getTask_comment());
					// }
					// }
					// }

					// 과목코드에 맞는 모든 학생정보를 출력해옴
					StudentInfoDao stdInfoDao = (StudentInfoDao) sc.getAttribute("stdInfoDao");
					Map<String, ArrayList<StudentInfo>> inClassStd = stdInfoDao.getInclassStd(code_stdIdList);
					session.setAttribute("inClassStd", inClassStd);

					// 로그인 후 메인화면에 올 때,
					// 교수가 보낸 모든 공지사항을 가져온다.
					// 교수의 id 파라메타.
					NoticeDao noticeDao = (NoticeDao) sc.getAttribute("noticeDao");
					ArrayList<Notice> noticeList = (ArrayList<Notice>) noticeDao.selectList(profId);
					session.setAttribute("noticeList", noticeList);

					ArrayList<Notice> temp_notice = new ArrayList<Notice>();
					for (int i = 0; i < noticeList.size(); i++) {
						temp_notice.add(noticeList.get(i));
					}
					// 최근 시간순서대로
					// 공지사항 정렬
					ArrayList<Notice> latestNotice = new ArrayList<Notice>();

					Date max = null;
					int idx = 0;
					int noticeSize = temp_notice.size();
					for (int j = 0; j < noticeSize; j++) {
						for (int k = 0; k < temp_notice.size(); k++) {
							int m = 0;
							if (k == 0) {
								max = temp_notice.get(k).getNoti_mod_date();
								idx = 0;
							} else {
								m = max.compareTo(temp_notice.get(k).getNoti_mod_date());
							}

							if (m < 0) {
								max = temp_notice.get(k).getNoti_mod_date();
								idx = k;
							}
						}
						latestNotice.add(temp_notice.get(idx));
						temp_notice.remove(idx);
					}
					session.setAttribute("latestNotice", latestNotice);

					response.setContentType("text/html; charset=UTF-8");
					RequestDispatcher rd = request.getRequestDispatcher("/pro_main.jsp");
					rd.forward(request, response);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				ServletContext sc = this.getServletContext();

				HttpSession session = (HttpSession) request.getSession();
				Auth auth = (Auth) session.getAttribute("auth");

				if (auth == null) {

					response.sendRedirect("login");

				} else {

					if (auth.getAuth().equals("student")) {

						StudentInfoDao stdInfoDao = (StudentInfoDao) sc.getAttribute("stdInfoDao");
						// request.setAttribute("stdInfo", stdInfoDao.selectList());

						StudentInfo stdInfo = (StudentInfo) session.getAttribute("stdInfo");
						int stdId = stdInfo.getStdId();

						// 로그인 후 메인화면에 올 때,
						// 학생이 수강하는 과목코드를 불러온다.
						StudentSubjectDao studentSubjectDao = (StudentSubjectDao) sc.getAttribute("studentSubjectDao");
						ArrayList<StudentSubject> studentSubject = (ArrayList<StudentSubject>) studentSubjectDao
								.getSubjCodeDivList(stdId);

						// 로그인 후 메인화면에 올 때,
						// 수강/강의중인 강의 정보를 갖고온다.
						SubjectInfoDao subjectInfoDao = (SubjectInfoDao) sc.getAttribute("subjectInfoDao");
						ArrayList<SubjectInfo> stdSubjList = (ArrayList<SubjectInfo>) subjectInfoDao
								.getSubjInfo(studentSubject);
						session.setAttribute("stdSubjList", stdSubjList);

						// 로그인 후 메인화면에 올 때,
						// 교수가 보낸 모든 공지사항을 가져온다.
						// 과목코드가 파라메타.
						NoticeDao noticeDao = (NoticeDao) sc.getAttribute("noticeDao");
						Map<String, ArrayList<Notice>> noticeList = noticeDao.selectList(studentSubject);
						session.setAttribute("noticeList", noticeList);

						// 최근 시간순서대로
						// 공지사항 정렬
						Iterator<String> keys = noticeList.keySet().iterator();
						ArrayList<Notice> latestNotice = new ArrayList<Notice>();
						ArrayList<Notice> temp_notice = new ArrayList<Notice>();
						while (keys.hasNext()) {
							String key = keys.next();
							for (int i = 0; i < noticeList.get(key).size(); i++) {
								temp_notice.add(noticeList.get(key).get(i));
							}
						}
						Date max = null;
						int idx = 0;
						int noticeSize = temp_notice.size();
						for (int j = 0; j < noticeSize; j++) {
							for (int k = 0; k < temp_notice.size(); k++) {
								int m = 0;
								if (k == 0) {
									max = temp_notice.get(k).getNoti_mod_date();
									idx = 0;
								} else {
									m = max.compareTo(temp_notice.get(k).getNoti_mod_date());
								}

								if (m < 0) {
									max = temp_notice.get(k).getNoti_mod_date();
									idx = k;
								}
							}
							latestNotice.add(temp_notice.get(idx));
							temp_notice.remove(idx);
						}
						session.setAttribute("latestNotice", latestNotice);

						ArrayList<String> subj_code_div = new ArrayList<String>();
						for (int i = 0; i < stdSubjList.size(); i++) {
							subj_code_div.add(stdSubjList.get(i).getSubj_code_div());
						}
						// 로그인 후 메인화면에 올 때,
						// 학생이 듣는 모든 과제정보를 가져온다.
						// 과목코드가 파라메타.
						TaskInfoDao taskInfoDao = (TaskInfoDao) sc.getAttribute("taskInfoDao");
						List<TaskInfo> taskNumberList = taskInfoDao.allSelectList(subj_code_div);

						session.setAttribute("AlltaskInfoList", taskNumberList);

						// 과제관리정보 불러오기
						// Map<과목코드,Map<과제번호,ArrayList<과제관리정보>>>
						Map<String, ArrayList<TaskManagementInfo>> taskManagement = new HashMap<String, ArrayList<TaskManagementInfo>>();
						TaskManagementInfo info = new TaskManagementInfo();

						TaskManagementDao taskManagementDao = (TaskManagementDao) sc.getAttribute("taskManagementDao");

						for (int i = 0; i < subj_code_div.size(); i++) {
							ArrayList<TaskManagementInfo> temp_taskManagement = new ArrayList<TaskManagementInfo>();
							for (int j = 0; j < taskNumberList.size(); j++) {
								if (subj_code_div.get(i).equals(taskNumberList.get(j).getSubj_code_div())) {
									info = (TaskManagementInfo) taskManagementDao
											.getInfo(taskNumberList.get(j).getTask_number(), stdId);
									temp_taskManagement.add(info);
								}
							}

							taskManagement.put(subj_code_div.get(i), temp_taskManagement);
						}
						session.setAttribute("taskManagement", taskManagement);

						ArrayList<Integer> temp_taskNumber = new ArrayList<Integer>();
						Iterator<String> keys3 = taskManagement.keySet().iterator();
						while (keys3.hasNext()) {

							String key = keys3.next();
							for (int i = 0; i < taskManagement.get(key).size(); i++) {
								System.out.println(taskManagement.size());
								temp_taskNumber.add(taskManagement.get(key).get(i).getTask_number());

							}
						}

						taskNumberList = taskInfoDao.oneSelectList(temp_taskNumber);
						for (int i = 0; i < taskNumberList.size(); i++) {
							System.out.println(taskNumberList.get(i).getTask_name());
						}
						session.setAttribute("taskInfoList", taskNumberList);

						///////////////////////////

						// 과제관리정보 불러오기
						// Map<과목코드,Map<과제번호,ArrayList<과제관리정보>>>
						Map<String, Map<Integer, ArrayList<TaskManagementInfo>>> taskManagement1 = new HashMap<String, Map<Integer, ArrayList<TaskManagementInfo>>>();
						ArrayList<TaskManagementInfo> info1 = new ArrayList<TaskManagementInfo>();

						TaskManagementDao taskManagementDao1 = (TaskManagementDao) sc.getAttribute("taskManagementDao");

						for (int i = 0; i < subj_code_div.size(); i++) {
							Map<Integer, ArrayList<TaskManagementInfo>> temp_taskManagement = new HashMap<Integer, ArrayList<TaskManagementInfo>>();
							for (int j = 0; j < taskNumberList.size(); j++) {
								if (subj_code_div.get(i).equals(taskNumberList.get(j).getSubj_code_div())) {
									info1 = (ArrayList<TaskManagementInfo>) taskManagementDao1
											.getInfo(taskNumberList.get(j).getTask_number());
									temp_taskManagement.put(taskNumberList.get(j).getTask_number(), info1);
								}
							}

							taskManagement1.put(subj_code_div.get(i), temp_taskManagement);
						}
						session.setAttribute("AlltaskManagement", taskManagement1);

						// System.out.println(taskManagement1);

						///////////////

						response.setContentType("text/html; charset=UTF-8");
						RequestDispatcher rd = request.getRequestDispatcher("/stu_main.jsp");
						rd.forward(request, response);

					} else if (auth.getAuth().equals("professor")) {

						ProfessorInfoDao profInfoDao = (ProfessorInfoDao) sc.getAttribute("proInfoDao");

						ProfessorInfo profInfo = (ProfessorInfo) session.getAttribute("profInfo");

						// 로그인 후 메인화면에 올 때,
						// 수강/강의중인 강의 정보를 갖고온다.
						int profId = profInfo.getProfId();
						SubjectInfoDao subjectInfoDao = (SubjectInfoDao) sc.getAttribute("subjectInfoDao");
						ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>) subjectInfoDao.getSubjInfo(profId);
						if (session.getAttribute("subjInfoList") == null) {
							session.setAttribute("subjInfoList", subjInfoList);
						}

						// 로그인 후 메인화면에 올 때,
						// 교수의 강의를 수강하는 학생 정보를 가져온다.
						ArrayList<String> subj_code_divList = new ArrayList<String>();

						// 교수가 강의하는 모든 과목코드를 가져온다.
						for (int i = 0; i < subjInfoList.size(); i++) {
							subj_code_divList.add(subjInfoList.get(i).getSubj_code_div());
						}

						// 교수가 강의하는 과목코드들에 맞는 학번을 배열로 가져온다.
						StudentSubjectDao studentSubjectDao = (StudentSubjectDao) sc.getAttribute("studentSubjectDao");
						Map<String, ArrayList<Integer>> code_stdIdList = (Map<String, ArrayList<Integer>>) studentSubjectDao
								.getInClassStdId(subj_code_divList);

						// 로그인 후 메인화면에 올 때,
						// 교수가 출제한 모든 과제 정보를 가져온다.
						// 교수의 과목이 파라메타.
						TaskInfoDao taskInfoDao = (TaskInfoDao) sc.getAttribute("taskInfoDao");
						ArrayList<String> subj_code_div = new ArrayList<String>();
						// Map<String,ArrayList<Integer>> subj_task_list = new
						// HashMap<String,ArrayList<Integer>>();
						for (int i = 0; i < subjInfoList.size(); i++) {
							subj_code_div.add(subjInfoList.get(i).getSubj_code_div());

						}
						List<TaskInfo> taskNumberList = taskInfoDao.allSelectList(subj_code_div);
						if (session.getAttribute("taskInfoList") == null) {
							session.setAttribute("pro_taskInfoList", taskNumberList);
						}

						// 과제관리정보 불러오기
						// Map<과목코드,Map<과제번호,ArrayList<과제관리정보>>>
						Map<String, Map<Integer, ArrayList<TaskManagementInfo>>> taskManagement = new HashMap<String, Map<Integer, ArrayList<TaskManagementInfo>>>();
						ArrayList<TaskManagementInfo> info = new ArrayList<TaskManagementInfo>();

						TaskManagementDao taskManagementDao = (TaskManagementDao) sc.getAttribute("taskManagementDao");

						for (int i = 0; i < subj_code_div.size(); i++) {
							Map<Integer, ArrayList<TaskManagementInfo>> temp_taskManagement = new HashMap<Integer, ArrayList<TaskManagementInfo>>();
							for (int j = 0; j < taskNumberList.size(); j++) {
								if (subj_code_div.get(i).equals(taskNumberList.get(j).getSubj_code_div())) {
									info = (ArrayList<TaskManagementInfo>) taskManagementDao
											.getInfo(taskNumberList.get(j).getTask_number());
									temp_taskManagement.put(taskNumberList.get(j).getTask_number(), info);
								}
							}

							taskManagement.put(subj_code_div.get(i), temp_taskManagement);
						}
						session.setAttribute("taskManagement", taskManagement);

						///////////////////////////

						// 과제관리정보 불러오기
						// Map<과목코드,Map<과제번호,ArrayList<과제관리정보>>>
						Map<String, Map<Integer, ArrayList<TaskManagementInfo>>> taskManagement1 = new HashMap<String, Map<Integer, ArrayList<TaskManagementInfo>>>();
						ArrayList<TaskManagementInfo> info1 = new ArrayList<TaskManagementInfo>();

						TaskManagementDao taskManagementDao1 = (TaskManagementDao) sc.getAttribute("taskManagementDao");

						for (int i = 0; i < subj_code_div.size(); i++) {
							Map<Integer, ArrayList<TaskManagementInfo>> temp_taskManagement1 = new HashMap<Integer, ArrayList<TaskManagementInfo>>();
							for (int j = 0; j < taskNumberList.size(); j++) {
								if (subj_code_div.get(i).equals(taskNumberList.get(j).getSubj_code_div())) {
									info1 = (ArrayList<TaskManagementInfo>) taskManagementDao1
											.getInfo(taskNumberList.get(j).getTask_number());
									temp_taskManagement1.put(taskNumberList.get(j).getTask_number(), info1);
								}
							}

							taskManagement1.put(subj_code_div.get(i), temp_taskManagement1);
						}
						session.setAttribute("AlltaskManagement", taskManagement1);
						System.out.println(taskManagement1);
						// System.out.println(taskManagement1);

						/*
						 * 과제관리정보 사용 요령
						 */
						// Iterator<String> keys =
						// taskManagement.keySet().iterator();
						// while( keys.hasNext() ){
						//
						// String key = keys.next();
						// //과목코드의 과제코드
						// Iterator<Integer> keys2 =
						// taskManagement.get(key).keySet().iterator();
						//
						// //과제코드 나열
						// while(keys2.hasNext()) {
						// int key2 = keys2.next();
						// for(int i=0 ; i<taskManagement.get(key).get(key2).size()
						// ; i++) {
						// // 여기서 출력
						// //교수의 과목코드당 과제당 학생의 과제관리정보
						// System.out.println(taskManagement.get(key).get(key2).get(i).getTask_comment());
						// }
						// }
						// }

						// 과목코드에 맞는 모든 학생정보를 출력해옴
						StudentInfoDao stdInfoDao = (StudentInfoDao) sc.getAttribute("stdInfoDao");
						Map<String, ArrayList<StudentInfo>> inClassStd = stdInfoDao.getInclassStd(code_stdIdList);
						session.setAttribute("inClassStd", inClassStd);

						// 로그인 후 메인화면에 올 때,
						// 교수가 보낸 모든 공지사항을 가져온다.
						// 교수의 id 파라메타.
						NoticeDao noticeDao = (NoticeDao) sc.getAttribute("noticeDao");
						ArrayList<Notice> noticeList = (ArrayList<Notice>) noticeDao.selectList(profId);
						session.setAttribute("noticeList", noticeList);

						ArrayList<Notice> temp_notice = new ArrayList<Notice>();
						for (int i = 0; i < noticeList.size(); i++) {
							temp_notice.add(noticeList.get(i));
						}
						// 최근 시간순서대로
						// 공지사항 정렬
						ArrayList<Notice> latestNotice = new ArrayList<Notice>();

						Date max = null;
						int idx = 0;
						int noticeSize = temp_notice.size();
						for (int j = 0; j < noticeSize; j++) {
							for (int k = 0; k < temp_notice.size(); k++) {
								int m = 0;
								if (k == 0) {
									max = temp_notice.get(k).getNoti_mod_date();
									idx = 0;
								} else {
									m = max.compareTo(temp_notice.get(k).getNoti_mod_date());
								}

								if (m < 0) {
									max = temp_notice.get(k).getNoti_mod_date();
									idx = k;
								}
							}
							latestNotice.add(temp_notice.get(idx));
							temp_notice.remove(idx);
						}
						session.setAttribute("latestNotice", latestNotice);

						response.setContentType("text/html; charset=UTF-8");
						RequestDispatcher rd = request.getRequestDispatcher("/pro_main.jsp");
						rd.forward(request, response);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error", e);
				RequestDispatcher rd = request.getRequestDispatcher("login");
				rd.forward(request, response);
			}
	}
}
