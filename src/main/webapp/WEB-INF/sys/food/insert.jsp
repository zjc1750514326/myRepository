<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<html>
<head>
<%@ include file="/common/common.jspf"%>
<title></title>
</head>
<body>

	<form id="ff" method="post" enctype="multipart/form-data">
	   <div>
			<label for="id">菜单编号:</label> <input type="text" name="id" />
		</div>
		<div>
			<label for="foodname">菜单名称:</label> <input type="text" name="foodname" />
		</div>
		<div>
			<label for="foodprice">菜单价格:</label> <input type="text" name="foodprice" />
		</div>
		<div>
			<label for="foodvprice">会员价格:</label> <input type="text" name="foodvprice" />
		</div>
		
		<div>
			<label for="foodPic">菜单图片:</label> <input type="file" name="foodPic" />
		</div>
		<div>
			<label for="styId">菜系类型：</label> 									
				<select id="cc" class="easyui-combobox" name="styId" style="width: 200px">
					<c:forEach items="${applicationScope.sysParam.styleParamMap }" var="style">
						<option value="${style.key }">${style.value }</option>
					</c:forEach>
			    </select> 
		</div>
		<div>
			<label for="foodremark">备注:</label>
			<textarea name="foodremark"></textarea>
		</div>
		<div>
			<input id="btn" type="button" value="提交" />
		</div>
	</form>

	<script type="text/javascript">
		$(function() {
			var win = parent.$("iframe[title='菜品查询']").get(0).contentWindow; //返回ifram页面文档（window)
		
			$("[name='id']").validatebox({
				required : true,
				missingMessage : '请填写菜单Id！'
			});
			$("[name='foodname']").validatebox({
				required : true,
				missingMessage : '请填写出菜单名称！'
			});
			$("[name='foodprice']").validatebox({
				required : true,
				missingMessage : '请填写菜单价格！'
			});
			
			$("[name='foodvprice']").validatebox({
				required : true,
				missingMessage : '请填写会员价格！'
			});
			
			
			//禁用验证
			$("#ff").form("disableValidation");

			$("#btn").click(function() {
				$("#ff").form("enableValidation");
				if ($("#ff").form("validate")) {
					$('#ff').form('submit', {
						url : '${proPath}/sys/food/insert.action',
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