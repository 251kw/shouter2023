<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="dto.UserDTO"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザー検索結果画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/UserSearchResult.css">
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
		<div style="width: 60%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<%-- action 属性は送信データの送信先の設定 --%>


			<%-- fromの先を変更する必要あり --%>
			<form action="./uis" method="post">

				<button type="button" class="btn1"
					onclick="location.href='./UserSearchInput.jsp'">全選択</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn1"
					onclick="location.href='./UserSearchInput.jsp'">全解除</button>
				<%-- セッションスコープにある ArrayList 型のオブジェクトを参照 --%>



				<div style="width: 150%" class="container padding-y-5">
					<%-- リストにある要素の数だけ繰り返し --%>

					<table class="table table-striped table-bordered">
						<tr>
							<th class="result"></th>
							<%-- ログインID 入力欄の名前は loginId --%>
							<th class="result"><span class="icon-speaker pe-2x pe-va"></span>&nbsp;&nbsp;&nbsp;&nbsp;ログインID</th>

							<%-- ログインID 入力欄の名前は username --%>
							<th class="result"><span class="icon-speaker pe-2x pe-va"></span>&nbsp;&nbsp;&nbsp;&nbsp;ユーザー名</th>

							<%-- パスワード入力欄の名前は Icon --%>
							<th class="result"><span class="icon-speaker pe-2x pe-va"></span>&nbsp;&nbsp;&nbsp;&nbsp;アイコン</th>

							<%-- ログインID 入力欄の名前は loginId --%>
							<th class="result"><span class="icon-speaker pe-2x pe-va"></span>&nbsp;&nbsp;&nbsp;&nbsp;プロフィール</th>
							<th class="result"></th>
						</tr>
						<c:forEach var="User" items="${user}">
							<tr>
								<td class="result"><input type="checkbox" name="checkbox"
									value="choice"></td>
								<td class="result">${User.loginId}</td>
								<td class="result">${User.userName}</td>
								<td class="result"><span class="${User.icon} pe-2x pe-va"></span></td>
								<td class="result">${User.profile}</td>
								<td class="result"><button type="button" class="btn1"
										onclick="location.href='./UserSearchInput.jsp'">編集</button></td>
							</tr>
						</c:forEach>
					</table>
					<button type="button" class="btn1"
						onclick="location.href='./UserSearchInput.jsp'">全選択</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn1"
						onclick="location.href='./UserSearchInput.jsp'">全解除</button>
						<br> <br>
					<input class="btn" type="submit" value="消去" />
					<%-- submitデータの送信() 送信先はaction="./uis" method="post"--%>

					<button type="button" class="btn"
						onclick="location.href='./UserSearchInput.jsp'">戻る</button>
					<%-- クリック時にindex.jspに戻る --%>
				</div>

			</form>
		</div>
	</div>
</body>
</html>