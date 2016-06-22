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
	List<TaskInfo> taskInfoList = (List<TaskInfo>) session.getAttribute("select_taskInfoList");

	Map<String, Map<Integer, ArrayList<TaskManagementInfo>>> taskManagement = (Map<String, Map<Integer, ArrayList<TaskManagementInfo>>>) session
			.getAttribute("AlltaskManagement");
	
	ArrayList<TaskEvaluateManagementInfo> taskEvaluateManagementInfo = (ArrayList<TaskEvaluateManagementInfo>) session
			.getAttribute("taskEvaluateManagement");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>나의 과제 현황</title>
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
<link rel="stylesheet" href="css/Notify.css" />
<link href="dist/css/timeline.css" rel="stylesheet">
<link href="dist/css/sb-admin-2.css" rel="stylesheet">
<link href="bower_components/morrisjs/morris.css" rel="stylesheet">
<link href="bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="SE2/js/HuskyEZCreator.js"></script>
<script type="text/javascript" src="js/stu_task_submit.js"></script>
<script>
	$(document).ready(function() {
		$("#content-slider").lightSlider({
			loop : true
		});
	});
</script>
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
				<a class="navbar-brand" href="stu_main.jsp"><img
					src="image/Logo.png" alt="" style="max-width: 36%; height: auto;" /></a>
			</div>
			<!-- /.navbar-header -->

			<ul class="nav navbar-top-links navbar-right">
				<!-- /.dropdown -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-tasks fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a> <!-- /.dropdown-tasks --></li>
				<!-- /.dropdown -->

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
					<h1 class="page-header">과제 세부사항</h1>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">과제 내용</div>
							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="panel-body">
									<%
										for (int i = 0; i < taskInfoList.size(); i++) {
									%>
									<h3><%=taskInfoList.get(i).getTask_name()%></h3>
									<br>
									<blockquote>
										<p><%=taskInfoList.get(i).getTask_content()%></p>
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

			<div class="row">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">과제 제출</div>
							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="dataTable_wrapper">
									<%
										String code = taskInfoList.get(0).getSubj_code_div();
										int num = taskInfoList.get(0).getTask_number();
										String tt = "N";
										String title = "";
										String content = "";
										if (taskManagement.get(code).get(num) != null) {
											for (int j = 0; j < taskManagement.get(code).get(num).size(); j++) {
												if (taskManagement.get(code).get(num).get(j).getTask_number() == num
														&& taskManagement.get(code).get(num).get(j).getStd_id() == stdInfo.getStdId()) {
															tt = "Y";
															title = taskManagement.get(code).get(num).get(j).getTask_title();
															content = taskManagement.get(code).get(num).get(j).getTask_content();
												} }}
									%>
									<%if(tt.equals("Y")) {%>
									<h3><%=title%></h3>
									<br>
									<blockquote>
										<p><%=content%></p>
									</blockquote>
									<% } else if(tt.equals("N")) {%>
													<form method="post" action="task_submit" id="frm">

										<div class="form-group" align="center">
											<label class="col-sm-1 control-label"> 제목 </label>
											<div class="col-sm-7">
												<input type="text" class="form-control" name="title"
													id="name" size="100">
											</div>
										</div>
										<br>
										<div class="form-group" align="center">
											<div class="panel-heading">
												<textarea name="smarteditor" id="smarteditor" rows="10"
													class="form-control" cols="100"
													style="width: 100%; height: 400px;"></textarea>
											</div>
										</div>
										<input type="hidden" name="Subj_name"
											value="<%=taskInfoList.get(0).getTask_name()%>"> <input
											type="hidden" name="Subj_code"
											value="<%=taskInfoList.get(0).getSubj_code_div()%>">
										<input type="hidden" name="Subj_number"
											value="<%=taskInfoList.get(0).getTask_number()%>">

										<div class="form-group" align="center">
											<input type="submit" class="form-control" value="제출하기"
												name="subscribeForm" id="savebutton" />
										</div>
									</form>
									<%} %>
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