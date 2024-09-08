package com.minis.beans.factory.config;

import java.util.*;

/**
 * @author njx
 * @version 1.0
 * @since 1.0
 */
public class ConstructorArgumentValues {


    private final Map<Integer, ConstructorArgumentValue> indexedArgumentValues = new HashMap<>(0);

    private final List<ConstructorArgumentValue> genericArgumentValues = new ArrayList<>();

    public ConstructorArgumentValues() {
    }

    private void addArgumentValue(Integer key, ConstructorArgumentValue newValue) {
        indexedArgumentValues.put(key, newValue);
    }

    public void addArgumentValue(ConstructorArgumentValue argumentValue) {
        this.genericArgumentValues.add(argumentValue);
    }

    public boolean hasIndexedArgumentValue(int index) {
        return this.indexedArgumentValues.containsKey(index);
    }

    public void addGenericArgumentValue(Object value, String type) {
        this.genericArgumentValues.add(new ConstructorArgumentValue(type, value));
    }

    private void addGenericArgumentValue(ConstructorArgumentValue newValue) {
        if (newValue.getName() != null) {
            this.genericArgumentValues.removeIf(currentValue -> newValue.getName().equals(currentValue.getName()));
        }
        this.genericArgumentValues.add(newValue);
    }

    public ConstructorArgumentValue getIndexedArgumentValue(int index) {
        ConstructorArgumentValue argumentValue = this.genericArgumentValues.get(index);
        return argumentValue;
    }

    public ConstructorArgumentValue getGenericArgumentValue(String requiredName) {
        for (ConstructorArgumentValue valueHolder : this.genericArgumentValues) {
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
