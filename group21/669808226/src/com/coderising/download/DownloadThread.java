package com.coderising.download;

import java.io.IOException;

import com.coderising.download.api.Connection;

interface ThreadCompleteListener {
    void notifyOfThreadComplete(final DownloadThread thread);
}

public class DownloadThread extends Thread
{
	Connection conn;
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	int startPos;
	public int getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	int endPos;
	
	public int getEndPos() {
		return endPos;
	}

	public void setEndPos(int endPos) {
		this.endPos = endPos;
	}

	private ThreadCompleteListener onFinish;
	private byte[] data;

	public DownloadThread(Connection conn, int startPos, int endPos){
		setConn(conn);		
		setStartPos(startPos);
		setEndPos(endPos);
	}
	
	public void run(){	
		try {
			setData(conn.read(this.startPos, this.endPos));
			getOnFinish().notifyOfThreadComplete(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ThreadCompleteListener getOnFinish() {
		return onFinish;
	}

	public void setOnFinish(ThreadCompleteListener onFinish) {
		this.onFinish = onFinish;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
