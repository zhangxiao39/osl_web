function validform() {
	return $("#myform").validate({
		rules : {
			categoryId : {
				required : true,
				range : [ 1, 10000000 ]
			},
			sku : {
				required : true
			},
			name : {
				required : true
			},
			volume : {
				required : true
			},
			length : {
				required : true
			},
			width : {
				required : true
			},
			height : {
				required : true
			},
			weight : {
				required : true
			}
		},
		messages : {
			categoryId : {
				required : "请选择商品分类",
				range : "请选择商品分类"
			},
			sku : {
				required : "请输入商品SKU",
			},
			name : {
				required : "请输入商品名称"
			},
			volume : {
				required : "请再次输入商品体积",
			},
			length : {
				required : "请输入长度",
			},
			width : {
				required : "请输入宽度",
			},
			height : {
				required : "请输入高度",
			},
			weight : {
				required : "请输入重量",
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
}
$(function() {
	reloadTableCss();
	$('#btn_submit').click(function() {
		if (validform().form()) {
			$.ajax({
				url : "/osl/goods",
				type : $("#mode").val(),
				data : $("#myform").serialize(),
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "保存成功！",
							text : "成功保存了一条商品信息。",
							type : "success"
						}, function() {
							window.location.reload();
						})
					} else if (data == "exist") {
						swal({
							title : "商品SKU已经存在！",
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
		$("#categoryId").val("0");
		$("#sku").val("");
		$("#barcode").val("");
		$("#name").val("");
		setShapeChecked(1);
		$("input[type='radio'][name='shape'][value='1']").click();
		$("#length").val("");
		$("#width").val("");
		$("#height").val("");
		$("#volume").val("");
		$("#weight").val("");
		$("#color").val("");
		$("#remark").val("");
		$("#goodsId").val("0");
		$("#mode").val("POST");
		validform().resetForm();
		$(".form-control").removeClass("error");
	});
});
function showInfo(id) {
	$.ajax({
		url : "/osl/goods/" + id,
		type : "get",
		success : function(data) {
			$("#categoryId").val(data.categoryId);
			$("#sku").val(data.sku);
			$("#barcode").val(data.barcode);
			$("#name").val(data.name);
			setShapeChecked(data.shape);
			$("input[type='radio'][name='shape'][value='" + data.shape + "']")
					.click();
			$("#length").val(data.length);
			$("#width").val(data.width);
			$("#height").val(data.height);
			$("#volume").val(data.volume);
			$("#weight").val(data.weight);
			$("#color").val(data.color);
			$("#remark").val(data.remark);
			$("#goodsId").val(id);
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
				url : "/osl/goods/" + id,
				type : "delete",
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "删除成功！",
							text : "成功删除了一条商品信息。",
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
function setShapeChecked(v) {
	$('input[name="shape"]').each(function(index) {
		$('input[name="shape"]').eq(index).removeAttr('checked');
	});
	$("input[name='shape'][value='" + v + "']").attr('checked', 'checked');
}