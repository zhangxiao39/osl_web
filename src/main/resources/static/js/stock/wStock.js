/*
    运营商端库存一览对应js
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
    setTableCss();
    getBusiness();  //获取商家
    getAdminSkuList();  //获取商品sku列表
    getCategpry();				//获取叶子节点分类列表
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
                    extend : 'csv',
                    action : function (nButton, oConfig, oFlash) {
                        var data = {};
                        data.inputId = $("#inputId").val();
                        data.sku = $("#sku").val();
                        data.barcode = $("#barcode").val();
                        data.goodsName = $("#goodsName").val();
                        data.status = $("#status").val();
                        var dataJson = JSON.stringify(data);
                        window.location.href='/all/export/input?params=' + encodeURIComponent(dataJson);
                    }
                }]

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
    var status = -1;    //良品和非良品
    var bussinessId = $('#businessSelect').val();	//根据商家查询
    var goodsCategoryId = $('#type').val();	//根据商品分类id查询
    var dttable = $('.dataTables-example').dataTable();
    dttable.fnClearTable(); //清空一下table
    dttable.fnDestroy(); //还原初始化了的datatable
    $('#data_table').load("/b/stock/adminCondition" , {'sku':sku,'name':name,'barCode':barCode,'nums':nums,'status':status,'businessId':bussinessId,'goodsCategoryId':goodsCategoryId});
    setTableCss();

}

/*
    加载和当前仓库有合作关系的商家
 */
function getBusiness()
{
    $.ajax({
        url : "/a/businessList",
        data:{},
        type : "POST",
        success : function(data) {
            var businessList = data;
            var busiSelect = $('#businessSelect');
            var strOption ='<option value="-1" selected>全部</option>';
            for(var i=0;i<data.length;i++)
            {
                strOption+='<option value='+data[i].id+'>'+data[i].name+'</option>'
            }
            busiSelect.append(strOption);
        },
        error : function(e) {
            alert("查询错误！！");
        }
    });
}


/*
 * 	获取叶子节点分类列表
 */

function getCategpry()
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
    加载当前运营商仓库对应的商品sku列表
 */
function getAdminSkuList() {
    $.ajax({
        url : "/b/ModelList",
        data:{},
        type : "POST",
        success : function(data) {

            var businessList = data;
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