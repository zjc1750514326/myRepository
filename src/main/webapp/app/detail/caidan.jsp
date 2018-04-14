<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/common/common.jspf"%>
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	<link rel="stylesheet" type="text/css" href="${proPath}/app/detail/style/css/hotle.css" />

<title>无线点餐平台</title>
	<script type="text/javascript">
        //改变菜系，异步刷新
        function changeStyle() {
            $("#zjc").empty();
            var styles = document.getElementById("selectStyle");
            var index = styles.selectedIndex;
            var style = styles.options[index].value;
            var url = "${proPath}/sys/food/caidanAJAX.action";
            var params = {
                'stylename':style
            };
            $.ajax({
                type:"post",
                url:url,
                data:{'stylename':style},
                dataType:"json",
                success:function (foodList) {
                    $("#sp1").text(foodList.page);
                    // 两数相除向上取整
                    var totalPage = Math.ceil(foodList.totalRecord/foodList.rows);
                    $("#sp2").text(totalPage);
                    //更新评论列表
                    $.each(foodList.list,function(i,food){
                        if(i>=0){
                            var htmlString
                                = $(
                                '<li>'+ '<dl>'+
                                ' <dd>'+
                                '<a href="#">'+
                                '<img style="height: 150px;width: 229px" src="${proPath}/upload/'+food.foodimage+'" />'+
                                '</a>'+
                                '</dd>'+
                                ' <dd>'+food.foodname + '</dd>'+
                                ' <dd>'+food.foodprice + '</dd>'+
                                ' <dd>'+
                                '<button id="'+i+'" value="'+food.foodname+'" onclick="addGouwc(this.value)">'+ '加入购物车'+ '</button>'+
                                '</dd>'+
                                '</dd>'+'</li>'
                            );
                            //把每个数据追回到列表中
                            $("#zjc").append($(htmlString));
                        }
                    });
                }
            });
        }
        //搜索菜名，异步刷新
        function searchFoodName() {
            $("#zjc").empty();
            var foodname = $("#foodname").val();
            var url = "${proPath}/sys/food/caidanAJAX.action";
            var params = {
				'foodname':foodname
            };
            $.ajax({
                type:"post",
                url:url,
                data:{'foodname':foodname},
                dataType:"json",
                success:function (foodList) {
                    $("#sp1").text(foodList.page);
                    // 两数相除向上取整
                    var totalPage = Math.ceil(foodList.totalRecord/foodList.rows);
                    $("#sp2").text(totalPage);
                    //更新评论列表
                    $.each(foodList.list,function(i,food){
                        if(i>=0){
                            var htmlString
                                = $(
                                '<li>'+ '<dl>'+
                                ' <dd>'+
                                '<a href="#">'+
                                '<img style="height: 150px;width: 229px" src="${proPath}/upload/'+food.foodimage+'" />'+
                                '</a>'+
                                '</dd>'+
                                ' <dd>'+food.foodname + '</dd>'+
                                ' <dd>'+food.foodprice + '</dd>'+
                                ' <dd>'+
                                '<button id="'+i+'" value="'+food.foodname+'" onclick="addGouwc(this.value)">'+ '加入购物车'+ '</button>'+
                                '</dd>'+
                                '</dd>'+'</li>'
                            );
                            //把每个数据追回到列表中
                            $("#zjc").append($(htmlString));
                        }
                    });
                }
            });
        }
        // 改变页数，异步刷新
        function changePage(value) {
            var styles = document.getElementById("selectStyle");
            var index = styles.selectedIndex;
            var style = styles.options[index].value;
            var page;
            var num = 1;
            var currentPage = $("#sp1").text();
            var totalPage = $("#sp2").text();

            if(value==1){  //上一页
				// 算出上一页的页码
                page = currentPage - num;
                if(page<=0){
                    page = 1;
				}
            }else if (value==2){ //下一页
                // 算出下一页的页码
                page = parseInt(currentPage) + parseInt(num);
                if(page>=totalPage){
                    page = totalPage;
                }
			}else if (value == 0){ //首页
                page = 1;
			}else if (value == 4){ //末页
                page = totalPage;
			}
			// 清空展示菜品的区域
            $("#zjc").empty();
            var url = "${proPath}/sys/food/caidanAJAX.action";
            var params = {
                'stylename':style,
                'page':page
            };
            $.ajax({
                type:"post",
                url:url,
                data:{'stylename':style,'page':page},
                dataType:"json",
                success:function (foodList) {
                    var state = 0;
                    $("#sp1").text(foodList.page);
                    // 两数相除向上取整
                    var totalPage = Math.ceil(foodList.totalRecord/foodList.rows);
                    $("#sp2").text(totalPage);
                    //更新评论列表
                    $.each(foodList.list,function(i,food){
                        if(i>=0){
                            var htmlString
                                = $(
                                '<li>'+ '<dl>'+
                                ' <dd>'+
                                '<a href="#">'+
                                '<img style="height: 150px;width: 229px" src="${proPath}/upload/'+food.foodimage+'" />'+
                                '</a>'+
                                '</dd>'+
                                ' <dd>'+food.foodname + '</dd>'+
                                ' <dd>'+food.foodprice + '</dd>'+
                                ' <dd>'+
                                '<button id="'+i+'" value="'+food.foodname+'" onclick="addGouwc(this.value)">'+ '加入购物车'+ '</button>'+
                                '</dd>'+
                                '</dd>'+'</li>'
                            );
                            //把每个数据追回到列表中
                            $("#zjc").append($(htmlString));
                        }
                    });
                }
            });
        }

        //加入购物车
		function queryGouwc() {
            window.location.href="${proPath}/sys/orders/queryForList.action";
        }

        function addGouwc(value) {
            var tableId = $("#tabId").val();
            var orderId = $("#orderId").val();
//            alert("餐桌ID:"+tableId);
            window.location.href="${proPath}/sys/food/clientCart.action?foodname="+value+"&&tableId="+tableId+"&&orderId="+orderId;
        }
        function tab() {
        	var tableid = $("#tabname1").val();
        	alert(tableid);
        }
	</script>
</head>
<body style="text-align: center;" >
	<%--最外围--%>
	<div id="all">
		<%--显示菜品的div--%>
		<div id="menu">
			<span>订单号：${orderId}</span>
			<input type="hidden" id="orderId" value="${orderId}">
			<input type="hidden" id="tabId" value="${tableId}">
			<div id="tab"><span id="tabname"></span></div>
			<%--顶部--%>
			<div id="top">
				<%--循环列表--%>
				<ul id="zjc">
					<c:forEach items="${foodList}" var="food" varStatus="statu">
							<!-- 循环列出餐品 -->
							<li>
								<dl>
									<dd>
										<a>
											<img style="height: 150px;width: 229px" src="${proPath}/upload/${food.foodimage}"/>
										</a>
									</dd>
									<dd id="f1">
										<a id="foname">${food.foodname}</a>&nbsp;&nbsp;<a>&yen;${food.foodprice}</a>
									</dd>
									<dd id="f2">
										<button id="${statu.count}" value="${food.foodname}" onclick="addGouwc(this.value)">加入购物车</button>
									</dd>
								</dl>
							</li>
					</c:forEach>
				</ul>
			</div>
				<!-- 底部分页导航条div -->
			<div id="foot">
				<div id="btn">
					当前<span id="sp1">${page}</span>/<span id="sp2">${totalPage}</span>页
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button  value="0"  onclick="changePage(this.value)">首页</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button  value="1"  onclick="changePage(this.value)">上一页</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button  value="2"  onclick="changePage(this.value)">下一页</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button  value="4"  onclick="changePage(this.value)">末页</button>
				</div>
			</div>
		</div>

		<h2><button id="qingdan" onclick="queryGouwc()">查看清单列表</button></h2>
		<div id="dish_2">
			<select id="selectStyle"  name="styId" style="width: 200px" onchange="changeStyle();">
				<c:forEach items="${applicationScope.sysParam.styleParamMap }" var="style">
					<option value="${style.value }">${style.value }</option>
				</c:forEach>
			</select>
		</div>
		<div id="dish_3">
			<!-- 搜索菜品表单  -->
			<form action="#" method="post">
				<table width="166px">
					<tr>
						<td>
							<input type="text" id="foodname" name="foodName" class="select_value" placeholder="请输入菜品名称..." />
						</td>
					</tr>
					<tr>
						<td><input type="button" onclick="searchFoodName()" id="sub" value="SELECT"
								   style="font-family: '微软雅黑', '黑体', serif, '宋体';font-size: 15px;
								   background-image: url("${proPath}/app/detail/style/images/look.gif")" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="caidan">
			<h2><button >查看菜单列表</button></h2>
		</div>
	</div>
</body>
</html>