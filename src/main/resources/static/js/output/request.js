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
	querySellplatformList();
	$("#iscombination").selectpicker("val",0);
	$('#table_refresh').on('click', 'button[name="delete"]',
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
				var tmpOrderId = that.attr("data-id");
				updateTmpRequestData(tmpOrderId);
				reloadTableCss();
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

function validform() {
	return $("#myform").validate({
		rules : {
			sellplatformId : {
				required : true
			},
			iscombination : {
				required : true
			},
			shipId : {
				required : true
			},
			nums : {
				required : true,
				range : [ 1, 1000000000 ]
			},
			orderId : {
				required : true
			},
			customer : {
				required : true
			},
			postcode : {
				required : true
			},
			address1 : {
				required : true
			},
			address2 : {
				required : true
			},
			tele : {
				required : true
			},
			sendType : {
				required : true
			},
			sendId : {
				required : true
			}
		},
		messages : {
			sellplatformId : {
				required : "请选择销售平台"
			},
			iscombination : {
				required : "请选择贩卖类型"
			},
			shipId : {
				required : "请选择贩卖ID"
			},
			nums : {
				required : "请输入正确数量"
			},
			orderId : {
				required : "请输入订单号"
			},
			customer : {
				required : "请输入客户名称"
			},
			postcode : {
				required : "请输入邮编"
			},
			address1 : {
				required : "请输入地址1"
			},
			address2 : {
				required : "请输入地址2"
			},
			tele : {
				required : "请输入电话"
			},
			sendType : {
				required : "请输入发送方式"
			},
			sendId : {
				required : "请输入发货番号"
			}
		}
	});
}

//新建出库请求信息
function saveDetails(){
	if (validform().form()) {
		var object=$("#myform").serializeJson();
		object.requestOutputdetailModelList=tmpRequestData;
		var dttable = $('.dataTables-example').dataTable();
	    dttable.fnClearTable(); //清空一下table
	    dttable.fnDestroy(); //还原初始化了的datatable
		var a = $('#table_refresh').load("/b/output/request/savenew" , {'params':JSON.stringify(object)});
		reloadTableCss();
		hideModal();
	}
}

function hideModal(){
	$("#sellplatformId").selectpicker("val",null);
	$("#iscombination").selectpicker("val",0);
	$("#shipId").selectpicker("val",null);
	$("#nums").val("");
	$("#orderId").val("");
	$("#customer").val("");
	$("#postcode").val("");
	$("#address1").val("");
	$("#address2").val("");
	$("#tele").val("");
	$("#sendType").val("");
	$("#sendId").val("");
	$("#goodsId").val("");
	$("#sku").val("");
	$("#goodsName").val("");
	$("#combinationId").val("");
	$("#myModal").modal('hide');
}
/**
 * 改写data对象
 */
$.fn.serializeJson=function(){
    var serializeObj={};
    var array=this.serializeArray();
    $(array).each(function(){
         
        if(serializeObj[this.name]){
            if($.isArray(serializeObj[this.name])){
                serializeObj[this.name].push(this.value);
            }else{
                serializeObj[this.name]=[serializeObj[this.name],this.value];
            }
        }else{
            serializeObj[this.name]=this.value;
        }
    });
    return serializeObj;
};

/**
 * 修改临时datatable中的数组
 * @param tmpOrderId
 * @returns
 */
function updateTmpRequestData(tmpOrderId){
	for(var i = 0; i < tmpRequestData.length ; i++){
		if(tmpOrderId == tmpRequestData[i].orderId){
			tmpRequestData.splice(i,1);
			break;
		}
	}
}

function tmpDataCheck(){
	if(tmpRequestData != null && tmpRequestData.length > 0){
		swal({
			title : "出库请求列表中已有数据！是否放弃？",
			text : "本次上传将清除已有列表数据，请谨慎操作！",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "继续上传！",
			cancelButtonText : "让我再考虑一下…",
			closeOnConfirm : false,
			closeOnCancel : false
		}, function(isConfirm) {
			if (isConfirm) {
				document.getElementById('form').submit();
			} else {
				swal({
					title : "已取消",
					text : "您取消了取消操作！",
					type : "error"
				})
			}
		})
		return
	}else{
		document.getElementById('form').submit();
	}
}

/**
 * 提交最终可出库申请的list 并且新建出库详情信息
 * @returns
 */
function submitRequestData(){
	if(tmpRequestData == null || tmpRequestData.length == 0){
		swal({
			title : "提交失败",
			text : "没有可提交的出库请求，请先上传或新建出库请求！",
			type : "error"
		})
		return;
	}
	for(var i in tmpRequestData){
		if(tmpRequestData[i].insufficient == 0){
			swal({
				title : "提交失败",
				text : "出库请求中存在库存不足记录！请重新整理后再提交申请！",
				type : "error"
			})
			return;
			break;
		}
	}
	$.ajax({
	    type : "post",
	    dataType : "text",
	    url: "/b/output/submit/request",
	    cache: false,
	    data : {params:JSON.stringify(tmpRequestData)},
	    success : function(data) {
		if (data == "ok") {
			swal({
				title : "保存成功！",
				text : "成功保存了出库详情信息。",
				type : "success"
			}, function() {
				window.location.href = '/b/output/request';
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
	    }
	});
}
/**
 * 销售平台列表获取
 * @returns
 */
function querySellplatformList(){
	$("#sellplatformId").html("");
	$.ajax({
		url : "/b/sys/sellplatformList",
		type : "POST",
		contentType : 'application/json;charset=utf-8',
		data: {},
		dataType: 'text',
		success : function(data) {
			var tmp = JSON.parse(data);
			var tmpStr = "";
			for(var i in tmp){
				tmpStr += "<option value="+tmp[i].id+" >"+tmp[i].name+"</option>";
			}
			$("#sellplatformId").html(tmpStr);
			$("#sellplatformId").selectpicker('refresh');
		},
		error : function(e) {
			alert("错误！！");
		}
	});
}

/**
 * 销售平台列表查询
 * @param obj
 * @returns
 */
function selectOnchangBySellplatformId(obj){
	$("#goodsId").val("");
	$("#sku").val("");
	$("#goodsName").val("");
	$("#combinationId").val("");
	querySellshipList();
}

/**
 * 贩卖类型查询
 * @param obj
 * @returns
 */
function selectOnchangByIscombination(obj){
	$("#goodsId").val("");
	$("#sku").val("");
	$("#goodsName").val("");
	$("#combinationId").val("");
	if(obj.value == "0"){
		$("#iscombinationFlagTrue").show();
		$("#iscombinationFlagFalse").hide();
	}else{
		$("#iscombinationFlagFalse").show();
		$("#iscombinationFlagTrue").hide();
	}
	querySellshipList();
}

/**
 * 贩卖ID列表获取
 * @returns
 */
function querySellshipList(){
	$("#shipId").html("");
	var iscombinationStr = $("#iscombination").val();
	var sellplatformIdStr = $("#sellplatformId").val();
	$.ajax({
		type : "post",
	    dataType : "text",
	    url: "/b/goods/sellshipList",
	    cache: false,
		data: {qry_platformId : sellplatformIdStr , qry_type : iscombinationStr},
		dataType: 'text',
		success : function(data) {
			var tmp = JSON.parse(data);
			var tmpStr = "";
			for(var i in tmp){
				tmpStr += "<option value="+tmp[i].sellId+" data-id='"+tmp[i].goodsId+"'>"+tmp[i].goodsId+"</option>";
			}
			$("#shipId").html(tmpStr);
			$("#shipId").selectpicker('refresh');
		},
		error : function(e) {
			alert("错误！！");
		}
	});
}

/**
 * 贩卖ID查询
 * @param obj
 * @returns
 */
function selectOnchangByshipId(obj){
	$("#goodsId").val("");
	$("#sku").val("");
	$("#goodsName").val("");
	$("#combinationId").val("");
	var objectId = $('#shipId').find("option:selected").attr('data-id');
	if($("#iscombination").val() == "0"){
		$.ajax({
			type : "get",
		    dataType : "text",
		    url: "/osl/goods/"+objectId,
			data: {},
			dataType: 'text',
			success : function(data) {
				var tmp = JSON.parse(data);
				$("#goodsId").val(tmp.goodsId);
				$("#sku").val(tmp.sku);
				$("#goodsName").val(tmp.name);
			},
			error : function(e) {
				alert("错误！！");
			}
		});
	}else{
		$.ajax({
			type : "get",
		    dataType : "text",
		    url: "/osl/combinationshow/"+objectId,
			data: {},
			dataType: 'text',
			success : function(data) {
				var tmp = JSON.parse(data);
				if(tmp.length > 0){
					$("#combinationId").val(tmp[0].combinationId);
					$("#goodsName").val(tmp[0].name);
				}
			},
			error : function(e) {
				alert("错误！！");
			}
		});
	}
}

function reloadTableCss() {
	$('.dataTables-example').DataTable({
		bFilter : false,
        ordering : false,
		paging : false,
		responsive : true,
		destroy:true,
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

function downloadTemplet(){
	window.location="/b/download/outputdemo"; 
}
