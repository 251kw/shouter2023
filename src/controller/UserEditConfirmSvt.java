package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;
import dto.UserDTO;

/**
 * Servlet implementation class UserInfoInputSvt
 */
@WebServlet("/uec")
public class UserEditConfirmSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserEditConfirmSvt() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// index.jsp の「新規登録」ボタンから呼び出される
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");
		RequestDispatcher dispatcher = null;

		//セッションにある、前のログインIDを受け取る(UserSearchResultSvt.javaで登録した)
		HttpSession session = request.getSession();
		String edituserId = (String)session.getAttribute("edituserId");

		DBManager dbm = new DBManager();
		//更新
		boolean updateResult = dbm.getEditUser(loginId, password, userName, icon, profile, edituserId);
		if(updateResult == true) {	//更新成功したら
			//更新されたユーザを検索
			UserDTO updateUser = dbm.getLoginUser(loginId, password);
			// ログインユーザ情報、書き込み内容リストとしてリクエストスコープに保存
			request.setAttribute("updateuser", updateUser);
			//処理の転送先を UserInfoResult.jsp に指定。結果画面表示される。
			dispatcher = request.getRequestDispatcher("UserEditResult.jsp");
			//処理転送
			dispatcher.forward(request, response);
		}
	}
}
