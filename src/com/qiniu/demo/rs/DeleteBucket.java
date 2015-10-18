package com.qiniu.demo.rs;

import java.io.IOException;

import org.junit.Test;

import com.qiniu.common.Config;
import com.qiniu.util.Auth;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class DeleteBucket {
	
	
	public Auth auth = Auth.create(Config.ak, Config.sk);	
	
	@Test
	public void test2() throws IOException{		
		
		String signingStr =  "/drop/tes01\n";
		
		String url = "http://rs.qiniu.com/drop/tes01";
		
		String access_token = auth.sign(signingStr);
		

		System.out.println(access_token);
		
		OkHttpClient client = new OkHttpClient();		

		Request request = new Request.Builder().url(url)
				.addHeader("Content-Type", "application/x-www-form-urlencoded")
				.addHeader("Authorization", "QBox " +access_token).build();

		Response re = client.newCall(request).execute();

		if (re.isSuccessful() == true) {
			System.out.println(re.code());
		} else {
			System.out.println(re.code());
		}		
		
	}	
}
