package ${packageName};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingapi.tx.annotation.ITxTransaction;

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
	
	public List<${modelName}> findPage(${modelName} obj){
		return mapper.findPage(obj);
	}
	
	public ${modelName} findOne(String id){
		return mapper.findOne(id);
	}
	
	@Transactional
	public ${modelName} insert(${modelName} obj){
		obj.preInsert();
		if(mapper.insert(obj) > 0)
			return obj;
		else
			return null;
	}
	
	@Transactional
	public ${modelName} update(${modelName} obj){
		obj.preUpdate();
		if(mapper.update(obj) > 0)
			return obj;
		else
			return null;
	}
	
	<#if logicDelete>
	@Transactional
	public int delete(String id){
		return mapper.delete(id);
	}
	</#if>
	
}
