package com.coderising.download;

import java.io.IOException;

import com.coderising.download.api.Connection;
import com.coderising.download.api.DownloadListener;

public class DownloadThread extends Thread
{
	Connection conn;
	int startPos;
	int endPos;
	
	private DownloadListener listener;

	public DownloadThread(Connection conn, int startPos, int endPos)
	{
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	
	public void run()
	{	
		try {
			conn.read(this.startPos, this.endPos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.listener.notifyFinished();
		}
	}
	
	public void addListener(DownloadListener listener)
	{
		this.listener = listener;
	}
}
