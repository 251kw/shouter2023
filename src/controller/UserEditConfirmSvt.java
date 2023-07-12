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
 * Servlet implementation class UserEditConfirmSvt
 */
@WebServlet("/uec")
public class UserEditConfirmSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEditConfirmSvt() {
        super();
        // TODO Auto-generated constructor stub
    }

    //半角英数字かのチェックメソッド
  	public boolean isHalfNumeric(String loginPass) { //半角であった場合true
  		return java.util.regex.Pattern.matches("^[A-Za-z0-9]+$", loginPass);
  	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	//入力値チェック
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 登録画面フォームから送信された情報の取得
		String loginId = request.getParameter("loginId"); //name属性値の値を指定しgetParameterで値の取得
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");
		int updateResult;

		//フォワードの準備
		RequestDispatcher dispatcher = null;

		//DBの準備
		DBManager dbm = new DBManager();

		//更新の際に変更前のLoginIdが必要なため、sessionにセットする
		HttpSession session = request.getSession();
		String oldLoginId = (String)session.getAttribute("oldLoginId");

		//ユーザー情報の更新
		updateResult = dbm.editProfile(loginId, userName, password, icon, profile, oldLoginId);

		if(updateResult !=0) {
			//新しい情報の抽出
			UserDTO updateUser = dbm.getShowResult(loginId);

			//パラメーターにセット
			request.setAttribute("updateUser", updateUser); //属性値userで値userをrequestスコープに設定する。
			dispatcher = request.getRequestDispatcher("UserEditResult.jsp");

			//フォワードで転送
			dispatcher.forward(request, response);
		}
	}
}
