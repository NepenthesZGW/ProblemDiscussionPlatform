package com.zgw.pdpcommon.filter;


/**
 * @author 忘忧症
 * @Distribute
 */
public interface Filter<T> {


    boolean accept(T t);

}
