package cn.stwms.tag;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class FuncBase{
	
	/**
	 * @param fmt 日期格式，如：yyyy-MM-dd HH:mm:ss
	 * @param time 时间值
	 * @return String
	 */
	public static String date(String fmt,long time){
		java.util.Date date;
		String tmstr=String.valueOf(time);
		date=new java.util.Date(tmstr.length()<12?Long.valueOf(tmstr+"000"):time);
		DateFormat sdfmt = new SimpleDateFormat(fmt);
		return sdfmt.format(date);
	}
	public static String now(String fmt){
		long time=System.currentTimeMillis();
		return date(fmt, time);
	}
	
	/**
	 * @param str 要加密的字符串
	 * @return String 
	 * @throws NoSuchAlgorithmException
	 */
	public static String md5(String str) throws NoSuchAlgorithmException{
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(str.getBytes());
	    return new BigInteger(1, md.digest()).toString(16);
	}

	/**
	 * @param str 添加的字符串 
	 * @param len 增加后缀的长度限制
	 * @return String
	 */
	public static String suffix(String str,int len){
		return len<str.length() ? str+"..." : str;
	}
}
