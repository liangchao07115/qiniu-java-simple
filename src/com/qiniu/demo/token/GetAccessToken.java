package com.qiniu.demo.token;

import java.io.IOException;

import org.junit.Test;

import com.qiniu.common.Config;
import com.qiniu.util.Auth;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class GetAccessToken {	
	public String signingStr = "/drop/diaobotest\n";
	
	public String ak = "ak";
	
	public String sk = "sk";
	
	public Auth auth = Auth.create(Config.ak, Config.sk);	
	
	//  获取管理凭证
	@Test
	public void test(){
		String accessToken = auth.sign(signingStr);
		System.out.println(accessToken);
	}	
	
}
