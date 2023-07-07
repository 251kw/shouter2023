package src.controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class UserSearchInputSvt
 */
@WebServlet("/UserSearchInputSvt")
public class UserSearchInputSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSearchInputSvt() {
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

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String icon2 = request.getParameter("icon2");
		String profile = request.getParameter("profile");
		RequestDispatcher dispatcher = null;
		String message = null;

		DBManager dbm = new DBManager();
		ArrayList<UserDTO> users = dbm.getUserList(loginId, userName, icon, icon2, profile);
		HttpSession session = request.getSession();
		// ログインユーザ情報、書き込み内容リストとしてセッションに保存
		session.setAttribute("users", users);

		if (users.size() == 0) {//リストの中に要素がない
			message = "検索結果はありません";
			request.setAttribute("alert", message);
			// touroku.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("UserSearchResultAlert.jsp");
			dispatcher.forward(request, response);
		} else {
			// 処理の転送先を .jsp に指定
			dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
			// 処理を転送
			dispatcher.forward(request, response);
		}
	}
}