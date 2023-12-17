package com.dodo.api.helpers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

public class FileHelper {
	public static String generateFileName(String fileName) {
		var extension = fileName.substring(fileName.lastIndexOf("."));		
		return UUID.randomUUID().toString().replace("-", "")+extension;
	}
	
	public static String saveImageFile(MultipartFile file) throws Exception {
		try {
			File imagesFolder = new File(new ClassPathResource(".").getFile().getPath() + "/static/upload");
//			System.out.println(imagesFolder.getAbsolutePath());
			if (!imagesFolder.exists()) {
				imagesFolder.mkdirs();
			}
			File saveFile = new ClassPathResource("static/upload").getFile();
			var fileName = FileHelper.generateFileName(file.getOriginalFilename());
//			System.out.println(saveFile.getAbsolutePath());
//			System.out.println(saveFile.getAbsolutePath()+File.separator+fileName);
			Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+fileName);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Fail to save image!");
		}
	}
	
	public static boolean deleteImageFile(String fileName) throws Exception {
		try {
			File imagesFolder = new File(new ClassPathResource(".").getFile().getPath() + "/static/upload");
			if (imagesFolder.exists()) {
				File deleteFile = new ClassPathResource("static/upload").getFile();
				Path path = Paths.get(deleteFile.getAbsolutePath()+File.separator+fileName);
				
				if(Files.exists(path)) {
					Files.delete(path);
					return true;
				}else {
					return false;
				}	
			}	
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Fail to delete image!");
		}
	}
}
