package com.qiniu.demo.ops;


import org.junit.Test;

import com.qiniu.common.QiniuException;
import com.qiniu.config.Config;
import com.qiniu.processing.OperationManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;

public class Ops {

	public Auth auth = Auth.create(Config.ak, Config.sk);	
	
	public OperationManager Op = new OperationManager(auth);
	
	
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
