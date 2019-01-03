function validform() {
	return $("#myform").validate({
		rules : {
			userId : {
				required : true,
				minlength : 5,
				maxlength : 50
			},
			username : {
				required : true,
				maxlength : 200
			},
			password : {
				required : true
			},
			repassword : {
				required : true,
				equalTo : "#password"
			}
		},
		messages : {
			userId : {
				required : "请输入员工ID",
				minlength : "请输入5-50个字符",
				maxlength : "请输入5-50个字符"
			},
			username : {
				required : "请输入员工姓名",
				maxlength : "不能超过200个字符"
			},
			password : {
				required : "请输入密码"
			},
			repassword : {
				required : "请再次输入密码",
				equalTo : "两次密码输入不一致"
			}
		}
	});
}
$(validform());
$(function() {
	$('.dataTables-example').DataTable(
			{
				bFilter : true,
				ordering : false,
				iDisplayLength : 10,
				sDom : '<"html5buttons"B>fitlp',
				pageLength : 10,
				responsive : true,
				dom : '<"html5buttons"B>lTfgitp',
				buttons : [
						{
							extend : 'copy'
						},
						{
							extend : 'csv'
						},
						{
							extend : 'excel',
							title : 'ExampleFile'
						},
						{
							extend : 'pdf',
							title : 'ExampleFile'
						},

						{
							extend : 'print',
							customize : function(win) {
								$(win.document.body).addClass('white-bg');
								$(win.document.body).css('font-size', '10px');

								$(win.document.body).find('table').addClass(
										'compact').css('font-size', 'inherit');
							}
						} ]

			});
	$('#btn_submit').click(function() {
		if (validform().form()) {
			$.ajax({
				url : "/osl/user",
				type : $("#mode").val(),
				data : $("#myform").serialize(),
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "保存成功！",
							text : "成功保存了一条员工信息。",
							type : "success"
						}, function() {
							window.location.reload();
						})
					} else if (data == "exist") {
						swal({
							title : "保存失败",
							text : "该用户ID已经存在",
							type : "error"
						})
					} else {
						swal({
							title : "保存失败",
							text : "保存失败！",
							type : "error"
						})
					}
				},
				error : function(e) {
					alert("错误！！");
				}
			});
		} else {

		}
	});
	$("#myModal").on("hidden.bs.modal", function() {
		$("#userId").val("");
		$("#username").val("");
		$("#id").val("0");
		$("#mode").val("POST");
		validform().resetForm();
		$(".form-control").removeClass("error");
	});
});
function showUser(id) {
	$.ajax({
		url : "/a/sys/userManage/" + id,
		type : "get",
		success : function(data) {
			$("#user_id").val(data.user_id);
			$("#username").val(data.username);
			$("#id").val(id);
			$("#mode").val("PUT");
		},
		error : function(e) {
			alert("错误！！");
		}
	});
}
function deleteUser(id) {
	swal({
		title : "您确定要删除这条信息吗",
		text : "删除后将无法恢复，请谨慎操作！",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "是的，我要删除！",
		cancelButtonText : "让我再考虑一下…",
		closeOnConfirm : false,
		closeOnCancel : false
	}, function(isConfirm) {
		if (isConfirm) {
			$.ajax({
				url : "/osl/user/" + id,
				type : "delete",
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "删除成功！",
							text : "成功删除了一条员工信息。",
							type : "success"
						}, function() {
							window.location.reload();
						})
					} else {
						swal({
							title : "删除失败",
							text : "删除失败！",
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
				title : "已取消",
				text : "您取消了删除操作！",
				type : "error"
			})
		}
	})
}
function stopUser(id) {
	swal({
		title : "提示信息",
		text : "您确定要停止这个用户吗？",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "是的，我要停止！",
		cancelButtonText : "让我再考虑一下…",
		closeOnConfirm : false,
		closeOnCancel : false
	}, function(isConfirm) {
		if (isConfirm) {
			$.ajax({
				url : "/osl/user/stop/" + id,
				type : "POST",
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "停止成功！",
							text : "成功停止了一条员工信息。",
							type : "success"
						}, function() {
							window.location.reload();
						})
					} else {
						swal({
							title : "停止失败",
							text : "停止失败！",
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
				title : "已取消",
				text : "您取消了停止操作！",
				type : "error"
			})
		}
	})
}
function recoveryUser(id) {
	swal({
		title : "提示信息",
		text : "您确定要恢复这个员工吗？",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "是的，我要恢复！",
		cancelButtonText : "让我再考虑一下…",
		closeOnConfirm : false,
		closeOnCancel : false
	}, function(isConfirm) {
		if (isConfirm) {
			$.ajax({
				url : "/osl/user/recovery/" + id,
				type : "POST",
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "恢复成功！",
							text : "成功恢复了一条员工信息。",
							type : "success"
						}, function() {
							window.location.reload();
						})
					} else {
						swal({
							title : "恢复失败",
							text : "恢复失败！",
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
				title : "已取消",
				text : "您取消了恢复操作！",
				type : "error"
			})
		}
	})
}