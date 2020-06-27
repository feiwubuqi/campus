package com.campus.domain.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RestController
@RequestMapping("upload")
public class FileUpload {
private final static String path="C:\\Users\\求己\\Desktop\\影视资料\\09 微服务电商【黑马乐优商城】·\\leyou-manage-web\\static";
    @RequestMapping("/img")
    public String  uploadImg(MultipartFile file){
        File f=new File(new File(path).getAbsoluteFile()+"/"+file.getOriginalFilename());
        try {
            file.transferTo(f);
            f.exists();
        }catch (IOException e){
            System.out.println(e);
        }
        return "上传成功";
    }

    @RequestMapping("/removeImg")
    public String  removeImg(@RequestBody Map<String,String> map){
        System.out.println(map);
        File file=new File(path+"/"+map.get("name"));
        if (file.exists()){
            file.delete();
            return "删除成功！";
        }
        return "文件不存在！";
    }

}
