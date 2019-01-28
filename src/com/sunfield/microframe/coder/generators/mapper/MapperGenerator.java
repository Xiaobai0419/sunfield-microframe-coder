package com.sunfield.microframe.coder.generators.mapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.sunfield.microframe.CodeRunner;
import com.sunfield.microframe.coder.generators.domain.DomainGenerator;
import com.sunfield.microframe.coder.generators.provider.ProviderGenerator;
import com.sunfield.microframe.coder.utils.FreeMarkerUtils;

import freemarker.template.Template;

public class MapperGenerator {

	/**mapperģ���ļ�**/
	private final static String FTL_FILE = "mapper.ftl";
	
	/**����**/
	public final static String PACKAGE_NAME = "com.sunfield.microframe.mapper";
	
	public static void run(String tableName, String modelName, String fileDir) throws Exception{
		Template temp = FreeMarkerUtils.getTemplate(FTL_FILE, MapperGenerator.class);
		
		/**д���������**/
		Map<String, Object> m = new HashMap<String, Object>();
		
		/**д�����**/
		m.put("packageName", PACKAGE_NAME);
		
		/**���ñ�/ʵ������**/
		m.put("tableName", tableName);
		m.put("modelName", modelName);
		
		/**д��provider��**/
		String providerName = modelName + "SqlProvider";
		m.put("providerName", providerName);
		
		/**д��model����**/
		m.put("modelPackage", DomainGenerator.PACKAGE_NAME + "." + modelName);
		
		/**д��provider����**/
		m.put("providerPackage", ProviderGenerator.PACKAGE_NAME + "." + providerName);
		
		/**�����߼�ɾ��**/
		if(StringUtils.isNotBlank(CodeRunner.LOGIC_DELETE_COLUMN))
			m.put("logicDelete", true);
		else
			m.put("logicDelete", false);
		
		FreeMarkerUtils.generateCodeFile(temp, m, modelName + "Mapper.java", fileDir);
	}
}
