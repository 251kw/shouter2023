<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 更新画面 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<style>
.parent {
	display: flex; /*横並びを指定*/
}

.child {
	display: flex; /*横並びを指定*/
	padding-right: 40px; /*間隔をあける*/
}
</style>
<%
	//文字化け対策
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");

	String ID = null;
	String NAME = null;
	String PAS = null;
	String ICON = null;
	String PROF = null;

	//編集ボタン押下から推移・・・初期値は編集ボタンで送られてきたユーザの情報
	dto.UserDTO u = (dto.UserDTO)request.getAttribute("edit");
	if(u != null){
		ID = u.getLoginId();
		NAME = u.getUserName();
		PAS = u.getPassword();
		ICON = u.getIcon();
		PROF = u.getProfile();
	}

	//確認画面の戻るボタンか、入力チェックに引っかかった場合から推移・・・UserEditConfirm.jspからhiddenで来たキー(name)で、そのvalueを取得。
	if(request.getParameter("loginId")!=null)
	{
		ID =request.getParameter("loginId");
	}
	if(request.getParameter("userName")!=null)
	{
		NAME =request.getParameter("userName");
	}
	if(request.getParameter("password")!=null)
	{
		PAS =request.getParameter("password");
	}
	if(request.getParameter("icon")!=null)
	{
		ICON=request.getParameter("icon");
	}
	if(request.getParameter("profile")!=null)
	{
		PROF =request.getParameter("profile");
	}
%>
<body>

	<div class="bg-main padding-y-5">
		<div class="padding-y-5 text-center">
			<strong>ユーザ更新画面</strong><br>
			<p style="display: inline">ユーザーの情報を編集し、更新します。</p>
		</div>
	</div>
	<%-- リクエストスコープに alert があれば リクエストスコープの alert の値を出力--%>
	<div class="color-error text-center">
		<c:if
			test="${requestScope.alert1 != null && requestScope.alert1 != ''}">
			<c:out value="${requestScope.alert1}" />
			<br>
		</c:if>
		<c:if
			test="${requestScope.alert2 != null && requestScope.alert2 != ''}">
			<c:out value="${requestScope.alert2}" />
		</c:if>
		<c:if
			test="${requestScope.alert3 != null && requestScope.alert3 != ''}">
			<c:out value="${requestScope.alert3}" />
		</c:if>
		<c:if
			test="${requestScope.alert4 != null && requestScope.alert4 != ''}">
			<c:out value="${requestScope.alert4}" />
		</c:if>
		<c:if
			test="${requestScope.alert5 != null && requestScope.alert5 != ''}">
			<c:out value="${requestScope.alert5}" />
		</c:if>
		<c:if
			test="${requestScope.alert6 != null && requestScope.alert6 != ''}">
			<c:out value="${requestScope.alert6}" />
		</c:if>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定。押すボタンによって推移先が違うのでaction属性は「?」 --%>
			<form action="?" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left" nowrap><span
							class="icon-users pe-2x pe-va"></span>&nbsp;ログインID</td>
						<td class="text-left"><input class="form-control" type="text"
							maxlength="10" placeholder="10文字以内で入力" name="loginId"
							value="<%=ID%>"></td>
					<tr>
						<%-- ユーザー名 入力欄の名前は userName --%>
						<td class="color-main text-left" nowrap><span
							class="icon-users pe-2x pe-va"></span>&nbsp;ユーザー名</td>
						<td class="text-left"><input class="form-control" type="text" pattern="\S|\S.*?\S"
							maxlength="15" placeholder="15文字以内で入力"
							name="userName" value="<%=NAME%>"></td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<td class="color-main text-left"><span class="icon-users pe-2x pe-va"></span>&nbsp;パスワード</td>
						<td class="text-left"><input class="form-control" type="text" maxlength="8"
							placeholder="8文字以内で入力" name="password" value=<%=PAS%>></td>
					</tr>
					<tr>
						<%-- アイコン 選択欄の名前は icon --%>
						<td class="color-main text-left" nowrap><span
							class="icon-users pe-2x pe-va"></span>&nbsp;アイコン</td>
						<td class="parent">
							<!--親で囲った要素の横並びをcssで指定してる-->
							<div class="child">
								<!--子で囲った要素同士の間隔をcssで指定してる-->
								<label for="icon"> <span class="icon-star  pe-2x pe-va"></span>
								</label> <label class="fancy-radio"> <input type="radio"
									name="icon" id="icon" value="icon-star"  <% if(ICON.equals("icon-star")){%>checked<%}%>><span></span>
								</label>
							</div>
							<div class="child">
								<label for="icon"> <span class="icon-smile pe-2x pe-va"></span>
								</label> <label class="fancy-radio"> <input type="radio"
									name="icon" id="icon" value="icon-smile" <% if(ICON.equals("icon-smile")){%>checked<%}%>><span></span>
								</label>
							</div>
						</td>
						<td class="parent">
							<!--親で囲った要素の横並びをcssで指定してる-->
							<div class="child">
								<!--子で囲った要素同士の間隔をcssで指定してる-->
								<label for="icon"> <span class="icon-bell  pe-2x pe-va"></span>
								</label> <label class="fancy-radio"> <input type="radio"
									name="icon" id="icon" value="icon-bell" <% if(ICON.equals("icon-bell")){%>checked<%}%>><span></span>
								</label>
							</div>
							<div class="child">
								<label for="icon"> <span class="icon-user pe-2x pe-va"></span>
								</label> <label class="fancy-radio"> <input type="radio"
									name="icon" id="icon" value="icon-user" <% if(ICON.equals("icon-user")){%>checked<%}%>><span></span>
								</label>
							</div>
							<div class="child">
								<label for="icon"> <span class="icon-user-female pe-2x pe-va"></span>
								</label> <label class="fancy-radio"> <input type="radio"
									name="icon" id="icon" value="icon-user-female" <% if(ICON.equals("icon-user-female")){%>checked<%}%>><span></span>
								</label>
							</div>
						</td>
					</tr>
					<tr>
						<%-- プロフィール 入力欄の名前は profile --%>
						<td class="color-main text-left" nowrap><span
							class="icon-users pe-2x pe-va"></span>&nbsp;プロフィール</td>
						<td class="text-left"><input class="form-control" type="text"
							maxlength="30" placeholder="30文字以内で入力"
							name="profile" value="<%=PROF%>"></td>
					</tr>
					<tr>
						<td colspan="2" class="text-right">
						<input class="btn" type="submit" value="更新" formaction="./uei" /> <%-- 登録ボタンでUserUpdate.javaにとぶ --%>
							<a href="UserSearchResult.jsp" class="btn">キャンセル</a></td><%-- 戻る時は送る値がないのでハイパーリンクでOK --%>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>