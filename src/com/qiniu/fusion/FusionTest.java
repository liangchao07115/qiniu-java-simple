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
		String[] str = new String[] {"http://www.tripb2b.com" };
		map.put("dirs", str);	
		String s = Json.encode(map);
		fresh.fresh(s);
	}
	
		
	@Test
	public void prefetchTest() throws Exception{		
		StringMap map = new StringMap();
		
		String[] str = new String[]{"http://7xnw9e.media1.z0.glb.clouddn.com/video/10-course.mp4"};		
		map.put("urls", str);		

		String s = Json.encode(map);
		pre.pre(s);
	}	
	@Test
	public void prefetchGetStatusTest(){
		pre.getStatus("564f07d3e3ab3a32710000a5");
	}

}
