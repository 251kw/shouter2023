package dto;

public class ShoutDTO {
	private String userName;
	private String icon;
	private String date;
	private String writing;

	public ShoutDTO() {

	}

	public ShoutDTO(String userName, String icon, String date, String writing) {
		this.userName = userName;
		this.icon = icon;
		this.date = date;
		this.writing = writing;
	}

	// 各メンバ変数の getter および setter
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

	public void setWriting(String writing) {
		this.writing = writing;
	}

	public String getWriting() {
		return writing;
	}

	public void setDate(String date) {
		String[] str=date.split("\\.");
		this.date = str[0];
	}

	public String getDate() {
		return this.date;
	}
}
