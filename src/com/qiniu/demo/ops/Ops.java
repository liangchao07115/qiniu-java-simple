package com.qiniu.demo.ops;


import org.junit.Test;

import com.qiniu.common.QiniuException;
import com.qiniu.config.Config;
import com.qiniu.processing.OperationManager;
import com.qiniu.util.Auth;
import com.qiniu.util.Base64;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;

public class Ops {

	public Auth auth = Auth.create(Config.ak, Config.sk);	
	
	public OperationManager Op = new OperationManager(auth);
	
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
	
	@Test//|odconv/jpg
	public void test02(){
		String saveas = UrlSafeBase64.encodeToString("tes01:te");
		String saveas1 = UrlSafeBase64.encodeToString("tes01:tes003.jpg");

		/*
		String saveas1 = UrlSafeBase64.encodeToString("tes01:tes001.jpg");
		
		//"yifangyun_preview|odconv/jpg/page/2|saveas/"+saveas1
*
*/	
		String fops = "yifangyun_preview|odconv/jpg/page/3|saveas/" + saveas1;
		StringMap params = new StringMap();
		params.putNotEmpty("pipeline", "ops");
		
		try {
			String persistentId = Op.pfop("tes01", "tomcat5.5.docx", fops, params);
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
		String saveas = UrlSafeBase64.encodeToString("nepliang:46162409.avi");
		String fops = "avthumb/mp4|saveas/" + saveas;
		StringMap params = new StringMap();
		params.putNotEmpty("pipeline", "ops");
				
		try {
			String persistentId = Op.pfop("nepliang", "46162409.avi", fops, params);
			System.out.println(persistentId);
		} catch (QiniuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	

}
