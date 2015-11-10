package com.test;

import java.io.IOException;

import org.junit.Test;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.framed.FrameReader.Handler;
import com.squareup.okhttp.internal.framed.Header;

public class Demo_01 {
	
	//Synchronous Get
	public String getSynRequest(String url){
		OkHttpClient client = new OkHttpClient();
		
		Request request = new Request.Builder().url(url).build();
		
		try {
			Response response = client.newCall(request).execute();
			if(!response.isSuccessful()){
				System.out.println("noOK");
			}else{
				System.out.println("Ok");
				Headers headers = response.headers();
				for(int i = 0; i < headers.size(); i++){
					System.out.println(headers.name(i)+":"+headers.value(i));
				}
			}
			return response.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return new String("NO....");
		}
		
	}
	
	
	//Asynchronous Get	
	public String getAsynRequest(String url){
		OkHttpClient client = new OkHttpClient();
		
		Request request = new Request.Builder().url(url).build();
		
		client.newCall(request).enqueue(new Callback() {			
			@Override
			public void onResponse(Response response) throws IOException {
				System.out.println("duan!");
				if(!response.isSuccessful()){
					throw new IOException("unexpection code" + response);
				}	
				System.out.println("duan!");
				Headers headers = response.headers();
				for(int i = 0 ; i < headers.size(); i++){
					System.out.println(headers.name(i)+":"+headers.value(i));
				}
				System.out.println(response.body().string());				
			}
			
			@Override
			public void onFailure(Request arg0, IOException arg1) {
				System.out.println("Error");				
			}
		});		
		return new String("Yes!");
	}
	
	@Test
	public void test1(){
		String te = getAsynRequest("http://7xo0hi.com1.z0.glb.clouddn.com/node");
		System.out.println(te);
	}
	
	
	@Test
	public void test(){
		String te = getSynRequest("http://7xo0hi.com1.z0.glb.clouddn.com/node");
		System.out.println(te);
	}
			
	
}
