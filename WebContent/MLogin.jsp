
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="org.json.simple.*"
	import="vo.ProfessorInfo" import="java.util.ArrayList"
	import="vo.StudentInfo"%>

<%
	StudentInfo stdInfo = (StudentInfo) request.getAttribute("stdInfo");
	ProfessorInfo profInfo = (ProfessorInfo) request.getAttribute("profInfo");

	JSONArray jArray = new JSONArray();
	JSONObject jObject = new JSONObject();
	JSONObject jKind = new JSONObject();

	if (profInfo != null) {

		jObject.put("email", profInfo.getProfEmail());
		jObject.put("name", profInfo.getProfName());
		jObject.put("auth", profInfo.getProfAuth());
		jObject.put("id", profInfo.getProfId());
		// 위에서 만든 각각의 객체를 하나의 배열 형태로 만듬
		jArray.add(jObject);

		// 최종적으로 배열을 하나로 묶음
		jKind.put("login", jArray);

		// 안드로이드에 보낼 데이터를 출력
		out.println(jKind.toJSONString());
	} else if (stdInfo != null) {
		jObject.put("email", stdInfo.getStdEmail());
		jObject.put("name", stdInfo.getStdName());
		jObject.put("auth", stdInfo.getStdAuth());
		jObject.put("id", stdInfo.getStdId());
		// 위에서 만든 각각의 객체를 하나의 배열 형태로 만듬
		jArray.add(jObject);

		// 최종적으로 배열을 하나로 묶음
		jKind.put("login", jArray);

		// 안드로이드에 보낼 데이터를 출력
		out.println(jKind.toJSONString());
	}
%>