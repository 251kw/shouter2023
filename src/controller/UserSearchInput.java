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
 * Servlet implementation class UserSearchInput
 */
@WebServlet("/usi")
public class UserSearchInput extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSearchInput() {
        super();
        // TODO Auto-generated constructor stub
    }

    //半角英数字かのチェックメソッド
	public boolean isHalfNumeric(String loginPass) {  //半角であった場合true
		return java.util.regex.Pattern.matches("^[A-Za-z0-9]+$", loginPass);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	//検索ボタンが押された後
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//getPatameterで受け取った変数宣言
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String[] icon = request.getParameterValues("icon");
		String profile = request.getParameter("profile");
		boolean loginIdResult = true;

		//フォワードの準備
		RequestDispatcher dispatcher = null;

		//alertメッセージ変数
		String loginIdMessage = null;
		String empResultMessage = null;

		//loginId
		if(!loginId.equals("")) {
			loginId = request.getParameter("loginId");
			loginIdResult = isHalfNumeric(loginId);
			//loginId入力チェック
				if(loginIdResult == false) {
					loginIdMessage = "ログインIDは半角英数字を入力してください";

					// エラーメッセージをリクエストオブジェクトに保存
					request.setAttribute("alertNumLoginId", loginIdMessage);
					dispatcher = request.getRequestDispatcher("UserSearchInput.jsp");
				}
		}

		//userName
		if(!userName.equals("")) {
			userName = request.getParameter("userName");
		}

		//icon
		if(icon != null) {
			icon = request.getParameterValues("icon");
		}

		//profile
		if(!profile.equals("")) {
			profile = request.getParameter("profile");
		}

		//入力値にエラーがない場合
			HttpSession session = request.getSession();
			DBManager dbm =  new DBManager();

			//DB検索メソッド
			ArrayList<UserDTO> list =dbm.getSearchList(loginId, userName, icon, profile);

			//検索結果が0件の場合
			if(list.size() ==0) {
				empResultMessage = "検索結果は0件です。";
				request.setAttribute("alertEmpResult",empResultMessage );
				dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");

			//検索結果がある場合
			}else {
				request.setAttribute("searchResult", list); //属性値userで値userをrequestスコープに設定する。
				dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
				// リストをセッションに保存
				session.setAttribute("user", list);
			}


		//フォワードで転送
		dispatcher.forward(request, response);














	}

}
