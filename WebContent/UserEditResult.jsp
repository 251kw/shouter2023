<%@page import="dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 更新完了-</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<%
	//文字化け対策
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");

	dto.UserDTO update = (dto.UserDTO)request.getAttribute("updateuser");
	String id= update.getLoginId();

%>
<body>
		<div class="bg-main padding-y-5">
	<div class="padding-y-5 text-center">
		<strong>ユーザ更新結果画面</strong><br>
		<p style=display:inline>以下の内容で更新が完了しました。</p>
	</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
		<form action="./uer" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left"><span class="icon-users pe-2x pe-va"></span>&nbsp;ログインID</td>
						<td><%=id%></td>
					</tr>
					<tr>
						<%-- ユーザー名 入力欄の名前は userName --%>
						<td class="color-main text-left"><span class="icon-users pe-2x pe-va"></span>&nbsp;ユーザー名</td>
						<td><%=update.getUserName()%></td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<td class="color-main text-left"><span class="icon-users pe-2x pe-va"></span>&nbsp;パスワード</td>
						<td><%=update.getPassword()%></td>
					</tr>
					<tr>
						<%-- アイコン 入力欄の名前は icon --%>
						<td class="color-main text-left"><span class="icon-users pe-2x pe-va"></span>&nbsp;アイコン</td>
						<td>
						<label for="icon"><span class = "<%=update.getIcon()%> pe-2x pe-va"></span></label>
						</td>
					</tr>
					<tr>
						<%-- プロフィール 入力欄の名前は profile --%>
						<td class="color-main text-left"><span class="icon-users pe-2x pe-va"></span>&nbsp;プロフィール</td>
						<td><%=update.getProfile()%></td>
					</tr>
					<tr>
					<tr>
						<td colspan="2" class="text-right">
						<button class="btn" type="submit">戻る</button>
						</td>
					</tr>
					<%-- リクエストスコープに alert があれば --%>
					<c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert}" /></td>
						</tr>
					</c:if>
				</table>
				</form>
		</div>
	</div>
</body>
</html>