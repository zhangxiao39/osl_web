function validform() {
	return $("#myform").validate({
		rules : {
			name : {
				required : true,
			},
			tele : {
				required : true,
			}
		},
		messages : {
			name : {
				required : "请输入商家名称",
			},
			tele : {
				required : "请输入电话",
			}
		}
	});
}
$(validform());
$(function() {
	$('#btn_submit').click(function() {
		if (validform().form()) {
			if ($("input[type='checkbox']").is(':checked')) {
				$.ajax({
					url : "/osl/bussinessregister",
					type : "POST",
					data : $("#myform").serialize(),
					success : function(data) {
						if (data == "ok") {
							swal({
								title : "注册成功！",
								text : "成功注册了一条商家等级信息。",
								type : "success"
							}, function() {
								window.location.reload();
							})
						} else {
							swal({
								title : "注册失败",
								text : "注册失败！",
								type : "error"
							})
						}
					},
					error : function(e) {
						alert("错误！！");
					}
				});
			} else {
				swal({
					title : "温馨提示",
					text : "请勾选注册协议！",
					type : "error"
				})
			}
		} else {

		}
	});
});