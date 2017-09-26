package cn.stwms.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class BaseUtils {
	private static Properties config=BaseUtils.loadConfig("config");
	/**
	 * 字符串转换为整数，如果无法转换返回0
	 * @param str 待转换的字符串
	 * @return
	 */
	public static int strToInt(String str){
		if(str!=null && str.length()>0){
			try {
				return Integer.parseInt(str);
			} catch (NumberFormatException e) {
				return 0;
			}
		}
		return 0;
	}
	
	/**
	 * 获取时间戳（从1970-1-1 00:00:00 GMT开始的秒数）
	 * @return
	 */
	public static int getTime(){
		return (int)(System.currentTimeMillis()/1000);
	}

	/**
	 * 更加格式获取时间
	 * @param format 时间格式
	 * @return 字符串格式时间
	 * 时间格式范例：
	 * yyyy年MM月dd日 E =》 2012年06月09日 星期六
	 * yyyy-MM-dd HH:mm:ss -》2012-06-09 23:33:33
	 */
	public static String getTime(String format){
		Date date=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
	
	/**
	 * 返回指定格式的时间
	 * @param format 格式，如：yyyy-MM-dd HH:mm:ss
	 * @param time 时间戳
	 * @return
	 * */
	public static String getTime(String format,int time){
		Date date=new Date(time*1000);
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}


	/**
	 * 计算字符串的md5值
	 * @param string 字符串
	 * @return
	 * */
	public static String md5(String string) {
		if (string==null || string.isEmpty()) {
			return "";
		}
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(string.getBytes());
			StringBuilder res=new StringBuilder();
			for (byte b : bytes) {
				String temp = Integer.toHexString(b & 0xff);
				if (temp.length() == 1) {
					res.append("0");
				}
				res.append(temp);
			}
			return res.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 计算文件的 MD5 值
	 * @param file 文件对象
	 * @return
	 * */
	public static String md5(File file) {
		if (file == null || !file.isFile() || !file.exists()) {
			return "";
		}
		FileInputStream in = null;
		StringBuilder res=new StringBuilder();
		byte buffer[] = new byte[8192];
		int len;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer)) != -1) {
				md5.update(buffer, 0, len);
			}
			byte[] bytes = md5.digest();

			for (byte b : bytes) {
				String temp = Integer.toHexString(b & 0xff);
				if (temp.length() == 1) {
					res.append("0");
				}
				res.append(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null!=in){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return res.toString();
	}
	
	/**
	 * 根据键值获取配置
	 * @param key 配置键名称
	 * @return 返回键值
	 * */
	public static String getConfig(String key){
		return config!=null ? config.getProperty(key) : null;
	}
	
	/**
	 * 加载classpath下的配置文件
	 * @param file 配置文件名（不包括扩展名）
	 * @return Properties类型的配置文件
	 * */
	public static Properties loadConfig(String file){
		Properties prop=new Properties();
		InputStream fis=null;
        try {
        	fis = BaseUtils.class.getClassLoader().getResourceAsStream(file+".properties");
            prop.load(fis);
            System.out.println("Load config file:"+file+" success!");
        } catch (Exception e) {
        	System.out.println("Load config file:"+file+" failed!");
        } finally {
            if (fis != null) {
                try {
					fis.close();
					fis=null;
				} catch (IOException e) {
				}
            }
        }
		return prop;
	}
}
