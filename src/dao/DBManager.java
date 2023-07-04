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

	/***ログインID とパスワードを受け取り、登録ユーザ一覧に一致したものがあるか検索***/
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

	/***書き込み内容リストの getter***/
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
				shout.setDate(rset.getString(4).substring(0, 19));
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

	/***ログインユーザ情報と書き込み内容を受け取り、リストに追加する***/
	public boolean setWriting(UserDTO user, String writing) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;

		try {
			conn = getConnection();

			// INSERT 文の登録と実行
			String sql = "INSERT INTO shouts(loginId,password,userName,icon,profile) VALUES(?, ?, ?, ?)";
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

	/***新規登録情報を受け取り、リストに追加する***/
	public int setNewuser(String loginId, String password, String userName, String icon, String profile) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			conn = getConnection();
			// INSERT 文の登録と実行
			String sql = "INSERT INTO users(loginId, password, userName, icon, profile) VALUES(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, password);
			pstmt.setString(3, userName);
			pstmt.setString(4, icon);
			pstmt.setString(5, profile);

			// sql文を実行
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(pstmt);
			close(conn);
		}

		return result;
	}

	/***書き込み内容リストの getter***/
	public UserDTO getNewuser(String loginId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		UserDTO user = null;

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();

			// SELECT 文の実行
			String sql = "SELECT * FROM users WHERE loginId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,loginId);
			rset = pstmt.executeQuery();

			// 検索結果の数だけ繰り返す
			while (rset.next()) {
				user = new UserDTO();
				// 必要な列から値を取り出し、書き込み内容オブジェクトを生成
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

	/***ログインIDを受け取り、登録ユーザ一覧に一致したものがあるか検索***/
	public boolean searchLoginId(String loginId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		boolean resultId = false;	//重複なし

		try {
			conn = getConnection();

			// select 文の登録と実行
			String sql = "SELECT * FROM users WHERE loginId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			rset = pstmt.executeQuery();

			// INSERT 文の実行結果がtrueで帰ってきたら(見つかったら)重複してるってこと
			if (rset.next()) {
				resultId = true;	//なので、trueを返す
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断処理
			close(pstmt);
			close(conn);
		}

		return resultId;
	}

	/***検索***/
	public  ArrayList<UserDTO> search(String loginId, String userName, String icon[], String profile) {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果

		boolean result = false;

		String sql = "SELECT * FROM users";		//基本となるselect文
		ArrayList<String> param = new ArrayList<String>();	//パラメータを保持するリスト
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();	//結果を保持するリスト

		try {
			//ログインIdが入力されていればwhere句を連結し、loginId登録しましたよーのパラメータを追加。されなければスキップ
			if (!loginId.equals("")) {
				sql += " WHERE loginId=?";
				param.add("loginId");
				result = true;
			}
			//ユーザー名が入力されていればwhere句を連結し、ユーザ名登録しましたよーのパラメータを追加。されなければスキップ
			if (!userName.equals("")) {
				if(result != true) {
					sql += " WHERE userName LIKE ?";
					param.add("userName");
					result = true;
				}
				else {
					sql += " AND userName LIKE ?";
					param.add("userName");
				}
			}
			//アイコンが入力されていればwhere句を連結し、icon登録しましたよーのパラメータを追加。されなければスキップ
			if (icon != null) {
				//配列の長さが1のとき（片方しか選択されてないとき）
				if(icon.length == 1) {
					if(result != true) {
						sql += " WHERE icon=?";
						param.add("icon[0]");
						result = true;
					}
					else {
						sql += " AND icon=?";
						param.add("icon[0]");
					}
				}
				//配列の長さが2のとき（両方選択してるとき）
				else if(icon.length == 2) {
					if(result != true) {
						sql += " WHERE icon=? OR icon=?";
						param.add("icon[0]");
						param.add("icon[1]");
						result = true;
					}
					else {
						sql += " AND icon=? OR icon=?";
						param.add("icon[0]");
						param.add("icon[1]");
					}
				}
			}
			//プロフィールが入力されていればwhere句を連結し、プロフィール登録しましたよーのパラメータを追加。、されなければスキップ
			if (!profile.equals("")) {
				if(result != true) {
					sql += " WHERE profile LIKE ?";
					param.add("profile");
					result = true;
				}
				else {
					sql += " AND profile LIKE ?";
					param.add("profile");
				}
			}

			// データベース接続情報取得
			conn = getConnection();
			// SELECT 構文登録
			pstmt = conn.prepareStatement(sql);

			//登録されたパラメータがあったら?に入れる。パラメータを入れる?の位置を、0からふやしていって次に送る
			int index = 0;
			if (param.contains("loginId")) {
				pstmt.setString(++index, loginId);
			}
			if (param.contains("userName")) {
				pstmt.setString(++index, "%"+userName+"%");
			}
			if (param.contains("icon[0]")) {
				pstmt.setString(++index, icon[0]);
			}
			if (param.contains("icon[1]")) {
				pstmt.setString(++index, icon[1]);
			}
			if (param.contains("profile")) {
				pstmt.setString(++index, "%"+profile+"%");
			}

			// SELECT 文の登録と実行
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

	/***全件検索***/
	public  ArrayList<UserDTO> searchall() {
		Connection conn = null;            // データベース接続情報
		PreparedStatement pstmt = null;    // SQL 管理情報
		ResultSet rset = null;             // 検索結果


		String sql = "SELECT * FROM users";
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();

		try {
			// データベース接続情報取得
			conn = getConnection();
			// SELECT 構文登録
			pstmt = conn.prepareStatement(sql);
			// SELECT 文の登録と実行

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
}
