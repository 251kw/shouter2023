<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="src.dto.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html >
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新入力</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<%
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		UserDTO ID = (UserDTO) session.getAttribute("ID");

		String loginId = ID.getLoginId();
		String password = ID.getPassword();
		String userName = ID.getUserName();
		String icon = ID.getIcon();
		String profile = ID.getProfile();

		//指定した名前をいれる、いれたvalue値のこと
		if (request.getParameter("loginId") != null) {
			loginId = request.getParameter("loginId");
		}
		if (request.getParameter("password") != null) {
			password = request.getParameter("password");
		}
		if (request.getParameter("userName") != null) {
			userName = request.getParameter("userName");
		}
		if (request.getParameter("icon") != null) {
			icon = request.getParameter("icon");
		}
		if (request.getParameter("profile") != null) {
			profile = request.getParameter("profile");
		}
	%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー更新入力画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">登録内容を更新します。内容を入力してください。</strong>
			<p>
				<%-- エラー文の挿入 --%>
				<%-- リクエストスコープに alert があれば --%>
				<c:if
					test="${requestScope.alert2 != null && requestScope.alert2 != ''}">
					<%-- リクエストスコープの alert の値を出力 --%>
					<p class="color-error text-left">
						<c:out value="${requestScope.alert2}" />
					</p>
				</c:if>
			</p>
			<p>
				<%-- エラー文の挿入 --%>
				<%-- リクエストスコープに alert があれば --%>
				<c:if
					test="${requestScope.alert3 != null && requestScope.alert3 != ''}">
					<%-- リクエストスコープの alert の値を出力 --%>
					<p class="color-error text-left">
						<c:out value="${requestScope.alert3}" />
					</p>
				</c:if>
			</p>
			<p>
				<%-- エラー文の挿入 --%>
				<%-- リクエストスコープに alert があれば --%>
				<c:if
					test="${requestScope.alert4 != null && requestScope.alert4 != ''}">
					<%-- リクエストスコープの alert の値を出力 --%>
					<p class="color-error text-left">
						<c:out value="${requestScope.alert4}" />
					</p>
				</c:if>
			</p>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 50%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./userEditInputSvt" method="post">
				<table style="width: 700px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<%-- 半角空白禁止 --%>
						<td class="color-main text-left"><span
							class="icon-smile pe-2x pe-va"></span> ログインID</td>
						<td class="text-left"><input  class="form-control" type="text"
							name="loginId" size="20" id="login" pattern="[0-9a-zA-Z]+$"
							placeholder="10文字以下半角英数字空白禁止" autofocus maxlength="10"
							value=<%=loginId%> readonly></td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<%-- 半角空白禁止 --%>
						<td class="color-main text-left"><span
							class="icon-note pe-2x pe-va"></span> パスワード</td>
						<td class="text-left"><input class="form-control"
							type="password" name="password" size="20" pattern="[0-9a-zA-Z]+$"
							placeholder="10文字以下半角英数字空白禁止" maxlength="10" value=<%=password%>></td>
					</tr>
					<tr>
						<td class="color-main text-left"><span
							class="icon-users pe-2x pe-va"></span> ユーザー名</td>
						<td class="text-left"><input class="form-control" type="text"
							name="userName" size="20" id="userName" maxlength="10"
							placeholder="10文字以下" value=<%=userName%>></td>
					</tr>
					<tr>
						<%--checkedで必ずチェックいれる、選択したiconがicon-smileならそっちにチェック--%>
						<td class="color-main text-left"><span
							class="icon-joy pe-2x pe-va"></span> アイコン</td>
						<td><label><input type="radio" name="icon"
								value="icon-users" checked><span
								class="icon-users pe-2x pe-va"></span> </label> <label><input
								type="radio" name="icon" value="icon-smile"
								<%if (icon.equals("icon-smile")) {%> checked <%}%>><span
								class="icon-smile pe-2x pe-va"></span></label></td>
					</tr>
					<tr>
						<td class="color-main text-left"><span
							class="icon-note2 pe-2x pe-va"></span> プロフィール</td>
						<td class="text-left"><input class="form-control" type="text"
							name="profile" size="20" id="profile" maxlength="20"
							placeholder="20文字以下" value=<%=profile%>></td>
					</tr>
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="更新" /></td>
						<td colspan="2"><a href="UserSearchResult.jsp" class="btn">戻る</a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>