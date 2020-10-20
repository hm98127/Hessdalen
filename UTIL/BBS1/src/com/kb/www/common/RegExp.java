package com.kb.www.common;

import java.util.regex.Pattern;

public class RegExp {
    public static final int ARTICLE_NUM = 0;
    public static final int ARTICLE_SUBJECT = 1;
    public static final int ARTICLE_CONTENT = 2;
    public static final int MEMBER_ID = 3;
    public static final int MEMBER_PWD = 4;
    public static final int IS_NUMBER = 5;

    public static final String EXP_ARTICLE_NUM = "[0-9]*$";
    public static final String EXP_ARTICLE_SUBJECT = "^.{1,100}$";
    public static final String EXP_ARTICLE_CONTENT = "^.{1,65535}$";
    public static final String EXP_MEMBER_ID = "^[a-z0-9]{4,20}$";
    public static final String EXP_MEMBER_PWD = "^.{4,30}$";
    public static final String EXP_IS_NUMBER = "[0-9]*$";

    public static boolean checkString(int type, String data) {
        boolean result = false;
        switch (type) {
            case ARTICLE_NUM:
                result = Pattern.matches(EXP_ARTICLE_NUM, data);
                break;
            case ARTICLE_SUBJECT:
                result = Pattern.matches(EXP_ARTICLE_SUBJECT, data);
                break;
            case ARTICLE_CONTENT:
                result = Pattern.matches(EXP_ARTICLE_CONTENT, data);
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










