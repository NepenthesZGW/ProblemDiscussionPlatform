package com.zgw.servicefile.controller;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.upload.FastFile;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.zgw.pdpcommon.dto.ResponseResult;
import com.zgw.pdpcommon.util.DateUtil;
import com.zgw.servicefile.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 忘忧症
 * @Distribute web文件请求的控制器
 */

@RestController
public class FileRequestController {


    @Autowired
    FileService fileService;

    @RequestMapping("/uploadFile")
    public ResponseResult uploadFileAndMetaData(HttpServletRequest request){

        String id = request.getParameter("id");
        Set<MetaData> metaData=new HashSet<>();
        metaData.add(new MetaData("MemberId",id));
        metaData.add(new MetaData("CreateDate", DateUtil.getCurrentDate()));

        MultipartFile[] files =(MultipartFile[]) request.getAttribute("files");
        return    fileService.uploadWebFileAndMetaData(files,metaData);

    }

    @RequestMapping("/uploadImageFile")
    public ResponseResult uploadImageFile(HttpServletRequest request){

        String id = request.getParameter("id");
        Set<MetaData> metaData=new HashSet<>();
        metaData.add(new MetaData("MemberId",id));
        metaData.add(new MetaData("CreateDate", DateUtil.getCurrentDate()));

        MultipartFile[] files =(MultipartFile[]) request.getAttribute("images");
        return    fileService.uploadImageAndCrtThumbImage(files,metaData);

    }
}
