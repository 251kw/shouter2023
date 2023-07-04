<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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



	<jsp:useBean id="Users" scope="session" type="java.util.ArrayList<dto.UserDTO>" />
	<div class="padding-y-5">
		<div style="width: 50%" class="container padding-y-5">
			<%-- リストにある要素の数だけ繰り返し --%>
			<c:forEach var="User" items="${Users}">
				<table class="table table-striped table-bordered">
					<tr>
						<td rowspan="2" class="text-center">
						<span class="${User.loginId} pe-3x pe-va"></span></td>
						<td>${User.userName}</td>
					</tr>
					<tr>
						<td>	${User.icon}</td>
					</tr>
										<tr>
						<td>	${User.profile}</td>
					</tr>
					</table>
			</c:forEach>
		</div>

			</form>
		</div>
	</div>
</body>
</html>