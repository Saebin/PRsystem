<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@	page import="java.util.*" %>
<%@ page import="vo.SubjectInfo"%>
<%@	page import="vo.Notice" %>
<%@	page import="vo.TaskInfo" %>
<%@	page import="vo.ProfessorInfo"%>
<%@	page import="vo.StudentSubject"%>
<%@	page import="vo.StudentInfo"%>

<%
	StudentInfo stdInfo = (StudentInfo) session.getAttribute("stdInfo");
	ProfessorInfo profInfo = (ProfessorInfo) session.getAttribute("profInfo");	
	//ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>)session.getAttribute("subjInfoList");
	ArrayList<SubjectInfo> stdSubjList = (ArrayList<SubjectInfo>) session.getAttribute("stdSubjList");
	Map<String, ArrayList<Notice>> noticeList = (Map<String, ArrayList<Notice>>) session
			.getAttribute("noticeList");
	//해당 과목 공지사항 불러오기
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>공지사항</title>
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Student Page</title>
    <link href="css/profile.css" rel="stylesheet" />
    <link href="css/table.css" rel="stylesheet"/>
    <link href="css/stu_main.css" rel="stylesheet"/>
    <link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">
    <link href="dist/css/timeline.css" rel="stylesheet">
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">
    <link href="bower_components/morrisjs/morris.css" rel="stylesheet">
    <link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script src="bower_components/jquery/dist/jquery.min.js"></script>
	<script src="js/detail_Notify.js"></script>
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
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="stu_main.jsp"><img src="image/Logo.png" alt="" style="max-width: 36%; height: auto;"/></a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">                
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="stu_info_update.jsp"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        <li class="divider"></li>
                        <li><a href="logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
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
									<div class="profile-usertitle-name"><%=stdInfo.getStdName() %></div>
									<div class="profile-usertitle-job"><%=stdInfo.getStdAuth() %></div>
								</div>
							</div>
						</div>
						</li>
                           <li>
                            <a href="stu_main.jsp"><i class="fa fa-dashboard fa-fw"></i> 메인페이지</a>
                        </li>
                        <li>
                            <a href="stu_task_situation.jsp"><i class="fa fa-edit fa-fw"></i> 과제현황</a>
                        </li>
                        <li>
                            <a href="stu_notify.jsp"><i class="fa fa-bell-o fa-fw"></i> 공지사항</a>
                        </li>
                      
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
        
        
	<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">공지사항</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading" >공지사항
			
                        </div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
									<br>
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
									
										<tr>
											<th width="5%"></th>
											<th width="15%">과목</th>
											<th width="50%">제목</th>
											<th width="15%">날짜</th>
											<th width="15%">교수명</th>
										</tr>
										
									</thead>
									<!-- Table Header -->

									<!-- Table Body -->
									<tbody>
										<%for (int j = 0; j < stdSubjList.size(); j++) {
											String code = stdSubjList.get(j).getSubj_code_div();
											for (int i = 0; i < noticeList.get(code).size(); i++) {%> 
										<tr class="record"  name="<%=noticeList.get(code).get(i).getSubj_code_div() %>">
											<td></td>
											<td><%
												if(stdSubjList.get(j).getSubj_code_div().equals(noticeList.get(code).get(i).getSubj_code_div())){
													%><%=stdSubjList.get(j).getSubj_name()%><%
																							
											}
											%></td>
											<td><%=noticeList.get(code).get(i).getNoti_name() %></td>
											<td><%=noticeList.get(code).get(i).getNoti_cre_date() %></td>
											<td></td>
										</tr>
										<tr class="companion" id="<%=noticeList.get(code).get(i).getSubj_code_div() %>_<%=i%>" name="<%=noticeList.get(code).get(i).getSubj_code_div() %>">
											<td class="output" colspan="5"> 
							                    <p><%=noticeList.get(code).get(i).getNoti_comment() %>
							                   	</p>	
						                   	</td>	
					                   	</tr>
										<%} }%>
										<!-- Darker Table Row -->
									</tbody>
									<!-- Table Body -->

								</table>
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