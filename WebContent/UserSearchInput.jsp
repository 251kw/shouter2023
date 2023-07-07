<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 検索画面 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<style>
.parent{
  display: flex;	/*横並びを指定*/
}
.child{
  display: flex;	/*横並びを指定*/
  padding-right: 40px;	/*間隔をあける*/
}
</style>
<body>

	<div class="bg-main padding-y-5">
	<div class="padding-y-5 text-center">
		<strong>ユーザ検索入力画面</strong><br>
		<p style=display:inline>ユーザーを検索します。</p>
	</div>
	</div>
	<%-- リクエストスコープに alert があれば リクエストスコープの alert の値を出力--%>
	<div class = "color-error text-center">
		<c:if test="${requestScope.alert2 != null && requestScope.alert2 != ''}">
			<c:out value="${requestScope.alert2}" /><br>
		</c:if>
		<c:if test="${requestScope.alert3 != null && requestScope.alert3 != ''}">
			<c:out value="${requestScope.alert3}" />
		</c:if>
		<c:if test="${requestScope.alert4 != null && requestScope.alert4 != ''}">
			<c:out value="${requestScope.alert4}" />
		</c:if>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定。押すボタンによって推移先が違うのでaction属性は「?」 --%>
			<form action="?" method="post">
				<table style="width: 400px" class="table">

					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left" nowrap><span class="icon-users pe-2x pe-va"></span>&nbsp;ログインID</td>
						<td class="text-left"><input class="form-control" type="text" maxlength="10"
							placeholder="10文字以内で入力" name="loginId" value=""></td>
					<tr>
						<%-- ユーザー名 入力欄の名前は userName --%>
						<td class="color-main text-left" nowrap><span class="icon-users pe-2x pe-va"></span>&nbsp;ユーザー名</td>
						<td class="text-left"><input class="form-control" type="text" maxlength="15"
							placeholder="15文字以内で入力" name="userName" value=""></td>
					</tr>
					<tr>
						<%-- アイコン 選択欄の名前は icon --%>
						<td class="color-main text-left" nowrap><span class="icon-users pe-2x pe-va"></span>&nbsp;アイコン</td>
						<td class = "parent" >		<!--親で囲った要素の横並びをcssで指定してる-->
							<div class = "child">		<!--子で囲った要素同士の間隔をcssで指定してる-->
								<label for="icon">
								<span class = "icon-star  pe-2x pe-va"></span>
								</label>
								<label class="fancy-checkbox">
								<input type="checkbox" name="icon" id="icon" value="icon-star"><span></span>
								</label>
							</div>
							<div class = "child">
								<label for="icon">
								<span class = "icon-smile pe-2x pe-va"></span>
								</label>
								<label class="fancy-checkbox">
								<input type="checkbox" name="icon" id="icon" value="icon-smile"><span></span>
								</label>
							</div>
						</td>
						<td class = "parent">		<!--親で囲った要素の横並びをcssで指定してる-->
							<div class = "child">		<!--子で囲った要素同士の間隔をcssで指定してる-->
								<label for="icon">
								<span class = "icon-bell  pe-2x pe-va"></span>
								</label>
								<label class="fancy-checkbox">
								<input type="checkbox" name="icon" id="icon" value="icon-bell"><span></span>
								</label>
							</div>
							<div class = "child">
								<label for="icon">
								<span class = "icon-user pe-2x pe-va"></span>
								</label>
								<label class="fancy-checkbox">
								<input type="checkbox" name="icon" id="icon" value="icon-user"><span></span>
								</label>
							</div>
							<div class = "child">
								<label for="icon">
								<span class = "icon-user-female pe-2x pe-va"></span>
								</label>
								<label class="fancy-checkbox">
								<input type="checkbox" name="icon" id="icon" value="icon-user-female"><span></span>
								</label>
							</div>
						</td>
					</tr>
					<tr>
						<%-- プロフィール 入力欄の名前は profile --%>
						<td class="color-main text-left" nowrap><span class="icon-users pe-2x pe-va"></span>&nbsp;プロフィール</td>
						<td class="text-left"><input class="form-control" type="text" maxlength="30"
							placeholder="30文字以内で入力" name="profile" value=""></td>
					</tr>
					<tr>
					<%-- 登録ボタンでUserInfoInputsvt.javaにとぶ --%>
						<td colspan="2" class="text-right">
						<input class="btn" type="submit" value="検索" name="param" formaction="./usi"/>
						<%-- 戻る時は送る値がないのでハイパーリンクでOK --%>
						<a href="top.jsp" class="btn">戻る</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>