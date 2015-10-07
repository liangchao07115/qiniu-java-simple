package com.qiniu.demo.rs;

import java.io.IOException;

import org.junit.Test;

import com.qiniu.util.Auth;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class DeleteBucket {
	
	public String ak = "kq0nTUwXHRkfz5ipg_krWgGFYhMAZCTSf21IdjEu";
	
	public String sk = "sZHaqTsn72cYaywvwQC9i3KrJpbRQYvt3_GV4L-0";
	
	public Auth auth = Auth.create(ak , sk );
	
	@Test
	public void test2() throws IOException{		
		
		String signingStr =  "/drop/showvideo\n";
		
		String url = "http://rs.qiniu.com/drop/showvideo";
		
		String access_token = auth.sign(signingStr);
		
		OkHttpClient client = new OkHttpClient();		

		//RequestBody formBody = new FormEncodingBuilder().add("email", "**").add("password", "**").build();

		Request request = new Request.Builder().url(url)
				.addHeader("Content-Type", "application/x-www-form-urlencoded")
				.addHeader("Authorization", "QBox " + access_token).build();

		Response re = client.newCall(request).execute();

		if (re.isSuccessful() == true) {
			System.out.println(re.code());
		} else {
			System.out.println(re.code());
		}		
	}	
}
