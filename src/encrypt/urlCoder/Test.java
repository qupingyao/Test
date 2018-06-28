package encrypt.urlCoder;

import encrypt.Constant;

public class Test {

	private static final String text = "我是你爸爸,I'm your father";

	public static void main(String[] args) {
		try {
			System.out.println("source content(char): " + text);
			String encodeStr = UrlCoderTools.encode(text, Constant.defaultCharset);
			System.out.println("after urlCoder encode(char): " + encodeStr);
			String decodeStr = UrlCoderTools.decode(encodeStr, Constant.defaultCharset);
			System.out.println("after urlCoder decode(char): " + decodeStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}