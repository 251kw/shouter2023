package controller;

import java.io.IOException;

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
 * Servlet implementation class UserInfoConfirmSvt
 */
@WebServlet("/usr")
public class UserSearchResultSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 半角英数チェック
	 *
	 * @return true 正常(半角英数のみ)  false エラー(半角英数以外が入ってる)
	 */
	public static boolean isHanStr(String s){
		if (!s.matches("^[0-9a-zA-Z]+$")) {
			return false;
		}
		return true;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String param = "";
		String checkbox = request.getParameter("all");
		if(checkbox != null) {
			if(checkbox.equals("allcheck")) {
				param = "checked";
				request.setAttribute("true", param);
			}
			else if(checkbox.equals("allout")){
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
			dispatcher.forward(request, response);
		}

		//編集ボタンを押すと、editというnameでそのユーザのログインIDが返ってくる
		String edituserId = request.getParameter("edit");
		if(edituserId != null) {

			//現時点のIDは更新の時に使うので、セッションで保持(UserEditInputSvt.java)
			HttpSession session = request.getSession();
			session.setAttribute("edituserId", edituserId);

			//DBからloginIdで編集ボタンがおされたユーザーを検索する
			DBManager dbm = new DBManager();
			UserDTO edituser = dbm.getNewuser(edituserId);
			request.setAttribute("edit", edituser);

			// 問題なければ、処理の転送先を UserEditInput.jsp に指定。確認画面表示される。
			RequestDispatcher dispatcher = request.getRequestDispatcher("UserEditInput.jsp");
			dispatcher.forward(request, response);
		}
	}

}
