package com.sunfield.microframe.coder.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sunfield.microframe.CodeRunner;

/**
 * ��ȡ���ݿ�����
 * @author wangn
 *
 */
public class Connector {

	/**��������**/
	private final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	
	public Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(CodeRunner.DATA_BASE_URL, CodeRunner.USER_NAME, CodeRunner.PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
