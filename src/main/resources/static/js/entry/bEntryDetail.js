/*
    纳品明细对应js
 */

var selectEntryDetail = {}; //编辑，选中的纳品行信息
var entryId = '';   //当前纳品详情列表对应的entryId
var  additionalEntryDtailFlag = 'yes'; //追加纳品详情标识，yes：重新追加新商品，'no'：原商品基础上追加
var additionalSlectGoodsNum = 0;    //追加商品，选中的商品纳品数，如果已经纳品则存在纳品数，没有纳品则为0
var table = {};
$(function() {
    //判断是否现在【追加商品】按钮
    var status = $('td[name="status"]:eq(0)').text();
    if(status == '1' || status == '3')
    {
        $('#addGoodsBtn').show();
    }
    else{
        $('#addGoodsBtn').hide();
    }
    //获取当前纳品Id
    entryId = $('td[name="entryId"]:eq(0)').text();
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
    删除纳品明细
 */
function deleteEntruDetail(entryDetailId) {
    swal({
        title : "您确定要删除该纳品详情吗",
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
                url : "/b/entryDetail/physicsDelete",
                data:{
                    'entryDetailId':entryDetailId
                },
                type : "POST",
                success : function(data) {
                    swal({
                        title : "删除成功！",
                        text : "您已经成功删除该纳品详情信息",
                        type : "success"
                    }, function() {
                        window.location.reload();
                    })
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
    编辑，加载模态框
 */
function showEditModal(entryDetailId)
{
    $.ajax({
        url : "/b/entryDetail/getById",
        data:{
            'entryDetailId':entryDetailId
        },
        type : "POST",
        async:false,
        success : function(data) {
            selectEntryDetail = data;
        },
        error : function(e) {
            alert("查询错误！！");
        }
    });
    /**
     * 根据当前纳品详情的状态，判断可修改项
     * 状态：申请，标识->1 ，可修改项：入库数量
     * 状态：运营商承认完了，标识->3,可修改项：货运单号
     * 状态：入库完了，标识->6 ,可修改项：入库数量
     * 状态：库存不足，标识->2,可修改项：入库数量
     */
    $('#detailId').val(selectEntryDetail.detailId);
    $('#nums').val(selectEntryDetail.nums);
    $('#numbers').val(selectEntryDetail.sendId);
    var status = selectEntryDetail.status;

    switch (status)
    {
        case 1:
        {
            $('#opt1').show();
            break;
        }
        case 2:
        {
            $('#opt1').show();
            break;
        }
        case 3:
        {
            $('#opt2').show();
            break;
        }
        case 6:
        {
            $('#opt1').show();
            break;
        }
        default: {
            $('#opt1').hide();
            $('#opt2').hide();
            break;
        }
    }

    $('#myModal').modal('show');
}

/*
    编辑提交
 */
function submitEdit()
{
    var nums = $('#nums').val().trim();
    var numbers = $('#numbers').val().trim();
    var inputDiffNums = selectEntryDetail.nums - parseInt($('#nums').val().trim());
    var detailId = $('#detailId').val();
    var data = {};
    data.detailId = detailId;
    data.nums = nums;
    data.sendId = numbers;
    data.inputDiffNums = judeNull(inputDiffNums)?0:inputDiffNums;
    var dataJson = JSON.stringify(data);

    $.ajax({
        url : "/b/entryDetail/updateInputNumsAndNumbers",
        data:{
            'params':dataJson
        },
        async:false,
        type : "POST",
        success : function(data) {

           if(data == "ok")
           {
               swal({
                   title : "操作成功！",
                   text : "您已成功完成本次操作！",
                   type : "info"});
                window.location.reload();
           }else {
               swal({
                   title : "操作失败！",
                   text : "本次操作未完成！",
                   type : "error"
           })
        }},
        error : function(e) {
            alert("查询错误！！");
        }
    })
    $('#myModal').modal('hide');
}

/*
    追加商品，模态框显示
 */
function showAddGoddsModal()
{
    getGoodsList();//加载商品
    $('#stockNums').val('');
    $('#applyNums').val('');
    $('#packeageSize').val('');
    $('#innerNums').val('');
    $('#innerGoodsNums').val('');
    $('#validate').val('');
    $('#addGoodModel').modal('show');
}

/*
    追加纳品，数据有效性校验
 */
function validform() {
    return $("#additionalEntry").validate({
        rules : {
            additonalGoodsId: {
                required: true
            },
            additonalNums: {
                required: true
            },
            additonalSize: {
                required: true
            },
            additonalInnerNums: {
                required: true
            },
            additonalInnerGoodsNums: {
                required: true
            },
            additonalValidate: {
                required: true
            }
        },
        messages : {
            additonalGoodsId : {
                required : "请选择商品"

            },
            additonalNums : {
                required : "请输入纳品数量"
            },
            additonalSize : {
                required : "请输入包裹尺寸(cm³)"
            },
            additonalInnerNums : {
                required : "请输入内盒数量"
            },
            additonalInnerGoodsNums : {
                required : "请输入内盒商品数量"
            },
            additonalValidate : {
                required : "请输入有效期"
            }

        }
    });
}
/**
 * 追加商品提交
 */
function submitAdditionalEntryGoods()
{
    if(validform().form())  //数据校验
    {
        var data = {};
        data.entryId =entryId;
        data.detailId = $('#detailIdUp').val();
        data.nums = parseInt($('#applyNums').val());
        data.inputDiffNums = additionalSlectGoodsNum-parseInt($('#applyNums').val());
        data.goodsId = $('#goodsId').val();
        data.packageSize = $('#packeageSize').val();
        data.innerNums = $('#innerNums').val();
        data.innerGoodsNums = $('#innerGoodsNums').val();
        data.validateTime = $('#validate').val();
        $.ajax({
            url : "/b/entryDetail/additionalEntryGoods",
            data:{
                'params':JSON.stringify(data),
                'newFlag':additionalEntryDtailFlag
            },
            async:false,
            type : "POST",
            success : function(data) {

                if(data == "ok")
                {
                    swal({
                        title : "操作成功！",
                        text : "您已成功完成本次操作！",
                        type : "info"});
                    window.location.reload();
                }else {
                    swal({
                        title : "操作失败！",
                        text : "本次操作未完成！",
                        type : "error"
                    })
                }},
            error : function(e) {
                alert("查询错误！！");
            }
        })
        $('#addGoodModel').modal('hide');
    }

}

/*
    判断当前选中的商品是否已经存在纳品信息，存在则返回纳品信息，不存在则返回null
 */
function getEntryDetailInfoByGoodsId()
{
    var goodsId = $('#goodsId').val();
    var entryArray = new Array();
    var entryData = null;
    $.ajax({
        url : "/b/entryDetail/getEntryDetaiListByEntryId/",
        data:{
            'entryId':entryId
        },
        async:false,
        type : "POST",
        success : function(data) {
            entryArray = data;
        },
        error : function(e) {
            alert("查询错误！！");
        }
    });

    //遍历纳品详情列表，如果该goodsId存在当中，则返回改纳品详情信息， 不存在则返回null
    for(var i=0;i<entryArray.length;i++)
    {
        if(entryArray[i].goodsId == goodsId)
        {
            entryData =  entryArray[i];
            break;
        }
    }

    if(entryData != null)
    {
        additionalEntryDtailFlag = 'no'; //置标识位

        //模态框赋值
        $('#entryId').val(entryData.entryId);
        $('#detailIdUp').val(entryData.detailId);
        additionalSlectGoodsNum = parseInt(entryData.nums); // 获取选中商品的纳品数量
        $('#stockNums').val(entryData.stockNums);
        $('#applyNums').val(entryData.nums);
        $('#packeageSize').val(entryData.packageSize);
        $('#innerNums').val(entryData.innerNums);
        $('#innerGoodsNums').val(entryData.innerGoodsNums);
        $('#validate').val(entryData.validityTime);
    }else
    {
        additionalEntryDtailFlag = 'yes'; //置标识位
        //模态框赋值
        $('#stockNums').val(0);
        // $('#applyNums').val(0);
        // $('#packeageSize').val(0);
        // $('#innerNums').val(0);
        // $('#innerGoodsNums').val(0);
        // $('#validate').val('');
    }
}
/*
加载当前商家对应的商品列表
*/
function getGoodsList() {

    var strOption ='';
    $('#goodsId').selectpicker('refresh');
    $.ajax({
        url : "/osl/goods/goodsList",
        data:{},
        type : "POST",
        success : function(data) {
            var businessList = data;
            document.getElementById("goodsId").options.selectedIndex = 0;
            for(var i=0;i<data.length;i++)
            {
                strOption+='<option style="max-width: 100px" value='+data[i].goodsId+'>'+data[i].name+'</option>'
            }
            $("#goodsId").append(strOption);
            $('#goodsId').selectpicker('refresh');    //刷新数据
            $('#goodsId').selectpicker('render');
        },
        error : function(e) {
            alert("查询错误！！");
        }
    });
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