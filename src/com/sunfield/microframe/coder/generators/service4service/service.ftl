package ${packageName};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingapi.tx.annotation.ITxTransaction;

import com.sunfield.microframe.common.response.Page;

import ${modelPackage};
import ${mapperPackage};

/**
 * ${tableName} service
 * @author sunfield coder
 */
@Service
public class ${modelName}Service implements ITxTransaction{

	@Autowired
	private ${mapperName} mapper;
	
	public List<${modelName}> findList(${modelName} obj){
		return mapper.findList(obj);
	}
	
	public Page<${modelName}> findPage(${modelName} obj){
		List<${modelName}> totalList = mapper.findList(obj);
		if(!totalList.isEmpty()){
			List<${modelName}> pageList = mapper.findPage(obj);
			return new Page<${modelName}>(totalList.size(), obj.getPageSize(), obj.getPageNumber(), pageList);
		}else{
			return new Page<${modelName}>();
		}
	}
	
	public ${modelName} findOne(String id){
		return mapper.findOne(id);
	}
	
	@Transactional
	public ${modelName} insert(${modelName} obj){
		obj.preInsert();
		if(mapper.insert(obj) > 0) {
			return obj;
		} else {
			return null;
		}
	}
	
	@Transactional
	public ${modelName} update(${modelName} obj){
		obj.preUpdate();
		if(mapper.update(obj) > 0) {
			return obj;
		} else {
			return null;
		}
	}
	
	<#if logicDelete>
	@Transactional
	public int delete(String id){
		return mapper.delete(id);
	}
	</#if>
	
}
