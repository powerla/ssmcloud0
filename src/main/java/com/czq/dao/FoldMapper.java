package com.czq.dao;

import com.czq.pojo.Fold;
import org.apache.ibatis.annotations.Param;

public interface FoldMapper {

    Fold queryID(@Param("folderId") int folderId);

    void addFold(@Param("folderId") int folderId,
            @Param("folderName") String folderName,
            @Param("hdfsPath") String hdfsPath,
            @Param("parentId") int parentId);
}
//    private int folderId;
//    private String folderName;
//    private String hdfsPath;
//    private int parentId;
