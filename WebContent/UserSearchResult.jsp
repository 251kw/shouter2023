<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 検索結果画面 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/mycss.css">
</head>
<body>

	<div class="bg-main padding-y-5">
		<div class="padding-y-5 text-center">
			<strong>ユーザ検索結果画面</strong><br>
			<p style="display: inline">検索結果</p>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定。押すボタンによって推移先が違うのでaction属性は「?」 --%>
			<form action="?" method="post">

				<c:choose>

					<%-- リクエストスコープに alert があれば リクエストスコープの alert の値を出力 --%>
					<c:when
						test="${requestScope.alert != null && requestScope.alert != ''}">
						<div class = "color-error text-center">
						<c:out value="${requestScope.alert}" />
						</div>
					</c:when>

					<%-- 検索結果があれば 表を出す--%>
					<c:otherwise>
					<jsp:useBean id="user" scope="session" type="java.util.ArrayList<dto.UserDTO>" />
						<table border="1" style="width: 400px"  class="table" >
							<tr>
								<td nowrap><input type="button" onClick="checkAll()" value="全選択">
								<td nowrap><input type="button" onClick="uncheckAll()" value="全解除">
							</tr>
							<tr bgcolor="#1BBC9B">
								<th></th>
								<th nowrap>ログインID</th>
								<th nowrap>ユーザー名</th>
								<th nowrap>アイコン</th>
								<th nowrap>プロフィール</th>
								<th></th>
							</tr>
							<c:forEach var="result" items="${user}">
								<%-- int i = 1; %>
								<%-- dto.UserDTO u = (dto.UserDTO)request.getAttribute("user"); --%>
								<tr>
									<td><input type="checkbox" name="check" value="user"></td>
									<td nowrap>${result.loginId}<%-- <input type="hidden" name="user<%=i%>" value="<%=u.getLoginId()%>">--%></td>
									<td nowrap>${result.userName}</td>
									<td nowrap><span class = "${result.icon}"></span></td>
									<td nowrap>${result.profile}</td>
									<td><input class="btn" type="submit" value="編集" formaction="" /></td>
								</tr>
								<%-- i++; --%>
							</c:forEach>
							<tr>
								<td nowrap><input type="button" onClick="checkAll()" value="全選択">
								<td nowrap><input type="button" onClick="uncheckAll()" value="全解除">
							</tr>
						</table>
					</c:otherwise>

				</c:choose>

				<%-- 削除ボタンで?にとぶ --%>
				<br>
				<input class="btn" type="submit" value="削除" formaction="" />
				<input class="btn" type="submit" value="戻る" formaction="UserSearchInput.jsp" />

			</form>

		</div>
	</div>

	<script>
	//getElementsByName()メソッド:name属性がcheckのチェックボックスを取得
	//取得したチェックボックス群は、HTMLコレクション(配列みたいなもの)に格納されるので、配列と同じようにcheckbox1[i]の形で取り出せる
	//forで配列の長さ（取得したチェックボックスの数）分回して、全てのチェックボックスのchecked属性を順番に変更(trueだとチェック、falseだとチェックはずれる)
		//全選択
		const check = document.getElementsByName("check")
		function checkAll() {
			for (i = 0; i < check.length; i++) {
				check[i].checked = true
			}
		}

		//全解除
		const uncheck = document.getElementsByName("check")

		function uncheckAll() {
		  for(i = 0; i < uncheck.length; i++) {
			  uncheck[i].checked = false
		  }
		}
	</script>

</body>
</html>