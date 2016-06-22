$(document).ready(function() {
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
	
	var subj_code_div = getCookie("code");
	var Noti_number = getCookie("Noti_Number");
	var subj_name = $('#'+subj_code_div).text();

	$('.record').hide();
	$('tr[name='+subj_code_div+']').show();
	$('.page-header').text(subj_name+"공지사항");
	
	
	$('.Subj_list').click(function(){
		var subj_code_div = this.id;
		var subj_name = $('#'+subj_code_div).text();
		$('.record').hide();	
		$('tr[name='+subj_code_div+']').show();
		$('.companion').hide();
		$('.page-header').text(subj_name+"공지사항");
		
	});
	
	$('#total').click(function(){
		$('.record').show();
		$('.page-header').text("전체 공지사항");
	});
	
	window.onload = function(){
		$("#"+Noti_number).show();
    };
	
});

