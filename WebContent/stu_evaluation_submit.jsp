<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	page import="java.util.*"%>
<%@ page import="vo.SubjectInfo"%>
<%@	page import="vo.Notice"%>
<%@	page import="vo.TaskInfo"%>
<%@	page import="vo.ProfessorInfo"%>
<%@	page import="vo.StudentSubject"%>
<%@	page import="vo.StudentInfo"%>

<%
	StudentInfo stdInfo = (StudentInfo) session.getAttribute("stdInfo");
	ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>) session.getAttribute("subjInfoList");
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
				<a class="navbar-brand" href="stu_main.jsp"><img
					src="image/Logo.png" alt="" style="max-width: 36%; height: auto;" /></a>
			</div>
			<!-- /.navbar-header -->

			<ul class="nav navbar-top-links navbar-right">
				<!-- /.dropdown -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-tasks fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-tasks">
						<li><a href="#">
								<div>
									<p>
										<strong>Task 1</strong> <span class="pull-right text-muted">40%
											Complete</span>
									</p>
									<div class="progress progress-striped active">
										<div class="progress-bar progress-bar-success"
											role="progressbar" aria-valuenow="40" aria-valuemin="0"
											aria-valuemax="100" style="width: 40%">
											<span class="sr-only">40% Complete (success)</span>
										</div>
									</div>
								</div>
						</a></li>
						<li class="divider"></li>
						<li><a href="#">
								<div>
									<p>
										<strong>Task 2</strong> <span class="pull-right text-muted">20%
											Complete</span>
									</p>
									<div class="progress progress-striped active">
										<div class="progress-bar progress-bar-info" role="progressbar"
											aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"
											style="width: 20%">
											<span class="sr-only">20% Complete</span>
										</div>
									</div>
								</div>
						</a></li>
						<li class="divider"></li>
						<li><a href="#">
								<div>
									<p>
										<strong>Task 3</strong> <span class="pull-right text-muted">60%
											Complete</span>
									</p>
									<div class="progress progress-striped active">
										<div class="progress-bar progress-bar-warning"
											role="progressbar" aria-valuenow="60" aria-valuemin="0"
											aria-valuemax="100" style="width: 60%">
											<span class="sr-only">60% Complete (warning)</span>
										</div>
									</div>
								</div>
						</a></li>
						<li class="divider"></li>
						<li><a href="#">
								<div>
									<p>
										<strong>Task 4</strong> <span class="pull-right text-muted">80%
											Complete</span>
									</p>
									<div class="progress progress-striped active">
										<div class="progress-bar progress-bar-danger"
											role="progressbar" aria-valuenow="80" aria-valuemin="0"
											aria-valuemax="100" style="width: 80%">
											<span class="sr-only">80% Complete (danger)</span>
										</div>
									</div>
								</div>
						</a></li>
						<li class="divider"></li>
						<li><a class="text-center" href="#"> <strong>See
									All Tasks</strong> <i class="fa fa-angle-right"></i>
						</a></li>
					</ul> <!-- /.dropdown-tasks --></li>

				<!-- /.dropdown -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">
						<li><a href="stu_info_update.jsp"><i
								class="fa fa-user fa-fw"></i> User Profile</a></li>
						<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
						</li>
						<li class="divider"></li>
						<li><a href="Login.jsp"><i class="fa fa-sign-out fa-fw"></i>
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
						<li><a href="stu_evaluation.jsp"><i
								class="fa fa-user fa-fw"></i> 타학생평가</a></li>

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
							<div class="panel-heading">타학생 평가</div>

							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="dataTable_wrapper">

									<div>

										<div class="panel-body">
											<!-- Nav tabs -->
											<ul class="nav nav-tabs">
												<li class="active">
													<!-- 서블릿 수정해야할 부분 --> <a href="#1" data-toggle="tab">익명1</a>
												</li>
												<%
													for (int i = 2; i < 6; i++) {
												%>
												<li><a href="#<%=i%>" data-toggle="tab">익명<%=i%></a></li>
												<%
													}
												%>
											</ul>

											<!-- Tab panes -->

											<div class="tab-content">
												<div class="tab-pane fade in active" id="1">
													<br>
													<form name="evaluation" id="evaluation" method="post"
														class="form-horizontal" action="evaluationSubmit">

														<div class="form-group">
															<div class="col-sm-12">
																<div class="modal-body" height="550px">
																	sssLorem ipsum dolor sit amet,aa consectetur
																	adipisicing elit, sed do eiusmod tempor incididunt ut
																	labore et dolore magna aliqua Lorem ipsum dolor sit
																	amet, consectetur adipisicing elit, sed do eiusmod
																	tempor incididunt ut labore et dolore magna aliqua
																	Lorem ipsum dolor sit amet, consectetur adipisicing
																	elit, sed do eiusmod tempor incididunt ut labore et
																	dolore magna aliqua Lorem ipsum dolor sit amet,
																	consectetur adipisicing elit, sed do eiusmod tempor
																	incididunt ut labore et dolore magna aliqua Lorem ipsum
																	dolor sit amet, consectetur adipisicing elit, sed do
																	eiusmod tempor incididunt ut labore et dolore magna
																	aliqua Lorem ipsum dolor sit amet, consectetur
																	adipisicing elit, sed do eiusmod tempor incididunt ut
																	labore et dolore magna aliqua Lorem ipsum dolor sit
																	amet, consectetur adipisicing elit, sed do eiusmod
																	tempor incididunt ut labore et dolore magna aliqua
																	Lorem ipsum dolor sit amet, consectetur adipisicing
																	elit, sed do eiusmod tempor incididunt ut labore et
																	dolore magna aliqua v Lorem ipsum dolor sit amet,
																	consectetur adipisicing elit, sed do eiusmod tempor
																	incididunt ut labore et dolore magna aliqua Lorem ipsum
																	dolor sit amet, consectetur adipisicing elit, sed do
																	eiusmod tempor incididunt ut labore et dolore magna
																	aliqua Lorem ipsum dolor sit amet, consectetur
																	adipisicing elit, sed do eiusmod tempor incididunt ut
																	labore et dolore magna aliqua Lorem ipsum dolor sit
																	amet, consectetur adipisicing elit, sed do eiusmod
																	tempor incididunt ut labore et dolore magna aliqua v
																	Lorem ipsum dolor sit amet, consectetur adipisicing
																	elit, sed do eiusmod tempor incididunt ut labore et
																	dolore magna aliqua Lorem ipsum dolor sit amet,
																	consectetur adipisicing elit, sed do eiusmod tempor
																	incididunt ut labore et dolore magna aliqua Lorem ipsum
																	dolor sit amet, consectetur adipisicing elit, sed do
																	eiusmod tempor incididunt ut labore et dolore magna
																	aliqua Lorem ipsum dolor sit amet, consectetur
																	adipisicing elit, sed do eiusmod tempor incididunt ut
																	labore et dolore magna aliqua Lorem ipsum dolor sit
																	amet, consectetur adipisicing elit, sed do eiusmod
																	tempor incididunt ut labore et dolore magna aliqua
																	Lorem ipsum dolor sit amet, consectetur adipisicing
																	elit, sed do eiusmod tempor incididunt ut labore et
																	dolore magna aliqua Lorem ipsum dolor sit amet,
																	consectetur adipisicing elit, sed do eiusmod tempor
																	incididunt ut labore et dolore magna aliqua Lorem ipsum
																	dolor sit amet, consectetur adipisicing elit, sed do
																	eiusmod tempor incididunt ut labore et dolore magna
																	aliqua . Ut enim ad minim veniam, quis nostrud
																	exercitation ullamco laboris nisi ut aliquip ex ea
																	commodo consequat. Duis aute irure dolor in
																	reprehenderit in voluptate velit esse cillum dolore eu
																	fugiat nulla pariatur. Excepteur sint occaecat
																	cupidatat non proident, sunt in culpa qui officia
																	deserunt mollit anim id est laborum. Lorem ipsum dolor
																	sit amet, consectetur adipisicing elit, sed do eiusmod
																	tempor incididunt ut labore et dolore magna aliqua
																	Lorem ipsum dolor sit amet, consectetur adipisicing
																	elit, sed do eiusmod tempor incididunt ut labore et
																	dolore magna aliqua Lorem ipsum dolor sit amet,
																	consectetur adipisicing elit, sed do eiusmod tempor
																	incididunt ut labore et dolore magna aliqua Lorem ipsum
																	dolor sit amet, consectetur adipisicing elit, sed do
																	eiusmod tempor incididunt ut labore et dolore magna
																	aliqua Lorem ipsum dolor sit amet, consectetur
																	adipisicing elit, sed do eiusmod tempor incididunt ut
																	labore et dolore magna aliqua Lorem ipsum dolor sit
																	amet, consectetur adipisicing elit, sed do eiusmod
																	tempor incididunt ut labore et dolore magna aliqua
																	Lorem ipsum dolor sit amet, consectetur adipisicing
																	elit, sed do eiusmod tempor incididunt ut labore et
																	dolore magna aliqua Lorem ipsum dolor sit amet,
																	consectetur adipisicing elit, sed do eiusmod tempor
																	incididunt ut labore et dolore magna aliqua . Ut enim
																	ad minim veniam, quis nostrud exercitation ullamco
																	laboris nisi ut aliquip ex ea commodo consequat. Duis
																	aute irure dolor in reprehenderit in volupta orem ipsum
																	dolor sit amet, consectetur adipisicing elit, sed do
																	eiusmod tempor incididunt ut labore et dolore magna
																	aliqua Lorem ipsum dolor do eiusmod tempor incididunt
																	ut labore et dolore magna aliqua Lorem ipsum dolor sit
																	amet, consectetur adipisicing elit, sed <br>
																</div>
															</div>
														</div>

														<div class="form-group">
															<label class="col-sm-5 control-label">평가</label>
															<div class="col-sm-2">
																<select name="limit_num" class="form-control">
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
																<textarea rows="3" cols="10" class="form-control"
																	name="stu_evaluation"></textarea>
															</div>
														</div>

														<center>
															<input type="submit" class="form-control" value="제출 하기"
																style="width: 60pt;">
														</center>
													</form>


												</div>

												<%for(int j=2; j<6; j++) { %>
												<div class="tab-pane fade" id="<%=j%>">
													<br>
													<form name="evaluation" id="evaluation" method="post"
														class="form-horizontal" action="evaluationSubmit">

														<div class="form-group">
															<div class="col-sm-12">
																<div class="modal-body" height="550px">
																	sssLorem ipsum dolor sit amet,aa consectetur
																	adipisicing elit, sed do eiusmod tempor incididunt ut
																	labore et dolore magna aliqua Lorem ipsum dolor sit
																	amet, consectetur adipisicing elit, sed do eiusmod
																	tempor incididunt ut labore et dolore magna aliqua
																	Lorem ipsum dolor sit amet, consectetur adipisicing
																	elit, sed do eiusmod tempor incididunt ut labore et
																	dolore magna aliqua Lorem ipsum dolor sit amet,
																	consectetur adipisicing elit, sed do eiusmod tempor
																	incididunt ut labore et dolore magna aliqua Lorem ipsum
																	dolor sit amet, consectetur adipisicing elit, sed do
																	eiusmod tempor incididunt ut labore et dolore magna
																	aliqua Lorem ipsum dolor sit amet, consectetur
																	adipisicing elit, sed do eiusmod tempor incididunt ut
																	labore et dolore magna aliqua Lorem ipsum dolor sit
																	amet, consectetur adipisicing elit, sed do eiusmod
																	tempor incididunt ut labore et dolore magna aliqua
																	Lorem ipsum dolor sit amet, consectetur adipisicing
																	elit, sed do eiusmod tempor incididunt ut labore et
																	dolore magna aliqua v Lorem ipsum dolor sit amet,
																	consectetur adipisicing elit, sed do eiusmod tempor
																	incididunt ut labore et dolore magna aliqua Lorem ipsum
																	dolor sit amet, consectetur adipisicing elit, sed do
																	eiusmod tempor incididunt ut labore et dolore magna
																	aliqua Lorem ipsum dolor sit amet, consectetur
																	adipisicing elit, sed do eiusmod tempor incididunt ut
																	labore et dolore magna aliqua Lorem ipsum dolor sit
																	amet, consectetur adipisicing elit, sed do eiusmod
																	tempor incididunt ut labore et dolore magna aliqua v
																	Lorem ipsum dolor sit amet, consectetur adipisicing
																	elit, sed do eiusmod tempor incididunt ut labore et
																	dolore magna aliqua Lorem ipsum dolor sit amet,
																	consectetur adipisicing elit, sed do eiusmod tempor
																	incididunt ut labore et dolore magna aliqua Lorem ipsum
																	dolor sit amet, consectetur adipisicing elit, sed do
																	eiusmod tempor incididunt ut labore et dolore magna
																	aliqua Lorem ipsum dolor sit amet, consectetur
																	adipisicing elit, sed do eiusmod tempor incididunt ut
																	labore et dolore magna aliqua Lorem ipsum dolor sit
																	amet, consectetur adipisicing elit, sed do eiusmod
																	tempor incididunt ut labore et dolore magna aliqua
																	Lorem ipsum dolor sit amet, consectetur adipisicing
																	elit, sed do eiusmod tempor incididunt ut labore et
																	dolore magna aliqua Lorem ipsum dolor sit amet,
																	consectetur adipisicing elit, sed do eiusmod tempor
																	incididunt ut labore et dolore magna aliqua Lorem ipsum
																	dolor sit amet, consectetur adipisicing elit, sed do
																	eiusmod tempor incididunt ut labore et dolore magna
																	aliqua . Ut enim ad minim veniam, quis nostrud
																	exercitation ullamco laboris nisi ut aliquip ex ea
																	commodo consequat. Duis aute irure dolor in
																	reprehenderit in voluptate velit esse cillum dolore eu
																	fugiat nulla pariatur. Excepteur sint occaecat
																	cupidatat non proident, sunt in culpa qui officia
																	deserunt mollit anim id est laborum. Lorem ipsum dolor
																	sit amet, consectetur adipisicing elit, sed do eiusmod
																	tempor incididunt ut labore et dolore magna aliqua
																	Lorem ipsum dolor sit amet, consectetur adipisicing
																	elit, sed do eiusmod tempor incididunt ut labore et
																	dolore magna aliqua Lorem ipsum dolor sit amet,
																	consectetur adipisicing elit, sed do eiusmod tempor
																	incididunt ut labore et dolore magna aliqua Lorem ipsum
																	dolor sit amet, consectetur adipisicing elit, sed do
																	eiusmod tempor incididunt ut labore et dolore magna
																	aliqua Lorem ipsum dolor sit amet, consectetur
																	adipisicing elit, sed do eiusmod tempor incididunt ut
																	labore et dolore magna aliqua Lorem ipsum dolor sit
																	amet, consectetur adipisicing elit, sed do eiusmod
																	tempor incididunt ut labore et dolore magna aliqua
																	Lorem ipsum dolor sit amet, consectetur adipisicing
																	elit, sed do eiusmod tempor incididunt ut labore et
																	dolore magna aliqua Lorem ipsum dolor sit amet,
																	consectetur adipisicing elit, sed do eiusmod tempor
																	incididunt ut labore et dolore magna aliqua . Ut enim
																	ad minim veniam, quis nostrud exercitation ullamco
																	laboris nisi ut aliquip ex ea commodo consequat. Duis
																	aute irure dolor in reprehenderit in volupta orem ipsum
																	dolor sit amet, consectetur adipisicing elit, sed do
																	eiusmod tempor incididunt ut labore et dolore magna
																	aliqua Lorem ipsum dolor do e2221212iusmod tempor incididunt
																	ut labore et dolore magna aliqua Lorem ipsum dolor sit
																	amet, consectetur adipisicing elit, sed <br>
																</div>
															</div>
														</div>

														<div class="form-group">
															<label class="col-sm-5 control-label">평가</label>
															<div class="col-sm-2">
																<select name="limit_num" class="form-control">
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
																<textarea rows="3" cols="10" class="form-control"
																	name="stu_evaluation"></textarea>
															</div>
														</div>

														<center>
															<input type="submit" class="form-control" value="제출 하기"
																style="width: 60pt;">
														</center>
													</form>
												</div>
													<%} %>
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
	</div>

	<!-- jQuery -->
	<script src="bower_components/jquery/dist/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="dist/js/sb-admin-2.js"></script>

</body>
</html>