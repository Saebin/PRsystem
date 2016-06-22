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
	ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>) session.getAttribute("subjInfoList");
	ProfessorInfo profInfo = (ProfessorInfo) session.getAttribute("profInfo");
	ArrayList<TaskInfo> taskInfoList = (ArrayList<TaskInfo>) session.getAttribute("pro_taskInfoList");
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
<link href="css/profile.css" rel="stylesheet" />
<link href="css/table.css" rel="stylesheet" />
<link href="bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">
<link href="dist/css/timeline.css" rel="stylesheet">
<link href="dist/css/sb-admin-2.css" rel="stylesheet">
<link href="bower_components/morrisjs/morris.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="css/jquery.datetimepicker.css" />
<link href="bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/style2.css" media="screen"
	title="no title" charset="utf-8">
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="SE2/js/HuskyEZCreator.js"></script>
<script type="text/javascript" src="js/Notify.js"></script>
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
					<li><a href="pro_info_update.jsp"><i
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

			<div class="row">

				<div class="page-header">
					<h1>과제 수정</h1>
				</div>
				<!-- /.panel-heading -->

				<%
					for (int i = 0; i < taskInfoList.size(); i++) {
						int num = Integer.parseInt(request.getParameter("task_num"));
						if (taskInfoList.get(i).getTask_number() == num) {
				%>

				<form method="post" class="form-horizontal" action="taskupdate">
					<input type="hidden"
						value="<%=request.getParameter("task_num")%>" name="task_num">
					<div class="form-group">
						<label class="col-sm-2 control-label">과제제목</label>
						<div class="col-sm-9">
							<input type="text" name="task_name" class="form-control"
								id="task_name" size="38"
								value="<%=taskInfoList.get(i).getTask_name()%>">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">과제설명</label>
						<div class="col-sm-9">
							<textarea id="smarteditor" rows="7" name="task_outline"
								id="task_description" class="form-control"><%=taskInfoList.get(i).getTask_content()%></textarea>
						</div>
					</div>

					<%-- 	<div class="form-group">
						<label class="col-sm-2 control-label">학생 분배인원</label>
						<div class="col-sm-3">
							<select name="task_num" class="form-control">
								 <option value="1"
									<%if (Integer.parseInt(taskInfoList.get(i).getTask_group()) == 1) { %>
									selected <%}%> >1명 </option>
								<option value="2"
									<%if (Integer.parseInt(taskInfoList.get(i).getTask_group()) == 2) { %>
									selected <%}%>> 2명 </option>
								<option value="3"
									<%if (Integer.parseInt(taskInfoList.get(i).getTask_group()) == 3) { %>
									selected <%}%>> 3명 </option>
								<option value="4"
									<%if (Integer.parseInt(taskInfoList.get(i).getTask_group()) == 4) { %>
									selected <%}%>> 4명 </option>
								<option value="5"
									<%if (Integer.parseInt(taskInfoList.get(i).getTask_group()) == 5) { %>
									selected <%}%>> 5명 </option>
								<option value="6"
									<%if (Integer.parseInt(taskInfoList.get(i).getTask_group()) == 6) { %>
									selected <%}%>> 6명 </option>
								<option value="7"
									<%if (Integer.parseInt(taskInfoList.get(i).getTask_group()) == 7) { %>
									selected <%}%>> 7명 </option> 
							</select>
						</div>
					</div> --%>

					<div class="form-group">
						<label class="col-sm-2 control-label">종료 날짜</label>
						<div class="col-sm-6">
							<input name="task_end" class="some_class" type="text"
								id="some_class_2" value="<%=taskInfoList.get(i).getTask_end()%>" />
						</div>
					</div>
					<div>
					
						<input type="hidden" id="code" name="Subj_code_div"
							class="form-control" value="">

						<div class="col-md-2 col-md-offset-4">
							<input id="savebutton" type="submit" value="수정하기" class="form-control"> 
						</div>
						<div class="col-md-2 col-md-offset-1">	
							<input type="button" value="뒤로가기" class="form-control">
						</div>
					</div>
				</form>
				<%
					}
					}
				%>

			</div>
		</div>
	</div>
</body>

<script src="js/jquery.js"></script>
<script src="js/jquery.datetimepicker.js"></script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap2.min.js"></script>

<script src="js/jquery.js"></script>
<script src="js/jquery.datetimepicker.js"></script>
<script>
	$('#datetimepicker').datetimepicker({
		dayOfWeekStart : 1,
		lang : 'ko',
		disabledDates : [ '1986/01/08', '1986/01/09', '1986/01/10' ],
		startDate : '1986/01/05'
	});
	$('#datetimepicker').datetimepicker({
		value : '2015/04/15 05:03',
		step : 10
	});

	$('.some_class').datetimepicker();

	$('#default_datetimepicker').datetimepicker({
		formatTime : 'H:i',
		formatDate : 'd.m.Y',
		//defaultDate:'8.12.1986', // it's my birthday
		defaultDate : '+03.01.1970', // it's my birthday
		defaultTime : '10:00',
		timepickerScrollbar : false
	});

	$('#datetimepicker10').datetimepicker({
		step : 5,
		inline : true
	});
	$('#datetimepicker_mask').datetimepicker({
		mask : '9999/19/39 29:59'
	});

	$('#datetimepicker1').datetimepicker({
		datepicker : false,
		format : 'H:i',
		step : 5
	});
	$('#datetimepicker2').datetimepicker({
		yearOffset : 222,
		lang : 'ch',
		timepicker : false,
		format : 'd/m/Y',
		formatDate : 'Y/m/d',
		minDate : '-1970/01/02', // yesterday is minimum date
		maxDate : '+1970/01/02' // and tommorow is maximum date calendar
	});
	$('#datetimepicker3').datetimepicker({
		inline : true
	});
	$('#datetimepicker4').datetimepicker();
	$('#open').click(function() {
		$('#datetimepicker4').datetimepicker('show');
	});
	$('#close').click(function() {
		$('#datetimepicker4').datetimepicker('hide');
	});
	$('#reset').click(function() {
		$('#datetimepicker4').datetimepicker('reset');
	});
	$('#datetimepicker5').datetimepicker(
			{
				datepicker : false,
				allowTimes : [ '12:00', '13:00', '15:00', '17:00', '17:05',
						'17:20', '19:00', '20:00' ],
				step : 5
			});
	$('#datetimepicker6').datetimepicker();
	$('#destroy').click(function() {
		if ($('#datetimepicker6').data('xdsoft_datetimepicker')) {
			$('#datetimepicker6').datetimepicker('destroy');
			this.value = 'create';
		} else {
			$('#datetimepicker6').datetimepicker();
			this.value = 'destroy';
		}
	});
	var logic = function(currentDateTime) {
		if (currentDateTime && currentDateTime.getDay() == 6) {
			this.setOptions({
				minTime : '11:00'
			});
		} else
			this.setOptions({
				minTime : '8:00'
			});
	};
	$('#datetimepicker7').datetimepicker({
		onChangeDateTime : logic,
		onShow : logic
	});
	$('#datetimepicker8').datetimepicker({
		onGenerate : function(ct) {
			$(this).find('.xdsoft_date').toggleClass('xdsoft_disabled');
		},
		minDate : '-1970/01/2',
		maxDate : '+1970/01/2',
		timepicker : false
	});
	$('#datetimepicker9').datetimepicker(
			{
				onGenerate : function(ct) {
					$(this).find('.xdsoft_date.xdsoft_weekend').addClass(
							'xdsoft_disabled');
				},
				weekends : [ '01.01.2014', '02.01.2014', '03.01.2014',
						'04.01.2014', '05.01.2014', '06.01.2014' ],
				timepicker : false
			});
	var dateToDisable = new Date();
	dateToDisable.setDate(dateToDisable.getDate() + 2);
	$('#datetimepicker11').datetimepicker(
			{
				beforeShowDay : function(date) {
					if (date.getMonth() == dateToDisable.getMonth()
							&& date.getDate() == dateToDisable.getDate()) {
						return [ false, "" ]
					}

					return [ true, "" ];
				}
			});
	$('#datetimepicker12').datetimepicker(
			{
				beforeShowDay : function(date) {
					if (date.getMonth() == dateToDisable.getMonth()
							&& date.getDate() == dateToDisable.getDate()) {
						return [ true, "custom-date-style" ];
					}

					return [ true, "" ];
				}
			});
	$('#datetimepicker_dark').datetimepicker({
		theme : 'dark'
	})
</script>

</html>
