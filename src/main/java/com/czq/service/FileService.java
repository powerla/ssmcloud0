package com.czq.service;

import com.czq.pojo.File;
import com.czq.pojo.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileService {
    List<File> queryfileAll();
    List<File> queryFileRemove();
    List<File> ownerFind(@Param("owner") String owner,@Param("status") int status);
    List<File> typeFind(@Param("type") String type);
    File queryByfileId(@Param("fileId") int fileId);
    void changeStatus(@Param("fileId") int fileId,@Param("status") int status,String deleteTime);
    String uploadFile(
            @Param("fileName")   String fileName,
            @Param("userId")   int userId,
            @Param("createTime")   String createTime,
            @Param("owner")   String owner,
            @Param("status")   int status,
            @Param("hdfsPath")   String hdfsPath,
            @Param("fileSize")   String fileSize,
            @Param("type")     String type
    );
    void deleteById(@Param("fileId")  int fileId);

    int selectCount();

    PageBean<File> findByPage(int currentPage);
}
