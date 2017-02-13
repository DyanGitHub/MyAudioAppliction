package com.dy.myaudioappliction.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dy.myaudioappliction.R;
import com.dy.myaudioappliction.localmedia.FileInfo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public MainActivity mActivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mActivity=this;
		findViewById(R.id.picklocal).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(mActivity,PickActivity.class));
			}
		});
		findViewById(R.id.recorde).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				RecordeActivity.newInstance(mActivity);
			}
		});
		findViewById(R.id.convert).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {ArrayList<FileInfo> list=new ArrayList<>();
				FileInfo fileInfo=new FileInfo();
				//测试
				fileInfo.setFilePath("/storage/emulated/0/tencent/MicroMsg/Download/董燕.m4a");
				list.add(fileInfo);
				TranscodeActivity.newInstance(mActivity,list);
			}
		});
		findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				PlayActivity.newInstance(mActivity);
			}
		});
	}
}
