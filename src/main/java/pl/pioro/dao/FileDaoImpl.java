package pl.pioro.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.pioro.model.UploadedFileDTO;

//@Transactional
//@Component
public class FileDaoImpl implements FileDao {

	@Autowired
  	private SessionFactory sessionFactory;
	
	private Log log = LogFactory.getLog(getClass());
	
	@Override
	public UploadedFileDTO insert(UploadedFileDTO file) {
		sessionFactory.getCurrentSession().saveOrUpdate(file);
		return file;
	}
	
	
	@Override
	public UploadedFileDTO getByUUID(String uuid) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UploadedFileDTO.class);
		criteria.add(Restrictions.eq("uuid",uuid));
		@SuppressWarnings("unchecked")
		List<UploadedFileDTO> files = criteria.list();
		if(!files.isEmpty()){
			log.info("Recored loaded: ["+files.get(0));
			return files.get(0);
		}
		else
			return null;
	}
	
	

	@Override
	public UploadedFileDTO delete(UploadedFileDTO file) {
		Session session =  sessionFactory.getCurrentSession();
		session.delete(session.load(UploadedFileDTO.class, file.getUuid()));
		return file;
	}
	

	@Override
	public List<UploadedFileDTO> getActiveFiles() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UploadedFileDTO.class);
		Criterion fileExpCriteria =Restrictions.gt("fileExpr", new Date()); 
		Criterion fileExpNullCriteria = Restrictions.isNull("fileExpr");
		criteria.add(Restrictions.or(fileExpCriteria, fileExpNullCriteria));
		@SuppressWarnings("unchecked")
		List<UploadedFileDTO> files = criteria.list();
		return files;
	}
	
	public java.util.List<UploadedFileDTO> getExiredFiles() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UploadedFileDTO.class);
		Criterion fileExpCriteria =Restrictions.lt("fileExpr", new Date()); 
		criteria.add(fileExpCriteria);
		@SuppressWarnings("unchecked")
		List<UploadedFileDTO> files = criteria.list();
		return files;
	};
	

}
