<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザー登録結果画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>

	<div class="bg-success padding-y-5">
		<div class="padding-y-5 text-center">
			<h1><strong>ユーザー登録結果画面</strong></h1><br>
			<h6><strong>以下の内容で登録が完了しました。
			</strong></h6>

		</div>
	</div>
	<div class="container padding-y-5">
		<div style="width: 60%:" class="container padding-y-5">
			<form action="index.jsp">

				<table border="1" class="table">
					<tr>
						<th><label for="loginID"><span
								class="icon-note pe-2x peva"></span>&nbsp;ログインID</label></th>
						<td>${user.loginId }</td>
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
						<th><label for="icon"><span
								class="icon-smile pe-2x pe-va"></span>&nbsp;アイコン</label></th>
						<td><span class="${user.icon} pe-2x pe-va"></span></td>
					</tr>
					<tr>
						<th><label for="profile"><span
								class="icon-smile pe-2x pe-va"></span>&nbsp;プロフィール</label></th>
						<td>${user.profile}</td>
					</tr>

				</table>

				<input class="btn"  type="submit" value="戻る" autofocus>
			</form>
		</div>
	</div>
</body>
</html>