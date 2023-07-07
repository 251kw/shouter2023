<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>検索結果</title>
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
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main"></strong>
			<p>
				<%-- エラー文の挿入 --%>
				<%-- リクエストスコープに alert があれば --%>
				<c:if
					test="${requestScope.alert != null && requestScope.alert != ''}">
					<%-- リクエストスコープの alert の値を出力 --%>
					<p class="color-error text-center">
						<c:out value="${requestScope.alert}" />
					</p>
				</c:if>
			</p>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 60%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="UserSearchInput.jsp" method="post">
				<table style="width: 700px" class="table">
					<tr>
						<td class="text-center"><input class="btn" type="submit"
							value="戻る" /></td>
					</tr>

				</table>
			</form>
		</div>
	</div>
</body>
</html>