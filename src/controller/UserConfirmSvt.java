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
 * Servlet implementation class UserConfirmSvt
 */
@WebServlet("/uic")
public class UserConfirmSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserConfirmSvt() {
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
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 送信情報の取得
		String loginId = request.getParameter("id");
		String password = request.getParameter("pass");
		String name = request.getParameter("name");
		String pro = request.getParameter("pro");
		String icon = request.getParameter("icon");

		RequestDispatcher dispatcher = null;

		// ログイン認証を行い、ユーザ情報を取得
		DBManager dbm = new DBManager();

		UserDTO user = new UserDTO(loginId, password, name, icon, pro);
		dbm.setNewUser(user);

		dispatcher = request.getRequestDispatcher("UserInfoResult.jsp");
		dispatcher.forward(request, response);
	}

}
