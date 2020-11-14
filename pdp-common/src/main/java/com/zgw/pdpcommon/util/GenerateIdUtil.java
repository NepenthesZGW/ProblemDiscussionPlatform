package com.zgw.pdpcommon.util;


import com.zgw.pdpcommon.strategy.generateid.IGenerateIdStrategy;
import com.zgw.pdpcommon.strategy.generateid.impl.SnowflakeStrategy;

/**
 * @author 忘忧症
 * @Distribute
 */
public class GenerateIdUtil {

    private static final IGenerateIdStrategy generateIdStrategy=new SnowflakeStrategy(2,3);

    public long getNextId(){
        return generateIdStrategy.generateNextId();
    }

}
