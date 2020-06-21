<%--
  Created by IntelliJ IDEA.
  User: HelloZeiKan
  Date: 2020/6/17
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>登录拦截</title>

</head>
<body>
您还没有登录
<div class="main">
    <script type="text/javascript">
        var t=3;//设定跳转的时间
        setInterval("refer()",1000); //启动1秒定时
        function refer(){
            if(t==0){
                location="${pageContext.request.contextPath}/User/tologin"; //设定跳转的链接地址
            }
            document.getElementById('show').innerHTML=""+t+"秒后跳转到登录页面"; //显示倒计时
            t--; //计数器递减
        }
    </script>
</div>
<span id="show"></span>
</body>
</html>