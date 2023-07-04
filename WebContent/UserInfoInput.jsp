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
		String loginId = "";
		String userName = "";
		String password = "";
		String icon = "";
		String prof = "";
		String check_smile = "checked";
		String check_users = "";

		if (request.getParameter("loginId") != null) {
			loginId = request.getParameter("loginId");
			userName = request.getParameter("userName");
			password = request.getParameter("password");
			icon = request.getParameter("icon");
			prof = request.getParameter("prof");
		}
		if (icon.equals("icon-smile pe-2x pe-va")) {
			check_smile = "checked";
		} else if (icon.equals("icon-users pe-2x pe-va")) {
			check_users = "checked";
		}
	%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				Shouter&nbsp;<span class="icon-speaker"></span>
			</h1>
			<div class="padding-y-5 text-center">
				<div style="width: 40%" class="container padding-y-5 text-center">
					<div class="color-Light">ユーザー登録します。内容を入力してください。</div>
				</div>
			</div>
		</div>
	</div>


	<%--半角英数字エラーの確認と表示 --%>
	<c:if
		test="${requestScope.alert_id != null && requestScope.alert_id != ''}">
		<%-- リクエストスコープの alert の値を出力 --%>
		<div style="width: 40%" class="container color-error text-left">
			${requestScope.alert_id}</div>
	</c:if>

	<%--空白の入力欄の確認と表示 --%>
	<c:if
		test="${requestScope.alert_null != null && requestScope.alert_null != ''}">
		<div style="width: 40%" class="container color-error text-left">
			${requestScope.alert_null}</div>
	</c:if>

	<%--空白から始まってないかの確認 --%>
	<c:if
		test="${requestScope.alert_space != null && requestScope.alert_space != ''}">
		<div style="width: 40%" class="container color-error text-left">
			${requestScope.alert_space}</div>
	</c:if>

	<%--ログインIDが既存のものとかぶっていないか判定 --%>
	<c:if
		test="${requestScope.alert_db != null && requestScope.alert_db != ''}">
		<div style="width: 40%" class="container color-error text-left">
			${requestScope.alert_db}</div>
	</c:if>

	<%--ログインIDとパスワードにスペースが含まれていないかの確認 --%>
	<c:if
		test="${requestScope.alert_con != null && requestScope.alert_con != ''}">
		<div style="width: 40%" class="container color-error text-left">
			${requestScope.alert_con}</div>
	</c:if>

	<%-- 11文字以上のエラー --%>
	<c:if
		test="${requestScope.alert_elev != null && requestScope.alert_elev != ''}">
		<div style="width: 40%" class="container color-error text-left">
			${requestScope.alert_elev}</div>
	</c:if>

	<%-- 101文字以上のエラー --%>
	<c:if
		test="${requestScope.alert_thou != null && requestScope.alert_thou != ''}">
		<div style="width: 40%" class="container color-error text-left">
			${requestScope.alert_thou}</div>
	</c:if>


	<div class="padding-y-5 text-center">
		<div style="width:60%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./uii" method="post">
				<table style="width: 800px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left"><span
							class="icon-id pe-2x pe-va"></span><nobr>ログインID</nobr></td>
						<td class="text-left"><input class="form-control" type="text"
							name="loginId" value="<%=loginId%>" size="20" autofocus /></td>
					</tr>
					<tr>
						<%-- ユーザー名 入力欄の名前は userName --%>
						<td class="color-main text-left"><span
							class="icon-users pe-2x pe-va"></span><nobr>ユーザー名</nobr></td>
						<td class="text-left"><input class="form-control" type="text"
							name="userName" value="<%=userName%>" size="20" /></td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<td class="color-main text-left"><span
							class="icon-unlock pe-2x pe-va"></span><nobr>パスワード</nobr></td>
						<td class="text-left"><input class="form-control"
							type="password" name="password" value="<%=password%>" size="20" /></td>
					</tr>
					<tr>
						<td class="color-main text-left"><span
							class="icon-angle-up-circle pe-2x pe-va"></span><nobr>アイコン</nobr></td>
						<td class="text-left">
							<%--　アイコン入力欄の名前は icon --%>
							<div class="icon">
								<span class="icon-smile pe-2x pe-va"></span> <input type="radio"
									name="icon" id="icon-smile" value="icon-smile pe-2x pe-va"
									<%=check_smile%> />
							</div>
							<div class="icon">
								<span class="icon-users pe-2x pe-va"></span> <input type="radio"
									name="icon" id="icon-users" value="icon-users pe-2x pe-va"
									<%=check_users%> />
							</div>

						</td>
					</tr>
					<tr>
						<%-- プロフィールの名前は prof --%>
						<td class="color-main text-left"><span
							class="icon-display2 pe-2x pe-va"></span><nobr>プロフィール</nobr></td>
						<td class="text-left"><input type="text" class="form-control"
							name="prof" id="prof" value="<%=prof%>" size="60" /></td>
					</tr>

					<tr>
						<%--送信ボタンと戻るボタン --%>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="登録" /></td>
						<td colspan="2" class="text-right"><a href="index.jsp"
							class="btn">戻る</a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
