<%@page import="dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザー更新結果画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<%
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

			//変数の初期化
			String loginId = null;
			String userName = null;
			String password = null;
			String icon = null;
			String profile = null;

			UserDTO user = (UserDTO)request.getAttribute("updateUser");

			//編集ボタンが押されたとき
			if(user !=null){
				loginId = user.getLoginId();
				userName =user.getUserName();
				password = user.getPassword();
				icon = user.getIcon();
				profile =user.getProfile();
			}
	%>


	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー更新結果画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">以下の内容で登録が完了しました。</strong>
		</div>
	</div>

	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<form action=./uer method="post">
				<table style="width: 400px" class="table">
					<tr><!-- ログインID  -->
						<th nowrap>
							<label for="loginId"> <span class="icon-smile pe-2x pe-va"> </span>&nbsp;ログインID</label>
						</th>
						<td class="text-left"><%=loginId %></td>
					</tr>

					<tr><!-- ユーザー名-->
						<th nowrap>
							<label for="userName"> <span class="icon-note pe-2x pe-va"></span>&nbsp;ユーザー名</label>
						</th>
						<td class="text-left"><%=userName%></td>
					</tr>

					<tr><!-- パスワード -->
						<th nowrap>
							<label for="pass"> <span class="icon-note pe-2x pe-va"></span>&nbsp;パスワード</label>
						</th>
						<td class="text-left"><%=password %></td>
					</tr>

					<tr><!-- アイコン -->
						<th nowrap><span class="icon-joy pe-2x pe-va"></span>&nbsp;アイコン</th>
						<td class="pe-2x pe-va">
							<label for="icon"><span class=<%= icon %>> </span></label>
						</td>
					</tr>

					<tr><!-- プロフィール -->
						<th nowrap><span class="icon-joy pe-2x pe-va"></span>&nbsp;プロフィール</th>
						<td class="text-left"><%=profile %></td>
					</tr>

					<tr><!-- 登録＆戻るボタン -->
						<td colspan="2" class="text-right"> <button class="btn" type="submit" value="戻る" >戻る</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
