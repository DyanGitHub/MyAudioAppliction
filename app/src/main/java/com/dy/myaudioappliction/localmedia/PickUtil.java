package com.dy.myaudioappliction.localmedia;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * 作者： Dyan on 2016/5/26 09:57
 * 描述：从系统数据库获取文件信息
 */
public class PickUtil {
	/*
	   获取sd卡音频文件（存有音频文件未被检测出来的可能）
	 */
	public static ArrayList<FileInfo> queryAllAudio(final Context context) {
		if (context == null) { //判断传入的参数的有效性
			return null;
		}
		ArrayList<FileInfo> audios = new ArrayList<FileInfo>();
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = null;
		try {
			//查询数据库，参数分别为（路径，要查询的列名，条件语句，条件参数，排序）
			cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null ,null, MediaStore.Audio.Media.DATE_ADDED+" DESC");
			if (cursor != null) {
				while (cursor.moveToNext()) {
					FileInfo audio = new FileInfo();
					audio.setFileName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
					audio.setFilePath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))); //文件路径
					audio.setFileSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));//文件大小
					audio.setAddDate(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATE_MODIFIED)));//修改时间(从1970年开始的秒数)
					audio.setFileDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
					Log.d("dy", "audios:"+audio.getFileName()+":"+audio.getFileDuration()+":"+audio.getFileSize());
					audios.add(audio);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		Log.d("dy", "audios:"+audios.size());
		return audios;
	}
	//获取指定文件信息
	public static FileInfo queryAudio(final Context context, String data) {
		Log.d("dy", "data:"+data);
		if (context == null) { //判断传入的参数的有效性
			return null;
		}
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = null;
		FileInfo audio = new FileInfo();
		try {
			//查询数据库，参数分别为（路径，要查询的列名，条件语句，条件参数，排序）
			cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Audio.Media.DATA+"=?" ,new String[]{data}, MediaStore.Audio.Media.DATE_ADDED+" DESC");
			if (cursor != null) {
				while (cursor.moveToNext()) {

					audio.setFileName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
					audio.setFilePath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))); //文件路径
					audio.setFileSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));//文件大小
					audio.setAddDate(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATE_MODIFIED)));//修改时间(从1970年开始的秒数)
					audio.setFileDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		Log.d("dy", "audios:"+audio.getFileDuration()+":"+audio.getFileSize());
		return audio;
	}
	//创建的时间戳(秒)转换成年月日
	public static String changStr(long time)
	{
		time=time*1000;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String result=sdf.format(time);
		return result;
	}
	/**
	 * 根据时间点转成文本显示00:00:00 （音乐盒显示会用到）
	 */
	public static String millsecondsToStr(int seconds){
		seconds = seconds / 1000;
		String result = "";
		int hour = 0, min = 0, second = 0;
		hour = seconds / 3600;
		min = (seconds - hour * 3600) / 60;
		second = seconds - hour * 3600 - min * 60;
		if(hour!=0)
		{
			if (hour < 10) {
				result += "0" + hour + ":";
			} else {
				result += hour + ":";
			}
		}

		if (min < 10) {
			result += "0" + min + ":";
		} else {
			result += min + ":";
		}
		if (second < 10) {
			result += "0" + second;
		} else {
			result += second;
		}

		return result;

	}

}
