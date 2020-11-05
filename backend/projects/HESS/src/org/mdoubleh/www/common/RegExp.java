package org.mdoubleh.www.common;

import java.util.regex.Pattern;

public class RegExp {
	public static final int BOARD_NUM = 0;
	public static final int BOARD_TITLE = 1;
	public static final int BOARD_CONTENT = 2;
	public static final int MEMBER_NAME = 3;
	public static final int MEMBER_ID = 4;
	public static final int MEMBER_PWD = 5;
	public static final int IS_NUMBER = 6;

	public static final String EXP_BOARD_NUM = "^[0-9]*$";
	public static final String EXP_BOARD_TITLE = "^.{1,100}$";
	public static final String EXP_BOARD_CONTENT = "^.{1,65535}$";
	public static final String EXP_MEMBER_NAME = "^[가-힣a-z]{1,10}$";
	public static final String EXP_MEMBER_ID = "^[a-z0-9]{4,20}$";
	public static final String EXP_MEMBER_PWD = "^.{4,30}$";
	public static final String EXP_IS_NUMBER = "^[0-9]*$";

	public static boolean checkString(int type, String data) {
		boolean result = false;
		switch (type) {
		case BOARD_NUM:
			result = Pattern.matches(EXP_BOARD_NUM, data);
			break;
		case BOARD_TITLE:
			result = Pattern.matches(EXP_BOARD_TITLE, data);
			break;
		case BOARD_CONTENT:
			result = Pattern.matches(EXP_BOARD_CONTENT, data);
			break;
		case MEMBER_NAME:
			result = Pattern.matches(EXP_MEMBER_NAME, data);
			break;
		case MEMBER_ID:
			result = Pattern.matches(EXP_MEMBER_ID, data);
			break;
		case MEMBER_PWD:
			result = Pattern.matches(EXP_MEMBER_PWD, data);
			break;
		case IS_NUMBER:
			result = Pattern.matches(EXP_IS_NUMBER, data);
			break;
		}

		return result;
	}

}
