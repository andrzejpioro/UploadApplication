package pl.pioro.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pl.pioro.model.DownloadedFileDTO;

public interface DownloadDao extends CrudRepository<DownloadedFileDTO, Long> {
	
	@Query("select d from DownloadedFileDTO d where d.uuid = ?1")
	public List<DownloadedFileDTO> getDownloadsForFile(String uuid);
	
}
