package ${packageName};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
<#list packages as p>
import ${p};
</#list>

import com.sunfield.microframe.domain.base.BaseDomain;

/**
 * ${tableName} bean
 * @author sunfield coder
 */
@ApiModel(value="${modelName}", description="")
public class ${modelName} extends BaseDomain{

	<#list attrs as a>
	<#if a.columnName != 'del_flag' && a.columnName != 'create_time' && a.columnName != 'update_time' && a.columnName != 'id'>
	@ApiModelProperty(value="${a.columnComment}", dataType="${a.type}")
	private ${a.type} ${a.name};
	
	</#if>
	</#list>
	
	<#list attrs as a>
	<#if a.columnName != 'del_flag' && a.columnName != 'create_time' && a.columnName != 'update_time' && a.columnName != 'id'>
	public ${a.type} get${a.name?cap_first}() {
		return ${a.name};
	}

	public void set${a.name?cap_first}(${a.type} ${a.name}) {
		this.${a.name} = ${a.name};
	}
	
	</#if>
	</#list>
}