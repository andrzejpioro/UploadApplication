package pl.pioro.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pl.pioro.model.UploadedFileDTO;

@Transactional
public interface FileNewDao extends CrudRepository<UploadedFileDTO, Long>{

	
	public UploadedFileDTO getByUuid(String uuid);
	
	@Query("select f from UploadedFileDTO f where f.fileExpr < ?1  and f.deleted = 0")
	public List<UploadedFileDTO> getExiredFiles(Date data);
	
	@Query("select f from UploadedFileDTO f where (f.fileExpr > ?1 or f.fileExpr is null) and f.deleted = 0 ")
	public List<UploadedFileDTO> getActiveFiles(Date data);
	
	@Modifying
	@Query("UPDATE UploadedFileDTO set deleted = 1 where uuid= ?1 ")
	void delete(String uuid);
}
