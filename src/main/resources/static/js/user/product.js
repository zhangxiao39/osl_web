var CONSTANT = {
	DATA_TABLES : {
		DEFAULT_OPTION : { // DataTables初始化选项
			language : {
				"sProcessing" : "处理中...",
				"sLengthMenu" : "每页 _MENU_ 项",
				"sZeroRecords" : "没有匹配结果",
				"sInfo" : "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
				"sInfoEmpty" : "当前显示第 0 至 0 项，共 0 项",
				"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
				"sInfoPostFix" : "",
				"sSearch" : "搜索:",
				"sUrl" : "",
				"sEmptyTable" : "表中数据为空",
				"sLoadingRecords" : "载入中...",
				"sInfoThousands" : ",",
				"oPaginate" : {
					"sFirst" : "首页",
					"sPrevious" : "上页",
					"sNext" : "下页",
					"sLast" : "末页",
					"sJump" : "跳转"
				},
				"oAria" : {
					"sSortAscending" : ": 以升序排列此列",
					"sSortDescending" : ": 以降序排列此列"
				}
			},
			autoWidth : false, // 禁用自动调整列宽
			stripeClasses : [ "odd", "even" ],// 为奇偶行加上样式，兼容不支持CSS伪类的场合
			order : [], // 取消默认排序查询,否则复选框一列会出现小箭头
			processing : false, // 隐藏加载提示,自行处理
			serverSide : true, // 启用服务器端分页
			searching : false
		// 禁用原生搜索
		},
		COLUMN : {
			CHECKBOX : { // 复选框单元格
				className : "td-checkbox",
				orderable : false,
				width : "30px",
				data : null,
				render : function(data, type, row, meta) {
					return '<input type="checkbox" class="iCheck">';
				}
			}
		},
		RENDER : { // 常用render可以抽取出来，如日期时间、头像等
			ELLIPSIS : function(data, type, row, meta) {
				data = data || "";
				return '<span title="' + data + '">' + data + '</span>';
			}
		}
	}
};
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
$(function() {
	var $wrapper = $('#div-table-container');
	$('.dataTables-example').DataTable(
			{
				bFilter : false,
				ordering : false,
				iDisplayLength : 10,
				sDom : 'itlp',
				pageLength : 25,
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
		$("#id").val("0");
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