package com.minis.beans;
public class PropertyValue {

    private final String name;

    private final Object value;

    private final String type;

    public PropertyValue(String type, String name, Object value) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }


    public String getType() {
        return type;
    }
}
//单看Value这个词，后面不带上s就表明它只是针对某一个属性或者某一个参数，但是一个bean里面有很多的属性，
// 有很多的参数，所以我们就需要一个带s的集合类。
//ArgumentValues和PropertyValues两个类，封装 、增加、获取、判断等操作方法，简化了调用。即给外面提供单个的参数/属性的对象，也提供集合对象