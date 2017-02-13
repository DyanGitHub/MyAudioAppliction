package com.dy.myaudioappliction.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.czt.mp3recorder.MP3Recorder;
import com.dy.myaudioappliction.R;

import java.io.File;
import java.io.IOException;

/**
 * 作者： Dyan on 2017/2/7 15:03
 * 描述： 录音界面
 */
public class RecordeActivity extends Activity{
	public static void  newInstance(Context context)
	{
		context.startActivity(new Intent(context,RecordeActivity.class));
	}

	private MP3Recorder mRecorder = new MP3Recorder(new File(Environment.getExternalStorageDirectory(),"test.mp3"));
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recorde);
		Button startButton = (Button) findViewById(R.id.StartButton);
		startButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					mRecorder.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		Button stopButton = (Button) findViewById(R.id.StopButton);
		stopButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mRecorder.stop();
			}
		});
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mRecorder.stop();
	}
}
