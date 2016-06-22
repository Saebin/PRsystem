<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@	page import="java.util.*"%>
<%@ page import="vo.SubjectInfo"%>
<%@	page import="vo.Notice"%>
<%@	page import="vo.TaskInfo"%>
<%@	page import="vo.ProfessorInfo"%>
<%@	page import="vo.StudentSubject"%>
<%@	page import="vo.StudentInfo"%>
<%@   page import="vo.TaskManagementInfo"%>

<% 
	ProfessorInfo profInfo = (ProfessorInfo) session.getAttribute("profInfo");
	ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>)session.getAttribute("subjInfoList");
	ArrayList<Notice> noticeList = (ArrayList<Notice>) session.getAttribute("noticeList");
	ArrayList<TaskInfo> taskInfoList = (ArrayList<TaskInfo>) session.getAttribute("pro_taskInfoList");


	Map<String,ArrayList<StudentInfo>> inClassStd = (Map<String,ArrayList<StudentInfo>>)session.getAttribute("inClassStd");
	Iterator<String> keys = inClassStd.keySet().iterator();

	Map<String,Map<Integer,ArrayList<TaskManagementInfo>>> taskManagement = (Map<String,Map<Integer,ArrayList<TaskManagementInfo>>>)session.getAttribute("AlltaskManagement");
// 메인화면에 공지사항과 과목 보여주는 최대 갯수

%>

<!DOCTYPE html>
<html>
<head>
<%
	String tempPassword = "";

	for(int i=0; i<8; i++) {
	  int rndVal = (int)(Math.random() * 62);
	  if(rndVal < 10) {
	    tempPassword += rndVal;
	  } else if(rndVal > 35) {
	    tempPassword += (char)(rndVal + 61);
	  } else {
	    tempPassword += (char)(rndVal + 55);
	  }
	} //자바스크립트로 바꿀 예정
	%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


<title>Professor Page</title>
<link href="css/profile.css" rel="stylesheet" />
<link href="css/table.css" rel="stylesheet" />
<link href="bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">
<link href="dist/css/timeline.css" rel="stylesheet">
<link href="dist/css/sb-admin-2.css" rel="stylesheet">
<link href="bower_components/morrisjs/morris.css" rel="stylesheet">
<link href="bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/style2.css" media="screen"
	title="no title" charset="utf-8">
</head>
<body>
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="pro_main.jsp"><img
					src="image/Logo.png" alt="" style="max-width: 36%; height: auto;" /></a>
			</div>
			<!-- /.navbar-header -->

				<ul class="nav navbar-top-links navbar-right">
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-tasks fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-tasks">
					<% for(int i=0; i < 6; i++) { %>
					<li><a href="pro_task_situation.jsp">
							<div>
								<p>
									<strong><%=taskInfoList.get(i).getTask_name() %></strong> <span
										class="pull-right text-muted"> <% for(int j=0; j < subjInfoList.size(); j++) { %>

										<% if(taskInfoList.get(i).getSubj_code_div().equals(subjInfoList.get(j).getSubj_code_div())) { %>
										<% double div= (double)taskManagement.get(taskInfoList.get(i).getSubj_code_div()).get(taskInfoList.get(i).getTask_number()).size() / (double)subjInfoList.get(j).getCurr_num() * 100; %>
										<%=(int)div %> % Complete

									</span>
								</p>
								<div class="progress progress-striped active">
									<div class="progress-bar progress-bar-warning"
										role="progressbar" aria-valuenow="40" aria-valuemin="0"
										aria-valuemax="100" style="width: <%=div %>%">
										<span class="sr-only"><%=(int)div %>% Complete
											(success)</span>
									</div>
								</div>
							</div>
					</a></li>
					<% }}} %>

					<li class="divider"></li>
					<li><a class="text-center" href="pro_task_situation.jsp">
							<strong>See All Tasks</strong> <i class="fa fa-angle-right"></i>
					</a></li>
				</ul></li>
			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="pro_info_update.jsp"><i
							class="fa fa-user fa-fw"></i> User Profile</a></li>
					
					<li class="divider"></li>
					<li><a href="logout"><i class="fa fa-sign-out fa-fw"></i>
							Logout</a></li>
				</ul> <!-- /.dropdown-user --></li>
			<!-- /.dropdown -->
		</ul>
		
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li class="sidebar-search">
							<div class="row profile">
								<div class="profile-sidebar">
									<!-- SIDEBAR USERPIC -->
									<div class="profile-userpic">
										<img src="image/fox-03.png" class="img-responsive" alt="" />
									</div>
									<!-- END SIDEBAR USERPIC -->
									<!-- SIDEBAR USER TITLE -->
									<div class="profile-usertitle">
										<div class="profile-usertitle-name"><%=profInfo.getProfName() %></div>
										<div class="profile-usertitle-job"><%=profInfo.getProfAuth() %></div>
									</div>
								</div>
							</div>
						</li>
						<li><a href="pro_main.jsp"><i
								class="fa fa-dashboard fa-fw"></i> 메인페이지</a></li>
						<li><a href="pro_task_situation.jsp"><i
								class="fa fa-edit fa-fw"></i> 과제현황</a></li>
						<li><a href="pro_notify.jsp"><i
								class="fa fa-bell-o fa-fw"></i> 공지사항</a></li>
						<li><a href="pro_student_management.jsp"><i
								class="fa fa-user fa-fw"></i> 학생관리</a></li>

					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">과목생성</h1>
				</div>
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<form name="fileForm" id="fileForm" method="post"
							class="form-horizontal" enctype="multipart/form-data"
							action="subjectCreate">

							<div class="form-group">
								<label class="col-sm-2 control-label">과목코드</label>
								<div class="col-sm-3">
									<input type="text" id="sub_code" class="form-control"
										name="sub_code">
								</div>
								<label class="col-sm-1 control-label"> 분반</label>
								<div class="col-sm-1">
									<input type="text" class="form-control" id="sub_divide"
										name="sub_divide" size="2">
								</div>
							</div>


							<div class="form-group">
								<label class="col-sm-2 control-label">등록코드</label>
								<div class="col-sm-3">
									<input type="text" id="register_code" name="register_code"
										class="form-control" value="<% out.println(tempPassword); %>">
									<div class="col-sm-1">
										<!-- <form>
														<input type=button value="Refresh" onClick="history.go()">
													</form> -->
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label">과목 이름</label>
								<div class="col-sm-3">
									<input type="text" id="sub_name" class="form-control"
										name="sub_name">
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label">참여 인원 제한</label>
								<div class="col-sm-3">
									<select name="limit_num" class="form-control">
										<option value="5">5명</option>
										<option value="10">10명</option>
										<option value="15">15명</option>
										<option value="20">20명</option>
										<option value="25">25명</option>
										<option value="30">30명</option>
										<option value="40">40명</option>
										<option value="50">50명</option>
										<option value="60">60명</option>
										<option value="100">100명</option>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label">과목 설명</label>
								<div class="col-sm-5">
									<textarea rows="5" cols="100" class="form-control"
										name="sub_description"></textarea>
								</div>
							</div>

							<!-- 
												과목이미지 : <input type="file" name="filename1"
													id="filename1" " size="40"> -->


							<center>
								<input type="submit" class="form-control" value="과목 생성"
									style="width: 60pt;">
							</center>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap2.min.js"></script>

</body>
</html>