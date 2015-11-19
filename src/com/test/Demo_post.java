package com.test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import okio.BufferedSink;

public class Demo_post {
	
	public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown;charset=utf-8");
	
	private final OkHttpClient client = new OkHttpClient();
	
	@Test
	public void test04(){
		System.out.println(System.nanoTime());
	}
	
	@Test
	public void test02() throws IOException{
		File file = new File("C:"+File.separator+"charless"+ File.separator+"test.mp4");

		final long startNanos = System.nanoTime();
		
		client.setConnectTimeout(30, TimeUnit.SECONDS);
		
		//Tag tag = new Tag();
		
		Request request = new Request.Builder()
		    .url("https://api.github.com/markdown/raw")
		    .tag("tag")
		    .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
		    .build();

		Response response = client.newCall(request).execute();
		if (response.isSuccessful()){
			System.out.println("ok!");
		}

		System.out.println(response.body().string());
	}
	
	@Test // post stream
	public void test01() throws IOException{
		RequestBody requestBody = new RequestBody() {
			  @Override public MediaType contentType() {
			    return MEDIA_TYPE_MARKDOWN;
			  }

			  @Override public void writeTo(BufferedSink sink) throws IOException {
			    sink.writeUtf8("Numbers\n");
			    sink.writeUtf8("-------\n");
			    for (int i = 2; i <= 997; i++) {
			      sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
			    }
			  }

			  private String factor(int n) {
			    for (int i = 2; i < n; i++) {
			      int x = n / i;
			      if (x * i == n) return factor(x) + " × " + i;
			    }
			    return Integer.toString(n);
			  }
			};

			Request request = new Request.Builder()
			    .url("https://api.github.com/markdown/raw")
			    .post(requestBody)
			    .build();

			Response response = client.newCall(request).execute();
			if (!response.isSuccessful()){
				System.out.println("ok!");
			}

			System.out.println(response.body().string());
	}
	
	@Test //post string 
	public void test() throws IOException{
		String postBody = ""		
	    + "Releases\n"
	    	    + "--------\n"
	    	    + "\n"
	    	    + " 1. _1.0_ May 6, 2013\n"
	    	    + " 2. _1.1_ June 15, 2013\n"
	    	    + " 3. _1.2_ August 11, 2013\n";

	    	Request request = new Request.Builder()
	    	    .url("https://api.github.com/markdown/raw")
	    	    .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
	    	    .build();

	    	Response response = client.newCall(request).execute();
	    	if (!response.isSuccessful()){
	    		System.out.println("ok!");
	    	}

	    	System.out.println(response.body().string());
		
	}

}
