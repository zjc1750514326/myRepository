<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<html>
<head>
<%@ include file="/common/common.jspf"%>
<title></title>
</head>
<body>

	<form id="ff" method="post" enctype="multipart/form-data">
	   <div>
			<label for="id">菜单编号:</label> <input type="text" name="id" readonly="readonly" />
		</div>
		<div>
			<label for="stylename">菜单名称:</label> <input type="text" name="stylename" />
		</div>
		<div>
			<input id="btn" type="button" value="提交" />
		</div>
	</form>

	<script type="text/javascript">
		$(function() {
            //返回ifram页面文档（window)
			var win = parent.$("iframe[title='菜系查询']").get(0).contentWindow;
			
			var row = win.$("#dg").datagrid("getSelected");
			
			$("#ff").form('load',{
				id: row.id,
                stylename: row.stylename
			});
		
			$("[name='id']").validatebox({
				required : true,
				missingMessage : '请填写菜系Id！'
			});
			$("[name='foodname']").validatebox({
				required : true,
				missingMessage : '请填写出菜系名称！'
			});
			//禁用验证
			$("#ff").form("disableValidation");

			$("#btn").click(function() {
				$("#ff").form("enableValidation");
				if ($("#ff").form("validate")) {
					$('#ff').form('submit', {
						url : '${proPath}/sys/style/update.action',
						onSubmit : function() {
							return true;
						},
						success : function(count) {
							if(count>0){
								//可以定义为对应消息框
                        	    alert("修改成功");
							}else{
								alert("修改失败1");
							}
								// 关闭当前的窗体
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
