function validform() {
	return $("#myform").validate({
		rules : {
			businessId : {
				required : true,
				range : [ 1, 10000000 ]
			},
			grade : {
				required : true
			},
			minVolume : {
				required : true
			},
			maxVolume : {
				required : true
			},
			minWeight : {
				required : true
			},
			maxWeight : {
				required : true
			},
			type : {
				required : true,
				range : [ 1, 10000000 ]
			},
			price : {
				required : true
			}
		},
		messages : {
			businessId : {
				required : "请选择商家",
				range : "请选择商家"
			},
			grade : {
				required : "请输入等级",
			},
			minVolume : {
				required : "请输入最小体积"
			},
			maxVolume : {
				required : "请输入最大体积",
			},
			minWeight : {
				required : "请输入最小重量",
			},
			maxWeight : {
				required : "请输入最大重量",
			},
			type : {
				required : "请选择类型",
				range : "请选择类型"
			},
			price : {
				required : "请输入价格",
			}
		}
	});
}
$(validform());
function reloadTableCss() {
	var $wrapper = $('#div-table-container');
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
							extend : 'csv'
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
}
$(function() {
	reloadTableCss();
	$('#btn_submit').click(function() {
		if (validform().form()) {
			$.ajax({
				url : "/osl/balancebase",
				type : $("#mode").val(),
				data : $("#myform").serialize(),
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "保存成功！",
							text : "成功保存了一条信息。",
							type : "success"
						}, function() {
							window.location.reload();
						})
					} else if (data == "exist") {
						swal({
							title : "结算已经存在！",
							text : "保存失败！",
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
		}
	});
	$("#myModal").on("hidden.bs.modal", function() {
		$("#businessId").val("0");
		$("#grade").val("");
		$("#minVolume").val("");
		$("#maxVolume").val("");
		$("#minWeight").val("");
		$("#maxWeight").val("");
		$("#type").val("0");
		$("#price").val("");
		$("#id").val("0");
		$("#mode").val("POST");
		validform().resetForm();
		$(".form-control").removeClass("error");
	});
});
function showInfo(id) {
	$.ajax({
		url : "/osl/balancebase/" + id,
		type : "get",
		success : function(data) {
			$("#businessId").val(data.businessId);
			$("#grade").val(data.grade);
			$("#minVolume").val(data.minVolume);
			$("#maxVolume").val(data.maxVolume);
			$("#minWeight").val(data.minWeight);
			$("#maxWeight").val(data.maxWeight);
			$("#type").val(data.type);
			$("#price").val(data.price);
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
				url : "/osl/balancebase/" + id,
				type : "delete",
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "删除成功！",
							text : "成功删除了一条信息。",
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