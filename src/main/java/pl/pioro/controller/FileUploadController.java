package pl.pioro.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.pioro.exceptions.FileWithIdNotFoundException;
import pl.pioro.model.DownloadedFileDTO;
import pl.pioro.model.UploadedFileDTO;
import pl.pioro.service.MyDateUtils;
import pl.pioro.service.UploadService;

@Controller
@PropertySource("classpath:application.properties")
public class FileUploadController {
	
	@Value("${uploaed.dir.parh}")
	private String uploadFilePath;

	@Autowired
	private UploadService uploadService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	private final String getFileURI="/get";

	@RequestMapping(value="/getDetails/{uuid}", method=RequestMethod.GET)
	public ModelAndView getDetailsOfFile(@PathVariable String uuid, HttpServletRequest req){
		UploadedFileDTO fileDTO = uploadService.getFile(uuid);
		List<DownloadedFileDTO> downloads = uploadService.getFileDownloads(uuid);
		ModelAndView modelAndView = new ModelAndView("getDetails");
		if(fileDTO!=null){
	    	modelAndView.addObject("success", true);
	        modelAndView.addObject("FILE_ID", fileDTO.getUuid());
	        modelAndView.addObject("FILE_SIZE", fileDTO.getFileSize());
	        modelAndView.addObject("FILE_NAME", fileDTO.getFileName());
	        modelAndView.addObject("FILE_EXPR", MyDateUtils.printDateTime(fileDTO.getFileExpr()));
	        modelAndView.addObject("FILE_UPLOAD_START", MyDateUtils.printDateTime(fileDTO.getUploadStart()));
	        modelAndView.addObject("FILE_UPLOAD_STOP", MyDateUtils.printDateTime(fileDTO.getUploadStop()));
	        
	        if(fileDTO.getDeleted()==0){
	        	modelAndView.addObject("FILE_STATUS", "ACTIVE");
	        	modelAndView.addObject("FILE_URL", getURLForID(req,fileDTO.getUuid()));
	        }else{
	        	modelAndView.addObject("FILE_STATUS", "DELETED");
	        	modelAndView.addObject("FILE_URL", "URL NOT AVAILABLE - FILE DELETED");
	        }
	        
	        
	        
	        
		}else{
	    	modelAndView.addObject("success", false);
		}
		
		modelAndView.addObject("FILE_DOWNLOADS",downloads);
		
		return modelAndView;
	}
	
	@RequestMapping(value=getFileURI+"/{fileID}", method=RequestMethod.GET)
	public void  getFile(@PathVariable String fileID, HttpServletResponse response, HttpServletRequest req) throws IOException, FileWithIdNotFoundException {
		logger.info("New request from: ["+req.getRemoteAddr()+"] searching ID: ["+fileID+"]");
			UploadedFileDTO fileDTO = uploadService.getFile(fileID);
			if(fileDTO!=null && new File(uploadFilePath,fileDTO.getUuid()).isFile()){
				String fileName = fileDTO.getFileName();
				/** Zapis informacji o pobraniu pliku*/
				DownloadedFileDTO dFile = new DownloadedFileDTO();
				dFile.setFileName(fileDTO.getFileName());
				dFile.setDownloadStart(new Date());
				dFile.setUuid(fileDTO.getUuid());
				dFile.setSessionId(req.getSession(true).getId());
				dFile.setSourceIp(req.getRemoteAddr());
				
				
				logger.info("Under id: ["+fileID+"] there is a file: ["+fileName+"], fileSzie: ["+fileDTO.getFileSize()+"]");
				File file = new File(uploadFilePath,fileDTO.getUuid());
				response.setHeader("Content-Disposition", "attachment; filename="+fileName); 
				InputStream is = new FileInputStream(file);
				// MIME type of the file
				response.setContentType("application/octet-stream");
				// Response header
				response.setHeader("Content-Disposition", "attachment; filename=\""	+ fileName + "\"");
				// Read from the file and write into the response
				OutputStream os = response.getOutputStream();
				byte[] buffer = new byte[1024];
				int len;
				while ((len = is.read(buffer)) != -1) {
					os.write(buffer, 0, len);
				}
				os.flush();
				os.close();
				is.close();
				dFile.setDownloadStop(new Date());
				uploadService.saveDownoadedFile(dFile);
				logger.info("Request: ["+fileID+"] from: ["+req.getRemoteAddr()+"] completed");	
			}else{
				logger.warn("File with uuid: ["+fileID+"] not found!!!");
				FileWithIdNotFoundException exc = new FileWithIdNotFoundException("File: ["+fileID+"] not found exception",fileID);
				throw exc;
//				response.sendRedirect(getURLForException(req,exc));
			}
			
			
			
	    }
	
    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }
    
    
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("validity") String validity, HttpServletRequest req, @RequestParam("email") String email){
    	String name=file.getOriginalFilename();
    	logger.info("#### New request upload: ["+name+"] from: ["+req.getRemoteAddr()+"], validity: ["+validity+"]");
    	ModelAndView modelAndView = new ModelAndView("result");
    	modelAndView.addObject("time",  new Date());
    	modelAndView.addObject("success", false);
    	UploadedFileDTO fileDTO = uploadService.saveFile(file, uploadFilePath, validity, req.getRemoteAddr(), req.getSession(true).getId(), email);
    	modelAndView.addObject("success", true);
        modelAndView.addObject("FILE_ID", fileDTO.getUuid());
        modelAndView.addObject("FILE_URL", getURLForID(req,fileDTO.getUuid()));
        modelAndView.addObject("FILE_SIZE", new File(uploadFilePath,fileDTO.getUuid()).length());
        modelAndView.addObject("FILE_NAME", fileDTO.getFileName());
        modelAndView.addObject("FILE_EXPR", MyDateUtils.printDateTime(fileDTO.getFileExpr()));
        logger.info("#### File: ["+fileDTO.getFileName()+"] saved to ["+new File(uploadFilePath,fileDTO.getUuid()).getAbsolutePath()+"]");
    	return modelAndView;
    	
    }
    
    
    
    private String getURLForID(HttpServletRequest req, String id){
    	String scheme = req.getScheme();             // http
        String serverName = req.getServerName();     // hostname.com
        int serverPort = req.getServerPort();        // 80
        String contextPath = req.getContextPath(); 
        // Reconstruct original requesting URL
        StringBuffer url =  new StringBuffer();
        url.append(scheme).append("://").append(serverName);

        if ((serverPort != 80) && (serverPort != 443)) {
            url.append(":").append(serverPort);
        }
        url.append(contextPath);
        url.append(getFileURI).append("/").append(id);
        return url.toString();
    }
    
    private String getURLForException(HttpServletRequest req, FileWithIdNotFoundException exc){
    	String scheme = req.getScheme();             // http
        String serverName = req.getServerName();     // hostname.com
        int serverPort = req.getServerPort();        // 80
        String contextPath = req.getContextPath(); 
        // Reconstruct original requesting URL
        StringBuffer url =  new StringBuffer();
        url.append(scheme).append("://").append(serverName);

        if ((serverPort != 80) && (serverPort != 443)) {
            url.append(":").append(serverPort);
        }
        url.append(contextPath);
        url.append("/errorMsg?message=").append(exc.getMessage());
        return url.toString();
    }
    
    

    @PostConstruct
    public void init(){
    	logger.info("Uploaded files will be stored in ["+getUploadFilePath()+"]");
    }

    public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}
    public String getUploadFilePath() {
		return uploadFilePath;
	}
    
    
    
    @RequestMapping("/")
	public ModelAndView index() {
    	ModelAndView modelAndView = new ModelAndView("index");
    	modelAndView.addObject("time",  new Date());
    	modelAndView.addObject("message",  "HELLO WORLD");
		return modelAndView;
	}
    
}
