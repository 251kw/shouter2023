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
		String icon2 = request.getParameter("icon2");
		String pro = request.getParameter("pro");

		DBManager dbm = new DBManager();
		HttpSession session = request.getSession();
		String message = null;

		//未入力
		if ((id.equals("")) && (name.equals("")) && icon == null && icon2 == null && pro.equals("")) {
			ArrayList<UserDTO> user = dbm.allnull();
			session.setAttribute("userlist", user);
			dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			//ログインIDのみ
		} else if (!(id.equals("")) && name.equals("") && icon == null && icon2 == null && pro.equals("")) {
			ArrayList<UserDTO> user = dbm.loginid(id);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//ユーザ名のみ
		} else if (id.equals("") && !(name.equals("")) && icon == null && icon2 == null && pro.equals("")) {
			ArrayList<UserDTO> user = dbm.username(name);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//プロフィールのみ
		} else if (id.equals("") && name.equals("") && icon == null && icon2 == null && !(pro.equals(""))) {
			ArrayList<UserDTO> user = dbm.profile(pro);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//アイコンのみ２つ
		} else if (id.equals("") && name.equals("") && icon != null && icon2 != null && pro.equals("")) {
			ArrayList<UserDTO> user = dbm.icon2();
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//アイコンのみ１つ
		} else if (id.equals("") && name.equals("") && icon != null && icon2 == null && pro.equals("")) {
			ArrayList<UserDTO> user = dbm.icon(icon);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//アイコンのみ１つ
		} else if (id.equals("") && name.equals("") && icon == null && icon2 != null && pro.equals("")) {
			ArrayList<UserDTO> user = dbm.icon(icon2);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//ユーザ名とアイコン１つ
		} else if (id.equals("") && !(name.equals("")) && icon != null && icon2 == null && pro.equals("")) {
			ArrayList<UserDTO> user = dbm.nameicon1(name, icon);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//ユーザ名とアイコン１つ
		} else if (id.equals("") && !(name.equals("")) && icon == null && icon2 != null && pro.equals("")) {
			ArrayList<UserDTO> user = dbm.nameicon1(name, icon2);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//ユーザ名とアイコン２つ
		} else if (id.equals("") && name.equals("") && icon != null && icon2 != null && pro.equals("")) {
			ArrayList<UserDTO> user = dbm.nameicon2(name);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//ユーザ名とプロフィール
		} else if (id == "" && name != "" && icon == null && icon2 == null && pro != "") {
			ArrayList<UserDTO> user = dbm.namepro(name, pro);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//ユーザ名とアイコン1とプロフィール
		} else if (id.equals("") && !(name.equals("")) && icon != null && icon2 == null && !(pro.equals(""))) {
			ArrayList<UserDTO> user = dbm.nameicon1pro(name, icon, pro);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//ユーザ名とアイコン1とプロフィール
		} else if (id.equals("") && !(name.equals("")) && icon == null && icon2 != null && !(pro.equals(""))) {
			ArrayList<UserDTO> user = dbm.nameicon1pro(name, icon2, pro);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//ユーザ名とアイコン2とプロフィール
		} else if (id.equals("") && !(name.equals("")) && icon != null && icon2 != null && !(pro.equals(""))) {
			ArrayList<UserDTO> user = dbm.nameicon2pro(name, pro);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//アイコン1とプロフィール
		} else if (id.equals("") && name.equals("") && icon != null && icon2 == null && !(pro.equals(""))) {
			ArrayList<UserDTO> user = dbm.icon1pro(icon, pro);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//アイコン1とプロフィール
		} else if (id.equals("") && name.equals("") && icon == null && icon2 != null && !(pro.equals(""))) {
			ArrayList<UserDTO> user = dbm.icon1pro(icon2, pro);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//アイコン2とプロフィール
		} else if (id.equals("") && name.equals("") && icon != null && icon2 != null && !(pro.equals(""))) {//アイコン２つ
			ArrayList<UserDTO> user = dbm.icon2pro(pro);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}

			//全入力icon1
		} else if (!(id.equals("")) && !(name.equals("")) && icon != null && icon2 == null
				&& !(pro.equals(""))) {
			ArrayList<UserDTO> user = dbm.searchall(id, icon, name, pro);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//全入力アイコン１
		} else if (!(id.equals("")) && !(name.equals("")) && icon == null && icon2 != null
				&& !(pro.equals(""))) {
			ArrayList<UserDTO> user = dbm.searchall(id, icon2, name, pro);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//全入力アイコン２
		} else if (!(id.equals("")) && !(name.equals("")) && icon != null && icon2 != null
				&& !(pro.equals(""))) {//全入力アイコン２
			ArrayList<UserDTO> user = dbm.searchall2(id, name, pro);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//ログインIDとユーザ名
		} else if (!(id.equals("")) && !(name.equals("")) && icon == null && icon2 == null
				&& (pro.equals(""))) {
			ArrayList<UserDTO> user = dbm.idname(id, name);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//ログインIDとアイコン１
		} else if (!(id.equals("")) && (name.equals("")) && icon != null && icon2 == null
				&& (pro.equals(""))) {
			ArrayList<UserDTO> user = dbm.idicon1(id, icon);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//ログインIDとアイコン１
		} else if (!(id.equals("")) && (name.equals("")) && icon == null && icon2 != null
				&& (pro.equals(""))) {
			ArrayList<UserDTO> user = dbm.idicon1(id, icon2);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//IDとアイコン２
		} else if (!(id.equals("")) && (name.equals("")) && icon != null && icon2 != null
				&& (pro.equals(""))) {
			ArrayList<UserDTO> user = dbm.idicon2(id);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
		//IDとプロフィール
		}else if (!(id.equals("")) && (name.equals("")) && icon == null && icon2 == null
				&& !(pro.equals(""))) {
			ArrayList<UserDTO> user = dbm.idpro(id,pro);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//ID,name,icon1
		}else if (!(id.equals("")) && !(name.equals("")) && icon != null && icon2 == null
				&& (pro.equals(""))) {
			ArrayList<UserDTO> user = dbm.idnameicon1(id,name,icon);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//id,name,icon1
		}else if (!(id.equals("")) && !(name.equals("")) && icon == null && icon2 != null
				&& (pro.equals(""))) {
			ArrayList<UserDTO> user = dbm.idnameicon1(id,name,icon2);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//id,name,icon2
		}else if (!(id.equals("")) && !(name.equals("")) && icon != null && icon2 != null
				&& (pro.equals(""))) {
			ArrayList<UserDTO> user = dbm.idnameicon2(id,name);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//id,name,pro
		}else if (!(id.equals("")) && !(name.equals("")) && icon == null && icon2 == null
				&& !(pro.equals(""))) {
			ArrayList<UserDTO> user = dbm.idnamepro(id,name,pro);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//id,icon,pro
		}else if (!(id.equals("")) && (name.equals("")) && icon != null && icon2 == null
				&& !(pro.equals(""))) {
			ArrayList<UserDTO> user = dbm.idicon1pro(id,icon,pro);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
			//id,icon1,pro
		}else if (!(id.equals("")) && (name.equals("")) && icon == null && icon2 != null
				&& !(pro.equals(""))) {
			ArrayList<UserDTO> user = dbm.idicon1pro(id,icon2,pro);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
		}else {
			ArrayList<UserDTO> user = dbm.idicon2pro(id,pro);
			session.setAttribute("userlist", user);
			if (user.size() == 0) {
				message = "データがありません";
				request.setAttribute("alert", message);

				// 処理の転送先を index.jsp に指定
				dispatcher = request.getRequestDispatcher("./UserSearchInput.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
			}
		}

		dispatcher.forward(request, response);
	}

}
