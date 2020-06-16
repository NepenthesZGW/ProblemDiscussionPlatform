package com.zgw.servicefile;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.zgw.servicefile.service.FileService;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class ServiceFileApplicationTests {

    @Autowired
    protected FastFileStorageClient storageClient;

    @Autowired
    FileService fileService;

    @Test
    void contextLoads() throws FileNotFoundException {

        File file = new File("F:/aaa.txt");
        StorePath storePath = storageClient.uploadFile(new FileInputStream(file), file.length(), FilenameUtils.getExtension(file.getName()), createMetaData());
        System.out.println(storePath);
        //wKgvZF7ntiqANwTlAAAAESl2w3E394.txt

    }
    private Set<MetaData> createMetaData() {
        Set<MetaData> metaDataSet = new HashSet<>();
        metaDataSet.add(new MetaData("Author", "tobato"));
        metaDataSet.add(new MetaData("CreateDate", "2016-01-05"));
        return metaDataSet;
    }
}
