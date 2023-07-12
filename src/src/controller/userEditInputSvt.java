package src.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.dto.UserDTO;

/**
 * Servlet implementation class userEditInputSvt
 */
@WebServlet("/userEditInputSvt")
public class userEditInputSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public userEditInputSvt() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");
		RequestDispatcher dispatcher = null;
		String message2 = null;
		String message3 = null;
		String message4 = null;


		while(password.equals("") || userName.equals("") || icon.equals("") || profile.equals("")) {
			if (password.equals("")) {
				message2 = "パスワードは必須入力です";
			}
			if (userName.equals("")) {
				message3 = "ユーザー名は必須入力です";
			}
			if (profile.equals("")) {
				message4 = "プロフィールは必須入力です";
			}
			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert2", message2);
			request.setAttribute("alert3", message3);
			request.setAttribute("alert4", message4);
			// touroku.jsp に処理を転送
			dispatcher = request.getRequestDispatcher("userEditInput.jsp");
			dispatcher.forward(request, response);
			break;
		}


		if (!loginId.equals("") || password.equals("") || userName.equals("") || icon.equals("")
				|| profile.equals("")) {
			// ユーザ情報を取得し、確認画面へ
			UserDTO ID = new UserDTO(loginId, password, userName, icon, profile);
			HttpSession session = request.getSession();
			session.setAttribute("ID", ID);
			dispatcher = request.getRequestDispatcher("userEditConfirm.jsp");
			dispatcher.forward(request, response);

		}
	}
}

