package com.czq.service;

import com.czq.dao.FoldMapper;
import com.czq.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("FoldService")
public class FoldServiceImpl implements FoldService{

    private FoldMapper foldMapper;
    @Autowired
    public void setFoldMapper(FoldMapper foldMapper)
    {this.foldMapper=foldMapper;}
    @Override
    public void addFold(int folderId, String folderName, String hdfsPath, int parentId) {
        foldMapper.addFold(folderId,folderName,hdfsPath,parentId);
    }
}
