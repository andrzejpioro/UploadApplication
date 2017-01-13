package pl.pioro.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.pioro.dao.FileNewDao;
import pl.pioro.model.UploadedFileDTO;

@Component
public class HouseKeepingComponent {

	@Value("${uploaed.dir.parh}")
	private String uploadFilePath;
	
	@Autowired
	FileNewDao fileDao;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Scheduled(fixedDelay=1000*60*20 )
	public void deleteExpiredFiles(){
//		logger.info("Checking expired files");
		List<UploadedFileDTO> expiredFiles = fileDao.getExiredFiles(new Date());
		if(!expiredFiles.isEmpty()){
			logger.info("Found: ["+expiredFiles.size()+"] expired files");
			for(UploadedFileDTO fileToDelete: expiredFiles){
				logger.info("Deleting file: ["+fileToDelete.getFileName()+"], size: ["+fileToDelete.getFileSize()+"], uuid: ["+fileToDelete.getUuid()+"]");
				File f = new File(uploadFilePath,fileToDelete.getUuid());
				boolean deleted = f.delete();
				fileDao.delete(fileToDelete.getUuid());
				logger.info("Deleting file: ["+fileToDelete.getFileName()+"], size: ["+fileToDelete.getFileSize()+"], uuid: ["+fileToDelete.getUuid()+"] completed with result: ["+deleted+"]");
			}
		}
	}
	
	
//	@Scheduled(fixedDelay=1000*10 )
	@Scheduled(fixedDelay=1000*60*60*4)
	public void listActiveFiles(){
		List<UploadedFileDTO> activeFiles = fileDao.getActiveFiles(new Date());
		if(activeFiles.size()>0){
			logger.info("Listing active files..");
			logger.info("There are: ["+activeFiles.size()+"] active files");
			for(UploadedFileDTO f : activeFiles){
				logger.info("Active uuid: ["+f.getUuid()+"], fileName: ["+f.getFileName()+"], size: ["+f.getFileSize()+"], ip: ["+f.getSourceIp()+"], email: ["+f.getEmail()+"], uploaded: ["+MyDateUtils.printDateTime(f.getUploadStart())+"], expire: ["+MyDateUtils.printDateTime(f.getFileExpr())+"]");
			}	
		}
		
	}

}
