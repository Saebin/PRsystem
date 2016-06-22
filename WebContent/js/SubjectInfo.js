$(document).ready(function() {
	
	function setCookie(cname,cvalue,exdays) {
	    var d = new Date();
	    d.setTime(d.getTime() + (exdays*24*60*60*1000));
	    var expires = "expires=" + d.toGMTString();
	    document.cookie = cname+"="+cvalue+"; "+expires;
	}
	
	function getCookie(cname){
		var name = cname + "=";
		var ca = document.cookie.split(';');
		for(var i=0; i<ca.length;i++){
		var c = ca[i];
		while(c.charAt(0)==' ') c = c.substring(1);
		if(c.indexOf(name) == 0) return c.substring(name.length,c.length);
		}
		return "";
	}
	
	function deleteCookies() {
	    var count = 0; // 쿠키 개수
	
	    // 쿠키가 있다면...
	    if (document.cookie != "") {
	        var cookies = document.cookie.split("; ");
	        count = cookies.length; // 쿠키 개수
	         
	        // 쿠키에 대한 날짜를 -1로 설정하면 쿠키 바로 소멸됨
	        var expireDate = new Date();
	        expireDate.setDate(expireDate.getDate() -1); //-1: 쿠키 삭제
	
	        for (var i=0; i<count; i++) {
	            var cookieName = cookies[i].split("=")[0];
	            document.cookie = cookieName + "=; path=/; expires=" + expireDate.toGMTString(); // 쿠키 이름을 nothing으로 설정 및 소멸시키를 -1  
	        }
	    }
	
	    // 삭제된 쿠키 개수 반환
	    return count;
	}
	
	$('div[name=notice]').hide();
	$('tr[name=task]').hide();
	$('tr[name=std_managment]').hide();

	$('.subject').click(function(){
		var subj_code_div = this.id;

		/*var tt = document.getElementsByClassName('subject');
		//alert(tt[0].id);
		if($('#image_'+subj_code_div).attr('class')=='fa fa-folder fa-5x'){
			
			$('#image_'+subj_code_div).removeClass('fa fa-folder fa-5x').addClass('fa fa-folder-open fa-5x');
		} else {
			for(var i=0;i<tt.length;i++){
				$('#image_'+tt[0].id).removeClass('fa fa-folder-open fa-5x').addClass('fa fa-folder fa-5x');
			}
		}
		*/
		setCookie("code",subj_code_div,"1");
		
		if($('.'+subj_code_div).css("display") == "none"){
			
			$('tr[name=task]').hide();
			$('div[name=notice]').hide();
			$('tr[name=std_managment]').hide();
			$('.'+subj_code_div).show();
			//$('.page-header').text("공지사항");
		} 
	});
	
	var check = document.getElementsByClassName("subject");
	check[0].click();
	
	$('div[name=notice]').click(function(){
		var getNoti_Number = this.id;
		setCookie("Noti_Number",getNoti_Number,"1");
	});
	
});

