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
	<jsp:useBean id="userr" scope="session" type="java.util.ArrayList<dto.UserDTO>" />

  <a id="btn1" onclick="checked()" class="btn btn-empty">全選択</a>
<a id="btn２" onclick="unChecked()" class="btn btn-empty">全解除</a>

			<%-- fromの先を変更する必要あり --%>
			<form action="?" method="post">
				<%-- セッションスコープにある ArrayList 型のオブジェクトを参照 --%>
				<div style="width: 100%" class="container padding-y-5">
					<%-- リストにある要素の数だけ繰り返し --%>

					<table class="table table-striped table-bordered table-hover">
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
						<c:forEach var="User" items="${userr}">
							<tr>
							<td><label class="fancy-checkbox"><input type="checkbox" name="checkbox" class="cla"  value="choice"><span></span></label></td>
								<td class="result">${User.loginId}</td>
								<td class="result">${User.userName}</td>
								<td class="result"><span class="${User.icon} pe-2x pe-va"></span></td>
								<td class="result">${User.profile}</td>
								<td><a class="btn btn-empty btn-dark">編集</a></td>
							</tr>
						</c:forEach>
					</table>

				</div>
			</form>
								<p>
  <a id="btn1" onclick="checked()" class="btn btn-empty">全選択</a>
<a id="btn２" onclick="unChecked()" class="btn btn-empty">全解除</a>
</p>
<script >
	function unChecked() {
	    let boxes = document.querySelectorAll(".cla");
	  for (let i = 0; i < boxes.length; i++) {
	    boxes[i].checked = false;
	  }
	}

	function checked() {
	  let boxes = document.querySelectorAll(".cla");
	  for (let i = 0; i < boxes.length; i++) {
	    boxes[i].checked = true;
	  }
	}
	</script>
						<br> <br>
					<input class="btn" type="submit" value="消去" />
					<%-- submitデータの送信() 送信先はaction="./uis" method="post"--%>

					<button type="button" class="btn"
						onclick="location.href='./UserSearchInput.jsp'">戻る</button>
					<%-- クリック時にindex.jspに戻る --%>
		</div>
	</div>
</body>
</html>