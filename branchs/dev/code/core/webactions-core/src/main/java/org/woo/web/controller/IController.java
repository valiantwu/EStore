package org.woo.web.controller;


import org.woo.dataentity.model.Page;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/25.
 */
public interface IController<E> {
    /*
    *验证Token
    *验证不和法的情况jose4j会抛出一个异常
    *
    */
    Map<String,Object> resultPageUtil(Page<E> tPage);
    Map<String,Object> resultCollectionUtil(Collection<E> eCollection);
    Map<String,Object> resultEUtil(E e);
}
