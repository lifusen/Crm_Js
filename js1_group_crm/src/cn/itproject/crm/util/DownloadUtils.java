package cn.itproject.crm.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadUtils {

	public DownloadUtils() {
	}

	public static void saveToDisk(String URL_PATH, String name) {
		// 获取输入流
		InputStream inputStream = getInputStream(URL_PATH);

		byte[] date = new byte[1024];
		int len = 0;
		FileOutputStream fileOutputStream = null;

		try {
			fileOutputStream = new FileOutputStream("E://testDownload/" + name + ".jpg");

			while ((len = inputStream.read(date)) != -1) {
				fileOutputStream.write(date, 0, len);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (inputStream != null) {
					inputStream.close();

				}

				if (fileOutputStream != null) {
					fileOutputStream.close();

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * @return
	 */
	public static InputStream getInputStream(String URL_PATH) {
		InputStream inputStream = null;
		HttpURLConnection httpURLConnection = null;

		try {
			URL url = new URL(URL_PATH);
			httpURLConnection = (HttpURLConnection) url.openConnection();
			// 设置连接网络的超时时间
			httpURLConnection.setConnectTimeout(3000);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setRequestMethod("GET");

			int responseCode = httpURLConnection.getResponseCode();
			if (responseCode == 200) {
				inputStream = httpURLConnection.getInputStream();

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return inputStream;
	}

	// public static void main(String[] args) {
	// DownloadUtils downloadUtils = new DownloadUtils();
	//// downloadUtils.saveToDisk(URL_PATH);
	//
	// }

}
