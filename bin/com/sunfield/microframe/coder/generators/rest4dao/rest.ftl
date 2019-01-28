package ${packageName};

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ${modelPackage};
import ${servicePackage};

/**
 * ${tableName} rest
 * @author sunfield coder
 */
@RestController
@RequestMapping(value = "/dao/${modelName}")
public class ${modelName}Rest{
	
	@Autowired
	private ${serviceName} service;
	
	@RequestMapping(value = "/findList")
    public List<${modelName}> findList(@RequestBody ${modelName} obj) {
    	return service.findList(obj);
    }
	
	@RequestMapping(value = "/findPage")
    public List<${modelName}> findPage(@RequestBody ${modelName} obj) {
    	return service.findPage(obj);
    }
	
	@RequestMapping(value = "/findOne")
    public ${modelName} findOne(String id) {
    	if(StringUtils.isBlank(id))
			return null;
    
		return service.findOne(id);
    }
	
	@RequestMapping(value = "/insert")
    public ${modelName} insert(@RequestBody ${modelName} obj) {
    	return service.insert(obj);
    }
	
	@RequestMapping(value = "/update")
    public ${modelName} update(@RequestBody ${modelName} obj) {
    	if(StringUtils.isBlank(obj.getId()))
			return null;
    
    	return service.update(obj);
    }
	
	<#if logicDelete>
	@RequestMapping(value = "/delete")
    public int delete(String id) {
    	if(StringUtils.isBlank(id))
			return 0;
    
    	return service.delete(id);
    }
    </#if>
    
}
