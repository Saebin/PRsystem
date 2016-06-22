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
   ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>)session.getAttribute("subjInfoList");
   ArrayList<Notice> noticeList = (ArrayList<Notice>) session.getAttribute("noticeList");
   ArrayList<TaskInfo> taskInfoList = (ArrayList<TaskInfo>) session.getAttribute("pro_taskInfoList");
   ProfessorInfo profInfo = (ProfessorInfo) session.getAttribute("profInfo");
   
   Map<String,ArrayList<StudentInfo>> inClassStd = (Map<String,ArrayList<StudentInfo>>)session.getAttribute("inClassStd");
   Iterator<String> keys = inClassStd.keySet().iterator();
   
   Map<String,Map<Integer,ArrayList<TaskManagementInfo>>> taskManagement = (Map<String,Map<Integer,ArrayList<TaskManagementInfo>>>)session.getAttribute("AlltaskManagement");
   // 메인화면에 공지사항과 과목 보여주는 최대 갯수
   int viewnum = 5;

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


<title>Professor Page</title>
<script type="text/javascript"
   src="bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="js/SubjectInfo.js"></script>
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
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
               <li><a href="studentList"><i class="fa fa-user fa-fw"></i>
                     학생관리</a></li>

            </ul>
         </div>
         <!-- /.sidebar-collapse -->
      </div>
      <!-- /.navbar-static-side --> </nav>

      <div id="page-wrapper">
         <div class="row">
            <div class="col-lg-12">
               <h1 class="page-header">데이터베이스</h1>
               <div class="pull-right">

                  <a href="pro_subject_create.jsp"><i class="fa fa-edit fa-fw"></i>
                     <right> 과목생성</right> </a> <a href="pro_subject_update.jsp"><i
                     class="fa fa-wrench fa-fw"></i> <right> 과목수정</right> </a>
               </div>
            </div>
            <!-- /.col-lg-12 -->
         </div>
         <!-- /.row -->
         <div class="row" id="cl">
            <% String[] str = {"panel panel-primary","panel panel-green", "panel panel-red", "panel panel-yellow","panel panel-primary","panel panel-green", "panel panel-blue", "panel panel-yellow"};
            
            for(int i=0; i<subjInfoList.size(); i++){ %><%-- <%for(int i=0; i<subjInfoList.size(); isubj_code_div++){%> --%>
            <div class="col-lg-3 col-md-6">
               <div class="<%=str[i]%>">
                  <div class="panel-heading">
                     <div class="row">
                        <div class="col-xs-3">
                           <i id="image_<%=subjInfoList.get(i).getSubj_code_div()%>"
                              class="fa fa-folder fa-5x" name="image"></i>
                        </div>
                        <div class="col-xs-9 text-right">
                           <div class="huge"><%=subjInfoList.get(i).getSubj_name()%></div>
                           <div><%=subjInfoList.get(i).getSubj_code_div()%></div>
                        </div>
                     </div>
                  </div>
                  <a href="#" class="subject"
                     id="<%=subjInfoList.get(i).getSubj_code_div()  %>">
                     <div class="panel-footer">
                        <span class="pull-left">View Details</span> <span
                           class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                        <div class="clearfix"></div>
                     </div>
                  </a>
               </div>
            </div>
            <%}%>
         </div>
         <!-- /.row -->
         <div class="row">
            <div class="col-lg-7">
               <div class="panel panel-default">
                  <div class="panel-heading">
                     <i class="fa fa-edit fa-fw"></i> 과제현황
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
                           <li><a href="pro_task_situation.jsp"> <i
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
                              <th width="40%">진행률</th>

                           </tr>
                        </thead>
                        <tbody id="task">
                           <%for(int i=0 ; i<taskInfoList.size(); i++){ %>
                           <tr name="task"
                              class="<%=taskInfoList.get(i).getSubj_code_div()%>">
                              <td><a href="pro_task_situation.jsp"><%=taskInfoList.get(i).getTask_name()%></a></td>
                              <% for(int j=0; j < subjInfoList.size(); j++) { %>

                              <% if(taskInfoList.get(i).getSubj_code_div().equals(subjInfoList.get(j).getSubj_code_div())) { %>
                              <% double div= (double)taskManagement.get(taskInfoList.get(i).getSubj_code_div()).get(taskInfoList.get(i).getTask_number()).size() / (double)subjInfoList.get(j).getCurr_num() * 100; %>
                              <td><%=(int)div %>%</td>
                              <% }}%>
                           </tr>
                           <%} %>
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
                           <li><a href="pro_notify.jsp"> <i
                                 class="fa fa-external-link fa-fw"></i> detail-link
                           </a></li>
                        </ul>
                     </div>
                  </div>
                  <!-- /.panel-heading -->
                  <div class="panel-body">
                     <div class="list-group">
                        <%for(int i=0; i<noticeList.size(); i++) { 
                           %>
                        <div name="notice"
                           class=<%=noticeList.get(i).getSubj_code_div()%>
                           id=<%=noticeList.get(i).getSubj_code_div()%> _ <%=i%>>
                           <a href="pro_notify.jsp" class="list-group-item"> <i
                              class="fa fa-comment fa-fw"></i> <%=noticeList.get(i).getNoti_name() %>
                              <span class="pull-right text-muted small"><em><%=noticeList.get(i).getNoti_cre_date() %></em>
                           </span>
                           </a>
                        </div>
                        <%} %>

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
         <!-- /.row -->
         <div class="row">
            <div class="col-lg-12">
               <div class="panel panel-default">
                  <div class="panel-heading">
                     <i class="fa fa-user fa-fw"></i> 학생관리
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
                           <li><a href="studentList"> <i
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
                              <th width="25%">학번</th>
                              <th width="25%">이름</th>
                              <th width="15%">이메일</th>
                              <th width="15%">전화번호</th>
                           </tr>
                        </thead>
                        <tbody>
                           <%while( keys.hasNext() ){   
                              String key = keys.next();
                              for(int i=0; i<inClassStd.get(key).size(); i++){%>
                           <tr name="std_managment" class="<%=key %>">
                              <td><%= inClassStd.get(key).get(i).getStdId() %></td>
                              <td><%= inClassStd.get(key).get(i).getStdName() %></td>
                              <td><%= inClassStd.get(key).get(i).getStdEmail() %></td>
                              <td><%= inClassStd.get(key).get(i).getStdPhone() %></td>
                           </tr>
                           <% }} %>
                        </tbody>
                     </table>
                  </div>
                  <!-- /.panel-body -->
               </div>
               <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
         </div>
      </div>
   </div>
</body>
</html>