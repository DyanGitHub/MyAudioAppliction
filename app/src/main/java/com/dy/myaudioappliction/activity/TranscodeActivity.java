package com.dy.myaudioappliction.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dy.myaudioappliction.R;
import com.dy.myaudioappliction.localmedia.FileInfo;

import java.util.ArrayList;

/**
 * 作者： Dyan on 2017/2/7 16:22
 * 描述：
 */
public class TranscodeActivity extends Activity {
	ArrayList<FileInfo> infos;
	public static void newInstance(Context context, ArrayList<FileInfo> infos)
	{
		Intent intent=new Intent(context,TranscodeActivity.class);
		intent.putExtra("infos",infos);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transcode);
		initData();
	}

	private void initData() {
		infos= (ArrayList<FileInfo>) getIntent().getSerializableExtra("infos");
	}
}
