package com.sunfield.microframe.coder.model;

import java.io.Serializable;

/**
 * 实体结构
 * @author wangnan
 *
 */
public class ModelColumns implements Serializable{

	private static final long serialVersionUID = 1233392741723633683L;

	/**属性类型**/
	private String type;
	
	/**属性名**/
	private String name;
	
	/**属性注释**/
	private String columnComment;
	
	/**字段长度**/
	private String length;
	
	/**字段长度**/
	private String nullable;
	
	/**是否为主键**/
	private boolean prikey;
	
	/**映射数据库字段名称**/
	private String columnName;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	public boolean isPrikey() {
		return prikey;
	}

	public void setPrikey(boolean prikey) {
		this.prikey = prikey;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
}
