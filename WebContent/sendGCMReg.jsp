<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 
    System.out.println("*********************************************************");
    System.out.println("REG 획득");
    System.out.println("KEY : " + request.getParameter("KEY"));
    System.out.println("REG : " + request.getParameter("REG"));
    System.out.println("*********************************************************");
    
    Map rtnMap = new HashMap();
    
    rtnMap.put("DATA", URLEncoder.encode("등록되었습니다.", "UTF-8"));
    rtnMap.put("DATA1", "END1");
    out.print(rtnMap.toString());
 
%>
