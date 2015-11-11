package shu.nova.model;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shu.cloud.model.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * TaskFileData entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see shu.nova.model.TaskFileData
 * @author MyEclipse Persistence Tools
 */
public class TaskFileDataDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TaskFileDataDAO.class);
	// property constants
	public static final String FILE_PATH = "filePath";
	public static final String UUID = "uuid";
	public static final String STATUS = "status";
	public static final String PROGRESS = "progress";
	public static final String XML_PATH = "xmlPath";
	public static final String TXT_PATH = "txtPath";
	public static final String TXT_DOWNLOAD_PATH = "txtDownloadPath";
	public static final String XLS_PATH = "xlsPath";
	public static final String XLS_DOWNLOAD_PATH = "xlsDownloadPath";
	public static final String FILE_NAME = "fileName";
	public static final String TOTAL_COUNT = "totalCount";
	public static final String DONE_COUNT = "doneCount";

	public void save(TaskFileData transientInstance) {
		log.debug("saving TaskFileData instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TaskFileData persistentInstance) {
		log.debug("deleting TaskFileData instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TaskFileData findById(java.lang.Integer id) {
		log.debug("getting TaskFileData instance with id: " + id);
		try {
			TaskFileData instance = (TaskFileData) getSession().get(
					"shu.nova.model.TaskFileData", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TaskFileData instance) {
		log.debug("finding TaskFileData instance by example");
		try {
			List results = getSession()
					.createCriteria("shu.nova.model.TaskFileData")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TaskFileData instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TaskFileData as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFilePath(Object filePath) {
		return findByProperty(FILE_PATH, filePath);
	}

	public List findByUuid(Object uuid) {
		return findByProperty(UUID, uuid);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByProgress(Object progress) {
		return findByProperty(PROGRESS, progress);
	}

	public List findByXmlPath(Object xmlPath) {
		return findByProperty(XML_PATH, xmlPath);
	}

	public List findByTxtPath(Object txtPath) {
		return findByProperty(TXT_PATH, txtPath);
	}

	public List findByTxtDownloadPath(Object txtDownloadPath) {
		return findByProperty(TXT_DOWNLOAD_PATH, txtDownloadPath);
	}

	public List findByXlsPath(Object xlsPath) {
		return findByProperty(XLS_PATH, xlsPath);
	}

	public List findByXlsDownloadPath(Object xlsDownloadPath) {
		return findByProperty(XLS_DOWNLOAD_PATH, xlsDownloadPath);
	}

	public List findByFileName(Object fileName) {
		return findByProperty(FILE_NAME, fileName);
	}

	public List findByTotalCount(Object totalCount) {
		return findByProperty(TOTAL_COUNT, totalCount);
	}

	public List findByDoneCount(Object doneCount) {
		return findByProperty(DONE_COUNT, doneCount);
	}

	public List findAll() {
		log.debug("finding all TaskFileData instances");
		try {
			String queryString = "from TaskFileData";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TaskFileData merge(TaskFileData detachedInstance) {
		log.debug("merging TaskFileData instance");
		try {
			TaskFileData result = (TaskFileData) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TaskFileData instance) {
		log.debug("attaching dirty TaskFileData instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TaskFileData instance) {
		log.debug("attaching clean TaskFileData instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}