<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/global.jsp"%>
	<title>登录页</title>
	<script>
		var logon = ${not empty user};
		if (logon) {
			location.href = '${ctx}/main/index';
		}
	</script>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/include-jquery-ui-theme.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    
    <script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
    <script src="${ctx }/js/common/plugins/jui/jquery-ui.min.js" type="text/javascript"></script>
    <script type="text/javascript">
	$(function() {
		$('button').button({
			icons: {
				primary: 'ui-icon-key'
			}
		});
	});
	</script>
</head>

<body style="margin-top: 3em;">
	<center>
	<div style="width: 350px">
		<h2>工作流引擎Activiti演示</h2>
		<hr />
		<form action="${ctx }/user/logon" method="get">
			<table>
				<tr>
					<td width="80">用户名：</td>
					<td><input id="username" name="username" /></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input id="password" name="password" type="password" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<button type="submit">登录系统</button>
					</td>
				</tr>
			</table>
		</form>
		<hr />
		<table border="1">
			<caption>用户列表(密码：000000)</caption>
			<tr>
				<th style="text-align: center">用户名</th>
				<th style="text-align: center">角色</th>
			</tr>
			<tr>
				<td>admin</td>
				<td>管理员、用户</td>
			</tr>
			<tr>
				<td>kafeitu</td>
				<td>用户</td>
			</tr>
			<tr>
				<td>hruser</td>
				<td>人事、用户</td>
			</tr>
			<tr>
				<td>leaderuser</td>
				<td>部门经理、用户</td>
			</tr>
		</table>
		<hr />
		<p>
			<b>Wiki：</b><a target="_blank" href="https://github.com/henryyan/kft-activiti-demo/wiki">https://github.com/henryyan/kft-activiti-demo/wiki</a>
		</p>
	</div>
	</center>
</body>
</html>
