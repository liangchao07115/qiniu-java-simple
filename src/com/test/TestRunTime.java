package com.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class TestRunTime {

	@Test
	public void test() throws InterruptedException{
		Runtime runTime = Runtime.getRuntime(); //get Runtime object
		String[] cmd = new String[3];
		cmd[0]="cmd.exe"; 
		cmd[1]="-c"; 
		cmd[2]="qshell account"; 
		
		String test = "";
		try {
			Process p = runTime.exec("C:/chaoLiang/ProgramFiles/Tools/putty/putty.exe");
			
			BufferedInputStream in = new BufferedInputStream(p.getInputStream());  
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));  
            String lineStr;             
            while ((lineStr = inBr.readLine()) != null)  
                System.out.println(lineStr);// 打印输出信息  
            if (p.waitFor() != 0) {  
                if (p.exitValue() == 1)//p.exitValue()==0表示正常结束，1：非正常结束  
                    System.err.println("命令执行失败!");  
            }  
            inBr.close();  
            in.close();  
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
