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

    /**mapper模板文件**/
    private final static String FTL_FILE = "mapper.ftl";

    /**包名**/
    public final static String PACKAGE_NAME = "com.sunfield.microframe.mapper";

    public static void run(String tableName, String modelName, String fileDir) throws Exception{
        Template temp = FreeMarkerUtils.getTemplate(FTL_FILE, MapperGenerator.class);

        /**写入代码内容**/
        Map<String, Object> m = new HashMap<String, Object>();

        /**写入包名**/
        m.put("packageName", PACKAGE_NAME);

        /**设置表/实体名称**/
        m.put("tableName", tableName);
        m.put("modelName", modelName);

        /**写入provider名**/
        String providerName = modelName + "SqlProvider";
        m.put("providerName", providerName);

        /**写入model包名**/
        m.put("modelPackage", DomainGenerator.PACKAGE_NAME + "." + modelName);

        /**写入provider包名**/
        m.put("providerPackage", ProviderGenerator.PACKAGE_NAME + "." + providerName);

        /**设置逻辑删除**/
        if(StringUtils.isNotBlank(CodeRunner.LOGIC_DELETE_COLUMN)) {
            m.put("logicDelete", true);
        } else {
            m.put("logicDelete", false);
        }

        FreeMarkerUtils.generateCodeFile(temp, m, modelName + "Mapper.java", fileDir);
    }
}

