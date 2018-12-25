var detailInputId = "";
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
	detailInputId = getThisUrlParam("inputId");
	reloadTableCss();
	
	//初始化日期控件
	$('.input-daterange').datepicker({
    	format: 'yyyy-mm-dd',
    	language: 'zh-CN',
    	todayBtn: "linked",//显示‘今日’按钮
    	clearBtn:true,//清除按钮
        keyboardNavigation: false,
        forceParse: false,
        autoclose: true
    });
});
//新建&修改入库信息
function saveDetails(){
	if($("#status").val() == ''){
		$("#status").val(0);
	}
	if (validform().form()) {
		$("#depotId").attr("disabled",false);
		$("#shelvesId").attr("disabled",false);
		$("input[name='goodsType']").attr("disabled",false);
		$.ajax({
			url : "/a/save/inputdetail",
			type : $("#mode").val(),
			data : $("#myform").serialize(),
			success : function(data) {
				if (data == "ok") {
					swal({
						title : "保存成功！",
						text : "成功保存了一条入库详情信息。",
						type : "success"
					}, function() {
						window.location.reload();
					})
				} else if (data == "exist") {
					swal({
						title : "入库详情ID已经存在！",
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
}

function reloadTableCss(){
	$('.dataTables-example').DataTable(
		{
			pageLength : 25,
			responsive : true,
			dom : '<"html5buttons"B>lTfgitp',
			buttons : [
					{
						extend : 'csv',
						action : function (nButton, oConfig, oFlash) {
							var data = {};
							data.inputId = detailInputId;
							var dataJson = JSON.stringify(data);
							window.location.href='/all/export/inputDetail?params=' + encodeURIComponent(dataJson);
						}
					}
					 ]
	
		});
}

function validform() {
	return $("#myform").validate({
		rules : {
			entryId : {
				required : true
			},
			sku : {
				required : true
			},
			goodsName : {
				required : true
			},
			goodsType : {
				required : true
			},
			nums : {
				required : true,
				range : [ 1, 1000000000 ]
			},
			deoptId : {
				required : true
			},
			shelvesId : {
				required : true
			},
			validityTime : {
				required : true
			}
		},
		messages : {
			entryId : {
				required : "请选择纳品信息",
			},
			sku : {
				required : "请输入商品SKU",
			},
			goodsName : {
				required : "请输入商品名称"
			},
			goodsType : {
				required : "请选择商品类型",
			},
			nums : {
				required : "请输入入库数量",
			},
			deoptId : {
				required : "请选择仓库",
			},
			shelvesId : {
				required : "请选择货架",
			},
			validityTime : {
				required : "请输入商品有效期",
			}
		}
	});
}
/**
 * 新建入库
 * @returns
 */
function wNewInputDetail(){
	wQueryEntryDetailList();
	wQueryDepotList();
}

function editDetail(detailId){
	$.ajax({
		url : "/a/inputDetail/detail/"+detailId,
		type : "POST",
		contentType : 'application/json;charset=utf-8',
		data: {},
		dataType: 'text',
		success : function(data) {
			debugger;
			var tmp = JSON.parse(data);
			$("#tmpDepotId").val(tmp.depotId);
			$("#tmpShelvesId").val(tmp.shelvesId);
			$("#sku").val(tmp.sku);
			$("#nums").val(tmp.nums);
			$("#inputId").val(tmp.inputId);
			$("#innerNums").val(tmp.innerNums);
			$("#innerGoodsNums").val(tmp.innerGoodsNums);
			$("#type").val(tmp.type);
			$("#status").val(tmp.status);
			$("#deleteFlag").val(tmp.deleteFlag);
			$("#entryId").val(tmp.entryId);
			$("#goodsName").val(tmp.goodsName);
			$("input[name='goodsType'][value="+tmp.goodsType+"]").attr("checked",true); 
			$("#validityTime").val(tmp.validityTime);
			$("#detailId").val(tmp.detailId);
			$("#goodsId").val(tmp.goodsId);
			$("#shipId").val(tmp.shipId);
			$("#deleteFlg").val(tmp.deleteFlg);
			$("input[name='goodsType']").attr("disabled",true);
			$("#sku").attr("readonly",true); 
			$("#goodsName").attr("readonly",true);
			wQueryDepotList();
		},
		error : function(e) {
			alert("错误！！");
		}
	});
}

/**
 * 删除
 * @returns
 */
function deleteDetail(id){
	swal({
		title : "您确定要删除这条入库详情信息吗？",
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
				url : "/a/delete/detail/" + id,
				type : "delete",
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "删除成功！",
							text : "成功删除了一条入库详情信息。",
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

/**
 * 查询需要入库的纳品信息
 * @returns
 */
function wQueryEntryDetailList(){
	$("#entryId").html("");
	$.ajax({
		url : "/a/search/entiydetail/"+0,
		type : "POST",
		contentType : 'application/json;charset=utf-8',
		data: {},
		dataType: 'text',
		success : function(data) {
			var tmp = JSON.parse(data);
			var tmpStr = "";
			for(var i in tmp){
				tmpStr += "<optgroup label="+i+">" ;
				for(var j = 0 ; j < tmp[i].length; j++){
					tmpStr += "<option data-tokens="+i+" value="+tmp[i][j].detailId+" >"+tmp[i][j].sku+"</option>";
				}
				tmpStr += "</optgroup>";
			}
			$("#entryId").html(tmpStr);
			$("#entryId").selectpicker('refresh');
		},
		error : function(e) {
			alert("错误！！");
		}
	});
}

/**
 * 根据纳品详细id 获取 纳品信息并赋值
 * @param obj
 * @returns
 */
function wSelectOnchangByEntryId(obj){
	$.ajax({
		url : "/a/entiydetail/"+obj.value,
		type : "POST",
		contentType : 'application/json;charset=utf-8',
		data: {},
		dataType: 'text',
		success : function(data) {
			var tmp = JSON.parse(data);
			$("#sku").val(tmp.sku);
			$("#nums").val(tmp.nums);
			$("#goodsName").val(tmp.goodsName);
			$("#sku").attr("readonly",true); 
			$("#goodsName").attr("readonly",true);
		},
		error : function(e) {
			alert("错误！！");
		}
	});
}

/**
 * 获取仓库list
 * @returns
 */
function wQueryDepotList(){
	$("#deoptId").empty();
	$.ajax({
		url : "/a/search/depot",
		type : "get",
		contentType : 'application/json;charset=utf-8',
		data: {},
		dataType: 'text',
		success : function(data) {
			var tmp = JSON.parse(data);
			var tmpStr = "";
			for(var i = 0 ; i < tmp.length; i++){
				tmpStr += "<option value="+tmp[i].depotId+" >"+tmp[i].name+"</option>";
			}
			$("#depotId").html(tmpStr);
			$("#depotId").selectpicker('refresh');
			if($("#tmpDepotId").val() != null && $("#tmpDepotId").val() != ''){
				$("#depotId").selectpicker('val',$("#tmpDepotId").val());
			}
			$("#depotId").attr("disabled",true); 
		},
		error : function(e) {
			alert("错误！！");
		}
	});
}

function wSelectOnchangByDepotId(obj){
	$("#shelvesId").empty();
	$.ajax({
		url : "/a/search/shelves/" + obj.value,
		type : "POST",
		contentType : 'application/json;charset=utf-8',
		data: {},
		dataType: 'text',
		success : function(data) {
			var tmp = JSON.parse(data);
			var tmpStr = "";
			for(var i = 0 ; i < tmp.length; i++){
				tmpStr += "<option value="+tmp[i].shelvesId+" >"+tmp[i].name+"</option>";
			}
			$("#shelvesId").html(tmpStr);
			$("#shelvesId").selectpicker('refresh');
			if($("#tmpShelvesId").val() != null && $("#tmpShelvesId").val() != ''){
				$("#shelvesId").selectpicker('val',$("#tmpShelvesId").val());
			}
			$("#shelvesId").attr("disabled",true);
		},
		error : function(e) {
			alert("错误！！");
		}
	});
}
