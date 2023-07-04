package dto;

public class ShoutDTO {
	private String userName; // ユーザー名
	private String icon; // ユーザーアイコン
	private String date; // 書き込み日時
	private String writing; // 書き込み内容

	public ShoutDTO() {

	}

	public ShoutDTO(String userName, String icon, String date,
			String writing) {
		this.userName = userName;
		this.icon = icon;
		this.date = date;
		this.writing = writing;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWriting() {
		return writing;
	}

	public void setWriting(String writing) {
		this.writing = writing;
	}

}
