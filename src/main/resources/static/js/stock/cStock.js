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
    // var $wrapper = $('#div-table-container');
    setTableCss();
    getBusiness();
    getBusinessSkuList();

});

//加载table样式
function setTableCss() {
    $('.dataTables-example').DataTable(
        {
            // bFilter : false,
            // ordering : false,
            // iDisplayLength : 10,
            // bDestory:true,
            // // sDom : 'itlp',
            // pageLength : 25,
            // responsive : true,
            // dom : '<"html5buttons"B>lTfgitp',
            pageLength : 25,
            searching : false,
            responsive : true,
            bLengthChange: true,
            dom : '<"html5buttons"B>lTfgitp',
            // "oLanguage": {
            //     "sLengthMenu": "每页显示 _MENU_ 条记录",
            //     "sZeroRecords": "对不起，查询不到任何相关数据",
            //     "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
            //     "sInfoEmtpy": "找不到相关数据",
            //     "sInfoFiltered": "数据表中共为 _MAX_ 条记录)",
            //     "sProcessing": "正在加载中...",
            //     "sSearch": "搜索",
            //     "sUrl": "", //多语言配置文件，可将oLanguage的设置放在一个txt文件中，例：Javascript/datatable/dtCH.txt
            //     "oPaginate": {
            //         "sFirst": "第一页",
            //         "sPrevious": " 上一页 ",
            //         "sNext": " 下一页 ",
            //         "sLast": " 最后一页 "
            //     }
            // },
            buttons : [{
                extend : 'csv',
                action : function (nButton, oConfig, oFlash) {
                var skuList = $('#goods_sku').val();
                var data = {};
                data.barCode = $("#goods_barCode").val().trim();
                data.goodsName = $("#goods_name").val().trim();
                data.categoryId = $("#type").val();
                data.goodsType = $("#status").val();
                data.nums = $("#goods_nums").val().trim();
                var dataJson = JSON.stringify(data);
                window.location.href='/b/export/bStock?params=' + encodeURIComponent(dataJson)+'&sku_list='+skuList;
                 }}
            ]

        });
}
/*
   库存搜索
 */
function searchStock()
{
    var skuList = $('#goods_sku').val(); //根据商品sku查询
    var sku = '';
    for(var i=0;i<skuList.length;i++)
    {
        sku+=skuList[i];
        if(skuList.length!=1&&i!=skuList.length-1)
        {
            sku+=","
        }

    }
    var name = $('#goods_name').val().trim(); //根据商品名称查询
    var barCode = $('#goods_barCode').val().trim(); //根据商品条形码查询
    var nums = $('#goods_nums').val().trim(); //根据商品数量查询
    var status = $('#status').val();    //良品和非良品
    var goodsCategoryId = $('#type').val();	//根据商品分类id查询
    var dttable = $('.dataTables-example').dataTable();
    dttable.fnClearTable(); //清空一下table
    dttable.fnDestroy(); //还原初始化了的datatable
    $('#table_refresh').load("/b/stock/condition" , {'sku':sku,'name':name,'barCode':barCode,'nums':nums,'status':status,'goodsCategoryId':goodsCategoryId});
    setTableCss();

}

/*
 * 	获取叶子节点分类列表
 */

function getBusiness()
{
    $.ajax({
        url : "/osl/goods/categoryMinList",
        data:{},
        type : "POST",
        success : function(data) {
            var categoryList = data;
            var categorySelect = $('#type');
            var strOption ='<option value="10000" selected>全部</option>';
            for(var i=0;i<categoryList.length;i++)
            {
                strOption+='<option value='+data[i].id+'>'+data[i].name+'</option>'
            }
            categorySelect.html(strOption);
        },
        error : function(e) {
            alert("查询错误！！");
        }
    });
}

/*
    加载当前商家对应的商品sku列表
 */
function getBusinessSkuList() {
    $.ajax({
        url : "/osl/goods/goodsList",
        data:{},
        type : "POST",
        success : function(data) {
            var businessList = data;
            debugger;
            var busiSelect = $('#goods_sku');
            document.getElementById("goods_sku").options.selectedIndex = 0;
            var strOption ='';
            for(var i=0;i<data.length;i++)
            {
                strOption+='<option value='+data[i].sku+'>'+data[i].sku+'</option>'
            }
            $("#goods_sku").append(strOption);
            $('#goods_sku').selectpicker('refresh');    //刷新数据
            $('#goods_sku').selectpicker('render');
        },
        error : function(e) {
            alert("查询错误！！");
        }
    });
}