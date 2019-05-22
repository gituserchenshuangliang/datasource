package com.csl.demo.datasources;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取属性文件
 * @author Cherry
 * @date  2019年5月19日
 */
public class PropertiesUtils {
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
	private static Properties props ;
	
	public static void loadProps(String propertyFile){
		props = new Properties();
		  // 通过输入缓冲流进行读取配置文件
        InputStream InputStream;
		try {
			InputStream = new BufferedInputStream(new FileInputStream(new File(propertyFile)));
			props.load(new InputStreamReader(InputStream));
		} catch (FileNotFoundException e) {
			logger.error("找不到文件！");
		} catch (IOException e) {
			logger.error("配置文件读取异常",e);
		}
	}
	
	/*
	 * 获取属性文件的key对应value值
	 */
	public static String getValue(String key){
		String value = props.getProperty(key.trim());
		return StringUtils.isBlank(value)?null:value.trim();
	}
	
	/*
	 * 获取属性文件的key对应value值,如果value为空则设置为default默认值
	 */
	public static String getValue(String key,String defalutValue){
		String value = props.getProperty(key.trim());
		return StringUtils.isBlank(value)?defalutValue:value.trim();
	}
	
	/*
	 * 获取属性对象
	 */
	public static Properties getProp(){
		return props;
	}
}