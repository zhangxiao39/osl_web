function check(id) {
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
			swal({
				title : "删除成功！",
				text : "您已经永久删除了这条信息。",
				type : "success"
			}, function() {
				window.location.reload();
			})
		} else {
			swal({
				title : "已取消",
				text : "您取消了删除操作！",
				type : "error"
			})
		}
	})
}
function pay(id) {
	swal({
		title : "您确定要付款吗",
		text : "付款！",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "是的，我要付款！",
		cancelButtonText : "让我再考虑一下…",
		closeOnConfirm : false,
		closeOnCancel : false
	}, function(isConfirm) {
		if (isConfirm) {
			swal({
				title : "付款成功！",
				text : "您已经付款这个结算请求。",
				type : "success"
			}, function() {
				window.location.reload();
			})
		} else {
			swal({
				title : "已取消",
				text : "您取消了付款操作！",
				type : "error"
			})
		}
	})
}
function goStockDetailed(goodsId) {
	window.location.href = '/a/stock/stockDetail/' + goodsId + '/';
}
function goBalanceList(bid, id) {
	window.location.href = '/a/balance/list/' + bid + '/' + id + '/';
}
function goBalanceList_b(id) {
	window.location.href = '/b/balance/list/' + id + '/';
}
function goBalanceDetailed(bid, id) {
	window.location.href = '/a/balance/detail/' + bid + '/' + id + '/';
}
function goBalanceDetailed_b(id) {
	window.location.href = '/b/balance/detail/' + id + '/';
}
function goEntryDetailed_b(id) {
	window.location.href = '/b/entry/detail/' + id + '/';
}
function goEntryDetailed(bid, id) {
	window.location.href = '/a/entry/detail/' + bid + '/' + id + '/';
}
function goIputDetailed_b(inputId) {
	if(inputId){
		window.location.href = '/b/input/detail?inputId=' + inputId;
	}else{
		window.location.href = '/b/input/detail';
	}
}
function goIputDetailed(inputId) {
	if(inputId){
		window.location.href = '/a/input/detail?inputId=' + inputId;
	}else{
		window.location.href = '/a/input/detail';
	}
}
function goOutputDetailed_b(outputId) {
	var data = {};
	data.outputId = outputId;
	data.status = -1;
	data.sellplatformId = "-1";
	var dataJson = JSON.stringify(data); 
	window.location.href = '/b/output/detail?params='+encodeURIComponent(dataJson);
}
function goOutputDetailed_a(outputId) {
	var data = {};
	data.outputId = outputId;
	data.status = -1;
	data.sellplatformId = "-1";
	var dataJson = JSON.stringify(data); 
	window.location.href = '/a/output/detail?params='+encodeURIComponent(dataJson);
}
function getThisUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var str = window.location.href;
    str = str.substr(str.indexOf("?") + 1);
    var r = str.match(reg);  //匹配目标参数
    if (r != null) return unescape(decodeURI(r[2]));
    return null; //返回参数值
}