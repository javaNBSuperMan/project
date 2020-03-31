package com.example.project.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

/**
 * Http请求工具类
 * 
 * @author 王信雨
 */
public class HttpUtils {

	public static void main(String[] args) throws Exception {
		String path = "http://192.168.101.142:8080/website/api/token";
		Map<String, String> params = new HashMap<String, String>();
		params.put("clientId", "jq7WEH4NcUyub8XMApecDGAfd37O0NPK");
		params.put("clientSecret", "4028a8b45a6f0d52015a6fe2e991003b");
		String result = post(path, params, null, "UTF-8");
		System.err.println(result);
	}

	/**
	 * GET请求
	 * 
	 * @param path 地址
	 * @param params 参数Map
	 * @param headers 头部请求Map
	 * @param charset 编码
	 */
	public static String get(String path, Map<String, String> params, Map<String, String> headers, String charset) throws Exception {
		path = gethPath(path, params);
		HttpGet method = new HttpGet(path);
		// 默认超时时间为5s。
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).setConnectionRequestTimeout(20000).setStaleConnectionCheckEnabled(true).build();
		method.setConfig(requestConfig);
		return getMethod(method, headers, charset);
	}

	/**
	 * POST请求
	 * 
	 * @param path 地址
	 * @param params 参数Map
	 * @param headers 头部请求Map
	 * @param charset 编码
	 */
	public static String post(String path, Map<String, String> params, Map<String, String> headers, String charset) throws Exception {
		HttpPost method = new HttpPost(path);
		// 默认超时时间为5s。
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).setConnectionRequestTimeout(20000).setStaleConnectionCheckEnabled(true).build();
		// 请求的参数信息传递
		List<NameValuePair> paires = new ArrayList<NameValuePair>();
		if (params != null) {
			Set<String> keys = params.keySet();
			Iterator<String> iterator = keys.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				paires.add(new BasicNameValuePair(key, URLDecoder.decode(params.get(key), charset)));
			}
		}
		if (paires.size() > 0) {
			HttpEntity entity = new UrlEncodedFormEntity(paires, charset);
			method.setEntity(entity);
		}
		method.setConfig(requestConfig);
		return postMethod(method, params, headers, charset);
	}

	// get获取页面代码
	private static String getMethod(HttpUriRequest method, Map<String, String> headers, String charset) throws Exception {
		HttpClient client = HttpClients.createDefault();
		method.setHeader("charset", charset);
		if (headers != null) {
			Set<String> keys = headers.keySet();
			Iterator<String> iterator = keys.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				method.setHeader(key, headers.get(key));
			}
		}
		HttpResponse response = client.execute(method);
		int status = response.getStatusLine().getStatusCode();
		if (status < 200 || status >= 300) {
			// throw new ClientProtocolException("Path:" + method.getURI() + "-Unexpected response status: " + status);
			return null;
		}
		HttpEntity entity = response.getEntity();
		String body = EntityUtils.toString(entity, charset);
		return body;
	}

	// post获取页面代码
	private static String postMethod(HttpUriRequest method, Map<String, String> params, Map<String, String> headers, String charset) throws Exception {
		HttpClient client = HttpClients.createDefault();
		method.setHeader("charset", charset);

		if (headers != null) {
			Set<String> keys = headers.keySet();
			Iterator<String> iterator = keys.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				method.setHeader(key, headers.get(key));
			}
		}

		HttpResponse response = client.execute(method);
		int status = response.getStatusLine().getStatusCode();
		if (status < 200 || status >= 300) {
			// throw new ClientProtocolException("Path:" + method.getURI() + "-Unexpected response status: " + status);
			return null;
		}
		HttpEntity entity = response.getEntity();
		String body = EntityUtils.toString(entity, charset);
		return body;
	}

	/**
	 * 获取页面代码流
	 * 
	 * @param path url地址
	 */
	public static InputStream GetString(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(3 * 1000);
		conn.setRequestProperty("Accept-Language", "zh-CN");
		conn.setRequestProperty("User-Agent", "User-Agent:Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setUseCaches(false);// 不进行缓存
		if (conn.getResponseCode() == 200) {
			return conn.getInputStream();
		}
		return null;
	}

	/**
	 * 组装path
	 * 
	 * @param path
	 * @param params 参数map
	 */
	private static String gethPath(String path, Map<String, String> params) {
		if (params != null) {
			if (path.indexOf("?") > -1) {
				path += "&";
			} else {
				path += "?";
			}
			Set<String> keys = params.keySet();
			Iterator<String> iterator = keys.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				path += key + "=" + params.get(key) + "&";
			}
		}
		return path;
	}

	public static int testWsdlConnection(String address) {
		int status = -1;
		try {
			URL urlObj = new URL(address);
			HttpURLConnection oc = (HttpURLConnection) urlObj.openConnection();
			oc.setUseCaches(false);
			oc.setConnectTimeout(3000); // 设置超时时间
			status = oc.getResponseCode();// 请求状态
		} catch (Exception e) {
			status = 500;
			System.err.println(e.getMessage());
		}
		return status;
	}

	/**
	 * 响应内容
	 * 
	 * @param response
	 * @param json
	 * @exception Exception
	 */
	public static void renderToJson(HttpServletResponse response, String json) {
		// 是否设置不缓存
		response.setDateHeader("Expires", 0);
		response.setHeader("Expires", "0");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
		try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * unicode转中文
	 * 
	 * @param unicodeString
	 * @return
	 */
	public static String decodeUnicode(String unicodeString) {
		char aChar;
		int len = unicodeString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = unicodeString.charAt(x++);
			if (aChar == '\\') {
				aChar = unicodeString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = unicodeString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';

					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();

	}

}
