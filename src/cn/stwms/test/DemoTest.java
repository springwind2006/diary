package cn.stwms.test;

import org.junit.Test;

import cn.stwms.utils.BaseUtils;
import cn.stwms.utils.CipherUtils;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;


public class DemoTest {

	public static void main(String[] param){
		
	}
	
	@Test
	public void test(){
		String str = "aa/bb/cc.dd";
		try {
			System.out.println(JSONUtil.serialize(str.split("/"),false));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2(){
		System.out.println(CipherUtils.encrypt("spring", "123"));
	}

}
