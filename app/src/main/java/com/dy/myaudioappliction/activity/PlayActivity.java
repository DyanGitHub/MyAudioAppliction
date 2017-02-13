package com.dy.myaudioappliction.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dy.myaudioappliction.R;

/**
 * 作者： Dyan on 2017/2/13 16:16
 * 描述：
 */
public class PlayActivity extends Activity {
	public static void newInstance(Context context)
	{
		context.startActivity(new Intent(context,PlayActivity.class));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
	}
}
