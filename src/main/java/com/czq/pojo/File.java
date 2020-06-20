package com.czq.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {
    private int fileId;
    private String fileName;
    private int userId;
    private String createTime;
    private String owner;
    private int status;//1为存在的文件
    private String hdfsPath;
    private String fileSize;
    private String type;
}
