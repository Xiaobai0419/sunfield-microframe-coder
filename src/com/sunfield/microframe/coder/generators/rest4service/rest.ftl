package ${packageName};

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sunfield.microframe.common.response.Page;
import com.sunfield.microframe.common.response.ResponseBean;
import com.sunfield.microframe.common.response.ResponseStatus;

import ${modelPackage};
import ${fallbackPackage};
import ${servicePackage};

/**
 * ${tableName} rest
 * @author sunfield coder
 */
@RestController
@RequestMapping(value = "/${modelName}")
public class ${modelName}Rest extends ${fallbackName}{
	
	@Autowired
	private ${serviceName} service;
	
	@ApiOperation(value="查询列表")
	@ApiImplicitParam(name = "obj", value = "", required = true, dataType = "${modelName}")
	@RequestMapping(value = "/findList", method = RequestMethod.POST)
	@HystrixCommand(fallbackMethod = "findListFallback")
    public ResponseBean<List<${modelName}>> findList(@RequestBody ${modelName} obj) {
		List<${modelName}> list = service.findList(obj);
		if(!list.isEmpty()) {
			return new ResponseBean<List<${modelName}>>(ResponseStatus.SUCCESS, list);
		} else {
			return new ResponseBean<List<${modelName}>>(ResponseStatus.NO_DATA);
		}
    }
	
	@ApiOperation(value="分页查询")
	@ApiImplicitParam(name = "obj", value = "", required = true, dataType = "${modelName}")
	@RequestMapping(value = "/findPage", method = RequestMethod.POST)
	@HystrixCommand(fallbackMethod = "findPageFallback")
    public ResponseBean<Page<${modelName}>> findPage(@RequestBody ${modelName} obj) {
    	return new ResponseBean<Page<${modelName}>>(ResponseStatus.SUCCESS, service.findPage(obj));
    }
	
	@ApiOperation(value="根据主键查询")
	@ApiImplicitParam(name = "obj", value = "", required = true, dataType = "${modelName}")
	@RequestMapping(value = "/findOne", method = RequestMethod.POST)
	@HystrixCommand(fallbackMethod = "findOneFallback")
    public ResponseBean<${modelName}> findOne(@RequestBody ${modelName} obj) {
    	if(StringUtils.isBlank(obj.getId())) {
			return new ResponseBean<${modelName}>(ResponseStatus.PARAMS_ERROR);
    	}
    	${modelName} object = service.findOne(obj.getId());
    	if(object != null) {
    		return new ResponseBean<${modelName}>(ResponseStatus.SUCCESS, object);
    	} else {
    		return new ResponseBean<${modelName}>(ResponseStatus.NO_DATA);
		}
    }
	
	@ApiOperation(value="新增")
	@ApiImplicitParam(name = "obj", value = "", required = true, dataType = "${modelName}")
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@HystrixCommand(fallbackMethod = "insertFallback")
    public ResponseBean<${modelName}> insert(@RequestBody ${modelName} obj) {
		${modelName} object = service.insert(obj);
		if(object != null) {
			return new ResponseBean<${modelName}>(ResponseStatus.SUCCESS, object);
		} else {
			return new ResponseBean<${modelName}>(ResponseStatus.FAIL);
		}
    }
	
	@ApiOperation(value="更新")
	@ApiImplicitParam(name = "obj", value = "", required = true, dataType = "${modelName}")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@HystrixCommand(fallbackMethod = "updateFallback")
    public ResponseBean<${modelName}> update(@RequestBody ${modelName} obj) {
    	if(StringUtils.isBlank(obj.getId())) {
			return new ResponseBean<${modelName}>(ResponseStatus.PARAMS_ERROR);
    	}
    	${modelName} object = service.update(obj);
    	if(object != null) {
			return new ResponseBean<${modelName}>(ResponseStatus.SUCCESS, object);
		} else {
			return new ResponseBean<${modelName}>(ResponseStatus.FAIL);
		}
    }
	
	@ApiOperation(value="删除")
	@ApiImplicitParam(name = "obj", value = "", required = true, dataType = "${modelName}")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@HystrixCommand(fallbackMethod = "deleteFallback")
    public ResponseBean<${modelName}> delete(@RequestBody ${modelName} obj) {
    	if(StringUtils.isBlank(obj.getId())) {
			return new ResponseBean<${modelName}>(ResponseStatus.PARAMS_ERROR);
    	}
    	if(service.delete(obj.getId()) > 0) {
    		return new ResponseBean<${modelName}>();
    	} else {
    		return new ResponseBean<${modelName}>(ResponseStatus.NO_DATA);
		}
    }
    
}
