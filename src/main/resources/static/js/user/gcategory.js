function validform() {
	return $("#myform").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : "请输入分类名称"
			}
		}
	});
}
$(validform());
$(function() {
	$('#jstree1').jstree({
		'core' : {
			'check_callback' : true
		},
		// 'plugins' : [ 'types', 'dnd', "contextmenu" ],
		'plugins' : [ 'types', 'dnd' ],
		'types' : {
			'default' : {
				'icon' : 'fa fa-folder'
			},
			'html' : {
				'icon' : 'fa fa-file-code-o'
			},
			'svg' : {
				'icon' : 'fa fa-file-picture-o'
			},
			'css' : {
				'icon' : 'fa fa-file-code-o'
			},
			'img' : {
				'icon' : 'fa fa-file-image-o'
			},
			'js' : {
				'icon' : 'fa fa-file-text-o'
			}

		}
	});
	$('#jstree1').on("changed.jstree", function(e, data) {
		console.log(data.selected);
		$("#myId").val(data.selected);
	});
	$('#btn_submit').click(function() {
		if (validform().form()) {
			$.ajax({
				url : "/osl/goodscategory",
				type : $("#mode").val(),
				data : $("#myform").serialize(),
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "保存成功！",
							text : "成功保存了一条分类信息。",
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
		}
	});
	$("#myModal").on("hidden.bs.modal", function() {
		$("#parentId").val("0");
		$("#name").val("");
		$("#parentName").val("");
		$("#id").val("0");
		$("#mode").val("POST");
		validform().resetForm();
		$(".form-control").removeClass("error");
	});
});
function showInfo() {
	var myId = $("#myId").val();
	if (myId != "0") {
		var node = $('#jstree1').jstree("get_node",
				$('#jstree1').jstree("get_node", myId).parent);
		$("#parentId").val(node.id);
		$("#parentName").val(node.text);
		$("#name").val($('#jstree1').jstree("get_node", myId).text);
		$("#id").val(myId);
		$("#mode").val("PUT");

		$("#myModal").modal('show');
	}
}
function addInfo() {
	var myId = $("#myId").val();
	var node = $('#jstree1').jstree("get_node", myId);
	if (node.parents.length <= 2) {
		$("#parentId").val(node.id);
		$("#parentName").val(node.text);
		$("#id").val("0");
		$("#myModal").modal('show');
	} else {
		swal({
			title : "提示信息",
			text : "不能为二级分类追加分类！",
			type : "warning"
		})
	}
}
function deleInfo() {
	var myId = $("#myId").val();
	if (myId != "0") {
		var cName = $('#jstree1').jstree("get_node", myId).text;
		swal({
			title : "您确定要删除吗\r\n" + cName,
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
					url : "/osl/goodscategory/" + myId,
					type : "delete",
					success : function(data) {
						if (data == "ok") {
							swal({
								title : "删除成功！",
								text : "成功删除了一条分类信息。",
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
	} else {
		swal({
			title : "提示信息",
			text : "商品根目录不能删除!",
			type : "warning"
		})
	}
}