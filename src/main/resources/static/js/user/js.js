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
			user_id : {
				required : true,
				minlength : 5,
				maxlength : 50
			},
			username : {
				required : true,
				maxlength : 200
			},
			password : {
				required : true
			},
			repassword : {
				required : true,
				equalTo : "#password"
			}
		},
		messages : {
			user_id : {
				required : "请输入员工ID",
				minlength : "请输入5-50个字符",
				maxlength : "请输入5-50个字符"
			},
			username : {
				required : "请输入员工姓名",
				maxlength : "不能超过200个字符"
			},
			password : {
				required : "请输入密码"
			},
			repassword : {
				required : "请再次输入密码",
				equalTo : "两次密码输入不一致"
			}
		}
	});
}
$(validform());
$(function() {
	//validform();
	var $wrapper = $('#div-table-container');
	$('.dataTables-example1')
			.DataTable(
					{
						"processing" : false,
						"serverSide" : true,
						"ajax" : {
							"url" : "/a/sys/userManage/getlist1",
							"type" : "get",
							"dataType" : "json"
						},
						"columns" : [ CONSTANT.DATA_TABLES.COLUMN.CHECKBOX, {
							"data" : "user_id"
						}, {
							"data" : "username"
						}, {
							data : null,
							defaultContent : ""
						} ],
						"createdRow" : function(row, data, index) {
							var $btnEdit = $('<button type="button" class="btn btn-info btn-xs dropdown-toggle" data-toggle="modal" data-target="#myModal"><i class="fa fa-edit"></i>编辑</button>');
							var $btnDel = $('<button type="button" class="btn btn-danger btn-xs dropdown-toggle z_ml5"><i class="fa fa-trash"></i>删除</button>');
							$('td', row).eq(3).append($btnEdit).append($btnDel);

						},
						"drawCallback" : function(settings) {
							// 渲染完毕后的回调
							// 清空全选状态
							$(":checkbox[name='cb-check-all']", $wrapper).prop(
									'checked', false);
						}
					});
	$('#btn_submit').click(function() {
		if (validform().form()) {
			$.ajax({
				url : "/a/sys/userManage",
				type : $("#mode").val(),
				data : $("#myform").serialize(),
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "保存成功！",
							text : "成功保存了一条员工信息。",
							type : "success"
						}, function() {
							window.location.reload();
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
		$("#user_id").val("");
		$("#username").val("");
		$("#id").val("0");
		$("#mode").val("POST");
		validform().resetForm();
	});
});
function showUser(id) {
	$.ajax({
		url : "/a/sys/userManage/" + id,
		type : "get",
		success : function(data) {
			$("#user_id").val(data.user_id);
			$("#username").val(data.username);
			$("#id").val(id);
			$("#mode").val("PUT");
		},
		error : function(e) {
			alert("错误！！");
		}
	});
}
function deleteUser(id) {
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
				url : "/a/sys/userManage/" + id,
				type : "delete",
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "删除成功！",
							text : "成功删除了一条员工信息。",
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