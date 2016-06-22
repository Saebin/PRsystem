package servlets.update;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SubjectInfoDao;
import dao.TaskInfoDao;
import vo.Auth;
import vo.ProfessorInfo;
import vo.SubjectInfo;
import vo.TaskInfo;


@WebServlet("/taskupdate")
public class TaskUpdateServlet extends HttpServlet {
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

			System.out.println(Integer.parseInt(request.getParameter("task_num")));
			System.out.println(request.getParameter("task_outline"));
			System.out.println(request.getParameter("task_name"));
			System.out.println(request.getParameter("task_start"));
			System.out.println(request.getParameter("task_end"));
			
			if(auth.getAuth().equals("professor")) {


				TaskInfoDao taskInfoDao = (TaskInfoDao)sc.getAttribute("taskInfoDao");
				
				// 날짜값이 넘어오면 포멧을 바꿔 java.util.Date 형식으로 바꾼 후,
				// mysql에 넣기위해 다시 java.sql.Date 형식으로 바꾸어 준다.
				DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
				Date temp_endDate = format.parse(request.getParameter("task_end"));
				
				java.sql.Date endDate = new java.sql.Date(temp_endDate.getTime());

				TaskInfo taskInfoList = new TaskInfo().setTask_number(Integer.parseInt(request.getParameter("task_num")))
													  .setTask_content(request.getParameter("task_outline"))
													  .setTask_name(request.getParameter("task_name"))
													  .setTask_end((java.sql.Date) endDate);
															

				taskInfoDao.update(taskInfoList);
			

				session.removeAttribute("pro_taskInfoList");
				
				ProfessorInfo profInfo = (ProfessorInfo) session.getAttribute("profInfo");
				int profId = profInfo.getProfId();
				SubjectInfoDao subjectInfoDao = (SubjectInfoDao) sc.getAttribute("subjectInfoDao");
				ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>) subjectInfoDao.getSubjInfo(profId);
				if (session.getAttribute("subjInfoList") == null) {
					session.setAttribute("subjInfoList", subjInfoList);
				}
				
				TaskInfoDao taskInfoDao1 = (TaskInfoDao) sc.getAttribute("taskInfoDao");
				ArrayList<String> subj_code_div = new ArrayList<String>();
				// Map<String,ArrayList<Integer>> subj_task_list = new
				// HashMap<String,ArrayList<Integer>>();
				for (int i = 0; i < subjInfoList.size(); i++) {
					subj_code_div.add(subjInfoList.get(i).getSubj_code_div());

				}
				List<TaskInfo> taskNumberList = taskInfoDao1.allSelectList(subj_code_div);
				if (session.getAttribute("taskInfoList") == null) {
					session.setAttribute("pro_taskInfoList", taskNumberList);
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
