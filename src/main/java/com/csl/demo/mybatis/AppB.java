package com.csl.demo.mybatis;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csl.demo.datasources.PropertiesUtils;

/**
 * mybatis连接数据库
 * @author Cherry
 * @date  2019年5月19日
 */
public class AppB {
	private static Logger logger = LoggerFactory.getLogger(AppB.class);
	
	private static String datas = "D:\\Workspaces\\MyEclipse 2017 CI\\datasources\\src\\main\\java\\com\\csl\\demo\\mybatis\\datasource.properties";
	
	private static String resouce = "D:\\Workspaces\\MyEclipse 2017 CI\\datasources\\src\\main\\java\\com\\csl\\demo\\mybatis\\mybatis-config.xml";
	
	private static SqlSessionFactory factory;
	
	private static SqlSession session;
	
	/*
	 * 加载mybatis全局配置文件
	 */
	static{
		try {
			PropertiesUtils.loadProps(datas);
			InputStream config = new BufferedInputStream(new FileInputStream(new File(resouce)));
			logger.debug("加载数据库属性文件和mybatis全局文件......");
			factory= new SqlSessionFactoryBuilder().build(config,PropertiesUtils.getProp());
			session = factory.openSession();
			logger.debug("获取Session");
		} catch (IOException e) {
			logger.error("文件读取异常",e);
		}
	}
	
	public static void main(String[] args) {
		Mapper mapper = session.getMapper(Mapper.class);
		HashMap<String,Object> m = new HashMap<String,Object>();
		m.put("username","chen");
		m.put("price",6000);
		//ArrayList<HashMap<String, Object>> lists = mapper.getAllUser();
		ArrayList<HashMap<String, Object>> list = mapper.showView(25);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		int count = mapper.queryViewForMap(m);
		logger.debug(count+"");
	}
	
}
