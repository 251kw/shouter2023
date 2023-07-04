package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dto.ShoutDTO;
import dto.UserDTO;

public class DBManager extends SnsDAO {
	// ログインID とパスワードを受け取り、登録ユーザ一覧に一致したものがあるか検索
	public UserDTO getLoginUser(String loginId, String password) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		String sql = "SELECT * FROM users WHERE loginId = ? AND password = ?";
		UserDTO user = null; // 登録ユーザ情報

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql);
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
		} finally {// データベース切断処理
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
			// データベース接続処理
			conn = getConnection();
			pstmt = conn.createStatement();

			// SELECT 文の実行
			String sql = "SELECT * FROM shouts ORDER BY date DESC";
			rset = pstmt.executeQuery(sql);

			// 検索結果の数だけ繰り返す
			while (rset.next()) {
				// 必要な列から値を取り出し、書き込み内容オブジェクトを生成			}
				ShoutDTO shout = new ShoutDTO();
				shout.setUserName(rset.getString(2));
				shout.setIcon(rset.getString(3));
				shout.setDate(rset.getString(4));
				shout.setWriting(rset.getString(5));

				//書き込み内容をリストに追加
				list.add(shout);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}
		return list;
	}

	// ログインユーザー情報と書き込み内容を受け取り、リストに追加する
	public boolean setWriting(UserDTO user, String writing) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			//INSERT文の登録と実行
			String sql = "INSERT INTO shouts(userName,icon,date,writing) VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getIcon());
			// 現在日時の取得と日付の書式指定
			Calendar calender = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pstmt.setString(3, sdf.format(calender.getTime()));
			pstmt.setString(4, writing);

			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				// INSERT文の実行結果が1なら登録成功
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

	// ログインIDが既存であるかどうか
	public UserDTO checkUser(String loginId, String password, String userName, String icon, String profile) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt1 = null; // SQL 管理情報
		PreparedStatement pstmt2 = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		String sql = "SELECT loginId FROM users WHERE loginId = ?";
		UserDTO user = null; // 登録ユーザ情報
		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setString(1, loginId);
			rset = pstmt1.executeQuery();

			// 検索結果があれば
			if (rset.next()) {
				return null;
			} else {
				//INSERT文の登録と実行
				sql = "INSERT INTO users(loginId,password,userName,icon,profile) VALUES(?,?,?,?,?)";
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setString(1, loginId);
				pstmt2.setString(2, password);
				pstmt2.setString(3, userName);
				pstmt2.setString(4, icon);
				pstmt2.setString(5, profile);
				pstmt2.executeUpdate();

				user = new UserDTO();
				user.setLoginId(loginId);
				user.setPassword(password);
				user.setUserName(userName);
				user.setIcon(icon);
				user.setProfile(profile);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// データベース切断処理
			close(pstmt1);
			close(pstmt2);
			close(conn);
		}
		return user;
	}

	public boolean isLetterDigit(String str) {
		boolean isDigit = false;//数字の判定用変数
		boolean isLetter = false;//英文字の判定用変数
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) { //数字であるかどうか判定
				isDigit = true;
			} else if (Character.isLetter(str.charAt(i))) { //英文字であるかどうか判定
				isLetter = true;
			}
		}
		String regex = "^[a-zA-Z0-9]{0,8}$";
		boolean isRight = (isDigit || isLetter) && str.matches(regex);
		return isRight;
	}

	@SuppressWarnings("resource")
	public ArrayList<UserDTO> getSearching(String loginId, String userName, String icon1,
			String icon2, String profile) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int iconcheck = 0;

		ArrayList<UserDTO> list = new ArrayList<UserDTO>();

		String sql = "SELECT * FROM users WHERE 1=1";
		try {
			// データベース接続処理
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			if (loginId != null && !loginId.trim().isEmpty()) {
				sql = sql + " and loginId = ?";
				int count = 0;
				count = stringMatch(sql, count);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(count, loginId);
			}

			if (userName != null && !userName.trim().isEmpty()) {
				sql = sql + " and userName like ?";
				int count = 0;
				count = stringMatch(sql, count);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(count, "%" + userName + "%");
			}

			if (iconcheck == 0) {
				if ((icon1 != null && !icon1.trim().isEmpty()) && (icon2 != null && !icon2.trim().isEmpty())) {
					iconcheck = 1;
				}
				else if((icon1 != null && !icon1.trim().isEmpty()) || (icon2 != null && !icon2.trim().isEmpty())) {
					if (icon1 != null && !icon1.trim().isEmpty()) {
						iconcheck = 2;
					}
					else
					{
						iconcheck = 3;
					}
				}
			}

			switch(iconcheck)
			{
			case 1:
				break;
			case 2:
				sql = sql + " and icon = ?";
				pstmt = conn.prepareStatement(sql);
				int count = 0;
				count = stringMatch(sql, count);
				pstmt.setString(count,icon1);
				break;
			case 3:
				sql = sql + " and icon = ?";
				int count2 = 0;
				count = stringMatch(sql, count2);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(count2,icon2);
				break;
			default:
				break;
			}
			if (profile != null && !profile.trim().isEmpty()) {
				sql = sql + " and profile like ?";
				int count = 0;
				count = stringMatch(sql, count);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(count, "%" + profile + "%");
			}

			rset = pstmt.executeQuery();
			// 検索結果の数だけ繰り返す
			while (rset.next()) {
				// 必要な列から値を取り出し、書き込み内容オブジェクトを生成			}
				UserDTO user = new UserDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));

				//書き込み内容をリストに追加
				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断処理
			close(rset);
			close(pstmt);
			close(conn);
		}
		return list;
	}

	public int stringMatch(String str,int count)
	{
		String pattern = "and";
		Pattern r = Pattern.compile(pattern);
		Matcher matcher = r.matcher(str);

		while(matcher.find())
		{
			count++;
		}
		return count;
	}
}
