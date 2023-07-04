<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter - ユーザー情報登録 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/style.css">
<script>
//全選択ボタンを取得する
const checkBtn = document.getElementById("check-btn");
//全解除ボタンを取得する
const uncheckBtn = document.getElementById("uncheck-btn");
//チェックボックスを取得する
const el = document.getElementsByClassName("checks");

//全てのチェックボックスにチェックを付ける
const checkAll = () => {
    for (let i = 0; i < el.length; i++) {
        el[i].checked = true;
    }
};

//全てのチェックボックスのチェックを外す
const uncheckAll = () => {
    for (let i = 0; i < el.length; i++) {
        el[i].checked = false;
    }
};

//全選択ボタンをクリックした時「checkAll」を実行
checkBtn.addEventListener("click", checkAll, false);
//全選択ボタンをクリックした時「uncheckAll」を実行
uncheckBtn.addEventListener("click", uncheckAll, false);
</script>
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>ユーザー検索結果画面</h1>
		</div>
	</div>
	<%-- action 属性にサーブレットを指定 --%>
	<form action="./usi" method="get">
		<div class="padding-y-5">
			<div style="width: 40%" class="container padding-y-5">
				<%-- リストにある要素の数だけ繰り返し --%>
				<table class="table table-striped table-bordered">
					<tr>
						<th></th>
							<th><button id="check-btn" type="button">全選択</button></th>
           					<th><button id="uncheck-btn" type="button">全解除</button></th>
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<th class="color-main text-left"></th>
						<th class="color-main text-left">ログインID</th>
						<th class="color-main text-left">ユーザー名</th>
						<th class="color-main text-left">アイコン</th>
						<th class="color-main text-left">プロフィール</th>
						<th class="color-main text-left"></th>
					</tr>
					<%-- リストにある要素の数だけ繰り返し --%>
					<c:forEach var="searchUser" items="${searchUser}">
						<tr>
							<th><input class="checks" type="checkbox"></th>
							<th>${searchUser.loginId}</th>
							<th>${searchUser.userName}</th>
							<th class="text-center"><span
								class="${searchUser.icon} pe-3x pe-va"></span></th>
							<th>${searchUser.profile}</th>
							<th><input type="button" name=""value="編集"></th>
						</tr>
					</c:forEach>
					<tr>
						<th></th>
						<th><button id="check-btn" type="button">全選択</button></th>
						<th><button id="uncheck-btn" type="button">全解除</button></th>

					<tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>