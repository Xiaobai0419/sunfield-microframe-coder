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

	/**rest模板文件**/
	private final static String FTL_FILE = "rest.ftl";
	
	/**包名**/
	public final static String PACKAGE_NAME = "com.sunfield.microframe.rest";
	
	public static void run(String tableName, String modelName, String fileDir) throws Exception{
		Template temp = FreeMarkerUtils.getTemplate(FTL_FILE, RestGenerator.class);
		
		/**写入代码内容**/
		Map<String, Object> m = new HashMap<String, Object>();
		
		/**写入包名**/
		m.put("packageName", PACKAGE_NAME);
		
		/**设置表/实体名称**/
		m.put("tableName", tableName);
		m.put("modelName", modelName);
		
		/**写入Service名**/
		String serviceName = modelName + "Service";
		m.put("serviceName", serviceName);
		
		/**写入fallback名**/
		String fallbackName = modelName + "Fallback";
		m.put("fallbackName", fallbackName);
		
		/**写入model包名**/
		m.put("modelPackage", DomainGenerator.PACKAGE_NAME + "." + modelName);
		
		/**写入service包名**/
		m.put("servicePackage", ServiceGenerator.PACKAGE_NAME + "." + serviceName);
		
		/**写入fallback包名**/
		m.put("fallbackPackage", FallbackGenerator.PACKAGE_NAME + "." + fallbackName);
		
		/**设置逻辑删除**/
		if(StringUtils.isNotBlank(CodeRunner.LOGIC_DELETE_COLUMN)) {
            m.put("logicDelete", true);
        } else {
            m.put("logicDelete", false);
        }
		
		FreeMarkerUtils.generateCodeFile(temp, m, modelName + "Rest.java", fileDir);
	}
}
