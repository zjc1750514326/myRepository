<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/common/common.jspf"%>
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="detail/style/js/jquery.js"></script>
<script type="text/javascript" src="detail/style/js/page_common.js"></script>
	<script type="text/javascript" src="detail/style/js/Util.js"></script>
<link href="detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="detail/style/css/index_1.css" />
	<style type="text/css">
		* {
			margin: 0px;
			padding: 0px
		}
		#dish_2 a{
			text-decoration:none;
			font-size:36px;
			color:#000;
		}
		#dish_2 ul {
			list-style:none;
		}
		#dish_2 li{
			width:164px;
			height:47px;
			text-align:center;
			padding-top:5px;
		}
		#east{
			border:0px;
			background-color:transparent;"
		}
	</style>
	<script type="text/javascript">
		// 设置长度为8，范围为0-9的变数，UUID
		var orderId = uuid(8,10);

		function submit(value) {
            window.location.href="${proPath}/sys/food/caidan.action?stylename=&&tableId="+value+"&&orderId="+orderId;
        }
	</script>
</head>
<body style="text-align: center; 
	background-image: url(detail/style/images/index_menu.gif); background-position:  right; position: fixed;">
	<!--外部的大层-->
	<div class="index_all">
			<!--放桌子的层-->
			<div id="center_bottom">
				<ul style=" display:inline-table">
						<li>
							<button id="east" value="1" onclick="submit(this.value)"><font size="5px" color="red">东</font>&nbsp;</button>
						</li>
					
						<li>
							<a href="detail/caidan.jsp">
								西&nbsp;
							</a>
						</li>
					
						<li>
							<a href="detail/caidan.jsp">
								南&nbsp;
							</a>
						</li><li>
							<a href="detail/caidan.jsp">
								北&nbsp;
							</a>
						</li>
				</ul>
			</div>
	</div>
</body>
</html>