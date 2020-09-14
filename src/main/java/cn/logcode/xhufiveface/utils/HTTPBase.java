package cn.logcode.xhufiveface.utils;

import cn.logcode.xhufiveface.config.GsonMessageConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class HTTPBase {
	public static final Log logger = LogFactory.getLog(HTTPBase.class);
	public static final String POST = "POST";
	public static final String GET = "GET";
	
	public String response = "";
	
	public Map<String, List<String>> getResponseHeader() {
		return responseHeader;
	}

	
	public String getResponse() {
		return response;
	}
	
	
	
	public Map<String, Object> header;
	public Map<String, Object> params;

	public Map<String, List<String>> responseHeader;

	public URL url = null;

	public String method;

	public HTTPBase(String response) {
		this.response = response;
	}

	public static class HTTPBuilder {

		public static SSLSocketFactory socketFactory = null;

		public Map<String, Object> header = new HashMap<>();
		public Map<String, Object> params = new HashMap<>();

		public URL url = null;

		public String strUrl;

		public String method = GET;

		public String response;
		
		public boolean https = false;
		
		public URLConnection connection;

		public Map<String, List<String>> responseHeader = new HashMap<>();

		/**
		 * json请求标记
		 */
		public boolean bodyRequestTag = false;

		/**
		 * 是否是传输xml数据格式请求
		 */
		public boolean isXmlData = false;
		
		
		/**
		 * HTTPS SSL 设置证书
		 */
		public void trustAllHosts() {

			try {
				// Create a trust manager that does not validate certificate chains
				TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
					@Override
					public X509Certificate[] getAcceptedIssuers() {
						return new X509Certificate[] {};
					}

					@Override
					public void checkClientTrusted(X509Certificate[] chain, String authType) {
					}

					@Override
					public void checkServerTrusted(X509Certificate[] chain, String authType) {
					}
				} };
				// Install the all-trusting trust manager
				SSLContext sc = SSLContext.getInstance("TLS");
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
				socketFactory = sc.getSocketFactory();
				HttpsURLConnection.setDefaultSSLSocketFactory(socketFactory);
				logger.info("初始化ssl证书成功");
			} catch (Exception e) {
				e.printStackTrace();
				socketFactory = null;
			}
		}

		public final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		public HTTPBuilder() {
			if (socketFactory == null) {
				trustAllHosts();
			}
		}

		public HTTPBuilder url(String url) {
			strUrl = url;
			return this;
		}

		public HTTPBuilder method(String method) {
			this.method = method;
			return this;
		}

		public HTTPBuilder addHeader(String key, Object value) {
			header.put(key, value);
			return this;
		}
		

		public HTTPBuilder addHeader(Map<String, Object> heades) {
			this.header.putAll(heades);
			return this;
		}

		public HTTPBuilder addParams(String key, Object value) {
			this.params.put(key, value);
			return this;
		}

		public HTTPBuilder addParams(Map<String, Object> params) {
			this.params.putAll(params);
			return this;
		}

		public HTTPBuilder bodyRequest(boolean tag) {
			bodyRequestTag = tag;
			return this;
		}
		public HTTPBuilder isXmlData(boolean tag) {
			isXmlData = tag;
			return this;
		}
		
		
		public HTTPBase build() {
			try {
				if (method.equals(GET)) {

					if (strUrl.endsWith("/")) {
						strUrl = strUrl.substring(0, strUrl.length() - 1);
					}

					strUrl = strUrl + "?" + buildFromParam();

				}
				logger.info("开始请求==》"+strUrl);
				
				url = new URL(strUrl);

				if(url.getProtocol().equals("https")) {
					https = true;
				}
				
				this.connection = url.openConnection();

				buildRequsetHeader();

				
				this.response = request();
				
				HTTPBase base = new HTTPBase(response);
				base.header = header;
				base.method = method;
				base.params = params;
				base.url = url;
				
				base.responseHeader = responseHeader;
				
				
				return base;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			throw new NullPointerException();
		}



		public String request(){
			try {
				// TODO Auto-generated method stub
				if(https) {
					HttpsURLConnection conn = (HttpsURLConnection) connection;
					conn.setHostnameVerifier(DO_NOT_VERIFY);
					return https(conn);
				}else {
					HttpURLConnection conn = (HttpURLConnection) connection;
					return http(conn);
				}
			}catch (Exception e) {
				// TODO: handle exception
				return "";
			}
			
		}

		
		public String https(HttpsURLConnection conn) throws Exception {
			conn.setRequestMethod(method);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setReadTimeout(8000);
			conn.setConnectTimeout(5000);

			if (POST.equals(method)) {
				String sendString = "";
				if(bodyRequestTag) {
					if(isXmlData) {
						conn.addRequestProperty("Content-type", "application/xml; charset=UTF-8");
					}else {
						conn.addRequestProperty("Content-type", "application/json; charset=UTF-8");
					}
					sendString = buildBodyParam();
					
//					logger.info("sendString===>"+sendString);
					
				}else {
					conn.addRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
					sendString = buildFromParam();
//					logger.info("sendString===>"+sendString);
				}
				logger.info("发送参数===》"+sendString);
				
				// 获取URLConnection对象对应的输出流
				OutputStream os = conn.getOutputStream();
				// 参数是键值队 , 不以"?"开始
				os.write(sendString.getBytes("UTF-8"));
				os.flush();
				os.close();
			}
			conn.connect();
			
			logger.info(conn.getResponseCode());
			resolveResponseHeader();
			if(conn.getResponseCode() == 200) {
				
				BufferedReader in = null;
				String result = "";
				try {
					in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String line;
					while ((line = in.readLine()) != null) {
						result += line;
					}
					
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally {
					if(in != null) {
						in.close();
					}
				}
				
				return result;
			}
			return "";
		}
		
		public String http(HttpURLConnection conn) throws Exception {
			conn.setRequestMethod(method);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setReadTimeout(8000);
			conn.setConnectTimeout(5000);

			if (POST.equals(method)) {
				String sendString = "";
				if(bodyRequestTag) {
					if(isXmlData) {
						conn.addRequestProperty("Content-type", "application/xml; charset=UTF-8");
					}else {
						conn.addRequestProperty("Content-type", "application/json; charset=UTF-8");
					}
					
					
					sendString = buildBodyParam();
				}else {
					conn.addRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
					sendString = buildFromParam();
				}
				logger.info("发送参数===》"+sendString);
				
				// 获取URLConnection对象对应的输出流
				OutputStream os = conn.getOutputStream();
				// 参数是键值队 , 不以"?"开始
				os.write(sendString.getBytes());
				os.flush();
				os.close();
			}
			conn.connect();
			
			logger.info(conn.getResponseCode());
			resolveResponseHeader();
			if(conn.getResponseCode() == 200) {
				
				BufferedReader in = null;
				String result = "";
				try {
					in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String line;
					while ((line = in.readLine()) != null) {
						result += line;
					}
					
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally {
					if(in != null) {
						in.close();
					}
				}
				
				return result;
			}
			return "";
		}
		
		
		/**
		 * 构建表单请求参数
		 * 
		 * @return
		 */
		public String buildFromParam() {
			String sendString = "";
			Iterator<Entry<String, Object>> it = params.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = it.next();
				sendString += "&";
				sendString += entry.getKey();
				sendString += "=";
				sendString += entry.getValue();
			}

			sendString = sendString.substring(1, sendString.length());
			return sendString;
		}

		/**
		 * 构建请求体参数
		 * 
		 * @return
		 */
		public String buildBodyParam() throws Exception {

//			ObjectMapper mappp = new ObjectMapper();
			
			if(isXmlData) {
				return new String(ApplicationTool.map2XML(params, "xml"),"UTF-8");
			}else {
				return GsonMessageConfig.DEFAULT_GSON.toJson(params);
			}
			
		}

		/**
		 * 构建请求头
		 */
		public void buildRequsetHeader() {

			Iterator<Entry<String, Object>> it = header.entrySet().iterator();

			while (it.hasNext()) {
				Entry<String, Object> entry = it.next();
				this.connection.addRequestProperty(entry.getKey(), entry.getValue().toString());
			}
		}

		/**
		 * 解析响应中的响应头
		 */
		public void  resolveResponseHeader() {
			responseHeader = this.connection.getHeaderFields();
		}
		
		
		
	}

	public Map<String, Object> getHeader() {
		return header;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public URL getUrl() {
		return url;
	}

	public String getMethod() {
		return method;
	}

}
