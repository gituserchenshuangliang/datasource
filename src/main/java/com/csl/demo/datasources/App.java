package com.csl.demo.datasources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 学习
 * @author Cherry
 * @date 2019年5月19日
 */
public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);
	static {
		PropertiesUtils.loadProps(
				"D:\\Workspaces\\MyEclipse 2017 CI\\datasources\\src\\main\\java\\com\\csl\\demo\\datasource\\datasource.properties");
		logger.debug("加载属性文件......");
	}
	
	/*
	 * 获取数据库连接
	 */
	public static Connection getConn() {
		String driverClassName = PropertiesUtils.getValue("db.driverClassName");
		String url = PropertiesUtils.getValue("db.url");
		String username = PropertiesUtils.getValue("db.username");
		String password = PropertiesUtils.getValue("db.password");
		Connection con = null;
		try {
			/*
			 * 加载驱动
			 */
			Class.forName(driverClassName);
			/*
			 * 创建连接
			 */
			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			logger.error("找不到加载类");
		} catch (SQLException e) {
			logger.error("数据库连接异常");
		}
		logger.debug("数据库连接成功！");
		return con;
	}
	
	/*
	 * 查询结果转换为List
	 */
	public static ArrayList<HashMap<String, Object>>  convertResultSet(ResultSet rs) {
		if (rs == null) {
			return null;
		} else {
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>(8);
			try {
				ResultSetMetaData rmd = rs.getMetaData();
				int count = rmd.getColumnCount();
				while (rs.next()) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					for (int i = 1; i <= count; i++) {
						map.put(rmd.getColumnName(i), rs.getObject(i));
					}
					list.add(map);
				}

			} catch (SQLException e) {
				logger.error("查询语句异常", e);
			}
			return list;
		}
	}
	
	/*
	 * 关闭所以数据库连接
	 */
	public static void closeAll(ResultSet rs,PreparedStatement ps,Connection con){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		Connection con = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from ymall_user;";
		try {
			ps = con.prepareStatement(sql);
			//ps.setXXX(X,X);
			rs = ps.executeQuery();

			ArrayList<HashMap<String, Object>> list = convertResultSet(rs);
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		} catch (SQLException e) {
			logger.error("查询语句异常", e);
		}finally {
			closeAll(rs, ps, con);
		}
	}
}
