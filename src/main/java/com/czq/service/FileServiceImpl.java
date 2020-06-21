package com.czq.service;

import com.czq.dao.FileMapper;
import com.czq.pojo.File;
import com.czq.pojo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;

@Service("FileService")
public class FileServiceImpl implements FileService {

    private FileMapper fileMapper;
    @Autowired
    public void setFileMapper(FileMapper fileMapper)
    {this.fileMapper=fileMapper;}

    @Override
    public List<File> queryfileAll() {
        return fileMapper.queryfileAll();
    }

    @Override
    public List<File> queryFileRemove() {
        return fileMapper.queryFileRemove();
    }

    @Override
    public List<File> ownerFind(String owner, int status) {

        return fileMapper.ownerFind(owner,status);
    }

    @Override
    public List<File> typeFind(String type) {
        return fileMapper.typeFind(type);
    }

    @Override
    public File queryByfileId(int fileId) {
        return fileMapper.queryByfileId(fileId);
    }


    public void changeStatus(int fileId, int status,String deleteTime) {
        fileMapper.changeStatus(fileId,status,deleteTime);
    }

    @Override
    public String uploadFile( String fileName, int userId, String createTime, String owner, int status, String hdfsPath, String fileSize,String type) {
        fileMapper.uploadFile(fileName,userId,createTime,owner,status,hdfsPath,fileSize,type);
        return "上传成功";
    }

    @Override
    public void deleteById(int fileId) {
        fileMapper.deleteById(fileId);
    }

    public int selectCount() {
        return fileMapper.selectCount();
    }

    public PageBean<File> findByPage(int currentPage) {
        HashMap<String,Object> map=new HashMap<String, Object>();
        PageBean<File> pageBean=new PageBean<File>();
        //封装当前页数
        pageBean.setCurrPage(currentPage);
        //每页显示的数据数
        int pageSize=5;
        pageBean.setPageSize(pageSize);
        //分装的总记录数
        int totalCount=fileMapper.selectCount();
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double tc=totalCount;
        Double num=Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());
        map.put("start",(currentPage-1)*pageSize);
        map.put("size",pageBean.getPageSize());
        //封装每页显示的数据
        List<File> list=fileMapper.findByPage(map);
        pageBean.setLists(list);
        return pageBean;
    }
}
