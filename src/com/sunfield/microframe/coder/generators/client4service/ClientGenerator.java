package com.sunfield.microframe.coder.generators.client4service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.sunfield.microframe.CodeRunner;
import com.sunfield.microframe.coder.generators.domain.DomainGenerator;
import com.sunfield.microframe.coder.utils.FreeMarkerUtils;

import freemarker.template.Template;

public class ClientGenerator {

	/**client模板文件**/
	private final static String FTL_FILE = "client.ftl";
	
	/**包名**/
	public final static String PACKAGE_NAME = "com.sunfield.microframe.client";
	
	public static void run(String tableName, String modelName, String fileDir) throws Exception{
		Template temp = FreeMarkerUtils.getTemplate(FTL_FILE, ClientGenerator.class);
		
		/**写入代码内容**/
		Map<String, Object> m = new HashMap<String, Object>();
		
		/**写入包名**/
		m.put("packageName", PACKAGE_NAME);
		
		/**设置表/实体名称**/
		m.put("tableName", tableName);
		m.put("modelName", modelName);
		
		/**写入model包名**/
		m.put("modelPackage", DomainGenerator.PACKAGE_NAME + "." + modelName);
		
		/**设置逻辑删除**/
		if(StringUtils.isNotBlank(CodeRunner.LOGIC_DELETE_COLUMN))
			m.put("logicDelete", true);
		else
			m.put("logicDelete", false);
		
//		/**写入dao工程名**/
//		m.put("daoApplicationName", CodeRunner.DAO_APPLICATION_NAME);
		
		FreeMarkerUtils.generateCodeFile(temp, m, modelName + "Client.java", fileDir);
	}
}
