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
 * Servlet implementation class userEditResultSvt
 */
@WebServlet("/userEditResultSvt")
public class userEditResultSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public userEditResultSvt() {
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
		RequestDispatcher dispatcher = null;

		HttpSession session = request.getSession();
		DBManager dbm = new DBManager();
		String loginId = (String) session.getAttribute("login");//更新したところ以外、入力時のがめんをだす＋更新後の検索を行い更新したリストを出す
		String userName = (String) session.getAttribute("userName");
		String icon = (String) session.getAttribute("icon");
		String icon2 = (String) session.getAttribute("icon2");
		String profile = (String) session.getAttribute("profile");
		ArrayList<UserDTO> users = dbm.getUserList(loginId, userName, icon, icon2, profile);//更新後の検索

		session.setAttribute("users", users);

		// 処理を転送
		dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
		dispatcher.forward(request, response);
	}
}
