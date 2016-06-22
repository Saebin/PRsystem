package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StudentSubjectDao;
import dao.SubjectInfoDao;
import vo.SubjectInfo;
import vo.TaskInfo;

@WebServlet("/subjectregist")
public class SubjectRegistServlet extends HttpServlet {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out1 = response.getWriter();
		String regist_code = "";
		String subj_code_div = "";
		String confirm = "N";
		String tt = "Y";
		String regist = "";
		int id = 0;
		regist_code = request.getParameter("regist_code");
		subj_code_div = request.getParameter("subj_code_div");
		confirm = request.getParameter("confirm");
		regist = request.getParameter("regist");
		id = Integer.parseInt(request.getParameter("num"));
		String aa = "sccess";
		
		/*
		 * System.out.println(regist_code); System.out.println(subj_code_div);
		 * System.out.println(id);
		 */

		try {
			ServletContext sc = this.getServletContext();

			HttpSession session = (HttpSession) request.getSession();

			if (regist_code == "") {

			} else {

				// 과목 가입 페이지에서
				// 가입가능한 과목정보를 불러온다.
				SubjectInfoDao subjectInfoDao = (SubjectInfoDao) sc.getAttribute("subjectInfoDao");
				int result = subjectInfoDao.WebsubjectRegistCheck(subj_code_div, regist_code);
				session.setAttribute("subj_code_div", subj_code_div);
				session.setAttribute("regist_code", regist_code);
				session.setAttribute("fail", aa);
				if (result == 1) {
					
					if(confirm.equals(tt)){
						int re = subjectInfoDao.WebsubjectRegistCheck01(regist);
						//System.out.println(re);
						if(re == 1){
							subj_code_div = regist_code + "_" + subj_code_div;
							StudentSubjectDao studentSubjectDao = (StudentSubjectDao) sc.getAttribute("studentSubjectDao");
							System.out.println(id + subj_code_div);
							int result2 = studentSubjectDao.WebsubjectRegist(id, subj_code_div);
							System.out.println(result2);
							
							
							response.sendRedirect("main");
							
							
						} else{
							
							session.setAttribute("fail", "fail");
							response.sendRedirect("stu_subject_add_confirm.jsp");
						}
						
					}
					
					SubjectInfoDao subjectInfoDao1 = (SubjectInfoDao) sc.getAttribute("subjectInfoDao");
					ArrayList<SubjectInfo> subjRegiList = (ArrayList<SubjectInfo>) subjectInfoDao1.subjectRegistList();
					for(int i=0; i<subjRegiList.size(); i++){
						if(subjRegiList.get(i).getSubj_code_div().equals(regist_code + "_" + subj_code_div)){
							ArrayList<SubjectInfo> select_subjRegiList = new ArrayList<SubjectInfo>();
							select_subjRegiList.add(subjRegiList.get(i));
							session.setAttribute("select_subjRegiList", select_subjRegiList);
						}
						
					}
					
					
					response.sendRedirect("stu_subject_add_confirm.jsp");

				} else {

					out1.println("<script language='javascript'>"); 
					out1.println("alert('NO Subject');");
					out1.println("history.back();");
					out1.println("</script>");

				}
				
				
			}

		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("login");
			rd.forward(request, response);

		}
	}
}