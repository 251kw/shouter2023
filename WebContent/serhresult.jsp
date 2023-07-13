<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter-検索結果-</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>

<style>
input {
	white-space: pre;
}

input:hover {
	background: rgba(80, 200, 200, 0.2);
}

textarea:hover {
	background: rgba(80, 200, 200, 0.2);
}

thead {
	background: rgba(80, 200, 200, 0.2);
}

.fancy-radio :hover:before {
	box-shadow: 0px 0px 0px 8px rgba(80, 200, 200, 0.2);
	border-radius: 50%;
	opacity: 1;
}

.required::after {
	content: "必須";
	background-color: #f0ad4e;
	color: #fff;
	font-size: 12px;
	font-weight: bold;
	min-width: 10px;
	padding: 3px 7px;
	margin: 0px 5px;
	line-height: 1;
	vertical-align: middle;
	white-space: nowrap;
	text-align: center;
	border-radius: 10px;
	display: inline-block;
}
</style>
<script>
	//テキストエリアの値を取得
	const check = document.getElementsByName("check")

	function sendProfile() {
		console.log(document.form1.profile.value);
	}

	function checkAll(tof) {

		for (i = 0; i < check.length; i++) {
			check[i].checked = tof;
		}

	}
</script>
<%
	//文字化け対策
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");

	String[] deleteUsers = request.getParameterValues("deleteUsers");

	if (deleteUsers != null) {
		ArrayList<String> deleteUsersList = new ArrayList<>();

		for (String str : deleteUsers) {
			deleteUsersList.add(str);
		}
		pageContext.setAttribute("deleteUsersList", deleteUsersList);
	}
%>

<c:if
	test="${requestScope.alert.equals ('ログインIDを英数字8桁以下入力してください')||
							requestScope.alert.equals ('ログインIDが既に存在する')}">
	<style>
input[name="loginId"]:focus {
	background: pink;
}
</style>
</c:if>
<c:if test="${requestScope.alert.equals ('ユーザー名を8文字以下入力してください') }">
	<style>
input[name="userName"]:focus {
	background: pink;
}
</style>
</c:if>
<c:if test="${requestScope.alert.equals ('パスワードを英数字8桁以下入力してください') }">
	<style>
input[name="password"]:focus {
	background: pink;
}
</style>
</c:if>

<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>ユーザー検索結果画面</h1>
		</div>
	</div>

	<%-- セッションスコープにある ArrayList 型のオブジェクトを参照 --%>
	<jsp:useBean id="users" scope="session"
		type="java.util.ArrayList<dto.UserDTO>" />

	<div class="padding-y-5">
		<div style="width: 60%" class="container padding-y-5">

			<%-- 全選択と全解除ボタン --%>

			<%
				int count = 0;
			%>
			<c:forEach var="user" items="${users}">
				<%
					count++;
				%>
			</c:forEach>

			<%
				if (count == 0) {
			%>
			<table style="width: 1000px" class="table table-bordered">
				<tr>
					<td>エラー</td>
					<td><input class="btn" type="button" value="戻る"
						onClick="window.location.href='./search.jsp'" /></td>
				</tr>
			</table>
			<%
				} else {
			%>
			<input class="btn" type="button" value="全選択" onClick="checkAll(true)" />
			<input class="btn" type="button" value="全解除"
				onClick="checkAll(false)" />

			<form method="post" action="./udc">

				<table style="width: 1000px" class="table table-bordered">

					<thead>
						<tr>
							<th style="width: 50px"></th>
							<th style="width: 120px">ログインID</th>
							<th style="width: 120px">ユーザー名</th>
							<th style="width: 120px">アイコン</th>
							<th style="width: 120px">プロフィール</th>
							<th style="width: 120px"></th>
						</tr>
					</thead>
					<c:forEach var="user" items="${users}">
						<%-- action 属性にサーブレットを指定 --%>

						<tbody>

							<tr>

								<td style="width: 50px"><label class="fancy-checkbox">
										<input name="check" type="checkbox" value="${user.loginId}"
										id="checkbox${user.loginId}"
										${deleteUsersList.contains(user.loginId) ? 'checked' : ''}>
										<span></span>
								</label></td>
								<td style="width: 120px">${user.loginId}</td>
								<td style="width: 120px">${user.userName}</td>
								<td style="width: 120px"><span
									class="${user.icon} pe-3x pe-va"></span></td>
								<td style="width: 200px">${user.profile}</td>
								<td style="width: 120px">
									<button type="submit" name="editId"
										class="btn btn-light btn-sm btn-empty" value="${user.loginId}"
										formaction="./userEditInput.jsp">編集</button>
								</td>
							</tr>
						</tbody>
					</c:forEach>
					<%-- リクエストスコープにalertがあれば --%>

				</table>
				<c:if
						test="${requestScope.alert !=null && requestScope.alert != '' }">
							<%-- リクエストスコープの alert の値を出力 --%>
							<label class="color-error text-left"><c:out
									value="${requestScope.alert}" /></label>
					</c:if>


				<%-- 全選択と全解除ボタン --%>
				<input class="btn" type="button" value="全選択"
					onClick="checkAll(true)" /> <input class="btn" type="button"
					value="全解除" onClick="checkAll(false)" />
				<%-- 削除と戻るボタン --%>
				<input class="btn" type="submit" value="削除" onclick="sendProfile" />
				<input class="btn" type="button" value="戻る"
					onClick="window.location.href='./search.jsp'" />
				<%
					}
				%>
			</form>
		</div>
	</div>
</body>
</html>

