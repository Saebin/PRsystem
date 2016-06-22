
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="org.json.simple.*" 
						 import="java.util.ArrayList"%>
<%@
	page import="java.util.*" import="vo.SubjectInfo"
	import="vo.Notice" import="vo.TaskInfo"
					   import="java.text.SimpleDateFormat"
					   import="java.util.Date"%>
<%


	ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>) request.getAttribute("subjInfoList");
	ArrayList<Notice> noticeList = (ArrayList<Notice>) request.getAttribute("noticeList");
	ArrayList<TaskInfo> taskInfoList = (ArrayList<TaskInfo>) request.getAttribute("taskInfoList");
	
	JSONArray jArray = new JSONArray();
	JSONArray jArray2 = new JSONArray();
	
	JSONObject jKind = new JSONObject();

	for(int i=0; i<subjInfoList.size(); i++) {
		JSONObject jObject = new JSONObject();
		jObject.put("subj_code_div", subjInfoList.get(i).getSubj_code_div());
		jObject.put("subj_name", subjInfoList.get(i).getSubj_name());
		jObject.put("subj_image", subjInfoList.get(i).getSubj_image());
		jObject.put("subj_outline", subjInfoList.get(i).getSubj_outline());
		// 위에서 만든 각각의 객체를 하나의 배열 형태로 만듬
		jArray.add(jObject);
	}
	SimpleDateFormat transFormat = new SimpleDateFormat("MM-dd");
	for(int i=0; i<noticeList.size(); i++) {
		JSONObject jObject = new JSONObject();
		
		// 파싱은 문자열로해야하므로 데이터 변수를 STring 변수로 바꾸어준다.
	    
	
		jObject.put("noti_number", noticeList.get(i).getNoti_number());
		jObject.put("noti_comment", noticeList.get(i).getNoti_comment());
		jObject.put("subj_code_div", noticeList.get(i).getSubj_code_div());
		jObject.put("prof_id", noticeList.get(i).getProf_id());
		jObject.put("noti_cre_date", transFormat.format(noticeList.get(i).getNoti_cre_date()));
		jObject.put("noti_mod_date", transFormat.format(noticeList.get(i).getNoti_mod_date()));
		jObject.put("noti_name", noticeList.get(i).getNoti_name());
		jObject.put("noti_del", noticeList.get(i).getNoti_del());
		// 위에서 만든 각각의 객체를 하나의 배열 형태로 만듬
		jArray2.add(jObject);
	}

		

		// 최종적으로 배열을 하나로 묶음
		jKind.put("subject", jArray);
		jKind.put("notice", jArray2);
		// 안드로이드에 보낼 데이터를 출력
		out.println(jKind.toJSONString());
	
%>