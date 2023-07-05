<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ユーザー検索入力画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<link rel="stylesheet" href="./css/stylesheet.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー検索入力画面&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>

	 <div class="alert">
		<c:if test="${requestScope.alertNumLoginId != null && requestScope.alertNumLoginId != ''}"><!-- 未入力の場合 -->
				<c:out value="${requestScope.alertNumLoginId}" /><br>
		</c:if>
	</div>

	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<form action=? method="post">
				<table  class="table">
					<tr>
						<!-- ログインID  -->
						<th nowrap class="color-main text-left"><label for="loginId">
								<span class="icon-smile pe-2x pe-va"> </span>&nbsp;ログインID
						</label></th>
						<td class="text-left"><input class="form-control" type="text" name="loginId" size="20" autofocus value=""
							maxlength="10" /></td>
					</tr>

					<tr>
						<!-- ユーザー名-->
						<th nowrap class="color-main text-left"><label
							for="userName"> <span class="icon-note pe-2x pe-va"></span>&nbsp;ユーザー名
						</label></th>
						<td class="text-left"><input class="form-control" type="text"
							name="userName" size="20" value="" maxlength="30"
							pattern="\S|\S.*?\S" /></td>
					</tr>

					<tr>
						<!-- アイコン選択 -->
						<th nowrap class="color-main text-left"><span class="icon-joy pe-2x pe-va"></span>&nbsp;アイコン</th>
						<td class="text-left" id=parent>
							<div class="child">
								<span class="icon-smile pe-2x pe-va"></span>
								<label class="fancy-checkbox">
									<input type="checkbox" name="icon" id="icon" value="icon-smile" ><span></span>
								</label>
							</div>

							<div class="child">
								<span class="icon-joy pe-2x pe-va"></span>
								<label class="fancy-checkbox" >
									<input type="checkbox" name="icon" id="icon" value="icon-joy" ><span></span>
								</label>
							</div>
						</td>
					</tr>

					<tr>
						<!-- プロフィール -->
						<th nowrap class="color-main text-left"><label for="profile"><span class="icon-note pe-2x pe-va"></span>&nbsp;プロフィール
						</label></th>
						<td class="text-left"><input class="form-control" type="text"
							name="profile" size="20" value="" maxlength="100" /></td>
					</tr>

					<tr>	<!-- 登録＆戻るボタン -->
						<td colspan="2" class="text-center">
							<button class="btn" type="submit" value="検索" formaction="./usi" name="searchParam">検索</button>
							<button class="btn" type="submit" value="戻る" formaction="top.jsp" name="searchParam">戻る</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>