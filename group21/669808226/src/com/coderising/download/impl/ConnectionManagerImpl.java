package com.coderising.download.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;

public class ConnectionManagerImpl implements ConnectionManager {
	
	@Override
	public Connection open(String url, int startPos, int endPos) throws ConnectionException {
		URLConnection urlConn;
		try
		{
			urlConn = new URL(url).openConnection();
			urlConn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
			ConnectionImpl conn = new ConnectionImpl();
			conn.setConn(urlConn);
			return conn;
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

}
