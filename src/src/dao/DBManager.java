package src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import src.dto.ShoutDTO;
import src.dto.UserDTO;

public class DBManager extends SnsDAO {

	// ログインID とパスワードを受け取り、登録ユーザ一覧に一致したものがあるか検索
	public UserDTO getLoginUser(String loginId, String password) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		String sql = "SELECT * FROM users WHERE loginId=? AND password=?";
		UserDTO user = null; // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql); // SELECT 構文登録
			pstmt.setString(1, loginId);
			//pstmt.setString(2, username);
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
				shout.setDate(rset.getString(4));
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pstmt.setString(3, sdf.format(calender.getTime()));
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

	// 登録ユーザ情報↓
	public boolean setUser(UserDTO user) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			// INSERT 文の登録と実行
			String sql = "INSERT INTO users(loginId,password,userName, icon,profile) VALUES(?, ?, ?, ?,?)";
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

	//ログインID重複確認
	public String Logincheck(String loginId) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果
		String result = "false";//重複なし
		String sql = "SELECT * FROM users WHERE loginId=?";

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql); // SELECT 構文登録
			pstmt.setString(1, loginId);
			rset = pstmt.executeQuery();

			// 検索結果があれば
			if (rset.next()) {
				result = "true";//重複あり
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

	/*// 完全一致
	public UserDTO getSearchUserlogin(String loginId) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		String sql = "SELECT * FROM users WHERE loginId =?";
		UserDTO user = null; // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql); // SELECT 構文登録
			pstmt.setString(1, loginId);
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

	// 部分一致
	public UserDTO getSearchUsername(String userName) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		String sql = "SELECT * FROM users WHERE userName like ?";
		UserDTO user = null; // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql); // SELECT 構文登録
			pstmt.setString(1, "%" + userName + "%");
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

	/*
	// 部分一致
	public UserDTO getSearchUserprof(String profile) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		String sql = "SELECT * FROM users WHERE profile like ?";
		UserDTO user = null; // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql); // SELECT 構文登録
			pstmt.setString(1, "%" + profile + "%");
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

	// OR検索
		public UserDTO getSearchUsericon(String icon) {
			Connection conn = null; // データベース接続情報
			PreparedStatement pstmt = null; // SQL 管理情報
			ResultSet rset = null; // 検索結果

			String sql = "SELECT * FROM users WHERE icon = icon-users OR icon =icon-smile";
			UserDTO user = null; // 登録ユーザ情報

			try {
				// データベース接続情報取得
				conn = getConnection();

				// SELECT 文の登録と実行
				pstmt = conn.prepareStatement(sql); // SELECT 構文登録
				pstmt.setString(1, icon);
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
		}*/
	// 複数表示
	public ArrayList<UserDTO> getUserList(String loginId, String userName, String icon, String icon2, String profile) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		ArrayList<String> arraylist = new ArrayList<>();

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();

			String sql = "SELECT * FROM users";

			if ((loginId != "") || (userName != "") || (icon != null) || (icon2 != null) || (profile != "")) {//入力されているとき

				//where文
				if (!loginId.equals("")) {
					// SELECT 文の実行
					arraylist.add("loginId =" + "'" + loginId + "'");
				}
				//where文
				if (!userName.equals("")) {
					// SELECT 文の実行
					arraylist.add("userName like '%" + userName + "%'");
				}
				//where文
				if ((icon != null) && (icon2 != null)) {
					arraylist.add("icon = " + "'" + icon + "'" + " OR " + "icon = " + "'" + icon2 + "'");
				} else {
					if (icon != null) {
						arraylist.add("icon = " + "'" + icon + "'");
					}
					if (icon2 != null) {
						arraylist.add("icon = " + "'" + icon2 + "'");
					}
				}
				if (!profile.equals("")) {
					arraylist.add("profile like '%" + profile + "%'");
				}

				sql += " WHERE " + arraylist.get(0);
				for (int i = 1; i < arraylist.size(); i++) {
					sql += " AND " + arraylist.get(i);
				}

				pstmt = conn.prepareStatement(sql); // SELECT 構文登録
				rset = pstmt.executeQuery();//実行

				// 検索結果の数だけ繰り返す
				while (rset.next()) {
					// 必要な列から値を取り出し、書き込み内容オブジェクトを生成
					UserDTO user = new UserDTO();
					user.setLoginId(rset.getString(2));
					user.setUserName(rset.getString(4));
					user.setIcon(rset.getString(5));
					user.setProfile(rset.getString(6));
					// 書き込み内容をリストに追加
					list.add(user);
				}
			}

			if (loginId.equals("") && userName.equals("") && icon == (null) && icon2 == (null)&& profile.equals("")) {//未入力のとき
				// SELECT 文の実行
				sql += ";";

				pstmt = conn.prepareStatement(sql); // SELECT 構文登録
				rset = pstmt.executeQuery();

				// 検索結果の数だけ繰り返す
				while (rset.next()) {
					// 必要な列から値を取り出し、書き込み内容オブジェクトを生成
					UserDTO user = new UserDTO();
					user.setLoginId(rset.getString(2));
					user.setUserName(rset.getString(4));
					user.setIcon(rset.getString(5));
					user.setProfile(rset.getString(6));
					// 書き込み内容をリストに追加
					list.add(user);
				}
			}

		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

		return list;
	}
}