/*
    纳品明细对应js
 */

var selectEntryDetail = {};
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
    $('#inputNums').val(selectEntryDetail.inputNums);
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
    提交
 */
function submitEdit()
{
    var inputNums = $('#inputNums').val().trim();
    var numbers = $('#numbers').val().trim();
    var inputDiffNums = selectEntryDetail.inputGoodsNums - parseInt($('#inputNums').val().trim());
    var detailId = $('#detailId').val();
    var data = {};
    data.detailId = detailId;
    data.inputNums = inputNums;
    data.sendId = numbers;
    data.inputDiffNums = judeNull(inputDiffNums)?0:inputDiffNums;
    var dataJson = JSON.stringify(data);

    $.ajax({
        url : "/b/entryDetail/updateInputNumsAndNumbers",
        data:{
            'params':dataJson
        },
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
    window.location.reload();
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