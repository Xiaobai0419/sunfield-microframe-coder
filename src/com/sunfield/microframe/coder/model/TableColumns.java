package com.sunfield.microframe.coder.model;

import java.io.Serializable;

/**
 * 表结构
 * @author wangnan
 *
 */
public class TableColumns implements Serializable{

	private static final long serialVersionUID = 8522784025714425467L;
	
	/**字段名称**/
	private String columnName;

	/**字段类型**/
	private String dataType;
	
	/**字符长度**/
	private Integer charMaxLength;
	
	/**数字长度**/
	private Integer numPrecision;
	
	/**是否可空**/
	private String isNullable;
	
	/**键类型**/
	private String columnKey;
	
	/**字段注释**/
	private String columnComment;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Integer getCharMaxLength() {
		return charMaxLength;
	}

	public void setCharMaxLength(Integer charMaxLength) {
		this.charMaxLength = charMaxLength;
	}

	public Integer getNumPrecision() {
		return numPrecision;
	}

	public void setNumPrecision(Integer numPrecision) {
		this.numPrecision = numPrecision;
	}

	public String getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
}
