package com.dy.myaudioappliction.util;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： Dyan on 2016/9/12 10:12
 * 描述： 自定义的打印
 */
public class Logger {
	public static final String sTag = "Dyan";
	private static final int JSON_INDENT = 2;

	public static void e(String tag, String content) {
		tag = getFinalTag(tag);
		StackTraceElement et = getLocationInfo();
		//最终的展示方式全靠拼接
		Log.e(tag, "(" + et.getFileName() + ":" + et.getLineNumber() + ")");
		Log.e(tag, content);
	}
	public static void e(String content) {
		e(null, content);
	}
	public static void d(String tag, String content) {
		tag = getFinalTag(tag);
		StackTraceElement et = getLocationInfo();
		//最终的展示方式全靠拼接
		Log.d(tag, "(" + et.getFileName() + ":" + et.getLineNumber() + ")");
		Log.d(tag, content);
	}
	public static void d(String content) {
		d(null, content);
	}
	private static String getFinalTag(String tag) {
		if (tag != null && !TextUtils.isEmpty(tag)) {
			return tag;
		}
		return sTag;
	}



	//通过当前方法被调用时的栈帧集合来获取当前函数所处的位置追踪信息
	public static String getLocationEvent() {
		StringBuilder sb = new StringBuilder();
		StackTraceElement[] et = Thread.currentThread().getStackTrace();
		for (StackTraceElement e : et) {
			sb.append("\tat");
			sb.append(e.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	//遍历element，Logger类本类方法打印完之后就是调用处的位置信息，凭此进行删选
	public static StackTraceElement getLocationInfo() {
		StackTraceElement[] et = Thread.currentThread().getStackTrace();
		boolean isLocalMethod = false;//是否已经到了logger本类方法
		for (StackTraceElement e : et) {
			if (e.getClassName().equals(Logger.class.getName())) {
				isLocalMethod = true;
			} else {
				if (isLocalMethod) {
					return e;
				}
			}
		}
		return null;
	}

	//针对json有个nice的展现
	public static String getPrettyJson(String json) {
		if (json == null)
			return null;
		String pj = null;
		try {
			if (json.startsWith("{")) {
				JSONObject jo = new JSONObject(json);
				//json_indent 可以呈现出分层的效果
				pj = jo.toString(JSON_INDENT);

			} else if (json.startsWith("[")) {
				JSONArray ja = new JSONArray(json);
				pj = ja.toString(JSON_INDENT);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return pj;
	}

	public static void json(String tag, String json) {
		e(tag,getPrettyJson(json));
	}

	public static void json(String json) {
		json(null, json);
	}


}
