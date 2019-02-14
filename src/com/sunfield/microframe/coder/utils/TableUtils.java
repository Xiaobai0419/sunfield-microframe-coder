package com.sunfield.microframe.coder.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.sunfield.microframe.coder.model.ModelColumns;
import com.sunfield.microframe.coder.model.TableColumns;

public class TableUtils {

    /**
     * 将表名转换为实体名
     * @param tableName 表名
     * @return
     */
    public static String tableName2ModelName(String tableName){
        StringBuilder n = new StringBuilder();
        if(StringUtils.isNotBlank(tableName)){
            int l = tableName.length();
            boolean upperNext = false;
            for(int i=0;i<l;i++){
                char c = tableName.charAt(i);
                if(i == 0 && Character.isLetter(c)){
                    n.append(Character.toUpperCase(c));
                    continue;
                }

                if(!Character.isLetter(c)){
                    upperNext = true;
                }else{
                    if(upperNext && Character.isLowerCase(c)){
                        n.append(Character.toUpperCase(c));
                        upperNext = false;
                    }else{
                        n.append(c);
                    }
                }
            }
        }
        return n.toString();
    }

    /**
     * 将表结构转换为实体结构
     * @param l 表结构
     * @return
     */
    public static List<ModelColumns> table2Model(List<TableColumns> l){
        List<ModelColumns> modelColumns = new ArrayList<ModelColumns>();
        ModelColumns mc = null;
        for(TableColumns tc : l){
            mc = new ModelColumns();
            mc.setType(dataType2FieldType(tc.getDataType()));
            mc.setName(columnName2FieldName(tc.getColumnName()));
            mc.setColumnComment(tc.getColumnComment());
            mc.setLength(getLength(tc));
            mc.setNullable("yes".equalsIgnoreCase(tc.getIsNullable()) ? "true" : "false");
            mc.setPrikey("pri".equalsIgnoreCase(tc.getColumnKey()));
            mc.setColumnName(tc.getColumnName());
            modelColumns.add(mc);
        }

        return modelColumns;
    }

    /**
     * 将字段名转换为属性名
     * @param columnName 字段名
     * @return
     */
    public static String columnName2FieldName(String columnName){
        StringBuilder n = new StringBuilder();
        if(StringUtils.isNotBlank(columnName)){
            int l = columnName.length();
            boolean upperNext = false;
            for(int i=0;i<l;i++){
                char c = columnName.charAt(i);
                if(!Character.isLetter(c)){
                    upperNext = true;
                }else{
                    if(upperNext && Character.isLowerCase(c)){
                        n.append(Character.toUpperCase(c));
                        upperNext = false;
                    }else{
                        n.append(c);
                    }
                }
            }
        }
        return n.toString();
    }

    /**
     * 将字段类型转换为属性类型
     * @param dataType 字段类型
     * @return
     */
    public static String dataType2FieldType(String dataType){
        switch(dataType){
            case "varchar": return "String";
            case "char": return "String";
            case "int": return "Integer";
            case "integer": return "Integer";
            case "bigint": return "Long";
            case "smallint": return "Short";
            case "tinyint": return "Byte";
            case "float": return "Float";
            case "double": return "Double";
            case "numberic": return "BigDecimal";
            case "bit": return "Boolean";
            case "timestamp": return "Date";
            case "date": return "Date";
            case "time": return "Date";
            case "datetime": return "Date";
            case "text": return "String";
            default: return "String";
        }
    }

    /**
     * 获取字段长度
     * @param tc 字段描述
     * @return
     */
    public static String getLength(TableColumns tc){
        if(tc.getCharMaxLength() != null) {
            return tc.getCharMaxLength().toString();
        }
        if(tc.getNumPrecision() != null) {
            return tc.getNumPrecision().toString();
        } else {
            return "16";
        }
    }

    /**
     * 查询表/视图中是否有主键
     * @param l 表结构
     * @return
     */
    public static boolean hasPriKey(List<TableColumns> l){
        for(TableColumns tc : l){
            if("pri".equalsIgnoreCase(tc.getColumnKey())) {
                return true;
            }
        }
        return false;
    }

    /**时间类型集合**/
    public final static String[] DATE_TYPE_ARRAY = {"date", "time", "year", "timestamp", "datetime"};

    /**
     * 查询表/视图中是否有时间
     * @param l 表结构
     * @return
     */
    public static boolean hasTime(List<TableColumns> l){
        for(TableColumns tc : l){
            if(ArrayUtils.contains(DATE_TYPE_ARRAY, tc.getDataType())) {
                return true;
            }
        }
        return false;
    }
}
