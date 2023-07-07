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
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー登録入力画面&nbsp;<span class="icon-speaker"></span>
			</h1>
			<h6>ユーザー登録します。内容を入力してください。
			</h6>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 60%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<%-- action 属性は送信データの送信先の設定 --%>
			<form action="./uis" method="post">
				<table style="width: 150%" class="table">
					<%
						//初期値の設定
						String hloginId = ""; //ログインIDのhiddenで帰ってくる値を先に宣言し初期化
						String hpassword = ""; //パスワードのhiddenで帰ってくる値を先に宣言し初期化
						String hUserName = ""; //ユーザー名IDのhiddenで帰ってくる値を先に宣言し初期化
						String hIcon = ""; //アイコンのhiddenで帰ってくる値を先に宣言し初期化
						String checked_smile = ""; //アイコンにチェックを入れるための変数
						String checked_speaker = ""; //アイコンにチェックを入れるための変数
						String hProf = ""; //プロフィールのhiddenで帰ってくる値を先に宣言し初期化
					%>

					<%
						//ログインIDに何か入力されている場合hiddenで帰ってきた値を初期値に代入
						if (request.getParameter("hidden-loginID") != null) {
							hloginId = request.getParameter("hidden-loginID");
							hpassword = request.getParameter("hidden-pass");
							hUserName = request.getParameter("hidden-username");
							hIcon = request.getParameter("hidden-geticon");
							hProf = request.getParameter("hidden-profile");
						}
					%>

					<%
						//アイコンのチェック先の指定
						if (hIcon.equals("icon-smile")) {
							checked_smile = "checked";

						} else  {
							checked_speaker = "checked";
						}
					%>

					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left"><span class="icon-speaker pe-2x pe-va"></span>&nbsp;&nbsp;&nbsp;&nbsp;ログインID</td>
						<td class="text-left"><input class="form-control" type="text" maxlength="10"
							name="hidden-loginID" value="<%=hloginId%>" size="20" autofocus /></td>
						<%-- <%=hloginId%>によってhiddenの値を代入 --%>
					</tr>
					<tr>
						<%-- ログインID 入力欄の名前は username --%>
						<td class="color-main text-left"><span class="icon-speaker pe-2x pe-va"></span>&nbsp;&nbsp;&nbsp;&nbsp;ユーザー名</td>
						<td class="text-left"><input class="form-control" type="text" maxlength="10"
							name="hidden-username" value="<%=hUserName%>" size="20" autofocus /></td>
					</tr>

					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<td class="color-main text-left"><span class="icon-speaker pe-2x pe-va"></span>&nbsp;&nbsp;&nbsp;&nbsp;パスワード</td>
						<td class="text-left"><input class="form-control"
							type="password" maxlength="10" name="hidden-pass" value="<%=hpassword%>" size="20" /></td>
					</tr>

					<tr>
						<%-- パスワード入力欄の名前は Icon --%>
						<td class="color-main text-left"><span class="icon-speaker pe-2x pe-va"></span>&nbsp;&nbsp;&nbsp;&nbsp;アイコン</td>
						<td><label class="icon-smile pe-2x pe-va"><input
								type="radio" name="hidden-geticon" value="icon-smile"<%=checked_smile%>></label>
								&nbsp;&nbsp;&nbsp;&nbsp;
								 <label
							class="icon-speaker pe-2x pe-va"><input
								<%-- <%=checked_smile%>にchwckedが代入してありアイコンにチェックを入れる --%>
								type="radio"
								name="hidden-geticon" value="icon-speaker"
								<%=checked_speaker%>></label></td>
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left"><span class="icon-speaker pe-2x pe-va"></span>&nbsp;&nbsp;&nbsp;&nbsp;プロフィール</td>
						<td class="text-left"><input class="form-control" type="text" maxlength="100" name="hidden-profile" value="<%=hProf%>" cols="20" rows="10"  autofocus /></td>
					</tr>

					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="登録" /></td>
						<%-- submitデータの送信() 送信先はaction="./uis" method="post"--%>
						<td>
							<button type="button" class="btn"
								onclick="location.href='./index.jsp'">戻る</button>
							<%-- クリック時にindex.jspに戻る --%>
						</td>
					</tr>

					<%-- リクエストスコープ(userInput.Servlet:46)に alert があれば --%>
					<c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert}" /></td>
						</tr>
					</c:if>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
