package pl.pioro.dao;

import java.util.List;

import pl.pioro.model.UploadedFileDTO;

public interface FileDao {

	public UploadedFileDTO insert(UploadedFileDTO file); 
	public UploadedFileDTO delete(UploadedFileDTO file); 
	public UploadedFileDTO getByUUID(String uuid);
	
	public List<UploadedFileDTO> getExiredFiles();
	public List<UploadedFileDTO> getActiveFiles();
}
