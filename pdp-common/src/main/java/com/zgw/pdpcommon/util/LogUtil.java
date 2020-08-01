package com.zgw.pdpcommon.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 忘忧症
 * @Distribute
 */
public class LogUtil {

    /**
     *  初始化两个静态Logger常量，一是为了快速获取（如果只是为了这个，那其实有点多余）
     *  更主要的是 利用类加载到内存中 类的静态代码块是单线程执行的
     *  不会造成多线程去创建 LoggerContext对象，从而不会引发日志可能会不成功记录的情况
     */
    private static final Logger ERROR_LOGGER=LoggerFactory.getLogger("error");
    private static final Logger WARN_LOGGER=LoggerFactory.getLogger("warn");


    /**
     *  获取 用于记录错误级别日志的 同步logger对象
     * @return 一个logger的门面接口对象
     */
    public static Logger getErrorLogger(){
        return ERROR_LOGGER;
    }

    /**
     * 获取 用于记录错误级别日志的 异步logger对象
     * @return 一个logger的门面接口对象
     */
    public static Logger getWarnLogger(){
        return WARN_LOGGER;
    }

    /**
     *  获取指定name的同步日志记录器对象
     * @param name 需要获取的日志记录器对象的名字
     * @return 一个logger的门面接口对象
     */
    public static Logger getSyncLogger(String name){
        return LoggerFactory.getLogger(name);
    }

    /**
     * 使用异步日志记录对象 来记录 info debug trace 信息，注意：
     *  因为异步logger 输出发出日志记录请求的调用者的完全限定类名，方法名，和行号 需要调用异常堆栈信息，这个操作比较耗时
     *  会导致生产者 生产消息太快，而消费者消费太慢，导致最后的性能比同步logger还要低
     *  所以为了获取到准确的类名来模糊定位消息位置，传进来的class必须要是发出日志请求的调用者的类对象
     *  否则 你将无法定位此日志 大概位置
     *
     *  提示：稍微有一点性能损耗，因为发出日志请求的类没有日志对象的引用，
     *  每次都需要通过传入他自己的类对象来获取，不过这种性能损耗相对来说可以忽略不记
     * @param clazz 发出日志请求的调用者的类对象
     * @return 一个logger的门面接口对象
     */
    public static Logger getAsyncLogger(Class clazz){
        return LoggerFactory.getLogger(clazz);
    }

    public static void main(String[] args) {

    }

}
