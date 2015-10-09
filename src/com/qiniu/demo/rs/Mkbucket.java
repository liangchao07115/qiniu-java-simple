package com.qiniu.demo.rs;

import java.io.IOException;

import org.junit.Test;

import com.qiniu.util.Auth;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class Mkbucket {
	
	public String ak = "kq0nTUwXHRkfz5ipg_krWgGFYhMAZCTSf21IdjEu";
	
	public String sk = "sZHaqTsn72cYaywvwQC9i3KrJpbRQYvt3_GV4L-0";
	
	public Auth auth = Auth.create(ak , sk );
	
	@Test
	public void test2() throws IOException{
		
		String path = "/mkbucket2/te011/public/0\n";
	
		String access_token = auth.sign(path);
		
		System.out.println(access_token);
		
		String url = "http://rs.qiniu.com/mkbucket2/te011/public/0";				
		
		OkHttpClient client = new OkHttpClient();		

		Request request = new Request.Builder().url(url)
				.addHeader("Content-Type", "application/x-www-form-urlencoded")
				.addHeader("Authorization", "QBox " + access_token).build();

		Response re = client.newCall(request).execute();

		if (re.isSuccessful() == true) {
			System.out.println(re.code());
			System.out.println(re.toString());
		} else {
			System.out.println(re.code());
		}		
	}	

}
