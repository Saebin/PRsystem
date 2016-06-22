$(function(){
$('select.styled').customSelect();
});

function valid_check()
{
	if(document.getElementById("Register_email").value==""){
		alert("이메일을 입력해주요.");
		document.getElementById("Register_email").focus();
		return false;
	}
	
	var regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/; 	
	if(regex.test(document.getElementById("Register_email").value) === false) {
		alert("이메일을 형식을 확인하세요");
		document.getElementById("Register_email").focus();
		return false;
	}
	//이메일 유효성검사
	if(document.getElementById("Register_password").value == ""){
		alert("패스워드를 입력하세요");
		document.Register.userpw.focus();
		return false;
	}
	var reg_pwd = /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
	if(!reg_pwd.test(Rdocument.getElementById("Register_password").value)) {
		alert("영문,숫자 혼합하여 6자리이상 입력하세요");
		document.Register.userpw.focus();
		return false;
	}
	if(document.getElementById("Register_password1").value != document.getElementById("Register_password").value){
		alert("패스워드를 확인하세요");
		document.Register.userpw1.focus();
		return false;
	}
	//비밀번호 유효성검사
	if(document.getElementById("Register_university").value==""){
		alert("학교정보를 확인하시오.");
		document.Register.university.focus();
		return false;
	}
	//학교정보 유효성검사
	if(document.getElementById("Register_num").value==""){
		alert("학번을 확인하세요");
		document.Register.stu_num.focus();
		return false;
	}
	var num_check = /^[0-9]+$/;
	if(!num_check.test(document.getElementById("Register_num").value)){
		alert("학번에 숫자만 입력하세요");
		document.Register.stu_num.focus();
		return false;
	}
	//학번 유효성검사
	if(document.Register.gender[0].checked==false && document.Register.gender[1].checked==false) {
		alert("성별을 선택하세요");
		document.Register.gender.focus();
		return false;
	}
	//성별 유효성검사
	if(document.Register.phone_num.value==""){
		alert("연락처를 확인하세요");
		document.Register.phone_num.focus();
		return false;
	}
	var regphone = /^\d{3}-\d{3,4}-\d{4}$/;
	if(!regphone.test(Register.phone_num.value)){
		alert("휴대폰번호 형식을 확인하세요");
		document.Register.phone_num.focus();
		return false;
	}
	//연락처 유효성검사

	if(document.Register.career[0].checked==false && document.Register.career[1].checked==false) {
		alert("직업을 선택하세요");
		document.Register.career.focus();
		return false;
	}
	// 성별 유효성 검사
	
	document.Register.submit();
	
}