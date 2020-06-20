<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="title"/></title>
<link rel="stylesheet" type="text/css" href="../css/login.css">
<script type="text/javascript" src="../js/login.js"></script>
</head>
<body>
	<div id="cloud">
		<div class="login">
			<div class="loginTop">
				<div class="topLeft">
					<span class="logo"></span> <span class="logoName"><spring:message code="title"/></span>
				</div>
				<div class="topRight">
					<ul>
						<li><a href="#"><spring:message code="li1"/></a></li>
						<li><a href="#"><spring:message code="li2"/></a></li>
						<li><a href="#"><spring:message code="li3"/></a></li>
					</ul>
				</div>
			</div>
			<div class="loginContent">
				<span style="color:chocolate;font-weight:bold;margin-left:auto;margin-right:auto;">${messre}</span>
				<form class="content" action="${pageContext.request.contextPath}/User/login" method="post"
					onsubmit="return checkLoad()">

					<p style="text-align: center; font-size: 18px;"><spring:message code="sty32"/></p>
					<input name="username" id="username" type="text"
						placeholder="<spring:message code="name"/>" class="ip" />
					<input name="password"
						id="password" type="password" placeholder="<spring:message code="pass"/>" class="ip" />
					<div class="remeber">
						<input type="checkbox" value="<spring:message code="remeber"/>"><spring:message code="remeber"/>
					</div>
					<input type="submit" class="logon"
						value="<spring:message code="login"/>">
					<div class="loginType">
						<ul>
							<li class="floatLi Bg1"></li>
							<li class="floatLi Bg2"></li>
							<li class="floatLi Bg3"></li>
							<li class="floatLi Bg4"></li>
							<li style="clear: both;"></li>
						</ul>
					</div>
					<div class="loginBottom">
						<ul>
							<li><a href="${pageContext.request.contextPath}/User/toforget"><input type="button" value="<spring:message code="forget"/>"></a></li>
							<li><a href="${pageContext.request.contextPath}/User/toregister"><input type="button" value="<spring:message code="register"/>"></a></li>
						</ul>
						<ur>
							<br>
							<a href="${pageContext.request.contextPath}/User/locale?local=zh">中文</a>|||
							<a href="${pageContext.request.contextPath}/User/locale?local=en">English</a>
						</ur>
					</div>

				</form>

			</div>
		</div>
		<div class="version">
			<ul>
				<li class="bottom versionBg1"></li>
				<li class="bottom versionBg2"></li>
				<li class="bottom versionBg3"></li>
				<li class="bottom versionBg4"></li>
				<li style="clear: both;"></li>
			</ul>
		</div>
	</div>
</body>
</html>