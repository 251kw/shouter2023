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
 * Servlet implementation class UserSearchResultSvt
 */
@WebServlet("/UserSearchResultSvt")
public class UserSearchResultSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSearchResultSvt() {
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
		//定義
		String x = request.getParameter("x");
		String number = "id" + x;
		String id = request.getParameter(number);
		RequestDispatcher dispatcher = null;

		DBManager dbm = new DBManager();
		UserDTO ID =dbm.getUserId(id);

		// 処理の転送先を .jsp に指定
		HttpSession session = request.getSession();
		session.setAttribute("ID", ID);
		dispatcher = request.getRequestDispatcher("userEditInput.jsp");
		// 処理を転送
		dispatcher.forward(request, response);

	}
}