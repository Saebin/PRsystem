<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	page import="java.util.*" %>
<%@ page import="vo.SubjectInfo"%>
<%@	page import="vo.Notice" %>
<%@	page import="vo.TaskInfo" %>
<%@	page import="vo.ProfessorInfo"%>
<%@	page import="vo.StudentSubject"%>
<%@	page import="vo.StudentInfo"%>
<%@   page import="vo.TaskManagementInfo"%>
<% 
	ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>)session.getAttribute("subjInfoList");
	ArrayList<Notice> noticeList = (ArrayList<Notice>) session.getAttribute("noticeList");
	ProfessorInfo profInfo = (ProfessorInfo) session.getAttribute("profInfo");
	ArrayList<TaskInfo> taskInfoList = (ArrayList<TaskInfo>) session.getAttribute("pro_taskInfoList");
	Map<String,Map<Integer,ArrayList<TaskManagementInfo>>> taskManagement = (Map<String,Map<Integer,ArrayList<TaskManagementInfo>>>)session.getAttribute("AlltaskManagement");
	Map<String,ArrayList<StudentInfo>> inClassStd = (Map<String,ArrayList<StudentInfo>>)session.getAttribute("inClassStd");
	Iterator<String> keys = inClassStd.keySet().iterator();
	
	
	// 메인화면에 공지사항과 과목 보여주는 최대 갯수
	int viewnum = 3;

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Professor Page</title>
<script src="bower_components/jquery/dist/jquery.min.js"></script>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script	src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/pro_std_cookie.js"></script>
<script	src="bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
<script	src="bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<link href="css/dataTables.min.css" rel="stylesheet">
<link href="css/profile.css" rel="stylesheet" />
<link href="bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="dist/css/sb-admin-2.css" rel="stylesheet">
<link
	href="bower_components/datatables-responsive/css/dataTables.responsive.css"
	rel="stylesheet">
<link
	href="bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">
<link href="bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
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
			<a class="navbar-brand" href="main"><img src="image/Logo.png" alt="" style="max-width: 36%; height: auto;"/></a>
		</div>
		<!-- /.navbar-header -->

		<ul class="nav navbar-top-links navbar-right">
			<!-- <li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-envelope fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-messages">
					<li><a href="#">
							<div>
								<strong>John Smith</strong> <span class="pull-right text-muted">
									<em>Yesterday</em>
								</span>
							</div>
							<div>Lorem ipsum dolor sit amet, consectetur adipiscing
								elit. Pellentesque eleifend...</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<strong>John Smith</strong> <span class="pull-right text-muted">
									<em>Yesterday</em>
								</span>
							</div>
							<div>Lorem ipsum dolor sit amet, consectetur adipiscing
								elit. Pellentesque eleifend...</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<strong>John Smith</strong> <span class="pull-right text-muted">
									<em>Yesterday</em>
								</span>
							</div>
							<div>Lorem ipsum dolor sit amet, consectetur adipiscing
								elit. Pellentesque eleifend...</div>
					</a></li>
					<li class="divider"></li>
					<li><a class="text-center" href="#"> <strong>Read
								All Messages</strong> <i class="fa fa-angle-right"></i>
					</a></li>
				</ul> /.dropdown-messages</li> -->
			<!-- /.dropdown -->
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
            </ul> 
			
			
				<!-- /.dropdown-tasks --></li>
			<!-- /.dropdown -->
			<!-- <li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-bell fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-alerts">
					<li><a href="#">
							<div>
								<i class="fa fa-comment fa-fw"></i> New Comment <span
									class="pull-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-twitter fa-fw"></i> 3 New Followers <span
									class="pull-right text-muted small">12 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-envelope fa-fw"></i> Message Sent <span
									class="pull-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-tasks fa-fw"></i> New Task <span
									class="pull-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="#">
							<div>
								<i class="fa fa-upload fa-fw"></i> Server Rebooted <span
									class="pull-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a class="text-center" href="#"> <strong>See
								All Alerts</strong> <i class="fa fa-angle-right"></i>
					</a></li>
				</ul> /.dropdown-alerts</li> -->
			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="pro_info_update.jsp"><i class="fa fa-user fa-fw"></i> User
							Profile</a></li>
				
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
		<!-- /.navbar-static-side --> </nav>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">학생관리</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							학생관리
							<div class="btn-group pull-right">
								<button type="button"
									class="btn btn-default btn-xs dropdown-toggle"
									data-toggle="dropdown">
									선택<i class="fa fa-chevron-down"></i>
								</button>
								<ul class="dropdown-menu slidedown">
									<li class="divider"></li>
									<% for(int i=0 ; i<subjInfoList.size() ; i++) { %>
									<li class="Subj_list"
										id="<%=subjInfoList.get(i).getSubj_code_div()%>"><a
										href="#"> <%=subjInfoList.get(i).getSubj_name()%>
									</a></li>
									<% } %>
									<li class="divider"></li>				

								</ul>
							</div>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th width="5%"></th>
											<th width="15%">학번</th>
											<th width="20%">이름</th>
											<th width="20%">이메일</th>
											<th width="15%">전화번호</th>
											<th width="15%">가입일</th>
										</tr>
									</thead>
									<!-- Table Header -->

									<!-- Table Body -->
									<tbody>
										<%while( keys.hasNext() ){   
										String key = keys.next();
										for(int i=0; i<inClassStd.get(key).size(); i++){%>
										<tr name="std_managment" class="<%=key%>"
											id="<%= inClassStd.get(key).get(i).getStdName() %>">
											<td><%=i+1 %></td>
											<td id="StdId"><%= inClassStd.get(key).get(i).getStdId() %></td>
											<td><%= inClassStd.get(key).get(i).getStdName() %></td>
											<td><%= inClassStd.get(key).get(i).getStdEmail() %></td>
											<td><%= inClassStd.get(key).get(i).getStdPhone() %></td>
											<td><%= inClassStd.get(key).get(i).getStdCreDate() %></td>
										</tr>
										<%}} %>
										<!-- Darker Table Row -->

									</tbody>
									<!-- Table Body -->
								</table>
							</div>
						</div>
					</div>



					<%-- <div class="detail">
						<div class="panel panel-default">
							<div class="panel-heading" id="std_detail">학생 컬럼 클릭 시 세부사항이 나타납니다.</div>
							<div class="panel-body">
								<div class="dataTable_wrapper">
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example">
										<thead>
											<tr>
												<th width="5%"></th>
												<th>이름</th>
												<th>과제명</th>
												<th>과제점수</th>
												<th>평균점수</th>
												<th>코멘트여부</th>
											</tr>
										</thead>
										<!-- Table Header -->

										<!-- Table Body -->
										<tbody>
										<%while( keys.hasNext() ){   
										String key1 = keys.next();
										for(int i=0; i<inClassStd.get(key1).size(); i++){%>
											<tr name="std_detail" class="<%=key1%>"
												id="<%= inClassStd.get(key1).get(i).getStdId() %>">
												<td><%= inClassStd.get(key1).get(i).getStdId() %></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
										<%}} %>
											<!-- Table Row -->
										</tbody>
										<!-- Table Body -->
									</table>

								</div>
							</div>
						</div>
					</div> --%>

				</div>

				<!-- jQuery -->
				
				
	
				<script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
                responsive: true
        });
    });
    </script>
</body>
</html>