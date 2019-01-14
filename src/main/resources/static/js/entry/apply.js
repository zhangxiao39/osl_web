/*
    纳品申请对应js
 */
var table = {};
var entryGoodsIdArr = new Array();
var backFlag = true;
$(function() {
    setTableCss();
    getBusiness();
    getBusinessSkuList();
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
    var status = -1;
    var goodsCategoryId = $('#type').val();	//根据商品分类id查询
    var dttable = $('.dataTables-example').dataTable();
    dttable.fnClearTable(); //清空一下table
    dttable.fnDestroy(); //还原初始化了的datatable
    $('#table_refresh').load("/b/entryStock/condition" , {'sku':sku,'name':name,'barCode':barCode,'nums':nums,'status':status,'goodsCategoryId':goodsCategoryId});
    setTableCss();

}


/*
    单个商品进行纳品
 */
function singleEntry(goodsId)
{

    //构建纳品实体
    var entryData = {};
    entryData.skuNums  = 1;
    entryData.goodsNums = $('#entryNums'+goodsId).find('input').val();
    //构建纳品详情列表实体
    var entryDetailListData =new Array();
    var entryDetailData = {};
    entryDetailData.goodsId = goodsId;
    entryDetailData.nums = $('#entryNums'+goodsId).find('input').val();
    entryDetailData.packageSize = $('#packageSize'+goodsId).find('input').val();
    entryDetailData.innerNums = $('#innerNums'+goodsId).find('input').val();
    entryDetailData.innerGoodsNums = $('#innerGoodsNums'+goodsId).find('input').val();
    entryDetailData.validityTime = $('#validate'+goodsId).find('input').val();
    entryDetailListData.push(entryDetailData);
    //数据校验
    backFlag = true;
    var backData = validationData(entryDetailListData,backFlag);
    if(!backFlag)
    {
        $('#'+backData).find('input').attr('placeholder','不能为空');
        $('#'+backData).find('input').focus();
        return;
    }
    var back = 'ok';
    $.ajax({
        url : "/a/entry/addEntry",
        data:{
            'entryParam':JSON.stringify(entryData),
            'entryDetailParams':JSON.stringify(entryDetailListData)
        },
        type : "POST",
        success : function(data) {
            if (!judeNull(data)) {
                swal({
                    title : "申请成功！",
                    text : "ok",
                    type : "info"
                },function () {
                    window.location="/b/entry/detailList/"+data;
                });
            }
            else  {
                swal({
                    title : "申请失败！",
                    text : "ok！",
                    type : "info"
                })
            }
        }

    });


}

/*
    批量纳品
 */
function batchEntry()
{
    var goodsIdArray = new Array();
    var entryData = {}; //纳品实体
    var entryDetailListData =new Array(); //纳品详情列表
    entryData.skuNums  = 0;
    entryData.goodsNums = 0;

    //获取勾选的纳品列表,获取goodsId
    $('div[class="icheckbox_square-green checked"]').each(function(){
        goodsIdArray.push($(this).find('input').attr("id"));
    });
    if(goodsIdArray.length == 0)
    {
        swal({
            title : "提醒！",
            text : "请填写纳品信息，再进行纳品",
            type : "info"
        })
        return;
    }
    for(var i=0;i<goodsIdArray.length;i++)
    {
        //纳品实体
        entryData.skuNums ++;
        entryData.goodsNums += parseInt($('#entryNums'+goodsIdArray[i]).find('input').val());
        //纳品详情list
        var entryDetailData = {};
        entryDetailData.goodsId = goodsIdArray[i];
        entryDetailData.nums = $('#entryNums'+goodsIdArray[i]).find('input').val();
        entryDetailData.packageSize = $('#packageSize'+goodsIdArray[i]).find('input').val();
        entryDetailData.innerNums = $('#innerNums'+goodsIdArray[i]).find('input').val();
        entryDetailData.innerGoodsNums = $('#innerGoodsNums'+goodsIdArray[i]).find('input').val();
        entryDetailData.validityTime = $('#validate'+goodsIdArray[i]).find('input').val();
        entryDetailListData.push(entryDetailData);
    }
    //数据校验
    backFlag = true;
    var backData = validationData(entryDetailListData,backFlag);
    if(!backFlag)
    {
        $('#'+backData).find('input').attr('placeholder','不能为空');
        $('#'+backData).find('input').focus();
        return;
    }
    $.ajax({
        url : "/a/entry/addEntry",
        data:{
            'entryParam':JSON.stringify(entryData),
            'entryDetailParams':JSON.stringify(entryDetailListData)
        },
        type : "POST",
        success : function(data) {
            if (!judeNull(data)) {
                swal({
                    title : "申请成功！",
                    text : "ok",
                    type : "info"
                },function () {
                    window.location="/b/entry/detailList/"+data;
                });
            }
            else  {
                swal({
                    title : "申请失败！",
                    text : "ok！",
                    type : "info"
                })
            }
        }

    });
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
    检查是否为空
 */
function validationData(dataList)
{

    for(var i=0;i<dataList.length;i++)
    {
        var data = dataList[i];
        if(judeNull(data.nums))
        {
            backFlag = false;
            return 'entryNums'+data.goodsId;

        }
        if(judeNull(data.packageSize))
        {
            backFlag = false;
            return 'packageSize'+data.goodsId;
        }
        if(judeNull(data.innerNums))
        {
            backFlag = false;
            return 'innerNums'+data.goodsId;
        }
        if(judeNull(data.innerGoodsNums))
        {
            backFlag = false;
            return 'innerGoodsNums'+data.goodsId;
        }
        if(judeNull(data.validityTime))
        {
            backFlag = false;
            return 'validate'+data.goodsId;
        }
    }
    return "";
}

/*
    下载纳品模板文件
 */
function downloadEntryFile()
{
    window.location="/b/entry/downloadEntryFile";
}
//内容判空
function judeNull(stringPara)
{
    var back = false;
    if(stringPara == null || stringPara == undefined || stringPara == '')
    {
        back =  true;
    }
    return back;
}