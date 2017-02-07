package com.dy.myaudioappliction.localmedia;

import java.io.Serializable;

public class FileInfo implements Serializable {
	String filePath;
	String fileName;
	long fileSize;
	int fileDuration;
//	Bitmap thumbnail;//audio生成封面有时会OOM
	long addDate;//2016年05月26日


	public void setAddDate(long addDate) {
		this.addDate = addDate;
	}

	public long getAddDate() {
		return addDate;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public void setFileDuration(int fileDuration) {
		this.fileDuration = fileDuration;
	}

	public int getFileDuration() {
		return fileDuration;
	}

	public long getFileSize() {
		return fileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	//业务外的参数(协助RecycleView显示的)
	private boolean isChecked=false;

	public void setChecked(boolean checked) {
		isChecked = checked;
	}

	public boolean isChecked() {
		return isChecked;
	}
}