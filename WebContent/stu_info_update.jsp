<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@   page import="vo.StudentInfo"%>
<%@page import="java.io.File"%>
<%@
	page import="java.util.*" import="vo.SubjectInfo"
	import="vo.Notice" import="vo.TaskInfo" import="vo.ProfessorInfo"
	import="vo.ProfessorInfo"%>
<%
	StudentInfo stdInfo = (StudentInfo) session.getAttribute("stdInfo");
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
<style>
#panel, #flip {
	padding: 6px;
	text-align: center;
	background-color: #FFFFFF;
	border: solid 0px #c3c3c3;
	cursor: pointer;
}

#panel {
	padding: 20px;
	display: none;
}
</style>
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

					<div class="page-header">
						<h1>회원 수정</h1>
					</div>
					<form action="studentupdate" method="post" class="form-horizontal">
						<input type="hidden" name="stu_num" value="<%=stdInfo.getStdId() %>">
				
						<div class="form-group">
							<label class="col-sm-3 control-label" for="login__username">학
								번</label>
							<div class="col-sm-3">
								<input type="hidden" name="stu_num"
									value="<%=stdInfo.getStdId()%>"> <label
									class="col-sm-2 control-label"><%=stdInfo.getStdId()%></label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="login__username">직
								업</label>
							<div class="col-sm-3">
								<input type="hidden" name="caarer" value="<%=stdInfo.getStdAuth()%>">
								<label class="col-sm-2 control-label"><%=stdInfo.getStdAuth()%></label>
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-3 control-label" for="login_username">이메일</label>
							<div class="col-sm-3">
								<input id="Register_email" name="userid1" type="email"
									class="form-control" placeholder="Email"
									value="<%=stdInfo.getStdEmail()%>" required> <span
									id="mail_check" class="check_image"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" for="login__username">이름</label>
							<div class="col-sm-3">
								<input id="Register_name" name="name"
									value=<%=stdInfo.getStdName()%> type="text"
									class="form-control" placeholder="이름" required> <span
									id="name_check" class="check_image"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" for="login__username">패스워드</label>
							<div class="col-sm-3">
								<input id="Register_password" name="userpw" type="password"
									class="form-control" placeholder="비밀번호"
									value="<%=stdInfo.getStdPwd()%>" required> <span
									id="pass_check" class="check_image"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" for="login__username">패스워드
								확인</label>
							<div class="col-sm-3">
								<input id="Register_password1" name="userpw1" type="password"
									class="form-control" placeholder="비밀번호 확인"
									value="<%=stdInfo.getStdPwd()%>" required> <span
									id="pass1_check" class="check_image"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="login__username">연락처</label>
							<div class="col-sm-3">
								<input id="Register_tel" name="phone" type="text"
									class="form-control" placeholder="전화번호"
									value="<%=stdInfo.getStdPhone()%>" required> <span
									id="tel_check" class="check_image"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" for="login__image">
								내 이미지 </label>
							<div class="col-sm-3">
								<input type="file" name="filename1" class="form-control"
									id="filename1" size="40">
							</div>
						</div>
						<br> <br>

						<div class="form-group">
							<label class="col-sm-3 control-label"></label>
							<button class="btn btn-default" onclick="valid_check()">수정하기</button>
							<button class="btn btn-default" onclick="window.history.back()">취소</button>
						</div>
						<br> <br> <br> <br> <br> <br> <br>
						<br> <br> <br> <br> <br> <br> <br>
						<br> <br> <br> <br>

					</form>

				</div>
			</div>

			<!-- /#page-wrapper -->

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