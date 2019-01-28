package ${packageName};

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ${modelPackage};

/**
 * ${tableName} client
 * @author sunfield coder
 */
@FeignClient(name = "${daoApplicationName}")
@RequestMapping(value = "/dao/${modelName}")
public interface ${modelName}Client {

	@RequestMapping(value = "/findList")
    public List<${modelName}> findList(@RequestBody ${modelName} obj);
	
	@RequestMapping(value = "/findPage")
    public List<${modelName}> findPage(@RequestBody ${modelName} obj);
	
	@RequestMapping(value = "/findOne")
    public ${modelName} findOne(@RequestParam("id")String id);
	
	@RequestMapping(value = "/insert")
    public ${modelName} insert(@RequestBody ${modelName} obj);
	
	@RequestMapping(value = "/update")
    public ${modelName} update(@RequestBody ${modelName} obj);
	
	<#if logicDelete>
	@RequestMapping(value = "/delete")
    public int delete(@RequestParam("id")String id);
    </#if>
	
}
