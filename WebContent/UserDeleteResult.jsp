<%@page import="dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 新規会員登録 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/myCss.css">
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				Shouter&nbsp;<span class="icon-speaker"></span>
			</h1>
			<div class="padding-y-5 text-center">
				<c:if
					test="${requestScope.alert_delete == null || requestScope.alert_delete == ''}">
					<div style="width: 40%" class="container padding-y-5 text-center">
						<div class="color-Light">削除が完了しました。</div>
					</div>
				</c:if>
				<c:if
					test="${requestScope.alert_delete != null && requestScope.alert_delete != ''}">
					<%-- リクエストスコープの alert の値を出力 --%>
					<div style="width: 40%" class="container color-error text-left">
						${requestScope.alert_delete}</div>
				</c:if>
			</div>
		</div>
	</div>

	<div class="padding-y-5 text-center">
		<div style="width: 60%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./usr" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<%--戻るボタン --%>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="戻る" /></td>

					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
