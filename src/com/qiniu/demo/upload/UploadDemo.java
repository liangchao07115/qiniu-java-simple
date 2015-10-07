package com.qiniu.demo.upload;

import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class UploadDemo {
	
	public String accessKey = "";
	
	public String secretKey = "";
	
	public Auth auth = Auth.create(accessKey, secretKey);	
	
	public String token = auth.uploadToken("");
	
	public UploadManager mp = new UploadManager();
	
	public void test(){	
				
	}
}
