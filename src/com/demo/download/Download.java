package com.demo.download;

import org.junit.Test;

import com.qiniu.common.Config;
import com.qiniu.util.Auth;

public class Download {
	
	public Auth auth = Auth.create(Config.ak, Config.sk);	
	
	public String privateUrl(String url){
		String provateUrl = auth.privateDownloadUrl(url);		
		return provateUrl;
	}
	
	@Test
	public void test(){
		System.out.println(privateUrl("http://7xngm2.com1.z0.glb.clouddn.com/IsValidCallback.war"));
	}
	
	
}
