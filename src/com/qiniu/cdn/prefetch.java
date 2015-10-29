package com.qiniu.cdn;

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

public class prefetch {	
	public Auth auth = Auth.create(Config.ak, Config.sk);
	
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	
	@Test
	public void test2() throws IOException{			
		String signingStr =  "/prefetch\n";		
		String url = "http://fusion.qiniuapi.com/prefetch";		
		String access_token = auth.sign(signingStr);

		System.out.println(access_token);
		
		OkHttpClient client = new OkHttpClient();		
		
		//url需要数组类型 StringMap是java-sdk中封装的方法，可以查看下源码
		StringMap map = new StringMap();
		
		String[] str = new String[]{"http://7xj50c.com1.z0.glb.clouddn.com/013","http://7xj50c.com1.z0.glb.clouddn.com/012"};
		
		map.put("urls", str);
		
		//map转json
		String s = Json.encode(map);
		
		RequestBody body = RequestBody.create(JSON,s);
				
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
