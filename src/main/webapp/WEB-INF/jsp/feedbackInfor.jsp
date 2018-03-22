<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="seu.complain.pojo.Complain" %>
<% Complain complain=(Complain)request.getAttribute("complain");%>
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
<body>
<style type="text/css"> 
	body,table{ 
		font-size:20px; 
	} 
	table{ 
		table-layout:fixed; 
		empty-cells:show; 
		border-collapse: collapse; 
		margin:0 auto; 
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
<!-- content start -->
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">反馈号</strong>  <small>ID: </small> <small>${complain.complain_id}</small></div>
    </div>
	<div class="am-cf am-padding" >
	<table width="30%" class="table am-fl" >
		<tr>
			<td>申述人ID</td>
			<td>${complain.applier_id}</td>
		</tr>
		<tr>
			<td>申述时间</td>
			<td>${complain.apply_time}</td>
		</tr>
		<tr>
			<td>订单号</td>
			<td>${complain.orderid}</td>
		</tr>
		<tr>
			<td>申述状态</td>
			<td>${complain.state}</td>
		</tr>
		<tr>
			<td>申述类型</td>
			<td>${complain.type}</td>
		</tr>
		<tr>
			<td>申述描述</td>
			<td>${complain.description}</td>
		</tr>
		</table>
		
	</div>
	<div class="am-u-sm-9 am-u-sm-push-3">
            <button type="button" class="am-btn am-btn-primary" onclick="audited(${complain.complain_id})">受理</button>
    </div>
  <iframe id="id_iframe" name="nm_iframe" style="display:none;"></iframe>
  <!-- content end -->
 <script type="text/javascript">
    function audited(id){
    	window.location.href="/wehelp/admin/main?action=auditfeedback&fid="+id;
    }
 </script>
</body>
</html>