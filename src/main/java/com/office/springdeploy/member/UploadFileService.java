package com.office.springdeploy.member;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UploadFileService {
	
	@Value("${server.name}")
	private String serverName;
	
	@Value("${server.static.profile-img}")
	private String serverStaticProfileImg;
	
	@Value("${server.dir-separator}")
	private String serverDirSeparator;
	
	public String upload(String m_id, MultipartFile file) {
		log.info("upload()");
		
		boolean result = false;
		
		String fileOriName = file.getOriginalFilename();
		String fileExtension = fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length());
		
		// Windows
//		String uploadDir = "c:\\member\\profile\\" + m_id;
		
		// Ubuntu
//		String uploadDir = "/member/profile/" + m_id;
		
		/*
		String uploadDir = null;
		if (serverName.equals(AppConfig.SERVER_NAME_LOCAL)) {
			uploadDir = "c:\\member\\profile\\" + m_id;
			
		} else if (serverName.equals(AppConfig.SERVER_NAME_DEV)) {
			uploadDir = "/member/profile/" + m_id;
			
		} else if (serverName.equals(AppConfig.SERVER_NAME_PROD)) {
			uploadDir = "/member/profile/" + m_id;
			
		}
		*/
		
		String uploadDir = serverStaticProfileImg + m_id;
		
		UUID uuid = UUID.randomUUID();
		String uniqueFileName = uuid.toString().replaceAll("-", "");
		
		// Windows
//		File saveFile = new File(uploadDir + "\\" + uniqueFileName + fileExtension);
		
		// Ubuntu
//		File saveFile = new File(uploadDir + "/" + uniqueFileName + fileExtension);
		
		/*
		File saveFile = null;
		if (serverName.equals(AppConfig.SERVER_NAME_LOCAL)) {
			saveFile = new File(uploadDir + "\\" + uniqueFileName + fileExtension);
			
		} else if (serverName.equals(AppConfig.SERVER_NAME_DEV)) {
			saveFile = new File(uploadDir + "/" + uniqueFileName + fileExtension);
			
		} else if (serverName.equals(AppConfig.SERVER_NAME_PROD)) {
			saveFile = new File(uploadDir + "/" + uniqueFileName + fileExtension);
			
		}
		*/
		
//		File saveFile = new File(uploadDir + serverDirSeparator + uniqueFileName + fileExtension);
		
		File saveFile = new File(uploadDir
								.concat(serverDirSeparator)
								.concat(uniqueFileName)
								.concat(fileExtension));
		
		if(!saveFile.exists())
			saveFile.mkdirs();
		
		try {
			
			file.transferTo(saveFile);
			result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		if (result) {
			log.info("FILE UPLOAD SUCCESS!!");
			return uniqueFileName + fileExtension;
			
		} else {
			log.info("FILE UPLOAD FAIL!!");
			return null;
			
		}
        
	}
	
}
