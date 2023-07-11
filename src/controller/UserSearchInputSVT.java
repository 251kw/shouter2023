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
		String message = null;
		request.setAttribute("loginId", loginId);
		request.setAttribute("userName", userName);
		request.setAttribute("icon-user-female", icon_user_female);
		request.setAttribute("icon-user", icon_user);
		request.setAttribute("icon-bell", icon_bell);
		request.setAttribute("icon-smile", icon_smile);
		request.setAttribute("profile", profile);
		RequestDispatcher dispatcher;

		if (search != null) {
			DBManager db = new DBManager();
			ArrayList<UserDTO> list = db.getUserList(loginId, userName, icon_user_female, icon_user, icon_bell, icon_smile, profile);
			if(list.size()!=0) {//検索結果が見つかった場合の処理
			request.setAttribute("searchUser", list);
			dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
			}else {//検索結果が見つからなかったとき（getUserListメソッドの戻り値のリストのサイズが0だった場合の処理)
				message = "検索条件に一致する結果が見つかりません。";
				request.setAttribute("alert", message);

				dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
			}
		}else {
			dispatcher = request.getRequestDispatcher("top.jsp");
		}
		dispatcher.forward(request, response);
	}

}
