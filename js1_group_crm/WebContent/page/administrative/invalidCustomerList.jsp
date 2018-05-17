<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid p-t-20">
	<div class="row-fluid">
		<div class="span12">									
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-user"></i>无效客户列表
					</div>
				</div>
				<div class="portlet-body">
					<table class="table table-striped table-bordered table-hover"id="customer-feedback-table">
						<thead>
							<tr role="row"  style="background: #EEEEEE;">
								<th width="5%">序号</th>
								<th width="10%" class="sorting_asc" role="columnheader" tabindex="0"
									aria-controls="sample_2" rowspan="1" colspan="1"
									aria-sort="ascending"
									aria-label="Email: activate to sort column descending">部门</th>
								<th width="10%" class="sorting_asc" role="columnheader" tabindex="0"
									aria-controls="sample_2" rowspan="1" colspan="1"
									aria-sort="ascending"
									aria-label="Email: activate to sort column descending">业务员</th>
								<th width="15%" class="sorting_asc" role="columnheader" tabindex="0"
									aria-controls="sample_2" rowspan="1" colspan="1"
									aria-sort="ascending"
									aria-label="Email: activate to sort column descending">客户编号</th>
								<th width="15%" class="sorting_asc" role="columnheader" tabindex="0"
									aria-controls="sample_2" rowspan="1" colspan="1"
									aria-sort="ascending"
									aria-label="Email: activate to sort column descending">姓名</th>
								<th width="15%" class="sorting_asc" role="columnheader" tabindex="0"
									aria-controls="sample_2" rowspan="1" colspan="1"
									aria-sort="ascending"
									aria-label="Email: activate to sort column descending">电话</th>
								<th width="15%" class="sorting_asc" role="columnheader" tabindex="0"
									aria-controls="sample_2" rowspan="1" colspan="1"
									aria-sort="ascending"
									aria-label="Email: activate to sort column descending">客户状态</th>
								<th width="15%" style="text-align: center;">操作</th>
							</tr>
						</thead>
						<tbody role="alert" aria-live="polite" aria-relevant="all">
							<tr class="odd gradeX">
								<td class="">1</td>
								<td class="">业务一部</td>
								<td class="">杨静</td>
								<td>C000001</td>
								<td>张三</td>
								<td>15812345678</td>
								<td>空号</td>
								<td>												
									<button class="btn mini yellow" id="a-customer-invalid-delete-1"><i class="icon-remove"></i>&nbsp;&nbsp;核对无效后删除</button>
								</td>
							</tr>
							<tr class="odd gradeX">
								<td class="">2</td>
								<td class="">业务一部</td>
								<td class="">刘六</td>
								<td>C000002</td>
								<td>李四</td>
								<td>15812345678</td>
								<td>停机</td>
								<td>
									<button class="btn mini yellow" id="a-customer-invalid-delete-2"><i class="icon-remove"></i>&nbsp;&nbsp;核对无效后删除</button>
								</td>
							</tr>
							<tr class="odd gradeX">
								<td class="">1</td>
								<td class="">业务一部</td>
								<td class="">杨静</td>
								<td>C000001</td>
								<td>张三</td>
								<td>15812345678</td>
								<td>停机</td>
								<td>
									<button class="btn mini yellow" id="a-customer-invalid-delete-2"><i class="icon-remove"></i>&nbsp;&nbsp;核对无效后删除</button>
								</td>
							</tr>
							<tr class="odd gradeX">
								<td class="">1</td>
								<td class="">业务一部</td>
								<td class="">杨静</td>
								<td>C000001</td>
								<td>张三</td>
								<td>15812345678</td>
								<td>停机</td>
								<td>
									<button class="btn mini yellow" id="a-customer-invalid-delete-2"><i class="icon-remove"></i>&nbsp;&nbsp;核对无效后删除</button>
								</td>
							</tr>
							<tr class="odd gradeX">
								<td class="">1</td>
								<td class="">业务一部</td>
								<td class="">杨静</td>
								<td>C000001</td>
								<td>张三</td>
								<td>15812345678</td>
								<td>空号</td>
								<td>
									<button class="btn mini yellow" id="a-customer-invalid-delete-2"><i class="icon-remove"></i>&nbsp;&nbsp;核对无效后删除</button>
								</td>
							</tr>
							<tr class="odd gradeX">
								<td class="">1</td>
								<td class="">业务一部</td>
								<td class="">杨静</td>
								<td>C000001</td>
								<td>张三</td>
								<td>15812345678</td>
								<td>空号</td>
								<td>
									<button class="btn mini yellow" id="a-customer-invalid-delete-2"><i class="icon-remove"></i>&nbsp;&nbsp;核对无效后删除</button>
								</td>
							</tr>
							<tr class="odd gradeX">
								<td class="">1</td>
								<td class="">业务一部</td>
								<td class="">杨静</td>
								<td>C000001</td>
								<td>张三</td>
								<td>15812345678</td>
								<td>空号</td>
								<td>
									<button class="btn mini yellow" id="a-customer-invalid-delete-2"><i class="icon-remove"></i>&nbsp;&nbsp;核对无效后删除</button>
								</td>
							</tr>
							<tr class="odd gradeX">
								<td class="">1</td>
								<td class="">业务一部</td>
								<td class="">杨静</td>
								<td>C000001</td>
								<td>张三</td>
								<td>15812345678</td>
								<td>停机</td>
								<td>
									<button class="btn mini yellow" id="a-customer-invalid-delete-2"><i class="icon-remove"></i>&nbsp;&nbsp;核对无效后删除</button>
								</td>
							</tr>
							<tr class="odd gradeX">
								<td class="">1</td>
								<td class="">业务一部</td>
								<td class="">杨静</td>
								<td>C000001</td>
								<td>张三</td>
								<td>15812345678</td>
								<td>停机</td>
								<td>
									<button class="btn mini yellow" id="a-customer-invalid-delete-2"><i class="icon-remove"></i>&nbsp;&nbsp;核对无效后删除</button>
								</td>
							</tr>
							<tr class="odd gradeX">
								<td class="">1</td>
								<td class="">业务一部</td>
								<td class="">杨静</td>
								<td>C000001</td>
								<td>张三</td>
								<td>15812345678</td>
								<td>空号</td>
								<td>
									<button class="btn mini yellow" id="a-customer-invalid-delete-2"><i class="icon-remove"></i>&nbsp;&nbsp;核对无效后删除</button>
								</td>
							</tr>
						</tbody>
					</table>							
				</div>
			</div>
			<!--结束：portlet-->		
									
		</div>
		<!--结束：第1行第1列-->		
	</div>
	<!--结束：第1行-->		
</div>
<!-- END PAGE CONTAINER--> 

<script>
jQuery(document).ready(function() {    

	
	$("[id^='menu-item-']").click(function(){
		$("[id^='menu-item-']").removeClass("active");
		$(this).addClass("active");
		$("[id^='menu-item-']").find("a span[class='selected']").remove();
		$("[id^='menu-item-']").find("a span[class^='arrow']").removeClass("open");
		$(this).find("a").append("<span class=\"selected\"></span>");
		$(this).find("a span[class^='arrow']").addClass("open");
	});
	
	$("[id^='a-customer-invalid-delete-']").on("click", function(){
		var cid = $(this).parent().parent().find("td:eq(3)").html();
		jdyConfirm("确认是否删除id为“" + cid + "”的客户信息？", function(){
		
		});
	});
	

	var templeteTable = {
		"bSort": false,				
		"bLengthChange": false,
		"bFilter": false,				
              // set the initial value
              "iDisplayLength": 8,
              "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
              "sPaginationType": "bootstrap",
              "oLanguage": {               
			"sLengthMenu": "每页显示 _MENU_ 条记录",
			"sZeroRecords": "抱歉， 没有找到",
			"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
			"sInfoEmpty": "没有数据",
			"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
			"oPaginate": {
				"sFirst": "首页",
				"sPrevious": "前一页",
				"sNext": "后一页",
				"sLast": "尾页"
			}
              }
          }	
	$("#customer-feedback-table").dataTable(templeteTable);
});

function jdyConfirm(content, callback) {
	var html = "";
	html += "<div id=\"static\" class=\"modal hide\"  data-backdrop=\"static\" data-keyboard=\"false\">"
	html += "<div class=\"modal-body\">"
	html += "<p>" + content + "</p>"
	html += "</div>"
	html += "<div class=\"modal-footer\">"
	html += "<button type=\"button\" data-dismiss=\"modal\" class=\"btn\">取消</button>"
	html += "<button id=\"message-delete-confirm\" type=\"button\" data-dismiss=\"modal\" class=\"btn red\">确定</button>"
	html += "</div>"
	html += "</div>	"	
	$(html).modal().css("margin-top", "80px");
	$("#message-delete-confirm").on("click", callback);			
}
</script>