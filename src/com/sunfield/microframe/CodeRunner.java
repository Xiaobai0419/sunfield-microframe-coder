package com.sunfield.microframe;

import com.sunfield.microframe.coder.generators.Generator;

public class CodeRunner {
	
	/**数据库连接地址**/
	public final static String DATA_BASE_URL = "jdbc:mysql://localhost:3306/microtest?characterEncoding=utf8&useSSL=false&zeroDateTimeBehavior=convertToNull";
	
	/**数据库用户名**/
	public final static String USER_NAME = "root";
	
	/**数据库密码**/
	public final static String PASSWORD = "root";
	
	/**表名**/
	public final static String TABLE_NAME = "m_users";
	
	/**逻辑删除列**/
	public final static String LOGIC_DELETE_COLUMN = "del_flag";
	
	/**逻辑删除类型枚举：{正常， 删除}**/
	public final static String[] LOGIC_DELETE_ENUM = {"0", "1"};
	
	/**查询列**/
	public final static String[] QUERY_COLUMNS = {"age"};
	
	/**模糊查询列**/
	public final static String[] FUZZY_QUERY_COLUMNS = {"mobile", "nick_name"};
	
	/**文件输出路径**/
	public final static String FILE_DIR = "F://micro-coder";
	
	public static void main(String[] a){
		Generator.run();
	}
	
}
