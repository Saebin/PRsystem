<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@   page import="java.util.*"%>
<%@ page import="vo.SubjectInfo"%>
<%@   page import="vo.Notice"%>
<%@   page import="vo.TaskInfo"%>
<%@   page import="vo.ProfessorInfo"%>
<%@   page import="vo.StudentSubject"%>
<%@   page import="vo.StudentInfo"%>
<%@   page import="vo.TaskManagementInfo"%>

<%
	SubjectInfo subJname = (SubjectInfo) session.getAttribute("subJname");
	StudentInfo stdInfo = (StudentInfo) session.getAttribute("stdInfo");
	
	ArrayList<SubjectInfo> stdSubjList = (ArrayList<SubjectInfo>) session.getAttribute("stdSubjList");
	List<TaskInfo> taskInfoList = (List<TaskInfo>) session.getAttribute("AlltaskInfoList");
	
	
	//Map<String,ArrayList<TaskManagementInfo>> taskManagement = (Map<String, ArrayList<TaskManagementInfo>>) session
	//		.getAttribute("taskManagement");
	Map<String, ArrayList<Notice>> noticeList = (Map<String, ArrayList<Notice>>) session
			.getAttribute("noticeList");
	int viewnum = 5;
	Map<String, Map<Integer, ArrayList<TaskManagementInfo>>> taskManagement = (Map<String, Map<Integer, ArrayList<TaskManagementInfo>>>) session.getAttribute("AlltaskManagement");
	
%>


<!DOCTYPE html>
<html>
<head>
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
						<li><a href="main"><i
								class="fa fa-dashboard fa-fw"></i> 메인페이지</a></li>
						<li><a href="taskview"><i
								class="fa fa-edit fa-fw"></i> 과제현황</a></li>
						<li><a href="noticeview"><i
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
					<h1 class="page-header">Welcome to Peer Project</h1>

					<div class="pull-right">
						<a href="stu_subject_add.jsp"><i class="fa fa-edit fa-fw"></i>
							<right> 과목추가</right> </a>
					</div>
				</div>

				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

			<!-- 추가된 과목 -->
			<div class="row" id="cl">
				<%
					String[] str = { "panel panel-primary", "panel panel-green", "panel panel-red", "panel panel-yellow",
							"panel panel-primary", "panel panel-green", "panel panel-blue", "panel panel-yellow" };

					for (int i = 0; i < stdSubjList.size(); i++) {
				%>
				<div class="col-lg-3 col-md-6">
					<div class="<%=str[i]%>">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i id="image_<%=stdSubjList.get(i).getSubj_code_div()%>"
										class="fa fa-folder fa-5x" name="image"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge"><%=stdSubjList.get(i).getSubj_name()%></div>
									<div><%=stdSubjList.get(i).getSubj_code_div()%></div>
								</div>
							</div>
						</div>
						<a href="#" class="subject"
							id="<%=stdSubjList.get(i).getSubj_code_div()%>">
							<div class="panel-footer">
								<span class="pull-left">View Details</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<%
					}
				%>
			</div>

			<div class="row">
				<div class="col-lg-7">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-edit fa-fw"></i> 나의과제현황
							<div class="btn-group pull-right">
								<button type="button"
									class="btn btn-default btn-xs dropdown-toggle"
									data-toggle="dropdown">
									<i class="fa fa-chevron-down"></i>
								</button>
								<ul class="dropdown-menu slidedown">
									<li><a href="#"> <i class="fa fa-refresh fa-fw"></i>
											Refresh
									</a></li>
									<li class="divider"></li>
									<li><a href="taskview"> <i
											class="fa fa-external-link fa-fw"></i> detail-link
									</a></li>
								</ul>
							</div>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th width="60%">과제명</th>
										<th width="20%">제출여부</th>
									</tr>
								</thead>
								<tbody id="task">
									<% for (int i=0; i<taskInfoList.size(); i++) {	
									String code = taskInfoList.get(i).getSubj_code_div();
												int num = taskInfoList.get(i).getTask_number();
												String tt = "No";%>
									<tr name="task"
										class="<%=taskInfoList.get(i).getSubj_code_div() %>">
										<td><a href="taskview"><%=taskInfoList.get(i).getTask_name() %></a></td>
										<td>
										<%if(taskManagement.get(code).get(num) != null){
												for(int j=0; j<taskManagement.get(code).get(num).size(); j++ ){
													if(taskManagement.get(code).get(num).get(j).getTask_number()==num && taskManagement.get(code).get(num).get(j).getStd_id()==stdInfo.getStdId()){
														tt = "Yes";
														break;
													} 
												 }}%>
												<%=tt %>
										
										</td>
									</tr>
									<%	}	%>    
								</tbody>
							</table>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-8 -->
				<div class="col-lg-5">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bell fa-fw"></i> 공지사항
							<div class="btn-group pull-right">
								<button type="button"
									class="btn btn-default btn-xs dropdown-toggle"
									data-toggle="dropdown">
									<i class="fa fa-chevron-down"></i>
								</button>
								<ul class="dropdown-menu slidedown">
									<li><a href="#"> <i class="fa fa-refresh fa-fw"></i>
											Refresh
									</a></li>
									<li class="divider"></li>
									<li><a href="stu_notify.jsp"> <i
											class="fa fa-external-link fa-fw"></i> detail-link
									</a></li>
								</ul>
							</div>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="list-group">
								<%
									for (int j = 0; j < stdSubjList.size(); j++) {
										String code = stdSubjList.get(j).getSubj_code_div();
										for (int i = 0; i < noticeList.get(code).size(); i++) {
								%>
								<div name="notice"
									class=<%=noticeList.get(code).get(i).getSubj_code_div()%>
									id=<%=noticeList.get(code).get(i).getNoti_number()%> _ <%=i%>>
									<a href="stu_notify.jsp" class="list-group-item"> <i
										class="fa fa-comment fa-fw"></i> <%=noticeList.get(code).get(i).getNoti_name() %>
										<span class="pull-right text-muted small"><em><%=noticeList.get(code).get(i).getNoti_cre_date()%></em>
									</span>
									</a>
								</div>
								<%
									}
									}
								%>
							</div>
							<!-- /.list-group -->
							<a href="#" class="btn btn-default btn-block">View All Alerts</a>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->

					<!-- /.panel .chat-panel -->
				</div>
				<!-- /.col-lg-4 -->
			</div>

			<!-- <div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-user fa-fw"></i> 타학생평가
							<div class="btn-group pull-right">
								<button type="button"
									class="btn btn-default btn-xs dropdown-toggle"
									data-toggle="dropdown">
									<i class="fa fa-chevron-down"></i>
								</button>
								<ul class="dropdown-menu slidedown">
									<li><a href="#"> <i class="fa fa-refresh fa-fw"></i>
											Refresh
									</a></li>
									<li class="divider"></li>
									<li><a href="stu_evaluation.jsp"> <i
											class="fa fa-external-link fa-fw"></i> detail-link
									</a></li>
								</ul>
							</div>
						</div>
						/.panel-heading
						<div class="panel-body">
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th width="25%">평가학생</th>
										<th width="15%">평가여부</th>
										<th width="15%">코멘트여부</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>익명1</td>
										<td>Yes</td>
										<td>No</td>
									</tr>
									Table Row
									<tr>
										<td>익명2</td>
										<td>No</td>
										<td>No</td>
									</tr>
									Table Row
									<tr>
										<td>익명3</td>
										<td>Yes</td>
										<td>Yes</td>
									</tr>
								</tbody>
							</table>
						</div>
						/.panel-body
					</div>
					/.panel
				</div>
				/.col-lg-12
			</div> -->
		</div>
	</div>
	<script src="bower_components/jquery/dist/jquery.min.js"></script>
	<script type="text/javascript" src="js/SubjectInfo.js"></script>
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