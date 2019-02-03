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
	 * �б��ѯ
	 * @param obj
	 * @return
	 */
	@SelectProvider(type=${providerName}.class, method="generateFindListSql")
	public List<${modelName}> findList(${modelName} obj);

	/**
	 * ��ҳ��ѯ
	 * @param obj
	 * @return
	 */
	@SelectProvider(type=${providerName}.class, method="generateFindPageSql")
	public List<${modelName}> findPage(${modelName} obj);

	/**
	 * ���в�ѯ
	 * @param id
	 * @return
	 */
	@SelectProvider(type=${providerName}.class, method="generateFindOneSql")
	public ${modelName} findOne(String id);

	/**
	 * ���뵥��
	 * @param obj
	 * @return
	 */
	@InsertProvider(type=${providerName}.class, method="generateInsertSql")
	public int insert(${modelName} obj);

	/**
	 * ���µ���
	 * @param obj
	 * @return
	 */
	@UpdateProvider(type=${providerName}.class, method="generateUpdateSql")
	public int update(${modelName} obj);

	/**
	 * ɾ�����У�һ��Ϊ�߼�ɾ����
	 * @param id
	 * @return
	 */
	<#if logicDelete>
	@UpdateProvider(type=${providerName}.class, method="generateDeleteSql")
	public int delete(String id);
	</#if>
	
}
