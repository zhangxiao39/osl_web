var i = 0;
var postData = [];
function validform() {
	return $("#myform").validate({
		rules : {
			combinationId : {
				required : true,
			},
			name : {
				required : true
			}
		},
		messages : {
			combinationId : {
				required : "请填写组合品CODE",
			},
			name : {
				required : "请填写组合品名称",
			}
		}
	});
}
$(validform());
$(function() {
	var $wrapper = $('#div-table-container');
	$('.dataTables-example').DataTable({
		bFilter : true,
		ordering : false,
		iDisplayLength : 10,
		sDom : 'iftlp',
		pageLength : 25
	});
	$('#btn_submit').click(function() {
		if (validform().form()) {
			initData();
			if (postData.length > 0) {
				var jsonStr = JSON.stringify(postData);
				$.ajax({
					url : "/osl/combination",
					type : $("#mode").val(),
					data : jsonStr,
					contentType : 'application/json;charset=utf-8',
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
								title : "组合品CODE已经存在！",
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
			} else {
				swal({
					title : "提示信息",
					text : "请添加一个要组合的商品！",
					type : "warning"
				}, function() {
				})
			}
		}
	});
	$("#myModal").on("hidden.bs.modal", function() {
		$("#combinationId").val("");
		$("#name").val("");
		$("#qry_sku").val("");
		$("#qryTable tbody tr").each(function() {
			$(this).remove();
		});
		$("#mode").val("POST");
		validform().resetForm();
		$(".form-control").removeClass("error");
		i = 0;
	});
});
function showInfo(id) {
	$.ajax({
		url : "/osl/combinationshow/" + id,
		type : "get",
		success : function(data) {
			$("#combinationId").val(data[0].combinationId);
			$("#name").val(data[0].name);
			formatGoodsTable(data);
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
				url : "/osl/combination/" + id,
				type : "delete",
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "删除成功！",
							text : "成功删除了一条组合品信息。",
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
function qryProductBySku() {
	var _sku = $("#qry_sku").val();
	if (_sku != "") {
		$('.modal-body').toggleClass('sk-loading');
		$
				.ajax({
					url : "/osl/goods/sku/" + _sku,
					type : "get",
					success : function(data) {
						if (data != "" && data != undefined) {
							var rowTem = '<tr>'
									+ '<td class="z-hidden">'
									+ data.goodsId
									+ '</td>'
									+ '<td>'
									+ data.sku
									+ '</td>'
									+ '<td>'
									+ data.name
									+ '</td>'
									+ '<td><input type="Text" name="nums" value="1" class="z_inputl_50" /></td>'
									+ '<td><button class="btn btn-danger btn-xs dropdown-toggle" type="button" onClick="delRow(this)"><i class="fa fa-trash"></i>删除</button></td>'
									+ '</tr>';
							$("#qryTable tbody:last").append(rowTem);
							i++;
						} else {
							swal({
								title : "提示信息",
								text : "没有检索到该商品信息！",
								type : "warning"
							})
						}
					},
					error : function(e) {
						alert("错误！！");
					}
				});
		$('.modal-body').toggleClass('sk-loading');
	}
}
function delRow(row) {
	$(row).parent().parent().remove();
	i--;
}
function initData() {
	postData = [];
	var _combinationid = $("#combinationId").val();
	var _name = $("#name").val();
	var _businessId = $("#businessId").val();
	$('#qryTable tbody tr').each(function(i) {
		_goodsId = $(this).children('td').eq(0).text();
		_sku = $(this).children('td').eq(1).text();
		_nums = $(this).children('td').eq(3).find('input[name="nums"]').val();
		var _tmpData = {
			'combinationId' : _combinationid,
			'name' : _name,
			'goodsId' : _goodsId,
			'nums' : _nums,
			'businessId' : _businessId
		};
		postData.push(_tmpData);
	});
}
function formatGoodsTable(data) {
	if (data != "" && data != undefined) {
		for (index in data) {
			var rowTem = '<tr>'
					+ '<td class="z-hidden">'
					+ data[index].goodsId
					+ '</td>'
					+ '<td>'
					+ data[index].sku
					+ '</td>'
					+ '<td>'
					+ data[index].goodsname
					+ '</td>'
					+ '<td><input type="Text" name="nums" value="'
					+ data[index].nums
					+ '" class="z_inputl_50" /></td>'
					+ '<td><button class="btn btn-danger btn-xs dropdown-toggle" type="button" onClick="delRow(this)"><i class="fa fa-trash"></i>删除</button></td>'
					+ '</tr>';
			$("#qryTable tbody:last").append(rowTem);
			i++;
		}
	}
}