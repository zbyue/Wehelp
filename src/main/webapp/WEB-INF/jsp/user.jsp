<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Wehelp后台管理系统</title>
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin.css"/>
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<header class="am-topbar admin-header">
  <div class="am-topbar-brand">
    <strong>Wehelp</strong> <small>后台管理</small>
  </div>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">0</span></a></li>
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-users"></span> 管理员 <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
          <li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
          <li><a href="#"><span class="am-icon-power-off"></span> 退出</a></li>
        </ul>
      </li>
    </ul>
  </div>
</header>


<div class="am-cf admin-main">
  <!-- sidebar start -->
  <div class="admin-sidebar">
    <ul class="am-list admin-sidebar-list">
      <li><a href="/wehelp/admin/main?action=home"><span class="am-icon-home"></span> 首页</a></li>
      <li><a href="/wehelp/admin/main?action=user"><span class="am-icon-file"></span> 用户</a></li>
      <li><a href="/wehelp/admin/main?action=order"><span class="am-icon-table"></span> 订单</a></li>
      <li><a href="/wehelp/admin/main?action=feedback"><span class="am-icon-user-md"></span> 反馈</a></li>
      <li><a href="/wehelp/admin/main?action=message"><span class="am-icon-pencil-square-o"></span> 消息</a></li>
    </ul>

    <div class="am-panel am-panel-default admin-sidebar-panel">
      <div class="am-panel-bd">
        <p><span class="am-icon-bookmark"></span> 公告</p>
        <p>时光静好，与君语；细水流年，与君同。—— Wehelp</p>
      </div>
    </div>

    <div class="am-panel am-panel-default admin-sidebar-panel">
      <div class="am-panel-bd">
        <p><span class="am-icon-tag"></span> wiki</p>
        <p>Welcome to the Wehelp wiki!</p>
      </div>
    </div>
  </div>
  <!-- sidebar end -->
  
  <!-- content start -->
  <div class="admin-content">

    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">用户数:</strong> <small>${countUsers}位</small></div>
    </div>

    <div class="am-g">      
      <div class="am-form-group am-margin-left am-fl">
              <select id="search">
                <option value="all">所有用户</option>
                <option value="id">ID</option>
                <option value="name">用户名</option>
                <option value="state">用户状态</option>
                <option value="credit">信誉值</option>
              </select>
            </div>
      <div class="am-u-md-3 am-cf">
        <div class="am-fr">
          <div class="am-input-group am-input-group-sm">
            <input type="text" class="am-form-field" id="value">
                <span class="am-input-group-btn">
                  <button class="am-btn am-btn-default" type="button" onclick="searchBy()">搜索</button>
                </span>
          </div>
        </div>
       </div>
       <div class="am-u-md-6 am-cf">
        <div class="am-fr am-cf">
          <div class="am-btn-toolbar am-fl">
            <div class="am-btn-group am-btn-group-xs">
              <button type="button" class="am-btn am-btn-default" onclick="edituserinfor('0')"><span class="am-icon-plus"></span> 新增</button>
              <button type="button" class="am-btn am-btn-default" onclick="checkuserinfor()"><span class="am-icon-archive"></span> 审核</button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="am-g">
      <div class="am-u-sm-12">
        <form class="am-form">
          <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
              <tr>
                <td></td><th class="table-id">ID</th><th class="table-title">用户名</th><th class="table-type">昵称</th><th class="table-author">性别</th><th class="table-date">用户状态</th><th class="table-date">信誉值</th><th class="table-date">接单数</th><th class="table-set">操作</th>
              </tr>
          </thead>
          <tbody>
          <c:forEach var="user" items="${userlist}">
            <tr>
              <td></td>
              <td>${user.userid }</td>
              <td>${user.username }</td>
              <td>${user.nickname }</td>
              <td>${user.sex }</td>
              <td>${user.state }</td>
              <td>${user.credit }</td>
              <td>${user.orderscount }</td>
              <td>
                <div class="am-btn-toolbar">
                  <div class="am-btn-group am-btn-group-xs">
                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary" type="button" onclick="edituserinfor(${user.userid})"><span class="am-icon-pencil-square-o"></span> 审核</button>
                    <button class="am-btn am-btn-default am-btn-xs am-text-danger" type="button" onclick="deleteuser(${user.userid})"><span class="am-icon-trash-o"></span> 删除</button>
                  </div>
                </div>
              </td>
            </tr>	
            </c:forEach>
          </tbody>
        </table>
<div class="am-cf">
  <div class="am-fr">
    <ul class="am-pagination">
      <li class="am-disabled"><a href="#">上一页</a></li>
      <li class="am-active"><a href="#">1</a></li>
      <li><a href="#">下一页</a></li>
    </ul>
  </div>
</div>
<div class="am-text-right">
	共 ${countPages}页
</div>
          <hr />
        </form>
      </div>

    </div>
  </div>
  <!-- content end -->
</div>

<script type="text/javascript">
	function searchBy(){
		var option = document.getElementById("search").value;
		var value = document.getElementById("value").value;
		switch(option){
		 	case "all":
				window.location.href="/wehelp/admin/main?action=user";
				break;
		 	case "id":
		 		window.location.href="/wehelp/admin/main?action=userid&uid="+value;
				break;
		 	case "name":
		 		window.location.href="/wehelp/admin/main?action=username&name="+value;
				break;
		 	case "state":
		 		window.location.href="/wehelp/admin/main?action=userstate&state="+value;
				break;
		 	case "credit":
		 		window.location.href="/wehelp/admin/main?action=usercredit&credit="+value;
				break;
		}
	}
	
	function deleteuser(uid){
		var b = window.confirm("Are you sure to delete this user?");
		if(b){
			window.location.href="/wehelp/admin/main?action=deleteuser&uid="+uid;
		}
	}
	
	function edituserinfor(uid){
		window.open("/wehelp/admin/main?action=edituserinfor&uid="+uid);
	}
	
	function checkuserinfor(){
		window.location.href="/wehelp/admin/main?action=userstate&state=0";
	}
</script>
<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/polyfill/rem.min.js"></script>
<script src="assets/js/polyfill/respond.min.js"></script>
<script src="assets/js/amazeui.legacy.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
<!--<![endif]-->
<script src="<%=request.getContextPath()%>/assets/js/amazeui.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/app.js"></script>
</body>
</html>
