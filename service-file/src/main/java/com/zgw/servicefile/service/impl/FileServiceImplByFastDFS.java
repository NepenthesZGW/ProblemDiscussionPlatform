package com.zgw.servicefile.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.FileInfo;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.zgw.pdpcommon.dto.ResponseResult;
import com.zgw.pdpcommon.util.FileUtil;
import com.zgw.servicefile.service.FileService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;


/**
 * @author 忘忧症
 * @Distribute 使用FastDFS实现文件的服务
 */

@Service
public class FileServiceImplByFastDFS implements FileService<Set<MetaData>> {


    private static final Logger log= LoggerFactory.getLogger(FileServiceImplByFastDFS.class);

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
        for (MultipartFile file: files) {
                try {
                    StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), metaData);
                    paths.add(storePath.getFullPath());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }catch (Exception ex){
                    log.error(ex.getMessage()); //记录文件上传失败的错误日志
                    deleteFile(paths);
                    return ResponseResult.fail(500,file.getName()+"文件上传错误");
                }
        }
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
        for (MultipartFile file : files) {
            if (FileUtil.isImageSuffix(file.getOriginalFilename())){
               //如果文件是图片,则进行处理
                //1：调用带有缩略图的方法获取fdfs返回的组名和相对路径名
                //2：获取缩略图的文件名
                //3：把原始图地址和缩略图地址加到容器
                try {
                    StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), file.getOriginalFilename(), metaData);
                    String slavePath = thumbImageConfig.getThumbImagePath(storePath.getPath());
                    imageUrls.add(storePath.getFullPath());
                    imageUrls.add(storePath.getGroup()+"/"+slavePath);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception ex){
                    //与fdfs交互出现运行时异常
                    //1：记录日志
                    //2：删除上传成功的图片
                    log.error(ex.getMessage());
                    deleteFile(imageUrls);
                    return ResponseResult.fail(500,file.getName()+"文件上传错误");
                }
            }
        }

        return ResponseResult.success(imageUrls);
    }


    /**
     *  删除一组文件
     * @param paths 文件路径数组
     * @return
     */
    @Override
    public ResponseResult deleteFile(Collection<String> paths) {
        boolean flag=true;
        for (String path : paths) {
            try {
                storageClient.deleteFile(path);
            }catch (Exception ex){
                log.error(ex.getMessage());
                flag=false;
            }
        }
        return flag?ResponseResult.success(null):ResponseResult.fail(null);
    }


}
