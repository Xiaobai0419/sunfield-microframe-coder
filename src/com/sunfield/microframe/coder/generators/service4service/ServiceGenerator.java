package com.sunfield.microframe.coder.generators.service4service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.sunfield.microframe.CodeRunner;
import com.sunfield.microframe.coder.generators.client4service.ClientGenerator;
import com.sunfield.microframe.coder.generators.domain.DomainGenerator;
import com.sunfield.microframe.coder.generators.mapper.MapperGenerator;
import com.sunfield.microframe.coder.utils.FreeMarkerUtils;

import freemarker.template.Template;

public class ServiceGenerator {

	/**serviceģ���ļ�**/
	private final static String FTL_FILE = "service.ftl";
	
	/**����**/
	public final static String PACKAGE_NAME = "com.sunfield.microframe.service";
	
	public static void run(String tableName, String modelName, String fileDir) throws Exception{
		Template temp = FreeMarkerUtils.getTemplate(FTL_FILE, ServiceGenerator.class);
		
		/**д���������**/
		Map<String, Object> m = new HashMap<String, Object>();
		
		/**д�����**/
		m.put("packageName", PACKAGE_NAME);
		
		/**���ñ�/ʵ������**/
		m.put("tableName", tableName);
		m.put("modelName", modelName);
		
		/**д��Client��**/
		String clientName = modelName + "Client";
		m.put("clientName", clientName);
		
		/**д��Mapper��**/
		String mapperName = modelName + "Mapper";
		m.put("mapperName", mapperName);
		
		/**д��model����**/
		m.put("modelPackage", DomainGenerator.PACKAGE_NAME + "." + modelName);
		
		/**д��Client����**/
		m.put("clientPackage", ClientGenerator.PACKAGE_NAME + "." + clientName);
		
		/**д��mapper����**/
		m.put("mapperPackage", MapperGenerator.PACKAGE_NAME + "." + mapperName);
		
		/**�����߼�ɾ��**/
		if(StringUtils.isNotBlank(CodeRunner.LOGIC_DELETE_COLUMN))
			m.put("logicDelete", true);
		else
			m.put("logicDelete", false);
		
		FreeMarkerUtils.generateCodeFile(temp, m, modelName + "Service.java", fileDir);
	}
}
