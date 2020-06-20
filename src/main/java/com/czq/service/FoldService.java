package com.czq.service;

import org.apache.ibatis.annotations.Param;

public interface FoldService {
    void addFold(int folderId,String folderName,String hdfsPath,int parentId);
}
