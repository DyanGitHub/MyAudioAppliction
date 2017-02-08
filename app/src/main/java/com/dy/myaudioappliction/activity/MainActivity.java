package com.dy.myaudioappliction.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dy.myaudioappliction.R;

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
				startActivity(new Intent(mActivity, RecordeActivity.class));
			}
		});
	}
}
