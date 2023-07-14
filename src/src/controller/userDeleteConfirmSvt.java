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

/**
 * Servlet implementation class userDeleteConfirmSvt
 */
@WebServlet("/userDeleteConfirmSvt")
public class userDeleteConfirmSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public userDeleteConfirmSvt() {
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
		//String loginId = request.getParameter("loginId");
		//String password = request.getParameter("password");
		//String userName = request.getParameter("userName");
		//String icon = request.getParameter("icon");
		//String profile = request.getParameter("profile");
		//RequestDispatcher dispatcher = null;

		//インスタンス化、DBManagerでつくった条件の分岐
		//UserDTO Delete = new UserDTO(loginId, password, userName, icon, profile);
		//DBManager dbm = new DBManager();

		//HttpSession session = request.getSession();


		HttpSession session = request.getSession();
		ArrayList<String> idlist = (ArrayList<String>) session.getAttribute("idlist");

		RequestDispatcher dispatcher = null;

		DBManager dbm = new DBManager();

		dbm.setDelete(idlist);

		//if以外ならDBMの値をもらう
		//session.setAttribute("Delete", list);

		// 処理を転送
		dispatcher = request.getRequestDispatcher("userDeleteResult.jsp");
		dispatcher.forward(request, response);
	}
}
