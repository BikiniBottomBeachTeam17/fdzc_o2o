package com.mustar.controller;

import cn.hutool.core.lang.UUID;
import com.mustar.common.lang.Result;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@RestController
@RequestMapping("/file")
public class FileController {
    public static final String SECRRET_ID="AKIDWuJelcDZeRzFFxNWu3xf5F9m9GAuPdZF";
    public static final String SECRET_KEY="VmoY1EBrGNEafT0HL8evxiCTcWBcniX8";
    public static final String BUCKET_NAME="ycxy-1307945987";
    public static final String REGION="ap-nanjing";
    public static final String PATH="https://ycxy-1307945987.cos.ap-nanjing.myqcloud.com";
    //文件上传
    @RequestMapping("/upload")
    public Result upload(@RequestParam("picture") MultipartFile picture, HttpServletRequest request) {
        //获取文件在服务器的储存位置
        String path = "C:\\Users\\chen\\Desktop\\新建文件夹";
        File filePath = new File(path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            filePath.mkdir();
        }
        //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();
        //获取文件类型，以最后一个`.`为标识
        assert originalFileName != null;
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(originalFileName.lastIndexOf("/") + 1);
        String fileName = UUID.randomUUID() + ".jpg";
        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);
        //将文件保存到服务器指定位置
        try {
            picture.transferTo(targetFile);
            //将文件在服务器的存储路径返回
            return Result.success("/file/viewPhoto/"+fileName);
        } catch (IOException e) {
            return Result.error(-1, e.getLocalizedMessage());
        }
    }
    @RequestMapping("/upImg")
    public Result upImg(@RequestParam("picture") MultipartFile file) {
        if(file == null){
            return Result.error(-1,"文件为空");
        }
        String newFileName = UUID.randomUUID()+".jpg";
        String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(SECRRET_ID, SECRET_KEY);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(REGION));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式

        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        File localFile = null;
        try {
            localFile = File.createTempFile("temp",null);
            file.transferTo(localFile);
            // 指定要上传到 COS 上的路径
            String key = date+"/"+newFileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, key, localFile);
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            return Result.success(PATH +"/"+ putObjectRequest.getKey());
        } catch (IOException e) {
            return Result.error(-1,e.getMessage());
        }finally {
            // 关闭客户端(关闭后台线程)
            cosclient.shutdown();
        }

    }
    @RequestMapping("/viewPhoto/{photopath}")
    public void getFeedBackPicture(HttpServletResponse response, @PathVariable("photopath")String photopath) throws Exception{
        String realPath="C:\\Users\\chen\\Desktop\\新建文件夹\\"+photopath;
        FileInputStream inputStream = new FileInputStream(realPath);
        int i = inputStream.available();
        //byte数组用于存放图片字节数据
        byte[] buff = new byte[i];
        inputStream.read(buff);
        //记得关闭输入流
        inputStream.close();
        //设置发送到客户端的响应内容类型
        response.setContentType("image/*");
        OutputStream out = response.getOutputStream();
        out.write(buff);
        //关闭响应输出流
        out.close();
    }
}