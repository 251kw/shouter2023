package src.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.dao.DBManager;
import src.dto.UserDTO;

/**
 * Servlet implementation class userEditConfirmSvt
 */
@WebServlet("/userEditConfirmSvt")
public class userEditConfirmSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public userEditConfirmSvt() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//定義
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");
		RequestDispatcher dispatcher = null;
		String message = null;

		//インスタンス化、DBManagerでつくった条件の分岐
		UserDTO USER = new UserDTO(loginId, password, userName, icon, profile);
		DBManager dbm = new DBManager();
		/*
		dbm.Logincheck(loginId);
		if (dbm.Logincheck(loginId).equals("true")) {
			// ログインID重複してたら
			message = "ログインIDが重複しています";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);

			// touroku.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);

		}
		*/
		HttpSession session = request.getSession();
		//if以外ならDBMの値をもらう
		dbm.setUSER(USER);
		session.setAttribute("USER", USER);

		// 処理を転送
		dispatcher = request.getRequestDispatcher("userEditResult.jsp");
		dispatcher.forward(request, response);
	}
}
