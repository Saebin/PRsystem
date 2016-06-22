$(function(){

	//$("#code").attr("class", null);
	
	$("#aa11").click(function(){
		
		var tt = $("#code_text").val();
		
		$("#code").val(tt);	
		$("#code1").val(tt);	
		$("#code").attr("class", "brush:cpp;");
		
	});
	

});