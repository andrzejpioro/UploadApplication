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

@Entity
@Table(name="downloaded_files")
public class DownloadedFileDTO {

	@Id
	@Column(name="download_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer fileId;
	
	@Column(name="fileName")
	private String fileName;
	
	@Column(name="session_id")
	private String sessionId;

	@Column(name="uuid")
	private String uuid;
	
	@Column(name="source_ip")
	private String sourceIp;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="download_start")
	private Date downloadStart;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="download_stop")
	private Date downloadStop;
	
	
	public Integer getFileId() {
		return fileId;
	}


	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}


	public String getSessionId() {
		return sessionId;
	}


	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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


	


	public Date getDownloadStart() {
		return downloadStart;
	}


	public void setDownloadStart(Date downloadStart) {
		this.downloadStart = downloadStart;
	}


	public Date getDownloadStop() {
		return downloadStop;
	}


	public void setDownloadStop(Date downloadStop) {
		this.downloadStop = downloadStop;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "DownloadedFileDTO [fileName="+fileName+", fileId=" + fileId + ", sessionId=" + sessionId + ", uuid=" + uuid + ", sourceIp="
				+ sourceIp + ", downloadStart=" + downloadStart + ", downloadStop=" + downloadStop + "]";
	}

	
	
}