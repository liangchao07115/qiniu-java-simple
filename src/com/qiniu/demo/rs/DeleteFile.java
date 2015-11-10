package com.qiniu.demo.rs;

import java.io.IOException;

import org.junit.Test;

import com.qiniu.config.Config;
import com.qiniu.util.Auth;
import com.qiniu.util.UrlSafeBase64;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class DeleteFile {
	
	public Auth auth = Auth.create("nlHeJgNUZv66xIOW2kEN4euqOO-hC2tNlIzHm-_0", "WEyGr6lhPEonrJQC029D3Ja9KVp-NhqMnB7W5CP9");	
	
	public void test2(String fileName) throws IOException{		
		
		String EncodedEntryURI = UrlSafeBase64.encodeToString(fileName);
		
		String signingStr =  "/delete/"+EncodedEntryURI+"\n";
		
		String url = "http://rs.qiniu.com/delete/"+EncodedEntryURI;
		
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
	public void test(){
		try {
			test2("test:新建文本文档.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
