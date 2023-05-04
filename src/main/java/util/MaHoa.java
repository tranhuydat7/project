package util;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;

public class MaHoa {
	// md5
	// sha-1 => thuong su dung

	public static String toSHA1(String str) {
		// biến mật khẩu thành chuỗi phức tạp hơn
		String salt = "askfdgrriwodfbcdfghsdcvbaqpmzjguf";
		String result = null;
		str = str + salt;

		try {
			byte[] dataByte = str.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			result = Base64.encodeBase64String(md.digest(dataByte));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

}
