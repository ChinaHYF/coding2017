package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{

	private URLConnection conn;
	private int startPos = 0, length = 0;
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		setStartPos(startPos);
		setLength(endPos - startPos + 1);
		//conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		//conn.connect();
		InputStream is = conn.getInputStream();
		byte[] buffer = new byte[getLength()];
		int offset = 0;
		int bytesRead = 0;
		while(offset < getLength())
		{
			
			bytesRead = is.read(buffer, offset, getLength() - offset);
			System.out.println("read " + bytesRead + " bytes with " + this.toString());
			if(bytesRead <= 0)
			{
				break;
			} 
			offset += bytesRead;
		}
		is.close();
		return buffer;
	}

	@Override
	public int getContentLength() {
		return conn.getContentLength();
	}

	@Override
	public void close() {}

	public URLConnection getConn() {
		return conn;
	}

	public void setConn(URLConnection conn) {
		this.conn = conn;
	}

	public int getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
