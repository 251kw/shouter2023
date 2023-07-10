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
 * Servlet implementation class UserEditConfirmSVT
 */
@WebServlet("/uec")
public class UserEditConfirmSVT extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEditConfirmSVT() {
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
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html:charset=UTF-8");
		//検索条件の受け取り
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String icon_user_female = request.getParameter("icon-user-female");
		String icon_user = request.getParameter("icon-user");
		String icon_bell = request.getParameter("icon-bell");
		String icon_smile = request.getParameter("icon-smile");
		String profile = request.getParameter("profile");
		String e_UserName = request.getParameter("e_UserName");
		//編集内容の受け取り
		String e_Password = request.getParameter("e_Password");
		String e_Icon = request.getParameter("e_Icon");
		String e_Profile = request.getParameter("e_Profile");
		String OK = request.getParameter("OK");
		String edit = request.getParameter("edit");
		//検索条件を遷移先のファイルに渡すための処理
		request.setAttribute("loginId", loginId);
		request.setAttribute("userName", userName);
		request.setAttribute("icon-user-female", icon_user_female);
		request.setAttribute("icon-user", icon_user);
		request.setAttribute("icon-bell", icon_bell);
		request.setAttribute("icon-smile", icon_smile);
		request.setAttribute("profile", profile);
		//編集内容を遷移先のファイルに渡すための処理
		request.setAttribute("e_UserName", e_UserName);
		request.setAttribute("e_Password", e_Password);
		request.setAttribute("e_Icon", e_Icon);
		request.setAttribute("e_Profile", e_Profile);
		request.setAttribute("edit", edit);
		UserDTO editUser = null;
		DBManager db = new DBManager();
		//検索条件に該当するユーザー情報の一覧を取得
		ArrayList<UserDTO> list = db.getUserList(loginId, userName, icon_user_female, icon_user, icon_bell,
				icon_smile, profile);
		//編集対象のデータの抽出
		editUser = list.get(Integer.parseInt(edit));
		//編集対象のデータを遷移先のファイルに送るための処理
		request.setAttribute("editUser", editUser);
		RequestDispatcher dispatcher = null;

		if (OK != null) {//OKボタンが押された時の処理
			db.editUser(editUser, e_UserName, e_Password, e_Icon, e_Profile);
			dispatcher = request.getRequestDispatcher("userEditResult.jsp");
		} else {//キャンセルボタンが押された時の処理
			dispatcher = request.getRequestDispatcher("userEditInput.jsp");
		}
		dispatcher.forward(request, response);
	}

}
