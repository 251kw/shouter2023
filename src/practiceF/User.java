package practiceF;

import java.io.Serializable;

public class User implements Serializable {
	private String loginId;
	private String password;
	private String userName;
	private String icon;
	private String profile;

	public User() {
	}

	public User(String loginId, String password, String userName, String icon, String profile) {
		this.loginId = loginId;
		this.password = password;
		this.userName = userName;
		this.icon = icon;
		this.profile = profile;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public void authentication(String id, String pass) {
		if (loginId.equals(id) && password.equals(pass)) {
			System.out.println("welcome" + userName);
		} else {
			System.out.println("Authentication failure ...");
		}
	}
}
