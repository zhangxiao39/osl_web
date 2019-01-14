var detailOutputId = "";
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
	detailOutputId = jQuery.parseJSON(getThisUrlParam("params")).outputId;
	reloadTableCss();
	cQuerySellplatformList();
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
/**
 * 批量取消
 * @returns
 */
function cCancelOutputDetailList(){
	var array= "";
	var arrChk=$("input[name='outputDetailCheck']:checked");
	if(arrChk.length<=0){
		swal('提示','请先勾选数据,在进行批量操作！', "warning");
		return;
	}
	var i=0;
	$(arrChk).each(function(){ 
		array +=this.value + ";";//组装数组
		i++;
	});
	array = array.substring(0,array.length-1);
	swal({
		title : "您确定要取消所选的出库操作吗？",
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
			$.ajax({
				url : "/b/cancel/output/detail/" + array,
				type : "delete",
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "取消成功！",
							text : "成功删除了出库信息。",
							type : "success"
						}, function() {
							window.location.reload();
						})
					} else {
						swal({
							title : "取消失败",
							text : "取消失败！",
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
				text : "您取消了取消操作！",
				type : "error"
			})
		}
	})
}
/**
 * 取消出库
 * @param outputId
 * @returns
 */
function cCancelOutputDetail(detailId){
	swal({
		title : "您确定要取消这条出库操作吗？",
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
			$.ajax({
				url : "/b/cancel/output/detail/" + detailId,
				type : "delete",
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "取消成功！",
							text : "成功取消了一条出库信息。",
							type : "success"
						}, function() {
							window.location.reload();
						})
					} else {
						swal({
							title : "取消失败",
							text : "取消失败！",
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
				text : "您取消了取消操作！",
				type : "error"
			})
		}
	})
}
/**
 * 批量删除
 * @returns
 */
function cDeleteOutputDetailList(){
	var array= "";
	var arrChk=$("input[name='outputDetailCheck']:checked");
	if(arrChk.length<=0){
		swal('提示','请先勾选数据,在进行批量操作！', "warning");
		return;
	}
	var i=0;
	$(arrChk).each(function(){ 
		array +=this.value + ";";//组装数组
		i++;
	});
	array = array.substring(0,array.length-1);
	swal({
		title : "您确定要删除所选的出库操作吗？",
		text : "取消后将无法恢复，请谨慎操作！",
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
				url : "/b/delete/output/detail/" + array,
				type : "delete",
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "删除成功！",
							text : "成功删除了出库信息。",
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
				text : "您取消了取消操作！",
				type : "error"
			})
		}
	})
}
/**
 * 删除出库
 * @param outputId
 * @returns
 */
function cDeleteOutputDetail(detailId){
	swal({
		title : "您确定要删除这条出库操作吗？",
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
				url : "/b/delete/output/detail/" + detailId,
				type : "delete",
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "删除成功！",
							text : "成功删除了一条出库信息。",
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
//商家
/**
 * 查询
 * @returns
 */
function cSearchOutputDetailList(){
	var data = {};
	data.outputId = detailOutputId;
	data.sku = $("#sku").val();
	data.barcode = $("#barcode").val();
	data.goodsName = $("#goodsName").val();
	data.status = $("#status").val();
	data.customer = $("#customer").val();
	data.startNewDate = $("#startNewDate").val();
	data.endNewDate = $("#endNewDate").val();
	data.sellplatformId = $("#sellplatformId").val();
	data.searchFlag = "1";
	var dataJson = JSON.stringify(data); 
	var dttable = $('.dataTables-example').dataTable();
    dttable.fnClearTable(); //清空一下table
    dttable.fnDestroy(); //还原初始化了的datatable
	$('#c_table_refresh').load("/b/output/detail" , {'params':dataJson});
	reloadTableCss();
}
/**
 * 查询商家的销售平台
 * @returns
 */
function cQuerySellplatformList(){
	$("#sellplatformId").html("");
	$.ajax({
		url : "/b/sys/sellplatformList",
		type : "POST",
		contentType : 'application/json;charset=utf-8',
		data: {},
		dataType: 'text',
		success : function(data) {
			var tmp = JSON.parse(data);
			var tmpStr = "<option value='-1' selected='selected'>全部平台</option>";
			for(var i in tmp){
				tmpStr += "<option value="+tmp[i].id+" >"+tmp[i].name+"</option>";
			}
			$("#sellplatformId").html(tmpStr);
		},
		error : function(e) {
			alert("错误！！");
		}
	});
}

//修改出库信息
function saveOutputDetailNums(){
	if (validform().form()) {
		$.ajax({
			url : "/b/save/outputdetail",
			type : 'POST',
			data : $("#editDetailNumsForm").serialize(),
			success : function(data) {
				if (data == "ok") {
					swal({
						title : "保存成功！",
						text : "成功保存了一条出库详情信息。",
						type : "success"
					}, function() {
						window.location.reload();
					})
				} else if (data == "exist") {
					swal({
						title : "出库详情ID已经存在！",
						text : "保存失败！",
						type : "error"
					})
				} else if (data == "overflow") {
					swal({
						title : "出库数量超出库存总数！",
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

function saveOutputDetailStatusBefore(detailId , status){
	$("#editStatus").val(status);
	$("#detailId").val(detailId);
}

function saveOutputDetailStatusAfter(){
	if(wValidform().form()){
		$.ajax({
			url : "/a/save/outputdetail",
			type : 'POST',
			data : $("#editDetailStatusForm").serialize(),
			success : function(data) {
				if (data == "ok") {
					swal({
						title : "保存成功！",
						text : "成功保存了一条出库详情信息。",
						type : "success"
					}, function() {
						window.location.reload();
					})
				} else if (data == "exist") {
					swal({
						title : "出库详情ID已经存在！",
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

function saveOutputDetailStatus(detailId , status){
	$("#editStatus").val(status);
	$("#detailId").val(detailId);
	$.ajax({
		url : "/a/save/outputdetail",
		type : 'POST',
		data : $("#editDetailStatusForm").serialize(),
		success : function(data) {
			if (data == "ok") {
				swal({
					title : "保存成功！",
					text : "成功保存了一条出库详情信息。",
					type : "success"
				}, function() {
					window.location.reload();
				})
			} else if (data == "exist") {
				swal({
					title : "出库详情ID已经存在！",
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

function reloadTableCss(){
	$('.dataTables-example').DataTable(
		{
			paging : false,
			responsive : true,
			dom : '<"html5buttons"B>lTfgitp',
			buttons : [
					/*{
						extend : 'csv',
						action : function (nButton, oConfig, oFlash) {
							var data = {};
							data.inputId = detailInputId;
							var dataJson = JSON.stringify(data);
							window.location.href='/all/export/inputDetail?params=' + encodeURIComponent(dataJson);
						}
					}*/
					 ]
	
		});
}

function validform() {
	return $("#editDetailNumsForm").validate({
		rules : {
			nums : {
				required : true,
				range : [ 1, 1000000000 ]
			}
		},
		messages : {
			nums : {
				required : "请输入出库数量",
			}
		}
	});
}

function wValidform() {
	return $("#editDetailStatusForm").validate({
		rules : {
			sendId : {
				required : true
			}
		},
		messages : {
			sendId : {
				required : "请输入发货番号",
			}
		}
	});
}

function cEditOutputDetail(detailId){
	$.ajax({
		url : "/b/outputDetail/detail/"+detailId,
		type : "POST",
		contentType : 'application/json;charset=utf-8',
		data: {},
		dataType: 'text',
		success : function(data) {
			debugger;
			var tmp = JSON.parse(data);
			$("#nums").val(tmp.nums);
			$("#detailId").val(tmp.detailId);
		},
		error : function(e) {
			alert("错误！！");
		}
	});
}

//运营商
/**
 * 查询
 * @returns
 */
function wSearchOutputDetailList(){
	var data = {};
	data.outputId = detailOutputId;
	data.sku = $("#sku").val();
	data.barcode = $("#barcode").val();
	data.goodsName = $("#goodsName").val();
	data.status = $("#status").val();
	data.customer = $("#customer").val();
	data.startNewDate = $("#startNewDate").val();
	data.endNewDate = $("#endNewDate").val();
	data.sellplatformId = $("#sellplatformId").val();
	data.searchFlag = "1";
	var dataJson = JSON.stringify(data); 
	var dttable = $('.dataTables-example').dataTable();
    dttable.fnClearTable(); //清空一下table
    dttable.fnDestroy(); //还原初始化了的datatable
	$('#c_table_refresh').load("/a/output/detail" , {'params':dataJson});
	$('#c_table_refresh').footable();
	reloadTableCss();
}
