function validform() {
	return $("#myform").validate({
		rules : {
			depotId : {
				required : true,
				minlength : 5,
				maxlength : 50
			},
			name : {
				required : true,
				maxlength : 200
			}
		},
		messages : {
			depotId : {
				required : "请输入仓库D",
				minlength : "请输入5-50个字符",
				maxlength : "请输入5-50个字符"
			},
			name : {
				required : "请输入仓库姓名",
				maxlength : "不能超过200个字符"
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
				url : "/osl/depot",
				type : $("#mode").val(),
				data : $("#myform").serialize(),
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "保存成功！",
							text : "成功保存了一条仓库信息。",
							type : "success"
						}, function() {
							window.location.reload();
						})
					} else if (data == "exist") {
						swal({
							title : "保存失败",
							text : "该仓库ID已经存在",
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
		$("#depotId").val("");
		$("#name").val("");
		$("#address").val("");
		$("#postcode").val("");
		$("#id").val("0");
		$("#mode").val("POST");
		validform().resetForm();
		$(".form-control").removeClass("error");
		$("#depotId").removeAttr("readonly");
	});
});
function showInfo(id) {
	$.ajax({
		url : "/osl/depot/" + id,
		type : "get",
		success : function(data) {
			$("#depotId").val(data.depotId);
			$("#name").val(data.name);
			$("#address").val(data.address);
			$("#postcode").val(data.postcode);
			$("#id").val(id);
			$("#mode").val("PUT");
			$("#depotId").attr("readonly","readonly");
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
				url : "/osl/depot/" + id,
				type : "delete",
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "删除成功！",
							text : "成功删除了一条仓库信息。",
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