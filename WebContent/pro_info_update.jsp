<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.io.File"%>
    <%@	page import="vo.ProfessorInfo"%>
<%@
	page import="java.util.*" import="vo.SubjectInfo"
	import="vo.Notice" import="vo.TaskInfo" import="vo.ProfessorInfo" import="vo.ProfessorInfo"
%>
<% 
	ProfessorInfo profInfo = (ProfessorInfo) session.getAttribute("profInfo");
%>

<% ArrayList<ProfessorInfo> ProfessorInfoList = (ArrayList<ProfessorInfo>) session.getAttribute("ProfessorInfoList");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원수정</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<meta name="viewport" content="width=device-width" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/jquery.popupoverlay.js"></script>

<link href="css/table.css" rel="stylesheet" />
<link href="css/profile.css" rel="stylesheet" />
<link href="bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
 <link href="bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">
<link href="dist/css/timeline.css" rel="stylesheet">
<link href="dist/css/sb-admin-2.css" rel="stylesheet">
<link href="bower_components/morrisjs/morris.css" rel="stylesheet">
<link href="bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/style2.css" media="screen" title="no title" charset="utf-8">


</head>

<body>
	<div id="wrapper">

		<!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="pro_main.jsp"><img src="image/Logo.png" alt="" style="max-width: 36%; height: auto;"/></a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-tasks fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-tasks">
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 1</strong>
                                        <span class="pull-right text-muted">40% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                            <span class="sr-only">40% Complete (success)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 2</strong>
                                        <span class="pull-right text-muted">20% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                            <span class="sr-only">20% Complete</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 3</strong>
                                        <span class="pull-right text-muted">60% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                            <span class="sr-only">60% Complete (warning)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 4</strong>
                                        <span class="pull-right text-muted">80% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                            <span class="sr-only">80% Complete (danger)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Tasks</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-tasks -->
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="Login.jsp"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
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
					<li><a href="main"><i
							class="fa fa-dashboard fa-fw"></i> 메인페이지</a></li>
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
					
        <div class="page-header">
		<h1>회원 수정</h1>
		 </div>
		<form action="professorupdate" method="POST" class="form-horizontal" >
			<div class="form-group">
				<label class="col-sm-5 control-label" for="login__username">학 번</label> 
				<div class="col-sm-3">
				<input type="hidden" name="stu_num" value="<%=profInfo.getProfId() %>">
				<label class="col-sm-2 control-label"><%=profInfo.getProfId() %></label>				
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-5 control-label" for="login__username">직 업</label> 
				<div class="col-sm-3">
				<input type="hidden" name="career" value="<%=profInfo.getProfAuth() %>">
				<label class="col-sm-2 control-label"><%=profInfo.getProfAuth() %></label>				
				</div>
			</div>					
			<div class="form-group">
				 <label class="col-sm-5 control-label" for="login_username">이메일</label>
					<div class="col-sm-3">
					<input id="Register_email" name="userid1" type="email" class="form-control" placeholder="Email" value="<%=profInfo.getProfEmail() %>" required> 
					<span id="mail_check" class="check_image"></span>
					</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-5 control-label" for="login__username">이름</label>
				<div class="col-sm-3">
				 <input id="Register_name" name="name" value="<%=profInfo.getProfName() %>" type="text" class="form-control" placeholder="" required>
				<span id="name_check" class="check_image"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-5 control-label" for="login__username">패스워드</label> 
				<div class="col-sm-3">
				<input id="Register_password" name="userpw" type="password" class="form-control" value="<%=profInfo.getProfPwd() %>" required>
				 <span id="pass_check" class="check_image"></span>
				 </div>
			</div>

			<div class="form-group">
				<label class="col-sm-5 control-label" for="login__username">패스워드 확인</label> 
				<div class="col-sm-3">
				<input id="Register_password1" name="userpw1" type="password" class="form-control" value="<%=profInfo.getProfPwd() %>" required> 
					<span id="pass1_check" class="check_image"></span>
					</div>
			</div>

			<div class="form-group">
				<label class="col-sm-5 control-label" for="login__username">연락처</label> 
				<div class="col-sm-3">
				<input id="Register_tel" name="userid" type="text" class="form-control" placeholder="전화번호" value="<%=profInfo.getProfPhone() %>" required> 
				<span id="tel_check" class="check_image"></span>
				</div>
			</div>
			
			<div class="form-group">	
			<label class="col-sm-5 control-label" for="login__image"> 내 이미지  </label>
			<div class="col-sm-3">
			<input type="file" name="filename1" class="form-control"
													id="filename1" size="40">
			</div>
			</div><br>
			<center>
			<button class="btn btn-default" onclick="valid_check()">수정하기</button>
			<button class="btn btn-default" onclick="window.history.back()">취소</button>
			</center>
		<br><br>
		</form>
		
			</div>
		</div>


		<!-- /#page-wrapper -->

	</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap2.min.js"></script>
</body>

</html>