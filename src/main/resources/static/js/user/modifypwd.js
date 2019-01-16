$(function() {
	$.validator.addMethod("passwordCheck", function(value, element) {
		var returnVal = false;
		var userblank = /^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)[0-9A-Za-z]{5,18}$/;
		return this.optional(element) || (userblank.test(value));
	}, "需包含数字和大小写字母中至少两种字符的5-18位字符");
	$("#myform").validate({
		rules : {
			oldpwd : {
				required : true,
			},
			newpwd : {
				required : true,
				passwordCheck : true,
			},
			renewpwd : {
				required : true,
				equalTo : "#newpwd"
			}
		},
		messages : {
			oldpwd : {
				required : "请输入旧密码",
			},
			newpwd : {
				required : "请输入新密码"
			},
			renewpwd : {
				required : "请再次输入密码",
				equalTo : "两次密码输入不一致"
			}
		}
	});
});

$.validator.setDefaults({
	submitHandler : function() {
		$.ajax({
			url : "/osl/sys/modifypwd",
			type : "POST",
			data : $("#myform").serialize(),
			success : function(data) {
				if (data == "ok") {
					swal({
						title : "修改成功！",
						text : "成功修改了密码。",
						type : "success"
					}, function() {
						window.location.reload();
					})
				} else if (data == "errorpwd") {
					swal({
						title : "修改失败",
						text : "旧密码不正确！",
						type : "error"
					})
				} else {
					swal({
						title : "修改失败",
						text : "修改密码失败！",
						type : "error"
					})
				}
			},
			error : function(e) {
				alert("错误！！");
			}
		});
	}
});