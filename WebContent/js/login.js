$(document).ready(function () {
    $.fn.popup.defaults.pagecontainer = '.container'

	$('#basic').popup();
	
	$('#fadeandscale').popup({
        pagecontainer: '.container',
        transition: 'all 0.3s'
    });
	
	
	$("#Register_email").keyup(function(){

		var email = $("#Register_email").val();
	
		if(email != 0)
		{
			if(isValidEmailAddress(email)) {	
				$("#mail_check").css({"background": "url('image/ok.png') no-repeat center", "backgroundSize":"30px"});
			} else {
				$("#mail_check").css({"background": "url('image/no.png') no-repeat center", "backgroundSize":"30px"});
			}
		} else {
			$("#mail_check").css({
				"background-image": "none"
			});			
		}
	
	});
	
	$("#Register_password").keyup(function(){

		var pass = $("#Register_password").val();
		var reg_pwd = /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
		
		if(pass != 0)
		{
			if(reg_pwd.test(pass)) {	
				$("#pass_check").css({"background": "url('image/ok.png') no-repeat center", "backgroundSize":"30px"});
			} else {
				$("#pass_check").css({"background": "url('image/no.png') no-repeat center", "backgroundSize":"30px"});
			}
		} else {
			$("#pass_check").css({
				"background-image": "none"
			});			
		}
	
	});
	
	$("#Register_password1").keyup(function(){

		var pass = $("#Register_password").val();
		var pass1 = $("#Register_password1").val();
		
		if(pass != 0)
		{
			if(pass == pass1) {	
				$("#pass1_check").css({"background": "url('image/ok.png') no-repeat center", "backgroundSize":"30px"});
			} else {
				$("#pass1_check").css({"background": "url('image/no.png') no-repeat center", "backgroundSize":"30px"});
			}
		} else {
			$("#pass1_check").css({
				"background-image": "none"
			});			
		}
	
	});
	
	$("#Register_num").keyup(function(){
		
		var num = $("#Register_num").val();
		var num_check = /^[0-9]+$/;
		
		if(num != 0)
		{
			if(num_check.test(num)) {	
				$("#num_check").css({"background": "url('image/ok.png') no-repeat center", "backgroundSize":"30px"});
			} else {
				$("#num_check").css({"background": "url('image/no.png') no-repeat center", "backgroundSize":"30px"});
			}
		} else {
			$("#num_check").css({
				"background-image": "none"
			});			
		}
	
	});

	$("#Register_tel").keyup(function(){
		
		var tel = $("#Register_tel").val();
		var regphone = /^\d{3}-\d{3,4}-\d{4}$/;
		
		if(tel != 0)
		{
			if(regphone.test(tel)) {	
				$("#tel_check").css({"background": "url('image/ok.png') no-repeat center", "backgroundSize":"30px"});
			} else {
				$("#tel_check").css({"background": "url('image/no.png') no-repeat center", "backgroundSize":"30px"});
			}
		} else {
			$("#tel_check").css({
				"background-image": "none"
			});			
		}
	
	});
	
	function isValidEmailAddress(emailAddress) {
		var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
		return pattern.test(emailAddress);
	}

});

