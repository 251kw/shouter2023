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
 * Servlet implementation class UserInfoResult
 */
@WebServlet("/usr")
public class UserInfoResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfoResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		DBManager db = new DBManager();
		String turnback = request.getParameter("return");
		String allCheck = request.getParameter("allCheck");
		String allClear = request.getParameter("allClear");
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String icon_user_female = request.getParameter("icon-user-female");
		String icon_user = request.getParameter("icon-user");
		String icon_bell = request.getParameter("icon-bell");
		String icon_smile = request.getParameter("icon-smile");
		String profile = request.getParameter("profile");
		request.setAttribute("loginId", loginId);
		request.setAttribute("userName", userName);
		request.setAttribute("icon-user-female", icon_user_female);
		request.setAttribute("icon-user", icon_user);
		request.setAttribute("icon-bell", icon_bell);
		request.setAttribute("icon-smile", icon_smile);
		request.setAttribute("profile", profile);

		if (turnback != null) {
			dispatcher = request.getRequestDispatcher("UserSearchInput.jsp");
		} else if (allCheck != null || allClear != null) {//全選択または全解除が押された場合の処理
			if (allCheck != null) {
				request.setAttribute("allCheck", allCheck);//全選択が押された場合のみ、allCheckという名称で値を受け渡し
			}
			ArrayList<UserDTO> list = db.getUserList(loginId, userName, icon_user_female, icon_user, icon_bell, icon_smile, profile);
			request.setAttribute("searchUser", list);
			dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
		}
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);

	}

}
