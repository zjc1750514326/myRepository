<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<html>
<head>
<%@ include file="/common/common.jspf"%>
<title></title>
</head>
<body>

	<form id="ff" method="post" enctype="multipart/form-data">
	   <div>
			<label for="id">菜系编号:</label> <input type="text" name="id" />
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
						url : '${proPath}/sys/style/insert.action',
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
