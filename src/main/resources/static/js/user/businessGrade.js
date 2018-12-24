function validform() {
	return $("#myform").validate({
		rules : {
			name : {
				required : true,
			},
			discount : {
				required : true,
			}
		},
		messages : {
			name : {
				required : "请输入等级名称",
			},
			discount : {
				required : "请输入优惠力度",
			}
		}
	});
}
$(validform());
$(function() {
	$('#btn_submit').click(function() {
		if (validform().form()) {
			$.ajax({
				url : "/osl/bussinessGrade",
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
		$("#discount").val("");
		$("#id").val("0");
		$("#mode").val("POST");
		validform().resetForm();
	});
});
function showInfo(id) {
	$.ajax({
		url : "/osl/bussinessGrade/" + id,
		type : "get",
		success : function(data) {
			$("#name").val(data.name);
			$("#discount").val(data.discount);
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
				url : "/osl/bussinessGrade/" + id,
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