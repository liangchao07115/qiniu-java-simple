package com.qiniu.fusion;
import java.io.IOException;
import java.util.HashMap;

import org.junit.Test;

import com.google.gson.Gson;
import com.qiniu.config.Config;
import com.qiniu.util.Auth;
import com.qiniu.util.Json;
import com.qiniu.util.StringMap;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class Refresh {

	public Auth auth = Auth.create(Config.ak,Config.sk);	
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	
	@Test
	public void fresh(String str) throws IOException{			
		String signingStr =  "/refresh\n";		
		String url = "http://fusion.qiniuapi.com/refresh";		
		String access_token = auth.sign(signingStr);
		
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
}
