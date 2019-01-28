package com.sunfield.microframe.coder.generators;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sunfield.microframe.CodeRunner;
import com.sunfield.microframe.coder.connect.Connector;
import com.sunfield.microframe.coder.generators.domain.DomainGenerator;
import com.sunfield.microframe.coder.generators.fallback.FallbackGenerator;
import com.sunfield.microframe.coder.generators.mapper.MapperGenerator;
import com.sunfield.microframe.coder.generators.provider.ProviderGenerator;
import com.sunfield.microframe.coder.model.TableColumns;
import com.sunfield.microframe.coder.utils.TableUtils;

public class Generator {

	private final static String SELECT_TABLE_COLUMNS_SQL = "select column_name, data_type, character_maximum_length,"+
															" numeric_precision, is_nullable, column_key, column_comment"+
															" from information_schema.columns where table_name = ':tableName'";
	
	public static void run(){
		try {
			System.out.println("-----------生成开始-----------");
			Connection conn = new Connector().getConnection();
			
			List<TableColumns> tcList = new ArrayList<TableColumns>();
			String columnsSql = SELECT_TABLE_COLUMNS_SQL.replace(":tableName", CodeRunner.TABLE_NAME);
			PreparedStatement ps1;
			ps1 = conn.prepareStatement(columnsSql);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()){
				TableColumns tc = new TableColumns();
				tc.setColumnName(rs1.getString(1));
				tc.setDataType(rs1.getString(2));
				tc.setCharMaxLength(rs1.getInt(3));
				tc.setNumPrecision(rs1.getInt(4));
				tc.setIsNullable(rs1.getString(5));
				tc.setColumnKey(rs1.getString(6));
				tc.setColumnComment(rs1.getString(7));
				tcList.add(tc);
			}
			
			String modelName = TableUtils.tableName2ModelName(CodeRunner.TABLE_NAME);
			
			/**domain**/
			try {
				DomainGenerator.run(CodeRunner.TABLE_NAME, modelName, tcList, CodeRunner.FILE_DIR+"/"+modelName);
			} catch (Exception e) {
				System.out.println("-----------domain代码生成失败-----------");
				e.printStackTrace();
			}
			
			/**sqlprovider**/
			try {
				ProviderGenerator.run(CodeRunner.TABLE_NAME, modelName, tcList, CodeRunner.FILE_DIR+"/"+modelName);
			} catch (Exception e) {
				System.out.println("-----------provider代码生成失败-----------");
				e.printStackTrace();
			}
			
			/**mapper**/
			try {
				MapperGenerator.run(CodeRunner.TABLE_NAME, modelName, CodeRunner.FILE_DIR+"/"+modelName);
			} catch (Exception e) {
				System.out.println("-----------mapper代码生成失败-----------");
				e.printStackTrace();
			}
			
			/**service**/
			try {
				com.sunfield.microframe.coder.generators.service4service.ServiceGenerator.run(CodeRunner.TABLE_NAME, modelName, CodeRunner.FILE_DIR+"/"+modelName);
			} catch (Exception e) {
				System.out.println("-----------service代码生成失败-----------");
				e.printStackTrace();
			}
			
			/**rest**/
			try {
				com.sunfield.microframe.coder.generators.rest4service.RestGenerator.run(CodeRunner.TABLE_NAME, modelName, CodeRunner.FILE_DIR+"/"+modelName);
			} catch (Exception e) {
				System.out.println("-----------rest代码生成失败-----------");
				e.printStackTrace();
			}
			
			/**fallback**/
			try {
				FallbackGenerator.run(CodeRunner.TABLE_NAME, modelName, CodeRunner.FILE_DIR+"/"+modelName);
			} catch (Exception e) {
				System.out.println("-----------fallback代码生成失败-----------");
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("-----------生成结束-----------");
		}
	}
	
}
