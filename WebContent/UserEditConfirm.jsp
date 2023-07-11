<%@page import="dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 会員情報更新 -</title>
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
				<div style="width: 40%" class="container padding-y-5 text-center">
					<div class="color-Light">更新します。よろしいでしょうか？</div>
				</div>
			</div>
		</div>
	</div>

	<%
		UserDTO user = (UserDTO) request.getAttribute("user");
	%>
	<div class="padding-y-5 text-center">
		<div style="width: 60%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="UserEditInput.jsp" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<%-- ログインID --%>
						<td class="color-main text-left"><span
							class="icon-id pe-2x pe-va"></span> <nobr>ログインID</nobr></td>
						<td class="text-left">${user.loginId}</td>
					</tr>
					<tr>
						<%-- ユーザー名 --%>
						<td class="color-main text-left"><span
							class="icon-unlock pe-2x pe-va"></span> <nobr>ユーザー名</nobr></td>
						<td class="text-left">${user.userName}</td>
					</tr>
					<tr>
						<%-- パスワード--%>
						<td class="color-main text-left"><span
							class="icon-unlock pe-2x pe-va"></span> <nobr>パスワード</nobr></td>
						<td class="text-left">${user.password}</td>
					</tr>
					<tr>
						<%--　アイコン --%>
						<td class="color-main text-left"><span
							class="icon-angle-up-circle pe-2x pe-va"></span> <nobr>アイコン</nobr></td>
						<td><label for="icon" class="<%=user.getIcon()%> pe-3x pe-va"><span></span></label></td>
					</tr>
					<tr>
						<%-- プロフィールの名前は plof --%>
						<td class="color-main text-left"><span
							class="icon-display2 pe-2x pe-va"></span> <nobr>プロフィール</nobr></td>
						<td class="text-left">${user.profile}</td>
					</tr>
					<tr>
						<%--送信ボタンと戻るボタン --%>
						<td><input type="hidden" id="loginId" name="loginId"
							value="<%=user.getLoginId()%>"> <input type="hidden"
							id="userName" name="userName" value="<%=user.getUserName()%>">
							<input type="hidden" id="password" name="password"
							value="<%=user.getPassword()%>"> <input type="hidden"
							id="icon" name="icon" value="<%=user.getIcon()%>"> <input
							type="hidden" id="prof" name="prof"
							value="<%=user.getProfile()%>"></td>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="OK" formaction="./uec" /></td>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="キャンセル" /></td>

					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
