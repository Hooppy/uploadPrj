package kr.co.upload.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	@GetMapping("/uForm")
	public String uploadForm() {
		
		return "uploadForm";
	}
	
	@PostMapping("/upload")
	public String upload(@RequestPart MultipartFile file1,
						 @RequestPart MultipartFile[] file2) throws IOException {
		
		if(!file1.isEmpty()) {
        	String fileNm1 = getImgFileNm(file1);
        	file1.transferTo(new File(fileNm1));
        }
		
		if(!file2[0].isEmpty()) {
	    	for(int i=0; i<file2.length; i++) {
	    		String fileNm2 = getImgFileNm(file2[i]);
		        file2[i].transferTo(new File(fileNm2));
			}
		}
		
		return "index";
	}
	
	public String getImgFileNm(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		
		UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString() + extension;
        
        return newFileName;
	}
}
