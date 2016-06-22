<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	page import="java.util.*"%>
<%@ page import="vo.SubjectInfo"%>
<%@	page import="vo.Notice"%>
<%@	page import="vo.TaskInfo"%>
<%@	page import="vo.ProfessorInfo"%>
<%@	page import="vo.StudentSubject"%>
<%@	page import="vo.StudentInfo"%>
<%@	page import="vo.TaskManagementInfo"%>
<%@	page import="vo.TaskEvaluateManagementInfo"%>

<%
	StudentInfo stdInfo = (StudentInfo) session.getAttribute("stdInfo");
	ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>) session.getAttribute("subjInfoList");
	Map<String, Map<Integer, ArrayList<TaskManagementInfo>>> taskManagement = (Map<String, Map<Integer, ArrayList<TaskManagementInfo>>>) session
			.getAttribute("AlltaskManagement");
	ArrayList<TaskManagementInfo> select_taskManagement = (ArrayList<TaskManagementInfo>) session
			.getAttribute("select_taskManagement");
	ArrayList<TaskInfo> select_taskInfoList = (ArrayList<TaskInfo>) session.getAttribute("select_taskInfoList");
	ArrayList<TaskEvaluateManagementInfo> taskEvaluateManagementInfo = (ArrayList<TaskEvaluateManagementInfo>) session
			.getAttribute("taskEvaluateManagement");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>타학생 평가</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Student Page</title>
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

				<!-- /.dropdown -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">
						<li><a href="stu_info_update.jsp"><i
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
										<div class="profile-usertitle-name"><%=stdInfo.getStdName()%></div>
										<div class="profile-usertitle-job"><%=stdInfo.getStdAuth()%></div>
									</div>
								</div>
							</div>
						</li>
						<li><a href="stu_main.jsp"><i
								class="fa fa-dashboard fa-fw"></i> 메인페이지</a></li>
						<li><a href="stu_task_situation.jsp"><i
								class="fa fa-edit fa-fw"></i> 과제현황</a></li>
						<li><a href="stu_notify.jsp"><i
								class="fa fa-bell-o fa-fw"></i> 공지사항</a></li>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">타학생 평가</h1>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">과제 내용</div>

							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="dataTable_wrapper">
									<div class="panel-body">
										<div class="dataTable_wrapper">
											<div class="panel-body">
												<%
													for (int i = 0; i < select_taskInfoList.size(); i++) {
												%>
												<h3><%=select_taskInfoList.get(i).getTask_name()%></h3>
												<br>
												<blockquote>
													<p><%=select_taskInfoList.get(i).getTask_content()%></p>

												</blockquote>
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
				</div>
			</div>

			<div class="row">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">타학생 평가</div>

							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="dataTable_wrapper">

									<div>

										<div class="panel-body">
											<!-- Nav tabs -->
											<ul class="nav nav-tabs">
												<%
													for (int i = 0; i < select_taskManagement.size(); i++) {
														%><% 
												%>
												<li <%if (i == 0) {%> class="active" <%}%>><a
													href="#<%=i + 1%>" data-toggle="tab">익명</a></li>
												<%
													}
												%>
											</ul>

											<!-- Tab panes -->

											<div class="tab-content">
												<%
													for (int i = 0; i < select_taskManagement.size(); i++) {
														String tt = "N";
														int score = 0;
														String comment = "";
												%>
												<div class="tab-pane fade<%if (i == 0) {%> in active<%}%>"
													id="<%=i + 1%>">
													<br>


													<div class="form-group">
														<div class="col-sm-12">
															<div class="modal-body" height="550px">
																<%=select_taskManagement.get(i).getTask_content()%>
																<br>
															</div>
														</div>
													</div>

													<%
														for (int j = 0; j < taskEvaluateManagementInfo.size(); j++) {
																if (taskEvaluateManagementInfo.get(j).getStd_id() == stdInfo.getStdId()
																		&& taskEvaluateManagementInfo.get(j).getStd_id_evaluate() == select_taskManagement.get(i)
																				.getStd_id()) {
																	tt = "Y";
																	score = taskEvaluateManagementInfo.get(j).getSubmit_score();
																	comment = taskEvaluateManagementInfo.get(j).getSubmit_comment();
																}
															}
													%>

													<%
														if (tt.equals("N")) {
													%>
													<form name="evaluation" id="evaluation" method="post"
														class="form-horizontal" action="evaluationSubmit">
														<input type="hidden" name="std_id_eval"
															value="<%=select_taskManagement.get(i).getStd_id()%>">
														<input type="hidden" name="task_num"
															value="<%=select_taskManagement.get(i).getTask_number()%>">
														<div class="form-group">
															<label class="col-sm-5 control-label">평가</label>
															<div class="col-sm-2">
																<select name="score" class="form-control">
																	<option value="0">0점</option>
																	<option value="1">1점</option>
																	<option value="2">2점</option>
																	<option value="3">3점</option>
																	<option value="4">4점</option>
																	<option value="5">5점</option>
																</select>

															</div>
														</div>

														<div class="form-group" align="center">
															<h4>
																<label class="col-sm-0 control-label">코멘트 작성</label>
															</h4>

															<div class="col-sm-12">
																<textarea rows="10" cols="10" class="form-control"
																	name="comment"></textarea>
															</div>
														</div>

														<center>
															<input type="submit" class="form-control" value="제출 하기"
																style="width: 60pt;">
														</center>
													</form>
													<%
														} else if(tt.equals("Y")) {
													%>
													
													<hr size=5>													
													<div class="form-group" align="center">
													<label class="col-sm-12 control-label">평가</label>
													<div class="col-sm-12"><%=score  %></div>
													</div>
													<br>
													<div class="form-group" align="center">
													<label class="col-sm-12 control-label">작성한 코멘트 </label>
													<div class="col-sm-12">
													<%=comment %>
													</div>
													</div>
													<%} %>


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
				</div>
			</div>
		</div>
		<script src="bower_components/jquery/dist/jquery.min.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

		<!-- Metis Menu Plugin JavaScript -->
		<script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>

		<!-- Morris Charts JavaScript -->
		<script src="bower_components/raphael/raphael-min.js"></script>
		<script src="bower_components/morrisjs/morris.min.js"></script>
		<script src="js/morris-data.js"></script>

		<!-- Custom Theme JavaScript -->
		<script src="dist/js/sb-admin-2.js"></script>
</body>
</html>