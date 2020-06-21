package com.czq.controller;
import com.czq.pojo.User;
import com.czq.service.FileService;
import com.czq.service.FoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("file")
public class FileController {

    @Autowired
    private FoldService foldService;

    @Autowired
    private FileService fileService;

    @RequestMapping("createfold")
    public String  creatFold(String folderName)
    {
        int parentId =1;
        int foldid=new Random().nextInt(100000);
        //十万以内的随机数作为id,减少重复
        String hdfsPath="F:\\load\\"+folderName;
        File dir = new File(hdfsPath);
        if (!dir.exists())
        {
            dir.mkdir();
            foldService.addFold(foldid,folderName,hdfsPath,parentId);
        }
        return "redirect:/file/all";
    }

    @RequestMapping("findfile")
    public String finaFile(String type,Model model,HttpSession session)
    {
        List<com.czq.pojo.File> list=fileService.typeFind(type);
        System.out.println(list==null);
        if(list==null)
        {
            list=fileService.queryfileAll();
            model.addAttribute("err","没有改类型的文件");
        }
        model.addAttribute("filelist",list);
        model.addAttribute("user",session.getAttribute("user"));
        return "index";
    }
    @RequestMapping("/all")
    public String listpage(HttpServletRequest request,@RequestParam(value="currentPage",defaultValue="1",required=false)int currentPage, Model model,HttpSession session)
    {
        List<com.czq.pojo.File> list = fileService.findByPage(currentPage).getLists();
        model.addAttribute("list",list);
        User user= (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user.getUsername());
        model.addAttribute("pagemsg", fileService.findByPage(currentPage));
        return "index";
    }

    @RequestMapping("/up")
    public String threeFileUpload(
            @RequestParam("uploadFile") CommonsMultipartFile uploadFile,
            HttpServletRequest request, ModelMap model, HttpSession session) {
        // 获得项目的路径
        ServletContext sc = request.getSession().getServletContext();
        // 上传位置
        String path ="F:\\load\\";
        File f = new File(path);
//        uploadFile(fileId,fileName,userId,createTime,owner,status,hdfsPath,fileSize,type);
        if (!f.exists())
            f.mkdirs();
            // 获得原始文件名
            String fileName = uploadFile.getOriginalFilename();
            System.out.println("原始文件名:" + fileName);
            String type =fileName.split("\\.")[1];//特殊符号需要转义
            // 不重复文件名
            String newFileName = UUID.randomUUID() + fileName;
            User user=(User)request.getSession().getAttribute("user");
            String createtime=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
            if (!uploadFile.isEmpty()) {
                try {
                    FileOutputStream fos = new FileOutputStream(path
                            + newFileName);
                    InputStream in = uploadFile.getInputStream();
                    System.out.println("newFileName = " + newFileName);
                    System.out.println("path = " + path);
                    System.out.println("files.getSize() = " + uploadFile.getSize()/1024+"kb");
                    System.out.println("files.getName() = " + uploadFile.getName());
                    System.out.println("files.getContentType() = " + uploadFile.getContentType());
                    System.out.println("时间");
                    int b = 0;
                    while ((b = in.read()) != -1) {
                        fos.write(b);
                    }
                    fos.close();
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    //        uploadFile(fileName,userId,createTime,owner,status,hdfsPath,fileSize,type);
                    fileService.uploadFile(newFileName,user.getId(),createtime,user.getUsername(),1,path + newFileName,uploadFile.getSize()/1024+"kb",type);
                }
            System.out.println("上传文件到:" + path + newFileName);
        }
        // 保存文件地址，用于JSP页面回显
        model.addAttribute("filemess", "上传成功");
        return "redirect:/file/all";
    }

    //图片预览
    @RequestMapping("/yulan/{fileId}")
    public void yulan(HttpServletResponse response,@PathVariable("fileId") int fileId)
    {
        com.czq.pojo.File file1=fileService.queryByfileId(fileId);
        String hdfsPath=file1.getHdfsPath();
        //获取图片格式
        String format=file1.getType();
        File file = new File(hdfsPath);
        try{
            BufferedImage image= ImageIO.read(new FileInputStream(file));
            ImageIO.write(image,format,response.getOutputStream());
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @RequestMapping("/down/{fileId}")
    public String down(HttpServletResponse response,@PathVariable("fileId") int fileId)throws Exception{
//        String path = "F:\\tupian";
//        F:\load\5310f4b2-9e1d-4ade-87f9-26440abc73b36.jpg
//        String filename="7.jpg";
//        String path="F:\\load\\5310f4b2-9e1d-4ade-87f9-26440abc73b36.jpg";
        //respose响应头
        String path=fileService.queryByfileId(fileId).getHdfsPath();
        response.reset();//设置不缓存
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(path,"UTF-8"));
        File file = new File(path);
        InputStream input = new FileInputStream(file);
        OutputStream output = response.getOutputStream();
        byte[] buff = new byte[1024];
        int index=0;
        while((index=input.read(buff))!=-1)
        {
            output.write(buff,0,index);
            output.flush();
        }
        output.close();
        input.close();
        return null;
    }
    @RequestMapping("/huifu/{fileId}")
    public String huifu(@PathVariable("fileId") int fileId)
    {
        fileService.changeStatus(fileId,1,new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
        return "recycle";
    }
//    永久删除
    @RequestMapping("/truedelete/{fileId}")
    public String turedelete(@PathVariable("fileId") int fileId)
    {
        com.czq.pojo.File file1=fileService.queryByfileId(fileId);
        fileService.deleteById(fileId);//数据库删除
        try {
            new File(file1.getHdfsPath()).delete();//执行IO类中文件删除
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return "recycle";
    }

    @RequestMapping("recycle")
    public String Recycle(Model model)
    {
        List<com.czq.pojo.File> filemove=fileService.queryFileRemove();
        model.addAttribute("filemove",filemove);
        return "recycle";
    }
    @RequestMapping("/deletefile/{fileId}")
    public String deleteFile(@PathVariable("fileId") int fileId)
    {

        System.out.println("fileId = " + fileId);
        fileService.changeStatus(fileId,0,new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
        return "redirect:/file/all";
    }

    @RequestMapping("torecycle")
    public String toRecycle()
    {
        return "redirect:/file/recycle";
    }

    @RequestMapping("tofileupload")
    public String toFileupload()
    {
        return "fileupload";
    }

    @RequestMapping("tofiledownload")
    public String toFileDownload()
    {
        return "filedownload";
    }
}
