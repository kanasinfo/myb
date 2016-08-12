package com.myb.portal.util;

import java.util.HashMap;
import java.util.Map;

public abstract class Param {
	
	public static final Map<Integer, String> mapColor = new HashMap<Integer, String>();
	public static final Map<Integer, String> mapStatusName = new HashMap<Integer, String>();
	static {
		mapStatusName.put(0, "进行中");
		mapStatusName.put(1, "已停止");
		mapStatusName.put(2, "创建中");
		mapColor.put(0, "120,151,63");
		mapColor.put(1, "204,204,204");
		mapColor.put(2, "235,189,90");
	}
	public static final String ACTIVE_FLAG = "activeFlag";
	public static final String WELCOME_MSG = "welcomeMsg";
	public static final String RELEASE = "release_";
}
