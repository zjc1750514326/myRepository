<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@include file="/common/common.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户列表</title>
<body>

	<table id="dg"></table>

	<script type="application/javascript">
		$(
		    function () {
                $('#dg').datagrid({
                    url:'${proPath}/sys/employee/selectPageUseDyc.action',
					// 自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动
                    fitColumns : true,
					// 是否显示斑马线的效果
                    striped : true,
					// 在同一行中显示数据。设置为true可以提高加载性能。
                    nowrap : true,
					// 指明哪一个字段是标识字段。
                    idField: 'id',
					// 在DataGrid控件底部显示分页工具栏。
                    pagination : true,
					// 显示一个行号列
                    rownumbers : true,
					// 设置页大小为 5
                    pageSize: 5,
                    pageList:[3,5,10,20],
					/*  查询参数*/
                    queryParams: {
                        empname: '%%',
                        empjob: '%%'
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
                                content:"<iframe src='${proPath}/base/goURL/employee/insert.action' height='100%' width='100%' frameborder='0px' ></iframe>"
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
                                content:"<iframe src='${proPath}/base/goURL/employee/update.action' height='100%' width='100%' frameborder='0px' ></iframe>"
                            });
                        }
                    },'-',{
                        iconCls: 'icon-remove',
                        text: '删除',
                        handler: function(){
                            var array = $('#dg').datagrid("getSelections");
                            if(array.length>0){
                                //定义数组，通过下边的用来存储选中记录的Id
                                var ids = new Array();
                                for (i = 0; i < array.length; i++) {
                                    ids[i] = array[i].id;
                                    alert(ids[i]);
                                }
                                //如果需要锁整个页面，前面加parent.  这里运用了confirm对话框
                                parent.$.messager.confirm('删除对话框', '您确认要删除吗？', function(r) {
                                    if (r) {
                                        alert(r);
                                        $.ajax({
                                            url: "${proPath}/sys/employee/deleteList.action",
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
                        text: "员工姓名：<input type='text' id='empname' name='empname'/>",
                    },'-',{
                        text: "员工职位：<input type='text' id='empjob' name='empjob'/>",
                    }],
                    columns:[[
                        {checkbox: true},
                        {field:'id',title:'员工编号',width:100},
                        {field:'empname',title:'员工姓名',width:100},
                        {field:'empjob',title:'员工职位',width:100},
                        {field:'empps',title:'员工密码',width:100,align:'right'}
                    ]]
                });

                $('#empjob').searchbox({
                    searcher:function(value,name){
                        $('#dg').datagrid('load',{
                            empname: '%'+$('#empname').val()+'%',
                            empjob: '%'+value+'%',
                        });
                    },
                });
            }
		);
	</script>
</body>
</html>