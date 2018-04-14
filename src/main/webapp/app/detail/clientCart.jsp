<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/common/common.jspf"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${proPath}/app/detail/style/css/index.css" />
	<script type="text/javascript">
		// 删除菜品项
		function removeSorder(node) {
			var gid = node.lang;
			window.location.href = "/wirelessplatform/sorder.html?method=removeSorder&gid="+gid;
		}

		修改菜品项数量
		function alterSorder(node) {
            var price = $("#price").text();
			var snumber = node.value;
            if(snumber <=0 || snumber == null){
                snumber = 1;
                $("#shuliang").val(snumber);
            }
			var gid = node.lang;
			var count = parseFloat(price)*parseFloat(snumber);

			$("#xiaoji").text(count)
			return;
			window.location.href = "/wirelessplatform/sorder.html?method=alterSorder&gid="+gid+"&snumber="+snumber;
		}

		// 下单
		function genernateOrder() {
//		    alert("234");
            var fooId = $("#fid").text();
            var foodName = $("#fname").text();
            var foodPrivice = $("#fprice").text();
            var foodcount = $("#shuliang").val();
            var totalMoney = $("#xiaoji").text();
            var tableId = $("#tableId").val();
            var orderId = $("#orderId").val();

//            alert(fooId+" : "+foodName+" : "+foodPrivice+" : "+foodcount+" : "+totalMoney);
//            return;

			window.location.href = "${proPath}/sys/orderdetail/insert.action?fooId="
				+fooId+"&&foodName="+foodName+"&&foodPrivice="+foodPrivice+
				"&&foodcount="+foodcount+"&&totalMoney="+totalMoney+"&&tableId="+tableId+"&&orderId="+orderId;
		}

		// 返回菜单列表
		function liebiao() {
            window.location.href = "${proPath}/sys/food/caidan.action?stylename=粤菜";
        }
	</script>
</head>

<body style="text-align: center">
	<div id="all">
		<div id="menu">
			<!-- 餐车div -->
			<input type="hidden" id="orderId" value="${orderId}" />
			<input type="hidden" id="tableId" value="${tableId}" />
			<div id="count">
				<table align="center" width="100%" id="caidanbiao">
					<tr height="40">
						<td align="center" width="20%">编号</td>
				 		<td align="center" width="20%">菜名</td>
				 		<td align="center" width="20%">单价</td>
				 		<td align="center" width="20%">数量</td>
				 		<td align="center" width="20%">小计</td>
				 		<td align="center" width="20%">操作</td>
				 	</tr>
					<tr height="60">
						<td align="center" width="20%"><span id="fid">${food.id}</span></td>
					 		<td align="center" width="20%"><span id="fname">${food.foodname}</span></td>
					 		<td id="price" align="center" width="20%"><span id="fprice">${food.foodprice}</span></td>
					 		<td align="center" width="20%">
					 			<input id="shuliang" type="text" value="1" size="3" lang="3" onblur="alterSorder(this)"/>
					 		</td>
					 		<td align="center" width="20%"><span id="xiaoji">${food.foodprice}</span></td>
						<td colspan="6" style="margin-left: 100px; text-align: center;"align="right">
							<input type="hidden" name="bId" value="">
							<input type="button" value="下单" class="btn_next" onclick="genernateOrder()" />
						</td>
				 	</tr>
				</table>
			</div>
			<div id="fanhui"><button onclick="liebiao()" >返回菜单列表</button></div>
		</div>
	</div>
</body>
</html>
