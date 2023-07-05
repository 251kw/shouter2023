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

	public ArrayList<UserDTO> getSearching(String loginId, String userName, ArrayList<String> icons, String profile) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		//int iconcheck = 0;//iconのチェック状況を判断する
		int countId = 0;//loginIdの入れる場所探す
		int countName = 0;//userNameの入れる場所探す
		//int countIcon1 = 0;//iconの入れる場所探す
		//int countIcon2 = 0;//iconの入れる場所探す
		int countProfile = 0;//profileの入れる場所探す
		int countIcon_first = 0;
		int countIcon_last = 0;
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();

		String sql = "SELECT * FROM users WHERE 1=1";
		try {
			// データベース接続処理
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			if (loginId != null && !loginId.trim().isEmpty()) {//loginIdが入力された場合
				sql = sql + " and loginId = ?";
				countId = stringMatch(sql, countId);//loginIdの入れる場所探す
			}

			if (userName != null && !userName.trim().isEmpty()) {//userNameが入力された場合
				sql = sql + " and userName like ?";
				countName = stringMatch(sql, countName);//userNameの入れる場所探す
			}

			if (!icons.isEmpty()) {
				int i;
				sql = sql + " and (icon = ?";
				countIcon_first = stringMatch(sql, countIcon_first);
				for (i = 0; i < icons.size() - 1; i++) {
					sql = sql + " or icon = ?";
				}
				if (i > 0) {
					countIcon_last = stringMatch(sql, countIcon_last);
				}
				sql = sql +")";
			}

			/*
			if (iconcheck == 0) {
				if ((icon1 != null && !icon1.trim().isEmpty()) && (icon2 != null && !icon2.trim().isEmpty())) {
					//"男"と"女"両方チェックされた場合
					iconcheck = 1;
				} else if ((icon1 != null && !icon1.trim().isEmpty()) || (icon2 != null && !icon2.trim().isEmpty())) {
					//"男"と"女"片方チェックされた場合
					if (icon1 != null && !icon1.trim().isEmpty()) {
						//"男"だけチェックされた場合
						iconcheck = 2;
					} else {
						//"女"だけチェックされた場合
						iconcheck = 3;
					}
				}
			}

			switch (iconcheck) {
			case 1://"男"と"女"両方チェックされた場合
				break;
			case 2://"男"だけチェックされた場合
				sql = sql + " and icon = ?";
				countIcon1 = stringMatch(sql, countIcon1);//iconの入れる場所探す
				break;
			case 3://"女"だけチェックされた場合
				sql = sql + " and icon = ?";
				countIcon2 = stringMatch(sql, countIcon2);//iconの入れる場所探す
				break;
			default:
				break;
			}
			*/

			if (profile != null && !profile.trim().isEmpty()) {//profileが入力された場合
				sql = sql + " and profile like ?";
				countProfile = stringMatch(sql, countProfile);//profileの入れる場所探す
			}

			pstmt = conn.prepareStatement(sql);

			if (countId != 0)//loginIdの入れる場所が分かる場合
				pstmt.setString(countId, loginId);
			if (countName != 0)//userNameの入れる場所が分かる場合
				pstmt.setString(countName, "%" + userName + "%");
			if (countIcon_first != 0)//iconの入れる場所が分かる場合
			{
				if (countIcon_last != 0) {
					int j = 0;
					for (int i = countIcon_first; i <= countIcon_last; i++) {
						pstmt.setString(i, icons.get(j));
						j++;
					}

				} else {
					pstmt.setString(countIcon_first, icons.get(0));
				}
			}

			//if (countIcon2 != 0)//iconの入れる場所が分かる場合
			//	pstmt.setString(countIcon2, icon2);
			if (countProfile != 0)//profileの入れる場所が分かる場合
				pstmt.setString(countProfile, "%" + profile + "%");

			rset = pstmt.executeQuery();//sqlに命令実行

			// 検索結果の数だけ繰り返す
			while (rset.next()) {
				// 必要な列から値を取り出し、書き込み内容オブジェクトを生成}
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

	public int stringMatch(String str, int count) {//入れる場所を探すメソッド
		String pattern1 = "and";//"and"でsql文の入れる場所を判断する
		Pattern r = Pattern.compile(pattern1);
		Matcher matcher = r.matcher(str);
		while (matcher.find()) {//"and"の数をカウントする
			count++;
		}

		String pattern2 = "or";
		Pattern r_2 = Pattern.compile(pattern2);
		Matcher matcher2 = r_2.matcher(str);
		while (matcher2.find()) {
			count++;
		}
		return count;
	}
}
