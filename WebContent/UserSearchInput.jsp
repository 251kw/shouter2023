<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
				ユーザー検索入力画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 60%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<%-- action 属性は送信データの送信先の設定 --%>
			<form action="./uss" method="post">

				<table style="width: 150%" class="table">

									<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left"><span class="icon-speaker pe-2x pe-va"></span>&nbsp;&nbsp;&nbsp;&nbsp;ログインID</td>
						<td class="text-left"><input class="form-control" type="text" maxlength="10"
							name="LoginID" value="" size="20" autofocus /></td>
						<%-- <%=hloginId%>によってhiddenの値を代入 --%>
					</tr>
					<tr>
						<%-- ログインID 入力欄の名前は username --%>
						<td class="color-main text-left"><span class="icon-speaker pe-2x pe-va"></span>&nbsp;&nbsp;&nbsp;&nbsp;ユーザー名</td>
						<td class="text-left"><input class="form-control" type="text" maxlength="10"
							name="Username" value="" size="20"  /></td>
					</tr>

					<tr>
						<%-- パスワード入力欄の名前は Icon --%>
						<td class="color-main text-left"><span class="icon-speaker pe-2x pe-va"></span>&nbsp;&nbsp;&nbsp;&nbsp;アイコン</td>
						<td><label class="icon-smile pe-2x pe-va">  <input type="checkbox" name="Icon" value="smile"></label>
								&nbsp;&nbsp;&nbsp;&nbsp;
								 <label class="icon-speaker pe-2x pe-va">  <input type="checkbox" name="Iconn" value="speaker"></label></td>
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left"><span class="icon-speaker pe-2x pe-va"></span>&nbsp;&nbsp;&nbsp;&nbsp;プロフィール</td>
						<td class="text-left"><input class="form-control" type="text" maxlength="100" name="Prof" value="" /></td>
					</tr>

					<c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class ="color-error text-left"><c:out
									value="${requestScope.alert}" /></td>
						</tr>
					</c:if>

					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="検索" /></td>
						<%-- submitデータの送信() 送信先はaction="./uis" method="post"--%>
						<td>
							 <a class="btn" type="button" href="./top.jsp">戻る</a>
							<%-- クリック時にindex.jspに戻る --%>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>