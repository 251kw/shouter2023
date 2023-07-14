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
 * Servlet implementation class userDeleteResultSvt
 */
@WebServlet("/userDeleteResultSvt")
public class userDeleteResultSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public userDeleteResultSvt() {
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
		HttpSession session = request.getSession();
		DBManager dbm = new DBManager();
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String loginId = (String)session.getAttribute("login");//入力時をセッションに保存,再検索用＋更新する前のデータを表示
		String userName = (String)session.getAttribute("userName");
		String icon = (String)session.getAttribute("icon");
		String icon2 = (String)session.getAttribute("icon2");
		String profile = (String)session.getAttribute("profile");
		ArrayList<UserDTO> users = dbm.getUserList(loginId, userName, icon, icon2, profile);
		session.setAttribute("users",users);
		//定義
		RequestDispatcher dispatcher = null;
		ArrayList<String> idlist = (ArrayList<String>) session.getAttribute("idlist");

		 dbm.setDelete(idlist);

		// 処理を転送
		dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
		dispatcher.forward(request, response);

	}
}