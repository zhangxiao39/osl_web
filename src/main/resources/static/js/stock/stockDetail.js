
/*
    库存详细
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
});
var selectEntity = {};
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
    var goodsId = $('#goodsId').text().trim(); //商品id查询
    var shevelsId = $('#shevelsId').val().trim(); //根据货架id查询
    var manangeId = $('#manangeId').val().trim(); //根据管理id查询
    var startDate = $('#startDate').val(); //根据开始时间
    var endDate = $('#endDate').val();    //根据结束时间
    var nums = $('#nums').val();	//在库数量
    // var goodsCategoryId = $('#type').val();	//根据商品分类id查询
    var dttable = $('#data_table').dataTable();
    dttable.fnClearTable(); //清空一下table
    dttable.fnDestroy(); //还原初始化了的datatable
    $('#data_table').load("/a/stock/adminStockDetailCondition" , {'goodsId':goodsId,'shevelsId':shevelsId,
        'manageId':manangeId,'nums':nums,'startDate':startDate,'endDate':endDate});
    setTableCss();

}

/*
    修改库存
 */
function updateStock(manageId) {
    $('#upNum').val('')
    $.ajax({
        url : "/osl/stock/getStockById",
        data:{
            'manageId':manageId
        },
        type : "POST",
        success : function(data) {
            selectEntity  = data;
            var goodsType = '良品';
            if(data.goodsType == '1')
            {
                goodsType = '不良品'
            }
            if(data.goodsType == '2')
            {
                goodsType = '废弃品'
            }
            //隐藏相同的类型
            $('input[type="radio"]').each(function(index){
               if($(this).attr('value') == data.goodsType)
               {
            	   $(this).parent().hide();
               }else{
            	   $(this).parent().show();
               }
            });
            $('#upGoodsName').val(data.goodsName);
            $('#upInputDate').val(data.inputTime);
            $('#upNums').val(data.nums);
            $('#upType').val(goodsType);

            //页面赋值
            $('#shelvesId').val(data.shelvesId);
            $('#validityTime').val(data.validityTime);
            $('#depotId').val(data.depotId);
            $('#goodsType').val(data.goodsType);
            $('#manageId').val(data.manageId);

        },
        error : function(e) {
            alert("查询错误！！");
        }
    });
    $('#myModal').modal('show')

}

/*
    数据有效性校验
 */
function validform() {
    return $("#myform").validate({
        rules : {
            goodsType: {
                required: true,
            },
            nums: {
                required: true,
                range: [0, $('#upNums').val()]

            }
        },
        messages : {
            goodsType : {
                required : "请选择更改的状态",

            },
            nums : {
                required : "请输入更改数量",
                range : "数值不在0到"+$('#upNums').val()+'内'
            }

        }
    });
}
// $(validform());
/*
    修改库存提交
 */
function updateStockSubmit()
{
     if (validform().form())
     {
        selectEntity .nums = parseInt(selectEntity .nums) - parseInt($('#upNum').val());
        $.ajax({
            url : "/a/stock/updateStock",
            data:{
            	'manageId':selectEntity.manageId,
                'nums':selectEntity .nums
            },
            type : "POST",
            success : function(data) {
                if (data == "ok") {
                    var stcok = judeGoodsExist();
                    var operateFlag = 'fail';

                    if(stcok.length == 0)
                    {
                         operateFlag = upAddStock();
                    }
                    else{
                        //根据查找出的库存id，添加数量
                        var manageId = stcok[0].manageId;
                        var nums = stcok[0].nums + parseInt($('#upNum').val());
                        operateFlag = updateStockNums(manageId,nums);
                    }
                    if(operateFlag == "ok")       //添加一条新的库存
                    {
                        swal({
                            title : "更新成功！",
                            text : "成功更新了一条库存信息。",
                            type : "success"
                        }, function() {
                            window.location.reload();
                        })
                    }

                } else if (data == "exist") {
                    swal({
                        title : "库存信息不存在！",
                        text : "更新失败！",
                        type : "error"
                    })
                } else {
                    swal({
                        title : "更新失败",
                        text : "更新失败！",
                        type : "error"
                    })
                }
            },
            error : function(e) {
                alert("查询错误！！");
            }
        });
        $('#myModal').modal('hide');
     }

}

/*
    新建库存
 */
function upAddStock()
{
    var back = 'ok';
    $.ajax({
        url : "/a/stock/insertStock",
        data:{
             'manageId':selectEntity.manageId,
            'nums':$('#upNum').val(),
            'goodsType':$("input[name='goodsType']:checked").val()
        },
        type : "POST",
        success : function(data) {

            if (data == "ok") {

            }
            else  {
                back =  'fail';
            }
        }

    });
    return back;
}


/*
    更改库存数量根据manageId
 */
function updateStockNums(manageId,nums)
{
    var back = 'ok';
    $.ajax({
        url : "/a/stock/updateStock",
        data:{
            'manageId':manageId,
            'nums':nums
        },
        type : "POST",
        success : function(data) {
            if (data == "ok") {

            }
            else  {
                back =  'fail';
            }
        }

    });
    return back;
}

/*
    判断同一件商品，同一个批次（入库详情id相同），同种类型商品是否存在
 */
function judeGoodsExist()
{
    var stcok = {};
    $.ajax({
        url: "/a/stockListConditon",
        async:false,
        data: {
            'goodsId': selectEntity.goodsId,
            'inputDetailId': selectEntity.inputDetailId,
            'goodsType':$("input[name='goodsType']:checked").val()
        },
        type: "POST",
        success: function (data) {
            stcok = data;
        },
        error : function(e) {
            alert("查询错误！！");
        }
    });
    return stcok;
}
