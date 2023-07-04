<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dto.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー情報登録確認</title>
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
				ユーザー登録確認画面<br>登録します。よろしいでしょうか？
			</h1>
		</div>
	</div>
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./uic" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<th class="color-main text-left"><span class="icon-smile pe-2x pe-va"></span>ログインID</th>
						<td>${newUser.loginId}</td>
					</tr>
					<tr>
						<%-- ユーザー名 入力欄の名前は userName --%>
						<th class="color-main text-left"><span class="icon-smile pe-2x pe-va"></span>ユーザー名</th>
						<td>${newUser.userName}</td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<th class="color-main text-left"><span class="icon-smile pe-2x pe-va"></span>パスワード</th>
						<td>${newUser.password}</td>
					</tr>
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<th class="color-main text-left"><span class="icon-smile pe-2x pe-va"></span>アイコン</th>
						<td class="text-left">
							 <span class="${newUser.icon} pe-2x pe-va"></span>
						</td>
					</tr>
					<tr>
						<th class="color-main text-left"><span class="icon-smile pe-2x pe-va"></span>プロフィール</th>
						<td>${newUser.profile}</td>
				   	</tr>
					<tr>
						<td colspan="2" class="text-center"><input class="btn"
							type="submit" name="OK" value="OK" /></td>
						<td colspan="2" class="text-center"><input class="btn"
							type="submit" name="cancel" value="キャンセル" /></td>
					</tr>
				</table>
				<!-- hiddenで送るデータ -->
			<input type="hidden" name=loginId value="<%=newUser.getLoginId()%>">
			<input type="hidden" name=userName value="<%=newUser.getUserName()%>">
			<input type="hidden" name=password value=<%=newUser.getPassword()%>>
			<input type="hidden" name=icon value="<%=newUser.getIcon()%>">
			<input type="hidden" name=profile value="<%=newUser.getProfile()%>">
			</form>
</body>
</html>