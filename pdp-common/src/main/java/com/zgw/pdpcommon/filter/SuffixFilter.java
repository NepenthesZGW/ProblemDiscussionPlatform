package com.zgw.pdpcommon.filter;

import com.zgw.pdpcommon.filter.Filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author 忘忧症
 * @Distribute
 */
public abstract class SuffixFilter implements Filter<String> {

    protected  final List<String> suffixes=new ArrayList<>();

    protected  void  addSuffixes(String... strings){
        Arrays.stream(strings).forEach(o->{
            suffixes.add(o.toUpperCase());
        });
    }

    @Override
    public boolean accept(String str) {
        return suffixes.stream().anyMatch(o->str.toUpperCase().endsWith(o));
    }

}
