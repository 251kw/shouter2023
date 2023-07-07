package check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dto.UserDTO;

public class Check_func {
	//半角英数字かどうか判断するメソッド
	public static boolean checkLogic(String regex, String target) {
		boolean result = false;
		if( target == null || target.equals("")) return true ;
		// 3. 引数に指定した正規表現regexがtargetにマッチするか確認する
		Pattern p1 = Pattern.compile(regex); // 正規表現パターンの読み込み
		Matcher m1 = p1.matcher(target); // パターンと検査対象文字列の照合
		result = m1.matches(); // 照合結果をtrueかfalseで取得
		return result;
	}
	//全角もしくは半角のスペースのみの文字列か判断するメソッド
	public static boolean checkSpace(String data) {
		boolean result=false;
		//一文字も入ってこなかったらFalseを返す。
		if(data.equals("")||data==null) {
			return result;
		}

		String pat_str = "^[ 　]+$";
		Pattern pat =Pattern.compile(pat_str);
		Matcher matcher = pat.matcher(data);
		if (matcher.find()) {
			result = true;
		}
		return result;
	}


	public static boolean checkEmpty(String loginId,String password,String userName) {
		boolean result = false;

		if(loginId.equals("") || password.equals("") || userName.equals("")) {
			result = true;
		}
		return result;
	}

	public static boolean checkEmptyAll(String loginId,String password,String userName) {
		boolean result1 = false;
		boolean result2 = false;
		boolean result3 =false;
		if(loginId==null) {
			result1=true;
		}else if(loginId.equals("")){
			result1 =true;
		}
		if(password==null) {
			result2=true;
		}else if(password.equals("")) {
			result2 =true;
		}
		if(userName==null) {
			result3=true;
		}else if(userName.equals("")){
			result3=true;
		}
		return (result1 && result2 && result3);
	}

	public static boolean countElev(String str) {
		int len = str.length();
		if (len > 10) {
			return true;
		}else {
			return false;
		}
	}
	public static boolean countThou(String str) {
		int len = str.length();
		if (len > 100) {
			return true;
		}else {
			return false;
		}
	}

	public static boolean checkContain(String loginId, String password) {
		boolean result_id= false;
		boolean result_pass = false;
		if((loginId.contains(" ") || loginId.contains("　")) && ! checkSpace(loginId)) {
			result_id = true;
		}
		if((password.contains(" ") || password.contains("　")) && ! checkSpace(password)) {
			result_pass = true;
		}
		return (result_id||result_pass);
	}

	public static boolean checkSame(UserDTO user, UserDTO olduser) {
		boolean result = false;
		if(user.getLoginId().equals(olduser.getLoginId()) &&
				user.getPassword().equals(olduser.getPassword()) &&
				user.getUserName().equals(olduser.getUserName()) &&
				user.getIcon().equals(olduser.getIcon()) &&
				user.getProfile().equals(olduser.getProfile()) ) {
			result = true;
		}
		return result;
	}
}
