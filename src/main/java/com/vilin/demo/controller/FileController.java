package com.vilin.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {

	@RequestMapping("upload")
	@ResponseBody
	public String upload(@RequestParam("lwfile") MultipartFile file) {
		System.out.println("file.name = " + file.getOriginalFilename());
		//获取文件名
		String fileName = file.getOriginalFilename();
		//获取文件后缀
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		//文件上传路劲
		String filePath = "d:/upload/";
		//文件上传全路径名
		fileName = filePath + UUID.randomUUID() + fileName;
		//文件对象
		File dest = new File(fileName);
		
		if(!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		    
		try {
			file.transferTo(dest);
			return "上传成功";
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "上传失败";
	}
	
	@RequestMapping("download")
	public void download(HttpServletResponse response) throws Exception {
		File file = new File("C:\\Users\\XPRO\\Desktop\\011001900211-44106504.pdf");
		FileInputStream fis = new FileInputStream(file);
		response.setContentType("application/force-download");
		//设置下载的文件名
		response.addHeader("Content-disposition", "attachment;fileName=011001900211-44106504.pdf");
		OutputStream os = response.getOutputStream();
		byte[] buf = new byte[1024];
		int len = 0;
		while((len = fis.read(buf)) != -1) {
			os.write(buf, 0, len);
		}
	}
}
