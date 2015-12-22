package com.qiniu.demo.rs;

import org.junit.Test;

import com.qiniu.common.QiniuException;
import com.qiniu.config.Config;
import com.qiniu.storage.BucketManager;
import com.qiniu.util.Auth;
import com.qiniu.util.UrlSafeBase64;

public class ListBucket {
	public  Auth auth = Auth.create("**","**");
	
	//空间资源管理
	public  BucketManager bucketManager = new BucketManager(auth);
	
	@Test
	public void test1(){
		
		try {
			bucketManager.delete("test", "新建文本文档.txt");
		} catch (QiniuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	@Test
	public void test(){
		try {
			String[] buckets = bucketManager.buckets();
			for(String name : buckets){
				System.out.println(name);
			}
		} catch (QiniuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void test11(){
		String url = "http://developer.qiniu.com/resource/logo-2.jpg";
		System.out.println(UrlSafeBase64.encodeToString(url));
		
	}
}
