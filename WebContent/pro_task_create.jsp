<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="vo.SubjectInfo"%>
<%
	ArrayList<SubjectInfo> subjInfoList = (ArrayList<SubjectInfo>) session.getAttribute("subjInfoList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="">

<title>Professor Page</title>
<script src="bower_components/jquery/dist/jquery.min.js"></script>

<script src="js/pro_task_create.js"></script>
<meta name="viewport" content="width=device-width" />
<link href="dist/css/sb-admin-3.css" rel="stylesheet">
<link href="bower_components/morrisjs/morris.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="css/jquery.datetimepicker.css" />
<link href="bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">

<style type="text/css">
.custom-date-style {
	background-color: red !important;
}
</style>

</head>
<body>

	<div id="page-wrapper">
		<div class="row">

			<div class="page-header">
				<h1>과제 생성</h1>
			</div>
			<!-- /.panel-heading -->
			<div>
				<form name="task_create" id="task_create" method="post"
					class="form-horizontal" action="taskcreate">

					<div class="form-group">
						<label class="col-sm-2 control-label">과제제목</label>
						<div class="col-sm-8">
							<input type="text" name="task_name" class="form-control"
								id="task_name" size="38">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">과목선택</label>
						<div class="col-sm-8">
							<select name="Subj_code_div" class="form-control">
								<%
									for (int i = 0; i < subjInfoList.size(); i++) {
								%>
								<option value="<%=subjInfoList.get(i).getSubj_code_div()%>"><%=subjInfoList.get(i).getSubj_name()%>
									<%
										}
									%>
								
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">과제설명</label>
						<div class="col-sm-8">
							<textarea rows="6" cols="40" name="task_description"
								id="task_description" class="form-control"></textarea>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">과제유형</label>
						<div class="col-sm-8">
							<select name="task" class="form-control">
								<option value="code1">소스코드
							</select>
						</div>
					</div>

<!-- 					<div class="form-group">
						<label class="col-sm-2 control-label">학생 분배인원</label>
						<div class="col-sm-8">
							<select name="task_num" class="form-control">
								<option value="1">1명
								<option value="2">2명
								<option vlaue="3">3명
								<option value="4">4명
								<option value="5">5명
								<option value="6">6명
								<option value="7">7명
							</select>
						</div>
					</div> -->

					<div class="form-group">
						<label class="col-sm-2 control-label">종료날짜설정</label>
						<div class="col-sm-8">
							<input name="task_end" class="some_class" type="text"
								id="some_class_2" />
						</div>
					</div>
					<div class="tt1">
						<input type="button" value="과목 생성" class="tt" style="width: 65pt;"
							onClick='receiver_onclick()'> <input type="button"
							value="취소" class="tt" style="width: 50pt;" onClick='self.close()'>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

<!-- 부모jsp로 값을 넘겨줌 -->
<script language="javaScript">
	function receiver_onclick() {
	
		opener.document.all.task_name.value = document.all.task_name.value;
		opener.document.all.task_description.value = document.all.task_description.value;
		opener.document.all.Subj_code_div.value = document.all.Subj_code_div.value;
		opener.document.all.task_end.value = document.all.task_end.value;
		opener.page_move(document.all.task_name.value,
				document.all.task_description.value,
				document.all.Subj_code_div.value,
				document.all.task_end.value)
		self.close();
	}
</script>
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