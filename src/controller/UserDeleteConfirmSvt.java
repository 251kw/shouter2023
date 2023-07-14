package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
 * Servlet implementation class UserDeleteConfirmSvt
 */
@WebServlet("/udc")
public class UserDeleteConfirmSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteConfirmSvt() {
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


	//検索結果画面で削除ボタン押下時の遷移先
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//フォワードの準備
		RequestDispatcher dispatcher = null;
		DBManager dbm = new DBManager();

		//getDeleteConfirmメソッドで取得したデータを格納するリスト
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		String[] checkBoxes;
		String delete = request.getParameter("delete");
		String cancel = request.getParameter("cancel");


		//削除ボタンが押されたとき
		if(delete !=null) {
			//チェックボタン(loginId)にチェックが入っている行を配列で取得
			checkBoxes = request.getParameterValues("checkbox");

			//チェックせず削除ボタンが押された際のエラーメッセージ
			String emptyMessage = null;

			if(checkBoxes == null) {  //チェックが１つも付いていないとき
				emptyMessage = "チェックをつけて削除ボタンを押下してください。";
				request.setAttribute("alertEmptyCheck", emptyMessage);
				dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");

			}else { //チェックがついているとき
				for(int i=0; i<checkBoxes.length; i++) { //チェックの数だけfor文で回しチェックの付いたデータを取得する
					UserDTO result = dbm.getDeleteConfirm(checkBoxes[i]);

					list.add(result); //UserDTO型のArrayListに保存

					request.setAttribute("deleteConList", list);
					dispatcher = request.getRequestDispatcher("UserDeleteConfirm.jsp");

					//削除する際にLoginIdが必要なため、sessionにセットする
					HttpSession session = request.getSession();
					session.setAttribute("checkBoxes",checkBoxes);
				}
			}
		}

		//削除確認画面でキャンセルボタンを押下したとき
		if(cancel != null) {
			HttpSession session = request.getSession();
			//チェックボックス(loginId)の値をセッションから取得
			String[] checks = (String[]) session.getAttribute("checkBoxes");

			//配列のままだとjspでcontainsメソッドを使用出来ない為、リスト型に変換する
			List<String> checkList = Arrays.asList(checks);
			//リクエストスコープで送信する
			request.setAttribute("checkList", checkList);
			dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");

		}
		//フォワードで転送
		dispatcher.forward(request, response);
	}

}
