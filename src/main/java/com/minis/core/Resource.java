package com.minis.core;

import java.util.Iterator;
//将外部的配置信息都当成Resource（资源来进行抽象）
public interface Resource extends Iterator<Object> {
}
