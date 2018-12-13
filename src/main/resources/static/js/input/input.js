$(function() {
	reloadTableCss();
});
//公共function(c、w)
/**
 * 重新加载table样式
 * @returns
 */
function reloadTableCss(){
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
}


//商家操作function(c)
/**
 *  查询
 * @returns
 */
function cSearchInputList(){
	var inputId = $("#inputId").val();
	var sku = $("#sku").val();
	var barcode = $("#barcode").val();
	var goodsName = $("#goodsName").val();
	var searchFlag = "1";
	var dttable = $('.dataTables-example').dataTable();
    dttable.fnClearTable(); //清空一下table
    dttable.fnDestroy(); //还原初始化了的datatable
	$('#c_table_refresh').load("/b/input/list" , {'searchFlag':searchFlag,'inputId':inputId,'sku':sku,'barcode':barcode,'goodsName':goodsName});
	reloadTableCss();
}


//运营商操作function(w)
/**
 * 查询
 * @returns
 */
function wSearchInputList(){
	var inputId = $("#inputId").val();
	var sku = $("#sku").val();
	var barcode = $("#barcode").val();
	var goodsName = $("#goodsName").val();
	var status = $("#status").val();
	var searchFlag = "1";
	var dttable = $('.dataTables-example').dataTable();
    dttable.fnClearTable(); //清空一下table
    dttable.fnDestroy(); //还原初始化了的datatable
	$('#w_table_refresh').load("/a/input/list" , {'searchFlag':searchFlag,'inputId':inputId,'sku':sku,'barcode':barcode,'goodsName':goodsName,'status':status});
	reloadTableCss();
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
