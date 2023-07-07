<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter-検索-</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>

<style>
.wrapper {
	display: flex;
}

input {
	white-space: pre;
}

input:hover {
	background: rgba(80, 200, 200, 0.2);
}

textarea:hover {
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
	function sendProfile() {
		console.log(document.form1.profile.value);
	}
</script>
<%
	//文字化け対策
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
%>

<c:if
	test="${requestScope.alert.equals ('ログインIDを英数字8桁以下入力してください')||
							requestScope.alert.equals ('ログインIDが既に存在する')}">
	<style>
input[name="loginId"]:focus {
	background: pink;
}
div
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
			<h1>ユーザー検索入力画面</h1>
		</div>
	</div>

	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./usi" name="form1" method="post">
				<table style="width: 450px" class="table">
					<tr>
						<%-- ログインID入力欄の名前はloginId --%>
						<td style="width: 200px" class="color-man text-left"><span
							class="icon-id pe-2x"></span>&nbsp; <span>ログインID</span></td>
						<td class="text-left">
							<div
								<c:if test = "${requestScope.alert.equals ('ログインIDを英数字8桁以下入力してください')||
							requestScope.alert.equals ('ログインIDが既に存在する')}">
							class="error"
							</c:if>>
								<input class="form-control" type="text" name="loginId"
									value="${param.loginId}" size="20" placeholder="英数字8桁以下入力"
									<c:if test = "${requestScope.alert.equals ('ログインIDを英数字8桁以下入力してください')||
							requestScope.alert.equals ('ログインIDが既に存在する')}">
							autofocus="autofocus"
							</c:if> />
							</div>
						</td>

					</tr>

					<tr>
						<%-- ユーザー名入力欄の名前はuserName --%>
						<td style="width: 200px" class="color-man text-left"><span
							class="icon-users pe-2x"></span>&nbsp; <span>ユーザー名</span></td>
						<td class="text-left">
							<div
								<c:if test = "${requestScope.alert.equals ('ユーザー名を8文字以下入力してください') }">
							class = "error"
							</c:if>>
								<input class="form-control" type="text" name="userName"
									value="${param.userName}" size="20" placeholder="8文字以下入力"
									<c:if test = "${requestScope.alert.equals ('ユーザー名を8文字以下入力してください') }">
							autofocus="autofocus"
							</c:if> />
							</div>
						</td>
					</tr>
					<tr>


						<%-- アイコンチェック --%>

						<td style="width: 200px" class="color_main text_left"><span
							class="icon-smile pe-2x"></span>&nbsp; <span>アイコン</span></td>
						<td>

							<div class="wrapper">
								<span
									class="icon-user pe-2x"></span>&nbsp;
								<label class="fancy-checkbox"><input type="checkbox"
									name="icon1"
									<c:if test="${param.icon=='icon-user' }">checked="checked"</c:if>
									value="icon-user" /><span></span> </label>
&nbsp;&nbsp;&nbsp;&nbsp;
									<span
									class="icon-user-female pe-2x"></span>&nbsp;&nbsp;
									<label
									class="fancy-checkbox"><input type="checkbox"
									name="icon2"
									<c:if test="${param.icon=='icon-user-female' }">checked="checked"</c:if>
									value="icon-user-female" /><span></span> </label>
&nbsp;&nbsp;&nbsp;
									<span
									class="icon-bell pe-2x"></span>
									 <label
									class="fancy-checkbox"><input type="checkbox"
									name="icon3"
									<c:if test="${param.icon=='icon-bell' }">checked="checked"</c:if>
									value="icon-bell" /><span></span> </label>
							</div>
						</td>
					<tr>
						<%--プロファイル入力 --%>
						<td style="width: 200px" class="color_main text_left"><span
							class="icon-note	 pe-2x"></span> <span>プロフィール</span></td>
						<td colspan="2"><textarea name="profile" rows="5"
								class="form-control" style="resize: none;"
								onchange="sendProfile()" placeholder="自己紹介(200文字以下)">${param.profile}</textarea>

						</td>

					</tr>
					<%-- リクエストスコープにalertがあれば --%>
					<c:if
						test="${requestScope.alert !=null && requestScope.alert != '' }">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert}" /></td>
						</tr>
					</c:if>
					<tr>
						<%-- 登録ボタン --%>
						<td colspan="1" class="text-left"><input class="btn"
							type="submit" value="検索" onClick="sendProfile" /></td>
						<%-- 戻るボタン --%>
						<td colspan="1" class="text-right"><input class="btn"
							type="button" value="戻る"
							onClick="window.location.href='./top.jsp'" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>

