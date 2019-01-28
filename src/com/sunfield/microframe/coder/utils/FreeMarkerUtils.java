package com.sunfield.microframe.coder.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

public class FreeMarkerUtils {

	/**
	 * 构造freemarker模板对象
	 * @param ftlLocation 模板文件路径
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws MalformedTemplateNameException 
	 * @throws TemplateNotFoundException 
	 */
	public static Template getTemplate(String ftlLocation, Class<?> clazz) 
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException{
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		cfg.setClassForTemplateLoading(clazz, "");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return cfg.getTemplate(ftlLocation);
	}
	
	/**
	 * 生成代码文件
	 * @temp 模板
	 * @param m 写入数据
	 * @param fileName 文件名
	 * @return
	 * @throws IOException 
	 * @throws TemplateException 
	 */
	public static File generateCodeFile(Template temp, Map<String, Object> m, String fileName, String fileDir) 
			throws TemplateException, IOException{
		File dir = new File(fileDir);
		if(!dir.exists())
			dir.mkdirs();
		
		File f = new File(dir, fileName);
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
        temp.process(m, out);
        out.flush();
        out.close();
        return f;
	}
}
