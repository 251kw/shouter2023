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
<%
	String CHECK = "";
	//文字化け対策
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");

	if (request.getParameter("true") != null) {
		CHECK = request.getParameter("true");
	}
%>
<body>

	<div class="bg-main padding-y-5">
		<div class="padding-y-5 text-center">
			<strong>ユーザ検索結果画面</strong><br>
			<p style="display: inline">検索結果</p>
		</div>
	</div>
	<%-- 削除確認画面からのリクエストスコープに alert があれば リクエストスコープの alert の値を出力--%>
	<div class = "color-error text-center">
		<c:if test="${requestScope.delalert != null && requestScope.delalert != ''}">
			<c:out value="${requestScope.delalert}" /><br>
		</c:if>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定。押すボタンによって推移先が違うのでaction属性は「?」 --%>
			<form action="?" method="post">

				<c:choose>

					<%-- リクエストスコープに alert があれば リクエストスコープの alert の値を出力 --%>
					<c:when
						test="${requestScope.alert != null && requestScope.alert != ''}">
						<div class="color-error text-center">
							<c:out value="${requestScope.alert}" />
						</div>
					</c:when>

					<%-- 検索結果があれば 表を出す--%>
					<c:otherwise>
						<jsp:useBean id="user2" scope="session" type="java.util.ArrayList<dto.UserDTO>" />
						<div class="padding-y-5 text-left">
							<button type="submit" name="all" value="allcheck" formaction="./usr">全選択</button>
							<button type="submit" name="all" value="allout" formaction="./usr">全解除</button>
						</div>
						<table border="1" style="width: 400px"
							class="table table-striped table-bordered table-hover">
							<tr bgcolor="#1BBC9B">
								<th></th>
								<th style="text-align: center" nowrap>ログインID</th>
								<th style="text-align: center" nowrap>ユーザー名</th>
								<th style="text-align: center" nowrap>アイコン</th>
								<th style="text-align: center" nowrap>プロフィール</th>
								<th></th>
							</tr>
							<c:forEach var="result" items="${user2}">
								<tr>
									<td><label class="fancy-checkbox">
									<input type="checkbox" name="check" id="check" value="${result.loginId}">
									<span></span></label></td>
									<td nowrap>${result.loginId}</td>
									<td nowrap>${result.userName}</td>
									<td nowrap><span class="${result.icon}  pe-2x pe-va"></span></td>
									<td nowrap>${result.profile}</td>
									<td><button type="submit" name="edit" value="${result.loginId}" formaction="./usr">編集</button></td>
								</tr>
							</c:forEach>
						</table>

						<div class="text-left">
							<input type="button" onClick="checkAll()" value="全選択">
							<input type="button" onClick="uncheckAll()" value="全解除">
						</div>
					</c:otherwise>
				</c:choose>
				<%-- 削除ボタンでudcにとぶ --%>
				<br> <input class="btn" type="submit" value="削除" name="delete" formaction="./udc" />
				<%-- 戻る時は送る値がないのでハイパーリンクでOK --%>
				<a href="UserSearchInput.jsp" class="btn">戻る</a>

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
			for (i = 0; i < uncheck.length; i++) {
				uncheck[i].checked = false
			}
		}
	</script>

</body>
</html>