package com.minis.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
    //存储PropertyValue对象的列表
    private final List<PropertyValue> propertyValueList;
    //将propertyValueList初始化为一个空的ArrayList
    public PropertyValues() {
        this.propertyValueList = new ArrayList<>(0);
    }
    public List<PropertyValue> getPropertyValueList() {
        return this.propertyValueList;
    }
    //返回属性值的大小
    public int size() {
        return this.propertyValueList.size();
    }
    //通过指定类型、名称和值创建并添加一个`propertyValue`对象
    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }
    public void addPropertyValue(String propertyType, String propertyName, Object propertyValue) {
        addPropertyValue(new PropertyValue(propertyType, propertyName, propertyValue));
    }
    public void removePropertyValue(PropertyValue pv) {
        this.propertyValueList.remove(pv);
    }
    public void removePropertyValue(String propertyName) {
        this.propertyValueList.remove(getPropertyValue(propertyName));
    }
    //返回 propertyValueList 的数组表示
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[this.propertyValueList.size()]);
    }
    //根据属性名称查找并返回对应的 PropertyValue 对象。如果找不到，则返回 null。
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }
    //获取指定名称的属性值。如果找不到，则返回 null。
    public Object get(String propertyName) {
        PropertyValue pv = getPropertyValue(propertyName);
        return pv != null ? pv.getValue() : null;
    }
    public boolean contains(String propertyName) {
        return getPropertyValue(propertyName) != null;
    }
    //检查 propertyValueList 是否为空。
    public boolean isEmpty() {
        return this.propertyValueList.isEmpty();
    }
}