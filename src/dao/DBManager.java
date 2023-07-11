package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import check.Check_func;
import dto.ShoutDTO;
import dto.UserDTO;

public class DBManager extends SnsDAO {

	// ログインID とパスワードを受け取り、登録ユーザ一覧に一致したものがあるか検索
	public UserDTO getLoginUser(String loginId, String password) {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果

		String sql = "SELECT * FROM users WHERE loginId=? AND password=?";
		UserDTO user = null;    // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);	// SELECT 構文登録
			pstmt.setString(1, loginId);
			pstmt.setString(2, password);
			rset = pstmt.executeQuery();

			// 検索結果があれば
			if (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return user;
	}

	// 書き込み内容リストの getter
	public ArrayList<ShoutDTO> getShoutList() {
		Connection conn = null;
		Statement pstmt = null;
		ResultSet rset = null;

		ArrayList<ShoutDTO> list = new ArrayList<ShoutDTO>();

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();
			pstmt = conn.createStatement();

			// SELECT 文の実行
			String sql = "SELECT * FROM shouts ORDER BY date DESC";
			rset = pstmt.executeQuery(sql);

			// 検索結果の数だけ繰り返す
			while (rset.next()) {
				// 必要な列から値を取り出し、書き込み内容オブジェクトを生成
				ShoutDTO shout = new ShoutDTO();
				shout.setUserName(rset.getString(2));
				shout.setIcon(rset.getString(3));
				shout.setDate(rset.getString(4).substring(0, rset.getString(4).length()-2));//文字列の下二桁を削る。
				shout.setWriting(rset.getString(5));

				// 書き込み内容をリストに追加
				list.add(shout);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return list;
	}

	// ログインユーザ情報と書き込み内容を受け取り、リストに追加する
	public boolean setWriting(UserDTO user, String writing) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			// INSERT 文の登録と実行
			String sql = "INSERT INTO shouts(userName, icon, date, writing) VALUES(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getIcon());
			Calendar calender = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String date=sdf.format(calender.getTime());
			pstmt.setString(3, date);
			pstmt.setString(4, writing);

			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				// INSERT 文の実行結果が１なら登録成功
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(pstmt);
			close(conn);
		}

		return result;
	}

	//ユーザー情報を受け取りDBに登録する。
	public boolean setUser(UserDTO user) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			// INSERT 文の登録と実行
			String sql = "INSERT INTO users(loginId,password,userName,icon,profile) VALUES(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getLoginId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getIcon());
			pstmt.setString(5, user.getProfile());

			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				// INSERT 文の実行結果が１なら登録成功
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(pstmt);
			close(conn);
		}

		return result;
	}

	public boolean getIdUser(String loginId) {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果
		boolean result =false;

		String sql = "SELECT * FROM users WHERE loginId=?";

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);	// SELECT 構文登録
			pstmt.setString(1, loginId);
			rset = pstmt.executeQuery();

			// 検索結果があれば
			if (rset.next()) {
				//結果にtrueを返す
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return result;
	}

	public ArrayList<UserDTO> SerchUser(String loginId, String userName, String[] icons, String prof) {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果

		String sql = "SELECT * FROM users ";
		UserDTO user = null;    // 登録ユーザ情報
		ArrayList<UserDTO> users = new ArrayList<UserDTO>();
		ArrayList<String> sqls = new ArrayList<String>();

		if(!(Check_func.checkEmptyAll(loginId, prof, userName) && icons==null)) {
			sqls.add("where ");
		}
		if(loginId!=null && loginId!="") {
			sqls.add("loginId = '" + loginId + "' ");
		}
		if(userName!=null && userName!="") {
			sqls.add("userName like '%" + userName + "%' ");
		}
		if(prof!=null && prof!="") {
			sqls.add("profile like '%" + prof + "%' ");
		}
		if(icons!=null) {
			String icon_str = "(icon = '" + icons[0] + "'";
			for(int i = 1; i <icons.length ; i++) {
				icon_str = icon_str+ " or icon = '" + icons[i] + "' ";
			}
			icon_str=icon_str+")";
			sqls.add(icon_str);
		}

		if (sqls.size()!=0) {
			sql = sql + sqls.get(0) + sqls.get(1);
			for(int i = 2 ; i < sqls.size(); i++) {
				sql = sql + " and " + sqls.get(i);
			}
		}


		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);	// SELECT 構文登録
			rset = pstmt.executeQuery();

			// 検索結果があれば
			while (rset.next()) {
				// 必要な列から値を取り出し、ユーザ情報オブジェクトを生成
				user = new UserDTO();
				user.setLoginId(rset.getString("loginId"));
				user.setPassword(rset.getString("password"));
				user.setUserName(rset.getString("userName"));
				user.setIcon(rset.getString("icon"));
				user.setProfile(rset.getString("profile"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return users;
	}

	//更新前後の情報を比べて、更新されている値だけupdate文に追加する。
	public int updateUser(UserDTO olduser,UserDTO user) {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		int rset = 0;             // 検索結果

		String sql = "UPDATE users SET";//
		ArrayList<String> sqls = new ArrayList<String>();

		if(!user.getLoginId().equals(olduser.getLoginId())){
			sqls.add(" loginId= '"+user.getLoginId()+"'");
		}if(!user.getUserName().equals(olduser.getUserName())){
			sqls.add(" userName='"+user.getUserName()+"'");
		}if(!user.getPassword().equals(olduser.getPassword())){
			sqls.add(" password='"+user.getPassword()+"'");
		}if(!user.getIcon().equals(olduser.getIcon())){
			sqls.add(" Icon='"+user.getIcon()+"'");
		}if(!user.getProfile().equals(olduser.getProfile())){
			sqls.add(" profile='"+user.getProfile()+"'");
		}

		sql = sql + sqls.get(0);
		for(int i = 1 ; i < sqls.size(); i++) {
			sql = sql + "," + sqls.get(i);
		}
		sql = sql + " where loginId = '"+olduser.getLoginId()+"'";


		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);	// SELECT 構文登録
			rset = pstmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(pstmt);
			close(conn);
		}

		return rset;
	}

	public int deleteUser(ArrayList<String>  Ids) {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		int rset = 0;             // 検索結果

		String sql = "DELETE FROM users WHERE";//
		ArrayList<String> sqls = new ArrayList<String>();
		for(String loginId:Ids) {
			sqls.add(loginId);
		}

		if(sqls.size()<=0) {
			return 0;
		}

		sql = sql + " loginId = '"+ sqls.get(0)+"'";
		for(int i = 1 ; i <sqls.size() ; i++) {
			sql = sql + " or loginId = '"+sqls.get(i)+"'";
		}
		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);	// SELECT 構文登録
			rset = pstmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(pstmt);
			close(conn);
		}

		return rset;

	}
}
