package com.qiniu.fusion;

import java.io.IOException;

import org.junit.Test;

import com.qiniu.config.Config;
import com.qiniu.util.Auth;
import com.qiniu.util.Json;
import com.qiniu.util.StringMap;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class Prefetch {	
	
	public Auth auth = Auth.create(Config.ak, Config.sk);
	
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	
	@Test
	public void pre(String str) throws IOException{	
		
		String signingStr =  "/prefetch\n";		
		String url = "http://fusion.qiniuapi.com/prefetch";			
		String access_token = auth.sign(signingStr);
		System.out.println(access_token);		
		OkHttpClient client = new OkHttpClient();		
				
		RequestBody body = RequestBody.create(JSON,str);		
		Request request = new Request.Builder()
				.url(url)
				.addHeader("Content-Type", "application/json")
				.addHeader("Authorization", "QBox " +access_token)
				.post(body)
				.build();

		Response re = client.newCall(request).execute();

		System.out.println(re.body().string());		
	}	
	
	
	@Test
	public void getStatus(String requestId){
		String signingStr = "/prefetch/query?requestId="+requestId+"\n";		
		String url = "http://fusion.qiniuapi.com/prefetch/query";			
		String access_token = auth.sign(signingStr);
		
		OkHttpClient client = new OkHttpClient();		
			
		Request request = new Request.Builder()
				.url(url+"?requestId="+requestId)//+ requestId
				.addHeader("Authorization", "QBox " +access_token)
				.build();
		Response re;
		try {
			re = client.newCall(request).execute();
			System.out.println(re.body().string());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
}
