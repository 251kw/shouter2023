<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter - ユーザー情報登録 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<%
		String loginId = "";
		//hiddenで送られたデータがからでなければ代入
		if (request.getParameter("loginId") != null) {
			loginId = request.getParameter("loginId");
		}
	%>
	<%
		String userName = "";
		//hiddenで送られたデータがからでなければ代入
		if (request.getParameter("userName") != null) {
			userName = request.getParameter("userName");
		}
	%>
	<%
		String password = "";
		//hiddenで送られたデータがからでなければ代入
		if (request.getParameter("password") != null) {
			password = request.getParameter("password");
		}
	%>
	<%
		String profile = "";
		//hiddenで送られたデータがからでなければ代入
		if (request.getParameter("profile") != null) {
			profile = request.getParameter("profile");
		}
	%>
	<%
		String icon = "";
		if (request.getParameter("icon") != null) {
			icon = request.getParameter("icon");
		}
	%>
	<%
		String smileIcon = "";
		String noteIcon = "";
		String joyIcon = "";
		String usersIcon = "";
		if (icon.equals("icon-users")) {
			usersIcon = "checked";
		} else if (icon.equals("icon-note")) {
			noteIcon = "checked";
		} else if (icon.equals("icon-joy")) {
			joyIcon = "checked";
		} else {
			smileIcon = "checked";
		}
	%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー登録入力画面<br>ユーザー登録します。内容を入力してください。
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">登録情報を入力してください。</strong>
		</div>
	</div>
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./uii" method="post">
				<table style="width: 500px" class="table">
					<c:if
						test="${requestScope.alertSpace != null && requestScope.alertSpace != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alertSpace}" /></td>
						</tr>
					</c:if>
					<c:if
						test="${requestScope.alertBlank != null && requestScope.alertBlank != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left">
							<c:out value="${requestScope.alertBlank}" /></td>
						</tr>
					</c:if>
					<c:if
						test="${requestScope.alertHalf_width != null && requestScope.alertHalf_width != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left">
							<c:out value="${requestScope.alertHalf_width}" /></td>
						</tr>
					</c:if>
					<c:if
						test="${requestScope.alertDuplication != null && requestScope.alertDuplication != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left">
							<c:out	value="${requestScope.alertDuplication}" /></td>
						</tr>
					</c:if>
					<c:if
						test="${requestScope.alertMaxlimit_ID != null && requestScope.alertMaxlimit_ID != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left">
							<c:out	value="${requestScope.alertMaxlimit_ID}" /></td>
						</tr>
					</c:if>
					<c:if
						test="${requestScope.alertMaxlimit_uName != null && requestScope.messageMaxlimit_uName != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left">
							<c:out	value="${requestScope.alertMaxlimit_uName}" /></td>
						</tr>
					</c:if>
					<c:if
						test="${requestScope.alertMaxlimit_password != null && requestScope.alertMaxlimit_password != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left">
							<c:out	value="${requestScope.alertMaxlimit_password}" /></td>
						</tr>
					</c:if>
					<c:if
						test="${requestScope.alertMaxlimit_profile != null && requestScope.alertMaxlimit_profile != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left">
							<c:out	value="${requestScope.alertMaxlimit_profile}" /></td>
						</tr>
					</c:if>
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left"><span class="icon-smile pe-2x pe-va"></span>ログインID</td>
						<td class="text-left"><input class="form-control" type="text"
							name="loginId" value="<%=loginId%>" size="20" placeholder="10文字以内、半角英数字で入力してください。"autofocus /></td>
					</tr>
					<tr>
						<%-- ユーザー名 入力欄の名前は userName --%>
						<td class="color-main text-left"><span class="icon-smile pe-2x pe-va"></span>ユーザー名</td>
						<td class="text-left"><input class="form-control" type="text"
							name="userName" value="<%=userName%>" placeholder="10文字以内で入力してください。"size="20" /></td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<td class="color-main text-left"><span class="icon-smile pe-2x pe-va"></span>パスワード</td>
						<td class="text-left"><input class="form-control"
							type="password" name="password" value="<%=password%>" size="20" placeholder="10文字以内で入力してください。"/></td>
					</tr>
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left"><span class="icon-smile pe-2x pe-va"></span>アイコン</td>
						<td class="text-left">
							<div class="icon">
								<span class="icon-user-female pe-2x pe-va"></span> <input type="radio"
									name="icon" value="icon-user-female" <%=smileIcon%>>
							</div>
							<div class="icon">
								<span class="icon-bell pe-2x pe-va"></span> <input type="radio"
									name="icon" value="icon-bell" <%=noteIcon%>>
							</div>
							<br>
							<div class="icon">
								<span class="icon-user pe-2x pe-va"></span> <input type="radio"
									name="icon" value="icon-user" <%=usersIcon%>>
							</div>
							<div class="icon">
								<span class="icon-smile pe-2x pe-va"></span> <input type="radio"
									name="icon" value="icon-smile" <%=joyIcon%>>
							</div>
						</td>
					</tr>
					<tr>
						<td class="color-main text-left"><span class="icon-smile pe-2x pe-va"></span>プロフィール</td>
						<td class="text-left"><input class="form-control" type="text"
							name="profile" value="<%=profile%>" size="20" placeholder="50文字以内で入力してください。"/>
					</tr>
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" name="register" value="登録" /></td>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" name="return" value="戻る" /></td>
					</tr>
				</table>
			</form>
</body>
</html>