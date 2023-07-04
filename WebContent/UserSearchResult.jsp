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
<script>
const checkbox3 = document.getElementsByName("checks")

function checkAllBox(trueOrFalse) {
  for(i = 0; i < checkbox3.length; i++) {
    checkbox3[i].checked = trueOrFalse
  }
}
</script>
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>ユーザー検索結果画面</h1>
		</div>
	</div>
	<%-- action 属性にサーブレットを指定 --%>
	<form action="./usr" method="get">
		<div class="padding-y-5">
			<div style="width: 60%" class="container padding-y-5">
				<%-- リストにある要素の数だけ繰り返し --%>
				<table class="table table-striped table-bordered">
					<tr>
						<th></th>
						<th>	<input class="btn btn-success btn-sm" type="button" onClick="checkAllBox(true)" value="全選択"></th>
						<th>	<input class="btn btn-success btn-sm" type="button" onClick="checkAllBox(false)" value="全解除"></th>
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
							<th><label class="fancy-checkbox"><input type="checkbox" name="checks"><span></span></label></th>
							<th>${searchUser.loginId}</th>
							<th>${searchUser.userName}</th>
							<th class="text-center"><span
								class="${searchUser.icon} pe-3x pe-va"></span></th>
							<th>${searchUser.profile}</th>
							<th><input class="btn btn-success btn-sm" type="button" name=""value="編集"></th>
						</tr>
					</c:forEach>
					<tr>
						<th></th>
						<th>	<input class="btn btn-success btn-sm" type="button" onClick="checkAllBox(true)" value="全選択"></th>
						<th>	<input class="btn btn-success btn-sm" type="button" onClick="checkAllBox(false)" value="全解除"></th>
						<th></th>
						<th></th>
						<th></th>
					<tr>
				</table>
					<input class="btn" type="submit" name="delete" value="削除">
					<input class="btn" type="submit" name="return" value="戻る">
			</div>
		</div>
	</form>
</body>
</html>