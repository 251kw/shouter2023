package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import check.Check_func;
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//打ち込まれた内容を受け取る
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String icon = request.getParameter("icon");
		String prof = request.getParameter("prof");

		UserDTO user = new UserDTO(loginId,password,userName,icon,prof);
		//
		request.setAttribute("user", user);
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();
		UserDTO olduser=(UserDTO)session.getAttribute("olduser");
		String oldId =olduser.getLoginId();
		int flag=0;//何個エラー処理を通ったかのフラグ

		String message_null = null;
		String message_id=null;
		String message_space=null;
		String message_db = null;
		String message_con =null;
		String message_elev=null;
		String message_thou=null;
		String message_same=null;

		String regex_AlphaNum = "^[A-Za-z0-9 ]+$";




		if(Check_func.checkEmpty(loginId,password,userName)) {
			// プロフィール以外の項目が未入力の場合
			message_null = "全て必須入力です";
			request.setAttribute("alert_null", message_null);
			flag++;
		}if(!Check_func.checkLogic(regex_AlphaNum, loginId)){
			//ログインIDに半角英数字以外が含まれる場合
			message_id="ログインIDは半角英数字でご記入ください。";
			request.setAttribute("alert_id", message_id);
			flag++;
		}if(Check_func.checkSpace(loginId) || Check_func.checkSpace(password) || Check_func.checkSpace(userName)) {
			//空白のみの項目があった場合
			message_space="空白のみをうちこむことは出来ません。";
			request.setAttribute("alert_space", message_space);
			flag++;
		}if(Check_func.checkContain(loginId,password)) {
			message_con="ログインIDとパスワードにスペースを使用することはできません。";
			request.setAttribute("alert_con", message_con);
			flag++;
		}if(Check_func.countElev(loginId) || Check_func.countElev(userName) || Check_func.countElev (password)) {
			message_elev="ログインID、ユーザー名、パスワードは10文字以内です。";
			request.setAttribute("alert_elev", message_elev);
			flag++;
		}if(Check_func.countThou(prof)){
			message_thou="プロフィールは100文字以内です。";
			request.setAttribute("alert_thou", message_thou);
			flag++;
		}if(Check_func.checkSame(user,olduser)){
			message_same="ユーザー情報を変更してください。";
			request.setAttribute("alert_same", message_same);
			flag++;
		}
		if(flag==0){//エラー処理を一度も通ってなければDBに接続してログインIDチェック
			// 処理を転送
			DBManager dbm = new DBManager();
			if(!oldId.equals(loginId) && dbm.getIdUser(loginId)) {
				message_db="ログインID:"+loginId+"は使用できません。";
				request.setAttribute("alert_db", message_db);
				dispatcher = request.getRequestDispatcher("UserEditInput.jsp");

			}else{
				//ログインIDも大丈夫であれば次のjspに飛ばす。
				dispatcher = request.getRequestDispatcher("UserEditConfirm.jsp");

			}
		}else {//エラー処理を通っていればindex.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("UserEditInput.jsp");
		}
		dispatcher.forward(request, response);

	}

}
