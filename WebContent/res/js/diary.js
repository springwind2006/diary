$(function(){
	//数据提交
	$("#submit_btn").click(function(){
		var data=$("#doform").serializeArray(),
			cid="",cvalue="",res="",tips="",loader;
		
		
		for(var k in data){
			id=data[k].name;
			cvalue=data[k].value;
			if($("#"+id).attr("data-input")!="optional"&&$.trim(cvalue)===""){
				tips=$("#"+id).attr("placeholder")||$("#"+id).attr("data-tips");
				weui.topTips(tips, 'success');
				$("#"+id).focus();
				return false;
			}
		}
		
		loader=weui.loading("提交中...");
		$.post($("#doform").attr("action"),$("#doform").serialize(),function(res){
			var is_reset=res.errcode=="0" && (typeof(res.noreset)=="undefined"||res.noreset=="0"),
				msg=res.errcode=="0"?"成功":res.errmsg;
			loader.hide();
			console.log(res);
			weui.toast(msg,2000);
			if(is_reset){
				$("#doform")[0].reset();
			}
			if(typeof(res.url)!="undefined" && res.url!=""){
				setTimeout(function(){
					window.location=res.url;
				},2000);
			}
		},"json");
	});
});