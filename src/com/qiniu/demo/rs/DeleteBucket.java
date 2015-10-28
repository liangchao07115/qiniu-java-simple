package com.qiniu.demo.rs;

import java.io.IOException;

import org.junit.Test;

import com.qiniu.config.Config;
import com.qiniu.util.Auth;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class DeleteBucket {	
	
	public Auth auth = Auth.create(Config.ak, Config.sk);	
	
	public void test2(String bucketName) throws IOException{		
			
		String signingStr =  "/drop/"+bucketName+"\n";
		
		String url = "http://rs.qiniu.com/drop/"+bucketName;
		
		String access_token = auth.sign(signingStr);
				
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
	@Test
	public void tets() throws IOException{
		test2("test2");
	}
}
