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
	$("#checkAll").on('click', function () {
		if (this.checked) {
            $(this).attr('checked','checked')
            $("input[name='outputlistCheck']").each(function () {
                this.checked = true;
            });
        } else {
            $(this).removeAttr('checked')
            $("input[name='outputlistCheck']").each(function () {
                this.checked = false;
            });
        }
    });
	if($('#businessId')){
		wgetBusinessByWarehouse();
	}
	if($("#deoptId")){
		wQueryDepotListByWarehouse();
	}
});
//公共function(c、w)
/**
 * 重新加载table样式
 * @returns
 */
function reloadTableCss(){
	$('.dataTables-example').DataTable(
			{
				pageLength : 25,
				responsive : true,
				dom : '<"html5buttons"B>lTfgitp',
				columnDefs:[{
				　　　　'targets' : [1],
				　　　　'orderable' : false
				}],
				buttons : [
						{
							extend : 'csv',
							action : function (nButton, oConfig, oFlash) {
								var data = {};
								data.outputId = $("#outputId").val();
								data.sku = $("#sku").val();
								data.barcode = $("#barcode").val();
								data.goodsName = $("#goodsName").val();
								data.status = $("#status").val();
								data.customer = $("#customer").val();
								data.startNewDate = $("#startNewDate").val();
								data.endNewDate = $("#endNewDate").val();
								var dataJson = JSON.stringify(data);
								window.location.href='/all/export/output?params=' + encodeURIComponent(dataJson);
							}
						} ]
			});
}

function childclick(){
    if ($(this).is(":checked") == false) {
        $("#checkAll").prop("checked", false);
    }
}

//商家操作function(c)
/**
 *  查询
 * @returns
 */
function cSearchOutputList(){
	var data = {};
	data.outputId = $("#outputId").val();
	data.sku = $("#sku").val();
	data.barcode = $("#barcode").val();
	data.goodsName = $("#goodsName").val();
	data.status = $("#status").val();
	data.customer = $("#customer").val();
	data.startNewDate = $("#startNewDate").val();
	data.endNewDate = $("#endNewDate").val();
	data.searchFlag = "1";
	var dataJson = JSON.stringify(data); 
	var dttable = $('.dataTables-example').dataTable();
    dttable.fnClearTable(); //清空一下table
    dttable.fnDestroy(); //还原初始化了的datatable
	$('#c_table_refresh').load("/b/output/list" , {'params':dataJson});
	reloadTableCss();
}

function cGotoRequest(){
	window.location.href = '/b/output/request';
}

/**
 * 批量取消
 * @returns
 */
function cCancelOutputList(){
	var array= "";
	var arrChk=$("input[name='outputlistCheck']:checked");
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
				url : "/a/cancel/output/" + array,
				type : "delete",
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "取消成功！",
							text : "成功取消了出库信息。",
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
function cCancelOutput(outputId){
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
				url : "/a/cancel/output/" + outputId,
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
 * 删除出库
 * @param outputId
 * @returns
 */
function cDeleteOutput(outputId){
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
				url : "/b/delete/output/" + outputId,
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


//运营商操作function(w)
/**
 *  查询
 * @returns
 */
function wSearchOutputList(){
	var data = {};
	data.outputId = $("#outputId").val();
	data.sku = $("#sku").val();
	data.barcode = $("#barcode").val();
	data.goodsName = $("#goodsName").val();
	data.status = $("#status").val();
	data.customer = $("#customer").val();
	data.startNewDate = $("#startNewDate").val();
	data.endNewDate = $("#endNewDate").val();
	data.endNewDate = $("#endNewDate").val();
	data.businessId = $("#businessId").val();
	data.depotId = $("#depotId").val();
	data.searchFlag = "1";
	var dataJson = JSON.stringify(data); 
	var dttable = $('.dataTables-example').dataTable();
    dttable.fnClearTable(); //清空一下table
    dttable.fnDestroy(); //还原初始化了的datatable
	$('#c_table_refresh').load("/a/output/list" , {'params':dataJson});
	reloadTableCss();
}
/**
 * 获取当前运营商下商家列表
 */
function wgetBusinessByWarehouse()
{
	$("#businessId").empty();
    $.ajax({
        url : "/a/businessList",
        data:{},
        type : "POST",
        success : function(data) {
            var businessList = data;
            var strOption ='<option value="-1" selected>全部</option>';
            for(var i=0;i<data.length;i++)
            {
                strOption+='<option value='+data[i].id+'>'+data[i].name+'</option>'
            }
            $('#businessId').selectpicker('refresh');
            $('#businessId').html(strOption);
        },
        error : function(e) {
            alert("查询错误！！");
        }
    });
}
/**
 * 获取仓库list
 * @returns
 */
function wQueryDepotListByWarehouse(){
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
function deleteInput(id){
	swal({
		title : "您确定要删除这条入库信息吗？",
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
				url : "/a/delete/input/" + id,
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
