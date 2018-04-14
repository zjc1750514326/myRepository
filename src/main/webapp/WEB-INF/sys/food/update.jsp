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
            //返回ifram页面文档（window)
			var win = parent.$("iframe[title='菜品查询']").get(0).contentWindow;
			
			var row = win.$("#dg").datagrid("getSelected");
			
			$("#ff").form('load',{
				id: row.id,
				foodname: row.foodname,
				foodprice: row.foodprice,
				foodvprice: row.foodvprice,
				foodPic: row.foodPic,
				foodremark: row.foodremark
			});
		
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
						url : '${proPath}/sys/food/update.action',
						onSubmit : function() {
							return true;
						},
						success : function(count) {
							if(count>0){
								//可以定义为对应消息框
                        	    alert("修改成功");
							}else{
								alert("修改失败");
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
