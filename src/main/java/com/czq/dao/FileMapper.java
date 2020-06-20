package com.czq.dao;

import com.czq.pojo.File;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface FileMapper {
    List<File> queryfileAll();
    List<File> queryFileRemove();
    List<File> ownerFind(@Param("owner") String owner,@Param("status") int status);
    List<File> typeFind(@Param("type") String type);
    File queryByfileId(@Param("fileId") int fileId);
    void changeStatus(@Param("fileId") int fileId,@Param("status") int status,@Param("deleteTime") String deleteTime);
    void uploadFile(
         @Param("fileName")   String fileName,
         @Param("userId")   int userId,
         @Param("createTime")   String createTime,
         @Param("owner")   String owner,
         @Param("status")   int status,
         @Param("hdfsPath")   String hdfsPath,
         @Param("fileSize")   String fileSize,
         @Param("type")     String type
    );
    void deleteById(@Param("fileId") int fileId);

    int selectCount();
    List<File> findByPage(HashMap<String,Object> map);

}
