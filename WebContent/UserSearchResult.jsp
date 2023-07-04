<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー検索結果画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/stylesheet.css">
</head>

<body>
<c:if test="${requestScope.alertEmpResult != null && requestScope.alertEmpResult != ''}">
						<c:out value="${requestScope.alertPassword}" /><br>
</c:if>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー検索結果画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
<jsp:useBean id="user" scope="session" type="java.util.ArrayList<dto.UserDTO>" />
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<form action=? method="post">
				 	<table style="width: 400px" class="table">
				 			<tr>
								<th nowrap><!-- ログインID -->
									<label for="whiteSpace"></label>
								</th>
								<th nowrap><!-- ログインID -->
									<div   class="text-right"> <button class="btn" type="submit" value="全選択"  formaction="">全選択</button></div>
								</th>
								<th nowrap><!-- ユーザー名 -->
									<div class="text-right"> <button class="btn" type="submit" value="解除"  formaction="UserSearchInput.jsp">解除</button></div>
								</th>
							</tr>
							<tr>
								<th nowrap><!-- ログインID -->
									<label for="whiteSpace"></label>
								</th>
								<th nowrap><!-- ログインID -->
									<label for="loginId">&nbsp;ログインID</label>
								</th>
								<th nowrap><!-- ユーザー名 -->
									<label for="userName">&nbsp;ユーザー名</label>
								</th>
								<th nowrap><label for="icon">&nbsp;アイコン</label>
								</th>
								<th nowrap><label for="profile">&nbsp;プロフィール</label>
								</th>
							</tr>
						<c:forEach var="user" items="${user}">
							<tr>
								<td><input type="checkbox" name="DeleteName[]" value="1"></td>
								<td class="text-left"><input class="form-control" type="hidden" name="loginId" value="" size="20" autofocus >
								${user.loginId}
								</td>
								<td class="text-left"><input class="form-control" type="hidden" name="userName" value="" size="20" />
								${user.userName}
								</td>
								<td class="text-left"><label for="icon"><span class=${user.icon} > </span></label><input type="hidden" name="icon" id="icon" value="${user.icon}" checked>
								</td>
								<td class="text-left"><input class="form-control" type="hidden" name="profile" value="" size="20" />
								${user.profile}
								</td>


								<td colspan="2" class="text-right"><button class="btn" type="submit" value="編集" formaction="">編集</button></td>
							</tr>
						</c:forEach>
						<tr>
							<th nowrap><!-- ログインID -->
								<label for="whiteSpace"></label>
							</th>
							<th nowrap><!-- ログインID -->
								<div   class="text-right"> <button class="btn" type="submit" value="全選択"  formaction="">全選択</button></div>
							</th>
							<th nowrap><!-- ユーザー名 -->
								<div class="text-right"> <button class="btn" type="submit" value="解除"  formaction="UserSearchInput.jsp">解除</button></div>
							</th>
						</tr>
					</table>

				<div   class="text-right"> <button class="btn" type="submit" value="削除"  formaction="">削除</button></div>
				<div class="text-right"> <button class="btn" type="submit" value="戻る"  formaction="UserSearchInput.jsp">戻る</button></div>
			</form>

		</div>
	</div>
</body>
</html>