<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="seu.user.pojo.User" %>
<%User user=(User)request.getAttribute("user");%>
<%String uid=(String)request.getAttribute("uid"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin.css"/>
  <title>个人信息</title>
</head>
<style type="text/css"> 
	#container {
		overflow:auto; 
		margin: 0 auto;
	}
</style>
<body id="container">
<!-- content start -->
  <div class="admin-content">
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">个人资料</strong> / <small>ID: </small> <small>${uid}</small></div>
    </div>
	
    <div class="am-g">
      <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
        <div class="am-panel am-panel-default">
          <div class="am-panel-bd">
            <div class="am-g">
              <div class="am-u-md-8">
                <img class="am-img-circle am-img-thumbnail" src="http://p5qrzuh1z.bkt.clouddn.com/${user.img}" alt=""/>
              </div>
            </div>
          </div>
        </div>

        <div class="am-panel am-panel-default">
          <div class="am-panel-bd">
            <div class="user-info">
              <p>信用信息</p>
              <div class="am-progress am-progress-sm">
                <div class="am-progress-bar am-progress-bar-success" style="width: ${user.credit}%"></div>
              </div>
              <p class="user-info-order">当前信誉值：<strong>${user.credit}</strong></p>
              <div class="am-progress am-progress-sm">
                <div class="am-progress-bar am-progress-bar-success" style="width: ${user.evaluation}%"></div>
              </div>
              <p class="user-info-order">当前评价值：<strong>${user.evaluation}</strong></p>
            </div>
          </div>
        </div>
      </div>
      
      <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
        <form id="userinforform" name="userinforform" action="/wehelp/admin/edit"  method="post" class="am-form am-form-horizontal" target="nm_iframe">
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">用户名：</label>
            <div class="am-u-sm-9">
              <input type="text" id="user-name" name="name" placeholder="输入你的用户名" value="${user.username}">
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-nickname" class="am-u-sm-3 am-form-label">昵称：</label>
            <div class="am-u-sm-9">
              <input type="text" id="user-nickname" name="name" placeholder="输入你的昵称" value="${user.nickname}">
            </div>
          </div>

          <div class="am-form-group">
            <label for="user-password" class="am-u-sm-3 am-form-label">密码：</label>
            <div class="am-u-sm-9">
              <input type="text" id="user-password" name="password" placeholder="输入你的密码" value="${user.password}">
            </div>
          </div>
			
		<div class="am-form-group">
            <label for="user-phone" class="am-u-sm-3 am-form-label">电话 ：</label>
            <div class="am-u-sm-9">
              <input type="text" id="user-phone" name="phone" placeholder="输入你的电话" value="${user.phone}">
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-address" class="am-u-sm-3 am-form-label">地址：</label>
            <div class="am-u-sm-9">
              <input type="text" id="user-address" name="address" placeholder="输入你的地址" value="${user.address}">
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-sex" class="am-u-sm-3 am-form-label">性别：</label>
            <div class="am-u-sm-9">
              <input type="text" id="user-sex" name="sex" placeholder="输入你的性别" value="${user.sex}">
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-state" class="am-u-sm-3 am-form-label">用户状态：</label>
            <div class="am-u-sm-9">
              <input type="text" id="user-state" name="state" placeholder="输入你的用户状态" value="${user.state}">
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-credit" class="am-u-sm-3 am-form-label">信誉值：</label>
            <div class="am-u-sm-9">
              <input type="text" id="user-credit" name="credit" placeholder="输入你的信誉值" value="${user.credit}">
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-money" class="am-u-sm-3 am-form-label">余额：</label>
            <div class="am-u-sm-9">
              <input type="text" id="user-money" name="money" placeholder="输入你的余额" value="${user.money}">
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-evaluation" class="am-u-sm-3 am-form-label">评价：</label>
            <div class="am-u-sm-9">
              <input type="text" id="user-evaluation" name="evaluation" placeholder="输入你的评价" value="${user.evaluation}">
            </div>
          </div>

          <div class="am-form-group">
            <label for="user-orderscount" class="am-u-sm-3 am-form-label">订单数：</label>
            <div class="am-u-sm-9">
              <input type="text" id="user-orderscount" name="orderscount" placeholder="输入你的订单数" value="${user.orderscount}">
            </div>
          </div>

          <div class="am-form-group">
            <label for="user-description" class="am-u-sm-3 am-form-label">简介： </label>
            <div class="am-u-sm-9">
              <textarea class="" rows="3" id="user-description" name="description" placeholder="输入个人简介">${user.description}</textarea>
            </div>
          </div>

          <div class="am-form-group">
            <div class="am-u-sm-9 am-u-sm-push-3">
              <button type="button" class="am-btn am-btn-primary" onclick="confirminfor()">保存修改</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
  <iframe id="id_iframe" name="nm_iframe" style="display:none;"></iframe>
  <!-- content end -->
<script type="text/javascript">
	function confirminfor(){
		var name = document.getElementById("user-name").value;
		var password = document.getElementById("user-password").value;
		if(name==""){
			window.confirm("请完善个人信息");
			return false;
		}else if(password==""){
			window.confirm("请完善个人信息");
			return false;
		}else{
			document.getElementById("userinforform").submit();	
			window.confirm("已提交！");
		}
	}
</script>
</body>
</html>