<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Sign up - 登録しました -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<script>
	//テキストエリアの値を取得
	function sendProfile() {
		console.log(document.form1.profile.value);
	}
</script>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー登録結果画面
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">以下の内容で登録が完了しました。</strong>
		</div>
	</div>
	<%
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	%>
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./signup" name="form1" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<%-- ログインID入力欄の名前はloginId --%>
						<td style="width:200px" class="color-man text-left">
						<span class="icon-id pe-2x"></span>&nbsp;
						<span class="required">ログインID</span></td>
						<td class="text-left"><label> ${user.loginId}
						</label></td>
					</tr>
					<tr>
						<%-- ユーザー名入力欄の名前はuserName --%>
						<td style="width:200px" class="color-man text-left">
						<span class="icon-users pe-2x"></span>&nbsp;
						<span class="required">ユーザー名</span></td>
						<td class="text-left"><label> ${user.userName}
						</label></td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前はpassword --%>
						<td style="width:200px" class="color_main text_left">
						<span class="icon-lock	 pe-2x"></span>&nbsp;
						<span class="required">パスワード</span></td>
						<td class="text-left"><label> ${user.password}
						</label></td>
					</tr>
					<tr>
						<td style="width:200px" class="color_main text_left">
						<span class="icon-male pe-2x"></span><span class="icon-female pe-2x"></span>&nbsp;
						<span class="required">性別</span></td>
						<td  class="text-left"><span><c:if
									test="${param.icon=='icon-user'}">男</c:if> <c:if
									test="${param.icon=='icon-user-female'}">女</c:if> </span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
							class="${user.icon} pe-3x pe-va"></span>
					</tr>

					<tr>
						<%--プロファイル入力 --%>
						<td style="width:200px" class="color_main text_left">
						<span class="icon-note	 pe-2x"></span>
						<span>プロファイル</span></td>
						<td colspan="2"><textarea name="profile" rows="5"
								class="form-control" style="resize: none;" readonly
								onchange="sendProfile()">${user.profile}</textarea></td>
					</tr>
					<tr>
					<c:if
						test="${requestScope.alert !=null && requestScope.alert != '' }">
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-main text-left"><strong><c:out
									value="${requestScope.alert}" /></strong></td>
					</c:if>
					</tr>
					<tr>
						<%-- 戻るボタン --%>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="戻る"
							formaction="index.jsp" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
