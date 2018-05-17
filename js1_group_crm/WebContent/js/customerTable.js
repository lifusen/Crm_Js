$('#customerTable').dataTable({
	/**定义列,可定义排序列,默认所有列排序
    "aoColumns": [
      null,
      { "bSortable": false },
      null,
      { "bSortable": false },
      { "bSortable": false }
    ],
    **/
    "aLengthMenu": [
        [5, 15, 20, -1],
        [5, 15, 20, "All"] // change per page values here
    ],
    // set the initial value
    "iDisplayLength": 20,
    /**"sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",**/
    "sDom": "<'row-fluid'<'span7'f>r>t<'row-fluid'<'span4'l><'span4'i><'span4'p>>",
    "sPaginationType": "bootstrap",
    "oLanguage": {
    	"sInfo" : "当前第 _START_ 页, 总共 _END_ 页, 总共 _TOTAL_ 行",
    	"sSearch": "搜索:",
        "sLengthMenu": "每页显示_MENU_行",
        "sZeroRecords": "没有匹配的记录!",
        "sInfoFiltered": "(从 _MAX_ total 记录中筛选)",
        "sInfo": "显示 _START_ to _END_ of _TOTAL_ entries",
        "sInfoEmpty": "没有匹配的数据!",
        "oPaginate": {
            "sPrevious": "上一页",
            "sNext": "下一页"
        }
    }
	/**定义列的默认行为**/
	,
	//不需要排序的列:序号、部门、客户经理、客户姓名、电话、资产、贷款类型、谈判师
    "aoColumnDefs": [{
            'bSortable': false,
            'aTargets': [0]		
        },{
            'bSortable': false,
            'aTargets': [1]		
        },{
            'bSortable': false,
            'aTargets': [2]		
        },{
            'bSortable': false,
            'aTargets': [3]		
        },{
            'bSortable': false,
            'aTargets': [4]		
        },{
            'bSortable': false,
            'aTargets': [5]		
        },{
            'bSortable': false,
            'aTargets': [6]		
        },{
            'bSortable': false,
            'aTargets': [7]		
        }
    ]
});
//jQuery('#customerTable_wrapper .dataTables_filter input').addClass("m-wrap medium"); // modify table search input
//jQuery('#customerTable_wrapper .dataTables_length select').addClass("m-wrap small"); // modify table per page dropdown
//jQuery('#customerTable_wrapper .dataTables_length select').select2(); // initialzie select2 dropdown
   