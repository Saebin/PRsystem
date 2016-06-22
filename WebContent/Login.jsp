<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width" />
<link rel="stylesheet" href="css/login-form.css" />
<link rel="stylesheet" href="css/login.css" />
<link rel="stylesheet" href="css/bootstrap.min.css" />

<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/jquery.popupoverlay.js"></script>

<title>Login</title>
</head>
<body class="align">
	<section class="container">
		    <article class="half">
			        <h1>Peer Review</h1>
			        <div class="tabs">
				            <span class="tab signin active"><a href="#signin">Sign in</a></span>
			        </div>
			        <div class="content">
				            <div class="signin-cont cont">
					                <form action="login" method="post">
						                    <input type="email" name="email" id="login__email" class="inpt" required="required" placeholder="Your email">
						                    <label for="email">Your email</label>
						                    <input type="password" name="password" id="login__password" class="inpt" required="required" placeholder="Your password">
                						    <label for="password">Your password</label>
						                    
						                    <div class="submit-wrap">
							                        <input type="submit" value="Sign in" class="submit">
						                    </div>
						                    
						                    <p class="text--center">
											Not a member?
											<a class="initialism fadeandscale_open btn btn-success" href="#fadeandscale"> Sign up now</a>
											</p>
        					        </form>
    				        </div>
			        </div>
		    </article>
		    <div class="half"><br><br><br><br><br><br><img src="image/logo/fox-02.png" alt=""  style="max-width: auto; height: 50%;"/></div>
	</section>
	
	<div id="fadeandscale" class="well">
		<h1>Sign Up!</h1>

		<form action="add" method="post" class="form form--registration">

			<div class="form__field1">
				<label class="fontawesome-envelope-alt" for="login__username"><span
					class="hidden">Email</span></label> <input id="Register_email"
					name="userid1" type="text" class="form__input" placeholder="Email"
					required> <span id="mail_check" class="check_image"></span>
			</div>
			<div class="form__field1">
				<label class="fontawesome-user" for="login__username"><span
					class="hidden">name</span></label> <input id="Register_name" name="name"
					type="text" class="form__input" placeholder="이름" required>
				<span id="name_check" class="check_image"></span>
			</div>
			<div class="form__field1">
				<label class="fontawesome-lock" for="login__username"><span
					class="hidden">Password</span></label> <input id="Register_password"
					name="userpw" type="password" class="form__input"
					placeholder="비밀번호" required> <span id="pass_check"
					class="check_image"></span>
			</div>

			<div class="form__field1">
				<label class="fontawesome-lock" for="login__username"><span
					class="hidden">Password</span></label> <input id="Register_password1"
					name="userpw1" type="password" class="form__input"
					placeholder="비밀번호 확인" required> <span id="pass1_check"
					class="check_image"></span>
			</div>

			<div class="form__field1">
				<label class="fontawesome-home" for="login__university"><span
					class="hidden">university</span></label> <input id="Register_university"
					name="university" type="text" class="form__input" placeholder="대학"
					required> <span id="uni_check" class="check_image"></span>
			</div>

			<div class="form__field1">
				<label class="fontawesome-book" for="login__username"><span
					class="hidden">Password</span></label> <input id="Register_num"
					name="stu_num" type="text" class="form__input" placeholder="학번"
					required> <span id="num_check" class="check_image"></span>
			</div>

			<div class="form__field1">
				<label class="fontawesome-phone" for="login__username"><span
					class="hidden">Password</span></label> <input id="Register_tel"
					name="userid" type="text" class="form__input" placeholder="전화번호"
					required> <span id="tel_check" class="check_image"></span>
			</div>

			성별 <select name="gender" class="styled">
				<option value="man">남성</option>
				<option value="woman">여성</option>
			</select> 직업 <select name="career" class="styled">
				<option value="professor">professor</option>
				<option value="student">student</option>
			</select> <br>
			<button class="btn btn-default" onclick="valid_check()">회원가입</button>
			<button class="fadeandscale_close btn btn-default">취소</button>

		</form>

	</div>
</body>
</html>