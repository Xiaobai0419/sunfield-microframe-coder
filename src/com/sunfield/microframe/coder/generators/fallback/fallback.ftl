package ${packageName};

import java.util.List;

import com.sunfield.microframe.common.response.Page;
import com.sunfield.microframe.common.response.ResponseBean;
import com.sunfield.microframe.common.response.ResponseStatus;

import ${modelPackage};

/**
 * ${tableName} fallback
 * @author sunfield coder
 */
public class ${modelName}Fallback {
	
    public ResponseBean<List<${modelName}>> findListFallback(${modelName} obj) {
    	return new ResponseBean<List<${modelName}>>(ResponseStatus.BUSY);
    }
	
    public ResponseBean<Page<${modelName}>> findPageFallback(${modelName} obj) {
    	return new ResponseBean<Page<${modelName}>>(ResponseStatus.BUSY);
    }
	
    public ResponseBean<${modelName}> findOneFallback(${modelName} obj) {
		return new ResponseBean<${modelName}>(ResponseStatus.BUSY);
    }
	
    public ResponseBean<${modelName}> insertFallback(${modelName} obj) {
    	return new ResponseBean<${modelName}>(ResponseStatus.BUSY);
    }
	
    public ResponseBean<${modelName}> updateFallback(${modelName} obj) {
    	return new ResponseBean<${modelName}>(ResponseStatus.BUSY);
    }
	
	<#if logicDelete>
    public ResponseBean<${modelName}> deleteFallback(${modelName} obj) {
    	return new ResponseBean<${modelName}>(ResponseStatus.BUSY);
    }
    </#if>
}
