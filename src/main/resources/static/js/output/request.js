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
$(function() {
	reloadTableCss();
	$('.dataTables-example tbody').on('click', 'button[name="delete"]',
			function(e) {
				e.preventDefault();
				var that = $(this);
				swal({
					title : "确定要删除该行出库请求？",
					text : "取消后将无法恢复，请谨慎操作！",
					type : "warning",
					showCancelButton : true,
					confirmButtonColor : "#DD6B55",
					confirmButtonText : "是的，我要取消！",
					cancelButtonText : "让我再考虑一下…",
					closeOnConfirm : false,
					closeOnCancel : false
				}, function(isConfirm) {
					if (isConfirm) {
						var table = $('.dataTables-example').DataTable();
						table.row(that.parents('tr')).remove().draw();
						swal({
							title : "删除成功！",
							text : "成功删除了一条出库请求。",
							type : "success"
						})
					} else {
						swal({
							title : "已取消",
							text : "您取消了取消操作！",
							type : "error"
						})
					}
				})
			});
});

function reloadTableCss() {
	$('.dataTables-example').DataTable({
		paging : false,
		responsive : true,
		dom : '<"html5buttons"B>lTfgitp',
		buttons : [
		/*
		 * { extend : 'csv', action : function (nButton, oConfig, oFlash) { var
		 * data = {}; data.inputId = detailInputId; var dataJson =
		 * JSON.stringify(data);
		 * window.location.href='/all/export/inputDetail?params=' +
		 * encodeURIComponent(dataJson); } }
		 */
		]

	});
}
