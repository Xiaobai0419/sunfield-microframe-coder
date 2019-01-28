package ${packageName};

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import ${modelPackage};
import ${providerPackage};

/**
 * ${tableName} mapper
 * @author sunfield coder
 */
@Mapper
public interface ${modelName}Mapper{

	@SelectProvider(type=${providerName}.class, method="generateFindListSql")
	public List<${modelName}> findList(${modelName} obj);
	
	@SelectProvider(type=${providerName}.class, method="generateFindPageSql")
	public List<${modelName}> findPage(${modelName} obj);
	
	@SelectProvider(type=${providerName}.class, method="generateFindOneSql")
	public ${modelName} findOne(String id);
	
	@InsertProvider(type=${providerName}.class, method="generateInsertSql")
	public int insert(${modelName} obj);
	
	@UpdateProvider(type=${providerName}.class, method="generateUpdateSql")
	public int update(${modelName} obj);
	
	<#if logicDelete>
	@UpdateProvider(type=${providerName}.class, method="generateDeleteSql")
	public int delete(String id);
	</#if>
	
}
