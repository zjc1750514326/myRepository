<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <%@ include file="/common/common.jspf"%>
<title>修改页面</title>
<body>
    <form id="ff" method="post">
        <div>
            <label for="id">员工编号:</label> <input type="text" name="id"  readonly="readonly"/>
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


    <script type="text/javascript">
        $(function () {
            //返回ifram页面文档（window)
            var win = parent.$("iframe[title='员工业务']").get(0).contentWindow;
            // 返回第一个被选中的行或如果没有选中的行则返回null。
            var row = win.$("#dg").datagrid("getSelected");
            $("#ff").form('load',{
                id: row.id,
                empname: row.empname,
                empjob: row.empjob,
                empps: row.empps
            });

            $("[name='id']").validatebox({
                required : true,
                missingMessage : '请填写员工Id！'
            });
            $("[name='empname']").validatebox({
                required : true,
                missingMessage : '请填写员工名称！'
            });
            $("[name='empjob']").validatebox({
                required : true,
                missingMessage : '请填写员工职位！'
            });
            $("[name='empps']").validatebox({
                required : true,
                missingMessage : '请填写员工密码！'
            });

            //禁用验证
            $("#ff").form("disableValidation");

            $("#btn").click(function () {
                // 开启验证
                $("#ff").form("disableValidation");
                // 如果验证通过
                if($("#ff").form("validate")){
                    // 提交表单
                    $("#ff").form('submit',{
                        url : '${proPath}/sys/employee/update.action',
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
                    })
                }
            });


        });
    </script>
</body>
</html>