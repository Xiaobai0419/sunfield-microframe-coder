package com.sunfield.microframe.coder.generators.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sunfield.microframe.CodeRunner;
import com.sunfield.microframe.coder.model.TableColumns;
import com.sunfield.microframe.coder.utils.FreeMarkerUtils;
import com.sunfield.microframe.coder.utils.TableUtils;

import freemarker.template.Template;

public class DomainGenerator {

	/**domainģ���ļ�**/
	private final static String FTL_FILE = "domain.ftl";
	
	/**����**/
	public final static String PACKAGE_NAME = "com.sunfield.microframe.domain";
	
	public static void run(String tableName, String modelName, List<TableColumns> list, String fileDir) throws Exception{
		Template temp = FreeMarkerUtils.getTemplate(FTL_FILE, DomainGenerator.class);
		
		/**д���������**/
		Map<String, Object> m = new HashMap<String, Object>();
		
		/**д�����**/
		m.put("packageName", PACKAGE_NAME);
		
		/**���������Ծ�������İ�**/
		List<String> packageList = new ArrayList<String>();
		if(TableUtils.hasTime(list)){
			packageList.add("java.util.Date");
		}
		m.put("packages", packageList);
		
		/**���ñ�/ʵ������**/
		m.put("tableName", tableName);
		m.put("modelName", modelName);
		
		/**����ʵ������**/
		m.put("attrs", TableUtils.table2Model(list));
		
		/**�����߼�ɾ����**/
		m.put("logicDeleteColumnName", CodeRunner.LOGIC_DELETE_COLUMN);
		
		FreeMarkerUtils.generateCodeFile(temp, m, modelName + ".java", fileDir);
	}
}
