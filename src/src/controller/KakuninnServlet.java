package src.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.dto.UserDTO;

/**
 * Servlet implementation class KakuninnServlet
 */
@WebServlet("/kakuninn")
public class KakuninnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 直接アクセスがあった場合は index.jsp に処理を転送
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");
		RequestDispatcher dispatcher = null;
		String message = null;
		String message2 = null;
		String message3 = null;
		String message4 = null;

		/*
		if (loginId.equals("") || password.equals("") || username.equals("") || icon.equals("") || profile.equals("")) {
			// ログインID 、パスワード、ユーザー名、アイコン、プロフィールが未入力なら
			message = "すべて必須入力です";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);

			// touroku.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("touroku.jsp");
			dispatcher.forward(request, response);
		*/
		while(loginId.equals("") || password.equals("") || username.equals("") || icon.equals("") || profile.equals("")) {
			if (loginId.equals("")) {
				message = "ログインIDは必須入力です";
			}
			if (password.equals("")) {
				message2 = "パスワードは必須入力です";
			}
			if (username.equals("")) {
				message3 = "ユーザー名は必須入力です";
			}
			if (profile.equals("")) {
				message4 = "プロフィールは必須入力です";
			}
			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);
			request.setAttribute("alert2", message2);
			request.setAttribute("alert3", message3);
			request.setAttribute("alert4", message4);
			// touroku.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("touroku.jsp");
			dispatcher.forward(request, response);
			break;
		}

		if (!loginId.equals("") || password.equals("") || username.equals("") || icon.equals("")
				|| profile.equals("")) {
			// ユーザ情報を取得し、確認画面へ
			UserDTO user = new UserDTO(loginId, password, username, icon, profile);
			request.setAttribute("user", user);
			dispatcher = request.getRequestDispatcher("kakuninn.jsp");
			dispatcher.forward(request, response);

		}
	}
}
