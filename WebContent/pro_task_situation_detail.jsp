<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	page import="java.util.*"%>
<%@ page import="vo.SubjectInfo"%>
<%@	page import="vo.Notice"%>
<%@	page import="vo.TaskInfo"%>
<%@	page import="vo.ProfessorInfo"%>
<%@	page import="vo.StudentSubject"%>
<%@	page import="vo.StudentInfo"%>
<%@	page import="vo.CodeSimilarityInfo"%>
<%@	page import="vo.TaskManagementInfo"%>
<%@	page import="vo.TaskEvaluateManagementInfo"%>
<%@	page import="edu.hkcity.cs.Main"%>
<%
	ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>) session.getAttribute("subjInfoList");
	ArrayList<Notice> noticeList = (ArrayList<Notice>) session.getAttribute("noticeList");
	ArrayList<CodeSimilarityInfo> codeList = (ArrayList<CodeSimilarityInfo>) session.getAttribute("codeList");
	ArrayList<TaskInfo> taskInfoList = (ArrayList<TaskInfo>) session.getAttribute("pro_taskInfoList");
	ArrayList<TaskInfo> select_taskInfoList = (ArrayList<TaskInfo>) session.getAttribute("select_taskInfoList");
	Map<String, Map<Integer, ArrayList<TaskManagementInfo>>> taskManagement = (Map<String, Map<Integer, ArrayList<TaskManagementInfo>>>) session
			.getAttribute("AlltaskManagement");
	ArrayList<TaskManagementInfo> select_taskManagement = (ArrayList<TaskManagementInfo>) session
			.getAttribute("select_taskManagement");
	ProfessorInfo profInfo = (ProfessorInfo) session.getAttribute("profInfo");

	Map<String, ArrayList<StudentInfo>> inClassStd = (Map<String, ArrayList<StudentInfo>>) session
			.getAttribute("inClassStd");
	//Iterator<String> keys = inClassStd.keySet().iterator();

	ArrayList<TaskEvaluateManagementInfo> taskEvaluateManagementInfo = (ArrayList<TaskEvaluateManagementInfo>) session
			.getAttribute("taskEvaluateManagement");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>과제현황</title>

<script type="text/javascript"
	src="bower_components/jquery/dist/jquery.min.js"></script>
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/modal-box.js"></script>
<script type="text/javascript" src="js/count.js"></script>
<link href="css/profile.css" rel="stylesheet" />
<link href="css/table.css" rel="stylesheet" />
<link href="css/modal-box.css" rel="stylesheet" />
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
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-tasks fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a> <!-- /.dropdown-tasks --></li>

			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="#"><i class="fa fa-user fa-fw"></i> User
							Profile</a></li>
					<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
					</li>
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
									<img src="image/logo/fox-03.png" class="img-responsive" alt="" />
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

		<!-- /.navbar-static-side --> </nav>
		<div id="page-wrapper">
			<div class="col-lg-12">
				<h1 class="page-header">세부 과제 현황</h1>
			</div>
			<div class="row">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">과제 상세 정보</div>
							<!-- /.panel-heading -->
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
									<%
										if (codeList.size() > 0) {
									%>
									<div class="alert alert-danger">
										<%
											for (int i = 0; i < codeList.size(); i++) {
										%>
										'<%=codeList.get(i).getStu_name1()%>'학생과 '<%=codeList.get(i).getStu_name2()%>'학생의
										코드가 '<%=Double.parseDouble(String.format("%.1f", codeList.get(i).getContent()))%>%'
										유사합니다.<br>
										<%
											}
										%>

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
			<div class="row">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">과제 상세 정보</div>

							<div class="panel-body">
								<div class="dataTable_wrapper">
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example">
										<thead>
											<tr>
												<th width="10%">학번</th>
												<th width="10%">이름</th>
												<th width="40%">제출명</th>
												<th width="17%">평가횟수</th>
												<th width="15%">평가평점</th>
											</tr>
										</thead>
										<%
											String code = select_taskInfoList.get(0).getSubj_code_div();
											int number = select_taskInfoList.get(0).getTask_number();

											for (int i = 0; i < taskManagement.get(code).get(number).size(); i++) {
												int total = 0;
												int amount = 0;
												int score = 0;
												double ave_score = 0;
										%>

										<tr>
											<td><%=taskManagement.get(code).get(number).get(i).getStd_id()%></td>
											<td>
												<%
													Iterator<String> keys = inClassStd.keySet().iterator();
														while (keys.hasNext()) {
															String key = keys.next();
															if (key.equals(select_taskInfoList.get(0).getSubj_code_div())) {
																for (int a = 0; a < inClassStd.get(key).size(); a++) {
																	if (inClassStd.get(key).get(a).getStdId() == taskManagement.get(code).get(number).get(i)
																			.getStd_id()) {
												%> <%=inClassStd.get(key).get(a).getStdName()%> <%
 	}
 				}
 			}
 		}
 %>
											</td>
											<td><a href="#" data-modal-id="sumbit_<%=i + 1%>"><%=taskManagement.get(code).get(number).get(i).getTask_title()%></a></td>
											<td><a href="#" data-modal-id="comment_<%=i + 1%>">
													<%
														for (int j = 0; j < taskEvaluateManagementInfo.size(); j++) {
																if (number == taskEvaluateManagementInfo.get(j).getTask_number()) {
																	total++;
																}
																if (taskEvaluateManagementInfo.get(j).getStd_id() == taskManagement.get(code).get(number).get(i)
																		.getStd_id()) {
																	amount++;
																}
															}
													%> <%=amount%>/4
											</a></td>
											<td><a href="#" data-modal-id="score_<%=i + 1%>"> <%
 	for (int j = 0; j < taskEvaluateManagementInfo.size(); j++) {
 			if (taskEvaluateManagementInfo.get(j).getStd_id() == taskManagement.get(code).get(number).get(i)
 					.getStd_id()) {
 				score = score + taskEvaluateManagementInfo.get(j).getSubmit_score();
 			}
 		}
 		ave_score = (double) score / (double) amount;
 %> <%=ave_score%></td>
										</tr>
										<%
											}
										%>
										<tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		 <%
			for (int o = 0; o < select_taskManagement.size(); o++) {
		%>
		<div id="comment_<%=o + 1%>" class="modal-box">
			<header> <a href="#" class="js-modal-close close">×</a>
			<center>
				<h1>타학생 평가 코멘트</h1>
			</center>
			</header>
			<div class="modal-body">
				<div class="panel-body">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs">
						<%
							for (int i = 0; i < taskEvaluateManagementInfo.size(); i++) {
									if (taskEvaluateManagementInfo.get(i).getStd_id() == select_taskManagement.get(o).getStd_id()) {
						%>
						<li class="comment" <%if (i == 0) {%> class="active" <%}%>><a
							href="#<%=i + 1%>" data-toggle="tab"><%=taskEvaluateManagementInfo.get(i).getStd_id_evaluate()%></a></li>
						<%
							}
								}
						%>
					</ul>

					<!-- Tab panes -->

					<div class="tab-content">
						<%
							for (int i = 0; i < taskEvaluateManagementInfo.size(); i++) {
									if (taskEvaluateManagementInfo.get(i).getStd_id() == select_taskManagement.get(o).getStd_id()) {
						%>
						<div class="tab-pane fade<%if (i == 0) {%> in active<%}%>"
							id="<%=i + 1%>">
							<br>

							<div class="form-group">
								<div class="col-sm-12">
									<div class="modal-body" height="550px">
										<div class="form-group" align="center">
											<h4>
												<label class="col-sm-12 control-label"><%=taskEvaluateManagementInfo.get(i).getStd_id_evaluate()%>학생
													제출 답안</label>
											</h4>
										</div>
										<div class="form-group">
											<div class="modal-body1" id="sss">
												<%
													for (int p = 0; p < taskManagement.get(code).get(number).size(); p++) {
														if (taskManagement.get(code).get(number).get(p).getStd_id() == taskEvaluateManagementInfo.get(i).getStd_id()) {
												%>
												<%=taskManagement.get(code).get(number).get(p).getTask_content()%>
												<%
														}
													}
												%>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group" align="center">
								<h4>
									<label class="col-sm-12 control-label">작성한 코멘트</label>
								</h4>

								<div class="col-sm-12"><%=taskEvaluateManagementInfo.get(i).getSubmit_comment()%></div>
							</div>



						</div>
						<%
							}
								}
						%>
						<footer> <a href="#" class="js-modal-close">닫기</a> </footer>
					</div>

				</div>
			</div>
		</div>
		<%
			}
		%>
		<%-- <%
			for (int o = 0; o < select_taskManagement.size(); o++) {
		%>
		<div id="score_<%=o + 1%>" class="modal-box">
			<header> <a href="#" class="js-modal-close close">×</a>
			<center>
				<h1>타학생 평가 점수</h1>
			</center>
			</header>
			<div class="modal-body">
				<div class="panel-body">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs">
						<%
							for (int i = 0; i < taskEvaluateManagementInfo.size(); i++) {
									if (taskEvaluateManagementInfo.get(i).getStd_id() == select_taskManagement.get(o).getStd_id()) {
						%>
						<li <%if (i == 0) {%> class="active" <%}%>><a
							href="#<%=i + 1%>" data-toggle="tab"><%=taskEvaluateManagementInfo.get(i).getStd_id_evaluate()%></a></li>
						<%
							}
								}
						%>
					</ul>

					<!-- Tab panes -->

					<div class="tab-content">
						<%
							for (int i = 0; i < taskEvaluateManagementInfo.size(); i++) {
									if (taskEvaluateManagementInfo.get(i).getStd_id() == select_taskManagement.get(o).getStd_id()) {
						%>
						<div class="tab-pane fade<%if (i == 0) {%> in active<%}%>"
							id="<%=i + 1%>">
							<br>

							<div class="form-group">
								<div class="col-sm-12">
									<div class="modal-body" height="550px">
										<div class="modal-body1" id="sss">
											<%
												for (int p = 0; p < taskManagement.get(code).get(number).size(); p++) {
																if (taskManagement.get(code).get(number).get(p).getStd_id() == taskEvaluateManagementInfo
																		.get(i).getStd_id()) {
											%>
											<%=taskManagement.get(code).get(number).get(i).getTask_content()%>
											<%
												}
															}
											%>
										</div>
									</div>
								</div>
							</div>

							<div class="form-group" align="center">
								<label class="col-sm-12 control-label">평가한 점수</label>
								<div class="col-sm-12"><%=taskEvaluateManagementInfo.get(i).getSubmit_score()%></div>
							</div>
						</div>
						<%
							}
								}
						%>
						<footer> <a href="#" class="js-modal-close">닫기</a> </footer>
					</div>

				</div>
			</div>
		</div>
		<%
			}
		%>

		<%
			for (int i = 0; i < select_taskManagement.size(); i++) {
		%>

		<div id="sumbit_<%=i + 1%>" class="modal-box">
			<header> <a href="#" class="js-modal-close close">×</a>
			<center>
				<h1><%=select_taskManagement.get(i).getTask_title()%></h1>
			</center>
			</header>
			<div class="modal-body">
				<div class="panel-body">
					<!-- Nav tabs -->
					<div class="tab-content">
						<div class="tab-pane fade in active" id="SS1">
							<br>
							<div class="form-group">
								<div class="col-sm-12">
									<div class="modal-body1" id="sss">
										<%=select_taskManagement.get(i).getTask_content()%>
									</div>
									<br>
									<footer> <a href="#" class="js-modal-close">닫기</a> </footer>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
		<%
			}
		%> --%> --%>
		<script>
			$(document).ready(function() {
				$('#dataTables-example').DataTable({
					responsive : true
				});

			});
		</script>
</body>