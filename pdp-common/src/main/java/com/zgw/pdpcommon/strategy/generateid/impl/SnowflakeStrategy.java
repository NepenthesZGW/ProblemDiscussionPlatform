package com.zgw.pdpcommon.strategy.generateid.impl;

import com.zgw.pdpcommon.strategy.generateid.IGenerateIdStrategy;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 忘忧症
 * @Distribute
 */
public class SnowflakeStrategy implements IGenerateIdStrategy {


    /** 时间部分所占长度 */
    private static final int TIME_LEN = 41;
    /** 数据中心id所占长度 */
    private static final int DATA_LEN = 5;
    /** 机器id所占长度 */
    private static final int WORK_LEN = 5;
    /** 毫秒内序列所占长度 */
    private static final int SEQ_LEN = 12;

    /** 定义起始时间 2015-01-01 00:00:00 */
    private static final long START_TIME = 1420041600000L;

    /** 时间部分向左移动的位数 22 */
    private static final int TIME_LEFT_BIT = 64 - 1 - TIME_LEN;


    /** 数据中心id最大值 31 */
    private static final int DATA_MAX_NUM = ~(-1 << DATA_LEN);
    /** 机器id最大值 31 */
    private static final int WORK_MAX_NUM = ~(-1 << WORK_LEN);


    /** 数据中心id左移位数 17 */
    private static final int DATA_LEFT_BIT = TIME_LEFT_BIT - DATA_LEN;
    /** 机器id左移位数 12 */
    private static final int WORK_LEFT_BIT = DATA_LEFT_BIT - WORK_LEN;
    /** 毫秒内序列的最大值 4095 */
    private static final long SEQ_MAX_NUM = ~(-1 << SEQ_LEN);

    /** 上一次的毫秒内序列值 */
    private volatile long lastSeq = 0L;
    /** 上次生成ID的时间截 */
    private volatile long lastTimestamp = -1L;

    /** 自动获取数据中心id（可以手动定义 0-31之间的数） */
    private long dataId;
    /** 自动机器id（可以手动定义 0-31之间的数） */
    private long wordId;


    public SnowflakeStrategy(long dataId, long wordId) {
        if (dataId<0||dataId>DATA_MAX_NUM){
            throw new RuntimeException("dataId > DATA_MAX_NUM");
        }
        if (wordId<0||wordId>WORK_MAX_NUM){
            throw new RuntimeException("wordId > WORK_MAX_NUM");
        }
        this.dataId = dataId;
        this.wordId = wordId;
    }

    private long getCurrentTimestamp(){
        return System.currentTimeMillis();
    }
    /**
     * 获取下一不同毫秒的时间戳，不能与最后的时间戳一样
     */
    private long nextMillis(long lastMillis) {
        long now =  getCurrentTimestamp();
        while (now <= lastMillis) {
            now = getCurrentTimestamp();
        }
        return now;
    }

    public synchronized  long generateNextId(){
        long now = getCurrentTimestamp();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (now < lastTimestamp) {
            throw new RuntimeException(String.format("系统时间错误！ %d 毫秒内拒绝生成雪花ID！", START_TIME - now));
        }
        if (now == lastTimestamp) {
            lastSeq = (lastSeq + 1) & SEQ_MAX_NUM;
            if (lastSeq == 0){
                now = nextMillis(lastTimestamp);
            }
        } else {
            lastSeq = 0;
        }
        //上次生成ID的时间截
        lastTimestamp = now;
        return ((now - START_TIME) << TIME_LEFT_BIT) | (dataId << DATA_LEFT_BIT) | (wordId << WORK_LEFT_BIT) | lastSeq;
    }

}
