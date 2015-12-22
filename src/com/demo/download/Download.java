package com.demo.download;

import org.junit.Test;

import com.qiniu.config.Config;
import com.qiniu.demo.util.Date2UnixTimestamp;
import com.qiniu.util.Auth;

public class Download{
	
	public Auth auth = Auth.create("3W8nxz2TkTiHZ0HXFzeezCwR_1Focs1s4eWQPZdR", "a54P_q_3uoeK5TpYip6gfvVdiquYdRburS5GOgT5");	
	
	public String privateUrl(String url){
		String provateUrl = auth.privateDownloadUrl(url);		
		return provateUrl;
	}
	
	
	/**
	 * 指定过期时间
	 * @param date yyyy/MM/dd hh:mm:ss日期格式
	 * @param url 私有的url 
	*/  
	public String unixTimeStampUrl(String date, String url){
		
		long unixTime = Date2UnixTimestamp.DateFomat(date);		
	    
	    long current = (System.currentTimeMillis()/1000);	 
	    
		long deadline = unixTime-current;		
		
		String getUrl = auth.privateDownloadUrl(url, deadline);
		
		return getUrl;
	}	
	
	@Test
	public void test(){
		System.out.println(privateUrl("http://videostatic.5tv.com/00361dcc0606a9c2b4b94997498ba98e.mp4"));
	}
	
	@Test
	public void test1(){
		
		System.out.println(unixTimeStampUrl("2015/10/20 12:00:00", "http://7xnn4a.com1.z0.glb.clouddn.com/34.jpg"));
		
	}
}










