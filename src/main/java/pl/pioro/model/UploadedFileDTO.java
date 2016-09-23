package pl.pioro.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="files", 
		uniqueConstraints = {	
        @UniqueConstraint(columnNames = "file_id"),
        @UniqueConstraint(columnNames = "uuid") }
		)
public class UploadedFileDTO {

	
	@Column(name="file_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer fileId;
	
	@Column(name="file_name")
	private String fileName;
	
	@Column(name="file_dir")
	private String fileDir;
	
	@Column(name="file_size")
	private Long fileSize;
	
	@Id
	@Column(name="uuid")
	private String uuid;
	
	@Column(name="source_ip")
	private String sourceIp;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="upload_start")
	private Date uploadStart;
	
	@Column(name="upload_stop")
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadStop;

	@Column(name="file_expr")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fileExpr;

	@Column(name="session_id")
	private String sessionId;
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public Integer getFileId() {
		return fileId;
	}



	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public String getFileDir() {
		return fileDir;
	}



	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}



	public String getUuid() {
		return uuid;
	}



	public void setUuid(String uuid) {
		this.uuid = uuid;
	}



	public String getSourceIp() {
		return sourceIp;
	}



	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}




	public Date getFileExpr() {
		return fileExpr;
	}



	public void setFileExpr(Date fileExpr) {
		this.fileExpr = fileExpr;
	}


	public Date getUploadStart() {
		return uploadStart;
	}



	public void setUploadStart(Date uploadStart) {
		this.uploadStart = uploadStart;
	}



	public Date getUploadStop() {
		return uploadStop;
	}



	public void setUploadStop(Date uploadStop) {
		this.uploadStop = uploadStop;
	}

	public Long getFileSize() {
		return fileSize;
	}
	
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	@Override
	public String toString() {
		return "UploadedFileDTO [fileId=" + fileId + ", fileName=" + fileName + ", fileDir=" + fileDir + ", fileSize="
				+ fileSize + ", uuid=" + uuid + ", sourceIp=" + sourceIp + ", uploadStart=" + uploadStart
				+ ", uploadStop=" + uploadStop + ", fileExpr=" + fileExpr + ", sessionId=" + sessionId + "]";
	}


}
