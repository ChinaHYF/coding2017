package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionManager;

public class ConnectionImpl implements Connection
{
	private URLConnection urlConnection;
	
	private ConnectionManagerImpl cm;
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		if(this.getUrlConnection() != null)
		{
			InputStream sm = this.getUrlConnection().getInputStream();
			byte[] buffer = new byte[endPos - startPos + 1];
			sm.read(buffer, 0, buffer.length);
			if(buffer.length > 0)
			{
				this.cm.write(buffer);
			}
			return buffer;
		}
		return null;
	}

	@Override
	public int getContentLength() {
		if(this.getUrlConnection() != null)
		{
			return this.getUrlConnection().getContentLength();
		}
		return 0;
	}

	@Override
	public void close() {
		//this.getUrlConnection().
		
	}

	public URLConnection getUrlConnection() {
		return urlConnection;
	}

	public void setUrlConnection(URLConnection urlConnection) {
		this.urlConnection = urlConnection;
	}
	
	public void setCm(ConnectionManagerImpl cm)
	{
		this.cm = cm;
	}

}
