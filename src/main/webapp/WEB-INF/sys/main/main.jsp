<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/common/common.jspf"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>自助式点餐后台管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

    <script type="text/javascript">
		$(function() {
			$("a[title]").click(function() {
				var text = this.href;
				//判断是否存在
				if($('#tt').tabs("exists",this.title)){
				//存在则选中
					alert("存在了");
					$('#tt').tabs("select",this.title);			
				}else{
					$('#tt').tabs('add', {
						title:this.title,
						//面板有关闭按键
					    closable:true,
					    border:true,
					    //href对远程页面js的支持不好 
						//href: text			
						//可以加载内容填充到选项卡，页面有Js时，也可加载
						content:"<iframe src='"+text+"' title='"+this.title+"' height='100%' width='100%' frameborder='0px' ></iframe>"
					});
					
				}
				return false;
				
			});
		});
	</script>
  <body >
    
   	<body class="easyui-layout">   
   		<!-- 北面 -->
	    <div data-options="region:'north',title:'',split:true"
			 style="background-image: url('${pageContext.request.contextPath}/img/topbg.jpg');height: 130%">
	    	用户：${employee.empname }
			<a href="${proPath}/sys/loginOut.action">退出</a>
			
			<div align="center"><font size="50px" color="red">尘·稀·餐饮</font></div>

	    </div>   
	      
	    <!-- 西面 -->
	    <div data-options="region:'west',title:'导航菜单',split:true" style="background-image: url('${pageContext.request.contextPath}/img/leftbg.jpg');width:220px;">
	    	<!-- Accordion分类 -->
	    	<div id="aa" class="easyui-accordion" style="width:300px;height:300px;">
			    <div title="餐桌管理" data-options="iconCls:'icon-reload'" style="background-image: url('${pageContext.request.contextPath}/img/leftbg.jpg');">
					<ul style="list-style: none;padding: 0px;margin:0px;">
						<li style="padding: 6px;"><a href="${proPath}/base/goURL/table/tableList.action" title="餐桌管理"
													 style="text-decoration: none;display: block;font-weight:bold;">餐桌管理</a>
						</li>
					</ul>
			    </div>   
			    <div title="菜系管理" data-options="iconCls:'icon-reload'" style="background-image: url('${pageContext.request.contextPath}/img/leftbg.jpg');">
					<ul style="list-style: none;padding: 0px;margin:0px;">
						<li style="padding: 6px;"><a href="${proPath}/base/goURL/style/styleList.action" title="菜系查询"
													 style="text-decoration: none;display: block;font-weight:bold;">菜系查询</a>
						</li>
					</ul>
			    </div>   
			    <div title="菜品管理" data-options="iconCls:'icon-reload',selected:true" style="background-image: url('${pageContext.request.contextPath}/img/leftbg.jpg');">
			       <!-- list-style: none去左边的点；text-decoration: none：去超链接下划线,title用来区分后继定位这里的超链接 -->
					<ul style="list-style: none;padding: 0px;margin:0px;">
						<li style="padding: 6px;"><a href="${proPath}/base/goURL/food/foodsList.action" title="菜品查询"
							style="text-decoration: none;display: block;font-weight:bold;">菜品查询</a>
						</li>
					</ul>  
			    </div>
			    <div title="订单管理" data-options="iconCls:'icon-reload'" style="background-image: url('${pageContext.request.contextPath}/img/leftbg.jpg');">
					<ul style="list-style: none;padding: 0px;margin:0px;">
						<li style="padding: 6px;"><a href="${proPath}/base/goURL/order/ordersList.action" title="订单查询"
													 style="text-decoration: none;display: block;font-weight:bold;">订单查询</a>
						</li>
						<li style="padding: 6px;"><a href="${proPath}/base/goURL/orderdetail/orderdetailList.action" title="订单明细"
													 style="text-decoration: none;display: block;font-weight:bold;">订单明细</a>
						</li>
					</ul>
				</div>
			    
			    <c:if test="${employee.empjob=='经理' }">
			    	<div title="员工管理" data-options="iconCls:'icon-reload'" style="background-image: url('${pageContext.request.contextPath}/img/leftbg.jpg');">
				        <ul style="list-style: none;padding: 0px;margin:0px;">
							<li style="padding: 6px;"><a href="${proPath}/base/goURL/employee/employeeList.action" title="员工业务"
								style="text-decoration: none;display: block;font-weight:bold;">员工业务</a>
							</li>
						</ul>   
				    </div>
			    </c:if>
			</div>  
	    </div>   
	    
	    <!-- 中间 --> 
	    <div data-options="region:'center',title:'center title'">
			<div id="tt" class="easyui-tabs" data-options="fit:true"
				 style="width:500px;height:250px;">
				<div title="系统介绍" style="padding:20px;background-image: url('${pageContext.request.contextPath}/img/folwer.jpg');">这里可以写餐厅的相关介绍等等 </div>
			</div>
	    </div>
	    <div id="win"></div>
	</body> 
  </body>
</html>
