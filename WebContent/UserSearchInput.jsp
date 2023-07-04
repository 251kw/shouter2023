<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 検索画面 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/mycss.css">
</head>
<body>

	<div class="bg-main padding-y-5">
	<div class="padding-y-5 text-center">
		<strong>ユーザ検索入力画面</strong><br>
		<p style=display:inline>ユーザーを検索します。</p>
	</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定。押すボタンによって推移先が違うのでaction属性は「?」 --%>
			<form action="?" method="post">
				<table style="width: 400px" class="table">

					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left"><span class="icon-users pe-2x pe-va"></span>&nbsp;ログインID</td>
						<td class="text-left"><input class="form-control" type="text" maxlength="10"
							placeholder="10文字以内で入力" name="loginId" value=""></td>
					<tr>
						<%-- ユーザー名 入力欄の名前は userName --%>
						<td class="color-main text-left"><span class="icon-users pe-2x pe-va"></span>&nbsp;ユーザー名</td>
						<td class="text-left"><input class="form-control" type="text" maxlength="15" pattern="\S|\S.*?\S"
							placeholder="15文字以内で入力" name="userName" value=""></td>
					</tr>
					<tr>
						<%-- アイコン 選択欄の名前は icon --%>
						<td class="color-main text-left"><span class="icon-users pe-2x pe-va"></span>&nbsp;アイコン</td>
						<td class = "parent">		<!--親で囲った要素の横並びをcssで指定してる-->
							<div class = "child">		<!--子で囲った要素同士の間隔をcssで指定してる-->
								<label for="icon">
								<span class = "icon-star  pe-2x pe-va"></span>
								</label>
								<input type="checkbox" name="icon" id="icon" value="icon-star">
							</div>
							<div class = "child">
								<label for="icon">
								<span class = "icon-smile pe-2x pe-va"></span>
								</label>
								<input type="checkbox" name="icon" id="icon" value="icon-smile">
							</div>
						</td>
					</tr>
					<tr>
						<%-- プロフィール 入力欄の名前は profile --%>
						<td class="color-main text-left"><span class="icon-users pe-2x pe-va"></span>&nbsp;プロフィール</td>
						<td class="text-left"><input class="form-control" type="text" maxlength="30"
							placeholder="30文字以内で入力" name="profile" value=""></td>
					</tr>
					<tr>
					<%-- 登録ボタンでUserInfoInputsvt.javaにとぶ --%>
						<td colspan="2" class="text-right">
						<input class="btn" type="submit" value="検索" formaction="./usi"/>
						<input class="btn" type="submit" value="戻る" formaction="top.jsp" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>