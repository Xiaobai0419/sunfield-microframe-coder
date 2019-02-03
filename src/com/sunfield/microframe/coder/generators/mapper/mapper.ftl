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

	/**
	 * 列表查询
	 * @param obj
	 * @return
	 */
	@SelectProvider(type=${providerName}.class, method="generateFindListSql")
	public List<${modelName}> findList(${modelName} obj);

	/**
	 * 分页查询
	 * @param obj
	 * @return
	 */
	@SelectProvider(type=${providerName}.class, method="generateFindPageSql")
	public List<${modelName}> findPage(${modelName} obj);

	/**
	 * 单行查询
	 * @param id
	 * @return
	 */
	@SelectProvider(type=${providerName}.class, method="generateFindOneSql")
	public ${modelName} findOne(String id);

	/**
	 * 插入单行
	 * @param obj
	 * @return
	 */
	@InsertProvider(type=${providerName}.class, method="generateInsertSql")
	public int insert(${modelName} obj);

	/**
	 * 更新单行
	 * @param obj
	 * @return
	 */
	@UpdateProvider(type=${providerName}.class, method="generateUpdateSql")
	public int update(${modelName} obj);

	/**
	 * 删除单行（一般为逻辑删除）
	 * @param id
	 * @return
	 */
	<#if logicDelete>
	@UpdateProvider(type=${providerName}.class, method="generateDeleteSql")
	public int delete(String id);
	</#if>
	
}
