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
 * Servlet implementation class UserDeleteConfirm
 */
@WebServlet("/udc")
public class UserDeleteConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html:charset=UTF-8");
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String icon_user_female = request.getParameter("icon-user-female");
		String icon_user = request.getParameter("icon-user");
		String icon_bell = request.getParameter("icon-bell");
		String icon_smile = request.getParameter("icon-smile");
		String profile = request.getParameter("profile");
		String[] deleteUser = request.getParameterValues("deleteUser");
		String ok = request.getParameter("delete");
		request.setAttribute("deleteUser", deleteUser);
		request.setAttribute("loginId", loginId);
		request.setAttribute("userName", userName);
		request.setAttribute("icon-user-female", icon_user_female);
		request.setAttribute("icon-user", icon_user);
		request.setAttribute("icon-bell", icon_bell);
		request.setAttribute("icon-smile", icon_smile);
		request.setAttribute("profile", profile);
		RequestDispatcher dispatcher;
		DBManager db = new DBManager();

		if(ok!=null) {
			boolean check = db.deleteUser(deleteUser);
			if(check) {
				String msg = "削除に失敗しました。";
				request.setAttribute("deleteAlert", msg);
			}
			dispatcher = request.getRequestDispatcher("userDeleteResult.jsp");
		}else {
			ArrayList<UserDTO> list = db.getUserList(loginId, userName, icon_user_female, icon_user, icon_bell, icon_smile,
					profile);
			if (list.size() != 0) {
				request.setAttribute("searchUser", list);
			} else {
				String msg = "検索条件に一致する結果が見つかりません。";
				request.setAttribute("alert", msg);
			}
			dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
		}
		dispatcher.forward(request, response);
	}

}
