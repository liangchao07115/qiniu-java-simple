package com.qiniu.demo.ops;


import java.util.ArrayList;

import org.junit.Test;

import com.qiniu.common.QiniuException;
import com.qiniu.config.Config;
import com.qiniu.processing.OperationManager;
import com.qiniu.util.Auth;
import com.qiniu.util.Base64;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import com.squareup.okhttp.Response;

public class Ops {

	public Auth auth = Auth.create(Config.ak,Config.sk);	
	
	public OperationManager Op = new OperationManager(auth);
	
	
	@Test
	public void mkzip(){
		String saveName = UrlSafeBase64.encodeToString("public:mkzipTest2");
		
		ArrayList<String> strArray = new ArrayList<String>();
		
		strArray.add("http://7xo0hi.com1.z0.glb.clouddn.com/576.mp4");
		strArray.add("http://7xo0hi.com1.z0.glb.clouddn.com/2FQp6CoZ1.gif");
		strArray.add("http://7xo0hi.com1.z0.glb.clouddn.com/c-sdk.tar.gz");
		
		String fopCmdStr = "mkzip/2";
		for(String str: strArray){
			fopCmdStr += "/url/"+UrlSafeBase64.encodeToString(str);					
		}
				
		fopCmdStr += "|saveas/" +   saveName;
		System.out.println(fopCmdStr);
		
		StringMap params = new StringMap();
		params.putNotEmpty("pipeline", "ops");
		params.putNotEmpty("force", "1");

		try {
			String persistentId = Op.pfop("public", "_log/public/2015-12-22/part0.gz", fopCmdStr, params);
			System.out.println(persistentId);
		} catch (QiniuException e) {
			e.printStackTrace();
		}	
		
	}
	
	@Test
	public void test1(){
		
		String saveName = UrlSafeBase64.encodeToString("public:test0056");
		String tt = "http://7xo0hi.com1.z0.glb.clouddn.com/wek";
				
		String url = UrlSafeBase64.encodeToString(tt);
		String fop = "avthumb/mp4/subtitle/"+url+"|saveas/" + saveName;
		StringMap params = new StringMap();
		params.putNotEmpty("pipeline", "ops");
		params.putNotEmpty("force", "1");

		try {
			String persistentId = Op.pfop("public", "testAdd", fop, params);
			System.out.println(persistentId);
		} catch (QiniuException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void test02(){
		String saveas = UrlSafeBase64.encodeToString("public:56.mp4");
		
		String str = UrlSafeBase64.encodeToString("http://7xo0hi.com1.z0.glb.clouddn.com/567a8ce76be1d.srt");
		
		String fops = "avthumb/mp4/subtitle/"+str+"|saveas/" + saveas;
		StringMap params = new StringMap();
		params.putNotEmpty("pipeline", "ops");
		
		try {
			String persistentId = Op.pfop("public", "567a8d1bc92f9.mp4", fops, params);
			System.out.println(persistentId);
		} catch (QiniuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	@Test//|odconv/jpg
	public void test01(){
		String saveas = UrlSafeBase64.encodeToString("tes01:te");
		String saveas1 = UrlSafeBase64.encodeToString("tes01:tes001.jpg");
		String fops = "yifangyun_preview|odconv/jpg/page/2|saveas/" + saveas1;
		StringMap params = new StringMap();
		params.putNotEmpty("pipeline", "ops");
		
		try {
			String persistentId = Op.pfop("nepliang", "5.docx", fops, params);
			System.out.println(persistentId);
		} catch (QiniuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}	
	
	@Test
	public void test(){
		
		String saveas = UrlSafeBase64.encodeToString("public:java-01");
		String saveas1 = UrlSafeBase64.encodeToString("public:java-02");
		String fops = "avthumb/mp4|saveas/"+saveas+";avthumb/flv|saveas/"+saveas1;
		
		StringMap params = new StringMap();
		params.putNotEmpty("pipeline", "ops")
		.put("force", "1");
				
		try {
			String persistentId = Op.pfop("dianbo", "wek", fops, params);
			System.out.println(persistentId);
		} catch (QiniuException e) {
			// TODO Auto-generated catch block
			try {
				System.out.println(e.response.bodyString());
			} catch (QiniuException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}		
		
	}	
	
	@Test
	public void test09(){
		String tyt = ";";
		System.out.println(UrlSafeBase64.encodeToString(tyt));
	}

}
