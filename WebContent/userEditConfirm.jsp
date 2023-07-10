<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dto.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー情報更新確認</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー更新確認画面<br>更新します。よろしいでしょうか？
			</h1>
		</div>
	</div>
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./uec" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<%-- ユーザー名 入力欄の名前は userName --%>
						<th class="color-main text-left"><span class="icon-smile pe-2x pe-va"></span>ユーザー名</th>
						<td><%=request.getAttribute("e_UserName") %><!-- 編集後のユーザー名を受け取り表示 --></td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<th class="color-main text-left"><span class="icon-smile pe-2x pe-va"></span>パスワード</th>
						<td><%=request.getAttribute("e_Password") %><!-- 編集後のパスワードを受け取り表示 --></td>
					</tr>
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<th class="color-main text-left"><span class="icon-smile pe-2x pe-va"></span>アイコン</th>
						<td class="text-left">
							 <span class="<%=request.getAttribute("e_Icon")%> pe-2x pe-va"><!-- 編集後のアイコンを取得し表示 --></span>
						</td>
					</tr>
					<tr>
						<th class="color-main text-left"><span class="icon-smile pe-2x pe-va"></span>プロフィール</th>
						<td><%=request.getAttribute("e_Profile") %><!-- 編集後のプロフィールを取得し表示 --></td>
				   	</tr>
					<tr>
						<td colspan="2" class="text-center"><input class="btn"
							type="submit" name="OK" value="OK" /></td>
						<td colspan="2" class="text-center"><input class="btn"
							type="submit" name="cancel" value="キャンセル" /></td>
					</tr>
				</table>
					<!-- 検索条件を取得し、UserEditConfirm.jspに渡すための処理 -->
					<input type="hidden" name="loginId" value="<%=request.getAttribute("loginId")%>" >
					<input type="hidden" name="userName" value="<%=request.getAttribute("userName")%>" >
					<input type="hidden" name="icon-user-female" value="<%=request.getAttribute("icon-user-female")%>">
					<input type="hidden" name="icon-user" value="<%=request.getAttribute("icon-user")%>" >
					<input type="hidden" name="icon-bell" value="<%=request.getAttribute("icon-bell")%>" >
					<input type="hidden" name="icon-smile" value="<%=request.getAttribute("icon-smile")%>" >
					<input type="hidden" name="profile" value="<%=request.getAttribute("profile")%>" >
					<!-- 編集内容を取得し、UserEditConfirm.jspに渡すための処理 -->
					<input type="hidden" name="e_UserName" value="<%=request.getAttribute("e_UserName")%>" >
					<input type="hidden" name="e_Password" value="<%=request.getAttribute("e_Password")%>" >
					<input type="hidden" name="e_Icon" value="<%=request.getAttribute("e_Icon")%>" >
					<input type="hidden" name="e_Profile" value="<%=request.getAttribute("e_Profile")%>" >
					<!-- 編集対象が検索されたデータの何番目かを特定する情報をUserEditConfirm.jspに渡すための処理 -->
					<input type="hidden" name="edit" value="<%=request.getAttribute("edit") %>">
			</form>
</body>
</html>