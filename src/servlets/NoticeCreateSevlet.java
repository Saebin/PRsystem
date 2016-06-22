package servlets;

import java.io.IOException;
import java.util.ArrayList;
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
import javax.websocket.SendResult;

import Push.GCMUtil;
import Push.GCMVo;
import dao.NoticeDao;
import dao.SubjectInfoDao;
import vo.Auth;
import vo.Notice;
import vo.ProfessorInfo;
import vo.StudentInfo;
import vo.SubjectInfo;

@WebServlet("/noticeCreate")
public class NoticeCreateSevlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

				RequestDispatcher rd = request.getRequestDispatcher(
						"/pro_notify_write.jsp");
				
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
			
			// 공지사항을 만들기 위해서는
			// 교수가 만든 과목 (과목코드) 이 있어야 하고,
			// 교수의 교번이 필요하다.
			
			// 과목이 여러개일 것인데, 웹에서 선택한 것으로 한다.

			if(auth.equals("professor")) {
				SubjectInfoDao subjectInfoDao = (SubjectInfoDao) sc.getAttribute("subjectInfoDao");
				ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>)session.getAttribute("subjInfoList");
				ArrayList<String> subj_code_divList = new ArrayList<String>();




				ProfessorInfo profInfo = (ProfessorInfo) session.getAttribute("profInfo");
				//이런 ;;
				//과목 선택수만큼 갯수를  디비에 insert ...
				NoticeDao noticeDao = (NoticeDao) sc.getAttribute("noticeDao");
				String[] list = request.getParameterValues("Subj_name");
				for(int i=0; i<list.length ; i++) {
					//System.out.println(list[i]);
					Notice notice = new Notice().setNoti_comment(request.getParameter("smarteditor"))/////////////////////////
							.setProf_id(profInfo.getProfId())
							.setSubj_code_div(list[i])
							.setNoti_name(request.getParameter("name"));
					noticeDao.insert(notice);
				}
				
				Map<String,ArrayList<StudentInfo>> inClassStd = (Map<String,ArrayList<StudentInfo>>)session.getAttribute("inClassStd");
				Iterator<String> keys = inClassStd.keySet().iterator();

				

				for(int i=0; i<subjInfoList.size();i++){
					String Subj_name = subjInfoList.get(i).getSubj_code_div();
					String Subj_code_div = request.getParameter("Subj_name");
					
					if(Subj_name.equals(Subj_code_div)) {

						//전달할 PUSH 내용
						String title = "'"+subjInfoList.get(i).getSubj_name()+"'"+"과목 공지가 등록되었습니다.";
						String msg = request.getParameter("name");

						// GCM 정보 셋팅
						GCMVo gcmVo = new GCMVo();
						gcmVo.setTitle(title);
						gcmVo.setMsg(msg);
						gcmVo.setTypeCode("");

						// GCM reg id 셋팅
						List<String> reslist = new ArrayList<String>();
						
						for(int j=0; j<list.length ; j++) {
							//System.out.println(list[j]);
							while( keys.hasNext() ){   
								String key = keys.next();
								
								if(list[j].equals(key)) {
									System.out.println(key);
									for(int k=0; k<inClassStd.get(key).size(); k++){
										System.out.println(inClassStd.get(key).size());
										System.out.println(inClassStd.get(key).get(k).getStdId());
										if(inClassStd.get(key).get(k).getStdToken().equals("a")){
											
										}else{
											reslist.add(inClassStd.get(key).get(k).getStdToken());
										}
									}
								}			
							}
						}
						    
						//reslist.add("c5odzPFU5NA:APA91bGaNanjFkUB3FuF5z_PuvmsG6CpWobibfNdY81-huhQDF-inGefMxJKk3xFSS5K_2XjZCeQ5vf01yF3tsIsRXxDth5II_aCLME9jR16CISug9ZVcVO43WqF4NNxjvFmIPSPRIT3");
						//reslist.add("eRWItDq2z04:APA91bHNGx9CU6R6nsxJjqZUjxwKnu-IULSKgbvGxri4ERqtizCvH9nhI4_5UebSZgjg7OlAkudZ2OT2q02oedtIDw5lJrYqQcKwbW6qpBgvjNnQVlhpXJeALqcjkaiZguyiq3yskjZT");
						GCMUtil gcmUtil = new GCMUtil(reslist, gcmVo);
						break;
					}
					else
						System.out.println("실패");
				}
				//공지추가해야되서 기존 세션 삭제
				session.removeAttribute("noticeList");

				// 공지사항을 추가해서세션을 다시 추가해야한다.
				response.sendRedirect("noticeview");
				
				
				
			} else {
				
			}
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("login");
			rd.forward(request, response);

		}


	}
}
