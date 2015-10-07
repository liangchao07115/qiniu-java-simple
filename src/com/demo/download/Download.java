package com.demo.download;

import org.junit.Test;

import com.qiniu.util.Auth;

public class Download {
	
	public String ak = "ak";
	public String sk = "sk";
	
	public Auth auth = Auth.create(ak, sk);	
	
	public String privateUrl(String url){
		String provateUrl = auth.privateDownloadUrl(url);		
		return provateUrl;
	}
	
	@Test
	public void test(){
		System.out.println(privateUrl("http://7xn46o.media1.z0.glb.clouddn.com/14396b.jpg"));
	}
}
