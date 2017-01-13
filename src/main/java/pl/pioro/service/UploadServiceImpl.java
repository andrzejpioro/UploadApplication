package pl.pioro.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pl.pioro.dao.DownloadDao;
import pl.pioro.dao.FileNewDao;
import pl.pioro.model.DownloadedFileDTO;
import pl.pioro.model.UploadedFileDTO;
import pl.pioro.model.ValidityEnum;

@Service
public class UploadServiceImpl implements UploadService {

	@Value("${uploaed.dir.parh}")
	private String uploadFilePath;
	
	@Autowired FileNewDao fileDao;
	@Autowired DownloadDao downloadDao;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public UploadedFileDTO saveFile(MultipartFile file, String dirPath, String validity, String sourceIp, String sessionId, String email) {
		UploadedFileDTO dto = new UploadedFileDTO();
		dto.setFileDir(dirPath);
		dto.setFileName(file.getOriginalFilename());
		dto.setSourceIp(sourceIp);
		dto.setUuid(UUID.randomUUID().toString());
		dto.setUploadStart(new Date());
		dto.setFileSize(file.getSize());
		dto.setSessionId(sessionId);
		dto.setEmail(email);
		ValidityEnum validityEnum = ValidityEnum.fromValue(validity);
		dto.setFileExpr(getExpirationDate(validityEnum));
		saveInFileSystem(dto,dirPath,file);
		dto.setUploadStop(new Date());

		fileDao.save(dto);
		logger.debug("File saved: ["+dto+"]");
		return dto;
	}


	@Override
	public DownloadedFileDTO saveDownoadedFile(DownloadedFileDTO file) {
		file = downloadDao.save(file);
		logger.info("Download action saved: ["+file+"]");
		return file;
	}
	
	
	@Override
	public List<DownloadedFileDTO> getFileDownloads(String uuid) {
		return downloadDao.getDownloadsForFile(uuid);
	}
	
	
	
	@Override
	public UploadedFileDTO getFile(String uuid) {
		return fileDao.getByUuid(uuid);
	}
	
	private void saveInFileSystem(UploadedFileDTO dto, String dirPath, MultipartFile file){
		File outputDir= new File(dirPath);
    	if(!outputDir.exists()){
    		logger.info("Creating upload dir: ["+outputDir.getAbsolutePath()+"]");
    		outputDir.mkdirs();
    	}
    	try {
    	 byte[] bytes = file.getBytes();
         logger.info("File will be stored in file: ["+new File(outputDir,dto.getUuid()).getAbsolutePath()+"]");
         BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(outputDir,dto.getUuid())));
         stream.write(bytes);
         stream.close();
         logger.info("Receiving file id :["+dto.getUuid()+"] completed");
    	} catch (IOException e){
    		logger.error(e.getMessage());
    	}
    	
	}

	
	public Date getExpirationDate(ValidityEnum validity){
		GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
		Date date = new Date();
		switch (validity){
			case ONE_MIN:{
				cal.add(Calendar.MINUTE, 1);
				date.setTime(cal.getTime().getTime());
				break;
			}
			case ONE_HOUR:{
				cal.add(Calendar.HOUR, 1);
				date.setTime(cal.getTime().getTime());
				break;
			}
			case ONE_DAY:{
				cal.add(Calendar.HOUR, 24);
				date.setTime(cal.getTime().getTime());
				break;
			}
			case SEVEN_DAYS:{
				cal.add(Calendar.DAY_OF_YEAR, 7);
				date.setTime(cal.getTime().getTime());
				break;
			}
			case THIRTY_DAYS:{
				cal.add(Calendar.DAY_OF_YEAR, 30);
				date.setTime(cal.getTime().getTime());
				break;
			}
			case UNLIMITED:{
				date = null;
				break;
			}
		}
		return date;
	}
	
	
}
