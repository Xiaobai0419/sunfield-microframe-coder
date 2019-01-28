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

	/**domain模板文件**/
	private final static String FTL_FILE = "domain.ftl";
	
	/**包名**/
	public final static String PACKAGE_NAME = "com.sunfield.microframe.domain";
	
	public static void run(String tableName, String modelName, List<TableColumns> list, String fileDir) throws Exception{
		Template temp = FreeMarkerUtils.getTemplate(FTL_FILE, DomainGenerator.class);
		
		/**写入代码内容**/
		Map<String, Object> m = new HashMap<String, Object>();
		
		/**写入包名**/
		m.put("packageName", PACKAGE_NAME);
		
		/**检查各种属性决定引入的包**/
		List<String> packageList = new ArrayList<String>();
		if(TableUtils.hasTime(list)){
			packageList.add("java.util.Date");
		}
		m.put("packages", packageList);
		
		/**设置表/实体名称**/
		m.put("tableName", tableName);
		m.put("modelName", modelName);
		
		/**设置实体内容**/
		m.put("attrs", TableUtils.table2Model(list));
		
		/**设置逻辑删除列**/
		m.put("logicDeleteColumnName", CodeRunner.LOGIC_DELETE_COLUMN);
		
		FreeMarkerUtils.generateCodeFile(temp, m, modelName + ".java", fileDir);
	}
}
