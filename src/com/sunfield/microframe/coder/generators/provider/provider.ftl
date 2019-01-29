package ${packageName};

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import ${modelPackage};

/**
 * ${tableName} sql provider
 * @author sunfield coder
 */
public class ${modelName}SqlProvider{
 
 	private static String COLUMNS = <#list attrs as a>
 									" ${a.columnName} AS ${a.name}<#if a_has_next>,</#if>"<#if a_has_next>+<#else>;</#if></#list>
 
 	public String generateFindListSql(${modelName} obj){
		return new SQL(){
			{
				SELECT(COLUMNS);
				FROM("${tableName}");
				
				<#if logicDelete>
				WHERE("${logicDeleteColumnName} = '${logicNormalValue}'");
				</#if>
				
				<#list queries as q>
				<#if q.type == 'String'>
				if(StringUtils.isNotBlank(obj.get${q.name?cap_first}())){
				</#if>
				<#if q.type != 'String'>
				if(obj.get${q.name?cap_first}() != null){
				</#if>
					WHERE("${q.columnName} = ${r'#{'}${q.name}${r'}'}");
				}
				</#list>
				
				<#list fuzzies as f>
				<#if f.type == 'String'>
				if(StringUtils.isNotBlank(obj.get${f.name?cap_first}())){
				</#if>
				<#if f.type != 'String'>
				if(obj.get${f.name?cap_first}() != null){
				</#if>
					WHERE("${f.columnName} LIKE CONCAT('%',${r'#{'}${f.name}${r'}'},'%')");
				}
				</#list>
				
			}
		}.toString();
	}
	
	public String generateFindPageSql(${modelName} obj){
		StringBuilder sql = new StringBuilder(generateFindListSql(obj));
		sql.append(" LIMIT ");
		sql.append((obj.getPageNumber() - 1) * obj.getPageSize());
		sql.append(", ");
		sql.append(obj.getPageSize());
		return sql.toString();
	}
 
 	public String generateFindOneSql(String id){
		return new SQL(){
			{
				SELECT(COLUMNS);
				FROM("${tableName}");
				
				WHERE("id = ${r'#{'}id${r'}'}");
			}
		}.toString();
	}
	
	public String generateInsertSql(${modelName} obj){
		return new SQL(){
			{
				INSERT_INTO("${tableName}");
				
				<#list attrs as a>
				<#if logicDelete && a.columnName == logicDeleteColumnName>
				VALUES("${logicDeleteColumnName}", "${logicNormalValue}");
				<#else>
				VALUES("${a.columnName}", "${r'#{'}${a.name}${r'}'}");
				</#if>
				</#list>
			}
		}.toString();
	}
	
	public String generateUpdateSql(${modelName} obj){
		return new SQL(){
			{
				UPDATE("${tableName}");
				
				<#list attrs as a>
				<#if (logicDelete && a.columnName == logicDeleteColumnName) || a.columnName == 'id'
					|| a.columnName == 'create_time' || a.columnName == 'create_by'>
				<#else>
				SET("${a.columnName} = ${r'#{'}${a.name}${r'}'}");
				</#if>
				</#list>
				
				WHERE("id = ${r'#{'}id${r'}'}");
			}
		}.toString();
	}
	
	<#if logicDelete>
	public String generateDeleteSql(String id){
		return new SQL(){
			{
				UPDATE("${tableName}");
				
				SET("${logicDeleteColumnName} = '${logicDeleteValue}'");
				
				WHERE("id = ${r'#{'}id${r'}'}");
			}
		}.toString();
	}
	</#if>
}