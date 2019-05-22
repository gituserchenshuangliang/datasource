package com.csl.demo.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Mapper接口
 * @author Cherry
 * @date  2019年5月20日
 */
public interface Mapper {
	/*
	 * 一条数据使用一个map，取出的数据都放进list
	 */
	ArrayList<HashMap<String, Object>> getAllUser();
	
	/*
	 * 从数据库视图中获取用户及用户订单信息
	 */
	ArrayList<HashMap<String, Object>> showView(int id);
	
	/*
	 * map作为查询参数,查询试图(map中的key值有：username,price)
	 */
	int queryViewForMap(HashMap<String,Object> map);
}
