<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@include file="/common/common.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>查询菜品列表</title>
<body> 

	<table id="dg"></table>  
	<script type="text/javascript">
		$(function () {
			$('#dg').datagrid({    
			    //url:'${proPath}/sys/food/selectPage.action',  //支持单条件查询
			    url:'${proPath}/sys/food/selectPageUseDyc.action',   //支付多条件查询
			    fitColumns:true,
			    striped:true,
			    nowrap:true,
			    idField: 'id',
			    pagination:true,
			    rownumbers: true,
			    pageSize: 5,
			    pageList:[3,5,10,20],
			   /*  查询参数*/
			   	queryParams: {
			   		foodname: '%%',
			   		stylename: '%%'
				}, 
			    toolbar: [{
					iconCls: 'icon-add',
					text: '新增',
					handler: function(){
						parent.$('#win').window({
							title:'新增菜单',
						    width:600,    
						    height:400,    
						    modal:true,
						    //可以加载内容填充到选项卡，页面有Js时，也可加载
							content:"<iframe src='${proPath}/base/goURL/food/insert.action' height='100%' width='100%' frameborder='0px' ></iframe>" 
						}); 
					}
				},'-',{
					iconCls: 'icon-edit',
					text: '修改',
					handler: function(){
						var array = $('#dg').datagrid("getSelections");
						if(array.length!=1){
							alert("请选中需要修改的记录，并且只能修改一条!");
							return false;
						}
						
						parent.$('#win').window({    
							title:'修改菜单',
						    width:600,    
						    height:400,    
						    modal:true,
						    //可以加载内容填充到选项卡，页面有Js时，也可加载
							content:"<iframe src='${proPath}/base/goURL/food/update.action' height='100%' width='100%' frameborder='0px' ></iframe>" 
						});
						
					}
				},'-',{
					iconCls: 'icon-remove',
					text: '删除',
					handler: function(){
						var array = $('#dg').datagrid("getSelections");
						if(array.length>0){
							alert("选中了");
							
							//定义数组，通过下边的用来存储选中记录的Id
							var ids = new Array();
							for (i = 0; i < array.length; i++) {
								ids[i] = array[i].id;
								alert(ids[i]);
							}
							//alert("ids" + ids);
							//如果需要锁整个页面，前面加parent.  这里运用了confirm对话框
							parent.$.messager.confirm('删除对话框', '您确认要删除吗？', function(r) {
								if (r) {
									alert(r);
									$.ajax({
									  url: "${proPath}/sys/food/deleteList.action",
									  type:"POST",
									  //设置为传统方式传送参数
									  traditional:true,
									  data:{pks:ids},
									  success: function(html){
										  if(html>0){
										  	alert("恭喜您 ，删除成功，共删除了"+html+"条记录");
										  }else{
										  	alert("对不超 ，删除失败");
										  }
									  //重新刷新页面
									    $("#dg").datagrid("reload");
									    //请除所有勾选的行
									    $("#dg").datagrid("clearSelections");
									  },
									  error: function (XMLHttpRequest, textStatus, errorThrown) {
										    $.messager.alert('删除错误','请联系管理员！','error');
										},
									  dataType:'json'
									});
								}
							});
						}else{
							alert("请选择需要删除的记录!");
						}
					}
				},'-',{
					text: "菜单名称：<input type='text' id='foodname' name='foodname'/>",
				},'-',{
					text: "菜系名称：<input type='text' id='stylename' name='stylename'/>",
				}],

			    columns:[[
			    	{checkbox: true},
			        {field:'id',title:'菜单编号',width:100},    
			        {field:'stylename',title:'菜系名称',width:100},
			        {field:'foodname',title:'菜单名称',width:100},
			        {field:'foodprice',title:'菜单价格',width:100},
			        {field:'foodvprice',title:'会员价格',width:100},
			        {field:'foodremark',title:'菜单描述',width:100,align:'right'}
			    ]]    
			});
			
			$('#stylename').searchbox({ 
				searcher:function(value,name){ 
					$('#dg').datagrid('load',{
						foodname: '%'+$('#foodname').val()+'%',
						stylename: '%'+value+'%',
					});
				}, 
			});
		})
		
	</script>

</body>

</html>