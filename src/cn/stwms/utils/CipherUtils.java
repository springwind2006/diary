package cn.stwms.utils;

import java.util.Base64;
/*
 * AES对称加密和解密
 */
public class CipherUtils {
	
	/**
	 * 基于Base64加密
	 * @param src 原文
	 * @param forUrl 是否用于URL，为true,将会执行字符替换：'='=>'_','/'=>'-','+'=>'$'
	 * @return 返回加密后的字符串
	 */
	public static String encodeBase64(String src,boolean forUrl){
		try{
			String string=Base64.getEncoder().encodeToString(src.getBytes());
			if(forUrl){
				return string.replace('=', '_').replace('/', '-').replace('+', '$');
			}
			return string;
		}catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 基于Base64解密（支持用于URL的解密）
	 * @param src 原文
	 * @return 返回加密后的字符串
	 */
	public static String decodeBase64(String cipher){
		try{
			if(cipher.indexOf('_')!=-1 || cipher.indexOf('-')!=-1 || cipher.indexOf('$')!=-1){
				cipher=cipher.replace('_','=').replace('-','/').replace('$','+');
			}
			return new String(Base64.getDecoder().decode(cipher));
		}catch (Exception e) {
			return "";
		}
	}
	
	
	/**
	 * 根据密钥加密字符串
	 * @param str 明文
	 * @param key 密钥
	 * @return 加密后的字符串
	 */
	public static String encrypt(String str,String key){
		char[] keys=BaseUtils.md5(key).toCharArray();
		char[] strs = str.toCharArray();
		for(int i=0;i<strs.length;i++){
			strs[i]=(char)((int)strs[i] ^ (int)keys[i%32]);
		}
		return encodeBase64(new String(strs),true);
	}
	
	/**
	 * 根据密钥解密字符串密文
	 * @param result 密文
	 * @param key 密钥匙
	 * @return 明文
	 */
	public static String decrypt(String result,String key){
		char[] keys=BaseUtils.md5(key).toCharArray();
		char[] results = decodeBase64(result).toCharArray();
		for(int i=0;i<results.length;i++){
			results[i]=(char)((int)results[i] ^ (int)keys[i%32]);
		}
		return new String(results);
	}

}