package src.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.dao.DBManager;
import src.dto.UserDTO;

/**
 * Servlet implementation class KannryouServlet
 */
@WebServlet("/kannryou")
public class KannryouServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KannryouServlet() {
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
		String loginId = request.getParameter("login");
		String password = request.getParameter("pass");
		String username = request.getParameter("name");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("prof");
		RequestDispatcher dispatcher = null;
		String message = null;

		//インスタンス化、DBManagerでつくった条件の分岐
		UserDTO user = new UserDTO(loginId, password, username, icon, profile);
		request.setAttribute("user", user);
		DBManager dbm = new DBManager();
		dbm.Logincheck(loginId);
		if (dbm.Logincheck(loginId).equals("true")) {
			// ログインID重複してたら
			message = "ログインIDが重複しています";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);

			// touroku.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("touroku.jsp");
			dispatcher.forward(request, response);

		}

		//if以外ならDBMの値をもらう
		dbm.setUser(user);

		// 処理を転送
		dispatcher = request.getRequestDispatcher("kannryou.jsp");
		dispatcher.forward(request, response);
	}
}
