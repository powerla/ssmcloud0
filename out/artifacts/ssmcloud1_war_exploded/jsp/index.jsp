<%@ page import="com.czq.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>云端网盘</title>
<link rel="stylesheet" type="text/css" href="../css/index.css">
<link rel="stylesheet" type="text/css" href="../css/jquery/easyui.css">
<link rel="stylesheet" type="text/css" href="./css/jquery/icon.css">
<link rel="stylesheet" type="text/css" href="../css/jquery/demo.css">
<script type="text/javascript" src="../js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<script type="text/javascript">
	function deleteFileFun(fileId) {
		var res = window.confirm("确定要删除该文件吗？");
		if (res) {
			//alert("删除了:" + fileId + "文件");
			window.location = "hdfs.do?type=deleteFile&fileId=" + fileId;
		}
		return res;
	}
</script>
</head>
<body>
	<div id="index">
		<div class="cloud">
			<div class="banner">
				<div class="bannerLeft">
					<span class="logoBg"></span> <span
						style="font-size: 22px; font-weight: bold">云端网盘</span>
				</div>
				<div class="bannerCenter">
					<ul>
						<li class="active"><a href="index.do">网盘</a></li>
						<li><a href="index.do">分享</a></li>
						<li><a href="fileupload.jsp">正在上传</a></li>
						<li><a href="filedownload.jsp">正在下载</a></li>
					</ul>
				</div>
				<div class="bannerRight">
					<span class="person"><img src="../images/person.jpg"></span> <span>${sessionScope.user.username}</span>
<%--					<span style="margin-left: 10px; margin-right: 10px;">丨</span> <span>当前目录:${sessionScope.currentFolder.hdfsPath }</span>--%>
					<span style="margin-left: 10px; margin-right: 10px;">丨</span> <span><a
						href="${pageContext.request.contextPath}/User/outLogin">注销</a></span> <span class="center"><a
						href="${pageContext.request.contextPath}/User/userMessage">会员中心</a></span>
				</div>
			</div>
			<div class="content">
				<div class="contentLeft" id="contentLeft">
					<ul>
<%--						<c:forEach var="rf" items="${sessionScope.sysFolderList }">--%>
<%--							<li--%>
<%--								<c:if test="${sessionScope.currentFolder.hdfsPath==rf.hdfsPath }">class="active"</c:if>>--%>
<%--								<a href="index.do?folderId=${rf.folderId}"> <c:out--%>
<%--										value="${rf.folderName }"></c:out></a>--%>
<%--							</li>--%>
<%--						</c:forEach>--%>
						<li><span class="contentBg bg2"></span><a href="">我的分享</a></li>
						<li><span class="contentBg bg3"></span><a href="${pageContext.request.contextPath}/file/torecycle">回收站</a></li>
					</ul>
				</div>
				<div class="contentRight" id="contentRight">
					<div class="fixedBtn" id="fixedBtn" onclick="showFullPage()">显示全部</div>
					<div class="fixedBtn" id="hideBtn" onclick="hideFullPage()"
						style="display: none;">显示目录</div>
					<div class="operat">
						<div class="operatLeft">
							<div onclick="showFileUpload()">
								<i class="operatLeftBg bg1"></i> <a href="javascript:void(0)">上传</a>
							</div>
							<div>
								<i class="operatLeftBg bg2"></i><a href="javascript:void(0)"
									onclick="$('#createFileWindow').window('open')">新建文件夹</a>
							</div>
							<div>
								<i class="operatLeftBg bg3"></i>离线下载
							</div>
							<div>
								<i class="operatLeftBg bg4"></i>我的设备
							</div>
							${filemess}
							${err}
						</div>
						${filemess}
						${err}
						<div class="operatRight">
							<form action="${pageContext.request.contextPath}/file/findfile?type=${type}">
							<input type="text" id="type" name="type" placeholder="搜索您的文件类型" />
								<input type="submit" value="查询" class="searchBg"/>
<%--							<span class="searchBg"></span>--%>
							<span class="sort"></span>
							<span class="sortTwo"></span>
							</form>
						</div>
						<div style="clear: both;"></div>
					</div>
					<div class="file">
						<table rules="rows" frame="below" bordercolor="#F2F6FD">
							<tr><td colspan="2">全部文件</td></tr>
							<tr>
								<td colspan="2" width="140px"><input type="checkbox"
									disabled="disabled" style="margin-right: 10px" />文件名</td>
								<td>上传人</td>
								<td>类型</td>
								<td>大小</td>
								<td>修改日期</td>
								<td>操作</td>
							</tr>
							<tbody>
							<c:forEach var="file" items="${requestScope.list}">
								<tr>
									<td>${file.fileName}</td>
									<td>${file.owner}</td>
									<td>${file.type}</td>
									<td>${file.fileSize}</td>
									<td>${file.createTime}</td>
									<td align="right">
										<a href="${pageContext.request.contextPath}/file/yulan/${file.fileId}">预览</a>
										&nbsp; | &nbsp;
										<a href="${pageContext.request.contextPath}/file/down/${file.fileId}">下载</a>
										&nbsp; | &nbsp;
										<a href="${pageContext.request.contextPath}/file/deletefile/${file.fileId}" onclick="确定要删除吗">删除</a>
									</td>
								</tr>
							</c:forEach>
							<td class="td2">
								<span>第${requestScope.pagemsg.currPage }/ ${requestScope.pagemsg.totalPage}页</span>
								<span>总记录数：${requestScope.pagemsg.totalCount }  每页显示:${requestScope.pagemsg.pageSize}</span>
								<span>
                        <c:if test="${requestScope.pagemsg.currPage != 1}">
							<a href="${pageContext.request.contextPath }/file/all?currentPage=1">[首页]</a>
							<a href="${pageContext.request.contextPath }/file/all?currentPage=${requestScope.pagemsg.currPage-1}">[上一页]</a>
						</c:if>

                        <c:if test="${requestScope.pagemsg.currPage != requestScope.pagemsg.totalPage}">
							<a href="${pageContext.request.contextPath }/file/all?currentPage=${requestScope.pagemsg.currPage+1}">[下一页]</a>
							<a href="${pageContext.request.contextPath }/file/all?currentPage=${requestScope.pagemsg.totalPage}">[尾页]</a>
						</c:if>
                          </span>
							</td>
							</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="createFileWindow" class="easyui-window" title="Modal Window"
		data-options="modal:true,closed:true,iconCls:'icon-save'"
		style="width: 300px; height: 120px; padding: 10px;">
		<form action="${pageContext.request.contextPath}/file/createfold" method="post">
			<table>
				<tr>
					<td>请输入文件夹名称:</td>
					<td><input id="folderName" name="folderName" type="text" /></td>
				</tr>
				<tr>
					<td></td>
					<td><button type="submit">提交</button></td>
				</tr>
			</table>
		</form>
	</div>
	<form id="uploadForm" action="${pageContext.request.contextPath}/file/up" method="post"
		  enctype="multipart/form-data">
		<input type="file" name="uploadFile" id="uploadFile"
			   style="visibility: hidden; position: absolute; top: 0px; width: 0px" />
	</form>
</body>
</html>