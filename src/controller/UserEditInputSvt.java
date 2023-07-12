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
 * Servlet implementation class UserEditInputSvt
 */
@WebServlet("/uei")
public class UserEditInputSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEditInputSvt() {
        super();
        // TODO Auto-generated constructor stub
    }


    //半角英数字かのチェックメソッド
  	public boolean isHalfNumeric(String loginPass) { //半角であった場合true
  		return java.util.regex.Pattern.matches("^[A-Za-z0-9]+$", loginPass);
  	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


//送信ボタンを押されたとき
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 登録画面フォームから送信された情報の取得
		String loginId = request.getParameter("loginId"); //name属性値の値を指定しgetParameterで値の取得
		String userName = request.getParameter("userName").trim();
		String password = request.getParameter("password");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		//フォワードの準備
		RequestDispatcher dispatcher = null;

		//セッションの接続
		HttpSession session = request.getSession();

		//編集ボタンを押下際に登録しているloginIdを取得
		String myLoginId = (String) session.getAttribute("oldLoginId");


		//alertメッセージの変数を準備
		String emptyMessage = null; //未入力の場合
		String halfMessage = null; //半角の場合
		String fullMessage = null; //全角の場合
		String doubleMessage  = null; //loginIdの重複チェック

		//入力チェック（未入力の場合）
		if (loginId.equals("") || userName.equals("") || password.equals("") || profile.equals("") || icon == null) {
			// ログインID かパスワードどちらか、もしくは両方とも空白の場合
			emptyMessage = "すべて必須入力項目です";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alertEmpty", emptyMessage); //alertという属性値でmessageを格納

			//画面遷移　フォワードなので処理終了後に呼び出し元に戻らない
			dispatcher = request.getRequestDispatcher("UserEditInput.jsp");
		}


		//半角スペースが含まれている場合
		if (loginId.contains(" ") || userName.contains(" ") || password.contains(" ") || profile.contains(" ")) {
			halfMessage = "入力フォームに半角の空文字が入力されています";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alertHalf", halfMessage); //alertという属性値でmessageを格納

			//画面遷移　フォワードなので処理終了後に呼び出し元に戻らない
			dispatcher = request.getRequestDispatcher("UserEditInput.jsp");
		}


		//全角スペースが含まれている場合 ユーザー名の先頭に全角が入ってる場合もチェック
		if (loginId.contains("　") ||  password.contains("　") || profile.contains("　") ||!userName.equals("") && userName.substring(0, 1).equals("　")) {
			fullMessage = "入力フォームに全角の空文字が入力されています";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alertFull", fullMessage); //alertという属性値でmessageを格納

			//画面遷移　フォワードなので処理終了後に呼び出し元に戻らない
			dispatcher = request.getRequestDispatcher("UserEditInput.jsp");
		}


		//正常に入力されている場合
		if (!(loginId.equals("") || userName.equals("") || password.equals("") || profile.equals("") || icon == null ||
			  loginId.contains(" ") || userName.contains(" ") || password.contains(" ")|| profile.contains(" ") ||
			  loginId.contains("　") || userName.substring(0, 1).equals("　") || password.contains("　") || profile.contains("　"))) {

			DBManager dbm = new DBManager();

			//loginIdの半角英数字チェック
			boolean numericRes = isHalfNumeric(loginId);
			//ログインIDの重複チェック
			boolean checkLoginId = dbm.checkLoginId(loginId);
			//passwordの半角英数字チェック
			boolean passNumericRes = isHalfNumeric(password);

			//エラーメッセージ
			String numLoginIdMessage = null; //半角英数字以外の文字が入力された際のメッセージ
			String passwordMessage = null; //パスワードに半角英数字以外の文字が入力された際のメッセージ


			//ログインID半角チェック
			if(numericRes == false ) {
				numLoginIdMessage = "ログインIDは半角英数字を入力してください";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alertNumLoginId", numLoginIdMessage);
				dispatcher = request.getRequestDispatcher("UserEditInput.jsp");

			}else if((checkLoginId == true) && !(myLoginId.equals(loginId))) {  //検索し同じログインIDが存在した場合 しかし自分のログインIDは許容する
				doubleMessage = "同じログインIDが存在します。";
				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alertdoubleLoginId", doubleMessage);
				dispatcher = request.getRequestDispatcher("UserEditInput.jsp");

			}else {
				//ユーザーオブジェ クトのインスタンス化とコンストラクタの呼び出し
				UserDTO user = new UserDTO(loginId, password, userName, icon, profile);

				//Userオブジェクトをリクエストに登録
				request.setAttribute("user", user); //属性値userで値userをrequestスコープに設定する。
				dispatcher = request.getRequestDispatcher("UserEditConfirm.jsp");
			}

			//パスワードの半角チェック
			if(passNumericRes == false) { //全角だった場合
				passwordMessage = "パスワードは半角英数字で入力してください";
				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alertPassword",passwordMessage );
				dispatcher = request.getRequestDispatcher("UserEditInput.jsp");

			}
		}

		// ログインの有無にかかわらずgetRequestDispatcherで指定したjspファイルにフォワード
		dispatcher.forward(request, response);
	}
}
