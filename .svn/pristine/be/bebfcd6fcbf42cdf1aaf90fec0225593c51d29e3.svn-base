<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<html>
<head>
<%@ include file="/common/common.jspf"%>
<title></title>
</head>
<body>

	<form id="ff" method="post" enctype="multipart/form-data">
	   <div>
			<label for="id">餐桌编号:</label> <input type="text" name="id" />
		</div>
		<div>
			<label for="tablename">餐桌名称:</label> <input type="text" name="tablename" />
		</div>
		<div>
			<label for="tablestate">餐桌状态:</label> <input type="text" name="tablestate" />
		</div>
		<div>
			<input id="btn" type="button" value="提交" />
		</div>
	</form>

	<script type="text/javascript">
		$(function() {
            //返回ifram页面文档（window)
			var win = parent.$("iframe[title='餐桌管理']").get(0).contentWindow;
		
			$("[name='id']").validatebox({
				required : true,
				missingMessage : '请填写餐桌Id！'
			});
			$("[name='tablename']").validatebox({
				required : true,
				missingMessage : '请填写出餐桌名称！'
			});
            $("[name='tablestate']").validatebox({
                required : true,
                missingMessage : '请填写出餐桌状态！'
            });
			//禁用验证
			$("#ff").form("disableValidation");

			$("#btn").click(function() {
				$("#ff").form("enableValidation");
				if ($("#ff").form("validate")) {
					$('#ff').form('submit', {
						url : '${proPath}/sys/dtable/insert.action',
						onSubmit : function() {
							return true;
						},
						success : function(count) {							
								//可以定义为对应消息框
								if(count>0){
	                        	    alert("添加成功！");
								}else{
									alert("添加失败！");
								}
								parent.$("#win").window("close");
                            	//reload是保留在当前页， load是不保留在当前页，从起始页开始
								win.$("#dg").datagrid("reload");
						}
					});
				}
			});
		});
	</script>
</body>
</html>
