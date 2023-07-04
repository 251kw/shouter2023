package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.UserDTO;

@WebServlet("/uii")
public class UserInfoInputSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserInfoInputSvt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isHalfNumeric(String loginPass) {  //半角であった場合true
		return java.util.regex.Pattern.matches("^[A-Za-z0-9]+$", loginPass);

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	//UserInfoInput.jspからpostで受け取り値入力チェック
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 登録画面フォームから送信された情報の取得
		String loginId = request.getParameter("loginId"); //name属性値の値を指定しgetParameterで値の取得
		String userName = request.getParameter("userName").trim();
		String password = request.getParameter("password");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");
		int userNameLength = userName.length()-1; //最後の空文字

		//フォワードの準備
		RequestDispatcher dispatcher = null;

		//alertメッセージの変数を準備
		String emptyMessage = null; //未入力の場合
		String halfMessage = null; //半角の場合
		String fullMessage = null; //全角の場合

		//入力チェック（未入力の場合）
		if (loginId.equals("") || userName.equals("") || password.equals("") || profile.equals("") || icon == null) {
			// ログインID かパスワードどちらか、もしくは両方とも空白の場合
			emptyMessage = "すべて必須入力項目です";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alertEmpty", emptyMessage); //alertという属性値でmessageを格納

			//画面遷移　フォワードなので処理終了後に呼び出し元に戻らない
			dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
		}

		//半角スペースが含まれている場合
		if (loginId.contains(" ") || userName.contains(" ") || password.contains(" ") || profile.contains(" ")) {
			halfMessage = "入力フォームに半角の空文字が入力されています";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alertHalf", halfMessage); //alertという属性値でmessageを格納

			//画面遷移　フォワードなので処理終了後に呼び出し元に戻らない
			dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
		}


		//全角スペースが含まれている場合 ユーザー名の先頭に全角が入ってる場合もチェック
		if (loginId.contains("　") ||  password.contains("　") || profile.contains("　") ||!userName.equals("") && userName.substring(0, 1).equals("　")) {
			fullMessage = "入力フォームに全角の空文字が入力されています";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alertFull", fullMessage); //alertという属性値でmessageを格納

			//画面遷移　フォワードなので処理終了後に呼び出し元に戻らない
			dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
		}

		//正常に入力されている場合
		if (!(loginId.equals("") || userName.equals("") || password.equals("") || profile.equals("") || icon == null ||
			  loginId.contains(" ") || userName.contains(" ") || password.contains(" ")|| profile.contains(" ") ||
			  loginId.contains("　") || userName.substring(0, 1).equals("　") || password.contains("　") || profile.contains("　"))) {

			// DBManagerのインスタンス化
			DBManager dbm = new DBManager();

			//loginIdの重複チェック
			boolean res = dbm.checkLoginId(loginId);

			//loginIdの半角英数字チェック
			boolean numericRes = isHalfNumeric(loginId);
			//passwordの半角英数字チェック
			boolean passNumericRes = isHalfNumeric(password);


			String loginIdMessage = null; //loginIdが存在する際の表示メッセージ
			String numLoginIdMessage = null; //半角英数字出ない場合に表示するメッセージ
			String passwordMessage = null;

			if (res == true) { //同じloginIdが存在している場合
				loginIdMessage = "このログインIDはすでに使用されています。";

				request.setAttribute("alertLoginId", loginIdMessage);
				dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");

			} else if(numericRes == false ) { //半角英数字以外の場合
				numLoginIdMessage = "ログインIDは半角英数字を入力してください";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alertNumLoginId", numLoginIdMessage);
				dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");

			}else {
				//ユーザーオブジェ クトのインスタンス化とコンストラクタの呼び出し
				UserDTO user = new UserDTO(loginId, password, userName, icon, profile);

				//Userオブジェクトをリクエストに登録
				request.setAttribute("user", user); //属性値userで値userをrequestスコープに設定する。
				dispatcher = request.getRequestDispatcher("UserInfoConfirm.jsp");
			}

			//パスワードの半角チェック
			if(passNumericRes == false) { //全角だった場合
				passwordMessage = "パスワードは半角英数字で入力してください";
				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alertPassword",passwordMessage );
				dispatcher = request.getRequestDispatcher("UserInfoInput.jsp");
			}
		}

		// ログインの有無にかかわらずgetRequestDispatcherで指定したjspファイルにフォワード
		dispatcher.forward(request, response);
	}

}
