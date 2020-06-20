package com.czq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fold {
    private int folderId;
    private String folderName;
    private String hdfsPath;
    private int parentId;
}
