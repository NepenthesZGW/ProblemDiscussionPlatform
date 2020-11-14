package com.zgw.servicefile.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.zgw.pdpcommon.dto.ResponseResult;
import com.zgw.servicefile.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


/**
 * @author 忘忧症
 * @Distribute 使用FastDFS实现文件的服务
 */

@Service
public class FileServiceImplByFastDFS implements FileService<Set<MetaData>> {



    @Autowired
    protected FastFileStorageClient storageClient;

    @Autowired
    private ThumbImageConfig thumbImageConfig;


    /**
     * web上传普通文件
     * @param files    文件数组
     * @param metaData 元数据信息
     * @return
     */
    @Override
    public ResponseResult uploadWebFileAndMetaData(MultipartFile[] files, Set<MetaData> metaData) {

        List<String> paths=new ArrayList<>();

        return ResponseResult.success(paths);
    }


    /**
     * 上传一组图片及缩略图
     * @param files 图片文件
     * @param metaData
     * @return
     */
    @Override
    public ResponseResult uploadImageAndCrtThumbImage(MultipartFile[] files, Set<MetaData> metaData)  {

        List<String> imageUrls=new ArrayList<>(); //该容器存储图片地址的配置


        return ResponseResult.success(imageUrls);
    }


    /**
     *  删除一组文件
     * @param paths 文件路径数组
     * @return
     */
    @Override
    public ResponseResult deleteFile(String... paths) {
        return null;
    }


}
