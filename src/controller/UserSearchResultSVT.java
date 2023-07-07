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
@WebServlet("/usr")
public class UserSearchResultSVT extends HttpServlet {
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
		request.setAttribute("loginId", loginId);
		request.setAttribute("userName", userName);
		request.setAttribute("icon-user-female", icon_user_female);
		request.setAttribute("icon-user", icon_user);
		request.setAttribute("icon-bell", icon_bell);
		request.setAttribute("icon-smile", icon_smile);
		request.setAttribute("profile", profile);
		String allCheck = request.getParameter("allCheck");
		String allClear = request.getParameter("allClear");
		String edit = request.getParameter("edit");
		request.setAttribute("edit", edit);
		RequestDispatcher dispatcher;
		DBManager db = new DBManager();

		if (allCheck!=null || allClear!=null) {
			if(allCheck!=null) {
				request.setAttribute("allCheck", allCheck);
			}
			ArrayList<UserDTO> list = db.getUserList(loginId, userName, icon_user_female, icon_user, icon_bell, icon_smile, profile);
			request.setAttribute("searchUser", list);
			dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
		}else if(edit!=null) {
			int ed = Integer.parseInt(edit);
			ArrayList<UserDTO> list = db.getUserList(loginId, userName, icon_user_female, icon_user, icon_bell, icon_smile, profile);
			UserDTO editUser = list.get(ed);
			request.setAttribute("editUser", editUser);

			dispatcher = request.getRequestDispatcher("userEditInput.jsp");
		}else {
			dispatcher = request.getRequestDispatcher("UserSearchInput.jsp");
		}
		dispatcher.forward(request, response);
	}

}
