
/*
    库存盘点
 */
var table = {};
$(function() {
    setTableCss();
    getAdminSkuList();
    getBusiness();
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
function searchTakeStock()
{
	var data = {};
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
	data.barCode = $('#goods_barCode').val().trim(); 
	data.goodsName = $('#goods_name').val().trim(); //根据货架id查询
	data.businessId= $('#businessSelect').val().trim(); //根据管理id查询
	data.nums = $('#goods_nums').val();	//在库数量
    var dttable = $('#data_table').dataTable();
    dttable.fnClearTable(); //清空一下table
    dttable.fnDestroy(); //还原初始化了的datatable
    var dataJson = JSON.stringify(data);
    debugger;
    $('#data_table').load("/a/stock/takeStockCondition" , {"params":dataJson,"skuList":sku});
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
            nums: {
                required: true


            }
        },
        messages : {
            nums : {
                required : "请输入更改数量"

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
        selectEntity .nums = parseInt($('#upNum').val());
        $.ajax({
            url : "/a/stock/updateStock",
            data:{
            	'manageId':selectEntity.manageId,
                'nums':selectEntity .nums
            },
            type : "POST",
            success : function(data) {
                if (data == "ok") {
                    swal({
                        title : "更新成功！",
                        text : "成功更新了一条库存信息。",
                        type : "success"
                    }, function() {
                        window.location.reload();
                    })
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
