package com.dy.myaudioappliction.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.dy.myaudioappliction.R;
import com.dy.myaudioappliction.localmedia.FileInfo;
import com.dy.myaudioappliction.localmedia.PickUtil;
import com.dy.myaudioappliction.util.Logger;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 作者： Dyan on 2017/2/7 15:03
 * 描述： 选择本地音频
 */
public class PickActivity extends Activity {
	PickActivity mActivity;
	ListView mDataList;
	ArrayList<FileInfo> localAudios;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pick);
		mActivity = this;
		initView();
	}

	private void initView() {
		mDataList = (ListView) findViewById(R.id.list);
		localAudios = PickUtil.queryAllAudio(mActivity);
		mDataList.setAdapter(myAdapter);
		findViewById(R.id.tomp3).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ArrayList<FileInfo> files = new ArrayList<>();
				for (FileInfo file : localAudios) {
					if (file.isChecked())
						files.add(file);
				}
				if (files.size() != 0)
					TranscodeActivity.newInstance(mActivity, files);

			}
		});
	}

	BaseAdapter myAdapter = new BaseAdapter() {
		@Override
		public int getCount() {
			if (localAudios == null)
				return 0;
			return localAudios.size();
		}

		@Override
		public Object getItem(int i) {
			if (localAudios == null)
				return null;
			return localAudios.get(i);
		}

		@Override
		public long getItemId(int i) {
			if (localAudios == null)
				return 0;
			return i;
		}

		@Override
		public View getView(final int i, View view, ViewGroup viewGroup) {
			if (view == null) {
				view = LayoutInflater.from(mActivity).inflate(R.layout.item_audiopick, null);
				view.setTag(new ViewHolder(view));
			}
			ViewHolder holder = (ViewHolder) view.getTag();
			if (holder == null)
				holder = new ViewHolder(view);
			FileInfo fileInfo = localAudios.get(i);
			holder.setText(R.id.title, fileInfo.getFileName());
			holder.setText(R.id.date, PickUtil.changStr(fileInfo.getAddDate()));
			holder.setText(R.id.duration, PickUtil.millsecondsToStr(fileInfo.getFileDuration()));
			holder.setCheck(R.id.check, fileInfo.isChecked());
			final ViewHolder finalHolder = holder;
			holder.setOnclickListener(R.id.check, new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					boolean isCheck = localAudios.get(i).isChecked();
					finalHolder.setCheck(R.id.check, !isCheck);
					localAudios.get(i).setChecked(!isCheck);
				}
			});
			return view;
		}
	};
	/*
	   播放指定曲
	 */
	MediaPlayer player;
	private void play(String path) {
		Logger.d("path:"+path);
		if(player==null)
		{
			player=new MediaPlayer();
			player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
				@Override
				public void onPrepared(MediaPlayer mediaPlayer) {
					player.start();
				}
			});
		}
		if(player.isPlaying())
		{
			player.stop();
			player.reset();
		}

		try {
			player.setDataSource(path);
			player.prepareAsync();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class ViewHolder {
		View mainview;
		SparseArray<View> mViews = new SparseArray<>();

		public ViewHolder(View view) {
			this.mainview = view;
		}

		/*
	   ui获取
	 */
		public View getView(int id) {
			View view;
			view = mViews.get(id);
			if (view == null) {
				view = mainview.findViewById(id);
				mViews.put(id, view);
			}
			return view;
		}

		public void setText(int id, String text) {
			TextView view = (TextView) getView(id);
			view.setText(text);
		}

		public void setCheck(int id, boolean isCheck) {
			CheckBox view = (CheckBox) getView(id);
			view.setChecked(isCheck);
		}

		public void setOnclickListener(int id, View.OnClickListener listener) {
			View view = getView(id);
			view.setOnClickListener(listener);
		}

	}
}
