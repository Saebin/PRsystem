<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>
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
	ArrayList<TaskInfo> taskInfoList = (ArrayList<TaskInfo>) session.getAttribute("pro_taskInfoList");
	ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>) session.getAttribute("subjInfoList");
	Map<String, Map<Integer, ArrayList<TaskManagementInfo>>> taskManagement = (Map<String, Map<Integer, ArrayList<TaskManagementInfo>>>) session
			.getAttribute("AlltaskManagement");
%>

<%
	
%>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link href="css/profile.css" rel="stylesheet" />
<title>Professor Page</title>
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
				<a class="navbar-brand" href="main"><img src="image/Logo.png"
					alt="" style="max-width: 36%; height: auto;" /></a>
			</div>
			<!-- /.navbar-header -->

			<ul class="nav navbar-top-links navbar-right">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-tasks fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-tasks">
						<%
							for (int i = 0; i < 6; i++) {
						%>
						<li><a href="pro_task_situation.jsp">
								<div>
									<p>
										<strong><%=taskInfoList.get(i).getTask_name()%></strong> <span
											class="pull-right text-muted"> <%
 	for (int j = 0; j < subjInfoList.size(); j++) {
 %> <%
 	if (taskInfoList.get(i).getSubj_code_div().equals(subjInfoList.get(j).getSubj_code_div())) {
 %> <%
 	double div = (double) taskManagement.get(taskInfoList.get(i).getSubj_code_div())
 						.get(taskInfoList.get(i).getTask_number()).size()
 						/ (double) subjInfoList.get(j).getCurr_num() * 100;
 %> <%=(int) div%> % Complete

										</span>
									</p>
									<div class="progress progress-striped active">
										<div class="progress-bar progress-bar-warning"
											role="progressbar" aria-valuenow="40" aria-valuemin="0"
											aria-valuemax="100" style="width: <%=div%>%">
											<span class="sr-only"><%=(int) div%>% Complete
												(success)</span>
										</div>
									</div>
								</div>
						</a></li>
						<%
							}
								}
							}
						%>

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
										<div class="profile-usertitle-name"><%=profInfo.getProfName()%></div>
										<div class="profile-usertitle-job"><%=profInfo.getProfAuth()%></div>
									</div>
								</div>
							</div>
						</li>
						<li><a href="main"><i class="fa fa-dashboard fa-fw"></i>
								메인페이지</a></li>
						<li><a href="taskview"><i class="fa fa-edit fa-fw"></i>
								과제현황</a></li>
						<li><a href="noticeview"><i class="fa fa-bell-o fa-fw"></i>
								공지사항</a></li>
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
					<h1 class="page-header">과목수정</h1>
				</div>
				<div class="panel-body">
					<div class="dataTable_wrapper">

						<div class="form-group">
							<label class="col-sm-2 control-label">과목 선택</label>
							<div class="col-sm-3">
								<select id="select" name="Subj_code_div" class="form-control">
									<option value="assasasa">과목을 선택하세요.</option>
									<%
										for (int i = 0; i < subjInfoList.size(); i++) {
									%>
									<option id="select+<%=i%>>" name="name"
										value="<%=subjInfoList.get(i).getSubj_code_div()%> "><%=subjInfoList.get(i).getSubj_name()%>
										<%
											}
										%>
									
								</select>
							</div>
						</div>
						<br>
						<div class="form-group">
							<div id="abc">
								<%
									for (int i = 0; i < subjInfoList.size(); i++) {
								%>
								<div id="<%=subjInfoList.get(i).getSubj_code_div()%>"
									style="display: none">
									<form name="subjectupdate" id="subjectupdate" method="post"
										class="form-horizontal" action="subjectupdate">

										<div class="form-group">
											<input type="hidden" name="code"
												value="<%=subjInfoList.get(i).getSubj_code_div()%>">

										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">과목코드 / 분반</label> <label
												class="col-sm-1 control-label"> <%=subjInfoList.get(i).getSubj_code_div()%>
											</label>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">등록코드</label>
											<div class="col-sm-3">
												<input type="text" id="register_code" name="regist_code"
													class="form-control"
													value="<%=subjInfoList.get(i).getRegist_code()%>">

											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label">참여 인원 제한</label>
											<div class="col-sm-3">
												<select name="limit_num" class="form-control">
													<option value="5"
														<%if (subjInfoList.get(i).getLimit_num() == 5) {%>
														selected <%}%>>5명</option>
													<option value="10"
														<%if (subjInfoList.get(i).getLimit_num() == 10) {%>
														selected <%}%>>10명</option>
													<option value="15"
														<%if (subjInfoList.get(i).getLimit_num() == 15) {%>
														selected <%}%>>15명</option>
													<option value="20"
														<%if (subjInfoList.get(i).getLimit_num() == 20) {%>
														selected <%}%>>20명</option>
													<option value="25"
														<%if (subjInfoList.get(i).getLimit_num() == 25) {%>
														selected <%}%>>25명</option>
													<option value="30"
														<%if (subjInfoList.get(i).getLimit_num() == 30) {%>
														selected <%}%>>30명</option>
													<option value="40"
														<%if (subjInfoList.get(i).getLimit_num() == 40) {%>
														selected <%}%>>40명</option>
													<option value="50"
														<%if (subjInfoList.get(i).getLimit_num() == 50) {%>
														selected <%}%>>50명</option>
													<option value="60"
														<%if (subjInfoList.get(i).getLimit_num() == 60) {%>
														selected <%}%>>60명</option>
													<option value="100"
														<%if (subjInfoList.get(i).getLimit_num() == 100) {%>
														selected <%}%>>100명</option>
												</select>

											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label">과목 설명</label>
											<div class="col-sm-5">
												<textarea rows="5" cols="100" class="form-control"
													name="sub_outline"><%=subjInfoList.get(i).getSubj_outline()%></textarea>
											</div>
										</div>

										<div class="col-sm-5">
											<input type="submit" class="form-control" value="과목 수정"
												style="width: 60pt;">
										</div>
									</form>
								</div>
								<%
									}
								%>
							</div>
						</div>
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
	<script src="js/subjectSelect.js"></script>
</body>
</html>