/*
    商家端库存一览对应js
 */
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
var table = {};
$(function() {
    var $wrapper = $('#div-table-container');
    setTableCss();

});

//加载table样式
function setTableCss() {
    $('.dataTables-example').DataTable(
        {
            bFilter : false,
            ordering : false,
            iDisplayLength : 10,
            bDestory:true,
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
/*
   库存搜索
 */
function searchStock()
{
    var sku = $('#goods_sku').val().trim(); //根据商品sku查询
    var name = $('#goods_name').val().trim(); //根据商品名称查询
    var barCode = $('#goods_barCode').val().trim(); //根据商品条形码查询
    var nums = $('#goods_nums').val().trim(); //根据商品数量查询
    var status = $('#status').val();    //良品和非良品
    // $.ajax({
    //     url : "/b/stock/condition",
    //     type : "POST",
    //     contentType:'application/json;charset=utf-8',
    //     data:JSON.stringify(stockModel),
    //     dataType:'text',
    //     success : function(data) {
    //         debugger;
    //        table.ajax.reload();
    //     	window.location.reload();
    //     },
    //     error : function(e) {
    //         debugger;
    //         alert("查询错误！！");
    //     }
    // });
    var dttable = $('.dataTables-example').dataTable();
    dttable.fnClearTable(); //清空一下table
    dttable.fnDestroy(); //还原初始化了的datatable
    $('#table_refresh').load("/b/stock/condition" , {'sku':sku,'name':name,'barCode':barCode,'nums':nums,'status':status});
    setTableCss();
}