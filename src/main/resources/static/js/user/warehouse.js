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
function validform_admin() {
	return $("#adminform").validate({
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
				required : "请输入ID",
				minlength : "请输入5-50个字符",
				maxlength : "请输入5-50个字符"
			},
			username : {
				required : "请输入名称",
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
$(validform_admin());
$(function() {
	$('#btn_submit').click(function() {
		if (validform().form()) {
			$.ajax({
				url : "/osl/warehouse",
				type : $("#mode").val(),
				data : $("#myform").serialize(),
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "保存成功！",
							text : "成功保存了一条商家等级信息。",
							type : "success"
						}, function() {
							window.location.reload();
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
		$("#name").val("");
		$("#address").val("");
		$("#postcode").val("");
		$("#url").val("");
		$("#tele").val("");
		$("#tax").val("");
		$("#email").val("");
		$("#contacts").val("");
		$("#gradeId").val("1");
		$("#id").val("0");
		$("#mode").val("POST");
		validform().resetForm();
		$(".form-control").removeClass("error");
	});
	$('#btn_submit_admin').click(function() {
		if (validform_admin().form()) {
			$.ajax({
				url : "/osl/useradmin",
				type : "POST",
				data : $("#adminform").serialize(),
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "保存成功！",
							text : "成功保存了一条商家管理员信息。",
							type : "success"
						}, function() {
							window.location.reload();
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
	$("#adminModal").on("hidden.bs.modal", function() {
		$("#userId").val("");
		$("#username").val("");
		$("#password").val("");
		$("#repossword").val("");
		validform_admin().resetForm();
		$(".form-control").removeClass("error");
	});
});
function showInfo(id) {
	$.ajax({
		url : "/osl/bussiness/" + id,
		type : "get",
		success : function(data) {
			$("#name").val(data.name);
			$("#address").val(data.address);
			$("#postcode").val(data.postcode);
			$("#url").val(data.url);
			$("#tele").val(data.tele);
			$("#tax").val(data.tax);
			$("#email").val(data.email);
			$("#contacts").val(data.contacts);
			$("#gradeId").val(data.gradeId);
			$("#id").val(id);
			$("#mode").val("PUT");
		},
		error : function(e) {
			alert("错误！！");
		}
	});
}
function deleteInfo(id) {
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
				url : "/osl/bussiness/" + id,
				type : "delete",
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "删除成功！",
							text : "成功删除了一条商家等级信息。",
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
function addAdmin(bid) {
	$("#businessId_admin").val(bid);
}
function relieveShip(id) {
	swal({
		title : "提示信息",
		text : "您确定要解除关联吗？",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "是的，我要解除！",
		cancelButtonText : "让我再考虑一下…",
		closeOnConfirm : false,
		closeOnCancel : false
	}, function(isConfirm) {
		if (isConfirm) {
			$.ajax({
				url : "/osl/relieveship/" + id,
				type : "delete",
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "解除成功！",
							text : "成功解除了与商家的关联。",
							type : "success"
						}, function() {
							window.location.reload();
						})
					} else {
						swal({
							title : "解除失败",
							text : "解除失败！",
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
				text : "您取消了解除关联操作！",
				type : "error"
			})
		}
	})
}
function recoveryShip(id) {
	swal({
		title : "提示信息",
		text : "您确定要恢复关联商家吗？",
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
				url : "/osl/recoveryShip/" + id,
				type : "PUT",
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "恢复关联成功！",
							text : "成功恢复关联了商家。",
							type : "success"
						}, function() {
							window.location.reload();
						})
					} else {
						swal({
							title : "恢复关联失败",
							text : "恢复关联失败！",
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
				text : "您取消了恢复关联操作！",
				type : "error"
			})
		}
	})
}
function relationShip(id) {
	swal({
		title : "提示信息",
		text : "您确定要关联商家吗？",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "是的，我要关联！",
		cancelButtonText : "让我再考虑一下…",
		closeOnConfirm : false,
		closeOnCancel : false
	}, function(isConfirm) {
		if (isConfirm) {
			$.ajax({
				url : "/osl/recoveryShip/" + id,
				type : "PUT",
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "关联成功！",
							text : "成功关联了商家。",
							type : "success"
						}, function() {
							window.location.reload();
						})
					} else {
						swal({
							title : "关联失败",
							text : "关联失败！",
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
				text : "您取消了关联操作！",
				type : "error"
			})
		}
	})
}