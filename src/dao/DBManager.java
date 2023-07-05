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

	// 新しいユーザを登録
	public Boolean setNewUser(UserDTO user) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {

			conn = getConnection();

			// INSERT 文の登録と実行
			String sql = "INSERT INTO users(loginId, password, userName, icon, profile) VALUES(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getLoginId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getIcon());
			pstmt.setString(5, user.getProfile());

			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				//INSERT 文の実行結果が１なら登録成功
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

	// loginIdに重複がないか
	public String loginId(String id) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		String sql = "SELECT * FROM users WHERE loginId=?";
		String result = "false";

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql); // SELECT 構文登録
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();

			// 検索結果があれば
			if (rset.next()) {
				result = "true";
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

	// ID、アイコン、ユーザ名、プロフィール記入(全部記述)
	public ArrayList<UserDTO> searchall(String id, String icon, String name, String pro) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		String sql = "SELECT * FROM users WHERE loginId=? and icon=? and userName like ? and profile like ?";

		try {
			// データベース接続情報取得
			conn = getConnection();

			// SELECT 文の登録と実行
			pstmt = conn.prepareStatement(sql); // SELECT 構文登録
			pstmt.setString(1, id);
			pstmt.setString(2, icon);
			pstmt.setString(3, "%" + name + "%");
			pstmt.setString(4, "%" + pro + "%");
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

	// ID、アイコン2、ユーザ名、プロフィール記入(全部記述)
		public ArrayList<UserDTO> searchall2(String id, String name, String pro) {
			Connection conn = null; // データベース接続情報
			PreparedStatement pstmt = null; // SQL 管理情報
			ResultSet rset = null; // 検索結果

			ArrayList<UserDTO> list = new ArrayList<UserDTO>();
			String sql = "SELECT * FROM users WHERE loginId=? and userName like ? and profile like ? and (icon = 'icon-tools' or icon = 'icon-rocket')";

			try {
				// データベース接続情報取得
				conn = getConnection();

				// SELECT 文の登録と実行
				pstmt = conn.prepareStatement(sql); // SELECT 構文登録
				pstmt.setString(1, id);
				pstmt.setString(2, "%" + name + "%");
				pstmt.setString(3, "%" + pro + "%");
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

	// ログインID
	public ArrayList<UserDTO> loginid(String id) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		String sql = "SELECT * FROM users where loginId = ?";

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
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

	// ユーザ名
	public ArrayList<UserDTO> username(String name) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		String sql = "SELECT * FROM users where userName like ?";

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
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

	// プロフィール
	public ArrayList<UserDTO> profile(String pro) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		String sql = "SELECT * FROM users where profile like ?";

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + pro + "%");
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

	//アイコンのみ
	public ArrayList<UserDTO> icon(String icon) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		String sql = "SELECT * FROM users where icon = ?";

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, icon);
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

	//アイコン２つ選択
	public ArrayList<UserDTO> icon2() {
		Connection conn = null; // データベース接続情報
		Statement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		String sql = "SELECT * FROM users where icon = 'icon-tools' or icon = 'icon-rocket'";

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();
			pstmt = conn.createStatement();
			rset = pstmt.executeQuery(sql);

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

	// 全て空
	public ArrayList<UserDTO> allnull() {
		Connection conn = null; // データベース接続情報
		Statement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		String sql = "SELECT * FROM users;";

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();
			pstmt = conn.createStatement();
			rset = pstmt.executeQuery(sql);

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

	//IDとユーザ名いらない
	public ArrayList<UserDTO> idname(String id, String name) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		String sql = "SELECT * FROM users where loginId=? and userName like ?";

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, "%" + name + "%");
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

	//nameとicon1
	public ArrayList<UserDTO> nameicon1(String name, String icon) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		String sql = "SELECT * FROM users where userName like ? and icon = ?";

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			pstmt.setString(2, icon);
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

	//nameとicon2
	public ArrayList<UserDTO> nameicon2(String name) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		String sql = "SELECT * FROM users where userName like ? and (icon = 'icon-tools' or icon = 'icon-rocket')";

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
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

	// プロフィール
	public ArrayList<UserDTO> namepro(String name, String pro) {
		Connection conn = null; // データベース接続情報
		PreparedStatement pstmt = null; // SQL 管理情報
		ResultSet rset = null; // 検索結果

		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		String sql = "SELECT * FROM users where userName like ? and profile like ?";

		try {
			// SnsDAO クラスのメソッド呼び出し
			conn = getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			pstmt.setString(2, "%" + pro + "%");
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

	//nameとicon1とpro
		public ArrayList<UserDTO> nameicon1pro(String name, String icon,String pro) {
			Connection conn = null; // データベース接続情報
			PreparedStatement pstmt = null; // SQL 管理情報
			ResultSet rset = null; // 検索結果

			ArrayList<UserDTO> list = new ArrayList<UserDTO>();
			String sql = "SELECT * FROM users where userName like ? and icon = ? and profile like ?";

			try {
				// SnsDAO クラスのメソッド呼び出し
				conn = getConnection();

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + name + "%");
				pstmt.setString(2, icon);
				pstmt.setString(3, "%" + pro + "%");
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

		//nameとicon2とpro
		public ArrayList<UserDTO> nameicon2pro(String name,String pro) {
			Connection conn = null; // データベース接続情報
			PreparedStatement pstmt = null; // SQL 管理情報
			ResultSet rset = null; // 検索結果

			ArrayList<UserDTO> list = new ArrayList<UserDTO>();
			String sql = "SELECT * FROM users where userName like ? and profile like ? and (icon = 'icon-tools' or icon = 'icon-rocket')";

			try {
				// SnsDAO クラスのメソッド呼び出し
				conn = getConnection();

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + name + "%");
				pstmt.setString(2, "%" + pro + "%");
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

		//icon1とpro
				public ArrayList<UserDTO> icon1pro( String icon,String pro) {
					Connection conn = null; // データベース接続情報
					PreparedStatement pstmt = null; // SQL 管理情報
					ResultSet rset = null; // 検索結果

					ArrayList<UserDTO> list = new ArrayList<UserDTO>();
					String sql = "SELECT * FROM users where icon = ? and profile like ?";

					try {
						// SnsDAO クラスのメソッド呼び出し
						conn = getConnection();

						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, icon);
						pstmt.setString(2, "%" + pro + "%");
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

		//icon2とpro
		public ArrayList<UserDTO> icon2pro(String pro) {
			Connection conn = null; // データベース接続情報
			PreparedStatement pstmt = null; // SQL 管理情報
			ResultSet rset = null; // 検索結果

			ArrayList<UserDTO> list = new ArrayList<UserDTO>();
			String sql = "SELECT * FROM users where profile like ? and (icon = 'icon-tools' or icon = 'icon-rocket')";

			try {
				// SnsDAO クラスのメソッド呼び出し
				conn = getConnection();

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + pro + "%");
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
