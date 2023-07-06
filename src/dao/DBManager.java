package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dto.ShoutDTO;
import dto.UserDTO;

//dBとの接続を行うクラス
public class DBManager extends SnsDAO {

	//ログインメソッド
	public UserDTO getLoginUser(String loginId, String password) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		String sql = "SELECT * FROM users WHERE loginId=? AND password=?"; //loginIdとpasswordはプレースホルダー
		UserDTO user = null; // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql); // SELECT 構文登録
			pstmt.setString(1, loginId); //値の設定
			pstmt.setString(2, password);
			rset = pstmt.executeQuery(); //実行

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

	// 書き込み内容リストの取得メソッド
	public ArrayList<ShoutDTO> getShoutList() {
		Connection conn = null;
		Statement pstmt = null;
		ResultSet rset = null;

		//ArrayListの作成　型はShoutDTO型
		ArrayList<ShoutDTO> list = new ArrayList<ShoutDTO>();

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection(); //DBへの接続の記述がされているメソッド(SnsDAO)
			pstmt = conn.createStatement(); //プレースホルダーがないのでcreateStatementを使用

			//selectで取得時に日付に小数点がついてしまう　ここで小数点を切り捨てる処理を行う
			String sql = "SELECT * FROM shouts ORDER BY date DESC";
			// SELECT 文の実行(ResultSetに格納)
			rset = pstmt.executeQuery(sql);

			// 検索結果の数だけ繰り返す
			while (rset.next()) {
				// 必要な列から値を取り出し、書き込み内容オブジェクトを生成
				//検索結果の数だけShoutDTOをインスタンス化する
				ShoutDTO shout = new ShoutDTO();
				//SHoutDTOのsetterに値を格納
				shout.setUserName(rset.getString(2)); //1からgetString()は始まる
				shout.setIcon(rset.getString(3));
				shout.setDate(rset.getString(4).substring(0, 19)); //ここで小数点が表示される str.substring(0, str.indexOf('.'))
				shout.setWriting(rset.getString(5));

				// その後ArrayListに値を格納
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

	// 書き込み内容登録メソッド
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

	//新規ユーザー登録メソッド
	public int setRegisterInfo(String loginId, String userName, String password, String icon, String profile) {
		Connection conn = null; // データベース接続
		PreparedStatement pstmt = null; // SQL 管理

		String sql = "INSERT INTO users(loginId, userName, password, icon, profile) VALUES(?, ?, ?, ?, ?)";

		//挿入結果件数
		int result = 0;

		try {
			conn = getConnection(); //DBへの接続

			//preparedStatement
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, userName);
			pstmt.setString(3, password);
			pstmt.setString(4, icon);
			pstmt.setString(5, profile);

			//SQlの実行
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

	//ユーザー登録結果画面に表示するためのメソッド
	public UserDTO getShowResult(String loginId) {
		Connection conn = null; // データベース接続
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		//クエリ
		String sql = "SELECT * FROM users WHERE loginId=?";
		UserDTO user = null; //

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);

			//SQlの実行
			rset = pstmt.executeQuery();

			// 検索結果
			while (rset.next()) {
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

	//ログインIDの重複チェックメソッド
	public boolean checkLoginId(String loginId) {
		Connection conn = null; // データベース接続
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果
		boolean result = false;

		//クエリ
		String sql = "SELECT * FROM users WHERE loginId=?";

		try {
			conn = getConnection(); //DBとの接続
			pstmt = conn.prepareStatement(sql); //preparedStatement
			pstmt.setString(1, loginId.trim());

			//SQlの実行
			rset = pstmt.executeQuery();
			if (rset.next()) { //検索結果があった場合
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

	//ユーザー情報検索メソッド
	public ArrayList<UserDTO> getSearchList(String loginId, String userName, String icon[],String profile) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String icon1 = null;
		String icon2 = null;

		//ArrayListの作成　型はUserDTO型
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();

		try {
			String sql = "SELECT loginId, userName, icon, profile  FROM users WHERE 1 =1 ";
			//入力された値の名前をリストで格納していく
			List<String> parameters = new ArrayList<>();

		//値が入力されているかチェックし、入力されている場合は条件をANDで繋げていく
			//loginId条件追加
			if (!loginId.equals("")) {
				  sql += "AND loginId = ? ";
				  parameters.add("loginId");
			}

			//userName条件追加
			if(!userName.equals("")) {
				sql += "AND userName LIKE ?";
				 parameters.add("userName");
			}

			//icon条件追加
			if(!(icon ==null)) { //少なくとも１つアイコンが選択されたとき
				if(icon.length ==1) { //選択されたアイコンが1つの時
					icon1 = icon[0];
					sql += "AND icon = ?";
					parameters.add("icon[0]");

				}else if(icon.length ==2) { //選択されたアイコンが2つの時
					icon1 = icon[0];
					icon2 = icon[1];
					sql += "AND (icon =? OR icon = ?)";
					parameters.add("icon[0][1]");
				}
			}

			//profile条件追加
			if (!profile.equals("")) {
				sql += "AND profile LIKE  ?"; //部分一致
				parameters.add("profile");
			}

			//DB接続
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

		//値をセットしていく
			int columnIndex = 0;
			//loginId
			if (parameters.contains("loginId")) {
			  pstmt.setString(++columnIndex, loginId); //setString(1, loginId)と同じ意味
			}

			//userName
			if (parameters.contains("userName")) {
				pstmt.setString(++columnIndex,  "%"+userName+"%");
			}

			//icon
			if (parameters.contains("icon[0]")) {
				pstmt.setString(++columnIndex, icon1);
			}
			if(parameters.contains("icon[0][1]")) {
				pstmt.setString(++columnIndex, icon1);
				pstmt.setString(++columnIndex, icon2);
			}

			//profile
			if (parameters.contains("profile")) {
				pstmt.setString(++columnIndex,  "%"+profile+"%");
			}

			rset = pstmt.executeQuery(); //実行

			// 検索結果の数だけ繰り返す
			while (rset.next()) {
				UserDTO user = new UserDTO();
				user.setLoginId(rset.getString(1)); //getString()の第一引数は1から始まる
				user.setUserName(rset.getString(2));
				user.setIcon(rset.getString(3));
				user.setProfile(rset.getString(4));

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
