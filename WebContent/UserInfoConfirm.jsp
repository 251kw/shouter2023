<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dto.UserDTO"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザー登録確認画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>

	<%
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	%>

	<div class="bg-success padding-y-5">
		<div class="padding-y-5 text-center">
			<h1><strong>ユーザー登録確認画面</strong></h1>
			<h6><strong>
			登録します。よろしいでしょうか？
			</strong></h6>

		</div>
	</div>
	<div class="container padding-y-5">
		<div style="width: 60%:" class="container padding-y-5">
			<%-- action=?にすることでactionでの転送先をあとで宣言できる --%>
			<form action="?" method="post">

				<table border="1" class="table">
					<tr>
						<th><label for="loginID"><span
								class="icon-note pe-2x peva"></span>&nbsp;ログインID</label></th>
						<td>${user.loginId }</td>
						<%-- userのloginIdを引っ張ってくる --%>
					</tr>
					<tr>
						<th><label for="userName"><span
								class="icon-smile pe-2x pe-va"></span>&nbsp;ユーザー名</label></th>
						<td>${user.userName}</td>
					</tr>
					<tr>
						<th><label for="pass"><span
								class="icon-note pe-2x peva"></span>&nbsp;パスワード</label></th>
						<td>${user.password}</td>
					</tr>
					<tr>
						<th><label for="userName"><span
								class="icon-smile pe-2x pe-va"></span>&nbsp;アイコン</label></th>
						<td><span class="${user.icon}"></span></td>
						<%-- iconをアイコンとして表示されるためにlabelでくくる --%>
					</tr>
					<tr>
						<th><label for="userName"><span
								class="icon-smile pe-2x pe-va"></span>&nbsp;プロフィール</label></th>
						<td>${user.profile}</td>
					</tr>

				</table>

<input class="btn"  type="submit" value="OK" formaction="./ucs">&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="hidden" name="hidden-loginID" value="${user.loginId }">
				<input type="hidden" name="hidden-username" value="${user.userName }">
				<input type="hidden" name="hidden-pass" value="${user.password }">
				<input type="hidden" name="hidden-geticon" value="${user.icon }">
				<input type="hidden" name="hidden-profile" value="${user.profile }">
				<%-- fromactionで送信先の決定 --%>
				<input class="btn"  type="submit" value="キャンセル" formaction="UserInfoInput.jsp">


			</form>
		</div>
	</div>
</body>
</html>