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
});
function reloadTableCss(){
	$('.dataTables-example').DataTable(
		{
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
						customize : function(
								win) {
							$(
									win.document.body)
									.addClass(
											'white-bg');
							$(
									win.document.body)
									.css(
											'font-size',
											'10px');
	
							$(
									win.document.body)
									.find(
											'table')
									.addClass(
											'compact')
									.css(
											'font-size',
											'inherit');
						}
					} ]
	
		});
}
function cSearchInputList(){
	/*var input = {};
	input.input_id = $("#inputId").val();
	input.sku = $("#sku").val();
	input.barcode = $("#barcode").val();
	input.goodsName = $("#goodsName").val();
	input.searchFlag = "1";*/
	//input.startNewDate = $("#startNewDate").val();
	//input.endNewDate = $("#endNewDate").val();
	/*$.ajax({
		url : "/b/input/list",
		type : "POST",
		contentType : 'application/json;charset=utf-8',
		data: JSON.stringify(input),
		dataType:"text", 
		success : function(data) {
			var dttable = $('.dataTables-example').dataTable();
            dttable.fnClearTable(); //清空一下table
            dttable.fnDestroy(); //还原初始化了的datatable
		},
		error : function(e) {
			alert("错误！！");
		}
	});*/
	var inputId = $("#inputId").val();
	var sku = $("#sku").val();
	var barcode = $("#barcode").val();
	var goodsName = $("#goodsName").val();
	var searchFlag = "1";
	var dttable = $('.dataTables-example').dataTable();
    dttable.fnClearTable(); //清空一下table
    dttable.fnDestroy(); //还原初始化了的datatable
	$('#table_refresh').load("/b/input/list" , {'searchFlag':searchFlag,'inputId':inputId,'sku':sku,'barcode':barcode,'goodsName':goodsName});
	reloadTableCss();
}
