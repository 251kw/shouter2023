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
 * Servlet implementation class UserEditConfirm
 */
@WebServlet("/uec")
public class UserEditConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

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
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String icon_user_female = request.getParameter("icon-user-female");
		String icon_user = request.getParameter("icon-user");
		String icon_bell = request.getParameter("icon-bell");
		String icon_smile = request.getParameter("icon-smile");
		String profile = request.getParameter("profile");
		String e_LoginId = request.getParameter("e_LoginId");
		String e_UserName = request.getParameter("e_UserName");
		String e_Password = request.getParameter("e_Password");
		String e_Icon = request.getParameter("e_Icon");
		String e_Profile = request.getParameter("e_Profile");
		String OK = request.getParameter("OK");
		String edit = request.getParameter("edit");
		request.setAttribute("loginId", loginId);
		request.setAttribute("userName", userName);
		request.setAttribute("icon-user-female", icon_user_female);
		request.setAttribute("icon-user", icon_user);
		request.setAttribute("icon-bell", icon_bell);
		request.setAttribute("icon-smile", icon_smile);
		request.setAttribute("profile", profile);
		request.setAttribute("e_LoginId", e_LoginId);
		request.setAttribute("e_UserName", e_UserName);
		request.setAttribute("e_Password", e_Password);
		request.setAttribute("e_Icon", e_Icon);
		request.setAttribute("e_Profiel", e_Profile);
		request.setAttribute("edit", edit);
		UserDTO editUser = null;

		DBManager db = new DBManager();

		RequestDispatcher dispatcher = null;

		if (OK != null) {
			ArrayList<UserDTO> list = db.getUserList(loginId, userName, icon_user_female, icon_user, icon_bell,
					icon_smile, profile);
			for(UserDTO u:list) {
				if(u.getLoginId().equals("edit")) {
					editUser = u;
				}
			}
			UserDTO editedUser = db.editUser(editUser, e_LoginId, e_UserName, e_Password, e_Icon, e_Profile);
			request.setAttribute("editedUser", editedUser);
			dispatcher = request.getRequestDispatcher("userEditResult.jsp");
		}else {
			dispatcher = request.getRequestDispatcher("userEditInput.jsp");
		}
		dispatcher.forward(request, response);
	}

}
