package servlets;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import Push.GCMUtil;
import Push.GCMVo;
import dao.StudentSubjectDao;
import dao.SubjectInfoDao;
import dao.TaskInfoDao;
import dao.TaskManagementDao;
import vo.Auth;
import vo.Notice;
import vo.StudentInfo;
import vo.TaskInfo;
import vo.SubjectInfo;

@WebServlet("/taskcreate")
public class TaskCreateServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

				RequestDispatcher rd = request.getRequestDispatcher(
						"/pro_task_create.jsp");
				rd.forward(request, response);
	}
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {


		try {
			ServletContext sc = this.getServletContext();

			HttpSession session = request.getSession();
			Auth getAuth = (Auth) session.getAttribute("auth");
			String auth =getAuth.getAuth();
			
			// 과제를 만들기 위해서는
			// 교수가 만든 과목 (과목코드) 가 있어야 한다.
			

			if(auth.equals("professor")) {
				SubjectInfoDao subjectInfoDao = (SubjectInfoDao) sc.getAttribute("subjectInfoDao");
				ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>)session.getAttribute("subjInfoList");

				ArrayList<String> list = new ArrayList<String>();
				//	list = "받은과목";//////////////////////////////////////////////////////////////

				//				list.add(request.getParameter("notify"));


				TaskInfoDao taskInfoDao = (TaskInfoDao) sc.getAttribute("taskInfoDao");
				
				// 날짜값이 넘어오면 포멧을 바꿔 java.util.Date 형식으로 바꾼 후,
				// mysql에 넣기위해 다시 java.sql.Date 형식으로 바꾸어 준다.
				DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
				Date temp_endDate = format.parse(request.getParameter("task_end"));
				
				java.sql.Date endDate = new java.sql.Date(temp_endDate.getTime());
				
				TaskInfo taskInfo = new TaskInfo().setTask_name(request.getParameter("task_name"))/////////////////////////
						.setTask_content(request.getParameter("task_description").replaceAll("\r\n","<br/>"))
						.setSubj_code_div(request.getParameter("Subj_code_div"))
						.setTask_end(endDate)
						//.setTask_group_num(request.getParameter("task_num"))
						;
				taskInfoDao.insert(taskInfo);
				
				// 교수가 강의하는 과목코드들에 맞는 학번을 배열로 가져온다.
				StudentSubjectDao studentSubjectDao = (StudentSubjectDao) sc.getAttribute("studentSubjectDao");
				ArrayList<Integer>  stdIdList = studentSubjectDao.getStdId(request.getParameter("Subj_code_div"));
				int num = taskInfoDao.getLastNum();
				
				// 과제생성에 의해 task_management 테이블에 과목수강중인 학생들이 추가된다.
				TaskManagementDao taskManagementDao = (TaskManagementDao)sc.getAttribute("taskManagementDao");
				taskManagementDao.insert(num, stdIdList);
				

				

				String code = request.getParameter("Subj_code_div");
				String name = request.getParameter("task_name");
				System.out.println(request.getSession().getServletContext().getRealPath(""));
				File dir = new File("C:/Users/b10310/git/web/PRsystem/WebContent/code_similarity/" + code+ "_" + name);

				dir.mkdirs();

				Map<String,ArrayList<StudentInfo>> inClassStd = (Map<String,ArrayList<StudentInfo>>)session.getAttribute("inClassStd");
				Iterator<String> keys = inClassStd.keySet().iterator();
				
				for(int i=0; i<subjInfoList.size();i++){
					String Subj_name = subjInfoList.get(i).getSubj_code_div();
					String Subj_code_div = request.getParameter("Subj_code_div");
					
					if(Subj_name.equals(Subj_code_div)) {
						
						//전달할 PUSH 내용
					    String title = "'"+subjInfoList.get(i).getSubj_name()+"'"+"과목 공지가 등록되었습니다.";
					    String msg = request.getParameter("task_name");
					    
					    // GCM 정보 셋팅
					    GCMVo gcmVo = new GCMVo();
					    gcmVo.setTitle(title);
					    gcmVo.setMsg(msg);
					    gcmVo.setTypeCode("");
					    
					    // GCM reg id 셋팅
					    List<String> reslist = new ArrayList<String>();
					    while( keys.hasNext() ){   
							String key = keys.next();
							
							if(Subj_code_div.equals(key)) {
								System.out.println(key);
								for(int k=0; k<inClassStd.get(key).size(); k++){
									System.out.println(inClassStd.get(key).get(k).getStdToken());
									if(inClassStd.get(key).get(k).getStdToken().equals("a")){
										
									}else{
										reslist.add(inClassStd.get(key).get(k).getStdToken());
									}
								}
							}			
						}
					    GCMUtil gcmUtil = new GCMUtil(reslist, gcmVo);
					    System.out.println("성공");
					    break;
					}
					else
						System.out.println("실패");
				}
				
				//과제추가해야되서 기존 세션 삭제
				session.removeAttribute("taskInfoList");

				// 과제를 추가해서세션을 다시 추가해야한다.
				response.sendRedirect("main");
				
			} else {
				response.sendRedirect("main");
			}

		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("login");
			rd.forward(request, response);

		}
	}
}
