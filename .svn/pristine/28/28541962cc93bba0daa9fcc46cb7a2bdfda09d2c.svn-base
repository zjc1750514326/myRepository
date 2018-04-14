<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ include file="/common/common.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增页面</title>
<body>
	<form id="ff" method="post">
		<div>
			<label for="id">员工编号:</label> <input type="text" name="id" />
		</div>
		<div>
			<label for="empname">员工名称:</label> <input type="text" name="empname" />
		</div>
		<div>
			<label for="empjob">员工职位:</label> <input type="text" name="empjob" />
		</div>
		<div>
			<label for="empps">员工密码:</label> <input type="text" name="empps" />
		</div>
		<div>
			<input id="btn" type="button" value="提交" />
		</div>
	</form>

	<script type="application/javascript">
		$(function () {

            var win = parent.$("iframe[title='员工业务']").get(0).contentWindow; //返回ifram页面文档（window)

		    $("[name='id']").validatebox({
                required : true,
                missingMessage : '请填写员工Id！'
			});
            $("[name='empname']").validatebox({
                required : true,
                missingMessage : '请填写员工姓名！'
            });
            $("[name='empjob']").validatebox({
                required : true,
                missingMessage : '请填写员工职位！'
            });
            $("[name='empps']").validatebox({
                required : true,
                missingMessage : '请填写员工密码！'
            });

            // 禁用验证
            $("#ff").form("disableValidation");

            // 提交点击事件
            $("#btn").click(function () {
                // 开始验证
				$("#ff").form("enableValidation");
				// 如果通过验证
				if ($("#ff").form("validate")){
				    // 提交表单
				    $("#ff").form('submit',{
                        url : '${proPath}/sys/employee/insert.action',
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
							// 关闭弹窗
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