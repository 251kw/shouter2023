package controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class UserSearchInputSvt
 */
@WebServlet("/usi")
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
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		RequestDispatcher dispatcher = null;

		// 送信情報の取得
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String icon = request.getParameter("icon");
		String pro = request.getParameter("pro");

		DBManager dbm = new DBManager();

		//if(id!=null && name!=null && icon!=null && pro!=null) {
			//UserDTO user = dbm.searchall(id,icon,name,pro);
		//}

		//ArrayList<UserDTO> user = dbm.loginid(id);
		//ArrayList<UserDTO> user = dbm.username(name);
		ArrayList<UserDTO> user = dbm.profile(pro);

		HttpSession session = request.getSession();
		// ログインユーザ情報、書き込み内容リストとしてセッションに保存
		session.setAttribute("user", user);

		dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
		dispatcher.forward(request, response);
	}

}
