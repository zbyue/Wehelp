<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
    <title>登陆</title>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"> 
  	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/amazeui.min.css"/>
  <style>
    .header {
      text-align: center;
    }
    .header h1 {
      font-size: 200%;
      color: #333;
      margin-top: 30px;
    }
    .header p {
      font-size: 14px;
    }
  </style>	
</head>
<body >
 <div class="header">
  <div class="am-g">
    	<h1>Wehelp</h1>
    	<p>后台管理系统</p>
  	</div>
  	<hr />
 </div>
  	<div class="am-g">
  	   <div class="am-u-lg-3 am-u-md-8 am-u-sm-centered">
  		 <form name="thisform" action="/wehelp/admin/login" method="post" class="am-form"> 
  		    <label for="username">用户:</label>
  		    <input type="text" class="form" id="username" name="username" placeholder="用户名" value="zby" >                                                
  		    <br>                                  
  		    <label for="password">密码:</label>                                                     
  		    <input type="password" class="form" id="password" name="password" placeholder="密码" value="123" >
  		    <br>
  		    <br>
  		    <div align="center">
  		      <button type="submit" class="button am-btn am-btn-primary am-btn-sm am-fr" onclick="cf()" > 登录</button>
  		    </div>                                                 
  	      </form>                                                      
  		</div>
  	</div>
</body>

<script type="text/javascript">
  	function cf(){
  		var t = document.getElementById("username").value;
  		var p = document.getElementById("password").value;
  		if(t==""){	
  			return false;
  		}
  		if(p==""){	
  			return false;
  		}else{
  			return true;
  		}
  	}
</script>
</html>
