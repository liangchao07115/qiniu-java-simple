package com.qiniu.demo.upload;

import java.io.File;

import org.junit.Test;

import com.google.gson.JsonObject;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.Json;
import com.qiniu.util.StringMap;

public class UploadDemo {	
	public String accessKey = "N9qIq1ZvEyCMKibp3tKCeuBDmxRFzZ-2RuYfmffi";	
	public String secretKey = "-945Mj482FV4KRwGQCIT9Tm_bnC4eDTE5DSG-vSw";	
	public Auth auth = Auth.create(accessKey, secretKey);		
	public UploadManager mp = new UploadManager();
	
	@Test
	public void test01(){
		File file = new File("C:"+File.separator+"charless"+ File.separator+"Base64.java");	
		//"key=$(key)&hash=$(etag)&id=$(reqId)"
		StringMap policy = new StringMap()//"{\"success\":\"ok\",\"name\":\"test1\"}"
				.putNotEmpty("callbackUrl", "https://7b0ded5d.ngrok.io/IsValidCallback/servlet/IsValidCallback")
				.putNotEmpty("callbackBody", "key=$(key)&hash=$(etag)&id=$(reqId)")
				//.putNotEmpty("callbackHost", "https://3b90c11ea0e8.a.passageway.io/IsValidCallback/servlet/IsValidCallback")
//		.putNotEmpty("callbackBody", "{\"success&\":\"ok\",\"name\":\"test1\"}");
				.putNotEmpty("callbackBodyType", "application/json");		
		String token = auth.uploadToken("nepliang", "test12ngork", 3600, policy);
		try {
			Response res = mp.put(file, "test12ngork", token);
			System.out.println(res.bodyString());
			System.out.println(res.statusCode);
		} catch (QiniuException e){
			// TODO Auto-generated catch block
			Response tet = e.response;
			System.out.println(tet.statusCode);
			System.out.println(tet.contentType());
			try {
				System.out.println(tet.bodyString());
			} catch (QiniuException e1) {
				// TODO Auto-generated catch block
				System.out.println("NO!");
			}
		}
	}	
	@Test
	public void test(){
		String token = auth.uploadToken("nepliang",null,360000,null);
		System.out.println(token);
	}
}
