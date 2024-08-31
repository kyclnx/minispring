package com.minis.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

/**
 * @author njx
 */
public class ClassPathXmlResource implements Resource{

    Document document;

    Element rootElement;

    Iterator<Element> elementIterator;

    public ClassPathXmlResource(String fileName) {
        SAXReader saxReader = new SAXReader();
        //
        URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
        try {
            //使用 SAXReader 实例来读取并解析 XML 文件。
            this.document = saxReader.read(xmlPath);
            //获取 XML 文档根元素的迭代器，用于遍历根元素下的所有子元素。
            this.rootElement = document.getRootElement();
            this.elementIterator = this.rootElement.elementIterator();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean hasNext() {
        return this.elementIterator.hasNext();
    }

    @Override
    public Object next() {
        return this.elementIterator.next();
    }
}
