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
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>ユーザー検索結果画面&nbsp;<span class="icon-speaker"></span></h1>
		</div>
	</div>

			<form action=? method="post">
				<c:choose>
					<c:when test="${requestScope.alertEmpResult != null && requestScope.alertEmpResult != ''}">
						<div class="color-error text-center">
							<c:out value="${requestScope.alertEmpResult}" />
							<br>
						</div>
					</c:when>
					<c:otherwise>
						<jsp:useBean id="user2" scope="session" type="java.util.ArrayList<dto.UserDTO>" />
						<tr>
							<th nowrap>
								<label for="whiteSpace"></label>
							</th>

							<th nowrap>
								<button class="checkBtn" type="button"  id="check-btn" onclick="checked()">全選択</button>
							</th>

							<th nowrap>
								<button class="uncheckBtn" type="button"  id="uncheck-btn" onclick="unChecked()" >全解除</button>
							</th>
						</tr>
						<div class="padding-y-5 text-center">
						<div style="width: 40%" class="container padding-y-5 text-center">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th nowrap>
										 <label for="whiteSpace"></label>
									</th>
									<th nowrap>
										<!-- ログインID -->
										 <label for="loginId">&nbsp;ログインID</label>
									</th>
									<th nowrap>
										<!-- ユーザー名 -->
										<label for="userName">&nbsp;ユーザー名</label>
									</th>
									<th nowrap><label for="icon">&nbsp;アイコン</label></th>
									<th nowrap><label for="profile">&nbsp;プロフィール</label></th>
									<th nowrap> <label for="whiteSpace"></label></th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="user" items="${user2}">
									<tr>
										<td><!-- チェックボックス -->
											<label class="fancy-checkbox" >
												<input type="checkbox" name="checkbox" value="${user.loginId}" class="checks"><span></span>
											</label>
										</td>

										<td class="text-left"><input class="form-control" type="hidden" name="loginId" value="" size="20" autofocus>
											${user.loginId}
										</td>
										<td class="text-left"><input class="form-control" type="hidden" name="userName" value="" size="20" />
											${user.userName}
										</td>
										<td class="text-left"><label for="icon"><span class=${user.icon} ></span></label>
											<input type="hidden" name="icon" id="icon" value="${user.icon}" checked>
										</td>
										<td class="text-left"><input class="form-control" type="hidden" name="profile" value="" size="20" />
											${user.profile}
										</td>

										<td colspan="2" class="text-right"><button class="btn" type="submit" value="編集" formaction="">編集</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
							</div>
						</div>
						<tr class="resultBtn">
							<th nowrap>
								<label for="whiteSpace"></label>
							</th>
							<th nowrap>
								<button class="checkBtn" type="button"  id="check-btn" onclick="checked()">全選択</button>
							</th>

							<th nowrap>
								<button class="uncheckBtn" type="button"  id="uncheck-btn" onclick="unChecked()" >全解除</button>
							</th>
							<th nowrap><label for="whiteSpace"></label></th>
							<th nowrap><label for="whiteSpace"></label></th>
							<th nowrap><label for="whiteSpace"></label></th>
						</tr>
					</c:otherwise>
				</c:choose>
				<div class="padding-y-5 text-center">
				<div style="width: 40%" class="container padding-y-5 text-center">
					<input class="btn" type="submit" value="削除" formaction="">
					<a href="UserSearchInput.jsp" class ="btn">戻る</a>
				</div>
				</div>
			</form>
	<script>
		function unChecked() {
		    let boxes = document.querySelectorAll(".checks");
		  for (let i = 0; i < boxes.length; i++) {
		    boxes[i].checked = false;
		  }
		}

		function checked() {
		  let boxes = document.querySelectorAll(".checks");
		  for (let i = 0; i < boxes.length; i++) {
		    boxes[i].checked = true;
		  }
		}
	</script>
</body>
</html>