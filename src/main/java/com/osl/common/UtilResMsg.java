package com.osl.common;

import java.io.Serializable;

public class UtilResMsg implements Serializable {
	private static final long serialVersionUID = -5674233790803912108L;

	public static String getMessage(String msg, Object... params) {
		if (Util.isEmpty(msg)) {
			return UtilConst.EMPTY_STRING;
		}
		if ((params != null) && (params.length > 0)) {
			for (int i = 0; i < params.length; i++) {
				String param = UtilConv.objToStr(params[i]);
				if (param == null) {
					param = UtilConst.EMPTY_STRING;
				}
				String key = "\\{" + Integer.toString(i) + "\\}";
				msg = msg.replaceAll(key, param);
			}
		}
		return msg;
	}
}
