package com.minis.context;

import java.util.EventObject;
//**事件监听**的基础之上进行了简单的封装。。
//目前还没有任何实现，方便后续进行扩展

/**
 * @author njx
 */
public class ApplicationEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a prototypical Event.
     *
     * @param arg0 The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object arg0) {
        super(arg0);
    }
}
