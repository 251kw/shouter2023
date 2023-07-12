<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="dto.UserDTO,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter - ユーザー情報削除 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<!-- <script>
const checkbox3 = document.getElementsByName("checks")

function checkAllBox(trueOrFalse) {
  for(i = 0; i < checkbox3.length; i++) {
    checkbox3[i].checked = trueOrFalse
  }
}
</script> -->
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>ユーザー削除確認画面</h1>
		</div>
	</div>
	<%-- action 属性にサーブレットを指定 --%>
	<form action="./udc" method="post">
		<!-- 全選択ボタンまたは全解除ボタンが押された場合の値の受け渡しのためにhiddenを使用 -->
		<input type="hidden" name="loginId"
			value="<%=request.getAttribute("loginId")%>"> <input
			type="hidden" name="userName"
			value="<%=request.getAttribute("userName")%>"> <input
			type="hidden" name="icon-user-female"
			value=<%=request.getAttribute("icon-user-female")%>> <input
			type="hidden" name="icon-user"
			value=<%=request.getAttribute("icon-user")%>> <input
			type="hidden" name="icon-bell"
			value=<%=request.getAttribute("icon-bell")%>> <input
			type="hidden" name="icon-smile"
			value=<%=request.getAttribute("icon-smile")%>> <input
			type="hidden" name="profile"
			value="<%=request.getAttribute("profile")%>">
		<div class="padding-y-5">
			<div style="width: 60%" class="container padding-y-5">
				<table class="table table-striped table-bordered">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<th class="color-main text-left">ログインID</th>
						<th class="color-main text-left">ユーザー名</th>
						<th class="color-main text-left">アイコン</th>
						<th class="color-main text-left">プロフィール</th>
					</tr>
					<%-- リストにある要素の数だけ繰り返し --%>
					<c:forEach var="deleteUser" items="${deleteUser}">
						<tr>
							<th>${deleteUser.loginId}</th>
							<th>${deleteUser.userName}</th>
							<th class="text-center"><span
								class="${deleteUser.icon} pe-3x pe-va"></span></th>
							<th>${deleteUser.profile}</th>
						</tr>
						<input type="hidden" name="deleteUser" value="${deleteUser.loginId}">
					</c:forEach>
				</table>
				<div class="text-center">
					<input class="btn" type="submit" name="delete" value="OK">
					<input class="btn" type="submit" name="return" value="戻る">
				</div>
			</div>
		</div>
	</form>
</body>
</html>