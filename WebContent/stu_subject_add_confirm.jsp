<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@   page import="java.util.*"%>
<%@ page import="vo.SubjectInfo"%>
<%@   page import="vo.Notice"%>
<%@   page import="vo.TaskInfo"%>
<%@   page import="vo.ProfessorInfo"%>
<%@   page import="vo.StudentSubject"%>
<%@   page import="vo.StudentInfo"%>

<%
   StudentInfo stdInfo = (StudentInfo) session.getAttribute("stdInfo");
   ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>)session.getAttribute("subjInfoList");
   ArrayList<SubjectInfo> select_subjRegiList = (ArrayList<SubjectInfo>)session.getAttribute("select_subjRegiList");
   
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
                loop:true
            });
      });
    </script>
<script>   
        $(document).ready(function() {
         $("#content-slider").lightSlider({
                loop:true
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
										<div class="profile-usertitle-name"><%=stdInfo.getStdName() %></div>
										<div class="profile-usertitle-job"><%=stdInfo.getStdAuth() %></div>
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
						<h1>과목 상세정보</h1>
					</div>
					<form action="subjectregist" method="post" class="form-horizontal" role="form">

						<input type="hidden" name="num" value="<%=stdInfo.getStdId() %>">


						<div class="form-group">
							<label class="col-sm-3 control-label">과목명</label>
							<div class="col-sm-3">
								<div class="col-sm-0 control-label"><%=select_subjRegiList.get(0).getSubj_name() %></div>

							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">과목교수</label>
							<div class="col-sm-3">
								<div class="col-sm-0 control-label"><%=select_subjRegiList.get(0).getProf_id() %></div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">과목설명</label>
							<div class="col-sm-3">
								<div class="col-sm-0 control-label"><%=select_subjRegiList.get(0).getSubj_outline() %></div>
							</div>
						</div>
						<hr>
						<br>
						<div<% if(session.getAttribute("fail").equals("fail")){%> class="form-group has-error"<%} %>>
							<label class="col-sm-3 control-label" for="inputError">등록코드 입력</label>
							<div class="col-sm-3">
								<input type="text" name="regist" id="inputError"
									class="form-control" placeholder="등록코드를 입력하시오" required> 
							</div>
							
						</div>
						
						<div class="form-group">
							<label class="col-sm-3 control-label"></label><% if(session.getAttribute("fail").equals("fail")){%>
							<div class="col-sm-3"><div class="alert alert-danger">다시입력하세요!</div></div><%} %>
						</div>
						<input type="hidden" name="num" value="<%= stdInfo.getStdId() %>">
						<input type="hidden" name="regist_code" value="<%= session.getAttribute("regist_code") %>">
						<input type="hidden" name="subj_code_div" value="<%= session.getAttribute("subj_code_div") %>">
						<input type="hidden" name="confirm" value="Y">
						<br>
						<br> <label class="col-sm-5 control-label">
							<button class="btn btn-default">과목추가</button>
							<button class="btn btn-default" onclick="window.history.back()">취소</button>
						</label> <br> <br>
					</form>

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

<!-- 과목코드 확인 -->
<script type="text/javascript">
function button_event() {
   if(confirm("과목코드 확인") ==true) {
      return;
   } else {
      location.replace('Login.jsp')
   }
}
</script>




</html>