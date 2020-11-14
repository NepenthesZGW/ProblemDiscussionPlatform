package com.zgw.servicefile.service;

import com.zgw.pdpcommon.dto.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Collection;

/**
 * @author 忘忧症
 * @Distribute
 */
public interface FileService<T> {

    ResponseResult uploadWebFileAndMetaData(MultipartFile[] files, T t);
    ResponseResult uploadImageAndCrtThumbImage(MultipartFile[] files, T t);
    ResponseResult deleteFile(String... paths);
}
