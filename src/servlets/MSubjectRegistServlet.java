package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StudentSubjectDao;
import dao.SubjectInfoDao;

@WebServlet("/msubjectregist")
public class MSubjectRegistServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		String regist_code = "";
		String subj_code_div = "";
		int id = 0;
		regist_code = request.getParameter("regist_code");
		subj_code_div = request.getParameter("subj_code_div");
		id = Integer.parseInt(request.getParameter("id"));
		System.out.println(regist_code);

		try {
			ServletContext sc = this.getServletContext();

			HttpSession session = (HttpSession) request.getSession();

			if(regist_code == "") {

				PrintWriter out = response.getWriter();
				out.print(0);
				out.close();


			} else {

				
				// 과목 가입 페이지에서
				// 가입가능한 과목정보를 불러온다.
				SubjectInfoDao subjectInfoDao = (SubjectInfoDao) sc.getAttribute("subjectInfoDao");
				int result = subjectInfoDao.subjectRegistCheck(subj_code_div, regist_code);
				
				StudentSubjectDao studentSubjectDao = (StudentSubjectDao)sc.getAttribute("studentSubjectDao");	
				System.out.println(id + subj_code_div);
				int result2 = studentSubjectDao.subjectRegist(id, subj_code_div);
				System.out.println(result2);
				
				PrintWriter out = response.getWriter();
				out.print(result2);
				out.close();

			}


		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}

