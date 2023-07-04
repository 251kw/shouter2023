package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.UserDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/uic")
public class UserInfoConfirmSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfoConfirmSvt() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("signupid.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	// index.jspの「ログイン」ボタンから呼び出される
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 送信情報の取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		RequestDispatcher dispatcher = null;
		String message = null;
		//ユーザー情報を作成
		DBManager dbm = new DBManager();
		UserDTO user = dbm.checkUser(loginId, password, userName, icon, profile);
		if (user == null) {
			message = "ログインIDが既に存在する";
			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);
			// signup.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("signupid.jsp");
		} else {
			message = "「戻る」を押すとトップページに戻ります";
			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);
			request.setAttribute("user", user);
			// index.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("result.jsp");
		}
		dispatcher.forward(request, response);
	}

}
