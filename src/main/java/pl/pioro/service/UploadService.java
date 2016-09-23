package pl.pioro.service;

import org.springframework.web.multipart.MultipartFile;

import pl.pioro.model.UploadedFileDTO;

public interface UploadService {

	
	public UploadedFileDTO saveFile(MultipartFile file, String dirPath, String validity, String sourceIp, String sessionId) ;
	
	public UploadedFileDTO getFile(String uuid);
	
}
