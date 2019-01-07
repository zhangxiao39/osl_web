/*
    纳品对应js
 */

var table = {};
$(function() {
    setTableCss();
    getBusinessSkuList();
    getBusinessEntryList();
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
    条件查询纳品列表
 */
function searchEntryList()
{
    var data = {};
    var entryStr = $('#entry_id').val();     //纳品id列表
    var skuStr = $('#goods_sku').val();     //根据商品sku
    var sku = '';
    var entry = '';
    for(var i=0;i<skuStr.length;i++)
    {
        sku+=skuStr[i];
        if(skuStr.length!=1&&i!=skuStr.length-1)
        {
            sku+=","
        }

    }
    for(var i=0;i<entryStr.length;i++)
    {
        entry+=entryStr[i];
        if(entryStr.length!=1&&i!=entryStr.length-1)
        {
            entry+=","
        }
    }
    data.entruIdStr = entry;
    data.skuStr = sku;
    data.barcode = $('#barCode').val().trim();
    data.goodsName = $('#goodsName').val().trim();
    data.status = $('#status').val();
    var dttable = $('.dataTables-example').dataTable();
    dttable.fnClearTable(); //清空一下table
    dttable.fnDestroy(); //还原初始化了的datatable
    $('#table_refresh').load("/b/entry/listCondition" , {'params':JSON.stringify(data)});
    setTableCss();
}

/*
    查看明细
 */
function getEnterDetail(entryId)
{
    window.location.href = '/b/entry/detailList/' + entryId + '/';
}

/*
    纳品取消
 */
function cancle(entryId)
{
    swal({
        title : "您确定要取消本次纳品吗",
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
                url : "/b/entry/updateEntryStatus",
                data:{
                    'entryId':entryId
                },
                type : "POST",
                success : function(data) {
                //     $.ajax({
                //         url: "/b/entry/updateEntryDetailStatus",
                //         data: {
                //             'entryId': entryId
                //         },
                //         type: "POST",
                //         success: function (data) {
                            swal({
                                title : "取消成功！",
                                text : "您已经成功取消本次纳品",
                                type : "success"
                            }, function() {
                                window.location.reload();
                            })
                    //     }
                    // })
                },
                error : function(e) {
                    alert("查询错误！！");
                }
            });

        } else {
            swal({
                title : "已取消",
                text : "您取消了本次操作！",
                type : "error"
            })
        }
    })
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

/*
    加载当前商家对应的商品纳品列表
*/
function getBusinessEntryList() {
    $.ajax({
        url : "/osl/entry/entryList",
        data:{},
        type : "POST",
        success : function(data) {
            var businessList = data;
            document.getElementById("entry_id").options.selectedIndex = 0;
            var strOption ='';
            for(var i=0;i<data.length;i++)
            {
                strOption+='<option value='+data[i].entryId+'>'+data[i].entryId+'</option>'
            }
            $("#entry_id").append(strOption);
            $('#entry_id').selectpicker('refresh');    //刷新数据
            $('#entry_id').selectpicker('render');
        },
        error : function(e) {
            alert("查询错误！！");
        }
    });
}

