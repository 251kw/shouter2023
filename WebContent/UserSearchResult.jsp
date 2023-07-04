<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザー登録入力画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/UserSearch.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー検索結果画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./uii" method="post">
			<table>
			<tr>
				<td class="sample-td">全選択</td>
				<td class="sample-td">全解除</td>
				</tr>
				</table>
				<table class="sample-table" style="width: 400px">

					<tr>
						<th class="sample-th"></th>
						<th class="sample-th">ログインID</th>
						<th class="sample-th">ユーザー名</th>
						<th class="sample-th">アイコン</th>
						<th class="sample-th">プロフィール</th>
						<th class="sample-th"></th>
					</tr>
					<tr>
						<td class="sample-td"><input type="checkbox"
							name="icon" id="icon" value=""></td>
						<td class="sample-td">${User.loginId}</td>
						<td class="sample-td">${User.userName}</td>
						<td class="sample-td">${User.icon}</td>
						<td class="sample-td">${User.profile}</td>
						<td class="sample-td">編集</td>
					</tr>
					<tr>
						<td class="sample-td"><input type="checkbox"
							name="icon" id="icon" value=""></td>
						<td class="sample-td"></td>
						<td class="sample-td"></td>
						<td class="sample-td"></td>
						<td class="sample-td"></td>
						<td class="sample-td">編集</td>
					</tr>
					<tr>
						<td class="sample-td"></td>
						<td class="sample-td"></td>
						<td class="sample-td"></td>
						<td class="sample-td"></td>
						<td class="sample-td"></td>
						<td class="sample-td">編集</td>
					</tr>

				</table>
				<table>
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="削除" /><a class="btn"
							href="./UserSearchInput.jsp">戻る</a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>