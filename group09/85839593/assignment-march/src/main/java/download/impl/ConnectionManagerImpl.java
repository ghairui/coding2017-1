package download.impl;

import download.api.Connection;
import download.api.ConnectionException;
import download.api.ConnectionManager;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
        try {
            // 构造URL
            URL resourceurl = new URL(url);
            // 打开连接
            URLConnection con = resourceurl.openConnection();
            //设置请求超时为5s
            con.setConnectTimeout(5*1000);
            con.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
	}

}
