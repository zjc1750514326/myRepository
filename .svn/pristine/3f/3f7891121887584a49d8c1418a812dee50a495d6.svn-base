<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/common.jspf"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="UTF-8">
<title>空格键餐饮平台</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.6/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style2.0.css">

<script type="text/javascript">
		// 实现验证码异步刷新
    	function myRefersh( e ) {
            const source = e.src; // 获得原来的 src 中的内容
            //console.log( "source : " + source  ) ;

            var index = source.indexOf("?");  // 从 source 中寻找 ? 第一次出现的位置 (如果不存在则返回 -1 )
            //console.log( "index : " + index  ) ;

            if (index > -1) { // 如果找到了 ?  就进入内部
                var s = source.substring(0, index); // 从 source 中截取 index 之前的内容 ( index 以及 index 之后的内容都被舍弃 )
                //console.log( "s : " + s  ) ;

                var date = new Date(); // 创建一个 Date 对象的 一个 实例
                var time = date.getTime(); // 从 新创建的 Date 对象的实例中获得该时间对应毫秒值
                e.src = s + "?time=" + time; // 将 加了 尾巴 的 地址 重新放入到 src 上

                //console.log( e.src ) ;
            } else {
                var date = new Date();
                e.src = source + "?time=" + date.getTime();
            }
        }
        // 登录
        function formSubmit() {
            // 获取用户名和密码
            var username = $("#username").val();
            var password = $("#password").val();
            var code = $("#code").val();

            if(username == ''){
				$("#n1").html("用户名不能为空");
				return;
			}
			if (password == ''){
                $("#p1").html("密码不能为空");
                return;
			}
            if (code == ''){
                $("#c1").html("验证码不能为空");
                return;
            }
            // 提交表单
            $("#subform").submit();
        }

        // 注册
        function register() {
            window.location.href="${pageContext.request.contextPath}/sys/registerUI.action";
        }
</script>

<style type="text/css">
	ul li{font-size: 30px;color:#2ec0f6;}
	.tyg-div{z-index:-1000;float:left;position:absolute;left:5%;top:20%;}
	.tyg-p{
		font-size: 14px;
	    font-family: 'microsoft yahei';
	    position: absolute;
	    top: 135px;
	    left: 60px;
	}
	.tyg-div-denglv{
		z-index:1000;float:right;position:absolute;right:3%;top:-5%;
	}
	.tyg-div-form{
		background-color: #23305a;
		width:300px;
		height:auto;
		margin:120px auto 0 auto;
		color:#2ec0f6;
	}
	.tyg-div-form form {padding:10px;}
	.tyg-div-form form input[type="text"]{
		width: 270px;
	    height: 30px;
	    margin: 25px 10px 0px 0px;
	}
	.tyg-div-form form button {
	    cursor: pointer;
	    width: 270px;
	    height: 44px;
	    margin-top: 25px;
	    padding: 0;
	    background: #2ec0f6;
	    -moz-border-radius: 6px;
	    -webkit-border-radius: 6px;
	    border-radius: 6px;
	    border: 1px solid #2ec0f6;
	    -moz-box-shadow:
	        0 15px 30px 0 rgba(255,255,255,.25) inset,
	        0 2px 7px 0 rgba(0,0,0,.2);
	    -webkit-box-shadow:
	        0 15px 30px 0 rgba(255,255,255,.25) inset,
	        0 2px 7px 0 rgba(0,0,0,.2);
	    box-shadow:
	        0 15px 30px 0 rgba(255,255,255,.25) inset,
	        0 2px 7px 0 rgba(0,0,0,.2);
	    font-family: 'PT Sans', Helvetica, Arial, sans-serif;
	    font-size: 14px;
	    font-weight: 700;
	    color: #fff;
	    text-shadow: 0 1px 2px rgba(0,0,0,.1);
	    -o-transition: all .2s;
	    -moz-transition: all .2s;
	    -webkit-transition: all .2s;
	    -ms-transition: all .2s;
}
</style>
	<body>
		<div class="tyg-div">
			<ul>
				<li>让</li>
				<li><div style="margin-left:20px;">空</div></li>
				<li><div style="margin-left:40px;">格</div></li>
				<li><div style="margin-left:60px;">改</div></li>
				<li><div style="margin-left:80px;">变</div></li>
				<li><div style="margin-left:100px;">生</div></li>
				<li><div style="margin-left:120px;">活</div></li>
			</ul>
		</div>
		<div id="contPar" class="contPar">
			<div id="page1"  style="z-index:1;">
				<div class="title0">空格键餐饮平台</div>
				<div class="title1">宾客至上，宾至如归</div>
				<div class="imgGroug">
					<ul>
						<img alt="" class="img0 png" src="${pageContext.request.contextPath}/img/page1_0.png">
						<img alt="" class="img1 png" src="${pageContext.request.contextPath}/img/page1_1.png">
						<img alt="" class="img2 png" src="${pageContext.request.contextPath}/img/page1_2.png">
					</ul>
				</div>
				<img alt="" class="img3 png" src="${pageContext.request.contextPath}/img/page1_3.jpg">
			</div>
		</div>
		<div class="tyg-div-denglv">
			<div class="tyg-div-form">
				<form action="${pageContext.request.contextPath}/sys/login.action" method="post" id="subform">
					<h2>登录</h2><p class="tyg-p">欢迎访问  空格键</p>
					<div style="margin:5px 0px;">
						<span id="n1"></span>
						<input type="text" id="username" name="empname" placeholder="请输入账号..."/>
					</div>

					<div style="margin:15px 0px;">
						<span id="p1"></span>
						<input type="password" id="password" name="empps"  placeholder="请输入密码..." style="height: 30px;width: 270px"/>
					</div>

					<div style="margin:5px 0px;">
						<select style="width: 100px" name="empjob">
							<option selected="selected">普通员工</option>
							<option>经理</option>
						</select>
						<!-- <input type="text" name="empjob" placeholder="请输入职位..."/> -->
					</div>

					<div style="margin:0px 0px;">
						<c:if test="${ error1!=null}">
							<font color="red">${ error1}</font>
						</c:if>
						<span id="c1"></span>

						<img src="${pageContext.request.contextPath}/verify/regist.do" onclick="myRefersh(this)" style="margin-top: 15px">
						<input type="text" id="code" name="verifyCode"  style="width:100px;" placeholder="请输入验证码..." autocomplete="off"/>
					</div>
					<button type="button" onclick="formSubmit()">登<span style="width:10px;"></span>录</button>
					<button type="button" onclick="register()" >注<span style="width:10px;"></span>册</button>
				</form>
			</div>
		</div>

		<%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>--%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/com.js"></script>
		<!--[if IE 6]>
		<script language="javascript" type="text/javascript" src="./script/ie6_png.js"></script>
		<script language="javascript" type="text/javascript">
				DD_belatedPNG.fix(".png");
		</script>
		<![endif]-->

	</body>
</html>