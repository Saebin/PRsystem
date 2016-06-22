
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

		

		// 최종적으로 배열을 하나로 묶음
		jKind.put("subject", jArray);
		// 안드로이드에 보낼 데이터를 출력
		out.println(jKind.toJSONString());
	
%>