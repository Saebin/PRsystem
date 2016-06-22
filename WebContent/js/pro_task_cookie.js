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

	
	var subj_code_div = getCookie("code");
	var subj_name = $('#'+subj_code_div).text();
	
	$('.record').hide();
	$('tr[name='+subj_code_div+']').show();
	$('.page-header').text(subj_name+"과제현황");
	
	$('.record').click(function(){
		
	});
	
	$('.Subj_list').click(function(){
		var subj_code_div = this.id;
		var subj_name = $('#'+subj_code_div).text();	
		$('.record').hide();	
		$('tr[name='+subj_code_div+']').show();
		$('.companion').hide();
		$('.page-header').text(subj_name+"과제현황");
		setCookie("code",subj_code_div,"1");
	});
	$('#total').click(function(){
		$('.record').show();
		$('.page-header').text("전체 과제현황");
	});
});
