package com.minis.beans;

import java.util.*;

/**
 *addArgumentValue：将参数值添加到列表或映射中。
 * hasIndexedArgumentValue：检查是否存在指定索引的参数。
 * addGenericArgumentValue：添加一个通用的参数值。
 * getIndexedArgumentValue：按索引检索参数。
 * getGenericArgumentValue：按名称检索参数。
 * getArgumentCount：返回通用参数值的数量。
 * isEmpty：检查是否没有参数值。
 */
public class ArgumentValues {
    //用于按索引存储参数（适用于构造函数参数）
    private final Map<Integer, ArgumentValue> indexedArgumentValues = new HashMap<>(0);
    //按其通用属性存储参数（适用于构造函数或属性值）。
    private final List<ArgumentValue> genericArgumentValues = new ArrayList<>();

    public ArgumentValues() {
    }

    private void addArgumentValue(Integer key, ArgumentValue newValue) {
        indexedArgumentValues.put(key, newValue);
    }

    public void addArgumentValue(ArgumentValue argumentValue) {
        this.genericArgumentValues.add(argumentValue);
    }

    public boolean hasIndexedArgumentValue(int index) {
        return this.indexedArgumentValues.containsKey(index);
    }

    public void addGenericArgumentValue(Object value, String type) {
        this.genericArgumentValues.add(new ArgumentValue(type, value));
    }

    private void addGenericArgumentValue(ArgumentValue newValue) {
        if (newValue.getName() != null) {
            this.genericArgumentValues.removeIf(currentValue -> newValue.getName().equals(currentValue.getName()));
        }
        this.genericArgumentValues.add(newValue);
    }

    public ArgumentValue getIndexedArgumentValue(int index) {
        ArgumentValue argumentValue = this.genericArgumentValues.get(index);
        return argumentValue;
    }

    public ArgumentValue getGenericArgumentValue(String requiredName) {
        for (ArgumentValue valueHolder : this.genericArgumentValues) {
            if (valueHolder.getName() != null && (!valueHolder.getName().equals(requiredName))) {
                continue;
            }
            return valueHolder;
        }
        return null;
    }

    public int getArgumentCount() {
        return this.genericArgumentValues.size();
    }

    public boolean isEmpty() {
        return this.genericArgumentValues.isEmpty();
    }

}
