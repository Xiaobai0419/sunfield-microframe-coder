package com.sunfield.microframe.coder.generators.rest4service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.sunfield.microframe.CodeRunner;
import com.sunfield.microframe.coder.generators.domain.DomainGenerator;
import com.sunfield.microframe.coder.generators.fallback.FallbackGenerator;
import com.sunfield.microframe.coder.generators.service4service.ServiceGenerator;
import com.sunfield.microframe.coder.utils.FreeMarkerUtils;

import freemarker.template.Template;

public class RestGenerator {

	/**restģ���ļ�**/
	private final static String FTL_FILE = "rest.ftl";
	
	/**����**/
	public final static String PACKAGE_NAME = "com.sunfield.microframe.rest";
	
	public static void run(String tableName, String modelName, String fileDir) throws Exception{
		Template temp = FreeMarkerUtils.getTemplate(FTL_FILE, RestGenerator.class);
		
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
		
		/**д��fallback��**/
		String fallbackName = modelName + "Fallback";
		m.put("fallbackName", fallbackName);
		
		/**д��model����**/
		m.put("modelPackage", DomainGenerator.PACKAGE_NAME + "." + modelName);
		
		/**д��service����**/
		m.put("servicePackage", ServiceGenerator.PACKAGE_NAME + "." + serviceName);
		
		/**д��fallback����**/
		m.put("fallbackPackage", FallbackGenerator.PACKAGE_NAME + "." + fallbackName);
		
		/**�����߼�ɾ��**/
		if(StringUtils.isNotBlank(CodeRunner.LOGIC_DELETE_COLUMN)) {
            m.put("logicDelete", true);
        } else {
            m.put("logicDelete", false);
        }
		
		FreeMarkerUtils.generateCodeFile(temp, m, modelName + "Rest.java", fileDir);
	}
}
