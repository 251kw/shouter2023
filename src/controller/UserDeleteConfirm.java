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
 * Servlet implementation class UserDeleteConfirm
 */
@WebServlet("/udc")
public class UserDeleteConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDeleteConfirm() {
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
	//UserSearchResult.jspの削除ボタンから呼ばれる
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		RequestDispatcher dispatcher = null;
		String message = null;

		//削除ボタンのパラメータを受け取る
		String deluser = request.getParameter("delete");
		if (deluser!=null) {

			//選択されたチェックボックスのデータを取得し、変数checkeduserに代入する。チェックされているユーザのloginIdがvalueでとれる。
			String[] checkeduser = request.getParameterValues("check");

			if(checkeduser==null) {
				//checkeduserの中身が空だったらアラートを設定。エラーメッセージをリクエストオブジェクトに保存。
				message = "削除したいユーザを１件以上選択してください";
				request.setAttribute("delalert", message);
				//UserSearchResult.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
			}
			else {
				DBManager dbm = new DBManager();
				ArrayList<UserDTO> userlist = new ArrayList<>();

				//loginIdを引数にユーザを検索するgetNewuserメソッドを呼び出し、検索されたユーザをリストに追加(checkeduseリストrの長さ分繰り返す)
				for(int i= 0; i<checkeduser.length; i++){
					    String loginId = checkeduser[i];
					    userlist.add(dbm.getNewuser(loginId));
				}
				//リストをリクエストスコープに登録
				request.setAttribute("deletelist", userlist);

				//loginIdのリストだけセッションに保存しておく（udrサーブレットで削除メソッドを呼び出すときに必要になるので）
				HttpSession session = request.getSession();
				session.setAttribute("keepLogId", checkeduser);

				//UserDeleteConfirm.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("UserDeleteConfirm.jsp");
			}
		}
		dispatcher.forward(request, response);
	}
}


