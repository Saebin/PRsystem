
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="org.json.simple.*" 
						 import="java.util.ArrayList"%>
<%@
	page import="java.util.*" import="vo.SubjectInfo"
	%>
<%
	ArrayList<SubjectInfo> subjRegiList = (ArrayList<SubjectInfo>) request.getAttribute("subjRegiList");

	JSONArray jArray = new JSONArray();
	
	JSONObject jKind = new JSONObject();

	for(int i=0; i<subjRegiList.size(); i++) {
		JSONObject jObject = new JSONObject();
		jObject.put("subj_code_div", subjRegiList.get(i).getSubj_code_div());
		jObject.put("subj_name", subjRegiList.get(i).getSubj_name());
		jObject.put("subj_image", subjRegiList.get(i).getSubj_image());
		jObject.put("subj_outline", subjRegiList.get(i).getSubj_outline());
		jObject.put("curr_num", subjRegiList.get(i).getCurr_num());
		jObject.put("limit_num", subjRegiList.get(i).getLimit_num());
		// 위에서 만든 각각의 객체를 하나의 배열 형태로 만듬
		jArray.add(jObject);
	}


		// 최종적으로 배열을 하나로 묶음
		jKind.put("subject", jArray);
		
		// 안드로이드에 보낼 json 데이터를 출력
		out.println(jKind.toJSONString());
	
%>