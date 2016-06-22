package servlets;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.SubjectInfoDao;
import vo.Auth;
import vo.ProfessorInfo;
import vo.SubjectInfo;


// pro_subject_fileUpload.jsp 에서 업로드 후 이쪽으로 옴
@WebServlet("/subjectCreate")
public class SubjectCreateServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		//System.out.println("WAegawehrherh");
		//				RequestDispatcher rd = request.getRequestDispatcher(
		//						"/pro_create_subject.jsp");
		//				rd.forward(request, response);
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

			String uploadPath =  "C:/Users/b10310/git/web/PRsystem/WebContent/Fileupload/";
//			String uploadPath =  "C:/Users/S_se/workspace/PRsystem/WebContent/Fileupload/";
			//		String uploadPath = "C:/eclipse/work/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/PRsystem/Fileupload";
			int size = 10*1024*1024;

			String register_code="";
			String sub_divide="";
			String sub_code ="";
			String sub_name = "";
			Integer limit_num = 0;
			String sub_description ="";
			String filename1 = "";
			String finish = "";
			String oFile1 = "";
			
			try{
				MultipartRequest multi = new MultipartRequest(
						request,
						uploadPath,
						size,
						"utf-8",
						new DefaultFileRenamePolicy()
						); /* MultipartRequest multi 생성되는 순간, 파일은 미리 업로드 되어있을 것임*/		
				register_code = multi.getParameter("register_code");		
				sub_divide = multi.getParameter("sub_divide");	
				sub_code = multi.getParameter("sub_code");
				sub_name = multi.getParameter("sub_name");
				limit_num = Integer.parseInt(multi.getParameter("limit_num"));
				sub_description = multi.getParameter("sub_description");
				finish = sub_code+"_"+sub_divide;


				Enumeration files = multi.getFileNames(); // file1. file2 라고 저장된 사진이 이곳에..
				//			
				String file1 = (String)files.nextElement(); // 첫번째 file인 file1을 파일로 만듦
				//			filename1 = multi.getFilesystemName(finish);
				//			oFile1 = multi.getOriginalFileName(finish);

				int maxSize = 50 * 1024 * 1024;  //파일 사이즈


				String fileName = multi.getFilesystemName(file1);  

				int i = -1;
				i = fileName.lastIndexOf("."); // 파일 확장자 위치
				 filename1 = finish + fileName.substring(i, fileName.length());  //과목 코드 + 확장자

				File oldFile = new File(uploadPath + fileName);
				File newFile = new File(uploadPath + filename1); 

				oldFile.renameTo(newFile); // 파일명 변경



//				System.out.println("---------------------");
//				System.out.println(files);
//				System.out.println(file1);
//				System.out.println(filename1);
//				System.out.println(oFile1);
//				System.out.println("---------------------");
			}catch(Exception ex){

			}

			if(auth.equals("professor")) {

				//세션에서 교수정보 얻음
				ProfessorInfo profInfo = (ProfessorInfo) session.getAttribute("profInfo");


				SubjectInfoDao subjectInfoDao = (SubjectInfoDao) sc.getAttribute("subjectInfoDao");
				subjectInfoDao.insert(
						new SubjectInfo().setSubj_code_div(finish)
						.setSubj_name(sub_name)
						.setSubj_outline(sub_description)
						.setSubj_image(filename1)
						.setLimit_num(limit_num)
						.setRegist_code(register_code)	
						.setProf_id(profInfo.getProfId())
						);


				// 로그인 후 메인화면에 올 때,
				// 수강/강의중인 강의 정보를 갖고온다.
				session.removeAttribute("subjInfoList");	
				response.sendRedirect("main");
				//				RequestDispatcher rd = request.getRequestDispatcher("");
				//				rd.forward(request, response);
			} else {
				//// 
			}

		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("login");
			rd.forward(request, response);

		}


	}
}
