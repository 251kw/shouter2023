<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="dto.UserDTO"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.List"%>
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
	<form action="?" method="post">
	<div class="padding-y-5 text-center">
		<div style="width: 100%" class="container padding-y-5 text-center">
	<c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">
					<table class="table table-striped table-bordered ">

						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error"><h3><font color="red"><c:out
									value="${requestScope.alert}" /></font></h3></td>
						</tr>
						</table>
										<button type="button" class="btn"
					onclick="location.href='./UserSearchInput.jsp'">戻る</button>
					</c:if>

					<c:if test="${requestScope.alert == null}">

			<%-- action 属性にサーブレットを指定 --%>
			<%-- action 属性は送信データの送信先の設定 --%>
			<jsp:useBean id="userr" scope="session"
				type="java.util.ArrayList<dto.UserDTO>" />

			<a id="btn1" onclick="checked()" class="btn btn-empty">全選択</a> <a
				id="btn２" onclick="unChecked()" class="btn btn-empty">全解除</a>

			<%-- fromの先を変更する必要あり --%>

				<%-- セッションスコープにある ArrayList 型のオブジェクトを参照 --%>
				<div style="width: 100%" class="container padding-y-5">
					<%-- リストにある要素の数だけ繰り返し --%>

					<table class="table table-striped table-bordered ">
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
<%

		String[] DELETEreID = request.getParameterValues("delete-ID");
if(DELETEreID!=null){
List<String> deletereid = Arrays.asList(DELETEreID);
pageContext.setAttribute("deletereid",deletereid);
}
				 		  //listに Arrays.asList(DELETEreID);を使ってDELETEreIDをListにする

%>

							<tr>
								<td><label class="fancy-checkbox"><input
										type="checkbox" name="delete-ID-checkbox" class="cla"
										value="${User.loginId}" ${deletereid.contains(User.loginId)?"checked":""}><span></span></label></td>
								<td class="result">${User.loginId}</td>
								<td class="result">${User.userName}</td>
								<td class="result"><span class="${User.icon} pe-2x pe-va"></span></td>
								<td class="result">${User.profile}</td>
								<td><button type="submit" name="edit-ID" class="btn"
										formaction="./usr" value="${User.loginId}">編集</button></td>
							</tr>
						</c:forEach>
					</table>
				</div>

				<p>
									<a id="btn1" onclick="checked()" class="btn btn-empty">全選択</a><a
						id="btn２" onclick="unChecked()" class="btn btn-empty">全解除</a>
				</p>
				<script>
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
					<c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert}" /></td>
						</tr>
					</c:if>
				<br> <br> <input class="btn" name="delete-ID"
					type="submit" value="消去" formaction="./udc" />
				<%-- submitデータの送信() 送信先はaction="./uis" method="post"--%>

				<button type="button" class="btn"
					onclick="location.href='./UserSearchInput.jsp'">戻る</button>
				<%-- クリック時にindex.jspに戻る --%>
				</c:if>
				</div>
	</div>


	</form>
</body>
</html>