package com.sunfield.microframe.coder.generators.fallback;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.sunfield.microframe.CodeRunner;
import com.sunfield.microframe.coder.generators.domain.DomainGenerator;
import com.sunfield.microframe.coder.utils.FreeMarkerUtils;

import freemarker.template.Template;

public class FallbackGenerator {

	/**fallbackģ���ļ�**/
	private final static String FTL_FILE = "fallback.ftl";
	
	/**����**/
	public final static String PACKAGE_NAME = "com.sunfield.microframe.fallback";
	
	public static void run(String tableName, String modelName, String fileDir) throws Exception{
		Template temp = FreeMarkerUtils.getTemplate(FTL_FILE, FallbackGenerator.class);
		
		/**д���������**/
		Map<String, Object> m = new HashMap<String, Object>();
		
		/**д�����**/
		m.put("packageName", PACKAGE_NAME);
		
		/**���ñ�/ʵ������**/
		m.put("tableName", tableName);
		m.put("modelName", modelName);
		
		/**д��Service��**/
		String serviceName = modelName + "Service";
		m.put("serviceName", serviceName);
		
		/**д��model����**/
		m.put("modelPackage", DomainGenerator.PACKAGE_NAME + "." + modelName);
		
		/**�����߼�ɾ��**/
		if(StringUtils.isNotBlank(CodeRunner.LOGIC_DELETE_COLUMN))
			m.put("logicDelete", true);
		else
			m.put("logicDelete", false);
		
		FreeMarkerUtils.generateCodeFile(temp, m, modelName + "Fallback.java", fileDir);
	}
}
