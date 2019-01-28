package com.sunfield.microframe.coder.generators.client4service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.sunfield.microframe.CodeRunner;
import com.sunfield.microframe.coder.generators.domain.DomainGenerator;
import com.sunfield.microframe.coder.utils.FreeMarkerUtils;

import freemarker.template.Template;

public class ClientGenerator {

	/**clientģ���ļ�**/
	private final static String FTL_FILE = "client.ftl";
	
	/**����**/
	public final static String PACKAGE_NAME = "com.sunfield.microframe.client";
	
	public static void run(String tableName, String modelName, String fileDir) throws Exception{
		Template temp = FreeMarkerUtils.getTemplate(FTL_FILE, ClientGenerator.class);
		
		/**д���������**/
		Map<String, Object> m = new HashMap<String, Object>();
		
		/**д�����**/
		m.put("packageName", PACKAGE_NAME);
		
		/**���ñ�/ʵ������**/
		m.put("tableName", tableName);
		m.put("modelName", modelName);
		
		/**д��model����**/
		m.put("modelPackage", DomainGenerator.PACKAGE_NAME + "." + modelName);
		
		/**�����߼�ɾ��**/
		if(StringUtils.isNotBlank(CodeRunner.LOGIC_DELETE_COLUMN))
			m.put("logicDelete", true);
		else
			m.put("logicDelete", false);
		
//		/**д��dao������**/
//		m.put("daoApplicationName", CodeRunner.DAO_APPLICATION_NAME);
		
		FreeMarkerUtils.generateCodeFile(temp, m, modelName + "Client.java", fileDir);
	}
}
