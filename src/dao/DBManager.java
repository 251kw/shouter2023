package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dto.ShoutDTO;
import dto.UserDTO;

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
			} else {
				System.out.println("叫びは必須項目です。");//叫びの必須チェック
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

	public boolean setUserInformation(UserDTO user) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			String sql = "INSERT INTO users(loginId, password, userName, icon, profile) VALUES (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getLoginId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getIcon());
			pstmt.setString(5, user.getProfile());

			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}

		return result;

	}

	public boolean searchDB(String login) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean result = false;
		try {
			conn = getConnection();

			String sql = "SELECT * FROM users WHERE loginId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		return result;
	}

	public ArrayList<UserDTO> getUserList(String loginId, String userName, String icon_user_female, String icon_user,
			String icon_bell, String icon_smile, String profile) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		//String[] iconArray= {icon_user_female,icon_user,icon_bell,icon_smile};

		ArrayList<UserDTO> list = new ArrayList<UserDTO>();

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();

			// SELECT 文の実行
			String sql = "SELECT * FROM users";
			String sqlCondition = "";
			if (loginId != "" || userName != "" || profile != "" || icon_user_female != null || icon_user != null
					|| icon_bell != null || icon_smile != null) {//検索条件が選択されている場合
				sqlCondition = sql + " WHERE";
				if (loginId != "") {//ログインIDが指定されている場合
					sqlCondition += " loginId=" + "'" + loginId + "'";
				}
				if (userName != "") {//ユーザー名が指定されている場合
					if (loginId != "") {
						sqlCondition += " AND";
					}
					sqlCondition += " userName LIKE('%" + userName + "%')";
				}
				if (profile != "") {//プロフィールが指定されている場合
					if (userName != "" || loginId != "") {
						sqlCondition += " AND";
					}
					sqlCondition += " profile LIKE('%" + profile + "%')";
				}
				if (icon_smile != null || icon_user != null || icon_user_female != null || icon_bell != null) {//アイコンが指定されている場合
					if (loginId != "" || userName != "" || profile != "") {
						sqlCondition += " AND";
					}
					sqlCondition += " icon IN(";
					if (!(icon_smile == null)) {
						sqlCondition += "'icon-smile'";
					}
					if (icon_user != null) {
						if (icon_smile != null) {
							sqlCondition += ",";
						}
						sqlCondition += "'icon-user'";
					}
					if (icon_user_female != null) {
						if (icon_user != null || icon_smile != null) {
							sqlCondition += ",";
						}
						sqlCondition += "'icon-user-female'";
					}
					if (icon_bell != null) {
						if (icon_user_female != null || icon_user != null || icon_smile != null) {
							sqlCondition += ",";
						}
						sqlCondition += "'icon-bell'";
					}
					sqlCondition += ")";
				}
				pstmt = conn.prepareStatement(sqlCondition);//検索条件が指定されている場合の検索実行
				rset = pstmt.executeQuery();
			} else {
				pstmt = conn.prepareStatement(sql);//検索条件が指定されていない場合の検索実行
				rset = pstmt.executeQuery();
			}

			// 検索結果の数だけ繰り返す
			while (rset.next()) {
				// 必要な列から値を取り出し、ユーザー情報オブジェクトを作成
				UserDTO user = new UserDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));

				// 検索結果のUerDTOオブジェクトをリストに追加
				list.add(user);
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

	public void editUser(UserDTO editUser, String e_LoginId, String e_UserName, String e_Password, String e_Icon,
			String e_Profile) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		String sql = "UPDATE users";
		ArrayList<String> sqlConditions = new ArrayList<String>();
		try {
			// データベース接続情報取得
			conn = getConnection();
			if (!(editUser.getLoginId().equals(e_LoginId)) && e_LoginId.equals("")) {
				sqlConditions.add("'loginId'=" + e_LoginId);
			}
			if (!(e_UserName.equals(editUser.getUserName())) && e_UserName.equals("")) {
				sqlConditions.add("'userName'=" + e_UserName);
			}
			if (!(e_Password.equals(editUser.getPassword())) && e_Password.equals("")) {
				sqlConditions.add("'password'=" + e_Password);
			}
			if (!e_Icon.equals(editUser.getIcon())) {
				sqlConditions.add("'icon'=" + e_Icon);
			}
			if (!(e_Profile.equals(editUser.getProfile()))) {
				sqlConditions.add("'profile'=" + e_Profile);
			}
			for (int i = 0; i < sqlConditions.size(); i++) {
				if (i == 0) {
					sql += " SET";
				} else {
					sql += ",";
				}
				sql += " " + sqlConditions.get(i);
				if (i == (sqlConditions.size() - 1)) {
					sql += " WHERE loginId=" + editUser.getLoginId();
				}
			}
			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql); // SELECT 構文登録
			rset = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}

	}

}
