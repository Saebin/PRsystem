<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	page import="java.util.*" %>
<%@ page import="vo.SubjectInfo"%>
<%@	page import="vo.Notice" %>
<%@	page import="vo.TaskInfo" %>
<%@	page import="vo.ProfessorInfo"%>
<%@	page import="vo.StudentSubject"%>
<%@	page import="vo.StudentInfo"%>
<%@	page import="vo.TaskManagementInfo"%>
<% 
	ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>)session.getAttribute("subjInfoList");
	ArrayList<Notice> noticeList = (ArrayList<Notice>) session.getAttribute("noticeList");
	ArrayList<TaskInfo> taskInfoList = (ArrayList<TaskInfo>) session.getAttribute("pro_taskInfoList");
	ProfessorInfo profInfo = (ProfessorInfo) session.getAttribute("profInfo");
	
	Map<String,ArrayList<StudentInfo>> inClassStd = (Map<String,ArrayList<StudentInfo>>)session.getAttribute("inClassStd");
	Iterator<String> keys = inClassStd.keySet().iterator();
	
	Map<String,Map<Integer,ArrayList<TaskManagementInfo>>> taskManagement = (Map<String,Map<Integer,ArrayList<TaskManagementInfo>>>)session.getAttribute("AlltaskManagement");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>과제현황</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Professor Page</title>
<link rel="stylesheet" href="css/Notify.css" />
<link rel="stylesheet" href="css/similarity.css" />
<link href="css/profile.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="SE2/js/HuskyEZCreator.js"></script>
<script type="text/javascript" src="js/Notify.js"></script>
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
			<a class="navbar-brand" href="main"><img src="image/Logo.png" alt="" style="max-width: 36%; height: auto;"/></a>
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
					<h1 class="page-header">과제현황</h1>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								과제현황
								<div class="btn-group pull-right">
									<button type="button"
										class="btn btn-default btn-xs dropdown-toggle"
										data-toggle="dropdown">
										선택<i class="fa fa-chevron-down"></i>
									</button>
									<ul class="dropdown-menu slidedown">
										<li id="total"><a href="#"> 과목전체 </a></li>
										<li class="divider"></li>
										<% for(int i=0 ; i<subjInfoList.size() ; i++) { %>
										<li class="Subj_list"
											id="<%=subjInfoList.get(i).getSubj_code_div()%>"><a
											href="#"><%=subjInfoList.get(i).getSubj_name()%></a></li>
										<% } %>
										<li class="divider"></li>
										<li><a href="#"
											onclick="window.open('pro_task_create.jsp', 'popup', 'width=900px height=568px toolbar=no menubar=no');">
												과제등록 </a></li>
										<li class="divider"></li>
										<li><a href="pro_task_update.jsp"> 과제수정 </a></li>

									</ul>
								</div>

								<span class="pull-right text-muted"> </span>
							</div>

							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="dataTable_wrapper">
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example">
										<thead>
											<tr>
												<th width="5%"></th>
												<th width="30%">과제명</th>
												<th width="15%">마감날짜</th>
												<th width="8%">진행율</th>
												<th width="8%">수정</th>
												</tr>
										</thead>
										<!-- Table Header -->

										<!-- Table Body -->
										<tbody>
											<%for(int i=0; i<taskInfoList.size(); i++) { %>
											<tr class="record"
												id="<%=taskInfoList.get(i).getTask_number() %>"
												name="<%=taskInfoList.get(i).getSubj_code_div() %>">
												<td><%=i+1%></td>
												<td><form action="taskevaluation" method="POST">
												<input type="hidden" name="num" value="<%=taskInfoList.get(i).getTask_number() %>">	
												<input type="hidden" name="Subj_code" value="<%=taskInfoList.get(i).getSubj_code_div() %>">											
												<input type="hidden" name="similarity" value="<%=taskInfoList.get(i).getSubj_code_div()+"_"+taskInfoList.get(i).getTask_name()%>">
												<input type="submit" class="similarity" value="<%=taskInfoList.get(i).getTask_name()%>"></form></td>
												<td><%=taskInfoList.get(i).getTask_end() %></td>
												
                              <% for(int j=0; j < subjInfoList.size(); j++) { %>

                              <% if(taskInfoList.get(i).getSubj_code_div().equals(subjInfoList.get(j).getSubj_code_div())) { %>
                              <% double div= (double)taskManagement.get(taskInfoList.get(i).getSubj_code_div()).get(taskInfoList.get(i).getTask_number()).size() / (double)subjInfoList.get(j).getCurr_num() * 100; %>
                              <td><%=(int)div %>%</td>
												<td><a href="pro_task_update.jsp?task_num=<%=taskInfoList.get(i).getTask_number() %>">수정</a></td>
												 
											</tr>
											<!-- Table Row -->
											<%} }}%>
										</tbody>
										<!-- Table Body -->
									</table>
								</div>
								<form name="paging">
									
										<input type="hidden" name="task_name">
										<input type="hidden" name="task_description">
										<input type="hidden" name="Subj_code_div">
										<input type="hidden" name="task_end">
									
								</form>
							</div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>

	<script src="bower_components/jquery/dist/jquery.min.js"></script>
	<script src="js/pro_task_cookie.js"></script>
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

	<script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
                responsive: true
        });
    });

    function page_move(task_name,task_description,Subj_code_div,task_end){
        var f=document.paging; //폼 name

        f.task_name.value = task_name; //POST방식으로 넘기고 싶은 값
        f.task_description.value = task_description;//POST방식으로 넘기고 싶은 값
        f.Subj_code_div.value = Subj_code_div;
        f.task_end.value = task_end;
        f.action="taskcreate";//이동할 페이지
        f.method="post";//POST방식
        f.submit();
    }
    </script>
</body>