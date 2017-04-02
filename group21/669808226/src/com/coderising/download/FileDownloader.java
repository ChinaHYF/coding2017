package com.coderising.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.ConnectionImpl;

public class FileDownloader {
	
	String url;
	
	DownloadListener listener;
	
	ConnectionManager cm;
	
	List<DownloadThread> threadList = new ArrayList<DownloadThread>();
	
	private final int THREAD_COUNT = 3;

	public FileDownloader(String _url) {
		this.url = _url;
	}
	
	public void execute()
	{
		// 在这里实现你的代码， 注意： 需要用多线程实现下载
		// 这个类依赖于其他几个接口, 你需要写这几个接口的实现代码
		// (1) ConnectionManager , 可以打开一个连接，通过Connection可以读取其中的一段（用startPos, endPos来指定）
		// (2) DownloadListener, 由于是多线程下载， 调用这个类的客户端不知道什么时候结束，所以你需要实现当所有
		//     线程都执行完以后， 调用listener的notifiedFinished方法， 这样客户端就能收到通知。
		// 具体的实现思路：
		// 1. 需要调用ConnectionManager的open方法打开连接， 然后通过Connection.getContentLength方法获得文件的长度
		// 2. 至少启动3个线程下载，  注意每个线程需要先调用ConnectionManager的open方法
		// 然后调用read方法， read方法中有读取文件的开始位置和结束位置的参数， 返回值是byte[]数组
		// 3. 把byte数组写入到文件中
		// 4. 所有的线程都下载完成以后， 需要调用listener的notifiedFinished方法
		
		// 下面的代码是示例代码， 也就是说只有一个线程， 你需要改造成多线程的。
		URLConnection _conn;
		try {
			_conn = new URL(this.url).openConnection();
			int length = _conn.getContentLength();
			int sectionLen = length / THREAD_COUNT;
			int frag = length % THREAD_COUNT;
			
			for(int i=0; i<THREAD_COUNT; ++i)
			{
				try {
					int startPos = i * sectionLen;
					int endPos = startPos + sectionLen - 1;
					if(i == THREAD_COUNT -1)
					{
						endPos += frag;
					}
					Connection conn = cm.open(this.url, startPos, endPos);
					DownloadThread t = new DownloadThread(conn,startPos,endPos);
					t.setOnFinish(new ThreadCompleteListener(){
						@Override
						public void notifyOfThreadComplete(DownloadThread thread) {
							// TODO Auto-generated method stub
							try {
								write(thread);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
					threadList.add(t);
					t.start();
				} catch (ConnectionException e) {			
					e.printStackTrace();
				}
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
				 
	}
	
	public synchronized void write(DownloadThread thread) throws IOException
	{
		File f = new File("test.jpg");
		if(!f.exists())
		{
			f.createNewFile();
		}
		RandomAccessFile raf = new RandomAccessFile("test.jpg", "rw");
		raf.seek(thread.getStartPos());
		raf.write(thread.getData(), 0, thread.getEndPos() - thread.getStartPos() + 1);
		threadList.remove(thread);
		if(threadList.isEmpty())
		{
			listener.notifyFinished();
		}
		raf.close();
	}
	
	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	public void setConnectionManager(ConnectionManager ucm){
		this.cm = ucm;
	}
	
	public DownloadListener getListener(){
		return this.listener;
	}
}