package practiceF;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;

/**
 * Servlet implementation class UserlnfolnputSvt
 */
@WebServlet("/uii")
public class UserInfoInputSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfoInputSvt() {
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
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String noEnterd = null;
		String duplication = null;
		String blank = null;
		String halfSize = null;

		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		User user = new User(loginId, password, userName, icon, profile);

		DBManager dbm = new DBManager();

		request.setAttribute("User", user);

		ServletContext sc = getServletContext();

		if (loginId.equals("") || password.equals("") || userName.equals("") || profile.equals("")) {
			noEnterd = "未入力です";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("noEnterd", noEnterd);
		}

		if (dbm.idCheck(loginId)) {
			duplication = "ログインIDが重複してます";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("duplication", duplication);
		}

		if (loginId.contains(" ") || loginId.contains("　") || password.contains(" ") || password.contains("　")) {
			blank = "空白が入力されています";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("blank", blank);
		}

		if (!loginId.matches("^[A-Za-z0-9]+$")) {
			halfSize = "半角英数字で入力してください";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("halfSize", halfSize);
		}

		if (noEnterd != null || duplication != null || blank != null || halfSize != null) {
			RequestDispatcher rd = sc.getRequestDispatcher("/UserInfoInput.jsp");
			rd.forward(request, response);
		}

		RequestDispatcher rd = sc.getRequestDispatcher("/UserInfoConfirm.jsp");
		rd.forward(request, response);
	}
}
