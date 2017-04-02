package com.coderising.download.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException 
	{
		ConnectionImpl connection = new ConnectionImpl();
		try {
			URLConnection urlConnection = new URL(url).openConnection();
			connection.setUrlConnection(urlConnection);
			connection.setCm(this);
			return connection;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public synchronized void write(byte[] data)
	{
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("test.jpg");
			fos.write(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
