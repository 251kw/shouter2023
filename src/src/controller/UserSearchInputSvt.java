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
 * Servlet implementation class UserSearchInputSvt
 */
@WebServlet("/UserSearchInputSvt")
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
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");
		RequestDispatcher dispatcher = null;
		String message = null;


		DBManager dbm = new DBManager();
		//UserDTO user = dbm.getSearchUserlogin(loginId);
		//UserDTO user = dbm.getSearchUsername(userName);
		//UserDTO user = dbm.getSearchUserprof(profile);
		//UserDTO user = dbm.getSearchUsericon(icon);//できない
		ArrayList<UserDTO> user = dbm.getUserList(loginId, userName, icon, profile) ;//できない
		HttpSession session = request.getSession();
		// ログインユーザ情報、書き込み内容リストとしてセッションに保存
		session.setAttribute("user", user);

		// 処理の転送先を .jsp に指定
		dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
		// 処理を転送
		dispatcher.forward(request, response);
	}
}
		/*try {
			// Beanクラスのインスタンス
			UserSearchInputDTO input_user = new UserSearchInputDTO(loginId, username, icon, profile);

			// Beanクラスに入力したユーザIDをセットする
			input_user.setLoginId(loginId);
			input_user.setUserName(username);
			input_user.setIcon(icon);
			input_user.setProfile(profile);

			// リスト＜Beanクラス＞に検索結果を格納する
			ArrayList<UserSearchInputDTO> user_list = UserSearchInputDTO.getInstance().SelectUserData(input_user);

			// 検索結果をJSP画面に返す
			request.setAttribute("user_list", user_list);
			request.getRequestDispatcher("/WEB-INF/jsp/UserSearchResult.jsp").forward(request, response);

			} catch (Exception e) {
			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);
			// UserSearchResult.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
			dispatcher.forward(request, response);
		}
	}
}
*/
