<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dto.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー情報登録結果</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/style.css">

</head>
<body>
	<%
		UserDTO newUser = (UserDTO)request.getAttribute("newUser");
	%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー登録確認画面<br>以下の内容で登録が完了しました。
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="UserInfoResult" method="get">
				<table style="width: 350px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<th class="color-main text-left"><span class="icon-smile pe-2x pe-va"></span>ログインID</th>
								<%-- ログインIDの表示 --%>
						<td>${newUser.loginId}</td>
					</tr>
					<tr>
						<th class="color-main text-left"><span class="icon-smile pe-2x pe-va"></span>ユーザー名</th>
							<%-- ユーザー名の表示 --%>
						<td>${newUser.userName}</td>
					</tr>
					<tr>
						<th class="color-main text-left"><span class="icon-smile pe-2x pe-va"></span>パスワード</th>
								<%-- パスワードの表示 --%>
						<td>${newUser.password}</td>
					</tr>
					<tr>
						<th class="color-main text-left"><span class="icon-smile pe-2x pe-va"></span>アイコン</th>
						<td>
									<%-- アイコンの表示 --%>
								 <span class="${newUser.icon} pe-2x pe-va"></span>
						</td>
					</tr>
					<tr>
						<th class="color-main text-left"><span class="icon-smile pe-2x pe-va"></span>プロフィール</th>
								<%-- プロフィールの表示 --%>
						<td>${newUser.profile}</td>
				   	</tr>
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" name="cancel" value="戻る" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>