package pl.pioro.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import pl.pioro.model.DownloadedFileDTO;
import pl.pioro.model.UploadedFileDTO;

public interface UploadService {

	
	public UploadedFileDTO saveFile(MultipartFile file, String dirPath, String validity, String sourceIp, String sessionId, String email) ;
	
	public UploadedFileDTO getFile(String uuid);
	
	public DownloadedFileDTO saveDownoadedFile(DownloadedFileDTO file);
	
	public List<DownloadedFileDTO> getFileDownloads(String uuid);
	
}
