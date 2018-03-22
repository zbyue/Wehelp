<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="seu.orders.pojo.Orders" %>
<% Orders order=(Orders)request.getAttribute("order");%>
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
  <title>订单信息</title>
</head>
<style type="text/css"> 
	#container {
		overflow:auto; 
		margin: 0 auto;
	}
	body,table{ 
		font-size:20px; 
	} 
	table{ 
		table-layout:fixed; 
		empty-cells:show; 
		border-collapse: collapse; 
		margin:0 auto; 
		overflow-y:auto;
	} 
	td{ 
		height:30px; 
	} 
	.table{ 
		border:1px solid #cad9ea; 
		color:#666; 
	} 
	.table th { 
		background-repeat:repeat-x; 
		height:30px; 
	} 
	.table td,.table th{ 
		border:1px solid #cad9ea; 
		padding:0 1em 0; 
	} 
	.table tr.alter{ 
		background-color:#f5fafe; 
	} 
</style> 
<body id="container">
<!-- content start -->
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">订单号</strong>  <small>ID: </small> <small>${order.orderid}</small></div>
    </div>
	<div class="am-cf am-padding" >
	<table width="30%" class="table am-fl" >
		<tr>
			<td>订单类型</td>
			<td colspan="2">${order.type}</td>
		</tr>
		<tr>
			<td>报酬</td>
			<td colspan="2">${order.reward}</td>
		</tr>
		<tr>
			<td>发布人</td>
			<td colspan="2">${order.releaser_id}</td>
		</tr>
		<tr>
			<td>接受人</td>
			<td colspan="2">${order.taker_id}</td>
		</tr>
		<tr>
			<td>时间限制</td>
			<td colspan="2">${order.time_limit}</td>
		</tr>
		<tr>
			<td>发布时间</td>
			<td colspan="2">${order.releasetime}</td>
		</tr>
		<tr>
			<td>截止时间</td>
			<td colspan="2">${order.deadline}</td>
		</tr>
		<tr>
			<td>订单地址</td>
			<td colspan="2">${order.address}</td>
		</tr>
		<tr>
			<td>订单状态</td>
			<td colspan="2">${order.state}</td>
		</tr>
		<tr>
			<td>图片是否可见</td>
			<td colspan="2">${order.privateright}</td>
		</tr>
		<tr>
			<td>描述</td>
			<td colspan="2">${order.description}</td>
		</tr>
		</table>
		<div class="am-u-sm-12 am-u-md-5">
        <div class="am-panel am-panel-default">
          <div class="am-panel-bd">
            <div class="am-g">
              <div class="am-u-md-6">
              	<c:forEach var="string" items="${img}">
                <img class="am-img-circle am-img-thumbnail" src="http://p5qrzuh1z.bkt.clouddn.com/${string}" alt=""/>
                </c:forEach>
              </div>
            </div>
          </div>
        </div>
      </div>
	</div>
  <iframe id="id_iframe" name="nm_iframe" style="display:none;"></iframe>
  <!-- content end -->
</body>
</html>