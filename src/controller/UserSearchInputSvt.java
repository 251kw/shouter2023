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
 * Servlet implementation class UserSearchInputSvt
 */
@WebServlet("/usi")
public class UserSearchInputSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSearchInputSvt() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		RequestDispatcher dispatcher = null;

		// 送信情報の取得
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String[] icon = request.getParameterValues("icon");
		String pro = request.getParameter("pro");

		DBManager dbm = new DBManager();
		HttpSession session = request.getSession();

		int count=0;
		if(icon.length>=0) {
			for(int i=0;i<icon.length;i++) {
				count++;
			}
		}

		//未入力
		if(id=="" && name=="" && icon==null && pro==""){
			ArrayList<UserDTO> user = dbm.allnull();
			session.setAttribute("user", user);
		//ログインIDのみ
		}else if(id!="" && name=="" && icon==null && pro=="") {
			ArrayList<UserDTO> user = dbm.loginid(id);
			session.setAttribute("user", user);
		//ユーザ名のみ
		}else if(id=="" && name!="" && icon==null && pro=="") {
			ArrayList<UserDTO> user = dbm.username(name);
			session.setAttribute("user", user);
		//プロフィールのみ
		}else if(id=="" && name=="" && icon==null && pro!="") {
			ArrayList<UserDTO> user = dbm.profile(pro);
			session.setAttribute("user", user);
		//アイコンのみ
		}else if(id=="" && name=="" && icon!=null && pro=="") {
			if(count==2) {//アイコンが２つ選択
				ArrayList<UserDTO> user = dbm.icon2();
				session.setAttribute("user", user);
			}else if(count==1){//アイコン１つだけ選択*/
				ArrayList<UserDTO> user = dbm.icon(icon[0]);
				session.setAttribute("user", user);
			}
		//ユーザ名とアイコン
		}else if(id=="" && name!="" && icon!=null && pro=="") {
			if(count==1){//アイコン１つだけ選択*/
				ArrayList<UserDTO> user = dbm.nameicon1(name,icon[0]);
				session.setAttribute("user", user);
			}else if(count==2) {//アイコンが２つ選択
				ArrayList<UserDTO> user = dbm.nameicon2(name);
				session.setAttribute("user", user);
			}
		//ユーザ名とプロフィール
		}else if(id=="" && name!="" && icon==null && pro!="") {
			ArrayList<UserDTO> user = dbm.namepro(name,pro);
			session.setAttribute("user", user);
		//ユーザ名とアイコンとプロフィール
		}else if(id=="" && name!="" && icon!=null && pro!="") {
			if(count==1) {//アイコン１つ
				ArrayList<UserDTO> user = dbm.nameicon1pro(name,icon[0],pro);
				session.setAttribute("user", user);
			}else if(count==2) {
				ArrayList<UserDTO> user = dbm.nameicon2pro(name,pro);
				session.setAttribute("user", user);
			}
		//アイコンとプロフィール
		}else if(id=="" && name=="" && icon!=null && pro!="") {
			if(count==1) {//アイコン１つ
				ArrayList<UserDTO> user = dbm.icon1pro(icon[0],pro);
				session.setAttribute("user", user);
			}else if(count==2) {//アイコン２つ
				ArrayList<UserDTO> user = dbm.icon2pro(pro);
				session.setAttribute("user", user);
			}
		//全入力
		}else if(id!="" && name!="" && icon!=null && pro!="") {
			if(count==1) {//全入力アイコン１
				ArrayList<UserDTO> user = dbm.searchall(id,icon[0],name,pro);
				session.setAttribute("user", user);
			}else if(count==2) {//全入力アイコン２
				ArrayList<UserDTO> user = dbm.searchall2(id,name,pro);
				session.setAttribute("user", user);
			}
		}else {
			ArrayList<UserDTO> user = dbm.loginid(id);
			session.setAttribute("user", user);
		}

	// ログインユーザ情報、書き込み内容リストとしてセッションに保存
	//session.setAttribute("user", user);

	dispatcher = request.getRequestDispatcher("./UserSearchResult.jsp");
	dispatcher.forward(request, response);
}

}


		//IDとユーザ名
		//}else if(id!="" && name!="" && icon==null && pro=="") {
			//ArrayList<UserDTO> user = dbm.idname(id,name);//loginIdでいいかも
			//session.setAttribute("user", user);
		//IDとアイコン
		//}else if(id!="" && name=="" && icon!=null && pro=="") {
			//ArrayList<UserDTO> user = dbm.loginid(id);
			//session.setAttribute("user", user);
		//}