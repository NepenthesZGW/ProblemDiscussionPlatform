package com.zgw.pdpcommon.filter.impl;

import com.zgw.pdpcommon.filter.SuffixFilter;

import java.util.Arrays;

/**
 * @author 忘忧症
 * @Distribute
 */
public class ImageSuffixFilter extends SuffixFilter {

    {

        suffixes.add(".JPG");
        suffixes.add(".JPEG");
        suffixes.add(".PNG");
        suffixes.add(".GIF");
        suffixes.add(".BMP");
        suffixes.add(".WBMP");
    }
}
