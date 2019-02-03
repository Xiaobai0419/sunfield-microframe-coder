package com.sunfield.microframe;

import com.sunfield.microframe.coder.generators.Generator;

public class CodeRunner {
	
	/**数据库连接地址**/
	public final static String DATA_BASE_URL = "jdbc:mysql://172.16.175.22:3306/jiaoma?characterEncoding=utf8&useSSL=false&zeroDateTimeBehavior=convertToNull";
	
	/**数据库用户名**/
	public final static String USER_NAME = "jiaoma";
	
	/**数据库密码**/
	public final static String PASSWORD = "123456";
	
	/**表名**/
	public final static String TABLE_NAME = "jm_industries";
	
	/**逻辑删除列**/
	public final static String LOGIC_DELETE_COLUMN = "status";
	
	/**逻辑删除类型枚举：{正常， 删除}**/
	public final static String[] LOGIC_DELETE_ENUM = {"0", "1"};
	
	/**查询列**/
	public final static String[] QUERY_COLUMNS = {};
	
	/**模糊查询列**/
	public final static String[] FUZZY_QUERY_COLUMNS = {};
	
	/**文件输出路径**/
	public final static String FILE_DIR = "F://micro-coder";
	
	public static void main(String[] a){
		Generator.run();
	}
	
}
