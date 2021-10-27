package com.example.easi641.common;

public class UserType {
	public static final int STAND_ALONE = 0;
	public static final int DEVELOPER = 1;
	public static final int REVIEWER = 2;

	public static String parseType(int type) {
		if (type == 0) {
			return "STAND_ALONE";
		} else if (type == 1) {
			return "DEVELOPER";
		} else if (type == 2) {
			return "REVIEWER";
		} else {
			return "UNDEFINED";
		}
	}
}
