<%@page import="Push.GCMUtil" %>
<%@page import="Push.GCMVo" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
    //전달할 PUSH 내용
    String title = "정시설계 공지가 등록되었습니다";
    String msg = "1차과제 연장";
    
    // GCM 정보 셋팅
    GCMVo gcmVo = new GCMVo();
    gcmVo.setTitle(title);
    gcmVo.setMsg(msg);
    gcmVo.setTypeCode("");
    
    // GCM reg id 셋팅
    List<String> reslist = new ArrayList<String>();
    reslist.add("c5odzPFU5NA:APA91bGaNanjFkUB3FuF5z_PuvmsG6CpWobibfNdY81-huhQDF-inGefMxJKk3xFSS5K_2XjZCeQ5vf01yF3tsIsRXxDth5II_aCLME9jR16CISug9ZVcVO43WqF4NNxjvFmIPSPRIT3");
    
    GCMUtil gcmUtil = new GCMUtil(reslist, gcmVo);
    
%>
