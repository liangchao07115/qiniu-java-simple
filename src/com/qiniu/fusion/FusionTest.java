package com.qiniu.fusion;

import java.io.IOException;

import org.junit.Test;

import com.qiniu.util.Json;
import com.qiniu.util.StringMap;

public class FusionTest {
	
	public Prefetch pre = new Prefetch();
	
	public Refresh fresh = new Refresh();
	
	@Test
	public void refreshTest() throws IOException{
		StringMap map = new StringMap();
		String[] str = new String[] {"http://7xo0hi.com1.z0.glb.clouddn.com/jianshu.log" };
		map.put("urls", str);	
		String s = Json.encode(map);
		fresh.fresh(s);
	}
	
		
	@Test
	public void prefetchTest() throws Exception{		
		StringMap map = new StringMap();
		
		String[] str = new String[]{"http://7xo0hi.com1.z0.glb.clouddn.com/ob.png","http://7xo0hi.com1.z0.glb.clouddn.com/node.js"};		
		map.put("urls", str);		

		String s = Json.encode(map);
		pre.pre(s);
	}	
	@Test
	public void prefetchGetStatusTest(){
		pre.getStatus("5641d48de3ab3a7f2d000001");
	}
	

}
