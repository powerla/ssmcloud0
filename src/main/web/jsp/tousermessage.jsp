<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HelloZeiKan
  Date: 2020/6/20
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>用户信息</title>
    <link rel="stylesheet" type="text/css" href="../css/register.css">
    <script type="text/javascript" src="../js/register.js"></script>
</head>
<body>
<div id="register">
    <div class="registerTop">
        <div class="topContent">
            <div class="contentLeft">
                <span class="logoBg"></span>
                <span style="border-left: 1px solid #C9C9C9; margin-left: 10px; padding-left: 10px">用户信息</span>
            </div>
            <div style="clear: both;"></div>
            <div><a href="${pageContext.request.contextPath}/file/all">返回</a></div>
        </div>
    </div>
    <div class="content">
        <div class="reContent">
            <div class="reContentLeft">
                    <div>
                        用户名称：<input type="text"  readonly="readonly" value="${sessionScope.user.username}">
                    </div>
                    <div>
                        手机号码：<input type="text" readonly="readonly"  value="${sessionScope.user.phone}">
                    </div>
                    <div>
                        电子邮箱：<input type="text" readonly="readonly"  value="${sessionScope.user.email}">
                    </div>
                    <div>
                        上传且未删除的文件为${count1}个:</br>
                        <table rules="rows" frame="below" bordercolor="#F2F6FD">
                            <tr>
                                <td colspan="2">全部文件</td>
                            </tr>
                            <tr>
                                <td colspan="2" width="140px"><input type="checkbox"
                                                                     disabled="disabled" style="margin-right: 10px" />文件名</td>
                                <td>类型</td>
                                <td>大小</td>
                                <td>修改日期</td>
                            </tr>
                            <tbody>
                            <c:forEach var="file" items="${list1}">
                                <tr>
                                    <td>${file.fileName}</td>
                                    <td>${file.type}</td>
                                    <td>${file.fileSize}</td>
                                    <td align="right">${file.createTime}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div>
                        删除的文件为${count0}个:</br>
                        <table rules="rows" frame="below" bordercolor="#F2F6FD">
                            <tr>
                                <td colspan="2">全部文件</td>
                            </tr>
                            <tr>
                                <td colspan="2" width="140px"><input type="checkbox"
                                                                     disabled="disabled" style="margin-right: 10px" />文件名</td>
                                <td>类型</td>
                                <td>大小</td>
                                <td>修改日期</td>
                            </tr>
                            <tbody>
                            <c:forEach var="file" items="${list0}">
                                <tr>
                                    <td>${file.fileName}</td>
                                    <td>${file.type}</td>
                                    <td>${file.fileSize}</td>
                                    <td align="right">${file.createTime}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
            </div>
            <div style="clear: both;"></div>
        </div>
    </div>
    <div class="reBottom">
        <img src="../images/foot_pic.jpg">
    </div>
</div>
</body>
</html>