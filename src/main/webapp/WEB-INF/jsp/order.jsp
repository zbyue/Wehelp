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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">订单数:</strong> <small>${countOrders}单</small></div>
    </div>

    <div class="am-g">
      <div class="am-form-group am-margin-left am-fl">
              <select id="search">
                <option value="all">所有订单</option>
                <option value="id">ID</option>
                <option value="type">订单类型</option>
                <option value="rsid">发布人ID</option>
                <option value="tkid">接受者ID</option>
                <option value="state">订单状态</option>
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
    </div>

    <div class="am-g">
      <div class="am-u-sm-12">
        <form class="am-form">
          <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
              <tr>
                <td></td><th class="table-id">ID</th><th class="table-title">订单类型</th><th class="table-type">报酬</th><th class="table-author">发布人</th><th class="table-author">接收人</th><th class="table-date">订单状态</th><th class="table-date">发布时间</th><th class="table-date">截止时间</th><th class="table-set">操作</th>
              </tr>
          </thead>
          <tbody>
          <c:forEach var="order" items="${orderlist}">
            <tr>
              <td></td>
              <td>${order.orderid }</td>
              <td>${order.type}</td>
              <td>${order.reward}</td>
              <td>${order.releaser_id }</td>
              <td>${order.taker_id }</td>
              <td>${order.state }</td>
              <td>${order.releasetime }</td>
              <td>${order.deadline }</td>
              <td>
                <div class="am-btn-toolbar">
                  <div class="am-btn-group am-btn-group-xs">
                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary" type="button" onclick="detailinfor(${order.orderid})"><span class="am-icon-pencil-square-o"></span> 详情</button>
                    <button class="am-btn am-btn-default am-btn-xs am-text-danger" type="button" onclick="deleteorder(${order.orderid})"><span class="am-icon-trash-o"></span> 删除</button>
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
				window.location.href="/wehelp/admin/main?action=order";
				break;
		 	case "id":
		 		window.location.href="/wehelp/admin/main?action=orderid&oid="+value;
				break;
		 	case "type":
		 		window.location.href="/wehelp/admin/main?action=ordertype&type="+value;
				break;
		 	case "state":
		 		window.location.href="/wehelp/admin/main?action=orderstate&state="+value;
				break;
		 	case "rsid":
		 		window.location.href="/wehelp/admin/main?action=orderrsid&rsid="+value;
				break;
		 	case "tkid":
		 		window.location.href="/wehelp/admin/main?action=ordertkid&tkid="+value;
				break;
		}
	}
	
	function deleteorder(id){
		var b = window.confirm("Are you sure to delete this order?");
		if(b){
			window.location.href="/wehelp/admin/main?action=deleteorder&oid="+id;
		}
	}
	
	function detailinfor(id){
		window.open("/wehelp/admin/main?action=detailinfor&oid="+id);
	}
	function checkorderinfor(){
		window.location.href="/wehelp/admin/main?action=orderrstate&state=0";
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
