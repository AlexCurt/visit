package cn.lrcb.visit.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.json.JSONObject;

public class HttpUtil {
	
	//请求超时时间，这个时间定义了socket读数据的超时时间，也就是连接到服务器之后从服务器获取响应数据需要等待的时间，发生超时，会抛出SocketTimeoutException异常。
	private static final int SOCKET_TIME_OUT = 60000;
	//连接超时时间，这个时间定义了通过网络与服务器建立连接的超时时间，也就是取得了连接池中的某个连接之后到接通目标url的连接等待时间。发生超时，会抛出ConnectionTimeoutException异常
	private static final int CONNECT_TIME_OUT = 60000;
	
	/**
	 * get请求
	 * @param url
	 * @param nvps
	 * @return
	 */
	public static String doGet(String url,List<NameValuePair> nvps) {
		String result = null;
		CloseableHttpResponse response = null;
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			URIBuilder builder = new URIBuilder(url);
			builder.setParameters(nvps);
			HttpGet httpGet = new HttpGet(builder.build());
			httpGet.setHeader("Authorization", "Basic emdqOjEyMw==");
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, "utf-8");
//			System.out.println(result);
	        JSONObject jsonObj = new JSONObject(result);
			int number=jsonObj.getInt("totalResults");
			result = String.valueOf(number);
		} catch (ClientProtocolException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (URISyntaxException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				response.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return result;
	}
	
   /**
    * get请求
    * @param url
    * @param params
    * @return
    * @throws Exception
    */
	public static String doPost(String url,String params) throws Exception {
		String charSet = "UTF-8";
		HttpEntity responseEntity = null;
		String jsonString = "";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建HttpPost
		HttpPost httpPost = new HttpPost(url);
		//设置传输json格式数据
		//setConfig,添加配置，如果设置请求超时时间，连接超时时间
		RequestConfig reqConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIME_OUT).setConnectTimeout(CONNECT_TIME_OUT).build();
		httpPost.setConfig(reqConfig);
		
		StringEntity entity = new StringEntity(String.valueOf(params), charSet);
		httpPost.setEntity(entity);
		CloseableHttpResponse response = null;
		
		try {
			response = httpClient.execute(httpPost);
			if (response!=null) {
				StatusLine status = response.getStatusLine();
				int state = status.getStatusCode();
				if (state ==HttpStatus.SC_OK) {
					responseEntity = response.getEntity();
					jsonString = EntityUtils.toString(responseEntity);
					System.out.println("==========================请求返回：======================");
					return jsonString;
				} else {
					System.out.println("=================请求失败:返回状态为:=============="+state+"("+url+")");
				}
			} else {
				throw new Exception();
			}
		} finally {
			EntityUtils.consume(responseEntity);
			if(response!=null) {
				try {
					response.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			try {
				httpClient.close();
			}catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} 
		return jsonString;
	}
}
