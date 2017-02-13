package com.dy.myaudioappliction.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dy.myaudioappliction.R;
import com.dy.myaudioappliction.localmedia.FileInfo;

import java.io.File;
import java.util.ArrayList;

/**
 * 作者： Dyan on 2017/2/7 16:22
 * 描述：转码页面
 */
public class TranscodeActivity extends Activity {
	ArrayList<FileInfo> infos;
	TranscodeActivity mActivity;
//	TranscodeUtil mTranscodeUtil;
	private ProgressDialog pd;
	private String wavPath,mp3Path;
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
//		mTranscodeUtil=new TranscodeUtil();
		mActivity=this;
		initData();
		initView();
	}

	private void initView() {
		((TextView)findViewById(R.id.input)).setText(wavPath);
		((TextView)findViewById(R.id.output)).setText(mp3Path);
		pd=new ProgressDialog(mActivity);
	}

	//获取LAME版本号
	public void  getVersion(View view)
	{
//		Toast.makeText(mActivity, mTranscodeUtil.getLameVersion(),Toast.LENGTH_SHORT).show();
	}
	//转码
	public void convert(View view)
	{
		File file = new File(wavPath);
		int size = (int) file.length();
		System.out.println("文件大小 " + size);
		if ("".equals(mp3Path) || "".equals(wavPath)) {
			Toast.makeText(mActivity, "路径不能为空", Toast.LENGTH_LONG).show();
			return;
		}
		pd.setMessage("转换中....");
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setMax(size); // 设置进度条的最大值
		pd.setCancelable(false);
		pd.show();
		// 转码是个耗时的操作，所以这里需要开启新线程去执行
		new Thread() {

			@Override
			public void run() {
//				FLameUtils lameUtils = new FLameUtils(1, 44100, 32);
////
//				lameUtils.raw2mp3(wavPath, mp3Path);
//				mTranscodeUtil.convertmp3(wavPath, mp3Path);
				pd.dismiss();
			}

		}.start();
	}
	/**
	 * 设置进度条的进度，提供给C语言调用
	 *
	 * @param progress
	 */
	public void setConvertProgress(int progress) {
		pd.setProgress(progress);
	}


	private void initData() {
		infos= (ArrayList<FileInfo>) getIntent().getSerializableExtra("infos");
		wavPath=infos.get(0).getFilePath();
		mp3Path=wavtomps(wavPath);
	}
	private String wavtomps(String waspath)
	{
		return waspath.substring(0,waspath.lastIndexOf("."))+".mp3";
	}

}
