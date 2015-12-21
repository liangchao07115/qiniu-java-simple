package com.qiniu.demo.upload;

import java.io.File;

import org.junit.Test;

import com.google.gson.JsonObject;
import com.qiniu.common.QiniuException;
import com.qiniu.config.Config;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.Json;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;

public class UploadDemo {	
	public Auth auth = Auth.create(Config.ak, Config.sk);		
	public UploadManager mp = new UploadManager();
		
	
	@Test
	public void test4(){
		
		File file = new File("C:"+File.separator+"charless"+ File.separator+"test.mp3");	
		
		String saveas1 = UrlSafeBase64.encodeToString("public:tes00");
		
		//"yifangyun_preview|odconv/jpg/page/2|saveas/"+saveas1
		
		StringMap policy = new StringMap()
				.put("persistentOps", "avthumb/m3u8/t/10|saveas/"+saveas1);
				//.put("persistentPipeline", "ops");

				//.put("fsizeMin",98989898);
		String token = auth.uploadToken("dianbo", "testst", 3600, null,false);
		try {
			Response res = mp.put(file, "testst", token,null,null,false);
			System.out.println(res.bodyString());
			System.out.println(res.statusCode);
			System.out.println(res.reqId);
		} catch (QiniuException e){
			Response tet = e.response;
			System.out.println(tet.statusCode);
			System.out.println(tet.contentType());
			try {
				System.out.println(tet.bodyString());
			} catch (QiniuException e1) {
				System.out.println("NO!");
			}
		}		
	}
		
	@Test
	public void test02(){
		File file = new File("C:"+File.separator+"charless"+ File.separator+"FkShFyuESJMT4Ur1LJ5Zm-34qC0Y (3)");	
		
		String saveas1 = UrlSafeBase64.encodeToString("tes01:tes001.jpg");
		
		//"yifangyun_preview|odconv/jpg/page/2|saveas/"+saveas1
		
		StringMap policy = new StringMap().put("insertOnly", "1");
				//.put("persistentOps", "yifangyun_preview/docx|odconv/jpg/page/2|saveas/"+saveas1);

				//.put("fsizeMin",98989898);
		String token = auth.uploadToken("public", "wek3", 3600, null,false);
		try {
			Response res = mp.put(file, "wek3", token,null,"application/x-subrip",false);
			System.out.println(res.bodyString());
			System.out.println(res.statusCode);
		} catch (QiniuException e){
			Response tet = e.response;
			System.out.println(tet.statusCode);
			System.out.println(tet.contentType());
			try {
				System.out.println(tet.bodyString());
			} catch (QiniuException e1) {
				System.out.println("NO!");
			}
		}
	}	
	@Test
	public void	 test1(){
		File file = new File("C:"+File.separator+"charless"+ File.separator+"Base64.java");	
		//"key=$(key)&hash=$(etag)&id=$(reqId)" 
		StringMap policy = new StringMap()
				.putNotEmpty("callbackUrl", "https://c41e8c97.ngrok.io/IsValidCallback/servlet/IsValidCallback")
				.putNotEmpty("callbackBody", "key=$(key)&hash=$(etag)")
				.putNotEmpty("callbackBodyType", "application/json");
		
		String token = auth.uploadToken("public", "test12ngor", 3600, policy);
		try {
			Response res = mp.put(file, "test12ngor", token);
			System.out.println(res.bodyString());
			System.out.println(res.statusCode);
			System.out.println(res.reqId);
		} catch (QiniuException e){
			Response tet = e.response;
			System.out.println(tet.statusCode);
			System.out.println(tet.contentType());
			try {
				System.out.println(tet.bodyString());
			} catch (QiniuException e1) {
				System.out.println("NO!");
			}
		}
	}	
	
	@Test
	public void test3(){
		String str1 = "QBox N9qIq1ZvEyCMKibp3tKCeuBDmxRFzZ-2RuYfmffi:xU8TgQp6tc6DVlWFzI1632bOcoM=";
		String str2 = "QBox N9qIq1ZvEyCMKibp3tKCeuBDmxRFzZ-2RuYfmffi:xU8TgQp6tc6DVlWFzI1632bOcoM=";
		if(str1.equals(str2)){
			System.out.println("ss");
		}else{
			System.out.println("kk");
		}
	}

	@Test
	public void test(){
		String token = auth.uploadToken("public",null,360000,null);
		System.out.println(token);
	}
	
}
