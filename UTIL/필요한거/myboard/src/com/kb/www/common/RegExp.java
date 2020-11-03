package com.kb.www.common;

import java.util.regex.Pattern;

public class RegExp {
	public static final int MEMBER_NAME = 0;
	public static final int MEMBER_ID = 1;
	public static final int MEMBER_PASSWORD = 2;
	
	private final String EXP_MEMBER_NAME = "^[가-힣a-z]{1,10}$";
	private final String EXP_MEMBER_ID = "^[a-z0-1]{4,20}$";
	private final String EXP_MEMBER_PASSWORD = "^.{4,20}$";
	
	public boolean isValid(int type, String data) {
		boolean result = false;
		switch (type) {
		case MEMBER_NAME:
			result = Pattern.matches(EXP_MEMBER_NAME, data);
			break;

		case MEMBER_ID:
			result = Pattern.matches(EXP_MEMBER_ID, data);
			break;

		case MEMBER_PASSWORD:
			result = Pattern.matches(EXP_MEMBER_PASSWORD, data);
			break;
		}
		
		return result;
	}
}
