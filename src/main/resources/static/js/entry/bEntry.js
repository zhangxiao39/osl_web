/*
    纳品对应js
 */

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