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

	/**service模板文件**/
	private final static String FTL_FILE = "service.ftl";
	
	/**包名**/
	public final static String PACKAGE_NAME = "com.sunfield.microframe.service";
	
	public static void run(String tableName, String modelName, String fileDir) throws Exception{
		Template temp = FreeMarkerUtils.getTemplate(FTL_FILE, ServiceGenerator.class);
		
		/**写入代码内容**/
		Map<String, Object> m = new HashMap<String, Object>();
		
		/**写入包名**/
		m.put("packageName", PACKAGE_NAME);
		
		/**设置表/实体名称**/
		m.put("tableName", tableName);
		m.put("modelName", modelName);
		
		/**写入Client名**/
		String clientName = modelName + "Client";
		m.put("clientName", clientName);
		
		/**写入Mapper名**/
		String mapperName = modelName + "Mapper";
		m.put("mapperName", mapperName);
		
		/**写入model包名**/
		m.put("modelPackage", DomainGenerator.PACKAGE_NAME + "." + modelName);
		
		/**写入Client包名**/
		m.put("clientPackage", ClientGenerator.PACKAGE_NAME + "." + clientName);
		
		/**写入mapper包名**/
		m.put("mapperPackage", MapperGenerator.PACKAGE_NAME + "." + mapperName);
		
		/**设置逻辑删除**/
		if(StringUtils.isNotBlank(CodeRunner.LOGIC_DELETE_COLUMN))
			m.put("logicDelete", true);
		else
			m.put("logicDelete", false);
		
		FreeMarkerUtils.generateCodeFile(temp, m, modelName + "Service.java", fileDir);
	}
}
