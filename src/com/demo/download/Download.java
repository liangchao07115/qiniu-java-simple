package com.demo.download;

import org.junit.Test;

import com.qiniu.util.Auth;

public class Download {
	
	public String ak = "N9qIq1ZvEyCMKibp3tKCeuBDmxRFzZ-2RuYfmffi";
	public String sk = "-945Mj482FV4KRwGQCIT9Tm_bnC4eDTE5DSG-vSw";
	
	public Auth auth = Auth.create(ak, sk);	
	
	public String privateUrl(String url){
		String provateUrl = auth.privateDownloadUrl(url);		
		return provateUrl;
	}
	
	@Test
	public void test(){
		System.out.println(privateUrl("http://7xngm2.com1.z0.glb.clouddn.com/IsValidCallback.war"));
	}
	
}
