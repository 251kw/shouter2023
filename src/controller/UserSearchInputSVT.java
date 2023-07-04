package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.UserDTO;

/**
 * Servlet implementation class UserSearchInputSVT
 */
@WebServlet("/usi")
public class UserSearchInputSVT extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String icon_user_female = request.getParameter("icon-user-female");
		String icon_user = request.getParameter("icon-user");
		String icon_bell = request.getParameter("icon-bell");
		String icon_smile = request.getParameter("icon-smile");
		String profile = request.getParameter("profile");
		String search = request.getParameter("search");
		RequestDispatcher dispatcher;

		if (search != null) {
			DBManager db = new DBManager();
			ArrayList<UserDTO> list = db.getUserList(loginId, userName, icon_user_female, icon_user, icon_bell, icon_smile, profile);
			request.setAttribute("searchUser", list);
			dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
		}else {
			dispatcher = request.getRequestDispatcher("index.jsp");
		}
		dispatcher.forward(request, response);
	}

}
