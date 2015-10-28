package com.qiniu.demo.token;

import org.junit.Test;

import com.qiniu.config.Config;
import com.qiniu.util.Auth;
import com.qiniu.util.UrlSafeBase64;
//临时处理做转存
public class SaveasToken {
	
	Auth auth = Auth.create(Config.ak, Config.sk);
	
	public String url = "http://7xnlgu.com1.z0.glb.clouddn.com/Mps_01.png?imageView2/1/w/200/h/200";
	
	public String urlTest = "7xnlgu.com1.z0.glb.clouddn.com/Mps_01.png?imageView2/1/w/200/h/200";  
	
	public String saveas =  UrlSafeBase64.encodeToString("nepliang:test01.01");
	
	public String NewUrl = urlTest + "|saveas/" + saveas;
	
	@Test
	public void test(){
		
		String Sign = auth.sign(NewUrl);
			
		String FinalURL = NewUrl + "/sign/"+Sign;
		
		System.out.println(FinalURL);
		
	}
}
