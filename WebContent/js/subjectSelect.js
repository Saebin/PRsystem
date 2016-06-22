$(function() {

	//$("#code").attr("class", null);
	var ids;
	$("select").click(function(){
		ids = $(this).val();
	});
	$("select").change(function() {
//		$("#"+id).hide();
		var id = $(this).val();
		$("#"+id).show();
		$("#"+ids).hide();
		
	});
});