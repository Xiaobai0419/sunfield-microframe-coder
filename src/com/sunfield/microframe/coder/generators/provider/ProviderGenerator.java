package com.sunfield.microframe.coder.generators.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.sunfield.microframe.CodeRunner;
import com.sunfield.microframe.coder.generators.domain.DomainGenerator;
import com.sunfield.microframe.coder.model.ModelColumns;
import com.sunfield.microframe.coder.model.TableColumns;
import com.sunfield.microframe.coder.utils.FreeMarkerUtils;
import com.sunfield.microframe.coder.utils.TableUtils;

import freemarker.template.Template;

public class ProviderGenerator {

	/**providerģ���ļ�**/
	private final static String FTL_FILE = "provider.ftl";
	
	/**����**/
	public final static String PACKAGE_NAME = "com.sunfield.microframe.provider";
	
	public static void run(String tableName, String modelName, List<TableColumns> list, String fileDir) throws Exception{
		Template temp = FreeMarkerUtils.getTemplate(FTL_FILE, ProviderGenerator.class);
		
		/**д���������**/
		Map<String, Object> m = new HashMap<String, Object>();
		
		/**д�����**/
		m.put("packageName", PACKAGE_NAME);
		
		/**д��model����**/
		m.put("modelPackage", DomainGenerator.PACKAGE_NAME + "." + modelName);
		
		/**���ñ�/ʵ������**/
		m.put("tableName", tableName);
		m.put("modelName", modelName);
		
		/**����ʵ������**/
		m.put("attrs", TableUtils.table2Model(list));
		
		/**���ò�ѯ��**/
		List<ModelColumns> columns = TableUtils.table2Model(list);
		
		List<ModelColumns> queries = new ArrayList<ModelColumns>();
		for(ModelColumns mc : columns){
			if(ArrayUtils.contains(CodeRunner.QUERY_COLUMNS, mc.getColumnName()))
				queries.add(mc);
		}
		m.put("queries", queries);
		
		List<ModelColumns> fuzzies = new ArrayList<ModelColumns>();
		for(ModelColumns mc : columns){
			if(ArrayUtils.contains(CodeRunner.FUZZY_QUERY_COLUMNS, mc.getColumnName()))
				fuzzies.add(mc);
		}
		m.put("fuzzies", fuzzies);
		
		/**�����߼�ɾ��**/
		if(StringUtils.isNotBlank(CodeRunner.LOGIC_DELETE_COLUMN)){
			m.put("logicDelete", true);
			m.put("logicDeleteColumnName", CodeRunner.LOGIC_DELETE_COLUMN);
			if(ArrayUtils.isNotEmpty(CodeRunner.LOGIC_DELETE_ENUM)){
				m.put("logicNormalValue", CodeRunner.LOGIC_DELETE_ENUM[0]);
				m.put("logicDeleteValue", CodeRunner.LOGIC_DELETE_ENUM[1]);
			}
			else{
				m.put("logicNormalValue", "0");
				m.put("logicDeleteValue", "1");
			}
		}else{
			m.put("logicDelete", false);
		}
		
		FreeMarkerUtils.generateCodeFile(temp, m, modelName + "SqlProvider.java", fileDir);
	}
}
