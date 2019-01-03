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
			$.ajax({
				url : "/osl/bussiness",
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